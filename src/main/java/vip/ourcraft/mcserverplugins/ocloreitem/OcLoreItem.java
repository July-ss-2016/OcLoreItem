package vip.ourcraft.mcserverplugins.ocloreitem;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public class OcLoreItem extends JavaPlugin {
    private static OcLoreItem instance;
    private Logger logger = getLogger();
    private Settings settings;
    private ItemManager itemManager;

    public void onEnable() {
        instance = this;
        this.settings = new Settings();
        this.itemManager = new ItemManager(this);

        loadConfig();
        init();
        Bukkit.getPluginManager().registerEvents(new ItemListener(this), this);
        getCommand("ocloreitem").setExecutor(new AdminCommand(this));
        logger.info("初始化完毕!");
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public boolean loadConfig() {
        saveDefaultConfig();
        reloadConfig();

        File myItemsConfigFile = new File(getDataFolder().getParent(), "MyItems" + File.separator + "Configuration" + File.separator + "config.yml");

        if (!myItemsConfigFile.exists()) {
            logger.severe("MyItems 文件夹不存在!");
            return false;
        }

        YamlConfiguration myItemsConfigYml = YamlConfiguration.loadConfiguration(myItemsConfigFile);
        YamlConfiguration pluginConfigYml = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "config.yml"));

        settings.setMyItemsStatsFormatValue(Util.translateColorCode(myItemsConfigYml.getString("Configuration.Stats.Format_Value"))); // stats的正则表达式；like this: "&7<stats>&f&l: <value>"
        settings.setStatsIdentifierExp(pluginConfigYml.getString("stats.identifier_exp"));

        for (String particleName : pluginConfigYml.getConfigurationSection("particles").getKeys(false)) {
            settings.addParticle(particleName, pluginConfigYml.getStringList("particles." + particleName + ".items"));
        }

        logger.info("读取到了 " + settings.getParticles().size() + "个 粒子特效.");
        logger.info("配置载入完毕!");
        return true;
    }

    public boolean init() {
        ItemStatType.EXP_ADDITION.setChineseName(settings.getStatsIdentifierExp());
        logger.info("初始化完毕!");
        return true;
    }

    public Settings getSettings() {
        return settings;
    }

    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        logger.info("插件已被卸载!");
    }

    public static OcLoreItem getInstance() {
        return instance;
    }
}

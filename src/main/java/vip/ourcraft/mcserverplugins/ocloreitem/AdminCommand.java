package vip.ourcraft.mcserverplugins.ocloreitem;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class AdminCommand implements CommandExecutor {
    private OcLoreItem plugin;
    private Settings settings;

    public AdminCommand(OcLoreItem plugin) {
        this.plugin = plugin;
        this.settings = plugin.getSettings();
    }

    @Override
    public boolean onCommand(CommandSender cs, Command command, String s, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
            sendHelpMessage(cs);
        }

        if (cs.hasPermission("OcLoreItem.admin")) {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                plugin.loadConfig();
                plugin.init();
                Util.sendMessage(cs, "重载完成.");
                return true;
            }

            if (args.length > 1 && args[0].equalsIgnoreCase("stats")) {
                if (!(cs instanceof Player)) {
                    Util.sendMessage(cs, "命令执行者必须是玩家!");
                    return true;
                }

                Player player = (Player) cs;
                ItemStack item = player.getInventory().getItemInMainHand();
                ItemMeta itemMeta = item.getItemMeta();

                if (item.getType() == Material.AIR) {
                    Util.sendMessage(player, "目标物品不能为空气!");
                    return true;
                }

                switch (args[1]) {
                    case "add":
                        if (args.length == 4) {
                            String statName = args[2];
                            int level;
                            ItemStatType statType;

                            try {
                                statType = ItemStatType.valueOf(statName);
                            } catch (Exception e) {
                                Util.sendMessage(cs, "stat类型不存在!");
                                return true;
                            }

                            try {
                                level = Integer.parseInt(args[3]);
                            } catch (Exception e) {
                                Util.sendMessage(cs, "等级必须为数字!");
                                return true;
                            }

                            List<String> newLores = itemMeta.getLore();

                            if (newLores == null) {
                                newLores = new ArrayList<>();
                            }

                            newLores.add(settings.getMyItemsStatsFormatValue().replace("<stats>", statType.getChineseName()).replace("<value>", String.valueOf(level)));
                            itemMeta.setLore(newLores);
                            item.setItemMeta(itemMeta);
                            Util.sendMessage(cs,"添加成功!");
                            return true;
                        }
                }
            }
        }

        sendHelpMessage(cs);
        return false;
    }

    private void sendHelpMessage(CommandSender sender) {
        Util.sendMessage(sender, "&f/oli reload - 重载配置");
        Util.sendMessage(sender, "&f/oli stats add <stat> <level> - 添加stat");
    }
}

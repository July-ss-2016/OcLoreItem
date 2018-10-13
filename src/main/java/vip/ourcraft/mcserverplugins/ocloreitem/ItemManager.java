package vip.ourcraft.mcserverplugins.ocloreitem;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemManager {
    private Settings settings;

    public ItemManager(OcLoreItem plugin) {
        this.settings = plugin.getSettings();
    }

    public List<ItemStat> getStatsFromItemStack(ItemStack item) {
        if (item == null) {
            return null;
        }

        ItemMeta meta = item.getItemMeta();

        if (meta == null) {
            return null;
        }

        List<ItemStat> result = new ArrayList<>();
        List<String> lores = meta.getLore();

        if (lores == null || lores.size() == 0) {
            return null;
        }

        for (String lore : lores) {
            String noColorCodeLore = ChatColor.stripColor(lore);

            for (ItemStatType type : ItemStatType.values()) {
                Pattern pattern = Pattern.compile(settings.getStatsIdentifierExp().replace("<stats>", type.getChineseName()).replace("<value>", "[0-9]+"));
                Matcher matcher = pattern.matcher(noColorCodeLore);
                int level;

                // 匹配到了属性lore且等级>=0
                if (matcher.find() && (level = Util.matchNumberFromStr(noColorCodeLore)) >= 0) {
                    result.add(new ItemStat(type, level));
                }
            }
        }

        return result;
    }

    public int getItemStatLevel(ItemStatType statType, ItemStack item) {
        if (statType == null) {
            throw new IllegalArgumentException("stat type can not be null");
        }

        if (item == null) {
            return 0;
        }

        List<ItemStat> itemStats = getStatsFromItemStack(item);

        if (itemStats == null) {
            return 0;
        }

        for (ItemStat stat : itemStats) {
            if (statType == stat.getType()) {
                return stat.getLevel();
            }
        }

        return 0;
    }

    public int getItemsStatLevel(ItemStatType statType, ItemStack... items) {
        int total = 0;

        for (ItemStack item : items) {
            total += getItemStatLevel(statType, item);
        }

        return total;
    }

    public String getItemCode(ItemStack item) {
        if (item == null) {
            return null;
        }

        ItemMeta meta = item.getItemMeta();

        if (meta == null) {
            return null;
        }

        List<String> lores = meta.getLore();

        if (lores == null || lores.size() == 0) {
            return null;
        }

        Pattern pattern = Pattern.compile("[A-Z]+\\.[0-9]+\\.[0-9]+");
        Matcher matcher = pattern.matcher(ChatColor.stripColor(lores.get(0)).replace("- 编号 > ", ""));

        return matcher.find() ? matcher.group() : null;
    }
}

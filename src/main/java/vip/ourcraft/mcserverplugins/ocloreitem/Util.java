package vip.ourcraft.mcserverplugins.ocloreitem;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    public static int matchNumberFromStr(String s) {
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(s);

        return matcher.find() ? Integer.parseInt(matcher.group()) : -1;
    }

    public static boolean isInteger(String s) {
        return s.matches("[0-9]+");
    }

    public static int getMax(int... numbers) {
        int max = 0;

        for (int number : numbers) {
            if (number > max) {
                max = number;
            }
        }

        return max;
    }

    public static void sendMessage(CommandSender cs, String msg) {
        cs.sendMessage("§a[OcLoreItem] §c" + ChatColor.translateAlternateColorCodes('&', msg));
    }

    public static String translateColorCode(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}

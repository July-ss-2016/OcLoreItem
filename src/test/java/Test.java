import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        String lore = "§7- §e编号 §b> §eBA.0001.1";
        Pattern pattern = Pattern.compile("[A-Za-z]+\\.[0-9]+\\.[0-9]+");
        Matcher matcher = pattern.matcher(ChatColor.stripColor(lore).replace("- 编号 > ", ""));

        System.out.println(matcher.find());
        System.out.println(matcher.group());
    }
}

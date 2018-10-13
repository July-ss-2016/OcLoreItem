package vip.ourcraft.mcserverplugins.ocloreitem;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.inventory.PlayerInventory;

public class ItemListener implements Listener {
    private ItemManager itemManager;

    public ItemListener(OcLoreItem plugin) {
        this.itemManager = plugin.getItemManager();
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerExpChangeEvent(PlayerExpChangeEvent event) {
        Player player = event.getPlayer();
        PlayerInventory playerInv = player.getInventory();
        int expAdditionMaxLevel = Util.getMax(itemManager.getItemStatLevel(ItemStatType.EXP_ADDITION, playerInv.getItemInMainHand())
                , itemManager.getItemStatLevel(ItemStatType.EXP_ADDITION, playerInv.getItemInOffHand())
                , itemManager.getItemStatLevel(ItemStatType.EXP_ADDITION, playerInv.getHelmet()));

        if (expAdditionMaxLevel != 0) {
            event.setAmount(event.getAmount() * expAdditionMaxLevel);
        }
    }
}

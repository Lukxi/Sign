package me.lukas.JenoSign.GUIs;

import me.lukas.JenoSign.util.SignManager;
import me.oxolotel.utils.bukkit.menuManager.InventoryMenuManager;
import me.oxolotel.utils.bukkit.menuManager.menus.Closeable;
import me.oxolotel.utils.bukkit.menuManager.menus.CustomMenu;
import me.oxolotel.utils.bukkit.menuManager.menus.SlotCondition;
import me.oxolotel.utils.bukkit.menuManager.menus.content.InventoryContent;
import me.oxolotel.utils.bukkit.menuManager.menus.content.InventoryItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchantGUI extends CustomMenu implements Closeable, SlotCondition{

    private final InventoryContent c;

    public EnchantGUI() {
        super(26);
        c = new InventoryContent();
    }

    @Override
    public void onClose(Player player, ItemStack[] itemStacks, CloseReason closeReason) {

    }

    @Override
    public InventoryContent getContents(Player player) {

        c.fill(0, 26, new InventoryItem(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), ()->{}));
        c.addGuiItem(4, ()->{

        }, setEnchant(player));
        c.addGuiItem(17, ()->{}, Material.SLIME_BALL, "§a§lAnwenden");
        c.addGuiItem(13, ()->{

            SignManager.setEnchant(player.getUniqueId());
            InventoryMenuManager.getInstance().getOpenMenu(player);

        }, Material.ENCHANTED_BOOK, "§5Glow");
        c.addGuiItem(26, ()->{
            InventoryMenuManager.getInstance().closeMenu(player, CloseReason.CHANGEMENU);
        }, Material.BARRIER, "§c§lAbbrechen");
        return c;
    }

    @Override
    public boolean isClickAllowed(Player player, int i) {
        return true;
    }

    private ItemStack setEnchant(Player player) {

        ItemStack getActive;

        if (SignManager.signMap.get(player.getUniqueId()).isEnchant()) {
            ItemStack Active = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
            ItemMeta ActiveMeta = Active.getItemMeta();
            ActiveMeta.setDisplayName("§a§lAktiviert");
            Active.setItemMeta(ActiveMeta);
            getActive = Active;

        } else {
            ItemStack NotActive = new ItemStack(Material.RED_STAINED_GLASS_PANE);
            ItemMeta NotActiveMeta = NotActive.getItemMeta();
            NotActiveMeta.setDisplayName("§c§lDeaktiviert");
            NotActive.setItemMeta(NotActiveMeta);
            getActive = NotActive;

        }

        return getActive;
    }
}

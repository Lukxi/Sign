package me.lukas.JenoSign.GUIs;


import me.lukas.JenoSign.JenoSign;
import me.lukas.JenoSign.util.AnvilMenuManager;
import me.lukas.JenoSign.util.ItemBuilder;
import me.oxolotel.utils.bukkit.menuManager.InventoryMenuManager;
import me.oxolotel.utils.bukkit.menuManager.menus.Closeable;
import me.oxolotel.utils.bukkit.menuManager.menus.CustomMenu;
import me.oxolotel.utils.bukkit.menuManager.menus.SlotCondition;
import me.oxolotel.utils.bukkit.menuManager.menus.content.InventoryContent;
import me.oxolotel.utils.bukkit.menuManager.menus.content.InventoryItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class SettingsGUI extends CustomMenu implements Closeable, SlotCondition{

    private final InventoryContent c;

    public SettingsGUI() {
        super(54);
        c = new InventoryContent();
        setTitle("§b§lSign §r§8v."+ JenoSign.VERSION);
    }

    @Override
    public void onClose(Player player, ItemStack[] itemStacks, CloseReason closeReason) {
        if (!closeReason.equals(CloseReason.CHANGEMENU)) {
            if (!closeReason.equals(CloseReason.OTHER)) {
                JenoSign.signMap.remove(player.getUniqueId());
                player.sendMessage(JenoSign.PREFIX + " §7Signieren wurde abbgebrochen!");
            }
        }
    }

    @Override
    public InventoryContent getContents(Player player) {
        c.fill(0, 53, new InventoryItem(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setDisplayName(" ").build(), ()->{}));

        ItemStack i = new ItemStack(JenoSign.signMap.get(player.getUniqueId()).createDeploySign());

        c.addGuiItem(13, new InventoryItem(i, ()->{}));
        c.addGuiItem(28, ()->{
            InventoryMenuManager.getInstance().closeMenu(player, Closeable.CloseReason.CHANGEMENU);
            ItemStack name = JenoSign.signMap.get(player.getUniqueId()).getItemNoDefaultName();

            new AnvilMenuManager().createAnvilMenu(player, name,
                    "§5§lItem-Namen bearbeiten");
        }, Material.ANVIL, "§5§lBearbeite den Item-Namen");
        c.addGuiItem(30, ()->{
            InventoryMenuManager.getInstance().closeMenu(player, CloseReason.CHANGEMENU);
            InventoryMenuManager.getInstance().openMenu(player, new EditLoresGUI());

        }, Material.LOOM, "§6§lBearbeite die Lores");
        c.addGuiItem(32, ()->{
            JenoSign.signMap.get(player.getUniqueId()).setEnchant(!JenoSign.signMap.get(player.getUniqueId()).isEnchant());
            InventoryMenuManager.getInstance().getOpenMenu(player).generateInventory();
        }, Material.ENCHANTING_TABLE, "§b§lVerzaubern");
        c.addGuiItem(34, ()->{
            InventoryMenuManager.getInstance().closeMenu(player, CloseReason.CHANGEMENU);
            InventoryMenuManager.getInstance().openMenu(player, new SignatureSettingsGUI());
        }, Material.CLOCK, "§9§lSignatur Einstellungen");
        c.addGuiItem(53, ()->{
            player.getInventory().setItemInMainHand(JenoSign.signMap.get(player.getUniqueId()).createDeploySign());
            InventoryMenuManager.getInstance().closeMenu(player,CloseReason.OTHER);
            JenoSign.signMap.remove(player.getUniqueId());
            EditLoresGUI.signMap.remove(player.getUniqueId());
            player.sendMessage(JenoSign.PREFIX + " §7Du hast das Item erfolgreich signiert!");
        }, Material.LIME_STAINED_GLASS_PANE, "§a§lSignieren");
        c.addGuiItem(52, ()->{
            JenoSign.signMap.remove(player.getUniqueId());
            InventoryMenuManager.getInstance().closeMenu(player);
            player.sendMessage(JenoSign.PREFIX + " §7Signieren wurde abbgebrochen!");
        }, Material.BARRIER, "§c§lAbbrechen");
        return c;
    }

    @Override
    public boolean isClickAllowed(Player player, int i) {
        return true;
    }
}

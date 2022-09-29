package me.lukas.JenoSign.GUIs;

import com.sun.org.apache.xerces.internal.xs.StringList;
import me.lukas.JenoSign.util.AnvilMenuManager;
import me.lukas.JenoSign.util.Sign;
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

import java.util.ArrayList;
import java.util.UUID;

public class SettingsGUI extends CustomMenu implements Closeable, SlotCondition{

    private final InventoryContent c;

    public SettingsGUI() {
        super(54);
        c = new InventoryContent();
    }

    @Override
    public void onClose(Player player, ItemStack[] itemStacks, CloseReason closeReason) {

    }

    @Override
    public InventoryContent getContents(Player player) {
        c.fill(0, 53, new InventoryItem(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), ()->{}));

        ItemStack i = new ItemStack(SignManager.signMap.get(player.getUniqueId()).createDeploySign());

        c.addGuiItem(13, new InventoryItem(i, ()->{}));
        c.addGuiItem(28, ()->{
            InventoryMenuManager.getInstance().closeMenu(player, Closeable.CloseReason.CHANGEMENU);

            new AnvilMenuManager().createAnvilMenu(player, SignManager.getItemNoDefaultName(player.getUniqueId()),
                    "§5§lBearbeite den Item-Namen");
        }, Material.ANVIL, "§5§lBearbeite den Item-Namen");
        c.addGuiItem(30, ()->{
            InventoryMenuManager.getInstance().closeMenu(player, CloseReason.CHANGEMENU);
            InventoryMenuManager.getInstance().openMenu(player, new EditLoresGUI());

        }, Material.LOOM, "§6§lBearbeite die Lores");
        c.addGuiItem(32, ()->{}, Material.ENCHANTING_TABLE, "§b§lVerzaubern");
        c.addGuiItem(34, ()->{
            InventoryMenuManager.getInstance().closeMenu(player);
            InventoryMenuManager.getInstance().openMenu(player, new SignatureSettingsGUI());
        }, Material.CLOCK, "§5§lBearbeite die Signatur");
        c.addGuiItem(53, ()->{
            InventoryMenuManager.getInstance().closeMenu(player);
            SignManager.signMap.remove(player.getUniqueId());
            //SignManager.signMap.get(player.getUniqueId()).setSign();
        }, Material.LIME_STAINED_GLASS_PANE, "§a§lSignieren");
        c.addGuiItem(52, ()->{
            SignManager.signMap.remove(player.getUniqueId());
            InventoryMenuManager.getInstance().closeMenu(player);
        }, Material.BARRIER, "§c§lAbbrechen");
        return c;
    }

    @Override
    public boolean isClickAllowed(Player player, int i) {
        return true;
    }
}

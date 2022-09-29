package me.lukas.JenoSign.GUIs;

import jakarta.persistence.criteria.CriteriaBuilder;
import me.lukas.JenoSign.util.AnvilMenuManager;
import me.lukas.JenoSign.util.ItemBuilder;
import me.lukas.JenoSign.util.SignManager;
import me.lukas.JenoSign.util.SkullBuilder;
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
import java.util.HashMap;
import java.util.UUID;

public class EditLoresGUI extends CustomMenu implements Closeable, SlotCondition {

    private final InventoryContent c;
    public static HashMap<UUID, String[]> signMap = new HashMap<>();

    public EditLoresGUI() {
        super(54);
        c = new InventoryContent();
        setTitle("§6§lBearbeite die Lores");

    }

    @Override
    public void onClose(Player player, ItemStack[] itemStacks, CloseReason closeReason) {

    }

    @Override
    public InventoryContent getContents(Player player) {
        c.fill(0, 53, new InventoryItem(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), ()->{}));

        String[] lores = null;
        if (signMap.containsKey(player.getUniqueId())) {
            lores = SignManager.signMap.get(player.getUniqueId()).getLore();
        }else {
            signMap.put(player.getUniqueId(),SignManager.signMap.get(player.getUniqueId()).getLore());
            lores = signMap.get(player.getUniqueId());
        }
        ItemStack i = new ItemStack(SignManager.signMap.get(player.getUniqueId()).createDeploySign(signMap.get(player.getUniqueId())));
        c.addGuiItem(16, new InventoryItem(new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDRmN2JjMWZhODIxN2IxOGIzMjNhZjg0MTM3MmEzZjdjNjAyYTQzNWM4MjhmYWE0MDNkMTc2YzZiMzdiNjA1YiJ9fX0===",
                "§9§lSign Preview").build(), ()->{}));
        c.addGuiItem(25, new InventoryItem(i, ()->{}));

        c.addGuiItem(10, new InventoryItem(new ItemBuilder(Material.MAP).setDisplayName(lores[0]).build(), ()->{}));
        c.addGuiItem(19, new InventoryItem(new ItemBuilder(Material.MAP).setDisplayName(lores[1]).build(), ()->{}));
        c.addGuiItem(28, new InventoryItem(new ItemBuilder(Material.MAP).setDisplayName(lores[2]).build(), ()->{}));
        c.addGuiItem(37, new InventoryItem(new ItemBuilder(Material.MAP).setDisplayName(lores[3]).build(), ()->{}));

        ItemStack m = new  ItemBuilder(Material.MAP).setDisplayName("§c§lKeine Lore gefunden!").build();
        String[] lore = initLores(player.getUniqueId());
        if (lore[0].equalsIgnoreCase("")){
            c.addGuiItem(10, new InventoryItem(m, ()->{}));
            lore[0] = " ";
        }
        if (lore[1].equalsIgnoreCase("")){
            c.addGuiItem(19, new InventoryItem(m, ()->{}));
            lore[1] = " ";
        }
        if (lore[2].equalsIgnoreCase("")){
            c.addGuiItem(28, new InventoryItem(m, ()->{}));
            lore[2] = " ";
        }
        if (lore[3].equalsIgnoreCase("")){
            c.addGuiItem(37, new InventoryItem(m, ()->{}));
            lore[3] = " ";
        }


        String[] l = signMap.get(player.getUniqueId());
        c.addGuiItem(12, new InventoryItem(new ItemBuilder(Material.ANVIL).setDisplayName("Bearbeite die erste Lore").build(), ()->{
            new AnvilMenuManager().createAnvilMenu(player, new ItemBuilder(Material.MAP).setDisplayName(lore[0]).build(), "Lore 1");
        }));

        c.addGuiItem(21, new InventoryItem(new ItemBuilder(Material.ANVIL).setDisplayName("Bearbeite die zweite Lore").build(), ()->{
            new AnvilMenuManager().createAnvilMenu(player, new ItemBuilder(Material.MAP).setDisplayName(lore[1]).build(), "Lore 2");
        }));
        c.addGuiItem(30, new InventoryItem(new ItemBuilder(Material.ANVIL).setDisplayName("Bearbeite die dritte Lore").build(), ()->{
            new AnvilMenuManager().createAnvilMenu(player, new ItemBuilder(Material.MAP).setDisplayName(lore[2]).build(), "Lore 3");
        }));
        c.addGuiItem(39, new InventoryItem(new ItemBuilder(Material.ANVIL).setDisplayName("Bearbeite die vierte Lore").build(), ()->{
            new AnvilMenuManager().createAnvilMenu(player, new ItemBuilder(Material.MAP).setDisplayName(lore[3]).build(), "Lore 4");
        }));


        c.addGuiItem(13, new InventoryItem(new ItemBuilder(Material.PAPER).setDisplayName("Mache die erste Lore zu einer leeren Zeile").build(), ()->{
            signMap.get(player.getUniqueId())[0] = " ";
            InventoryMenuManager.getInstance().getOpenMenu(player).generateInventory();
        }));

        c.addGuiItem(22, new InventoryItem(new ItemBuilder(Material.PAPER).setDisplayName("Mache die zweite Lore zu einer leeren Zeile").build(), ()->{
            signMap.get(player.getUniqueId())[1] = " ";
            InventoryMenuManager.getInstance().getOpenMenu(player).generateInventory();
        }));
        c.addGuiItem(31, new InventoryItem(new ItemBuilder(Material.PAPER).setDisplayName("Mache die dritte Lore zu einer leeren Zeile").build(), ()->{
            signMap.get(player.getUniqueId())[2] = " ";
            InventoryMenuManager.getInstance().getOpenMenu(player).generateInventory();
        }));
        c.addGuiItem(40, new InventoryItem(new ItemBuilder(Material.PAPER).setDisplayName("Mache die vierte Lore zu einer leeren Zeile").build(), ()->{
            signMap.get(player.getUniqueId())[3] = " ";
            InventoryMenuManager.getInstance().getOpenMenu(player).generateInventory();
        }));


        c.addGuiItem(14, new InventoryItem(new ItemBuilder(Material.REDSTONE).setDisplayName("Lore löschen").build(), ()->{
            signMap.get(player.getUniqueId())[0] = "";
            InventoryMenuManager.getInstance().getOpenMenu(player).generateInventory();
        }));

        c.addGuiItem(23, new InventoryItem(new ItemBuilder(Material.REDSTONE).setDisplayName("Lore löschen").build(), ()->{
            signMap.get(player.getUniqueId())[1] = "";
            InventoryMenuManager.getInstance().getOpenMenu(player).generateInventory();
        }));
        c.addGuiItem(32, new InventoryItem(new ItemBuilder(Material.REDSTONE).setDisplayName("Lore löschen").build(), ()->{
            signMap.get(player.getUniqueId())[2] = "";
            InventoryMenuManager.getInstance().getOpenMenu(player).generateInventory();
        }));
        c.addGuiItem(41, new InventoryItem(new ItemBuilder(Material.REDSTONE).setDisplayName("Lore löschen").build(), ()->{
            signMap.get(player.getUniqueId())[3] = "";
            InventoryMenuManager.getInstance().getOpenMenu(player).generateInventory();
        }));






        c.addGuiItem(53, ()->{
            InventoryMenuManager.getInstance().closeMenu(player, CloseReason.CHANGEMENU);
            SignManager.setLores(player.getUniqueId(), signMap.get(player.getUniqueId()));
            InventoryMenuManager.getInstance().openMenu(player, new SettingsGUI());
        }, Material.SLIME_BALL, "§a§lAnwenden");

        c.addGuiItem(52, ()->{
            InventoryMenuManager.getInstance().closeMenu(player, CloseReason.CHANGEMENU);
            signMap.remove(player.getUniqueId());
            InventoryMenuManager.getInstance().openMenu(player, new SettingsGUI());
        }, Material.BARRIER, "§c§lAbbrechen");
        return c;
    }

    @Override
    public boolean isClickAllowed(Player player, int i) {
        return true;
    }

    public String[] initLores(UUID uuid){
        String[] s = new String[4];
        s[0] = signMap.get(uuid)[0];
        s[1] = signMap.get(uuid)[1];
        s[2] = signMap.get(uuid)[2];
        s[3] = signMap.get(uuid)[3];
        return s;
    }

}

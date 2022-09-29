package me.lukas.JenoSign.GUIs;

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
import org.bukkit.inventory.meta.SkullMeta;

public class SignatureSettingsGUI extends CustomMenu implements Closeable, SlotCondition {

    private InventoryContent c;
    private String[] settings;

    public SignatureSettingsGUI() {
        super(54);
        c = new InventoryContent();
        settings=new String[2];
        settings[0] = "normal";
        settings[1] = "name";
    }

    @Override
    public void onClose(Player player, ItemStack[] itemStacks, CloseReason closeReason) {

    }

    @Override
    public InventoryContent getContents(Player player) {
        c.fill(0, 53, new InventoryItem(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), ()->{}));
        ItemStack i = new ItemStack(SignManager.signMap.get(player.getUniqueId()).createDeploySignSettings(settings));
        ItemStack s = new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDRmN2JjMWZhODIxN2IxOGIzMjNhZjg0MTM3MmEzZjdjNjAyYTQzNWM4MjhmYWE0MDNkMTc2YzZiMzdiNjA1YiJ9fX0===",
                "§9§lSign Preview").build();
        c.addGuiItem(16, new InventoryItem(s, ()->{}));
        c.addGuiItem(25, new InventoryItem( i, ()->{}));
        c.addGuiItem(10, new InventoryItem(new ItemBuilder(Material.CLOCK).setDisplayName("§5§lZeit Einstellungen").build(), ()->{}));
        c.addGuiItem(19, new InventoryItem(new ItemBuilder(Material.REDSTONE).setDisplayName("§4§lSignatur ohne Zeit").build(), ()-> {
            settings[0] = null;
            InventoryMenuManager.getInstance().getOpenMenu(player).generateInventory();
        }));
        c.addGuiItem(13, new InventoryItem(new ItemBuilder(Material.LEGACY_BOOK_AND_QUILL).setDisplayName("§5§lName unter der Signatur").build(), ()->{}));
        c.addGuiItem(22, new InventoryItem(new ItemBuilder(Material.REDSTONE).setDisplayName("§4§lSignatur ohne Name").build(), ()->{
            settings[1] = null;
            InventoryMenuManager.getInstance().getOpenMenu(player).generateInventory();
        }));
        ItemStack sNormal = new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmU1ZDEwZjlmMzI0NjU5OTY1OGUwYzZkMDQ0NGU4NzRmZmFjMDE0MTA0NDBjNWNmZWM2ZjE5ZDNhYTg4Zjk0NSJ9fX0=",
                "§5§lZeige die Zeit normal an").build();
        ItemStack sMonth = new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTQyYTI0MTUyYWExM2FjMzE1YjI3MWQ2OThiODJiNGE3YTE3ZjEwZjE3ZjBlOGM0OTVmYjZmOGNiMzljNTU2NCJ9fX0=",
                "§5§lZeige nur das Monat an").build();
        c.addGuiItem(37, new InventoryItem(sMonth, ()->{
            settings[0] = "month";
            InventoryMenuManager.getInstance().getOpenMenu(player).generateInventory();
        }));
        c.addGuiItem(28, new InventoryItem(sNormal, ()->{
            settings[0] = "normal";
            InventoryMenuManager.getInstance().getOpenMenu(player).generateInventory();
        }));
        ItemStack sJeno = new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzdmODMxNDRjNGNlMzJlOWVhM2E0NDhjY2M4NWZkOWM5YmU0M2MyYmY1Njg4ZWMyZGQ1MzNhMTk5NTY2MTA2NiJ9fX0==",
                "§5§lSigniere als Jeno-Team").build();
        ItemStack sPlayer = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
        SkullMeta skullMeta = ((SkullMeta) sPlayer.getItemMeta());
        skullMeta.setOwningPlayer(player);
        sPlayer.setItemMeta(skullMeta);
        c.addGuiItem(31, new InventoryItem(sPlayer, ()->{
            settings[1] = player.getName();
            InventoryMenuManager.getInstance().getOpenMenu(player).generateInventory();
        }));
        c.addGuiItem(40, new InventoryItem(sJeno, ()->{
            settings[1] = "Jeno";
            InventoryMenuManager.getInstance().getOpenMenu(player).generateInventory();
        }));


        c.addGuiItem(53, ()->{
            SignManager.signMap.get(player.getUniqueId()).setSignatureSettings(settings);
            InventoryMenuManager.getInstance().closeMenu(player, CloseReason.CHANGEMENU);
            InventoryMenuManager.getInstance().openMenu(player, new SettingsGUI());
        }, Material.SLIME_BALL, "§a§lAnwenden");

        c.addGuiItem(52, ()->{
            InventoryMenuManager.getInstance().closeMenu(player, CloseReason.CHANGEMENU);
            InventoryMenuManager.getInstance().openMenu(player, new SettingsGUI());
        }, Material.BARRIER, "§c§lAbbrechen");

        return c;
    }

    @Override
    public boolean isClickAllowed(Player player, int i) {
        return true;
    }
}

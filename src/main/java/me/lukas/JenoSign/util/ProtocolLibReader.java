package me.lukas.JenoSign.util;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import me.lukas.JenoSign.GUIs.EditLoresGUI;
import me.lukas.JenoSign.GUIs.SettingsGUI;
import me.lukas.JenoSign.JenoSign;
import me.oxolotel.utils.bukkit.menuManager.InventoryMenuManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ProtocolLibReader {



    private static HashMap<UUID,String>mengeInput = new HashMap<>();
    private static HashMap<UUID,String>preisInput = new HashMap<>();

    public HashMap<UUID, String> getMengeInput() {
        return mengeInput;
    }

    public HashMap<UUID, String> getPreisInput() {
        return preisInput;
    }

    public void removeMengeInput(Player p){
        mengeInput.remove(p.getUniqueId());
    }
    public void removePreisInput(Player p){
        preisInput.remove(p.getUniqueId());
    }

    public void readWindowClickPacket(ProtocolManager pm, JenoSign main){
        if (pm != null) {
            pm.addPacketListener(new PacketAdapter(main, PacketType.Play.Client.WINDOW_CLICK) {
                @Override
                public void onPacketReceiving(PacketEvent event) {
                    Inventory invFound = null;
                    Player p = event.getPlayer();
                    for (Inventory inv: new AnvilMenuManager().getInvList()){
                        for (HumanEntity viewer: inv.getViewers()){
                            if (viewer == p){
                                invFound = inv;
                            }
                        }
                    }

                    if (invFound != null){
                        PacketContainer packet = event.getPacket();

                        if (packet.getIntegers().read(2) == 2) {
                            String input = packet.getItemModifier().read(0).getItemMeta().getDisplayName();
                            if (input.startsWith(" ")){
                                input = input.replaceFirst(" ", "");
                            }
                            input = input.replace("&", "§");
                            if(p.getOpenInventory().getTitle().equalsIgnoreCase("§5§lItem-Namen bearbeiten")){

                                SignManager.changeItemName(p.getUniqueId(), input);
                            }else if (p.getOpenInventory().getTitle().contains("Lore")){
                                String title = p.getOpenInventory().getTitle();
                                input = "§7"+input;
                                if (title.endsWith("1")){
                                    String[] l = EditLoresGUI.signMap.get(p.getUniqueId());
                                     l[0] = input;
                                     EditLoresGUI.signMap.replace(p.getUniqueId(), l);
                                }
                                if (title.endsWith("2")){
                                    String[] l = EditLoresGUI.signMap.get(p.getUniqueId());
                                    l[1] = input;
                                    EditLoresGUI.signMap.replace(p.getUniqueId(), l);
                                }
                                if (title.endsWith("3")){
                                    String[] l = EditLoresGUI.signMap.get(p.getUniqueId());
                                    l[2] = input;
                                    EditLoresGUI.signMap.replace(p.getUniqueId(), l);
                                }
                                if (title.endsWith("4")){
                                    String[] l = EditLoresGUI.signMap.get(p.getUniqueId());
                                    l[3] = input;
                                    EditLoresGUI.signMap.replace(p.getUniqueId(), l);
                                }
                            }

                            String title = p.getOpenInventory().getTitle();
                            PacketContainer container = new PacketContainer(PacketType.Play.Server.CLOSE_WINDOW);
                            try {
                                pm.sendServerPacket(p, container);
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }

                            new BukkitRunnable() {
                                int counter = 0;
                                @Override
                                public void run() {
                                    if (counter == 1){
                                        if (title.equalsIgnoreCase("§5§lItem-Namen bearbeiten")) {
                                            InventoryMenuManager.getInstance().openMenu(p, new SettingsGUI());
                                        }else {
                                            InventoryMenuManager.getInstance().openMenu(p, new EditLoresGUI());
                                        }
                                        cancel();
                                    }
                                    counter++;
                                }
                            }.runTaskTimer(main, 0, 1);
                        }
                    }
                }
            });

        }
    }
}
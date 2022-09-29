package me.lukas.JenoSign.commands;

import me.lukas.JenoSign.GUIs.SettingsGUI;
import me.lukas.JenoSign.util.Sign;
import me.lukas.JenoSign.util.SignManager;
import me.oxolotel.utils.bukkit.menuManager.InventoryMenuManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SignCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;



            if (player.hasPermission("sign.command")) {

                if (args.length >= 1) {


                    ItemStack item = player.getInventory().getItemInMainHand();


                    if (item.getType() != Material.AIR) {


                        if (item.hasItemMeta()) {

                            if (item.getItemMeta().hasLore()) {
                                for (String s : (item.getItemMeta().getLore())) {

                                    if (s.toLowerCase().equals("§7§m---------------------------------------")) {
                                        player.sendMessage("§b§l[Sign] §7Das Item ist bereits Signiert!");

                                        return true;
                                    }
                                }
                            }
                        }
                        StringBuilder stringBuilder = new StringBuilder();
                        for (String arg : args) {
                            stringBuilder.append(arg).append(" ");

                        }
                        new Sign(player, player.getInventory().getItemInMainHand(), stringBuilder.toString());
                        InventoryMenuManager.getInstance().openMenu(player, new SettingsGUI());

                    } else
                        player.sendMessage("§b§l[Sign] §7Du hast kein Item in der Hand!");

                } else
                    sendHelp(player);


            } else
                player.sendMessage("§b§l[Sign] §7Dafür hast du keine Berechtigung!");


        } else
            sender.sendMessage("§b§l[Sign] §7Diesen Befehl kannst du nur als Spieler benutzen!");

        return false;
    }

    public static void sendHelp(Player player){
        player.sendMessage("§8-=[§b§l●§8] §4§lSign Plugin by Lukxi §8[§b§l●§8]=-");
        player.sendMessage("§e/sign <Text> §f- §7Signiere Items");
        player.sendMessage("§e/unsign §f- §7entferne die Signatur");
        player.sendMessage("§eVersion: §71.1");

    }

}

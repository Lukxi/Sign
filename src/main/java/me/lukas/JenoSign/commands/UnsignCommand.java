package me.lukas.JenoSign.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class UnsignCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){

            Player player = (Player) sender;

            if(player.hasPermission("unsign.command")){

                if(args.length == 0){

                    if(player.getInventory().getItemInMainHand().getType() != Material.AIR){

                        if(player.getInventory().getItemInMainHand().hasItemMeta()){

                            ItemStack itemStack = player.getInventory().getItemInMainHand();
                            ItemMeta itemMeta = itemStack.getItemMeta();



                            if (itemStack.getItemMeta().hasLore()){

                                for (String s : (itemStack.getItemMeta().getLore())) {

                                    if (s.toLowerCase().equals("§7§m---------------------------------------")) {

                                        itemMeta.setLore(null);
                                        String name = itemStack.getType().toString().toLowerCase().replace('_' , ' ');
                                        String nameUp = Character.toUpperCase(name.charAt(0)) + name.substring(1);
                                        itemMeta.setDisplayName(ChatColor.RESET + nameUp);

                                        if(itemMeta.getEnchants().containsKey(Enchantment.LUCK_OF_THE_SEA)){
                                            itemMeta.removeEnchant(Enchantment.LUCK_OF_THE_SEA);
                                        }

                                        itemStack.setItemMeta(itemMeta);
                                        player.getInventory().setItemInMainHand(itemStack);



                                        player.sendMessage("§b§l[Sign] §7Du hast die Signatur entfernt!");
                                        return true;

                                    }
                                }


                            }else
                                player.sendMessage("§b§l[Sign] §7Dieses Item ist nicht Signiert!");


                        }else
                            player.sendMessage("§b§l[Sign] §7Dieses Item ist nicht Signiert!");


                    }else
                        player.sendMessage("§b§l[Sign] §7Du hast kein Item in der Hand!");


                }else
                    SignCommand.sendHelp(player);


            }else
                player.sendMessage("§b§l[Sign] §7Dafür hast du keine Berechtigung!");



        }else
            sender.sendMessage("§b§l[Sign] §7Diesen Befehl kannst du nur als Spieler benutzen!");

        return false;
    }

}

package me.lukas.JenoSign.util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class SignManager {

    public static HashMap<UUID, Sign> signMap = new HashMap<>();

    public static void setEnchant(UUID uuid){
        signMap.get(uuid).setEnchant(!signMap.get(uuid).isEnchant());
    }
    public static ItemStack getItem(UUID uuid){
       return signMap.get(uuid).getItem();
    }
    public static ItemStack getItemNoDefaultName(UUID uuid){
        return signMap.get(uuid).getItemNoDefaultName();
    }
    public static void changeItemName(UUID uuid, String n){
        signMap.get(uuid).changeItemName(n);
    }
    public static void changeLores(UUID uuid, ArrayList<String> l){
        signMap.get(uuid).setLores(l);
    }
    public static ItemStack getDeploySignItem(UUID uuid){
        return signMap.get(uuid).createDeploySign();
    }
    public static ArrayList<String> getLores(UUID uuid){
        return signMap.get(uuid).getLores();
    }
    public static void setLores(UUID uuid,String[] strings){
        signMap.get(uuid).setLore(strings);
    }
}

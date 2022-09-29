package me.lukas.JenoSign.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemBuilder {

        private final ItemStack item;
        private final ItemMeta itemMeta;

        public ItemBuilder(Material material){
            item = new ItemStack(material);
            itemMeta = item.getItemMeta();

        }

        public ItemBuilder setDisplayName(String name){
            if (name != null) {
                itemMeta.setDisplayName(name);
            }
            return this;
        }

        public ItemBuilder setEnchant(Enchantment enchant, int level, boolean res){
            itemMeta.addEnchant(enchant, level, res);
            return this;
        }

        public ItemBuilder setLore(ArrayList<String> lore){
            itemMeta.setLore(lore);
            return this;
        }

    public ItemBuilder setLore(String... lore){
        itemMeta.setLore(Arrays.asList(lore));
        return this;
    }

        public ItemStack build(){
            item.setItemMeta(itemMeta);
            return item;
        }


}

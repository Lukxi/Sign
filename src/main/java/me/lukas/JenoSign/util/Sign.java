package me.lukas.JenoSign.util;

import com.google.common.collect.Lists;
import me.lukas.JenoSign.JenoSign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Sign {

    private Player p;
    private String name;
    private String time;
    private ItemStack item;
    private ItemMeta meta;
    private ArrayList<String> lores;
    private String line1;
    private String line2;
    private String line3;
    private String line4;

    private boolean isEnchant;
    private boolean isName;
    private boolean isTime;

    public Sign(Player p, ItemStack pi, String lore){
        this.p = p;
        this.name = p.getName();
        this.time = "normal";
        this.item = new ItemStack(pi);
        this.meta = item.getItemMeta();
        this.lores = new ArrayList<>();
        SignManager.signMap.put(p.getUniqueId(), this);
        initLores();

            lore = lore.replace("&", "§");
            lore = "§7" + lore;
            line1 = lore;

    }

    public void changeItemName(String n){
        meta.setDisplayName(n);
        item.setItemMeta(meta);
    }

    private void initLores(){
     for (int i = 0; i<5; i++){
         lores.add(i,"");
     }
    }
    private String formatTime(final long millis) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return simpleDateFormat.format(millis);
    }
    private String formatTimeMonth(final long millis) {
        String month = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        if(simpleDateFormat.format(millis).equalsIgnoreCase("01")){
            month = "Januar";
        }else if (simpleDateFormat.format(millis).equalsIgnoreCase("02")){
            month = "Februar";
        }else if (simpleDateFormat.format(millis).equalsIgnoreCase("03")){
            month = "März";
        }else if (simpleDateFormat.format(millis).equalsIgnoreCase("04")){
            month = "April";
        }else if (simpleDateFormat.format(millis).equalsIgnoreCase("05")){
            month = "Mai";
        }else if (simpleDateFormat.format(millis).equalsIgnoreCase("06")){
            month = "Juni";
        }else if (simpleDateFormat.format(millis).equalsIgnoreCase("07")){
            month = "Juli";
        }else if (simpleDateFormat.format(millis).equalsIgnoreCase("08")){
            month = "August";
        }else if (simpleDateFormat.format(millis).equalsIgnoreCase("09")){
            month = "September";
        }else if (simpleDateFormat.format(millis).equalsIgnoreCase("10")){
            month = "Oktober";
        }else if (simpleDateFormat.format(millis).equalsIgnoreCase("11")){
            month = "November";
        }else if (simpleDateFormat.format(millis).equalsIgnoreCase("12")){
            month = "Dezember";
        }
        return month;
    }
    private String formatTimeYear(final long millis) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        return simpleDateFormat.format(millis);
    }


    public ItemStack createDeploySign(){

        String z = manageTime(time);
        String Signiert = manageName(name);

        ItemStack i = new ItemStack(item);
        ItemMeta m = i.getItemMeta();
        ArrayList<String> l= new ArrayList<>();
        if (line1 == null){
            line1 = "";
        }
        if (line2 == null){
            line2 = "";
        }
        if (line3 == null){
            line3 = "";
        }
        if (line4 == null){
            line4 = "";
        }
        if (!line1.equalsIgnoreCase("")){
            l.add(line1);
        }
        if (!line2.equalsIgnoreCase("")){
            l.add(line2);
        }
        if (!line3.equalsIgnoreCase("")){
            l.add(line3);
        }
        if (!line4.equalsIgnoreCase("")){
            l.add(line4);
        }
        l.add("§7§m---------------------------------------");
        l.add(Signiert + z);
        m.setLore(l);

        i.setItemMeta(m);
        return i;
    }

    public ItemStack createDeploySign(String[] s){

        String z = manageTime(time);
        String Signiert = manageName(name);
        ItemStack i = new ItemStack(item);
        ItemMeta m = i.getItemMeta();
        ArrayList<String> l= new ArrayList<>();

        if (s[0] == null){
            s[0] = "";
        }
        if (s[1] == null){
            s[1] = "";
        }
        if (s[2] == null){
            s[2] = "";
        }
        if (s[3] == null){
            s[3] = "";
        }

        if (!s[0].equalsIgnoreCase("")){
            l.add(s[0]);
        }
        if (!s[1].equalsIgnoreCase("")){
            l.add(s[1]);
        }
        if (!s[2].equalsIgnoreCase("")){
            l.add(s[2]);
        }
        if (!s[3].equalsIgnoreCase("")){
            l.add(s[3]);
        }
        l.add("§7§m---------------------------------------");
        l.add(Signiert + z);
        m.setLore(l);

        i.setItemMeta(m);
        return i;
    }
    public void setSignatureSettings(String[] s){
        this.time = s[0];
        this.name = s[1];
    }

    public ItemStack createDeploySignSettings(String[] s){
        String z = manageTime(s[0]);
        String Signiert = manageName(s[1]);

        ItemStack i = new ItemStack(item);
        ItemMeta m = i.getItemMeta();
        ArrayList<String> l= new ArrayList<>();
        if (line1 == null){
            line1 = "";
        }
        if (line2 == null){
            line2 = "";
        }
        if (line3 == null){
            line3 = "";
        }
        if (line4 == null){
            line4 = "";
        }
        if (!line1.equalsIgnoreCase("")){
            l.add(line1);
        }
        if (!line2.equalsIgnoreCase("")){
            l.add(line2);
        }
        if (!line3.equalsIgnoreCase("")){
            l.add(line3);
        }
        if (!line4.equalsIgnoreCase("")){
            l.add(line4);
        }
        l.add("§7§m---------------------------------------");
        l.add(Signiert + z);
        m.setLore(l);

        i.setItemMeta(m);
        return i;
    }

    private String manageTime(String s){
        String z = "§7am §c";
        if (s == null){
            s="";
            return s;
        }
        if (s.equalsIgnoreCase("month")){
            s= formatTimeMonth(System.currentTimeMillis());
            return "§7im §c"+s+ " " +formatTimeYear(System.currentTimeMillis());
        }
        if (s.equalsIgnoreCase("normal")){
            s= formatTime(System.currentTimeMillis());
            return z+s;
        }
        return z;
    }

    private String manageName(String s){
        String signiert = "§7Signiert von ";
        if (s == null){
            signiert = "§7Signiert ";
            return signiert;
        }else if (s.equalsIgnoreCase("Jeno")){
            signiert = "§7Signiert vom §4§lJenoMiners-Team ";
            return signiert;
        }else return signiert + p.getName()+" ";
    }

    public ItemStack createDeploySignLores(){

        String Name = p.getName();
        String Signiert = "§7Signiert von ";
        ItemStack i = new ItemStack(item);
        ItemMeta m = i.getItemMeta();
        ArrayList<String> l= new ArrayList<>();
        if (!line1.equalsIgnoreCase("")){
            l.add(line1);
        }
        if (!line2.equalsIgnoreCase("")){
            l.add(line2);
        }
        if (!line2.equalsIgnoreCase("")){
            l.add(line2);
        }
        if (!line3.equalsIgnoreCase("")){
            l.add(line3);
        }
        if (!line4.equalsIgnoreCase("")){
            l.add(line4);
        }
        l.add("§7§m---------------------------------------");
        l.add(Signiert + Name + " §7am §c" + formatTime(System.currentTimeMillis()));
        m.setLore(l);

        i.setItemMeta(m);
        return i;
    }
/*
    public void setSign(){

        StringBuilder stringBuilder = new StringBuilder();
        for (String arg : args) {
            stringBuilder.append(arg).append(" ");

        }


        if (meta.getLore() == null) {
            lore = Lists.newArrayList();
        } else {
            lore = itemMeta.getLore();

        }



        if (plugin.getManagers().get(player).ColorItem) {

            if (itemMeta.hasDisplayName()) {
                String name = itemMeta.getDisplayName();


                itemMeta.setDisplayName(plugin.getManagers().get(player).getColorSet().getColor(player.getUniqueId()) + plugin.getManagers().get(player).getColorSet().getFormat(player.getUniqueId()) + name);

            } else {
                String name;
                name = itemStack.getType().name().toLowerCase().replace("_", " ");
                String nameUp = Character.toUpperCase(name.charAt(0)) + name.substring(1);


                itemMeta.setDisplayName(plugin.getManagers().get(player).getColorSet().getColor(player.getUniqueId()) + plugin.getManagers().get(player).getColorSet().getFormat(player.getUniqueId()) + nameUp);

            }
        }

        String Name = player.getName();
        String Signiert = "§7Signiert von ";

        if(sm.isName()){
            Name = "";
            Signiert = "§7Signiert";
        }

        if(sm.isLine()){
            lore.add("");
            lore.add("");
        }

        if (sm.isTime()) {
            lore.add("§7" + stringBuilder.toString().replace('&', '§'));
            lore.add("§7§m---------------------------------------");
            lore.add(Signiert + sm.getSignColor().setSignColor(player) + Name);
            itemMeta.setLore(lore);

        } else {
            lore.add("§7" + stringBuilder.toString().replace('&', '§'));
            lore.add("§7§m---------------------------------------");
            lore.add(Signiert+ sm.getSignColor().setSignColor(player)+  Name + " §7am §c" + formatTime(System.currentTimeMillis()));
            itemMeta.setLore(lore);
        }

        sm.getSignColor().removeColor(player);

        if (sm.isEnchant()) {

            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);

        }

        itemStack.setItemMeta(itemMeta);
        player.getInventory().setItemInMainHand(itemStack);
        plugin.getManagers().remove(player);
    }

 */

    public void setEnchant(boolean enchant) {
        isEnchant = enchant;
    }

    public Player getP() {
        return p;
    }

    public void setP(Player p) {
        this.p = p;
    }

    public ItemStack getItem() {
        return item;
    }
    public ItemStack getItemNoDefaultName() {
        if (meta.hasDisplayName()) {
            return item;
        }else {
            ItemMeta m = item.getItemMeta();
            ItemStack i = new ItemStack(item);
            m.setDisplayName(" ");
            i.setItemMeta(m);
            return i;
        }
    }

    public void setLore(String[] s){

        if (s[0] != null){
            line1 = s[0];
        }
        if (s[1] != null){
            line2 = s[1];
        }
        if (s[2] != null){
            line3 = s[2];
        }
        if (s[3] != null){
            line4 = s[3];
        }
    }

    public String[] getLore(){
        String[] l = new String[4];
        if (line1 == null){
            line1 = "";
        }
        if (line2 == null){
            line2 = "";
        }
        if (line3 == null){
            line3 = "";
        }
        if (line4 == null){
            line4 = "";
        }
        l[0] = line1;
        l[1] = line2;
        l[2] = line3;
        l[3] = line4;
        return l;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public ItemMeta getMeta() {
        return meta;
    }

    public void setMeta(ItemMeta meta) {
        this.meta = meta;
    }

    public ArrayList<String> getLores() {
        return lores;
    }

    public void setLores(ArrayList<String> lores) {
        this.lores = lores;
    }

    public boolean isEnchant() {
        return isEnchant;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public String isTime() {
        return time;
    }

    public void setTime(String t) {
        time = t;
    }

}

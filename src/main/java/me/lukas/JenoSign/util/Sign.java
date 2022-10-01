package me.lukas.JenoSign.util;

import me.lukas.JenoSign.JenoSign;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class Sign {

    private final Player p;
    private String name;
    private String time;
    private ItemStack item;
    private ItemMeta meta;
    private String line1;
    private String line2;
    private String line3;
    private String line4;

    private String line;

    private boolean isEnchant;


    public Sign(Player p, ItemStack pi, String lore){
        this.p = p;
        this.name = p.getName();
        this.time = "normal";
        this.item = new ItemStack(pi);
        this.meta = item.getItemMeta();
        JenoSign.signMap.put(p.getUniqueId(), this);
        line = "§7§m---------------------------------------";
        lore = lore.replace("&", "§");
        lore = "§7" + lore;
        line1 = lore;

    }

    public void changeItemName(String n){
        meta.setDisplayName(n);
        item.setItemMeta(meta);
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

    private void checkLinesNull(){
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
    }

    public ItemStack createDeploySign(){

        String z = manageTime(time);
        String Signiert = manageName(name, p.getUniqueId());

        ItemStack i = new ItemStack(item);
        ItemMeta m = i.getItemMeta();
        ArrayList<String> l= new ArrayList<>();

        checkLinesNull();

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
        l.add(line);
        l.add(Signiert + z);
        m.setLore(l);

        if (isEnchant){
            m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            m.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        }

        i.setItemMeta(m);
        return i;
    }

    public ItemStack createDeploySign(String[] s){

        String z = manageTime(time);
        String Signiert = manageName(name, p.getUniqueId());
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

        if (isEnchant){
            m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            m.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        }

        i.setItemMeta(m);
        return i;
    }
    public void setSignatureSettings(String[] s){
        this.time = s[0];
        this.name = s[1];
        this.line = s[2];
    }

    public ItemStack createDeploySignSettings(String[] s){
        String z = manageTime(s[0]);
        String Signiert = manageName(s[1], p.getUniqueId());

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
        l.add(s[2]);
        l.add(Signiert + z);
        m.setLore(l);

        if (isEnchant){
            m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            m.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        }

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



    private String manageName(String s, UUID uuid){
            String suffix = "";

        try {
            LuckPerms lp = LuckPermsProvider.get();
            User u = lp.getUserManager().loadUser(uuid).get();
            suffix = u.getCachedData().getMetaData().getSuffix();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        suffix = suffix.replace("&","§");
        String signiert = "§7Signiert von ";
        if (s == null){
            signiert = "§7Signiert ";
            return signiert;
        }else if (s.equalsIgnoreCase("Jeno")){
            signiert = "§7Signiert vom §4§lJenoMiners-Team ";
            return signiert;
        }else return signiert + suffix + p.getName()+" ";
    }

    private ChatColor manageHex(String suffix){
        return ChatColor.valueOf(suffix);
    }


    public void setEnchant(boolean enchant) {
        isEnchant = enchant;
    }


    public ItemStack getItem() {
        return item;
    }
    public ItemStack getItemNoDefaultName() {
        if (meta.hasDisplayName()) {
            ItemStack i = new ItemStack(item);
            ItemMeta m = i.getItemMeta();
            String name = m.getDisplayName();
            name = name.replace("§", "&");
            m.setDisplayName(name);
            i.setItemMeta(m);
            return i;
        }else {
            ItemMeta m = item.getItemMeta();
            ItemStack i = new ItemStack(item);
            m.setDisplayName(" ");
            i.setItemMeta(m);
            return i;
        }
    }

    public void setLore(String[] s){
        if (s == null){
            line1="";
            line2="";
            line3="";
            line4="";
        }
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

    public void setLine(String line) {
        this.line = line;
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

package me.lukas.JenoSign;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.lukas.JenoSign.commands.SignCommand;
import me.lukas.JenoSign.commands.UnsignCommand;
import me.lukas.JenoSign.util.ProtocolLibReader;
import me.lukas.JenoSign.util.Sign;
import me.oxolotel.utils.bukkit.menuManager.InventoryMenuManager;
import me.oxolotel.utils.wrapped.module.ModuleManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


import java.util.HashMap;
import java.util.UUID;

public final class JenoSign extends JavaPlugin {

    public JenoSign plugin;

    public static final String PREFIX = "§7§l[§b§lSign§7§l]";
    public static final String VERSION = "2.0";

    public static HashMap<UUID, Sign> signMap = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        ModuleManager.loadModule(ModuleManager.getPluginModuleByClass(InventoryMenuManager.class));


        getCommand("sign").setExecutor(new SignCommand());
        getCommand("unsign").setExecutor(new UnsignCommand());

        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        ProtocolLibReader protocolLibReader = new ProtocolLibReader();
        protocolLibReader.readWindowClickPacket(protocolManager, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public JenoSign getPlugin() {
        return plugin;
    }

}

package me.lukas.JenoSign;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.lukas.JenoSign.commands.SignCommand;
import me.lukas.JenoSign.commands.UnsignCommand;
import me.lukas.JenoSign.util.ProtocolLibReader;
import me.oxolotel.utils.bukkit.menuManager.InventoryMenuManager;
import me.oxolotel.utils.wrapped.module.ModuleManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import me.lukas.JenoSign.listener.*;

public final class JenoSign extends JavaPlugin {

    public JenoSign plugin;

    public static final String PREFIX = "§7§l[§b§lQuest§7§l]";

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        ModuleManager.loadModule(ModuleManager.getPluginModuleByClass(InventoryMenuManager.class));

        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);

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

    private void loadConfig(){


        this.getConfig().options().header(
                "#  +----------------------------------------------------------------------------------------------+ # \n" +
                        "#  |                                       ___     ___   _                                        | # \n" +
                        "#  |                                      |___  | | __  | | |                                     | # \n" +
                        "#  |                                       ___| | |___| | |_| Plugin made                         | # \n" +
                        "#  |                                                                     with ♡ by Lukxi          | # \n" +
                        "#  +----------------------------------------------------------------------------------------------+ # \n");

        if (!this.getConfig().isSet("SignPlugin.signcommand.SignSettings")) {
            this.getConfig().addDefault("SignPlugin.signcommand.SignSettings", true);
        }
        //SignSettings = this.getConfig().getBoolean("SignPlugin.signcommand.SignSettings");

        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

    }

    public JenoSign getPlugin() {
        return plugin;
    }

}

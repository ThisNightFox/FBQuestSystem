package de.furubafox.fBQuestSystem;

import de.furubafox.fBQuestSystem.commands.PlayerQuestGui;
import de.furubafox.fBQuestSystem.utils.Util;
import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;
import me.clip.placeholderapi.PlaceholderAPIPlugin;
import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;


public final class FBQuestSystem extends JavaPlugin implements Listener {
    public static final String prefix = Util.Color("&f[&6FB-&aQuestsystem&f] §8₪");
    private static FBQuestSystem instance;
    PlayerPointsAPI ppAPI = PlayerPoints.getInstance().getAPI();
    private YamlDocument mainConfig;
    private YamlDocument messages;

    @Override
    public void onEnable() {
        Logger log = getLogger();

        log.info("QuestPlugin is enabled!");

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            /*
             * We register the EventListener here, when PlaceholderAPI is installed.
             * Since all events are in the main class (this class), we simply use "this"
             */
            Bukkit.getPluginManager().registerEvents(this, this);
        } else {
            log.info("=============================================");
            log.info(" ");
            log.info("DE: Deaktiviert, bitte PlaceholderAPI Plugin Installieren!");
            log.info("EN: Disabled, Please install the PlaceholderAPI plugin!");
            log.info(" ");
            log.info("=============================================");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }log.info("Es wurde das Plugin PlaceholderAPI gefunden." + PlaceholderAPIPlugin.getServerVersion().getVersion());

        if (Bukkit.getPluginManager().isPluginEnabled("PlayerPoints")) {
            log.info("Playerpoints Plugin found!");
        } else {
            log.info("=============================================");
            log.info(" ");
            log.info("DE: Deaktiviert, bitte Playerpoints Plugin Installieren!");
            log.info("EN: Disabled, Please install the Playerpoints plugin!");
            log.info(" ");
            log.info("=============================================");
            getServer().getPluginManager().disablePlugin(this);
        }
        try {
            this.mainConfig = YamlDocument.create(new File(getDataFolder(), "config.yml"),
                    Objects.requireNonNull(getResource("config.yml")),
                    GeneralSettings.builder().setUseDefaults(false).build(),
                    LoaderSettings.builder().setAutoUpdate(true).build(),
                    DumperSettings.DEFAULT,
                    UpdaterSettings.builder().setVersioning(new BasicVersioning("config-version")).build()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Commands registrieren
        loadListener(Bukkit.getPluginManager());
        Objects.requireNonNull(getCommand("tpo")).setExecutor((CommandExecutor) new PlayerQuestGui(this.mainConfig,this.messages));

    }

    //Listeners registrieren
    private void loadListener(final PluginManager pluginManager) {
        //pluginManager.registerEvents(new PlayerQuestGui(this.mainConfig, this.messages),this);
        pluginManager.registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("QuestPlugin is disabled!");
    }

    private void sendMessage(String loading) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "=====------===== " + prefix + ChatColor.AQUA +"=====------=====");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "§f[§6FB-§aMulti§8System§f] " + ChatColor.BLUE + "Status: " + ChatColor.GREEN + loading );
        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "§f[§6FB-§aMulti§8System§f] " + ChatColor.BLUE + "Author: " + this.getDescription().getAuthors());
        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "§f[§6FB-§aMulti§8System§f] " + ChatColor.BLUE + "Version: " + this.getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "=====------===== " + prefix + ChatColor.AQUA +"=====------=====");
    }

    public static FBQuestSystem getInstance() {
        return instance;
    }
}

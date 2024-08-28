package de.furubafox.fBQuestSystem;

import de.furubafox.fBQuestSystem.commands.AdminQuestGui;
import de.furubafox.fBQuestSystem.commands.PlayerQuestGui;
import de.furubafox.fBQuestSystem.listeners.GuiClickListener;
import de.furubafox.fBQuestSystem.manager.QuestProgressListener;
import de.furubafox.fBQuestSystem.utils.Util;
import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;
import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
    private YamlDocument mainConfig;
    private YamlDocument messages;

    @Override
    public void onEnable() {
        Logger log = getLogger();
        sendMessage("§aEnabled");
        sendMessage2("Activated");
        loadfilemessages("All Files loaded correctly");

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
        }

        if (Bukkit.getPluginManager().isPluginEnabled("PlayerPoints")) {
            PlayerPointsAPI ppAPI = PlayerPoints.getInstance().getAPI();
        } else {
            log.info("=============================================");
            log.info(" ");
            log.info("DE: Deaktiviert, bitte Playerpoints Plugin Installieren!");
            log.info("EN: Disabled, Please install the Playerpoints plugin!");
            log.info(" ");
            log.info("=============================================");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        File questFolder = new File(getDataFolder(), "quests");
        if (!questFolder.exists()) {
            if (questFolder.mkdirs()) {
                getLogger().info("quest-Ordner created");
            } else
                getLogger().warning("quests folder could not be created.");
        }

        createDefaultQuestFile("TemplateDailyQuest.yml");
        createDefaultQuestFile("TemplateWeeklyQuest.yml");
        createDefaultQuestFile("TemplateMonthlyQuest.yml");

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
        try {
            this.messages = YamlDocument.create(new File(getDataFolder(), "languages/messages.yml"),
                    Objects.requireNonNull(getResource("languages/messages.yml")),
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
        Objects.requireNonNull(getCommand("fbquest")).setExecutor(new PlayerQuestGui(this.mainConfig,this.messages));
        Objects.requireNonNull(getCommand("fbquestadmin")).setExecutor(new AdminQuestGui(this.mainConfig,this.messages));

    }

    //Listeners registrieren
    private void loadListener(final PluginManager pluginManager) {
        pluginManager.registerEvents(new GuiClickListener(this.mainConfig, this.messages),this);
        pluginManager.registerEvents(new QuestProgressListener(),this);
    }

    @Override
    public void onDisable() {
        sendMessage("§4Deaktiviert");
    }

    private void sendMessage2(String enabled) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "=====------===== " + prefix + ChatColor.AQUA +"=====------=====");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "§f[§6FB-§aQuest§8System§f] " + ChatColor.BLUE + "Placeholder: " + ChatColor.GREEN + enabled );
        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "§f[§6FB-§aQuest§8System§f] " + ChatColor.BLUE + "Playerpoints: " + ChatColor.GREEN + enabled);
        // Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "§f[§6FB-§aQuest§8System§f] " + ChatColor.BLUE + "Citiziens: " + ChatColor.GREEN + enabled);
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "=====------===== " + prefix + ChatColor.AQUA +"=====------=====");
    }
    private void sendMessage(String loading) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "=====------===== " + prefix + ChatColor.AQUA +"=====------=====");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "§f[§6FB-§aQuest§8System§f] " + ChatColor.BLUE + "Status: " + ChatColor.GREEN + loading );
        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "§f[§6FB-§aQuest§8System§f] " + ChatColor.BLUE + "Author: " + this.getDescription().getAuthors());
        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "§f[§6FB-§aQuest§8System§f] " + ChatColor.BLUE + "Version: " + this.getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "=====------===== " + prefix + ChatColor.AQUA +"=====------=====");
    }

    private void loadfilemessages(String filesload) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "=====------===== " + prefix + ChatColor.AQUA +"=====------=====");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "§f[§6FB-§aQuest§8System§f] " + ChatColor.BLUE + "Status: " + ChatColor.GREEN + filesload );
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "=====------===== " + prefix + ChatColor.AQUA +"=====------=====");
    }

    private void createDefaultQuestFile(String fileName) {
        File file = new File(getDataFolder(), "quests/" + fileName);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    getLogger().info(fileName + "was created.");
                }else {
                    getLogger().warning(fileName + "could not be created.");
            }
        } catch (IOException e) {
            getLogger().warning("Error when creating the file" + fileName + ":" + e.getMessage());
        }
    }
    }

    public static FBQuestSystem getInstance() {
        return instance;
    }
}

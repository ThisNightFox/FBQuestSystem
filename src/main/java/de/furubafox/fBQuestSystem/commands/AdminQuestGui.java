package de.furubafox.fBQuestSystem.commands;

import dev.dejvokep.boostedyaml.YamlDocument;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class AdminQuestGui implements CommandExecutor {

    private final YamlDocument mainConfig;
    private final YamlDocument messages;

    public AdminQuestGui(YamlDocument mainConfig, YamlDocument messages) {
        this.mainConfig = mainConfig;
        this.messages = messages;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        sender.sendMessage("§cFunktion derzeit in Entwicklung");
        return false;
    }
}

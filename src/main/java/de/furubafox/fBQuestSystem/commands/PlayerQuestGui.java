package de.furubafox.fBQuestSystem.commands;

import de.furubafox.fBQuestSystem.utils.QuestInventoryHolder;
import de.furubafox.fBQuestSystem.utils.ItemBuilder;
import dev.dejvokep.boostedyaml.YamlDocument;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class PlayerQuestGui implements CommandExecutor {

    private final YamlDocument mainConfig;
    private final YamlDocument messages;

    public PlayerQuestGui(YamlDocument mainConfig, YamlDocument messages) {
        this.mainConfig = mainConfig;
        this.messages = messages;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(messages.getString("messages.console")
                    .replace("%prefix%", messages.getString("prefix")))));
            return true;
        }

        if (!sender.hasPermission("fbquest.use")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(messages.getString("messages.no-perms")
                    .replace("%prefix%", messages.getString("prefix")))));
            return true;
        }

        // GUI-Titel aus der Config laden
        String title = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(mainConfig.getString("gui.player.title")
                .replace("%player%", player.getName())));

        // Erstelle das Inventar (GUI) mit einem benutzerdefinierten Holder
        Inventory questGui = Bukkit.createInventory(new QuestInventoryHolder("playerQuestGui"), 9, title);

        // Füge die Items wie zuvor hinzu
        ItemStack dailyItem = new ItemBuilder(Material.PAPER)
                .setDisplayName(ChatColor.GREEN + mainConfig.getString("gui.player.dailyDisplayName"))
                .setLore(ChatColor.GRAY + mainConfig.getString("gui.player.dailyLore"))
                .setAmount(1)
                .setUnbreakable()
                .setHideEnchantment()
                .build();

        ItemStack weeklyItem = new ItemBuilder(Material.BOOK)
                .setDisplayName(ChatColor.BLUE + "Weekly Quests")
                .setLore(ChatColor.GRAY + "Complete your weekly quests here!")
                .addEnchantment(org.bukkit.enchantments.Enchantment.DURABILITY, 1)
                .setHideEnchantment()
                .build();

        ItemStack monthlyItem = new ItemBuilder(Material.BOOKSHELF)
                .setDisplayName(ChatColor.GOLD + "Monthly Quests")
                .setLore(ChatColor.GRAY + "Complete your monthly quests here!")
                .addItemFlags(org.bukkit.inventory.ItemFlag.HIDE_ATTRIBUTES)
                .build();

        questGui.setItem(3, dailyItem);
        questGui.setItem(4, weeklyItem);
        questGui.setItem(5, monthlyItem);

        player.openInventory(questGui);

        return true;
    }
}

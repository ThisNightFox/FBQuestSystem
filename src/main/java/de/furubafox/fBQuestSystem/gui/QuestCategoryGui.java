package de.furubafox.fBQuestSystem.gui;

import de.furubafox.fBQuestSystem.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class QuestCategoryGui {

    public void openCategoryGui(Player player, String category) {
        // Titel und Größe der GUI festlegen
        String title = ChatColor.GOLD + category.substring(0, 1).toUpperCase() + category.substring(1) + " Quests";
        Inventory categoryGui = Bukkit.createInventory(new QuestInventoryHolder("categoryQuestGui"), 27, title);

        // Platzhalter-Items hinzufügen (kann später durch echte Quests ersetzt werden)
        ItemStack placeholder = new ItemBuilder(Material.PAPER)
                .setDisplayName(ChatColor.GRAY + "Quest Placeholder")
                .setLore(ChatColor.DARK_GRAY + "Quests will be listed here.")
                .build();

        for (int i = 0; i < categoryGui.getSize(); i++) {
            categoryGui.setItem(i, placeholder);
        }

        // Öffne die neue GUI für den Spieler
        player.openInventory(categoryGui);
    }
}


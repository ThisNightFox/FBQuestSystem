package de.furubafox.fBQuestSystem.gui;

import de.furubafox.fBQuestSystem.manager.Quest;
import de.furubafox.fBQuestSystem.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class QuestCategoryGui {

    private final List<Quest> quests;

    public QuestCategoryGui(List<Quest> quests) {
        this.quests = quests;
    }

    public void openCategoryGui(Player player, String category) {
        // Titel und Größe der GUI festlegen
        String title = ChatColor.GOLD + category.substring(0, 1).toUpperCase() + category.substring(1) + " Quests";
        Inventory categoryGui = Bukkit.createInventory(new QuestInventoryHolder("categoryQuestGui"), 27, title);

        int slot = 0;
        for (Quest quest : quests) {
            if (quest.getCategory().equalsIgnoreCase(category)){
                ItemStack questItem = new ItemBuilder(Material.valueOf(quest.getMaterial()))
                        .setDisplayName(ChatColor.translateAlternateColorCodes('&', quest.getDisplayName()))
                        .setLore(quest.getDescription().toArray(new String[0]))
                        .build();
                categoryGui.setItem(slot++, questItem);
            }
        }
        // Öffne die neue GUI für den Spieler
        player.openInventory(categoryGui);
    }
}


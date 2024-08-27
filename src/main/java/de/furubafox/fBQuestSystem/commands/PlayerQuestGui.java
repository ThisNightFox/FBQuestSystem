package de.furubafox.fBQuestSystem.commands;

import dev.dejvokep.boostedyaml.YamlDocument;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PlayerQuestGui {

    private final YamlDocument mainConfig;
    private final YamlDocument messages;

    public PlayerQuestGui(YamlDocument mainConfig, YamlDocument messages) {
        this.mainConfig = mainConfig;
        this.messages = messages;
    }

    public void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 9,  mainConfig.getString("gui.player.title"));

        // Create buttons
        ItemStack dailyQuestButton = createButton("Daily Quests", "Click to see daily quests");
        ItemStack weeklyQuestButton = createButton("Weekly Quests", "Click to see weekly quests");
        ItemStack monthlyQuestButton = createButton("Monthly Quests", "Click to see monthly quests");

        // Add buttons to the GUI
        gui.setItem(2, dailyQuestButton);
        gui.setItem(4, weeklyQuestButton);
        gui.setItem(6, monthlyQuestButton);

        // Open GUI for player
        player.openInventory(gui);
    }

    private ItemStack createButton(String name, String lore) {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);

        List<String> loreList = new ArrayList<>();
        loreList.add(lore);
        meta.setLore(loreList);

        item.setItemMeta(meta);
        return item;
    }

    // Handle clicks in the GUI
    public void onGUIClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null || !clickedItem.hasItemMeta()) return;

        String itemName = clickedItem.getItemMeta().getDisplayName();

        if (itemName.equals("Daily Quests")) {
            // Open daily quests
        } else if (itemName.equals("Weekly Quests")) {
            // Open weekly quests
        } else if (itemName.equals("Monthly Quests")) {
            // Open monthly quests
        }
    }
}


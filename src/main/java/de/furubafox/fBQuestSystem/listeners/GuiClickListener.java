package de.furubafox.fBQuestSystem.listeners;

import de.furubafox.fBQuestSystem.gui.QuestCategoryGui;
import de.furubafox.fBQuestSystem.gui.QuestInventoryHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GuiClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof QuestInventoryHolder holder) {
            String guiType = holder.getGuiType();

            if (guiType.equals("playerQuestGui")) {
                event.setCancelled(true); // Verhindert, dass der Spieler das Item aus der GUI herausnimmt

                Player player = (Player) event.getWhoClicked();
                // Zusätzliche Logik für den Klick auf spezielle Items
                switch (event.getSlot()) {
                    case 3:
                        new QuestCategoryGui().openCategoryGui(player, "daily");
                        event.getWhoClicked().sendMessage("You clicked on Daily Quests!");
                        break;
                    case 4:
                        new QuestCategoryGui().openCategoryGui(player, "weekly");
                        event.getWhoClicked().sendMessage("You clicked on Weekly Quests!");
                        break;
                    case 5:
                        new QuestCategoryGui().openCategoryGui(player, "monthly");
                        event.getWhoClicked().sendMessage("You clicked on Monthly Quests!");
                        break;
                    default:
                        break;
                }
            }

            // Du kannst weitere `guiType`-Prüfungen hinzufügen, wenn du zusätzliche GUIs hinzufügst
        }
    }
}


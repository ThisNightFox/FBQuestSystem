package de.furubafox.fBQuestSystem.listeners;

import de.furubafox.fBQuestSystem.commands.PlayerQuestGui;
import de.furubafox.fBQuestSystem.gui.QuestCategoryGui;
import de.furubafox.fBQuestSystem.gui.QuestInventoryHolder;
import de.furubafox.fBQuestSystem.manager.Quest;
import de.furubafox.fBQuestSystem.manager.QuestManager;
import de.furubafox.fBQuestSystem.manager.QuestType;
import dev.dejvokep.boostedyaml.YamlDocument;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

public class GuiClickListener implements Listener {

    private final YamlDocument mainConfig;
    private final YamlDocument messages;

    public GuiClickListener(YamlDocument mainConfig, YamlDocument messages) {
        this.mainConfig = mainConfig;
        this.messages = messages;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof QuestInventoryHolder holder) {
            String guiType = holder.getGuiType();

            if (guiType.equals("playerQuestGui")) {
                event.setCancelled(true); // Verhindert, dass der Spieler das Item aus der GUI herausnimmt

                Player player = (Player) event.getWhoClicked();
                List<Quest> quests = QuestManager.getAllQuests();
                // Zusätzliche Logik für den Klick auf spezielle Items
                switch (event.getSlot()) {
                    case 3:
                        new QuestCategoryGui(quests).openCategoryGui(player, "daily");
                        event.getWhoClicked().sendMessage("You clicked on Daily Quests!");
                        break;
                    case 4:
                        new QuestCategoryGui(quests).openCategoryGui(player, "weekly");
                        event.getWhoClicked().sendMessage("You clicked on Weekly Quests!");
                        break;
                    case 5:
                        new QuestCategoryGui(quests).openCategoryGui(player, "monthly");
                        event.getWhoClicked().sendMessage("You clicked on Monthly Quests!");
                        break;
                    default:
                        break;
                }
            } else if (guiType.equals("categoryQuestGui")) {
                event.setCancelled(true); // Verhindert das Entfernen der Items

                if (event.getSlot() == 26) { // Prüft, ob der "Zurück"-Button (Barrier) angeklickt wurde
                    new PlayerQuestGui(mainConfig,messages).openQuestSelectionGui((Player) event.getWhoClicked());
                }
            }

            // Du kannst weitere `guiType`-Prüfungen hinzufügen, wenn du zusätzliche GUIs hinzufügst
        }
    }
}


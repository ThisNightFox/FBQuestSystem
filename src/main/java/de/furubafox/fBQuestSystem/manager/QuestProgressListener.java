package de.furubafox.fBQuestSystem.commands.manager;

import de.furubafox.fBQuestSystem.manager.Quest;
import de.furubafox.fBQuestSystem.manager.QuestType;
import de.furubafox.fBQuestSystem.manager.QuestManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class QuestProgressListener implements Listener {

    private final QuestManager questManager;

    // Konstruktor zum Initialisieren des QuestManagers
    public QuestProgressListener(QuestManager questManager) {
        this.questManager = questManager;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Material blockType = event.getBlock().getType();

        // Überprüfen, ob der Spieler eine aktive Quest hat, die den Blocktyp erfordert
        for (Quest quest : questManager.getActiveQuests(player)) {
            for (QuestType questType : quest.getQuestTypes().values()) {
                if (questType.getType().equals("BREAK") && questType.getValue().equals(blockType.name())) {
                    // Fortschritt erhöhen
                    questManager.incrementQuestProgress(player, quest, questType);
                    break; // Wenn gefunden, bricht die Schleife ab (angenommen, nur eine Instanz des Ziels relevant ist)
                }
            }
        }
    }
}

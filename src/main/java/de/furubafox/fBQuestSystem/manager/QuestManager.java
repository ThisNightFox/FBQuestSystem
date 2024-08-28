package de.furubafox.fBQuestSystem.manager;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestManager {

    private final Map<Player, List<Quest>> playerActiveQuests = new HashMap<>();
    private static List<Quest> quests = new ArrayList<>();

    public List<Quest> getActiveQuests(Player player) {
        return playerActiveQuests.getOrDefault(player, List.of());
    }

    public void incrementQuestProgress(Player player, Quest quest, QuestType questType) {
        // Implementiere Logik zur Aktualisierung des Fortschritts für die Quest
        // Benachrichtige den Spieler über den Fortschritt
    }

    public void addActiveQuest(Player player, Quest quest) {
        // Implementiere Logik zum Hinzufügen einer aktiven Quest für den Spieler
    }

    public static List<Quest> getAllQuests() {
        return new ArrayList<>(quests); // Gibt eine Kopie der Liste zurück
    }
}

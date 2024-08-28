package de.furubafox.fBQuestSystem.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quest {

    private final String displayName;
    private final String material;
    private final int amount;
    private final String category;
    private final List<String> description;
    private final List<String> rewardDescription;
    private final Map<String, QuestType> questTypes;
    private final List<String> rewards;

    public Quest(String displayName, String material, int amount, String category,
                 List<String> description, List<String> rewardDescription, Map<String, QuestType> questTypes, List<String> rewards) {
        this.displayName = displayName;
        this.material = material;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.rewardDescription = rewardDescription;
        // Stelle sicher, dass questTypes nicht null ist
        this.questTypes = (questTypes != null) ? questTypes : new HashMap<>();
        this.rewards = rewards;
    }

    // Getter-Methoden

    public String getDisplayName() {
        return displayName;
    }

    public String getMaterial() {
        return material;
    }

    public int getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getDescription() {
        return description;
    }

    public List<String> getRewardDescription() {
        return rewardDescription;
    }

    public Map<String, QuestType> getQuestTypes() {
        return questTypes;
    }

    public List<String> getRewards() {
        return rewards;
    }
}


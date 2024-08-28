package de.furubafox.fBQuestSystem.manager;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestLoader {

    public static Quest loadFromYaml(YamlConfiguration config) {
        String displayName = config.getString("display");
        String material = config.getString("material");
        int amount = config.getInt("amount");
        String category = config.getString("category");
        List<String> description = config.getStringList("description");
        List<String> rewardDescription = config.getStringList("reward_description");

        Map<String, QuestType> questTypes = new HashMap<>();
        ConfigurationSection questTypeSection = config.getConfigurationSection("questtype");
        if (questTypeSection != null) {
            for (String key : questTypeSection.getKeys(false)) {
                String type = questTypeSection.getString(key + ".type");
                String value = questTypeSection.getString(key + ".value");
                int goalAmount = questTypeSection.getInt(key + ".amount");
                questTypes.put(key, new QuestType(type, value, goalAmount));
            }
        }

        List<String> rewards = config.getStringList("reward.run");

        return new Quest(displayName, material, amount, category, description, rewardDescription, questTypes, rewards);
    }
}

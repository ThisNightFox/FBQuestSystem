package de.furubafox.fBQuestSystem.manager;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class QuestProgressBar {

    private final BossBar bossBar;

    public QuestProgressBar(Player player) {
        this.bossBar = Bukkit.createBossBar("Quest Fortschritt", BarColor.GREEN, BarStyle.SEGMENTED_10);
        this.bossBar.addPlayer(player);
    }

    public void updateProgress(Player player, Quest quest, int progress, int total) {
        double progressPercentage = (double) progress / total;
        bossBar.setProgress(progressPercentage);
        bossBar.setTitle(quest.getDisplayName() + " (" + progress + "/" + total + ")");
    }
}


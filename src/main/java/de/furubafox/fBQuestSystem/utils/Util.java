package de.furubafox.fBQuestSystem.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Util {
    public static String Color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static HashMap<Player, Player> tpas = new HashMap<Player, Player>();
}

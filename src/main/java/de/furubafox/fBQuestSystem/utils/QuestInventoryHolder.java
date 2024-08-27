package de.furubafox.fBQuestSystem.utils;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class QuestInventoryHolder implements InventoryHolder {

    private final String guiType;

    public QuestInventoryHolder(String guiType) {
        this.guiType = guiType;
    }

    public String getGuiType() {
        return guiType;
    }

    @Override
    public Inventory getInventory() {
        return null; // Hier wird das tatsächliche Inventar zurückgegeben, wenn nötig
    }
}

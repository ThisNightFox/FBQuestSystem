package de.furubafox.fBQuestSystem.manager;

public class QuestType {
    private final String type;
    private final String value;
    private final int amount;

    public QuestType(String type, String value, int amount) {
        this.type = type;
        this.value = value;
        this.amount = amount;
    }

    public String getType() { return type; }
    public String getValue() { return value; }
    public int getAmount() { return amount; }
}


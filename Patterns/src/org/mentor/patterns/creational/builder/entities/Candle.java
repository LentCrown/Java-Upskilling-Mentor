package org.mentor.patterns.creational.builder.entities;

public class Candle {
    private int amount;
    private String type;

    public Candle(int amount, String type) {
        this.amount = amount;
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Candle{" +
                "amount=" + amount +
                ", type='" + type + '\'' +
                '}';
    }
}

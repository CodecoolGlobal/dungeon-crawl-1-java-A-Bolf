package com.codecool.dungeoncrawl.logic.items;

public enum ItemType {
    KEY('K'),
    SWORD('W'),
    FOOD('F');

    private char symbol;

    ItemType(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return this.symbol;
    }

}

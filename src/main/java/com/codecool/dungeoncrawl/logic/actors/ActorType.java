package com.codecool.dungeoncrawl.logic.actors;

public enum ActorType {


    OGRE('o'),
    PLAYER('@'),
    SKELETON('s');

    private char symbol;
    ActorType(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}

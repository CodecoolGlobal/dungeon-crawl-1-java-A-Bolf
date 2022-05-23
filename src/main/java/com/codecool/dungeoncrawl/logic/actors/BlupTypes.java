package com.codecool.dungeoncrawl.logic.actors;

public enum BlupTypes {
    MAIN("mainBlup", new BlupGrowSide[]{BlupGrowSide.LEFT, BlupGrowSide.RIGHT}, false),
    LEFT_RIGHT("leftRightBlup", new BlupGrowSide[]{BlupGrowSide.LEFT, BlupGrowSide.RIGHT}),
    UP_DOWN("upDownBlup", new BlupGrowSide[]{BlupGrowSide.UP, BlupGrowSide.DOWN}),
    UP_LEFT("upLeftBlup", new BlupGrowSide[]{BlupGrowSide.UP, BlupGrowSide.LEFT}),
    UP_RIGHT("upRightBlup", new BlupGrowSide[]{BlupGrowSide.UP, BlupGrowSide.RIGHT}),
    DOWN_LEFT("downLeftBlup", new BlupGrowSide[]{BlupGrowSide.DOWN, BlupGrowSide.LEFT}),
    DOWN_RIGHT("downRightBlup", new BlupGrowSide[]{BlupGrowSide.DOWN, BlupGrowSide.RIGHT});

    private final String stringName;
    private final BlupGrowSide[] growSides;
    private boolean autoGrow = true;

    BlupTypes(String stringName, BlupGrowSide[] growSides, boolean autoGrow){
        this.stringName = stringName;
        this.growSides = growSides;
        this.autoGrow = autoGrow;
    }

    BlupTypes(String stringName, BlupGrowSide[] growSides){
        this.stringName = stringName;
        this.growSides = growSides;}

    public String getStringName() {
        return stringName;
    }

    public void turnInAutoGrow() {
        autoGrow = true;
    }

    public boolean isAutoGrow() {
        return autoGrow;
    }

    public BlupGrowSide[] getGrowSides() {
        return growSides;
    }
}

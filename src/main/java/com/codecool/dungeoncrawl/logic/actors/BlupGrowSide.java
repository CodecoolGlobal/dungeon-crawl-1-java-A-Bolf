package com.codecool.dungeoncrawl.logic.actors;

public enum BlupGrowSide {
    UP(new BlupTypes[]{BlupTypes.UP_DOWN, BlupTypes.DOWN_LEFT, BlupTypes.DOWN_RIGHT}, 0, -1),
    DOWN(new BlupTypes[]{BlupTypes.UP_DOWN, BlupTypes.UP_LEFT, BlupTypes.UP_RIGHT}, 0, 1),
    LEFT(new BlupTypes[]{BlupTypes.LEFT_RIGHT, BlupTypes.UP_RIGHT, BlupTypes.DOWN_RIGHT}, -1, 0),
    RIGHT(new BlupTypes[]{BlupTypes.LEFT_RIGHT, BlupTypes.UP_LEFT, BlupTypes.DOWN_RIGHT}, 1, 0);

    private final BlupTypes[] possibleBlupTypes;
    private final int xModifier;
    private final int yModifier;

    BlupGrowSide(BlupTypes[] possibleBlupTypes, int xModifier, int yModifier){
        this.possibleBlupTypes = possibleBlupTypes;
        this.xModifier = xModifier;
        this.yModifier = yModifier;
    }

    public BlupTypes[] getPossibleBlupTypes() {
        return possibleBlupTypes;
    }

    public int getxModifier() {
        return xModifier;
    }

    public int getyModifier() {
        return yModifier;
    }
}

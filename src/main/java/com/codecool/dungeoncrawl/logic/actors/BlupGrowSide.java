package com.codecool.dungeoncrawl.logic.actors;

public enum BlupGrowSide {
    UP(0, -1){
        @Override
        public BlupTypes[] getPossibleBlupTypes(){
            return new BlupTypes[]{BlupTypes.UP_DOWN, BlupTypes.DOWN_LEFT, BlupTypes.DOWN_RIGHT};
        }
    },
    DOWN( 0, 1){
        @Override
        public BlupTypes[] getPossibleBlupTypes(){
            return new BlupTypes[]{BlupTypes.UP_DOWN, BlupTypes.UP_LEFT, BlupTypes.UP_RIGHT};
        }
    },
    LEFT( -1, 0){
        @Override
        public BlupTypes[] getPossibleBlupTypes(){
            return new BlupTypes[]{BlupTypes.LEFT_RIGHT, BlupTypes.UP_RIGHT, BlupTypes.DOWN_RIGHT};
        }
    },
    RIGHT(1, 0){
        @Override
        public BlupTypes[] getPossibleBlupTypes(){
            return new BlupTypes[]{BlupTypes.LEFT_RIGHT, BlupTypes.UP_LEFT, BlupTypes.DOWN_LEFT};
        }
    };

    private final int xModifier;
    private final int yModifier;
    private BlupTypes[] blupTypes;

    BlupGrowSide(int xModifier, int yModifier){
        this.xModifier = xModifier;
        this.yModifier = yModifier;
    }

    public abstract BlupTypes[] getPossibleBlupTypes();

    public int getxModifier() {
        return xModifier;
    }

    public int getyModifier() {
        return yModifier;
    }
}

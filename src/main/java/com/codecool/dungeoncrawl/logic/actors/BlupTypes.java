package com.codecool.dungeoncrawl.logic.actors;

public enum BlupTypes {
    MAIN("mainBlup", 'b') {
        @Override
        public BlupGrowSide[] getGrowSides() {
            return new BlupGrowSide[]{BlupGrowSide.LEFT, BlupGrowSide.RIGHT};
        }
    },
    FOUR_WAY("fourWayBlup", '1') {
        @Override
        public BlupGrowSide[] getGrowSides() {
            return new BlupGrowSide[]{BlupGrowSide.UP, BlupGrowSide.DOWN, BlupGrowSide.LEFT, BlupGrowSide.RIGHT};
        }
    },
    LEFT_RIGHT("leftRightBlup", '3') {
        @Override
        public BlupGrowSide[] getGrowSides() {
            return new BlupGrowSide[]{BlupGrowSide.LEFT, BlupGrowSide.RIGHT};
        }
    },
    UP_DOWN("upDownBlup", '2') {
        @Override
        public BlupGrowSide[] getGrowSides() {
            return new BlupGrowSide[]{BlupGrowSide.UP, BlupGrowSide.DOWN};
        }
    },
    UP_LEFT("upLeftBlup", '4') {
        @Override
        public BlupGrowSide[] getGrowSides() {
            return new BlupGrowSide[]{BlupGrowSide.UP, BlupGrowSide.LEFT};
        }
    },
    UP_RIGHT("upRightBlup", '6') {
        @Override
        public BlupGrowSide[] getGrowSides() {
            return new BlupGrowSide[]{BlupGrowSide.UP, BlupGrowSide.RIGHT};
        }
    },
    DOWN_LEFT("downLeftBlup", '5') {
        @Override
        public BlupGrowSide[] getGrowSides() {
            return new BlupGrowSide[]{BlupGrowSide.DOWN, BlupGrowSide.LEFT};
        }
    },
    DOWN_RIGHT("downRightBlup", '7') {
        @Override
        public BlupGrowSide[] getGrowSides() {
            return new BlupGrowSide[]{BlupGrowSide.DOWN, BlupGrowSide.RIGHT};
        }
    };

    private final String stringName;
    private final char symbol;

    BlupTypes(String stringName, char symbol) {
        this.symbol = symbol;
        this.stringName = stringName;
    }

    public String getStringName() {
        return stringName;
    }

    public abstract BlupGrowSide[] getGrowSides();

    public char getSymbol() {
        return symbol;
    }
}

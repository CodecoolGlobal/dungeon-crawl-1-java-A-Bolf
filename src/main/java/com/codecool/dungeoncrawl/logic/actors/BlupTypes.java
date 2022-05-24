package com.codecool.dungeoncrawl.logic.actors;

public enum BlupTypes {
    MAIN("mainBlup", false) {
        @Override
        public BlupGrowSide[] getGrowSides() {
            return new BlupGrowSide[]{BlupGrowSide.LEFT, BlupGrowSide.RIGHT};
        }
    },
    FOUR_WAY("fourWayBlup"){
        @Override
        public BlupGrowSide[] getGrowSides(){
            return new BlupGrowSide[]{BlupGrowSide.UP, BlupGrowSide.DOWN, BlupGrowSide.LEFT, BlupGrowSide.RIGHT};
        }
    },
    LEFT_RIGHT("leftRightBlup") {
        @Override
        public BlupGrowSide[] getGrowSides() {
            return new BlupGrowSide[]{BlupGrowSide.LEFT, BlupGrowSide.RIGHT};
        }
    },
    UP_DOWN("upDownBlup") {
        @Override
        public BlupGrowSide[] getGrowSides() {
            return new BlupGrowSide[]{BlupGrowSide.UP, BlupGrowSide.DOWN};
        }
    },
    UP_LEFT("upLeftBlup") {
        @Override
        public BlupGrowSide[] getGrowSides() {
            return new BlupGrowSide[]{BlupGrowSide.UP, BlupGrowSide.LEFT};
        }
    },
    UP_RIGHT("upRightBlup") {
        @Override
        public BlupGrowSide[] getGrowSides() {
            return new BlupGrowSide[]{BlupGrowSide.UP, BlupGrowSide.RIGHT};
        }
    },
    DOWN_LEFT("downLeftBlup") {
        @Override
        public BlupGrowSide[] getGrowSides() {
            return new BlupGrowSide[]{BlupGrowSide.DOWN, BlupGrowSide.LEFT};
        }
    },
    DOWN_RIGHT("downRightBlup") {
        @Override
        public BlupGrowSide[] getGrowSides() {
            return new BlupGrowSide[]{BlupGrowSide.DOWN, BlupGrowSide.RIGHT};
        }
    };

    private final String stringName;
    private boolean autoGrow = true;

    BlupTypes(String stringName, boolean autoGrow) {
        this.stringName = stringName;
        this.autoGrow = autoGrow;
    }

    BlupTypes(String stringName) {
        this.stringName = stringName;}

        public String getStringName () {
            return stringName;
        }

        public void turnInAutoGrow () {
            autoGrow = true;
        }

        public boolean isAutoGrow () {
            return autoGrow;
        }

        public abstract BlupGrowSide[] getGrowSides ();
    }

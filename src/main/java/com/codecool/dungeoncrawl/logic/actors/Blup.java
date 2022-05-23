package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Blup extends Monster {
    private Cell cell;
    private final BlupTypes blupType;
    /*private boolean upSide = false;
    private boolean downSide = false;
    private boolean leftSide = false;
    private boolean rightSide = false;
    private boolean isGrowing;*/
    private static final Random random = new Random();
    private BlupTypes[] possibleBlupTypes;

    public Blup(Cell cell, BlupTypes blupType) {
        super(cell, 5, 1);
        this.cell = cell;
        this.blupType = blupType;
        /*this.isGrowing = isGrowing;
        switch (blupType) {
            case MAIN:
            case LEFT_RIGHT:
                leftSide = true;
                rightSide = true;
                break;
            case UP_DOWN:
                upSide = true;
                downSide = true;
                break;
            case UP_LEFT:
                upSide = true;
                leftSide = true;
                break;
            case UP_RIGHT:
                upSide = true;
                rightSide = true;
                break;
            case DOWN_LEFT:
                downSide = true;
                leftSide = true;
                break;
            case DOWN_RIGHT:
                downSide = true;
                rightSide = true;
                break;
        }*/
    }

    public void grow(Player player) {
        /*int dX = 0;
        int dY = 0;*/

        if(!blupType.isAutoGrow()){
            //Is Player near the blup
            if (Math.abs(getX() - player.getX()) < 5
                    && Math.abs(getY() - player.getY()) < 5) {
                blupType.turnInAutoGrow();
            }
        }
        else{
            for (BlupGrowSide side :
                    blupType.getGrowSides()) {
                possibleBlupTypes = side.getPossibleBlupTypes();
                move(side.getxModifier(), side.getyModifier());
            }

            /*if(upSide){
                possibleBlupTypes.add(BlupTypes.UP_DOWN);
                possibleBlupTypes.add(BlupTypes.DOWN_RIGHT);
                possibleBlupTypes.add(BlupTypes.DOWN_LEFT);
                dY = -1;
                move(dX,dY);
                dY = 0;

            }
            if(downSide){
                possibleBlupTypes.clear();
                possibleBlupTypes.add(BlupTypes.UP_DOWN);
                possibleBlupTypes.add(BlupTypes.UP_RIGHT);
                possibleBlupTypes.add(BlupTypes.UP_LEFT);
                dY = 1;
                move(dX,dY);
                dY = 0;

            }
            if(leftSide){
                possibleBlupTypes.clear();
                possibleBlupTypes.add(BlupTypes.LEFT_RIGHT);
                possibleBlupTypes.add(BlupTypes.UP_RIGHT);
                possibleBlupTypes.add(BlupTypes.DOWN_RIGHT);
                dX = -1;
                move(dX,dY);
                dX = 0;
            }
            if(rightSide){
                possibleBlupTypes.clear();
                possibleBlupTypes.add(BlupTypes.LEFT_RIGHT);
                possibleBlupTypes.add(BlupTypes.UP_LEFT);
                possibleBlupTypes.add(BlupTypes.DOWN_LEFT);
                dX = 1;
                move(dX,dY);
            }*/

        }
    }

    @Override
    public String getTileName() {
        return blupType.getStringName();
    }

    @Override
    public void move(int dX, int dY) {
        Cell nextCell = cell.getNeighbor(dX,dY);
        BlupTypes newBlupType = possibleBlupTypes[random.nextInt(possibleBlupTypes.length)];
        if(nextCell.getType() == CellType.FLOOR
                && nextCell.getType() != CellType.ITEM
                && !nextCell.isAttackable()){
            new Blup(nextCell, newBlupType);
        }
    }

    @Override
    public void moveMonsters(Player player) {
        grow(player);
    }
}

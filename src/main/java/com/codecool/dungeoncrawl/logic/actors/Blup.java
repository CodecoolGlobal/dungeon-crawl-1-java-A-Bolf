package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Blup extends Monster {
    private Cell cell;
    private final BlupTypes blupType;
    private static final Random random = new Random();
    private BlupTypes[] possibleBlupTypes;
    private boolean isAutoGrow;

    public Blup(Cell cell, BlupTypes blupType, boolean isAutoGrow) {
        super(cell, 5, 1);
        this.cell = cell;
        this.blupType = blupType;
        this.isAutoGrow = isAutoGrow;
    }

    public void grow(Player player) {

        if(!isAutoGrow){
            //Is Player near the blup
            if (Math.abs(getX() - player.getX()) < 5
                    && Math.abs(getY() - player.getY()) < 5) {
                isAutoGrow = true;
                }
        }
        else{
            for (BlupGrowSide side :
                    blupType.getGrowSides()) {
                possibleBlupTypes = side.getPossibleBlupTypes();
                move(side.getxModifier(), side.getyModifier());
            }
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
        // 12% chance to grow a four way Blup
        if (random.nextInt(100) <= 12){
            newBlupType = BlupTypes.FOUR_WAY;
        }
        if(nextCell.getType() == CellType.FLOOR
                && nextCell.getType() != CellType.ITEM
                && !nextCell.isAttackable()){
            new Blup(nextCell, newBlupType, true);
        }
    }

    @Override
    public void moveMonsters(Player player) {
        grow(player);
    }
}

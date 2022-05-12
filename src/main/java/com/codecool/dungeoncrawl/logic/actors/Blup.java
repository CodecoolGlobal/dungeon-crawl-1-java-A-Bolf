package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Blup extends Actor {
    private Cell cell;
    private final String name;
    private boolean upSide = false;
    private boolean downSide = false;
    private boolean leftSide = false;
    private boolean rightSide = false;
    private static final Random random = new Random();

    public Blup(Cell cell, String name) {
        super(cell, 5, 1);
        this.cell = cell;
        this.name = name;
        switch (name) {
            case "mainBlup":
            case "leftRightBlup":
                leftSide = true;
                rightSide = true;
                break;
            case "upDownBlup":
                upSide = true;
                downSide = true;
                break;
            case "upLeftBlup":
                upSide = true;
                leftSide = true;
                break;
            case "upRightBlup":
                upSide = true;
                rightSide = true;
                break;
            case "downLeftBlup":
                downSide = true;
                leftSide = true;
                break;
            case "downRightBlup":
                downSide = true;
                rightSide = true;
                break;
        }
    }

    public void grow(Player player) {
        List<String> blupTypes = new ArrayList<>();
        int dX = 0;
        int dY = 0;
        boolean side;
        if (Math.abs(getX() - player.getX()) < 5
                && Math.abs(getY() - player.getY()) < 5) {
            if(upSide){
                blupTypes.add("upDownBlup");
                blupTypes.add("downRightBlup");
                blupTypes.add("downLeftBlup");
                dY = -1;
                upSide = growLogic(dX,dY,blupTypes);
                dY = 0;

            }
            if(downSide){
                blupTypes.clear();
                blupTypes.add("upDownBlup");
                blupTypes.add("upRightBlup");
                blupTypes.add("upLeftBlup");
                dY = 1;
                downSide = growLogic(dX,dY,blupTypes);
                dY = 0;

            }
            if(leftSide){
                blupTypes.clear();
                blupTypes.add("leftRightBlup");
                blupTypes.add("upRightBlup");
                blupTypes.add("downRightBlup");
                dX = -1;
                leftSide = growLogic(dX,dY,blupTypes);
                dX = 0;
            }
            if(rightSide){
                blupTypes.clear();
                blupTypes.add("leftRightBlup");
                blupTypes.add("upLeftBlup");
                blupTypes.add("downLeftBlup");
                dX = 1;
                rightSide = growLogic(dX,dY,blupTypes);
            }

        }
    }

    private boolean growLogic(int dX, int dY, List<String> blupTypes){
        Cell nextCell = cell.getNeighbor(dX,dY);
        String blupType = blupTypes.get(random.nextInt(blupTypes.size()));
        if(nextCell.getType() == CellType.FLOOR
                && nextCell.getType() != CellType.ITEM
                && !nextCell.isAttackable()){
            new Blup(nextCell, blupType);
            return true;
        }
        return true;
    }

    @Override
    public String getTileName() {
        return name;
    }

    @Override
    public void move(int dx, int dy) {

    }
}

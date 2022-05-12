package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Blup extends Monster {
    private Cell cell;
    private final String name;
    private boolean upSide = false;
    private boolean downSide = false;
    private boolean leftSide = false;
    private boolean rightSide = false;
    private static final Random random = new Random();
    private List<String> blupTypes = new ArrayList<>();

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
        int dX = 0;
        int dY = 0;
        boolean side;
        System.out.println("-------------------------------------");
        if (Math.abs(getX() - player.getX()) < 5
                && Math.abs(getY() - player.getY()) < 5) {
            if(upSide){
                blupTypes.add("upDownBlup");
                blupTypes.add("downRightBlup");
                blupTypes.add("downLeftBlup");
                dY = -1;
                move(dX,dY);
                dY = 0;

            }
            if(downSide){
                blupTypes.clear();
                blupTypes.add("upDownBlup");
                blupTypes.add("upRightBlup");
                blupTypes.add("upLeftBlup");
                dY = 1;
                move(dX,dY);
                dY = 0;

            }
            if(leftSide){
                blupTypes.clear();
                System.out.println("left side");
                blupTypes.add("leftRightBlup");
                blupTypes.add("upRightBlup");
                blupTypes.add("downRightBlup");
                dX = -1;
                move(dX,dY);
                dX = 0;
            }
            if(rightSide){
                blupTypes.clear();
                System.out.println("right side");
                System.out.println(blupTypes);
                blupTypes.add("leftRightBlup");
                blupTypes.add("upLeftBlup");
                blupTypes.add("downLeftBlup");
                dX = 1;
                move(dX,dY);
            }

        }
    }

    @Override
    public String getTileName() {
        return name;
    }

    @Override
    public void move(int dX, int dY) {
        Cell nextCell = cell.getNeighbor(dX,dY);
        String blupType = blupTypes.get(random.nextInt(blupTypes.size()));
        if(nextCell.getType() == CellType.FLOOR
                && nextCell.getType() != CellType.ITEM
                && !nextCell.isAttackable()){
            System.out.println("try to grow " +blupType);
            new Blup(nextCell, blupType);
        }
    }

    @Override
    public void moveMonsters(Player player) {
        grow(player);
    }
}

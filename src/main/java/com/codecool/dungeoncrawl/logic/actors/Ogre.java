package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Ogre extends Monster {
    private Cell cell;

    public Ogre(Cell cell) {

        super(cell, 20,4);
        this.cell = cell;
    }

    @Override
    public String getTileName() {
        return "ogre";
    }

    public void chasePlayer(Actor player){
        if (Math.abs(getX()-player.getX()) < 5
                && Math.abs(getY()-player.getY()) < 5){
            int diffX = player.getX()-getX();
            int dX = 0;
            if (diffX > 0){
                dX = 1;
            }
            else if (diffX < 0){
                dX = -1;
            }
            int diffY = player.getY() - getY();
            int dY = 0;
            if (diffY > 0){
                dY = 1;
            }
            else if (diffY < 0){
                dY = -1;
            }
            move(dX, dY);
        }
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getActor() instanceof Player){
            combat(nextCell.getActor());
            return;
        }
        if (nextCell.getActor() != null){
            return;
        }
        if (nextCell.getType() == CellType.FLOOR) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
            super.cell = nextCell;
        }
    }

    @Override
    public void moveMonsters(Player player) {
        chasePlayer(player);
    }
}

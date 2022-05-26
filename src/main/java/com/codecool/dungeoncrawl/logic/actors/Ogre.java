package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Ogre extends Monster {
    private Cell cell;
    private Actor player;
    private ActorType type;

    public Ogre(Cell cell) {

        super(cell, 20, 4);
        this.cell = cell;
        this.type=ActorType.OGRE;
    }

    @Override
    public String getTileName() {
        return "ogre";
    }

    public void chasePlayer(Actor player, boolean isWalkable) {
        this.player = player;
        // Chase Player if it's closer than 8 cell
        if (Math.abs(getX() - player.getX()) < 8
                && Math.abs(getY() - player.getY()) < 8) {
            int diffX = player.getX() - getX();
            int diffY = player.getY() - getY();
            int dX = 0;
            int dY = 0;
            if (isWalkable) {
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    dX = getDirectionChanger(diffX);
                } else {
                    dY = getDirectionChanger(diffY);
                }
            } else {
                if (Math.abs(diffX) <= Math.abs(diffY)) {
                    dX = getDirectionChanger(diffX);
                } else {
                    dY = getDirectionChanger(diffY);
                }
            }
            move(dX, dY);
        }
    }

    private int getDirectionChanger(int diff) {
        int direction = 0;
        if (diff > 0) {
            direction = 1;
        } else if (diff < 0) {
            direction = -1;
        }
        return direction;
    }


    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        System.out.println(nextCell.getType());
        if (nextCell.isAttackable()) {
            combat(nextCell.getActor());
            return;
        }
        if (nextCell.getActor() != null) {
            return;
        }
        if (nextCell.getType() == CellType.WALL) {
            chasePlayer(player, false);
        }
        if (nextCell.getType() == CellType.FLOOR || nextCell.getType() == CellType.ITEM) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
            super.cell = nextCell;
        }
    }

    @Override
    public char getSymbol() {
        return this.type.getSymbol();
    }

    @Override
    public void moveMonsters(Player player) {
        chasePlayer(player, true);
    }
}

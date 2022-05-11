package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ogre extends Actor{
    public Ogre(Cell cell) {
        super(cell, 20,2);
    }

    @Override
    public String getTileName() {
        return "ogre";
    }

    public void chasePlayer(Player player){
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
            moveMonster(dX, dY);
        }
    }

}

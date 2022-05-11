package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Blup extends Actor{
    private Cell cell;
    private String name;

    public Blup (Cell cell, String name){
        super(cell, 5, 1);
        this.cell = cell;
        this.name = name;
    }

    public void grow(Player player){
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
            System.out.println(dX +""+dY);
            moveMonster(dX, dY);
        }
    }

    @Override
    public String getTileName() {
        return name;
    }

    @Override
    public void move(int dx, int dy) {

    }
}

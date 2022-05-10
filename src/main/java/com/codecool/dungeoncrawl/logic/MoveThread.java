package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;

import java.util.List;

public class MoveThread extends Thread{
    private boolean isRunning = true;

    public void run(List<Skeleton> skeletons) throws InterruptedException {
        while (isRunning){
            for (Skeleton skeleton :
                    skeletons) {
                skeleton.moveSkeleton();
            }
            Thread.sleep(800);
        }
    }

    public void turnOffRun(){
        this.isRunning = false;
    }
}

package com.codecool.dungeoncrawl.logic;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public enum Sound {
    STEP("/sound/step.wav"),
    APPLE("/sound/apple.wav"),
    SWORD("/sound/getSword.wav"),
    SWORD_SLASH("/sound/swordSlash.wav"),
    SLASH_OGRE("/sound/slashOgre.wav"),
    SLASH_BLOB("/sound/slashBlob.wav"),
    KEY("/sound/key.wav"),
    UNLOCK("/sound/unlock.wav"),
    MAP1("/sound/Intro.wav"),
    DEATH("/sound/LegoYodaDeath.wav"),
    BUMP("/sound/Ouch.wav"),
    GAME_OVER("/sound/gameOver.wav"),
    MAP2("/sound/Underground.wav");


    public static enum Volume {
        MUTE, LOW, MEDIUM, HIGH
    }

    public static Volume volume = Volume.HIGH;


    private Clip clip;


    Sound(String soundFileName) {
        try {
            URL url = this.getClass().getResource(soundFileName);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play(Boolean loop) {
        if (volume != Volume.MUTE) {
            if (clip.isRunning())
                clip.stop();
            clip.setFramePosition(0);
            clip.start();
            if(loop)
                clip.loop(Clip.LOOP_CONTINUOUSLY);
        }

    }

    public void stop()
    {
        clip.stop();
        clip.setFramePosition(0);
    }

    public void mute()
    {
        volume = Volume.MUTE;
    }
}



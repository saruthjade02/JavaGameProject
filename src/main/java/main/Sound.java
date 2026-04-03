package main;

import javax.sound.sampled.*;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];
    
    
    FloatControl volumeControl;

    public Sound() {
        soundURL[0] = getClass().getClassLoader().getResource("sound/mainTheme.wav");
        soundURL[1] = getClass().getClassLoader().getResource("sound/pickup.wav");
        soundURL[2] = getClass().getClassLoader().getResource("sound/door.wav");
        soundURL[3] = getClass().getClassLoader().getResource("sound/powerup.wav");
        soundURL[4] = getClass().getClassLoader().getResource("sound/chestopen.wav");
        soundURL[5] = getClass().getClassLoader().getResource("sound/ending.wav");
        soundURL[6] = getClass().getClassLoader().getResource("sound/handcuffsf.wav");
        soundURL[7] = getClass().getClassLoader().getResource("sound/bombsf.wav");
        soundURL[8] = getClass().getClassLoader().getResource("sound/getcoin.wav");
        soundURL[9] = getClass().getClassLoader().getResource("sound/ouch.wav");
    }

    
    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

            
            //volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void playLoop() {
        if (clip != null && clip.isOpen()) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
    }

    public void stop() {
        if(clip!=null)
        clip.stop();
    }

   
    public void setVolume(float volume) {
        if (clip != null) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float range = gainControl.getMaximum() - gainControl.getMinimum();
            float gain = (range * volume) + gainControl.getMinimum()+40;
            gainControl.setValue(Math.min(gain, gainControl.getMaximum()));
        }
    }
    public void setAllVolumes(float volume) {
        for (int i = 0; i < soundURL.length; i++) {
            if (soundURL[i] != null) {
                setFile(i);  
                setVolume(volume); 
            }
        }
    }
    public void stopAllSounds() {
        for (int i = 0; i < soundURL.length; i++) {
            if (soundURL[i] != null && clip != null && clip.isRunning()) {
                clip.stop();
            }
        }    
    }
}
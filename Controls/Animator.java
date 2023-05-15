package Controls;

import java.awt.image.BufferedImage;

public class Animator {
    private final BufferedImage[] images;
    private int index = -1;
    private int timer = 4;

    public Animator(BufferedImage... images){
        this.images = images;
    }

    public Animator getCopy(){

        return new Animator(images);
    }

    public BufferedImage getImage(){
        timer++;
        if(timer == 5){
            timer = 0;
            index++;
        }
        return images[index];
    }

    public boolean isEnded(){
        return index == images.length - 1;
    }
}

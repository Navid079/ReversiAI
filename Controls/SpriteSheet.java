package Controls;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    public BufferedImage image;

    public SpriteSheet(BufferedImage image){
        this.image = image;
    }

    public BufferedImage GrabImage(int col, int row, int width, int height){
        return image.getSubimage((col * 64) - 64, (row * 64) - 64, width, height);
    }
}
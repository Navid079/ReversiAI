package Controls;

import java.awt.image.BufferedImage;

public class Sprite {
    private final BufferedImage board;
    private final BufferedImage black;
    private final BufferedImage white;
    private final BufferedImage select;
    private final BufferedImage icon;
    private final Animator bToW;
    private final Animator wTob;
    private final Animator nToW;
    private final Animator nToB;
    private final BufferedImage wSimple;
    private final BufferedImage bSimple;


    public Sprite(SpriteSheet sheet){
        board = sheet.GrabImage(1, 1, 64, 64);
        select = sheet.GrabImage(1, 2, 64, 64);
        black = sheet.GrabImage(1, 3, 64, 64);
        white = sheet.GrabImage(1, 4, 64, 64);
        icon = sheet.GrabImage(1, 5, 64, 64);
        bToW = new Animator(sheet.GrabImage(2, 1, 64, 64),sheet.GrabImage(2, 2, 64, 64),sheet.GrabImage(2, 3, 64, 64),sheet.GrabImage(2, 4, 64, 64),sheet.GrabImage(2, 5, 64, 64),
                            sheet.GrabImage(3, 1, 64, 64),sheet.GrabImage(3, 2, 64, 64),sheet.GrabImage(3, 3, 64, 64),sheet.GrabImage(3, 4, 64, 64),sheet.GrabImage(3, 5, 64, 64));
        wTob = new Animator(sheet.GrabImage(4, 1, 64, 64),sheet.GrabImage(4, 2, 64, 64),sheet.GrabImage(4, 3, 64, 64),sheet.GrabImage(4, 4, 64, 64),sheet.GrabImage(4, 5, 64, 64),
                            sheet.GrabImage(5, 1, 64, 64),sheet.GrabImage(5, 2, 64, 64),sheet.GrabImage(5, 3, 64, 64),sheet.GrabImage(5, 4, 64, 64),sheet.GrabImage(5, 5, 64, 64));
        nToB = new Animator(sheet.GrabImage(6, 1, 64, 64),sheet.GrabImage(6, 2, 64, 64),sheet.GrabImage(6, 3, 64, 64),sheet.GrabImage(6, 4, 64, 64),sheet.GrabImage(6, 5, 64, 64),
                            sheet.GrabImage(7, 1, 64, 64),sheet.GrabImage(7, 2, 64, 64),sheet.GrabImage(7, 3, 64, 64),sheet.GrabImage(7, 4, 64, 64),sheet.GrabImage(7, 5, 64, 64));
        nToW = new Animator(sheet.GrabImage(8, 1, 64, 64),sheet.GrabImage(8, 2, 64, 64),sheet.GrabImage(8, 3, 64, 64),sheet.GrabImage(8, 4, 64, 64),sheet.GrabImage(8, 5, 64, 64),
                            sheet.GrabImage(9, 1, 64, 64),sheet.GrabImage(9, 2, 64, 64),sheet.GrabImage(9, 3, 64, 64),sheet.GrabImage(9, 4, 64, 64),sheet.GrabImage(9, 5, 64, 64));
        wSimple = sheet.GrabImage(10, 1, 64, 64);
        bSimple = sheet.GrabImage(10, 2, 64, 64);
    }

    public BufferedImage getBoard() {
        return board;
    }

    public BufferedImage getBlack() {
        return black;
    }

    public BufferedImage getWhite() {
        return white;
    }

    public BufferedImage getSelect() {
        return select;
    }

    public BufferedImage getIcon() {
        return icon;
    }

    public Animator getBToW() {
        return bToW.getCopy();
    }

    public Animator getWToB() {
        return wTob.getCopy();
    }

    public Animator getNToW() {
        return nToW.getCopy();
    }

    public Animator getNToB() {
        return nToB.getCopy();
    }

    public BufferedImage getWSimple() {
        return wSimple;
    }

    public BufferedImage getBSimple() {
        return bSimple;
    }
}

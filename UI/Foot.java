package UI;

import Controls.CONST;

import java.awt.*;

public class Foot {
    private final Game game;
    public String message = "";

    public Foot(Game game){
        this.game = game;
    }

    public void render(Graphics g){
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 512, 512, 148);
        g.setColor(Color.GRAY);
        g.fillRoundRect(5, 515, 200, 70, 20, 20);
        g.fillRoundRect(5, 588, 200, 70, 20, 20);
        g.drawImage(game.sprites.getWSimple(), 7, 518, null);
        g.drawImage(game.sprites.getBSimple(), 7, 591, null);
        g.setColor(Color.BLACK);
        Font f = new Font("sans-serif", Font.BOLD, 40);
        g.setFont(f);
        g.drawString(String.valueOf(game.core.wCount),81, 565);
        g.drawString(String.valueOf(game.core.bCount),81, 638);
        g.setColor(Color.GRAY);
        g.fillRoundRect(437, 514, 70, 144, 20, 20);
        if(game.core.getTurn() == CONST.BLACK)
            g.drawImage(game.sprites.getBSimple(), 440, 554, null);
        else
            g.drawImage(game.sprites.getWSimple(), 440, 554, null);
        g.setColor(Color.BLACK);
        drawCenteredString(message, 210, 515, 222, 130, g);
    }

    public static void drawCenteredString(String s,int x, int y, int w, int h, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        x += (w - fm.stringWidth(s)) / 2;
        y += (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(s, x, y);
    }
}

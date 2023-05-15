package UI;

import Controls.Animator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class BoardView {
    private final Game game;
    public Animator[][] animators = new Animator[8][8];
    public BufferedImage[][] images = new BufferedImage[8][8];

    public BoardView(Game game){
        this.game = game;
        game.core.board.view = this;

        for(Animator[] row: animators){
            Arrays.fill(row, null);
        }
        for(BufferedImage[] row: images){
            Arrays.fill(row, game.sprites.getBoard());
        }
        images[3][4] = images[4][3] = game.sprites.getWhite();
        images[3][3] = images[4][4] = game.sprites.getBlack();
        game.core.board.updateView();
    }

    public void render(Graphics g){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(animators[i][j] == null || animators[i][j].isEnded()) {
                    animators[i][j] = null;
                    g.drawImage(images[i][j], j * 64, i * 64, null);
                } else{
                    g.drawImage(animators[i][j].getImage(), j * 64, i * 64, null);
                }
            }
        }
    }

    public int animatorCount(){
        int c = 0;
        for(Animator[] row : animators){
            for(Animator item : row){
                if(item != null)
                    c++;
            }
        }
        return c;
    }

    public void setImage(int i, int j, String type) {
        switch(type){
            case "black":
                animators[i][j] = game.sprites.getWToB();
                images[i][j] = game.sprites.getBlack();
                break;
            case "white":
                animators[i][j] = game.sprites.getBToW();
                images[i][j] = game.sprites.getWhite();
                break;
            case "black insert":
                animators[i][j] = game.sprites.getNToB();
                images[i][j] = game.sprites.getBlack();
                break;
            case "white insert":
                animators[i][j] = game.sprites.getNToW();
                images[i][j] = game.sprites.getWhite();
                break;
            case "select":
                images[i][j] = game.sprites.getSelect();
                break;
            case "null":
                images[i][j] = game.sprites.getBoard();
                break;
        }
    }
}

package Controls;

import UI.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private final Game game;

    public KeyInput(Game game){
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        game.keyPressed(e);
    }
}

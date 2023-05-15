package Controls;

import UI.Game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
    Game game;

    public MouseInput(Game game){
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        game.mouseClicked(e);
    }
}

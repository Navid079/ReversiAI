package UI;

import Controls.*;
import Controls.Choice;
import GameObjects.Core;
import GameObjects.MiniBoard;
import GameObjects.Move;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Game extends Canvas implements Runnable {
    private static final String TITLE = "Reversi";
    private boolean running = false;
    private Thread thread;
    Core core;
    public Sprite sprites;
    private BoardView boardView;
    public Foot foot;
    private boolean finished = false;
    private final JFrame frame;
    private boolean flag = false;

    public Game(JFrame frame){
        this.frame = frame;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame(TITLE);
        Game game = new Game(frame);
        game.setPreferredSize(new Dimension(512, 660));
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        game.start();
    }

    private synchronized void start() {
        if(running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop(){
        if(!running)
            return;

        running = false;
        try {
            thread.join();
        } catch (InterruptedException ignored){}

        System.exit(1);
    }

    public void run(){
        addMouseListener(new MouseInput(this));
        addKeyListener(new KeyInput(this));
        requestFocus();
        BufferedImage t = null;
        try {
            t = ImageIO.read(getClass().getResourceAsStream("spriteSheet.png"));
        } catch (IOException ignored) {}
        SpriteSheet sheet = new SpriteSheet(t);
        sprites = new Sprite(sheet);
        frame.setIconImage(sprites.getIcon());
        core = new Core(CONST.PLAY_WHITE, this);
        boardView = new BoardView(this);
        foot = new Foot(this);

        Timer renderTimer = new Timer(8, e -> render());
        renderTimer.start();
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();

        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        /////////////Render Game here/////////////

        boardView.render(g);
        foot.render(g);
        if(finished){
            g.setColor(Color.BLACK);
            Font f = new Font("sans-serif", Font.BOLD, 40);
            g.setFont(f);
            Foot.drawCenteredString("Click to Continue", 0, 0, 512, 512, g);
        }

        if(core.isAI() && boardView.animatorCount() == 0 && !finished){
            if(!flag){
                Choice c = core.minimax(core.getTurn(), 0, new MiniBoard(core.board), Integer.MIN_VALUE, Integer.MAX_VALUE, true, false);
                if(c.move == null){
                    try {
                        core.nextTurn();
                        return;
                    } catch (Exception e2){
                        foot.message = "Finished!";
                        finished = true;
                    }
                }
                assert c.move != null;
                int x = c.move.x;
                int y = c.move.y;

                playTurn(x, y);
            } else {
                flag = false;
            }
        }

        //////////////////////////////////////////
        g.dispose();
        bs.show();
    }

    public void mouseClicked(MouseEvent e) {
        if(finished){
            initialNewGame();
            return;
        }
        if(core.isAI()){
            return;
        }
        int x = e.getX() / 64;
        int y = e.getY() /64;
        if(y > 7){
            return;
        }
        playTurn(x, y);
    }

    private void playTurn(int x, int y){
        boolean flag = false;
        for(Move move: core.possibleMoves){
            if(move.x == x && move.y == y){
                core.insert(move);
                this.flag = true;
                flag = true;
            }
        }
        if(flag){
            try {
                core.nextTurn();
            } catch (Exception e2){
                foot.message = "Finished!";
                finished = true;
            }
        }
    }

    private void initialNewGame() {
        finished = false;
        core = new Core(core.type, this);
        boardView = new BoardView(this);
        foot.message = "";
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            stop();
        }
    }
}

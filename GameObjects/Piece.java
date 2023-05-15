package GameObjects;

import Controls.CONST;

public class Piece {
    private int color;
    private final int x;
    private final int y;

    public Piece(int x, int y){
        this.x = x;
        this.y = y;
        color = CONST.INVISIBLE;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

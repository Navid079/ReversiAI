package GameObjects;

public class Move {
    public final int x;
    public final int y;
    public final int type;
    public boolean flag = false;

    public Move(int x, int y, int type){
        this.x = x;
        this.y = y;
        this.type = type;
    }
}

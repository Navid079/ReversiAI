package Controls;

import GameObjects.Move;

public class Choice {
    public final Move move;
    public int score;

    public Choice(Move move, int score){
        this.move = move;
        this.score = score;
    }
}

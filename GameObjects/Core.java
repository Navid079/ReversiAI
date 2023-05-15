package GameObjects;

import Controls.CONST;
import Controls.Choice;
import UI.Game;

import java.util.ArrayList;

public class Core {
    public final Board board = new Board();
    private int turn = CONST.WHITE;
    public final int type;
    public ArrayList<Move> possibleMoves;
    private boolean flag = false;
    public int wCount = 2;
    public int bCount = 2;
    private final Game game;
    private static final int[][] scores =
                    {{60, -48, 0, 0, 0, 0, -48,60 },
                    {-48, -60, 0, 0, 0, 0, -60,-48},
                    {0  , 0  , 0, 0, 0, 0, 0  ,0  },
                    {0  , 0  , 0, 0, 0, 0, 0  ,0  },
                    {0  , 0  , 0, 0, 0, 0, 0  ,0  },
                    {0  , 0  , 0, 0, 0, 0, 0  ,0  },
                    {-48, -60, 0, 0, 0, 0, -60,-48},
                    {60 , -48, 0, 0, 0, 0, -48,40}};
    /*{{45, -30, 20, -5, -5, 20, -30, 45},
    {20 , 0 , 0 , 0 , 0 , 0 , 0 , 20},
    {-5 , 0 , 0 , 0  , 0  , 0 , 0 , -5},
    {-5 , 0 , 0 , 0  , 0  , 0 , 0 , -5},
    {20 , 0 , 0 , 0 , 0 , 0 , 0 , 20},
    {-30, -40, 0, 0, 0, 0, -40, -30},
    {45, -30, 20, -5, -5, 20, -30, 45}};*/

    public Core(int type, Game game){
        this.game = game;
        this.type = type;
        board.core = this;
        possibleMoves = board.getPossibleMoves(turn);
    }

    public void insert(Move move){
        board.insert(move.x, move.y, turn, move.type);
    }

    public Choice minimax(int turn, int depth, MiniBoard mb, int alpha, int beta, boolean isMax, boolean skipped){
        //Will be added
        int myAlpha = Integer.MIN_VALUE;
        int myBeta = Integer.MAX_VALUE;
        int op = turn == CONST.BLACK ? CONST.WHITE : CONST.BLACK;
        ArrayList<Move> pms = mb.getPossibleMoves(turn);

        if(pms.isEmpty()){
            if(!skipped && depth < 7) {
                Choice temp = minimax(op, depth + 1, mb, myAlpha, myBeta, !isMax, true);
                temp.score -= isMax ? 10 : -10;
                return temp;
            }
            else if(skipped){
                int wins = mb.getCount(turn) - mb.getCount(op);
                int score = 0;
                if(wins > 0){
                    score = isMax ? 1000000 : -1000000;
                } else if(wins < 0) {
                    score = isMax ? -1000000 : 1000000;
                }
                return new Choice(null, score);
            }
        }

        if(depth == 7) {
            int score = pms.size() * 2 + (mb.getMobility(turn) - mb.getMobility(op));
            if(!isMax)
                score *= -1;
            return new Choice(null, score);
        }

        ArrayList<MiniBoard> newBoards = new ArrayList<>();
        int[] scores = new int[pms.size()];
        Move[] moves = new Move[pms.size()];
        int i = 0;
        for(Move pm : pms){
            if(pm.flag)
                continue;
            MiniBoard t = new MiniBoard(mb);
            t.insert(pm.x, pm.y, turn, pm.type);
            for(Move pm2 : pms){
                if(pm.x == pm2.x && pm.y == pm2.y){
                    t.insert(pm2.x, pm2.y, turn, pm2.type);
                    pm2.flag = true;
                }
            }
            newBoards.add(t);
            scores[i] = isMax ? Core.scores[pm.y][pm.x] : -Core.scores[pm.y][pm.x];
            moves[i++] = pm;
        }
        i = 0;
        for(MiniBoard item : newBoards){
            Choice t = minimax(op , depth + 1, item, myAlpha, myBeta, !isMax, false);
            scores[i] += t.score;
            if(isMax){
                if(scores[i] > myAlpha){
                    myAlpha = scores[i];
                }
                if(scores[i] > beta)
                    return new Choice(moves[i], scores[i]);
            } else{
                if(scores[i] < myBeta){
                    myBeta = scores[i];
                }
                if(scores[i] < alpha){
                    return new Choice(moves[i], scores[i]);
                }
            }
            i++;
        }
        int mx = 0;
        int mn = 0;
        for (i = 1; i < newBoards.size(); i++){
            if(scores[i] > scores[mx])
                mx = i;
            if (scores[i] < scores[mn])
                mn = i;
        }
        Choice fin;
        if(isMax){
            Move mv = moves[mx];
            int sc = scores[mx];
            fin = new Choice(mv, sc);
        }
        else {
            Move mv = moves[mn];
            int sc = scores[mn];
            fin = new Choice(mv, sc);
        }
        return fin;
    }

    public void nextTurn() throws Exception {
        turn = turn == CONST.WHITE ? CONST.BLACK : CONST.WHITE;
        possibleMoves = board.getPossibleMoves(turn);
        if(possibleMoves.isEmpty() && !flag) {
            String t = turn == CONST.BLACK ? "Black" : "White";
            t += ":No turn";
            game.foot.message = t;
            flag = true;
            nextTurn();
        } else if(possibleMoves.isEmpty()){
            throw new Exception("Finished!");
        } else if(flag){
            flag = false;
        } else{
            game.foot.message = "";
        }
        board.updateView();
    }

    public int getTurn() {
        return turn;
    }

    public boolean isAI(){
        return type == CONST.PLAY_WHITE && getTurn() == CONST.BLACK || type == CONST.PLAY_BLACK && getTurn() == CONST.WHITE || type == CONST.PLAY_NONE;
    }
}

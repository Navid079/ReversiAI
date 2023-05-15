package GameObjects;

import Controls.CONST;
import UI.BoardView;

import java.util.ArrayList;

public class Board {

    public final Piece[][] board = new Piece[8][8];
    public BoardView view;
    public Core core;

    public Board(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                board[i][j] = new Piece(j, i);
            }
        }
        board[3][4].setColor(CONST.WHITE);
        board[3][3].setColor(CONST.BLACK);
        board[4][3].setColor(CONST.WHITE);
        board[4][4].setColor(CONST.BLACK);
    }

    private void revert(Piece p){
        if(p.getColor() == CONST.BLACK){
            core.bCount--;
            core.wCount++;
            p.setColor(CONST.WHITE);
            view.setImage(p.getY(), p.getX(), "white");
        } else{
            core.bCount++;
            core.wCount--;
            p.setColor(CONST.BLACK);
            view.setImage(p.getY(), p.getX(), "black");
        }
    }

    public void insert(int x, int y, int color, int type){
        if(board[y][x].getColor() != color) {
            board[y][x].setColor(color);
            if(color == CONST.BLACK){
                view.setImage(y, x, "black insert");
                core.bCount++;
            }
            else{
                view.setImage(y, x, "white insert");
                core.wCount++;
            }
        }
        int i;
        switch(type){
            case CONST.N:
                i = y + 1;
                while(board[i][x].getColor() != color){
                    revert(board[i][x]);
                    i++;
                }
                break;
            case CONST.E:
                i = x - 1;
                while(board[y][i].getColor() != color){
                    revert(board[y][i]);
                    i--;
                }
                break;
            case CONST.S:
                i = y - 1;
                while(board[i][x].getColor() != color){
                    revert(board[i][x]);
                    i--;
                }
                break;
            case CONST.W:
                i = x + 1;
                while(board[y][i].getColor() != color){
                    revert(board[y][i]);
                    i++;
                }
                break;
            case CONST.NE:
                i = 1;
                while(board[y + i][x - i].getColor() != color){
                    revert(board[y + i][x - i]);
                    i++;
                }
                break;
            case CONST.SE:
                i = 1;
                while(board[y - i][x - i].getColor() != color){
                    revert(board[y - i][x - i]);
                    i++;
                }
                break;
            case CONST.SW:
                i = 1;
                while(board[y - i][x + i].getColor() != color){
                    revert(board[y - i][x + i]);
                    i++;
                }
                break;
            case CONST.NW:
                i = 1;
                while(board[y + i][x + i].getColor() != color){
                    revert(board[y + i][x + i]);
                    i++;
                }
                break;
        }
    }

    public ArrayList<Move> getPossibleMoves(int color){
        ArrayList<Move> moves = new ArrayList<>();
        int k, x, y;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Piece p = board[i][j];
                if(p.getColor() == color){
                    //Check N move:
                    k = i - 1;
                    while(k >= 0 && board[k][j].getColor() != color){
                        if(board[k][j].getColor() == CONST.INVISIBLE){
                            break;
                        }
                        k--;
                    }
                    if(k >= 0 && board[k][j].getColor() == CONST.INVISIBLE &&  k != i - 1){
                        moves.add(new Move(j, k, CONST.N));
                    }
                    //Check E move:
                    k = j + 1;
                    while(k < 8 && board[i][k].getColor() != color){
                        if(board[i][k].getColor() == CONST.INVISIBLE){
                            break;
                        }
                        k++;
                    }
                    if(k < 8 && board[i][k].getColor() == CONST.INVISIBLE && k != j + 1){
                        moves.add(new Move(k, i, CONST.E));
                    }
                    //Check S move:
                    k = i + 1;
                    while(k < 8 && board[k][j].getColor() != color){
                        if(board[k][j].getColor() == CONST.INVISIBLE){
                            break;
                        }
                        k++;
                    }
                    if(k < 8 && board[k][j].getColor() == CONST.INVISIBLE && k != i + 1){
                        moves.add(new Move(j, k, CONST.S));
                    }
                    //Check W move:
                    k = j - 1;
                    while(k >= 0 && board[i][k].getColor() != color){
                        if(board[i][k].getColor() == CONST.INVISIBLE){
                            break;
                        }
                        k--;
                    }
                    if(k >= 0 && board[i][k].getColor() == CONST.INVISIBLE && k != j - 1){
                        moves.add(new Move(k, i, CONST.W));
                    }
                    //Check NE move
                    x = j + 1;
                    y = i - 1;
                    while(x < 8 && y >= 0 && board[y][x].getColor() != color){
                        if(board[y][x].getColor() == CONST.INVISIBLE){
                            break;
                        }
                        x++;
                        y--;
                    }
                    if(x < 8 && y >= 0 && board[y][x].getColor() == CONST.INVISIBLE && x != j + 1){
                        moves.add(new Move(x, y, CONST.NE));
                    }
                    //Check SE move
                    x = j + 1;
                    y = i + 1;
                    while(x < 8 && y < 8 && board[y][x].getColor() != color){
                        if(board[y][x].getColor() == CONST.INVISIBLE){
                            break;
                        }
                        x++;
                        y++;
                    }
                    if(x < 8 && y < 8 && board[y][x].getColor() == CONST.INVISIBLE && x != j + 1){
                        moves.add(new Move(x, y, CONST.SE));
                    }
                    //Check SW move
                    x = j - 1;
                    y = i + 1;
                    while(x >= 0 && y < 8 && board[y][x].getColor() != color){
                        if(board[y][x].getColor() == CONST.INVISIBLE){
                            break;
                        }
                        x--;
                        y++;
                    }
                    if(x >= 0 && y < 8 && board[y][x].getColor() == CONST.INVISIBLE && x != j - 1){
                        moves.add(new Move(x, y, CONST.SW));
                    }
                    //Check NW move
                    x = j - 1;
                    y = i - 1;
                    while(x >= 0 && y >= 0 && board[y][x].getColor() != color){
                        if(board[y][x].getColor() == CONST.INVISIBLE){
                            break;
                        }
                        x--;
                        y--;
                    }
                    if(x >= 0 && y >= 0 && board[y][x].getColor() == CONST.INVISIBLE && x != j - 1){
                        moves.add(new Move(x, y, CONST.NW));
                    }
                }
            }
        }
        return moves;
    }

    public void updateView(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++) {
                if (board[i][j].getColor() == CONST.INVISIBLE) {
                    view.setImage(i, j, "null");
                }
            }
        }
        for(Move move : core.possibleMoves){
            view.setImage(move.y, move.x, "select");
        }
    }
}

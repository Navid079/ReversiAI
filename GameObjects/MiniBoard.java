package GameObjects;

import Controls.CONST;

import java.util.ArrayList;

public class MiniBoard {
    public final int[][] board;

    public MiniBoard(Board parent){
        this.board = new int[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                board[i][j] = parent.board[i][j].getColor();
            }
        }
    }

    public MiniBoard(MiniBoard parent){
        this.board = new int[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                board[i][j] = parent.board[i][j];
            }
        }
    }

    private void revert(int i, int j){
        board[i][j] = board[i][j] == CONST.BLACK ? CONST.BLACK : CONST.WHITE;
    }

    public void insert(int x, int y, int color, int type){
        if(board[y][x] != color) {
            board[y][x] = color;
        }
        int i;
        switch(type){
            case CONST.N:
                i = y + 1;
                while(board[i][x] != color){
                    revert(i, x);
                    i++;
                }
                break;
            case CONST.E:
                i = x - 1;
                while(board[y][i] != color){
                    revert(y, i);
                    i--;
                }
                break;
            case CONST.S:
                i = y - 1;
                while(board[i][x] != color){
                    revert(i, x);
                    i--;
                }
                break;
            case CONST.W:
                i = x + 1;
                while(board[y][i] != color){
                    revert(y, i);
                    i++;
                }
                break;
            case CONST.NE:
                i = 1;
                while(board[y + i][x - i] != color){
                    revert(y + i, x - i);
                    i++;
                }
                break;
            case CONST.SE:
                i = 1;
                while(board[y - i][x - i] != color){
                    revert(y - i, x - i);
                    i++;
                }
                break;
            case CONST.SW:
                i = 1;
                while(board[y - i][x + i] != color){
                    revert(y - i, x + i);
                    i++;
                }
                break;
            case CONST.NW:
                i = 1;
                while(board[y + i][x + i] != color){
                    revert(y + i, x + i);
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
                int p = board[i][j];
                if(p == color){
                    //Check N move:
                    k = i - 1;
                    while(k >= 0 && board[k][j] != color){
                        if(board[k][j] == CONST.INVISIBLE){
                            break;
                        }
                        k--;
                    }
                    if(k >= 0 && board[k][j] == CONST.INVISIBLE &&  k != i - 1){
                        moves.add(new Move(j, k, CONST.N));
                    }
                    //Check E move:
                    k = j + 1;
                    while(k < 8 && board[i][k] != color){
                        if(board[i][k] == CONST.INVISIBLE){
                            break;
                        }
                        k++;
                    }
                    if(k < 8 && board[i][k] == CONST.INVISIBLE && k != j + 1){
                        moves.add(new Move(k, i, CONST.E));
                    }
                    //Check S move:
                    k = i + 1;
                    while(k < 8 && board[k][j] != color){
                        if(board[k][j] == CONST.INVISIBLE){
                            break;
                        }
                        k++;
                    }
                    if(k < 8 && board[k][j] == CONST.INVISIBLE && k != i + 1){
                        moves.add(new Move(j, k, CONST.S));
                    }
                    //Check W move:
                    k = j - 1;
                    while(k >= 0 && board[i][k] != color){
                        if(board[i][k] == CONST.INVISIBLE){
                            break;
                        }
                        k--;
                    }
                    if(k >= 0 && board[i][k] == CONST.INVISIBLE && k != j - 1){
                        moves.add(new Move(k, i, CONST.W));
                    }
                    //Check NE move
                    x = j + 1;
                    y = i - 1;
                    while(x < 8 && y >= 0 && board[y][x] != color){
                        if(board[y][x] == CONST.INVISIBLE){
                            break;
                        }
                        x++;
                        y--;
                    }
                    if(x < 8 && y >= 0 && board[y][x] == CONST.INVISIBLE && x != j + 1){
                        moves.add(new Move(x, y, CONST.NE));
                    }
                    //Check SE move
                    x = j + 1;
                    y = i + 1;
                    while(x < 8 && y < 8 && board[y][x] != color){
                        if(board[y][x] == CONST.INVISIBLE){
                            break;
                        }
                        x++;
                        y++;
                    }
                    if(x < 8 && y < 8 && board[y][x] == CONST.INVISIBLE && x != j + 1){
                        moves.add(new Move(x, y, CONST.SE));
                    }
                    //Check SW move
                    x = j - 1;
                    y = i + 1;
                    while(x >= 0 && y < 8 && board[y][x] != color){
                        if(board[y][x] == CONST.INVISIBLE){
                            break;
                        }
                        x--;
                        y++;
                    }
                    if(x >= 0 && y < 8 && board[y][x] == CONST.INVISIBLE && x != j - 1){
                        moves.add(new Move(x, y, CONST.SW));
                    }
                    //Check NW move
                    x = j - 1;
                    y = i - 1;
                    while(x >= 0 && y >= 0 && board[y][x] != color){
                        if(board[y][x] == CONST.INVISIBLE){
                            break;
                        }
                        x--;
                        y--;
                    }
                    if(x >= 0 && y >= 0 && board[y][x] == CONST.INVISIBLE && x != j - 1){
                        moves.add(new Move(x, y, CONST.NW));
                    }
                }
            }
        }
        return moves;
    }

    public int getMobility(int color){
        int m = 0;
        int op = color == CONST.BLACK ? CONST.WHITE : CONST.BLACK;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(board[i][j] == CONST.INVISIBLE){
                    if (j != 0 && board[i][j - 1] == op){
                        m++;
                        continue;
                    }
                    if (j != 7 && board[i][j + 1] == op){
                        m++;
                        continue;
                    }
                    if (i != 0 && board[i - 1][j] == op){
                        m++;
                        continue;
                    }
                    if (i != 7 && board[i + 1][j] == op){
                        m++;
                        continue;
                    }
                    if (i != 0 && j != 0 && board[i - 1][j - 1] == op){
                        m++;
                        continue;
                    }
                    if (i != 7 && j != 0 && board[i + 1][j - 1] == op){
                        m++;
                        continue;
                    }
                    if (i != 0 && j != 7 && board[i - 1][j + 1] == op){
                        m++;
                        continue;
                    }
                    if (i != 7 && j != 7 && board[i + 1][j + 1] == op){
                        m++;
                    }
                }
            }
        }
        return m;
    }

    public int getCount(int color){
        int res = 0;
        for(int[] row : board){
            for(int x : row){
                if(x == color)
                    res++;
            }
        }
        return res;
    }
}

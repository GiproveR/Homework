public class ChessBoard {
   
    private int x;
    private int y;
    
    ChessBoard() {//create figure with default coordinates a1
        this.x = 0;
        this.y = 0;
    }
    
    ChessBoard(int X, int Y) throws IllegalArgumentException{//create fugure with values X and Y
        if (X >= 0 && X < 8 && Y >= 0 && Y < 8) {
            this.x = X;
            this.y = Y;
        }
        else {
            throw new IllegalArgumentException("Please, check coordinates of chess board's cage");
        }
    }
    
    public int getX() {//method return x's value
        return this.x;
    }
    
    public int getY() {//method return y's value
        return this.y;
    }
    
    public void setX(int X) throws IllegalArgumentException{//method update x's value and check, that X is between 0 and 7
        if (X >= 0 && X < 8) {
            this.x = X;
        }
        else {
            throw new IllegalArgumentException("Please, check horizontal coordinates of chess board's cage");
        }
    }
    
    public void setY(int Y) throws IllegalArgumentException{//method update y's value and check, that Y is between 0 and 7
        if (Y >= 0 && Y < 8) {
            this.y = Y;
        }
        else {
            throw new IllegalArgumentException("Please, check vertical coordinates of chess board's cage");
        }
    }
    
    @Override
    public String toString() {//method return coordinates from chessboard, from example "a7" or "h3"
        return String.format("%s%d",(char)(this.getX()+97),this.getY()+1);//x's value = 0 corresponds symbol 'a'. In int 'a' = 97, that's why it's used sum of x's value and 97
    }
}

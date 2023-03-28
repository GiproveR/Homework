public class Main {
    public static void main(String[] args) {
        
        ChessBoard Figure1 = new ChessBoard();
        System.out.printf("Create Figure1 with coordinates: %s\n",Figure1.toString());//create Figure1 with coordinates a1
        try {//change coordinates on h5
            Figure1.setX(7);
            Figure1.setY(4);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        System.out.printf("Coordindates of Figure1 changed on: %s\n",Figure1.toString());//check coordinates
        
        ChessBoard Figure2 = new ChessBoard(3,2);
        System.out.printf("Create Figure2 with coordinates: %s\n",Figure2.toString());//create Figure1 with coordinates d3
        try {//try change coordinates to invalid
            Figure2.setX(9);
            Figure2.setY(-1);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e);//recieve message about error
        }
        System.out.printf("Programm tried to change coordinates of Figure2\n");
    }
}

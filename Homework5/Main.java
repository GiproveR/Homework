import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String path = in.nextLine();//path input
        String[] parts = path.trim().split("/");//split string by character /
        Deque<String> FullPath = new ArrayDeque<String>(); //create deque
        for (String str : parts) {//for each in parts of path
            if (!str.equals(".") & !str.equals("..")) {//if STR is not . or .. - str is part of path
                FullPath.addFirst(str);
            }
            else if (str.equals("..")) {
                FullPath.removeFirst();
                if (FullPath.isEmpty()) {//it's mean, that we went in folder, names of which we don't know
                    FullPath.addFirst("..");//that's why we insert ".." in the beginning of path
                }
            }
        }
        while (!FullPath.isEmpty()) {//print path
            String nextPart = FullPath.removeLast();
            System.out.print(nextPart + "/".repeat((2+FullPath.size())%(1+FullPath.size())));//repeat( expression ) - expression return 1 always, but return 0 only when FullPath.size==0, that is it is the last element in path and symbol / is not needed
        }
        System.out.println();
    }
}

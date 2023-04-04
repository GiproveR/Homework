import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader ReadFile = null;
        try {
            if (args.length == 0) {//check there is file's path
                throw new IllegalArgumentException("");
            }
            ReadFile = new BufferedReader(new FileReader(args[0]));//file read initialization
            String str;
            int kol = 0;
            while ((str = ReadFile.readLine()) != null) {//reading a file line by line
                str =str.trim()+ " ";//clear spaces on the sides AND appending " " to the end of the line not to count the single "\n"
                String[] S = str.split("\s+");//split string by random number of spaces
                kol += S.length;//count of words in line
            }
            System.out.print(kol);//print number of all words
        }
        catch (IllegalArgumentException e) {//path of file not set
            System.out.println("Путь файла не задан");
        }
        catch (FileNotFoundException e) {//error of open file
            System.out.println("Ошибка открытия файла");
        }
        finally {
            if (ReadFile != null) {
                ReadFile.close();
            }
        }
    }
}

public class Homework1 {
    public static void main (String[] args) {
        for (int i=1; i<=100;i++) { //цикл затрагивает диапазон [1,100]
            if (i%3==0&i%5==0) { //выполняется, если число кратно 3 и 5
                System.out.println("FizzBuzz");
            }
            else if(i%3==0) { //выполняется, если число кратно 3
                System.out.println("Fizz");
            }
            else if (i%5==0) { //выполняется, если число кратно 5
                System.out.println("Buzz");
            }
            else { //если условия выше не выполнились, выводится само число
                System.out.println(i);
            }
        }
    }
}

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Стартовый класс.
 */
public class Main {

    /**
     * Пример входной строки.
     */
    public static final String INPUT_STRING = "Невежество есть мать промышленности, как и суеверий." +
            " Сила размышления и воображения подвержена ошибкам; но привычка двигать рукой или ногой" +
            " не зависит ни от того, ни от другого. Поэтому мануфактуры лучше всего процветают там, где" +
            " наиболее подавлена духовная жизнь, так что мастерская может рассматриваться как машина," +
            " части которой составляют люди.";

    /**
     * Шаблон поиска символов в строке.
     */
    public static final String TEMPLATE = " ";

    /**
     * Переменная для подсчёта количества искомого символа
     */
    public static int sum;

    /**
     * Счётчик для отслеживания проверки всех символов
     */
    private static final CountDownLatch AllChecked = new CountDownLatch(INPUT_STRING.length());


    /**
     * Точка входа в приложение.
     *
     * @param args аргументы командной строки.
     */
    public static void main(String[] args){
        try {
            sum = 0;
            long current = System.currentTimeMillis();

            ExecutorService executorService = Executors.newFixedThreadPool(50);//создание пула потоков с ограничением в 50
            IntStream.rangeClosed(0, INPUT_STRING.length()).forEach(z -> {//перебор индексов строки от 0 до INPUT_STRING.length()-1
                executorService.submit(() -> {//вызов задачи в поток
                    if (Matcher.match(String.valueOf(INPUT_STRING.charAt(z)), TEMPLATE)) {//проверка символа
                        sum++;
                    }
                    AllChecked.countDown();//снижение счётчика
                });
            });

            while (AllChecked.getCount() != 0) //Проверяем, закончилась ли проверка каждого символа в строке
                Thread.sleep(250);//если нет, ожидаем

            executorService.shutdown();//завершение выполнения потоков

            System.out.println("Count of space: " + sum);//выводим количество найденных символов

            System.out.println("Time: " + (System.currentTimeMillis() - current) / 1000 + " c.");//выводим время работы
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


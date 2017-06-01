package hw5;

/**
 * Created by kirweekend on 5/11/17.
 */
import java.util.function.Predicate;



public class Hw5 {

    public static void main(String[] args) {
        print("Names that start with A", e -> e.startsWith("A"), "Mati", "Kati", "Aadu", "Ara");

        print("Values that are greater than ten", e -> e > 10, 40, 0, 4, -5, -14, 35, 1, 5, 6, 7, 8, 11);

    }

    public static <T> void print(String sentence, Predicate<T> predicate, T... array) {
        System.out.println(sentence + ":");
        for (T t : array) {
            if (predicate.test(t)) {
                System.out.print(t + " ");

            }
        }
        System.out.println();
    }
}
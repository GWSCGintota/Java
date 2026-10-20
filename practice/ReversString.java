import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

public class ReversString {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your input :");
        String input= scanner.nextLine();
        System.out.println("Out put : "+ input);

        StringBuilder stringBuilder =  new StringBuilder(input);
        System.out.println( stringBuilder.reverse() );
        scanner.close();

    }
}

import java.util.Scanner;

public class ProcentCalculator {
    void procent() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter summary: ");
        int summ = scanner.nextInt();

        System.out.println("Enter period: ");
        int period = scanner.nextInt();

        System.out.println("Enter procent: ");
        int procent = (scanner.nextInt()/100);

        for (int i = 0; i<=period; i++) {
            summ = summ + (summ*procent);
            System.out.println(summ);
        }

    }

    procent();
}

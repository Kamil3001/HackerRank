import java.util.*;

public class Solution {

    // matrix solution written into variables with O(log n) complexity
    public static int fibonacci(int n) {

        int x = 0;
        int y = 1;
        int a = 0;
        int b = 1;
        int c = 1;
        int d = 1;

        int x1, y1, a1, b1, c1, d1;

        while (n != 0) {
            if (n % 2 == 0) {
                n = n / 2;
                a1 = a * a + b * c;
                b1 = (a + d) * b;
                c1 = (a + d) * c;
                d1 = b * c + d * d;

                a = a1;
                b = b1;
                c = c1;
                d = d1;
            }
            else {
                n = n - 1;
                x1 = a * x + b * y;
                y1 = c * x + d * y;

                x = x1;
                y = y1;
            }
        }

        return x;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        System.out.println(fibonacci(n));
    }
}


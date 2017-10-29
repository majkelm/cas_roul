package pl.majkel.casino.simulator.roulette.utils;

public class SlowFibonacci extends Fibonacci{
    public static Integer calculate(Integer n) {
        if (n <= 6)
            return n;
        else {
            int temp = n - 7;
            if (temp % 2 == 0) {
                int k = 6 + temp / 2;
                return Fibonacci.calculate(k);
            } else {
                int fibDown = Fibonacci.calculate(6 + temp / 2);
                int fibUp = Fibonacci.calculate(6 + temp / 2 + 1);
                return fibDown + (fibUp - fibDown) / 2;
            }

        }

    }

    public static void main(String[] args) {
        System.out.println();
        for (int i = 1; i < 30; i++) {
            System.out.println(i + "->" + SlowFibonacci.calculate(i));
        }
    }
}
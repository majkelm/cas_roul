package pl.majkel.casino.simulator.roulette.utils;

public class VerySlowFibonacci extends Fibonacci{
    public static Integer calculate(Integer n) {
        if (n <= 8)
            return n;
        else {
            int temp = n - 8;
            if (temp % 3 == 0) {
                int k = 6 + temp / 3;
                return Fibonacci.calculate(k);
            } else {
                int mod = temp % 3;
                int fibDown = Fibonacci.calculate(6 + (temp / 3));
                int fibUp = Fibonacci.calculate(6 + temp / 3 + 1);
                return Double.valueOf(fibDown + mod * ((double)(fibUp - fibDown) / 3)).intValue() ;
            }

        }

    }

    public static void main(String[] args) {
        System.out.println();
        for (int i = 1; i < 30; i++) {
            System.out.println(i + "->" + VerySlowFibonacci.calculate(i));
        }
    }
}
import java.util.Scanner;

public class Main {

    public static int convert(Long val) {
        if (val == null)
            return 0;

        long vall = val;
        if (Integer.MAX_VALUE <= vall)
            return Integer.MAX_VALUE;
        if (Integer.MIN_VALUE >= vall)
            return Integer.MIN_VALUE;
        return (int) vall;
        // write your code here
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String val = scanner.nextLine();
        Long longVal = "null".equals(val) ? null : Long.parseLong(val);
        System.out.println(convert(longVal));
    }
}
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine().toUpperCase();
        double count = 0;
        for (char c : str.toCharArray()) {
            if (c == 'G' || c == 'C')
                count += 1;
        }
        System.out.println(count * 100 / str.length());


        // put your code here
    }
}
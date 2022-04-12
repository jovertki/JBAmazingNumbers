import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        int n = scanner.nextInt();
        if (n > str.length()) {
            System.out.println(str);
            return;
        } else {
            str = str.substring(n) + str.substring(0, n);
            System.out.println(str);
        }
        
    }
}

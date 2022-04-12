import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] out = str.split(scanner.nextLine(), -1);
        System.out.println(out.length - 1);
        // put your code here
    }
}

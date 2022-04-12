import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] str = scanner.nextLine().split("-");
        System.out.println(str[1] + "/" + str[2] + "/" + str[0]);
        // put your code here
    }
}
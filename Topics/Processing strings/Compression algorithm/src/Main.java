import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String out = "";
        char cur = str.charAt(0);
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == cur) {
                count++;
            } else {
                out += cur + Integer.toString(count);
                cur = c;
                count = 1;
            }
        }
        out += cur + Integer.toString(count);
        System.out.println(out);
    }
}

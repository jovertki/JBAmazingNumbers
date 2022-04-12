
class Main {
    public static void main() {
        StringBuilder sb = new StringBuilder("A");
        for (int i = 1; i < 26; i++) {
            sb.append(" ");
            sb.append('A' + i);
        }
        System.out.println(sb);
        // write your code here
    }
}
package numbers;

import java.util.*;

class AmazingNumber {

    public AmazingNumber(long num) {
        this.value = num;

    }

    public long getValue() {
        return this.value;
    }

    public boolean isSquare() {
        if (this.square != null)
            return this.square;
        double sqrt = Math.sqrt(this.value);
        this.square =  ((sqrt - Math.floor(sqrt)) == 0);
        return this.square;
    }

    public boolean isSunny() {
        if (this.sunny != null)
            return this.sunny;
        double sqrt = Math.sqrt(this.value + 1);
        this.sunny =  ((sqrt - Math.floor(sqrt)) == 0);
        return this.sunny;
    }

    public boolean isGapful() {
        if (this.gapful != null) {
            return this.gapful;
        }
        String str = Long.toString(this.value);
        if (str.length() < 3)
            return false;
        long div = Long.parseLong(str.substring(0, 1) + str.charAt(str.length() - 1));
        this.gapful = (this.value % div == 0);
        return this.gapful;
    }

    public boolean isEven() {
        if (this.even != null) {
            return this.even;
        }
        this.even =  (this.value % 2 == 0);
        return this.even;
    }

    public boolean isOdd() {
        if (this.odd != null) {
            return this.odd;
        }
        this.odd = !isEven();
        return this.odd;
    }

    public boolean isBuzz() {
        if (this.buzz != null) {
            return this.buzz;
        }
        this.buzz = (this.value % 10 == 7 || this.value % 7 == 0);
        return this.buzz;
    }

    public boolean isDuck() {
        if (this.duck != null) {
            return this.duck;
        }
        String str = String.valueOf(this.value);
        this.duck = (str.lastIndexOf('0') != -1);
        return this.duck;
    }

    public boolean isPalindrome() {
        if (this.palindromic != null)
            return this.palindromic;
        if (this.value < 10)
            return true;
        String str = String.valueOf(this.value);
        int i = 0;
        int j = str.length() - 1;

        while (str.charAt(i) == str.charAt(j) && i != j && i + 1 != j) {
            i++;
            j--;
        }
        this.palindromic = (str.charAt(i) == str.charAt(j));
        return this.palindromic;
    }

    public boolean isSpy() {
        if (this.spy != null) {
            return this.spy;
        }
        String str = Long.toString(this.value);
        String[] strs = str.split("");
        ArrayList<Long> nums = new ArrayList<>();
        for (String s : strs) {
            nums.add(Long.parseLong(s));
        }
        long sum = 0;
        long product = 1;
        for (Long n : nums) {
            sum += n;
            product *= n;
        }
        this.spy = (sum == product);
        return this.spy;
    }



    private final Long value;
    private Boolean even = null;
    private Boolean odd = null;
    private Boolean buzz = null;
    private Boolean duck = null;
    private Boolean palindromic = null;
    private Boolean gapful = null;
    private Boolean spy = null;
    private Boolean square = null;
    private Boolean sunny = null;
}

public class Main {
    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printIntroduction();

        do {
            System.out.print("Enter a request: ");
            String[] strs = getInput();
            if (Objects.equals(strs[0], "")) {
                printInstruction();
                continue;
            }
            System.out.println();

            AmazingNumber num = new AmazingNumber(Long.parseLong(strs[0]));
            Long count = (strs.length == 1) ? null : Long.parseLong(strs[1]);

            String[] props = (strs.length >= 3) ?
                    Arrays.copyOfRange(strs, 2, strs.length) : null;
            if (errorCheck(num, count, props, strs.length)) {
                if (num.getValue() == 0){
                    System.out.println("Goodbye!");
                    break;
                } else if (strs.length == 1) {
                    printOneNumProps(num);
                } else if (strs.length == 2) {
                    printMultipleNumProps(num, count);
                }  else if (strs.length == 3) {
                    printNumsWithProp(num, count, props[0]);
                } else if (strs.length == 4) {
                    printNumsWithDoubleProps(num, count, props);
                }
            }

        } while (true);
    }

    static boolean errorCheck(AmazingNumber num1, Long num2, String[] props, long length) {
        if (num1.getValue() < 0) {
            System.out.println("The first parameter should be a natural number or zero.");
            System.out.println();
            return false;
        } else if (length == 2 && num2 <= 0) {
            System.out.println("The second parameter should be a natural number.");
            System.out.println();
            return false;
        } else if (length == 4 && isNotProp(props[0]) && isNotProp(props[1])) {
            System.out.println("The properties [" + props[0].toUpperCase() + ", " + props[1].toUpperCase() + "] are wrong.");
            System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SQUARE, SUNNY]");
            System.out.println();
            return false;
        } else if ((length == 3  || length == 4) && isNotProp(props[0])) {
            System.out.println("The property [" + props[0].toUpperCase() + "] is wrong.");
            System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SQUARE, SUNNY]");
            System.out.println();
            return false;
        } else if ((length == 4) && isNotProp(props[1])) {
            System.out.println("The property [" + props[1].toUpperCase() + "] is wrong.");
            System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SQUARE, SUNNY]");
            System.out.println();
            return false;
        } else if (length == 4 && areExclusive(props)) {
            System.out.println("The request contains mutually exclusive properties: [" + props[0].toUpperCase() + ", " + props[1].toUpperCase() + "]");
            System.out.println("There are no numbers with these properties.");
            System.out.println();
            return false;
        }
        return true;
    }

    static boolean areExclusive(String[] props) {
        String prop1 = props[0].toUpperCase();
        String prop2 = props[1].toUpperCase();
        return (prop1.equals("EVEN") && prop2.equals("ODD")) ||
                (prop1.equals("ODD") && prop2.equals("EVEN")) ||
                (prop1.equals("DUCK") && prop2.equals("SPY")) ||
                (prop1.equals("SPY") && prop2.equals("DUCK")) ||
                (prop1.equals("SUNNY") && prop2.equals("SQUARE")) ||
                (prop1.equals("SQUARE") && prop2.equals("SUNNY"));
    }

    static void printNumsWithDoubleProps(AmazingNumber num, long count, String[] props) {
        long fit = 0;
        long n = num.getValue();
        while (fit != count) {
            AmazingNumber temp = new AmazingNumber(n);
            if (hasProp(temp, props[0]) && hasProp(temp, props[1])) {
                printPropsLine(temp);
                fit++;
            }
            n++;
        }
    }

    static boolean hasProp(AmazingNumber n, String prop) {
        return (prop.equalsIgnoreCase("even") && n.isEven()) ||
                (prop.equalsIgnoreCase("odd") && n.isOdd()) ||
                (prop.equalsIgnoreCase("buzz") && n.isBuzz()) ||
                (prop.equalsIgnoreCase("duck") && n.isDuck()) ||
                (prop.equalsIgnoreCase("palindromic") && n.isPalindrome()) ||
                (prop.equalsIgnoreCase("gapful") && n.isGapful()) ||
                (prop.equalsIgnoreCase("spy") && n.isSpy()) ||
                (prop.equalsIgnoreCase("square") && n.isSquare()) ||
                (prop.equalsIgnoreCase("sunny") && n.isSunny());
    }

    static void printNumsWithProp(AmazingNumber num, long count, String prop) {
        long fit = 0;
        long n = num.getValue();
        while (fit != count) {
            AmazingNumber temp = new AmazingNumber(n);
            if (hasProp(temp, prop)) {
                printPropsLine(temp);
                fit++;
            }
            n++;
        }
    }

    static boolean isNotProp(String prop) {
        if (prop == null)
            return true;
        return !prop.equalsIgnoreCase("even") &&
                !prop.equalsIgnoreCase("odd") &&
                !prop.equalsIgnoreCase("buzz") &&
                !prop.equalsIgnoreCase("duck") &&
                !prop.equalsIgnoreCase("palindromic") &&
                !prop.equalsIgnoreCase("gapful") &&
                !prop.equalsIgnoreCase("spy") &&
                !prop.equalsIgnoreCase("square") &&
                !prop.equalsIgnoreCase("sunny");
    }

    static String[] getInput() {
        return scanner.nextLine().split(" ", 0);
    }

    static void printPropsLine(AmazingNumber num) {
        System.out.println(num.getValue() + " is" +
                (num.isEven() ? " even" : " odd") +
                (num.isBuzz() ? ", buzz" : "") +
                (num.isDuck() ? ", duck" : "") +
                (num.isPalindrome() ? ", palindromic" : "") +
                (num.isGapful() ? ", gapful" : "") +
                (num.isSpy() ? ", spy" : "") +
                (num.isSquare() ? ", square" : "") +
                (num.isSunny() ? ", sunny" : ""));
    }

    static void printMultipleNumProps(AmazingNumber num, long num2) {
        for (long i = 0; i < num2; i++) {
            printPropsLine( new AmazingNumber(num.getValue() + i));
        }
    }

    static void printOneNumProps(AmazingNumber num) {
        printPropsOf(num.getValue());
        System.out.println("\teven: " + num.isEven());
        System.out.println("\todd: " + num.isOdd());
        System.out.println("\tbuzz: " + num.isBuzz());
        System.out.println("\tduck: " + num.isDuck());
        System.out.println("\tpalindromic: " + num.isPalindrome());
        System.out.println("\tgapful: " + num.isGapful());
        System.out.println("\tspy: " + num.isSpy());
        System.out.println("\tsquare: " + num.isSquare());
        System.out.println("\tsunny: " + num.isSunny());
        System.out.println();
    }

    static void printPropsOf(long num){
        System.out.print("Properties of ");
        String str = String.format(Locale.US, "%,d", num);
        System.out.println(str);
    }


    static void printInstruction() {
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be printed;");
        System.out.println("- two natural numbers and a property to search for;");
        System.out.println("- two natural numbers and two properties to search for;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
        System.out.println();
    }

    static void printIntroduction() {
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        printInstruction();
    }
}

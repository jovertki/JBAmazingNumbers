package numbers;

import java.util.*;

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

            Long[] num = new Long[2];
            num[0] = Long.valueOf(strs[0]);
            num[1] = (strs.length == 1) ? null : Long.valueOf(strs[1]);
            String[] props = (strs.length >= 3) ?
                    Arrays.copyOfRange(strs, 2, strs.length) : null;
            if (errorCheck(num, props, strs.length)) {
                if (num[0] == 0){
                    System.out.println("Goodbye!");
                    break;
                } else if (strs.length == 1) {
                    printOneNumProps(num[0]);
                } else if (strs.length == 2) {
                    printMultipleNumProps(num[0], num[1]);
                }  else if (strs.length == 3) {
                    printNumsWithProp(num[0], num[1], props[0]);
                } else if (strs.length == 4) {
                    printNumsWithDoubleProps(num[0], num[1], props);
                }
            }

        } while (true);
    }

    static boolean errorCheck(Long[] num, String[] props, long length) {
        if (num[0] < 0) {
            System.out.println("The first parameter should be a natural number or zero.");
            System.out.println();
            return false;
        } else if (length == 2 && num[1] <= 0) {
            System.out.println("The second parameter should be a natural number.");
            System.out.println();
            return false;
        } else if (length == 4 && !isProp(props[0]) && !isProp(props[1])) {
            System.out.println("The properties [" + props[0].toUpperCase() + ", " + props[1].toUpperCase() + "] are wrong.");
            System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SQUARE, SUNNY]");
            System.out.println();
            return false;
        } else if ((length == 3  || length == 4) && !isProp(props[0])) {
            System.out.println("The property [" + props[0].toUpperCase() + "] is wrong.");
            System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SQUARE, SUNNY]");
            System.out.println();
            return false;
        } else if ((length == 4) && !isProp(props[1])) {
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

    static void printNumsWithDoubleProps(long n, long count, String[] props) {
        long fit = 0;
        while (fit != count) {
            if (hasProp(n, props[0]) && hasProp(n, props[1])) {
                printPropsLine(n);
                fit++;
            }
            n++;
        }
    }

    static boolean hasProp(long n, String prop) {
        return (prop.equalsIgnoreCase("even") && isEven(n)) ||
                (prop.equalsIgnoreCase("odd") && !isEven(n)) ||
                (prop.equalsIgnoreCase("buzz") && isBuzz(n)) ||
                (prop.equalsIgnoreCase("duck") && isDuck(n)) ||
                (prop.equalsIgnoreCase("palindromic") && isPalindrome(n)) ||
                (prop.equalsIgnoreCase("gapful") && isGapful(n)) ||
                (prop.equalsIgnoreCase("spy") && isSpy(n)) ||
                (prop.equalsIgnoreCase("square") && isSquare(n)) ||
                (prop.equalsIgnoreCase("sunny") && isSunny(n));
    }

    static void printNumsWithProp(long n, long count, String prop) {
        long fit = 0;
        while (fit != count) {
            if (hasProp(n, prop)) {
                printPropsLine(n);
                fit++;
            }
            n++;
        }
    }

    static boolean isSpy(long num) {
        String str = Long.toString(num);
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
        return sum == product;
    }

    static boolean isProp(String prop) {
        if (prop == null)
            return false;
        return prop.equalsIgnoreCase("even") ||
                prop.equalsIgnoreCase("odd") ||
                prop.equalsIgnoreCase("buzz") ||
                prop.equalsIgnoreCase("duck") ||
                prop.equalsIgnoreCase("palindromic") ||
                prop.equalsIgnoreCase("gapful") ||
                prop.equalsIgnoreCase("spy") ||
                prop.equalsIgnoreCase("square") ||
                prop.equalsIgnoreCase("sunny");
    }

    static String[] getInput() {
        return scanner.nextLine().split(" ", 0);
    }

    static void printPropsLine(long num) {
        System.out.println(num + " is" +
                (isEven(num) ? " even" : " odd") +
                (isBuzz(num) ? ", buzz" : "") +
                (isDuck(num) ? ", duck" : "") +
                (isPalindrome(num) ? ", palindromic" : "") +
                (isGapful(num) ? ", gapful" : "") +
                (isSpy(num) ? ", spy" : "") +
                (isSquare(num) ? ", square" : "") +
                (isSunny(num) ? ", sunny" : ""));
    }

    static void printMultipleNumProps(long num, long num2) {
        for (long i = 0; i < num2; i++) {
            printPropsLine(num + i);
        }
    }

    static void printOneNumProps(long num) {
        printPropsOf(num);
        System.out.println("\teven: " + ((isEven(num)) ? "true" : "false"));
        System.out.println("\todd: " + ((isEven(num)) ? "false" : "true"));
        System.out.println("\tbuzz: " + ((isBuzz(num)) ? "true" : "false"));
        System.out.println("\tduck: " + ((isDuck(num)) ? "true" : "false"));
        System.out.println("\tpalindromic: " + ((isPalindrome(num)) ? "true" : "false"));
        System.out.println("\tgapful: " + ((isGapful(num)) ? "true" : "false"));
        System.out.println("\tspy: " + ((isSpy(num)) ? "true" : "false"));
        System.out.println("\tsquare: " + ((isSquare(num)) ? "true" : "false"));
        System.out.println("\tsunny: " + ((isSunny(num)) ? "true" : "false"));
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

    static boolean isSquare(long num) {
        double sqrt = Math.sqrt(num);
        return ((sqrt - Math.floor(sqrt)) == 0);
    }

    static boolean isSunny(long num) {
        return isSquare(num + 1);
    }

    static boolean isGapful(long num) {
        String str = Long.toString(num);
        if (str.length() < 3)
            return false;
        long div = Long.parseLong(str.substring(0, 1) + str.charAt(str.length() - 1));
        return num % div == 0;
    }

    static boolean isEven(long num) {
        return num % 2 == 0;
    }

    static boolean isBuzz(long num) {
        return num % 10 == 7 || num % 7 == 0;
    }

    static boolean isDuck(long num) {
        String str = String.valueOf(num);
        return str.lastIndexOf('0') != -1;
    }

    static boolean isPalindrome(long num) {
        if (num < 10)
            return true;
        String str = String.valueOf(num);
        int i = 0;
        int j = str.length() - 1;

        while (str.charAt(i) == str.charAt(j) && i != j && i + 1 != j) {
            i++;
            j--;
        }
        return str.charAt(i) == str.charAt(j);
    }
}

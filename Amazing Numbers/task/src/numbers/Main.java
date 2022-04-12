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

    public boolean isJumping() {
        if (this.jumping != null)
            return this.jumping;
        if (value < 10) {
            this.jumping = true;
            return true;
        }
        String[] temp = value.toString().split("");
        ArrayList<Long> nums = new ArrayList<>();
        for (String s : temp) {
            nums.add(Long.parseLong(s));
        }
        for (int i = 0; i <= nums.size() - 2; i++ ) {
            if (nums.get(i) != nums.get(i + 1) + 1 &&
                    nums.get(i) != nums.get(i + 1) - 1) {
                this.jumping = false;
                return false;
            }
        }
        this.jumping = true;
        return true;
    }

    public boolean isHappy() {
        if (this.happy != null)
            return this.happy;
        if (value == 1) {
            this.happy = true;
            this.sad = false;
            return true;
        }
        ArrayList<Long> repeats = new ArrayList<>();
        long temp = value;
        do {
            repeats.add(temp);
            String[] strs = Long.toString(temp).split("");
            temp = 0;
            for (String n : strs) {
                temp += Long.parseLong(n) * Long.parseLong(n);
            }
        } while (temp != 1 && !repeats.contains(temp));
        this.happy = temp == 1;
        this.sad = !this.happy;
        return this.happy;
    }

    public boolean isSad() {
        if (this.sad != null)
            return this.sad;
        isHappy();
        return this.sad;
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
    private Boolean jumping = null;
    private Boolean happy = null;
    private Boolean sad = null;
}

public class Main {
    final static Scanner scanner = new Scanner(System.in);
    final static String[] availableProps = {"EVEN", "ODD", "BUZZ", "DUCK",
            "PALINDROMIC", "GAPFUL", "SPY", "SQUARE", "SUNNY", "JUMPING",
            "HAPPY", "SAD", "-EVEN", "-ODD", "-BUZZ", "-DUCK", "-PALINDROMIC",
            "-GAPFUL", "-SPY", "-SQUARE", "-SUNNY", "-JUMPING", "-HAPPY", "-SAD"};

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

            String[] props = parseProps(strs);
            if (errorCheck(num, count, props, strs.length)) {
                if (num.getValue() == 0){
                    System.out.println("Goodbye!");
                    break;
                } else if (strs.length == 1) {
                    printOneNumProps(num);
                } else if (strs.length == 2) {
                    printMultipleNumProps(num, count);
                } else {
                    printNumsWithProps(num, count, props);
                }
            }

        } while (true);
    }

    static boolean hasAllProps(AmazingNumber num, String[] props) {
        for (String prop : props) {
            if (!hasProp(num, prop)){
                return false;
            }
        }
        return true;
    }

    static void printNumsWithProps(AmazingNumber num, long count, String[] props) {
        long fit = 0;
        long n = num.getValue();
        while (fit != count) {
            AmazingNumber temp = new AmazingNumber(n);
            if (hasAllProps(temp, props)) {
                printPropsLine(temp);
                fit++;
            }
            n++;
        }
    }

    static String[] parseProps(String[] strs){
        String[] props = (strs.length >= 3) ?
                Arrays.copyOfRange(strs, 2, strs.length) : null;
        if (props != null) {
            for (int i = 0; i < props.length; i++)
                props[i] = props[i].toUpperCase();
            Arrays.sort(props);
        }
        return props;
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
        }
        if (length >= 3) {
            return checkWrongProps(props) && checkExclusiveProps(props);
        }
        return true;
    }

    static boolean checkWrongProps(String[] props) {
        ArrayList<String> errProps = new ArrayList<>();
        for (String prop : props) {
            if (isNotProp(prop)) {
                errProps.add(prop);
            }
        }
        if (errProps.isEmpty())
            return true;
        else if (errProps.size() == 1){
            System.out.println("The property [" + props[0].toUpperCase() + "] is wrong.");
        } else {
            System.out.print("The properties ");
            System.out.print(errProps);
            System.out.println(" are wrong.");
        }
        System.out.println("Available properties: " + Arrays.toString(availableProps));
        System.out.println();
        return false;
    }

    static boolean checkExclusiveProps(String[] props) {
        if (hasExclusive(props)) {
            System.out.println("There are no numbers with these properties.");
            System.out.println();
            return false;
        }
        return true;
    }

    static boolean hasExclusive(String[] props) {
        String str = null;
        if(Arrays.binarySearch(props, "EVEN") >=0 &&
                Arrays.binarySearch(props, "ODD") >=0) {
            str = "EVEN, ODD";
        } else if (Arrays.binarySearch(props, "DUCK") >=0 &&
                Arrays.binarySearch(props, "SPY") >=0) {
            str = "DUCK, SPY";
        } else if (Arrays.binarySearch(props, "SUNNY") >=0 &&
                Arrays.binarySearch(props, "SQUARE") >=0) {
            str = "SUNNY, SQUARE";
        } else if (Arrays.binarySearch(props, "HAPPY") >=0 &&
                Arrays.binarySearch(props, "SAD") >=0) {
            str = "HAPPY, SAD";
        } else if(Arrays.binarySearch(props, "-EVEN") >=0 &&
                Arrays.binarySearch(props, "-ODD") >=0) {
            str = "-EVEN, -ODD";
        } else if (Arrays.binarySearch(props, "-DUCK") >=0 &&
                Arrays.binarySearch(props, "-SPY") >=0) {
            str = "-DUCK, -SPY";
        } else if (Arrays.binarySearch(props, "-SUNNY") >=0 &&
                Arrays.binarySearch(props, "-SQUARE") >=0) {
            str = "-SUNNY, -SQUARE";
        } else if (Arrays.binarySearch(props, "-HAPPY") >=0 &&
                Arrays.binarySearch(props, "-SAD") >=0) {
            str = "-HAPPY, -SAD";
        }
        for (String prop : props) {
            for (String s : props) {
                if (prop.equals("-" + s)) {
                    str = prop + ", " + s;
                    break;
                }
            }
            if (str != null)
                break;
        }
        if (str != null) {
            System.out.println("The request contains mutually exclusive properties: [" + str + "]");
            return true;
        }
        return false;
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
                (prop.equalsIgnoreCase("sunny") && n.isSunny()) ||
                (prop.equalsIgnoreCase("jumping") && n.isJumping()) ||
                (prop.equalsIgnoreCase("happy") && n.isHappy()) ||
                (prop.equalsIgnoreCase("sad") && n.isSad()) ||

                (prop.equalsIgnoreCase("-even") && !n.isEven()) ||
                (prop.equalsIgnoreCase("-odd") && !n.isOdd()) ||
                (prop.equalsIgnoreCase("-buzz") && !n.isBuzz()) ||
                (prop.equalsIgnoreCase("-duck") && !n.isDuck()) ||
                (prop.equalsIgnoreCase("-palindromic") && !n.isPalindrome()) ||
                (prop.equalsIgnoreCase("-gapful") && !n.isGapful()) ||
                (prop.equalsIgnoreCase("-spy") && !n.isSpy()) ||
                (prop.equalsIgnoreCase("-square") && !n.isSquare()) ||
                (prop.equalsIgnoreCase("-sunny") && !n.isSunny()) ||
                (prop.equalsIgnoreCase("-jumping") && !n.isJumping()) ||
                (prop.equalsIgnoreCase("-happy") && !n.isHappy()) ||
                (prop.equalsIgnoreCase("-sad") && !n.isSad());
    }

    static boolean isNotProp(String prop) {
        if (prop == null)
            return true;
        for (String s : availableProps) {
            if (prop.equalsIgnoreCase(s))
                return false;
        }
        return true;
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
                (num.isSunny() ? ", sunny" : "") +
                (num.isJumping() ? ", jumping" : "") +
                (num.isHappy() ? ", happy" : "") +
                (num.isSad() ? ", sad" : ""));
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
        System.out.println("\tjumping: " + num.isJumping());
        System.out.println("\thappy: " + num.isHappy());
        System.out.println("\tsad: " + num.isSad());
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
        System.out.println("  * the second parameter shows how many consecutive numbers are to be processed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
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

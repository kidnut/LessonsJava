import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner console = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String input = console.nextLine();
        input = input.replaceAll(" ", "");
        System.out.println(parse(input));
    }
    public static String parse(String input) throws Exception {
        int numOne;
        int numTwo;
        String operation;
        String result;
        boolean isRoman;
//        String[] operands = input.split("[\\s+\\-*/]");
        String[] operands = input.split("[\\s+\\-*/]");
        if (operands.length != 2) throw new Exception ("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        operation = detectOperation(input);
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
            numOne = Roman.converteToAradian(operands[0]);
            numTwo = Roman.converteToAradian(operands[1]);
            isRoman = true;
        } else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])){
            numOne = Integer.parseInt(operands[0]);
            numTwo = Integer.parseInt(operands[1]);
            isRoman = false;
        } else {
            throw new Exception("Используются одновременно разные системы счисления");
        }
        if (numOne > 10 || numTwo > 10) {
            throw new Exception("Числа должны быть больше 10");
        }
        int arabian = calc(numOne, numTwo, operation);
        if (isRoman) {
            if (arabian <= 0) {
                throw new Exception("Римское число должно быть больше 0");
            }
            result = Roman.convertToRoman(arabian);
        } else {
            result = String.valueOf(arabian);
        }
        return result;
    }

    static String detectOperation(String input) {
        if (input.contains("+")) return "+";
        else if (input.contains("-")) return "-";
        else if (input.contains("*")) return "*";
        else if (input.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String operation) {
        if (operation.equals("+")) return a + b;
        else if (operation.equals("-")) return a - b;
        else if (operation.equals("*")) return a * b;
        else return a / b;
    }
}

class Roman {
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"};

    public static boolean isRoman(String val) {
        for(int i = 0; i < romanArray.length; i++) {
            if (val.equals(romanArray[i])) {
                return true;
            }
        }
        return false;
    }
    public static int converteToAradian(String roman) {
        for(int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman(int arabian) {
        return romanArray[arabian];
    }
}
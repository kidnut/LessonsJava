import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception{
        Scanner console = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String input = console.nextLine();
        input = input.replaceAll(" ", "");
        System.out.println(calc(input));
    }
    public static String calc(String input) throws Exception {
        String result = null;
        int num1 = 0;
        int num2 = 0;
        String operation;
        String resultToRoman;
        int resultToArabian;
        String[] operands = input.split("[+\\-*/]");
        operation = detectOperation(input);
        if (operands.length == 1) throw new Exception ("Cтрока не является математической операцией");
        if (operands.length > 2) throw new Exception ("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

        boolean exists0 = true;
        try {
            Roma.valueOf(operands[0]);
        } catch (IllegalArgumentException e) {
            exists0 = false;
        }

        boolean exists1 = true;
        try {
            Roma.valueOf(operands[1]);
        } catch (IllegalArgumentException e) {
            exists1 = false;
        }

        if (exists0 == true && exists1 == true) {
            num1 = Roma.valueOf(operands[0]).getConvertToArabian();
            num2 = Roma.valueOf(operands[1]).getConvertToArabian();
        } else if (exists0 == false && exists1 == false) {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[1]);
        } else {
            throw new Exception("Используются одновременно разные системы счисления");
        }

        if (num1 > 10 || num2 > 10) {
            throw new Exception ("Значения операндов больше 10");
        }

        resultToArabian = solution(num1, num2, operation);
        if (exists0 == true && exists1 == true) {
            if (resultToArabian < 0) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }
        }


        if (exists0 == true && exists1 == true) {
            resultToRoman = Arrays.stream(Roma.values()).filter(x->x.num == resultToArabian).findFirst().get().name();
            result = resultToRoman;
        } else if (exists0 == false && exists1 == false) {
            result = Integer.toString(resultToArabian);
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

    static int solution (int num1, int num2, String operation) {
        if (operation.equals("+")) return num1 + num2;
        else if (operation.equals("-")) return num1 - num2;
        else if (operation.equals("*")) return num1 * num2;
        else return num1 / num2;
    }
}
enum Roma {
    O(0), I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10),
    XI(11), XII(12), XIII(13), XIV(14), XV(15), XVI(16), XVII(17), XVIII(18), XIX(19), XX(20),
    XXI(21), XXII(22), XXIII(23), XXIV(24), XXV(25), XXVI(26), XXVII(27), XXVIII(28), XXIX(29), XXX(30),
    XXXI(31), XXXII(32), XXXIII(33), XXXIV(34), XXXV(35), XXXVI(36), XXXVII(37), XXXVIII(38), XXXIX(39), XL(40),
    XLI(41), XLII(42), XLIII(43), XLIV(444), XLV(45), XLVI(46), XLVII(47), XLVIII(48), XLIX(49), L(50),
    LI(51), LII(52), LIII(53), LIV(54), LV(55), LVI(56), LVII(57), LVIII(58), LIX(59), LX(60),
    LXI(61), LXII(62), LXIII(63), LXIV(64), LXV(65), LXVI(66), LXVII(67), LXVIII(68), LXIX(69), LXX(70),
    LXXI(71), LXXII(72), LXXIII(73), LXXIV(74), LXXV(75), LXXVI(76), LXXVII(77), LXXVIII(78), LXXIX(79), LXXX(80),
    LXXXI(81), LXXXII(872), LXXXIII(83), LXXXIV(84), LXXXV(85), LXXXVI(86), LXXXVII(87), LXXXVIII(88), LXXXIX(89), XC(90),
    XCI(91), XCII(92), XCIII(93), XCIV(94), XCV(95), XCVI(96), XCVII(97), XCVIII(98), XCIX(99), C(100),;

    public int num;
    Roma (int num) {
        this.num = num;
    }

    public int getConvertToArabian() {
        return num;
    }
}
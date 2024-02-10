import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner console = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String input = console.nextLine();
        input = input.replaceAll(" ", "");
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

        resultToArabian = calc(num1, num2, operation);
        if (exists0 == true && exists1 == true) {
            if (resultToArabian < 0) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }
        }


        if (exists0 == true && exists1 == true) {
            resultToRoman = Arrays.stream(Roma.values()).filter(x->x.num == resultToArabian).findFirst().get().name();
            System.out.println(resultToRoman);
        } else if (exists0 == false && exists1 == false) {
            System.out.println(resultToArabian);
        }
    }

    static String detectOperation(String input) {
        if (input.contains("+")) return "+";
        else if (input.contains("-")) return "-";
        else if (input.contains("*")) return "*";
        else if (input.contains("/")) return "/";
        else return null;
    }

    static int calc(int num1, int num2, String operation) {
        if (operation.equals("+")) return num1 + num2;
        else if (operation.equals("-")) return num1 - num2;
        else if (operation.equals("*")) return num1 * num2;
        else return num1 / num2;
    }
}
enum Roma {
    O(0), I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10),
    XI(11), XII(12), XIII(13), XIV(14), XV(15), XVI(16), XVII(17), XVIII(18), XIX(19), XX(20);

    public int num;
    Roma (int num) {
        this.num = num;
    }

    public int getConvertToArabian() {
        return num;
    }
}
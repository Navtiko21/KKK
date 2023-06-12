import java.util.*;

public class Calculator1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String expression = scanner.nextLine();
        String[] values = expression.split(" ");

        int num1 = 0, num2 = 0;
        boolean isRoman = false;
        String romanNum1 = values[0], romanNum2 = values[2];

        if (isRomanNumeral(romanNum1) && isRomanNumeral(romanNum2)) {
            num1 = romanToArabic(romanNum1);
            num2 = romanToArabic(romanNum2);
            isRoman = true;
        } else {
            num1 = Integer.parseInt(values[0]);
            num2 = Integer.parseInt(values[2]);
        }

        if (num1 < 1 || num1 > 10) {
            System.out.println("вы ввели некорректное число");
            return;
        }
        if (num2 < 1 || num2 > 10) {
            System.out.println("вы ввели некорректное число");
            return;
        }

        String operator = values[1];
        double result = 0;
        switch (operator) {
            case "+":
                result = (double) (num1 + num2);
                break;
            case "-":
                if (isRoman) {
                    if (num1 - num2 < 1) {
                        throw new IllegalArgumentException("Отрицательное значение римского числа");
                    }
                }
                result = (double) (num1 - num2);
                break;
            case "*":
                result = (double) (num1 * num2);
                break;
            case "/":
                result = (double) (num1 / num2);
                break;
            default:
                System.out.println("Некорректный оператор");
                return;
        }

        String input = (isRoman ? romanNum1 : num1) + operator + (isRoman ? romanNum2 : num2);
        String output = isRoman ? arabicToRoman((int) result) : Double.toString(result);
        System.out.println(input + "=" + output);
    }

    public static boolean isRomanNumeral(String s) {
        return s.matches("^[IVXCDM]+$");
    }

    public static int romanToArabic(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;
        int previousValue = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            int currentValue = map.get(s.charAt(i));
            if (currentValue < previousValue) {
                result -= currentValue;
            } else {
                result += currentValue;
                previousValue = currentValue;
            }
        }
        return result;
    }

    // перевод арабских цифр в римские
    public static String arabicToRoman(int number) {
        String[] romanNumerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] arabicNumerals = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder result = new StringBuilder();
        int i = 0;

        while (number > 0) {
            if (number >= arabicNumerals[i]) {
                result.append(romanNumerals[i]);
                number -= arabicNumerals[i];
            } else {
                i++;
            }
        }
        return result.toString();
    }
}

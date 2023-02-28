/*
В калькулятор добавьте возможность отменить последнюю операцию.
 */

import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Stack;

public class task3 {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        String str = "";
        Scanner iScanner = new Scanner(System.in);
        for (int i = 0; i < 4; i++) {
            if (i == 3) {
                System.out.printf(
                        "Все ли верно? Нужно посчитать: %s %s %s? Введите 'y' если все верно, введите 'b' если нужно отменить последнюю операцию\n",
                        stack.get(0), stack.get(1), stack.get(2));
            }
            System.out.printf("Значение %s\n", i + 1); // Тут значение 1,3 - это числа, значение 2 - действие, а
                                                       // значение 4 - подтверждение что введено все верно или нет.
            str = iScanner.nextLine();
            stack.push(str);
            if (str.equals("b")) {
                stack.remove(i);
                stack.remove(i - 1);
                i -= 2;
            }
            if (i == 3) {
                if (stack.get(3).equals("y")) {
                    double num_a = Double.parseDouble(stack.get(0));
                    String action = (stack.get(1)).toString();
                    String result = "";
                    double num_b = Double.parseDouble(stack.get(2));
                    try (Writer FileWriter = new FileWriter("task3.txt", true)) {
                        switch (action) {
                            case "+":
                                System.out.printf("Ответ: %s\n", num_a + num_b);
                                result = String.format("Дано %s %s %s. Ответ: %s", num_a, action, num_b, num_a + num_b);
                                break;

                            case "-":
                                System.out.printf("Ответ: %s\n", num_a - num_b);
                                result = String.format("Дано: %s %s %s. Ответ: %s", num_a, action, num_b,
                                        num_a - num_b);
                                break;

                            case "*":
                                System.out.printf("Ответ: %s\n", num_a * num_b);
                                result = String.format("Дано: %s %s %s. Ответ: %s", num_a, action, num_b,
                                        num_a * num_b);
                                break;

                            case "/":
                                System.out.printf("Ответ: %s\n", num_a / num_b);
                                result = String.format("Дано: %s %s %s. Ответ: %s", num_a, action, num_b,
                                        num_a / num_b);
                                break;

                            default:
                                System.out.println("Введите правильное действие");
                                result = String.format("Дано: %s %s %s. Ответ: Введено неверное действие", num_a,
                                        action,
                                        num_b);
                                break;
                        }

                        FileWriter.write(result); // Сохраняет в файл, не затирая предыдущую информацию
                        FileWriter.write("\n");
                    } catch (IOException e) {
                        e.printStackTrace(); // Выведет в консоль причину ошибки
                    }
                }
            }
        }
        iScanner.close();
    }
}
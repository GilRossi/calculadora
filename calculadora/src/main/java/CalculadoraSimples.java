import java.util.Scanner;

public class CalculadoraSimples {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Calculadora Versão 1.0 ===");

        System.out.print("Digite o primeiro número: ");
        double num1 = scanner.nextDouble();

        System.out.print("Digite o segundo número: ");
        double num2 = scanner.nextDouble();

        System.out.print("Digite a operação (+, -, *, /): ");
        char operacao = scanner.next().charAt(0);

        double resultado;

        switch (operacao) {
            case '+':
                resultado = num1 + num2;
                System.out.printf("Resultado: %.2f + %.2f = %.2f%n", num1, num2, resultado);
                break;
            case '-':
                resultado = num1 - num2;
                System.out.printf("Resultado: %.2f - %.2f = %.2f%n", num1, num2, resultado);
                break;
            case '*':
                resultado = num1 * num2;
                System.out.printf("Resultado: %.2f * %.2f = %.2f%n", num1, num2, resultado);
                break;
            case '/':
                if (num2 != 0) {
                    resultado = num1 / num2;
                    System.out.printf("Resultado: %.2f / %.2f = %.2f%n", num1, num2, resultado);
                } else {
                    System.out.println("Erro: Divisão por zero não é permitida.");
                }
                break;
            default:
                System.out.println("Operação inválida. Por favor, use +, -, * ou /.");
                return;
        }
    }
}

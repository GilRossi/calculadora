// Importa bibliotecas que ajudam a criar a interface gráfica (janelas, botões, etc.)
import javax.swing.*;
// Importa classes para controle de layout e fontes
import java.awt.*;
// Importa classes para lidar com eventos do teclado e clique de botões
import java.awt.event.*;

// Define a classe da calculadora, que herda de uma janela (JFrame)
// Também implementa dois tipos de escuta: para cliques (ActionListener) e para teclas (KeyListener)
public class CalculadoraGUI extends JFrame implements ActionListener, KeyListener {

    // Campo onde o número e o resultado aparecem
    private JTextField display;

    // Armazena os dois números da conta
    private double num1 = 0, num2 = 0;

    // Armazena qual operação o usuário escolheu (+, -, *, /)
    private String operador = "";

    // Indica se o próximo número é de um novo cálculo
    private boolean novoCalculo = true;

    // Construtor da calculadora (roda quando a janela é criada)
    public CalculadoraGUI() {
        setTitle("Calculadora"); // Define o título da janela
        setSize(300, 400);       // Define o tamanho da janela (largura x altura)
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        setLayout(new BorderLayout()); // Define o tipo de layout (divisão em áreas)

        // Cria o display onde os números aparecem
        display = new JTextField("0"); // Começa com 0
        display.setFont(new Font("Arial", Font.BOLD, 28)); // Define fonte e tamanho
        display.setHorizontalAlignment(SwingConstants.RIGHT); // Alinha o texto à direita
        display.setEditable(false); // Impede que o usuário digite direto no campo
        add(display, BorderLayout.NORTH); // Adiciona o display na parte superior

        // Cria o painel com os botões da calculadora (números e operações)
        JPanel painel = new JPanel(new GridLayout(5, 4, 5, 5)); // Layout em grade (5 linhas, 4 colunas)

        // Lista com os textos dos botões que aparecerão na calculadora
        String[] botoes = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C" // Botão para limpar
        };

        // Para cada texto na lista, cria um botão com aquele texto
        for (String txt : botoes) {
            JButton botao = new JButton(txt); // Cria o botão
            botao.setFont(new Font("Arial", Font.BOLD, 24)); // Define o estilo do texto do botão
            botao.addActionListener(this); // Conecta o clique do botão ao método que trata o evento
            painel.add(botao); // Adiciona o botão ao painel
        }

        // Adiciona o painel com os botões à parte central da janela
        add(painel, BorderLayout.CENTER);

        // Permite o uso do teclado para digitar na calculadora
        addKeyListener(this); // Conecta as teclas ao programa
        setFocusable(true);   // Garante que a janela aceita foco do teclado
        setFocusTraversalKeysEnabled(false); // Evita que teclas como Tab mudem o foco dos componentes

        setVisible(true);         // Exibe a janela
        requestFocusInWindow();   // Garante que o foco do teclado vá direto para a janela
    }

    // Método que é chamado automaticamente quando um botão é clicado
    @Override
    public void actionPerformed(ActionEvent e) {
        processarEntrada(e.getActionCommand()); // Envia o texto do botão pressionado para processamento
    }

    // Método que processa o texto digitado ou clicado
    private void processarEntrada(String comando) {
        // Se for número ou ponto (ex: 1, 2, 3 ou .)
        if (comando.matches("[0-9\\.]")) {
            if (novoCalculo) { // Se for início de um novo cálculo, limpa o display
                display.setText("");
                novoCalculo = false;
            }
            display.setText(display.getText() + comando); // Adiciona o número ao display
        }
        // Se for um operador: +, -, *, /
        else if (comando.matches("[\\+\\-\\*/]")) {
            num1 = Double.parseDouble(display.getText()); // Armazena o primeiro número
            operador = comando; // Armazena o operador
            novoCalculo = true; // Prepara para digitar o segundo número
        }
        // Se for igual (=), realiza o cálculo
        else if (comando.equals("=")) {
            num2 = Double.parseDouble(display.getText()); // Lê o segundo número
            double resultado = calcular(num1, num2, operador); // Calcula
            display.setText(String.valueOf(resultado)); // Mostra o resultado no display
            novoCalculo = true;
        }
        // Se for "C" ou "c", limpa tudo
        else if (comando.equals("C") || comando.equalsIgnoreCase("c")) {
            display.setText("0");
            num1 = num2 = 0;
            operador = "";
            novoCalculo = true;
        }
    }

    // Método que realiza o cálculo com base no operador escolhido
    private double calcular(double a, double b, String op) {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> b != 0 ? a / b : 0; // Evita divisão por zero
            default -> 0;
        };
    }

    // Captura teclas pressionadas
    @Override
    public void keyTyped(KeyEvent e) {
        char tecla = e.getKeyChar(); // Lê qual tecla foi pressionada

        // Se for número ou ponto
        if (Character.isDigit(tecla) || tecla == '.') {
            processarEntrada(String.valueOf(tecla));
        }
        // Se for operador
        else if ("+-*/".indexOf(tecla) >= 0) {
            processarEntrada(String.valueOf(tecla));
        }
        // Se for igual ou Enter
        else if (tecla == '=' || tecla == '\n') {
            processarEntrada("=");
        }
        // Se for C ou c
        else if (tecla == 'c' || tecla == 'C') {
            processarEntrada("C");
        }
    }

    // Estes dois métodos são obrigatórios quando se usa KeyListener,
    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }

    // Método principal que inicia o programa
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculadoraGUI::new); // Cria a janela da calculadora
    }
}

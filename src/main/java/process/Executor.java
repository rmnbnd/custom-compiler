package process;

import core.Lexer;
import core.Parser;
import core.StaticAnalise;
import core.SymbolAnalise;
import core.model.ast.AST;
import core.model.lex.Token;
import core.model.symbol.Symbol;

import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.in;
import static java.lang.System.out;

public class Executor {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private Parser parser = new Parser();
    private Lexer lexer = new Lexer();
    private StaticAnalise staticAnalise = new StaticAnalise();
    private SymbolAnalise symbolAnalise = new SymbolAnalise();
    private static Executor executor = new Executor();

    public static void main(String[] args) throws IOException {
        out.println("Please choose to declare, assign, or exit");
        executor.execute();
    }

    public void execute() {
        boolean result = false;
        while (!result) {
            String inputString = executor.getInputString();
            result = executor.execute(inputString);
        }
    }

    public boolean execute(String input) {
        try {
            Queue<Token> tokens = lexer.lex(input);
            AST ast = parser.doParse(tokens);
            staticAnalise.analis(ast);
            List<Symbol> symbols = symbolAnalise.analis(ast);
            return true;
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
            out.println("Please choose to declare, assign, or exit");
            return false;
        }
    }

    private String getInputString() {
        Scanner scanner = new Scanner(in);
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }
            if ("exit".equals(input)) {
                exit(0);
            }
            stringBuilder.append(input).append(LINE_SEPARATOR);
        }
        return stringBuilder.toString();
    }

}

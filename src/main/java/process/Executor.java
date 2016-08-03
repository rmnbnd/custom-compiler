package process;

import core.Lexer;
import core.Parser;
import core.StaticAnalise;
import core.SymbolAnalise;
import core.model.ast.AST;
import core.model.lex.Token;
import core.model.symbol.Symbol;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import static java.lang.System.out;

public class Executor {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private Parser parser = new Parser();
    private Lexer lexer = new Lexer();
    private StaticAnalise staticAnalise = new StaticAnalise();
    private SymbolAnalise symbolAnalise = new SymbolAnalise();
    private static Executor executor = new Executor();

    public static void main(String[] args) throws IOException {
        Executor executor = new Executor();
        executor.execute();
    }

    public void execute() {
        String input = getFile("input.txt");
        execute(input);
    }

    public void execute(String input) {
        try {
            Queue<Token> tokens = lexer.lex(input);
            AST ast = parser.doParse(tokens);
            staticAnalise.analis(ast);
            List<Symbol> symbols = symbolAnalise.analis(ast);
            out.println("Execute without errors");
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }

    private String getFile(String fileName) {
        StringBuilder result = new StringBuilder("");
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();

    }

}

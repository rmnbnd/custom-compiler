package process;

import core.Interpreter;
import core.Lexer;
import core.Parser;
import core.StaticAnalise;
import core.SymbolAnalise;
import core.model.ast.AST;
import core.model.lex.Token;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Queue;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class Executor {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private Parser parser = new Parser();
    private Lexer lexer = new Lexer();
    private StaticAnalise staticAnalise = new StaticAnalise();
    private SymbolAnalise symbolAnalise = new SymbolAnalise();
    private Interpreter interpreter = new Interpreter();
    private static Executor executor = new Executor();

    public static void main(String[] args) throws IOException {
        out.println("Please choose to declare, assign");
        executor.execute();
    }

    private void execute() {
        File file = saveToFile(executor.getInputString());
        executor.execute(getInputString(file));
    }

    private String getInputString(File file) {
        StringBuilder result = new StringBuilder("");
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

    private File saveToFile(String inputString) {
        File file = new File("src/main/resources/input.txt");
        try (FileOutputStream fop = new FileOutputStream(file)) {

            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // get the content in bytes
            byte[] contentInBytes = inputString.getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private void execute(String input) {
        try {
            Queue<Token> tokens = lexer.lex(input);
            AST ast = parser.doParse(tokens);
            staticAnalise.analis(ast);
            interpreter.evaluate(ast, symbolAnalise.analis(ast));
            out.println("Execute without errors");
        } catch (Exception e) {
            out.println("too many, will terminate");
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
            stringBuilder.append(input).append(LINE_SEPARATOR);
        }
        return stringBuilder.toString();
    }

}

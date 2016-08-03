package core.process;

import core.Lexer;
import core.Parser;
import core.StaticAnalise;
import core.SymbolAnalise;
import core.model.ast.AST;
import core.model.lex.Token;
import core.model.symbol.Symbol;

import java.util.List;
import java.util.Queue;

public class Executor {

    private Parser parser = new Parser();
    private Lexer lexer = new Lexer();
    private StaticAnalise staticAnalise = new StaticAnalise();
    private SymbolAnalise symbolAnalise = new SymbolAnalise();

    public void execute(String input) {
        Queue<Token> tokens = lexer.lex(input);
        AST ast = parser.doParse(tokens);
        staticAnalise.analis(ast);
        List<Symbol> symbols = symbolAnalise.analis(ast);
    }
}

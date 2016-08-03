package core;

import core.model.ast.*;
import core.model.symbol.Symbol;

import java.util.ArrayList;
import java.util.List;

public class SymbolAnalise {

    public List<Symbol> analis(AST ast) {
        List<Symbol> symbols = new ArrayList<>();
        if (ast instanceof Body) {
            Body body = (Body) ast;
            body.getLines().stream().filter(line -> line instanceof Declare).forEach(line -> {
                Declare declare = (Declare) line;
                Symbol symbol = new Symbol(declare.getName(), declare.getType().getTypeId());
                symbols.add(symbol);
            });
        }
        return symbols;
    }

}

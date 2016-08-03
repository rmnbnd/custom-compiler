package core;

import core.model.ast.AST;
import core.model.ast.Assignment;
import core.model.ast.BinaryOp;
import core.model.ast.Body;
import core.model.ast.Declare;
import core.model.ast.Display;
import core.model.ast.NumberConst;
import core.model.ast.Read;
import core.model.ast.ReadExpression;
import core.model.ast.StringValue;
import core.model.ast.Variable;
import core.model.symbol.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Interpreter {

    private Map<Symbol, String> values = new HashMap<>();
    private String read;

    public void evaluate(AST ast, List<Symbol> analis) {
        populateValues(analis);
        evaluateAssignments(ast);
        evaluateDisplay(ast);
    }

    private void evaluateDisplay(AST ast) {
        if (ast instanceof Body) {
            Body body = (Body) ast;
            for (AST line : body.getLines()) {
                if (line instanceof Display) {
                    Display display = (Display) line;
                    System.out.println("Display: " + values.get(getSymbol(display.getValue())));
                }
            }
        }
    }

    private void evaluateAssignments(AST ast) {
        if (ast instanceof Body) {
            Body body = (Body) ast;
            for (AST line : body.getLines()) {
                if (line instanceof Declare) {
                    // skip
                } else if (line instanceof Read) {
                    read = ((Read) line).getValue();
                } else if (line instanceof Assignment) {
                    Assignment assignment = (Assignment) line;
                    Symbol symbol = getSymbol(assignment.getValue());
                    values.put(symbol, getValue(assignment));
                }
            }
        }
    }

    private String getValue(Assignment assignment) {
        AST expression = assignment.getExpression();
        if (expression instanceof ReadExpression) {
            return read;
        } else if (expression instanceof BinaryOp) {
            BinaryOp binaryOp = (BinaryOp) expression;
            int left = Integer.parseInt(getValue(binaryOp.getLeft()));
            int right = Integer.parseInt(getValue(binaryOp.getRight()));
            if (binaryOp.getOperation().equals("+")) {
                return String.valueOf(left + right);
            } else if (binaryOp.getOperation().equals("-")) {
                return String.valueOf(left - right);
            } else if (binaryOp.getOperation().equals("*")) {
                return String.valueOf(left * right);
            } else if (binaryOp.getOperation().equals("/")) {
                return String.valueOf(left / right);
            }
        } else if (expression instanceof StringValue) {
            StringValue stringValue = (StringValue) expression;
            return stringValue.getData();
        }
        return "";
    }

    private String getValue(AST operand) {
        if (operand instanceof Variable) {
            Variable variable = (Variable) operand;
            return values.get(getSymbol(variable.getValue()));
        } else if (operand instanceof NumberConst) {
            NumberConst numberConst = (NumberConst) operand;
            return numberConst.getNumber();
        }
        return "0";
    }

    private Symbol getSymbol(String value) {
        for (Map.Entry<Symbol, String> existValue : values.entrySet()) {
            if (value.equals(existValue.getKey().getName())) {
                return existValue.getKey();
            }
        }
        return null;
    }

    private void populateValues(List<Symbol> analis) {
        for (Symbol symbol : analis) {
            values.put(symbol, "");
        }
    }

}

package core;

import core.model.ast.Display;
import core.model.ast.DisplayOperator;
import core.model.ast.Read;
import core.model.ast.AST;
import core.model.ast.Assignment;
import core.model.ast.BinaryOp;
import core.model.ast.Body;
import core.model.ast.Declare;
import core.model.ast.NumberConst;
import core.model.ast.ReadExpression;
import core.model.ast.Type;
import core.model.ast.Variable;
import core.model.lex.Kind;
import core.model.lex.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Parser {

    private List<AST> lines = new ArrayList<>();

    public AST doParse(Queue<Token> tokens) {
        while (tokens.size() > 0) {
            parse(tokens);
        }
        return new Body(lines);
    }

    private void parse(Queue<Token> tokens) {
        Token token = tokens.peek();
        switch (token.getKind()) {
            case DECLARE: {
                tokens.poll();
                Declare declare = new Declare(getName(tokens.poll()), getType(tokens.poll()));
                lines.add(declare);
                break;
            }
            case READ_OPERATION: {
                tokens.poll();
                Read read = new Read(tokens.poll().getData());
                lines.add(read);
                break;
            }
            case DISPLAY_OPERATION: {
                tokens.poll();
                Display display = new Display(tokens.poll().getData());
                lines.add(display);
                break;
            }
            case NUMBER: {
                int lineNumber = getLineNumber(tokens.poll());
                Assignment assignment = new Assignment(lineNumber, getName(tokens.poll()), getExpression(lineNumber, tokens));
                lines.add(assignment);
                break;
            }
            default: {
                throw new RuntimeException(token.getData() + " is not a declare or assignment.");
            }
        }
    }

    private AST getExpression(int lineNumber, Queue<Token> tokens) {
        tokens.poll();
        if (tokens.peek().getKind() == Kind.READ_OPERATION) {
            tokens.poll();
            return new ReadExpression();
        }
        AST left = getElementFromExpression(lineNumber, tokens.poll());
        Token operation = tokens.poll();
        AST right = getElementFromExpression(lineNumber, tokens.poll());
        return new BinaryOp(operation.getData(), left, right);
    }

    private AST getElementFromExpression(int lineNumber, Token token) {
        if (token.getKind() == Kind.IDENTIFIER) {
            return new Variable(lineNumber, token.getData());
        } else if (token.getKind() == Kind.NUMBER) {
            return new NumberConst(token.getData());
        }
        throw new RuntimeException(lineNumber + "  Invalid element in expression");
    }

    private int getLineNumber(Token token) {
        return Integer.valueOf(token.getData());
    }

    private Type getType(Token token) {
        if (token.getKind() == Kind.INTEGER) {
            return Type.INTEGER;
        }
        throw new RuntimeException("Not found type");
    }

    private String getName(Token token) {
        if (token.getKind() == Kind.IDENTIFIER) {
            return token.getData();
        }
        throw new RuntimeException("Invalid name for variable");
    }

}

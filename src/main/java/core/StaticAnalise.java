package core;

import core.model.ast.AST;
import core.model.ast.Assignment;
import core.model.ast.BinaryOp;
import core.model.ast.Body;
import core.model.ast.Declare;
import core.model.ast.NumberConst;
import core.model.ast.ReadExpression;
import core.model.ast.StringValue;
import core.model.ast.Variable;

import java.util.HashSet;
import java.util.Set;

public class StaticAnalise {

    private Set<String> vars = new HashSet<>();

    public void analis(AST ast) {
        if (ast instanceof Body) {
            Body body = (Body) ast;
            for (AST line : body.getLines()) {
                if (line instanceof Declare) {
                    Declare declare = (Declare) line;
                    vars.add(declare.getName());
                } else if (line instanceof Assignment) {
                    Assignment assignment = (Assignment) line;
                    analis(assignment.getExpression());
                }
            }
        } else if (ast instanceof BinaryOp) {
            BinaryOp binaryOp = (BinaryOp) ast;
            analis(binaryOp.getLeft());
            analis(binaryOp.getRight());
        } else if (ast instanceof Variable) {
            Variable variable = (Variable) ast;
            if (!vars.contains(variable.getValue())) {
                throw new RuntimeException(variable.getLineNumber() + "  Use of undeclared variable name " + variable.getValue());
            }
        } else if (ast instanceof NumberConst) {
            // skip
        } else if (ast instanceof ReadExpression) {
            // skip
        } else if (ast instanceof StringValue) {
            // skip
        } else {
            throw new RuntimeException("Unexpected AST structure");
        }
    }

}

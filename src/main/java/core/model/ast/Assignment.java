package core.model.ast;

public class Assignment extends AST {

    private int lineNumber;
    private String value;
    private AST expression;

    public Assignment(int lineNumber, String value, AST expression) {
        this.lineNumber = lineNumber;
        this.value = value;
        this.expression = expression;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AST getExpression() {
        return expression;
    }

    public void setExpression(AST expression) {
        this.expression = expression;
    }
}

package core.model.ast;

public class Variable extends AST {

    private String value;
    private int lineNumber;

    public Variable(int lineNumber, String value) {
        this.value = value;
        this.lineNumber = lineNumber;
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
}

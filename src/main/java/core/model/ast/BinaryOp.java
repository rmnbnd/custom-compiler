package core.model.ast;

public class BinaryOp extends AST {

    private String operation;
    private AST left;
    private AST right;

    public BinaryOp(String operation, AST left, AST right) {
        this.operation = operation;
        this.left = left;
        this.right = right;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public AST getLeft() {
        return left;
    }

    public void setLeft(AST left) {
        this.left = left;
    }

    public AST getRight() {
        return right;
    }

    public void setRight(AST right) {
        this.right = right;
    }
}

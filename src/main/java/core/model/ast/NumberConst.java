package core.model.ast;

public class NumberConst extends AST {

    private String number;

    public NumberConst(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

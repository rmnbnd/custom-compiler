package core.model.ast;

public class Display extends AST {

    private String value;

    public Display(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

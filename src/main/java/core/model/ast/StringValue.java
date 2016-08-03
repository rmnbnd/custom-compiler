package core.model.ast;

public class StringValue extends AST {

    private String data;

    public StringValue(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
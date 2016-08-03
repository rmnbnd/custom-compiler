package core.model.ast;

import java.util.ArrayList;
import java.util.List;

public class Body extends AST {

    private List<AST> lines = new ArrayList<>();

    public Body(List<AST> lines) {
        this.lines = lines;
    }

    public List<AST> getLines() {
        return lines;
    }

    public void setLines(List<AST> lines) {
        this.lines = lines;
    }
}

package core.model.symbol;

public class Symbol {

    private String name;
    private int type;
    private boolean used;

    public Symbol(String name, int typeId) {
        this.name = name;
        this.type = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}

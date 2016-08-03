package core.model.ast;

public enum Type {
    INTEGER(1);

    private int typeId;

    Type(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }

}

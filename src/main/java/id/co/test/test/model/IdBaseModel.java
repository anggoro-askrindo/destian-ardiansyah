package id.co.test.test.model;

public abstract class IdBaseModel <PK> {
    public IdBaseModel() { /* compiled code */ }

    public abstract PK getId();

    public abstract void setId(PK pk);
}

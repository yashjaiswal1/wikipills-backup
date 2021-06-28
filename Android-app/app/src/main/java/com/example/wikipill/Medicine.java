package com.example.wikipill;

public class Medicine {
    private int limit=20;
    private int taken;
    private int quantity=20;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTaken() {
        return this.taken;
    }

    public void setTaken(int taken2) {
        this.taken = taken2;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit2) {
        this.limit = limit2;
    }
}

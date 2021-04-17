package com.flipkart.bean;

public class SpecialUser {
    public SpecialUser() {
        this.type = -1;
        this.id = -1;
    }

    public Integer type;
    public Integer id;

    public SpecialUser(int type, int id) {
        this.type=type;
        this.id =id;
    }
}

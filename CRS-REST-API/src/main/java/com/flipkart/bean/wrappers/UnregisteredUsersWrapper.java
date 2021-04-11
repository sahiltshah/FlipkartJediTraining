package com.flipkart.bean.wrappers;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UnregisteredUsersWrapper {
    public ArrayList<String> getUnregisteredUsers() {
        return unregisteredUsers;
    }

    public void setUnregisteredUsers(ArrayList<String> unregisteredUsers) {
        this.unregisteredUsers = unregisteredUsers;
    }

    public UnregisteredUsersWrapper(ArrayList<String> unregisteredUsers) {
        this.unregisteredUsers = unregisteredUsers;
    }

    public UnregisteredUsersWrapper() {
    }

    public ArrayList<String> unregisteredUsers;

}

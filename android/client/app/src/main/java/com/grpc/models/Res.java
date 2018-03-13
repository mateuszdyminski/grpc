package com.grpc.models;

import java.util.List;

public class Res {
    private List<User> users;

    public Res() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}


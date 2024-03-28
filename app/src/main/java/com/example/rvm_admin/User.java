package com.example.rvm_admin;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    private int id;
    private final String username;
    private final String password;

    // Constructor
    public User( String username, String password) {

        this.username = username;
        this.password = password;

    }

    // Getters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }



    // Static method to parse JSON object and construct User object
    public static User fromJson(JSONObject jsonObject) throws JSONException {

        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");


        return new User(username, password);
    }
}

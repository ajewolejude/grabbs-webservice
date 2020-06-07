package com.boca.grabswebservice.payload;

public class Key {

    private String name;

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Key(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public Key() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

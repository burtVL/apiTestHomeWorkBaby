package org.example.api.models;

public class Unicorn {
    private  String name;
    private  String tail;

    public Unicorn(String name, String tail) {
        this.name = name;
        this.tail = tail;
    }

    public String getName() {
        return name;
    }

    public String getTail() {
        return tail;
    }

    @Override
    public String toString() {
        return "Unicorn{" +
                "name='" + name + '\'' +
                ", tail='" + tail + '\'' +
                '}';
    }

    public String toJson() {
        return "{" + "\"name\":\"" + name + "\", \"tail\":\"" + tail + "\"" + "}";

    }
}

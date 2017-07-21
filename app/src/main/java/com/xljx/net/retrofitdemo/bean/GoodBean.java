package com.xljx.net.retrofitdemo.bean;

/**
 * Created by vito-xa49 on 2017/7/21.
 */

public class GoodBean {
    private String name;
    private String id;

    public GoodBean(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "GoodBean{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}

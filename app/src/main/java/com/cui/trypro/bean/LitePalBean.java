package com.cui.trypro.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by cuiyang on 15/9/7.
 */
public class LitePalBean extends DataSupport {

    private String name;
    private String city;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return
                "name='" + name +
                        ", city='" + city +
                        '}';
    }
}

package com.example.eli.greendaotest.gen;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Eli on 2016/9/23.
 */
@Entity
public class Car {
    @Id
    private Long id;
    private String name;
    @Transient
    private int tempCount; // not persisted
    @Generated(hash = 1169192254)
    public Car(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 625572433)
    public Car() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

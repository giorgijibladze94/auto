package com.example.geolabedu.testn2.database;

import android.text.Editable;

import java.io.Serializable;

/**
 * Created by GeoLabOwl on 06.08.15.
 */
public class VehicleData implements Serializable {

    private String name;
    private String fname;
    private String image;
    private String nomeri;
    private String mail;

    public VehicleData(String name, String fname, String image,String mail,String nomeri) {
        this.name = name;
        this.fname = fname;
        this.image = image;
        this.mail=mail;
        this.nomeri=nomeri;

    }

    public String getNomeri() {
        return nomeri;
    }

    public void setNomeri(String nomeri) {
        this.nomeri = nomeri;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "VehicleData{" +
                "name='" + name + '\'' +
                ", fname='" + fname + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

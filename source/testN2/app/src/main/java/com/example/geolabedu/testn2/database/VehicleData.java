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
    private String mail,categ,modeli;

    public VehicleData(String name, String fname, String image,String mail,String nomeri,String categ,String modeli) {
        this.name = name;
        this.fname = fname;
        this.image = image;
        this.mail=mail;
        this.nomeri=nomeri;
        this.categ=categ;
        this.modeli=modeli;
    }

    public String getNomeri() {
        return nomeri;
    }

    public String getCateg() {
        return categ;
    }

    public void setCateg(String categ) {
        this.categ = categ;
    }

    public String getModeli() {
        return modeli;
    }

    public void setModeli(String modeli) {
        this.modeli = modeli;
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

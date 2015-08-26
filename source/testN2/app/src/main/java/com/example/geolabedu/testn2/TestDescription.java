package com.example.geolabedu.testn2;

import android.net.Uri;

/**
 * Created by Geolabedu on 8/3/15.
 */
public class TestDescription {
    private String modeli;
    private String weli;
    private String saxeli;
    private String tel;
    private Uri uri;

    public TestDescription(String modeli, String weli, String saxeli, String tel) {
        this.modeli = modeli;
        this.weli = weli;
        this.saxeli = saxeli;
        this.tel = tel;
    }

    public Uri getUri() {
        return uri;
    }

    public TestDescription(String modeli, String weli, String saxeli, String tel,Object uri) {
        this.modeli = modeli;
        this.weli = weli;
        this.saxeli = saxeli;
        this.tel = tel;
        this.uri= (Uri) uri;

    }

    public String getModeli() {
        return modeli;
    }

    public void setModeli(String modeli) {
        this.modeli = modeli;
    }

    public String getWeli() {
        return weli;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}

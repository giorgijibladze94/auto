package com.example.geolabedu.testn2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by GeoLabOwl on 04.08.15.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Caucasus_Auto_ServiceDB";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    private static final String CREATE_VEHICLE_TABLE =
            "CREATE TABLE " + VehiclContracts.VEHICLE_TABLE_NAME + "("
                    + VehiclContracts.VEHICLE_ID + " String,"
                    + VehiclContracts.VEHICLE_PERSON_NAME + " text not null, "
                    + VehiclContracts.VEHICLE_PERSON_FNAME + " text not null, "
                    + VehiclContracts.VEHICLE_PERSON_EMAIL + " text not null, "
                    + VehiclContracts.VEHICLE_PERSON_PHONE + " text not null, "
                    + VehiclContracts.VEHICLE_IMAGE + " not null )";

    /*
    sheiqmneba sheni teiblebi es xdeba ertxel tu table damateba mogvinda database_version shecvlac gviwevs
    */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_VEHICLE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}

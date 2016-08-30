package com.coolweather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**  数据库类
 * Created by M1308_000 on 2016/8/30.
 */
public class CoolWeatherOpenHelper extends SQLiteOpenHelper {

    private static final String SQL_NAME = "WeatherData.db";
    private static final int VERSION = 1;

    // Province “省”建表语句 及字段
    private static final String CREATE_PROVINCE = "create table Province (id integer primary key autoincrement, " +
            "province_name text, province_code text)";
    public static final String TB_PROVINCE = "Province";
    public static final String PROVINCE_ID = "id";
    public static final String PROVINCE_NAME = "province_name";
    public static final String PROVINCE_CODE = "province_code";

    // City “城市”建表语句 及字段
    private static final String CREATE_CITY = "create table City (id integer primary key autoincrement, " +
            "city_name text, city_code text, province_id integer)";
    public static final String TB_CITY = "City";
    public static final String CITY_ID = "id";
    public static final String CITY_NAME = "city_name";
    public static final String CITY_CODE = "city_code";
    public static final String CITY_PROVINCE_ID = "province_id";

    // county “县”建表语句 及字段
    private static final String CREATE_COUNTY = "create table County (id integer primary key autoincrement, " +
            "county_name text, county_code, city_id integer)";
    public static final String COUNTY_ID = "id";
    public static final String TB_COUNTY = "County";
    public static final String COUNTY_NAME = "county_name";
    public static final String COUNTY_CODE = "county_code";
    public static final String COUNTY_CITY_ID = "city_id";


    public CoolWeatherOpenHelper(Context context) {
        super(context, SQL_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

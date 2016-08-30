package com.coolweather.app.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.coolweather.app.db.CoolWeatherOpenHelper;
import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

import java.util.ArrayList;
import java.util.List;

/** 数据库操作类 公共类
 * Created by M1308_000 on 2016/8/30.
 */
public class CoolWeatherDB {

    private static CoolWeatherDB coolWeatherDB;
    private SQLiteDatabase db;

    // 将构造方法私有化
    private CoolWeatherDB(Context context) {
        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // 获取CoolWeatherDB的实例。
    public synchronized static CoolWeatherDB getInstance(Context context) {
        if (coolWeatherDB == null) {
            coolWeatherDB = new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }

    // 将Province实例存储到数据库。
    public void saveProvince(Province province) {
        if (province != null) {
            ContentValues values = new ContentValues();
            values.put(CoolWeatherOpenHelper.PROVINCE_NAME, province.getProvinceName());
            values.put(CoolWeatherOpenHelper.PROVINCE_CODE, province.getProvinceCode());
            db.insert(CoolWeatherOpenHelper.TB_PROVINCE, null, values);
        }
    }

    // 从数据库读取全国所有省份的信息
    public List<Province> loadProvince() {
        List<Province> provinceList = new ArrayList<>();
        Cursor cursor = db.query(CoolWeatherOpenHelper.TB_PROVINCE, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex(CoolWeatherOpenHelper.PROVINCE_ID)));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex(CoolWeatherOpenHelper.PROVINCE_NAME)));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex(CoolWeatherOpenHelper.PROVINCE_CODE)));
                provinceList.add(province);
            } while (cursor.moveToNext());
        }
        return provinceList;
    }

    // 保存城市数据
    public void saveCity(City city) {
        if (city != null) {
            ContentValues values = new ContentValues();
            values.put(CoolWeatherOpenHelper.CITY_NAME, city.getCityName());
            values.put(CoolWeatherOpenHelper.CITY_CODE, city.getCityCode());
            values.put(CoolWeatherOpenHelper.CITY_PROVINCE_ID, city.getProvinceId());
            db.insert(CoolWeatherOpenHelper.TB_CITY, null, values);
        }
    }

    // 根据省份id 查询省内城市
    public List<City> loadCities(int provinceId) {
        List<City> cityList = new ArrayList<>();
        Cursor cursor = db.query(CoolWeatherOpenHelper.TB_CITY, null, CoolWeatherOpenHelper.CITY_PROVINCE_ID +
                " = ? ", new String [] {String.valueOf(provinceId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex(CoolWeatherOpenHelper.CITY_ID)));
                city.setCityName(cursor.getString(cursor.getColumnIndex(CoolWeatherOpenHelper.CITY_NAME)));
                city.setCityCode(cursor.getString(cursor.getColumnIndex(CoolWeatherOpenHelper.CITY_CODE)));
                city.setProvinceId(provinceId);
                cityList.add(city);
            } while (cursor.moveToNext());
        }
        return cityList;
    }

    // 保存城镇数据
    public void saveCounty(County county) {
        if (county != null) {
            ContentValues values = new ContentValues();
            values.put(CoolWeatherOpenHelper.COUNTY_NAME, county.getCountyName());
            values.put(CoolWeatherOpenHelper.COUNTY_CODE, county.getCountyCode());
            values.put(CoolWeatherOpenHelper.COUNTY_CITY_ID, county.getCityId());
            db.insert(CoolWeatherOpenHelper.TB_COUNTY, null, values);
        }
    }

    // 根据城市id 查询该城市内城镇信息
    public List<County> loadCounties(int cityId) {
        List<County> countyList = new ArrayList<>();
        Cursor cursor = db.query(CoolWeatherOpenHelper.TB_COUNTY, null, CoolWeatherOpenHelper.COUNTY_CITY_ID +
        " = ? ", new String [] {String.valueOf(cityId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex(CoolWeatherOpenHelper.COUNTY_ID)));
                county.setCountyName(cursor.getString(cursor.getColumnIndex(CoolWeatherOpenHelper.COUNTY_NAME)));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex(CoolWeatherOpenHelper.COUNTY_CODE)));
                county.setCityId(cityId);
                countyList.add(county);
            } while (cursor.moveToNext());
        }
        return countyList;
    }
}

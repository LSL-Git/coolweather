package com.coolweather.app.util;

import android.text.TextUtils;

import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

/** 解析和处理服务器返回的数据
 * Created by M1308_000 on 2016/8/31.
 */
public class Utility {

    //解析和处理服务器返回的省级数据
    public synchronized static boolean handleProvinceResponse(CoolWeatherDB coolWeatherDB, String response) {
        if (!TextUtils.isEmpty(response)) {
            String [] allProvince = response.split(",");
            if (allProvince != null && allProvince.length > 0) {
                for (String str : allProvince) {
                    String [] array = str.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    //
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    // 解析和处理服务器返回的市级数据
    public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB, String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            String [] allCities = response.split(",");
            if (allCities != null && allCities.length > 0 ) {
                for (String str : allCities) {
                    String[] array = str.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    //
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    // 解析和处理服务器返回的县级数据
    public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB, String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            String [] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0) {
                for (String str : allCounties) {
                    String [] array = str.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    //
                    coolWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}

package com.coolweather.app.util;

/** 接口来回调服务返回的结果
 * Created by M1308_000 on 2016/8/31.
 */
public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}

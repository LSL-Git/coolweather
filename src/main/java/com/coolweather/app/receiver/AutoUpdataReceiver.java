package com.coolweather.app.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.coolweather.app.service.AutoUpdataService;

/** 负责8小时后调用更新类
 * Created by M1308_000 on 2016/9/3.
 */
public class AutoUpdataReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context, AutoUpdataService.class);
        context.startActivity(intent1);
    }
}

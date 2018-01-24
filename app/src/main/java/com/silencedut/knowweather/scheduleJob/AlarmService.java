package com.silencedut.knowweather.scheduleJob;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.silencedut.knowweather.remoteviews.RemoteViewsHelper;
import com.silencedut.router.Router;
import com.silencedut.weather_core.callback.EventCenter;

/**
 * Created by SilenceDut on 2016/10/20 .
 */

public class AlarmService extends Service implements EventCenter.NotificationStatus {


    @Override
    public void onCreate() {
        super.onCreate();
        Router.instance().register(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        RemoteViewsHelper.showNotification(this);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Router.instance().unregister(this);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onAllowNotification(boolean allow) {
        if (!allow) {
            RemoteViewsHelper.stopNotification(this);
        } else {
            RemoteViewsHelper.showNotification(this);
        }
    }


    @Override
    public void onUpdateNotification() {
        RemoteViewsHelper.updateNotification(this);
    }

    @Override
    public void clearAlarm() {
        RemoteViewsHelper.stopAlarm(this);
    }
}

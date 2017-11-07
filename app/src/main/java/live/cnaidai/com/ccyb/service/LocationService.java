package live.cnaidai.com.ccyb.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.orhanobut.logger.Logger;

import live.cnaidai.com.ccyb.App;
import live.cnaidai.com.ccyb.api.UserAPI;


/**
 * Created by ChristLu on 16/10/30.
 */

public class LocationService extends Service {

    private LocationClient mLocClient;

    private double preLatitude,preLongitude ;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initLocationListener();

    }

    private void initLocationListener() {
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(new MyLocationListenner());
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000*10*60);// 间隔时间需要设置成10分钟
        option.setAddrType("all");
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null) {
                return;
            }


            App.latitude = location.getLatitude() ;
            App.longitude = location.getLongitude() ;
            App.sCity = location.getCity();

            Logger.d("latitude:"+App.latitude+"    longitude: "+App.longitude);

            UserAPI.getInstance().uploadLocation();



        }

        public void onReceivePoi(BDLocation poiLocation) {

        }
    }


}

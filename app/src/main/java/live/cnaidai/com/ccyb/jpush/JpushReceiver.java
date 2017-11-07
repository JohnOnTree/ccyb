package live.cnaidai.com.ccyb.jpush;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;
import live.cnaidai.com.ccyb.App;
import live.cnaidai.com.ccyb.R;
import live.cnaidai.com.ccyb.base.CommonWebActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by ChristLu on 17/1/18.
 */

public class JpushReceiver extends BroadcastReceiver {
    private NotificationManager nm;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();


        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            //send the Registration Id to your server...
            Logger.d("[MyReceiver] 接收Registration Id : " + regId);
            App.sRegistrationId = regId;

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            //processCustomMessage(context, bundle);
            Logger.d("[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
//            receivingNotification(context,bundle);

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            Logger.d("[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
//            receivingNotification(context,bundle);
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Logger.d("[MyReceiver] 用户点击打开了通知");
            openNotify(context, bundle);
        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            Logger.d("[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            Logger.d("[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
        } else {
            Logger.d("[MyReceiver] Unhandled intent - " + intent.getAction());
        }
    }


    private void receivingNotification(Context context, Bundle bundle) {
        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        String url = "";
        try {
            JSONObject extrasJson = new JSONObject(extras);
            url = extrasJson.optString("url");
        } catch (Exception e) {
            Logger.d("Unexpected: extras is not a valid json");
            return;
        }

        NotificationManager manger = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        //为了版本兼容  选择V7包下的NotificationCompat进行构造
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        //Ticker是状态栏显示的提示
        builder.setTicker(message);
        //第一行内容  通常作为通知栏标题
        builder.setContentTitle(message);
        //可以点击通知栏的删除按钮删除
        builder.setAutoCancel(true);
        //系统状态栏显示的小图标
        builder.setSmallIcon(R.mipmap.logo);
        Intent intent = new Intent(context, CommonWebActivity.class);
        Bundle bundle1 = new Bundle();
        bundle1.putString("url", url);
        bundle1.putString("title", message);
        intent.putExtras(bundle1);
        PendingIntent pIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //点击跳转的intent
        builder.setContentIntent(pIntent);
        //通知默认的声音 震动 呼吸灯
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        Notification notification = builder.build();
        manger.notify(1, notification);


    }


    private void openNotify(Context context, Bundle bundle) {
        String title = bundle.getString(JPushInterface.EXTRA_ALERT);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        String url = "";
        String type = "";
        try {
            JSONObject extrasJson = new JSONObject(extras);
            url = extrasJson.optString("url");
            type = extrasJson.optString("type");
        } catch (Exception e) {
            Logger.d("Unexpected: extras is not a valid json");
            return;
        }
        if (!"2".equals(type)) {
            if ("1".equals(type)) {
                title = "约票详情";
            }else{
                title = "互动消息";
            }
            Intent intent = new Intent(context, CommonWebActivity.class);
            Bundle bundle1 = new Bundle();
            bundle1.putString("url", url);
            bundle1.putString("title", title);
            bundle1.putString("type", type);
            intent.putExtras(bundle1);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }


    }


}

package com.example.alfredorfernandes.agents.listener;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.example.alfredorfernandes.agents.R;
import com.example.alfredorfernandes.agents.activities.MenuActivity;

public class SmsListener extends BroadcastReceiver {

    private SharedPreferences preferences;
    private String number;
    private String message;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){

            final Bundle bundle = intent.getExtras();

            try {

                if (bundle != null) {

                    final Object[] pdusObj = (Object[]) bundle.get("pdus");

                    for (int i = 0; i < pdusObj.length; i++) {

                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                        String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                        String mobile = phoneNumber;
                        String body = currentMessage.getDisplayMessageBody();

                        number = mobile.replaceAll("\\s","");
                        message = body.replaceAll("\\s","+");

                        Log.i("SmsReceiver", "senderNum: "+ mobile + "; message: " + message);

                        //TO DO -- CHECK IF NUMBER EXIST IN AGENTS
                        /*if (senderMobile.equals(mobile)) {
                            showNotification(context, body);
                            break;
                        }*/

                        // Show Alert
                        Toast.makeText(context, "senderNum: "+ number + ", message: " + body, Toast.LENGTH_LONG).show();

                    } // end for loop
                } // bundle is null

            } catch (Exception e) {
                Log.e("SmsReceiver", "Exception smsReceiver" +e);

            }

        }
    }

    private void showNotification(Context context, String sms) {
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MenuActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("My notification")
                        .setContentText(sms);
        mBuilder.setContentIntent(contentIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }

}

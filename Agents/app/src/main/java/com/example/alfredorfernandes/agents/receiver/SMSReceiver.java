package com.example.alfredorfernandes.agents.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.example.alfredorfernandes.agents.R;
import com.example.alfredorfernandes.agents.dao.AgentDAO;

public class SMSReceiver extends BroadcastReceiver {

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
                        String body = currentMessage.getDisplayMessageBody();

                        Log.i("SmsReceiver", "senderNum: "+ phoneNumber + "; message: " + body);

                        AgentDAO dao = new AgentDAO();
                        if (dao.isAgent(phoneNumber)) {
                            Toast.makeText(context, "SMS just arrived !!!", Toast.LENGTH_SHORT).show();

                            MediaPlayer mp = MediaPlayer.create(context, R.raw.msg);
                            mp.start();

                        }

                        // Show Alert
                        Toast.makeText(context, "senderNum: "+ phoneNumber + ", message: " + body, Toast.LENGTH_LONG).show();

                    } // end for loop
                } // bundle is null

            } catch (Exception e) {
                Log.e("SmsReceiver", "Exception smsReceiver" +e);

            }

        }
    }
}

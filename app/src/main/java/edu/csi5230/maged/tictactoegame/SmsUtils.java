package edu.csi5230.maged.tictactoegame;

import android.telephony.SmsManager;

/**
 * Created by maged on 2017/11/11.
 */

public class SmsUtils {

    public static void sendMessage(long phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(String.valueOf(phoneNumber), null, message, null, null);
    }
}

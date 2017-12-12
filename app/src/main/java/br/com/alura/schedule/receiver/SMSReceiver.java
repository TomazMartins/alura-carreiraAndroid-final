package br.com.alura.schedule.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;
import android.widget.Toast;

import br.com.alura.schedule.R;
import br.com.alura.schedule.dao.StudentDAO;

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive( Context context, Intent intent ) {
        Object[] pdus = (Object[]) intent.getSerializableExtra( "pdus" );
        byte[] pdu = (byte[]) pdus[ 0 ];
        String format = (String) intent.getSerializableExtra( "format" );

        SmsMessage sms = SmsMessage.createFromPdu( pdu, format );

        String phone = sms.getDisplayOriginatingAddress();
        StudentDAO studentDao = new StudentDAO( context );
        if( studentDao.isStudent( phone ) ) {
            Toast.makeText( context, "Chegou um SMS de Student!", Toast.LENGTH_SHORT ).show();
            MediaPlayer mp = MediaPlayer.create( context, R.raw.msg );
            mp.start();
        }
        studentDao.close();
    }
}

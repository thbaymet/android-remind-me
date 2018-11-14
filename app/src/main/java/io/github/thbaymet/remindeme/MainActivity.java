package io.github.thbaymet.remindeme;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import io.github.thbaymet.remindeme.services.AlertIntentService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fireAlertIntentService();
    }

    protected void fireAlertIntentService() {
        Intent myIntent = new Intent(getApplicationContext(), AlertIntentService.class);
        myIntent.setAction(getString(R.string.REMIND_ME_ALERT_ACTION));
        PendingIntent pIntent = PendingIntent
                .getService(getApplicationContext(),  0, myIntent, 0);
        AlarmManager aManager = (AlarmManager)getApplicationContext()
                .getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 5); // first time
        long frequency= TimeUnit.MINUTES.toMillis(60); // in ms
        aManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), frequency, pIntent);
    }
}

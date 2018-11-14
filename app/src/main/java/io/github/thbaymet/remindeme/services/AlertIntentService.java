package io.github.thbaymet.remindeme.services;

import android.app.AlertDialog;
import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.view.WindowManager;

import io.github.thbaymet.remindeme.R;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class AlertIntentService extends IntentService {

    private Handler mHandler;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public AlertIntentService() {
        super("AlertIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        final String action = intent.getAction();
        if (action.equalsIgnoreCase(getString(R.string.REMIND_ME_ALERT_ACTION))) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    fireAlertDialog();
                }
            });
        }
    }

    protected void fireAlertDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
        alertDialogBuilder.setTitle(R.string.app_name);
        alertDialogBuilder.setMessage("The only way to get smarter is by playing a smarter opponent.");
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();
    }
}

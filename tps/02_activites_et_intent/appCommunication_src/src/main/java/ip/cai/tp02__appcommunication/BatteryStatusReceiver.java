package ip.cai.tp02__appcommunication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.BatteryManager;
import android.util.Log;

public class BatteryStatusReceiver extends BroadcastReceiver {

    /**
     * Activité que l'on veut arrêter
     */
    Activity monitoredActivity;

    /**
     * Seuil mini batterie
     */
    int seuilMini;

    public BatteryStatusReceiver(Activity monitoredActivity, int seuilMini) {
        this.monitoredActivity = monitoredActivity;
        this.seuilMini = seuilMini;
    }

    @Override
    public void onReceive(final Context context, Intent intent) {

        // Obtention % de batterie restant
        Intent batteryStatus = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryPct = (level / (float)scale) * 100;
        Log.i("BATTERY", "" + batteryPct + "%");

        // Si seui l mini atteint on prévient l'utilisateur
        // avant d'arrêter l'application
        if(batteryPct < seuilMini)  {
            // Message pour prévenir l'utilisateur que l'application va s'arrêter
            new AlertDialog.Builder(context).setTitle("Avertissement Niveau Batterie")
                    .setMessage("Votre batterie est à " + batteryPct + "% l'application va être arrêtétée.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Arrêt activité référencée
                            monitoredActivity.finish();
                            // OU bien Arrêt de l'application
                            int id = android.os.Process.myPid();
                            android.os.Process.killProcess(id);
                        }
                    })
                    //.setCancelable(false)
                    .create().show();
            }

        }

}

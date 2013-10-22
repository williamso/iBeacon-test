package com.mobiorama.ibeaconTest;


import com.radiusnetworks.ibeacon.IBeaconManager;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity  {
        protected static final String TAG = "MainActivity";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                Log.d(TAG, "oncreate");
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                verifyBluetooth();
                
        }
        public void onRangingClicked(View view) {
                Intent myIntent = new Intent(this, RangingActivity.class);
                this.startActivity(myIntent);
        }
        public void onMonitoringClicked(View view) {
                Intent myIntent = new Intent(this, MonitoringActivity.class);
                this.startActivity(myIntent);
        }

        private void verifyBluetooth() {

                try {
                        if (!IBeaconManager.getInstanceForApplication(this).checkAvailability()) {
                                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                builder.setTitle("Bluetooth not enabled");                        
                                builder.setMessage("Please enable bluetooth in settings and restart this application.");
                                builder.setPositiveButton(android.R.string.ok, null);
                                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

                                        @Override
                                        public void onDismiss(DialogInterface dialog) {
                                                finish();
                                    System.exit(0);                                        
                                        }
                                        
                                });
                                builder.show();

                        }                        
                }
                catch (RuntimeException e) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Bluetooth LE not available");                        
                        builder.setMessage("Sorry, this device does not support Bluetooth LE.");
                        builder.setPositiveButton(android.R.string.ok, null);
                        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

                                @Override
                                public void onDismiss(DialogInterface dialog) {
                                        finish();
                            System.exit(0);                                        
                                }
                                
                        });
                        builder.show();
                        
                }
                
        }        

}
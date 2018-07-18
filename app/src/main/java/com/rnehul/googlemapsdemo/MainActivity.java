package com.rnehul.googlemapsdemo;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final int ERROR_DIALOG_REQUEST=9001;

    private Button btnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(isServiceOk()){
            init();
        }else{

        }
    }

    private void init(){
        btnMap=(Button)findViewById(R.id.btnMaps);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean isServiceOk(){
        Log.d(TAG, "isServiceOk: checking version of google play service");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);

        if(available==ConnectionResult.SUCCESS){
            //everything is fine
            Log.d(TAG, "isServiceOk: google play services is working fine");
            return true;
        }else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occur but we can fix it
            Log.d(TAG, "isServiceOk: error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(this,available,ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "Cant able to create maps request", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}

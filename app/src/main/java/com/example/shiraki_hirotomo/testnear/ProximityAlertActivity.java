package com.example.shiraki_hirotomo.testnear;

import android.app.Activity;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ProximityAlertActivity extends Activity {
    private String tag = this.getClass().getSimpleName();
    private TextView tv;

    protected void onNewIntent(Intent intent){
        Log.d("PAAの","onnewintentに通った");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_alert);


        Log.d(tag,"PA/onCreate starts.");

        tv = (TextView)findViewById(R.id.tv1);

        showStatus();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(tag, "onDestroy starts.");
        showStatus();
    }

    private void showStatus(){
        Intent intent = getIntent();
        boolean flag = intent.getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING,false);
        tv.setText(Boolean.toString(flag));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.proximity_alert, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

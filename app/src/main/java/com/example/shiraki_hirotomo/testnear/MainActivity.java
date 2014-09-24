package com.example.shiraki_hirotomo.testnear;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity implements LocationListener{
    private String tag = this.getClass().getSimpleName();
    private LocationManager mgr;
    double nowLatitude, nowLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(tag, "onCreate starts.");

        // 自由の女神 location=(40.690466,-74.045019)
        //double latitude = 40.690466;
        //double longitude = -74.045019;
        //横浜三井ビルディング
        double latitude = 35.463129;//35.463129;
        double longitude = 139.624933;//139.624933;
        float radius = 1000000000F;// m   ang = 10km*360度/40000km =0.09度に相当
        long expiration = -1L;
        PendingIntent pendingIntent = createPendingIntent();



        mgr=(LocationManager)getSystemService(Context.LOCATION_SERVICE);


        // Criteriaオブジェクトを生成
        Criteria criteria = new Criteria();

        // Accuracyを指定(低精度)
        //criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setAccuracy(Criteria.ACCURACY_MEDIUM);

        // PowerRequirementを指定(低消費電力)
        //criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setPowerRequirement(Criteria.POWER_MEDIUM);

        // ロケーションプロバイダの取得
        String provider = mgr.getBestProvider(criteria, true);
        //String provider = mLocationManager.getGps;

        // LocationListenerを登録
        mgr.requestLocationUpdates(provider, 0, 0, this);

        Log.d("latitude(Main)",String.valueOf(latitude));
        Log.d("longitude(Main)",String.valueOf(longitude));
        Log.d("radius(Main)",String.valueOf(radius));
        Log.d("expiration(Main)",String.valueOf(expiration));
        Log.d("pending(Main)",pendingIntent.toString());
        mgr.addProximityAlert(latitude, longitude, radius, expiration, pendingIntent);

        Log.d(tag, "onCreate finished.");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(tag,"onDestroy starts.");
    }

    private PendingIntent createPendingIntent(){
        Intent intent = new Intent(this, ProximityAlertActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Log.d(tag,"create pendingintent");
        return pendingIntent;
    }



    //onLocationChangedは一番最初も読んでいるのかどうか分からない。
    @Override
    public void onLocationChanged(Location location) {

        Log.d("lati","Latitude:"+location.getLatitude());
        Log.d("longi","Longitude:"+location.getLongitude());
//
//        // 緯度の表示
//        nowLatitude = location.getLatitude();
//        TextView tv_lat = (TextView) findViewById(R.id.latitude);
//        tv_lat.setText("Latitude:"+location.getLatitude());
//
//        // 経度の表示
//        nowLongitude = location.getLongitude();
//        TextView tv_lng = (TextView) findViewById(R.id.longitude);
//        tv_lng.setText("Longitude:"+location.getLongitude());
//
//
//        // 駅のを返すAPIのURLを格納する（2）
//        Log.d("now緯度経度の中身(onLocationChanged)",nowLongitude+":"+nowLatitude);
//        Log.d("リクエスト内容(onLocationChanged)","http://express.heartrails.com/api/json?method=getStations&x="+nowLongitude+"&y="+nowLatitude);
//        bundle.clear();
//        bundle.putString("url", "http://express.heartrails.com/api/json?method=getStations&x="+nowLongitude+"&y="+nowLatitude);
//        // LoaderManagerの初期化（3）
//        getLoaderManager().destroyLoader(0);
//        getLoaderManager().initLoader(0, bundle, this);
//
//        if(station_list != null) {
//            for (Station station : station_list) {
//                Log.d("loc駅/set駅", station.getName() + "/" + test_depaturestationname);
//                if (station.getName().equals(test_depaturestationname)) {
//                    //Toast.makeText(this, test_depaturestationname + "にきた", Toast.LENGTH_SHORT).show();
//                    Log.d("station_listのループ", test_depaturestationname+"にきた。");
//                    test_notificationtext.setText(station.getName());
//                }
//            }
//        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

package andexammap.ver6.c32_map;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import andexammap.ver6.R;

public class CameraMap extends FragmentActivity {
    GoogleMap mMap;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cameramap);

        FragmentManager fm = getFragmentManager();
        MapFragment frag = (MapFragment)fm.findFragmentById(R.id.map);
        frag.getMapAsync(new OnMapReadyCallback() {
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
            }
        });
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.bld63:
                setMapPosition(37.519576, 126.940245, 14);
                break;
            case R.id.chuncheon:
                setMapPosition(37.881311, 127.729968, 16);
                break;
            case R.id.kangwon:
                setMapPosition(37.885269, 127.729592, 16);
                break;
            case R.id.operahouse:
                setMapPosition(-33.8568, 151.2152, 12);
                break;
        }
    }

    void setMapPosition(double lat, double lng, float zlevel) {
        LatLng pt = new LatLng(lat, lng);
        CameraPosition cp = new CameraPosition.Builder().target((pt)).zoom(zlevel).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
    }
}
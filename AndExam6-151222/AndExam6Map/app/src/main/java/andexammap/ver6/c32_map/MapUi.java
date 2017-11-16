package andexammap.ver6.c32_map;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import andexammap.ver6.R;

public class MapUi extends FragmentActivity {
    GoogleMap mMap;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapui);

        FragmentManager fm = getFragmentManager();
        MapFragment frag = (MapFragment)fm.findFragmentById(R.id.map);
        frag.getMapAsync(new OnMapReadyCallback() {
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                LatLng pt = new LatLng(37.519576, 126.940245);
                CameraPosition cp = new CameraPosition.Builder().target((pt)).
                        zoom(16).build();
                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
            }
        });

         CheckBox chkGesture = (CheckBox) findViewById(R.id.chkgesture);
         chkGesture.setOnCheckedChangeListener(mCheckChange);

         CheckBox chkCompass = (CheckBox) findViewById(R.id.chkcompass);
         chkCompass.setOnCheckedChangeListener(mCheckChange);

         CheckBox chkZoom = (CheckBox) findViewById(R.id.chkzoom);
         chkZoom.setOnCheckedChangeListener(mCheckChange);
     }

    CompoundButton.OnCheckedChangeListener mCheckChange =
            new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    UiSettings settings = mMap.getUiSettings();
                    switch (buttonView.getId()) {
                        case R.id.chkgesture:
                            settings.setAllGesturesEnabled(isChecked);
                            break;
                        case R.id.chkcompass:
                            settings.setCompassEnabled(isChecked);
                            break;
                        case R.id.chkzoom:
                            settings.setZoomControlsEnabled(isChecked);
                            break;
                    }
                }
            };

}
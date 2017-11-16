package andexammap.ver6.c32_map;

import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;

import andexammap.ver6.R;

public class MapShape extends FragmentActivity {
    GoogleMap mMap;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.googlemaptest);

        FragmentManager fm = getFragmentManager();
        MapFragment frag = (MapFragment)fm.findFragmentById(R.id.map);
        frag.getMapAsync(new OnMapReadyCallback() {
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                double lat = 37.5759;
                double lng = 126.9769;

                LatLng pt = new LatLng(lat, lng);
                CameraPosition cp = new CameraPosition.Builder()
                        .target((pt)).zoom(15).build();
                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));

                MarkerOptions marker = new MarkerOptions().position(pt)
                        .title("광화문").snippet("경복궁의 정문입니다.");
                mMap.addMarker(marker);

                CircleOptions circle = new CircleOptions().center(pt)
                        .radius(100).strokeColor(Color.BLACK);
                mMap.addCircle(circle);

                double d = 0.005;
                PolylineOptions polyline = new PolylineOptions()
                        .add(new LatLng(lat - d, lng - d),new LatLng(lat - d, lng + d)
                                ,new LatLng(lat + d, lng + d),new LatLng(lat + d, lng - d)
                                ,new LatLng(lat - d, lng - d)).color(Color.BLUE).width(15);
                mMap.addPolyline(polyline);
                PolygonOptions polygon = new PolygonOptions()
                        .add(new LatLng(lat + d, lng), new LatLng(lat, lng + d),
                                new LatLng(lat - d, lng), new LatLng(lat, lng - d))
                        .strokeColor(Color.RED).strokeWidth(10);
                mMap.addPolygon(polygon);
            }
        });
    }
}
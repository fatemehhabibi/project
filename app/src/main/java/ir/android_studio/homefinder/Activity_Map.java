package ir.android_studio.homefinder;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Activity_Map extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng iran = new LatLng(32.7313226, 54.5899795);
        mMap.addMarker(new MarkerOptions().position(iran).title("Marker in Iran"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(iran));

       // onKeyLongPress()

        //این خط پایینی رو خوذم اضافه کردم و بالایی ها مال خود اندروید استودیو ست
//        public double location() {
//
//        Location loc = mMap.getMyLocation();
//       double width = loc.getLatitude();     //عرض جغرافیایی
//       double heigh = loc.getLongitude();     //طول جغرافیایی
//
//        return(width + " ," + heigh);
//        }
    }
}

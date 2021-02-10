package com.antixiansoftware.travelling.Map;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
//import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.antixiansoftware.travelling.Main.MainActivity;
import com.antixiansoftware.travelling.R;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


public class MapActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMarkerDragListener
{

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    int PROXIMITY_RADIUS = 10000;
    double latitude, longitude;
    double end_latitude, end_longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);



        SearchView searchView = (SearchView) findViewById( R.id.searchview );
        searchView.setQueryHint(Html.fromHtml("<font color = #ffffff>" + getResources().getString(R.string.queryhint) + "</font>"));


        TapTargetView.showFor(this,                 // `this` is an Activity
                TapTarget.forView(findViewById(R.id.get_ride), "lets get your comfort ride!!","Lets Go!!").transparentTarget(false).tintTarget(false)

        );

        TapTargetView.showFor(this,                 // `this` is an Activity
                TapTarget.forView(findViewById(R.id.searchview), "Search your destination","search and select a pink arrow and drop in your destination and start your journey with us!!").transparentTarget(false).tintTarget(false)

        );



        Button bt = (Button) findViewById(R.id.get_ride);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        //Check if Google Play Services Available or not
        if (!CheckGooglePlayServices()) {
            Log.d("onCreate", "Finishing test case since Google Play Services are not available");
            finish();
        }
        else {
            Log.d("onCreate","Google Play Services available.");
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    private boolean CheckGooglePlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        0).show();
            }
            return false;
        }
        return true;
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

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

        mMap.setOnMarkerDragListener(this);
        mMap.setOnMarkerClickListener(this);

    }



    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    public void onClick(View v)
    {
        Object dataTransfer[] = new Object[2];
        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();


        switch(v.getId()) {
            case R.id.B_search: {
                SearchView searchView = (SearchView) findViewById(R.id.searchview);
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;
                MarkerOptions markerOptions = new MarkerOptions();
                Log.d("location = ", location);

                if (!location.equals("")) {
                    Geocoder geocoder = new Geocoder(this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 5);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (addressList != null) {
                        for (int i = 0; i < addressList.size(); i++) {
                            Address myAddress = addressList.get(i);
                            LatLng latLng = new LatLng(myAddress.getLatitude(), myAddress.getLongitude());
                            markerOptions.position(latLng);
                            mMap.addMarker(markerOptions);
                            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                        }
                    }

                }
            }
            break;
//            case R.id.B_hospital:
//                mMap.clear();
//                String hospital = "hospital";
//                String url = getUrl(latitude, longitude, hospital);
//
//                dataTransfer[0] = mMap;
//                dataTransfer[1] = url;
//
//                getNearbyPlacesData.execute(dataTransfer);
//                Toast.makeText(MapActivity.this, "Showing Nearby Hospitals", Toast.LENGTH_LONG).show();
//                break;
//
//            case R.id.B_restaurant:
//                mMap.clear();
//                dataTransfer = new Object[2];
//                String restaurant = "restaurant";
//                url = getUrl(latitude, longitude, restaurant);
//                getNearbyPlacesData = new GetNearbyPlacesData();
//                dataTransfer[0] = mMap;
//                dataTransfer[1] = url;
//
//                getNearbyPlacesData.execute(dataTransfer);
//                Toast.makeText(MapActivity.this, "Showing Nearby restaurant", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.B_school:
//                mMap.clear();
//                String school = "school";
//                dataTransfer = new Object[2];
//                url = getUrl(latitude, longitude, school);
//                getNearbyPlacesData = new GetNearbyPlacesData();
//                dataTransfer[0] = mMap;
//                dataTransfer[1] = url;
//
//                getNearbyPlacesData.execute(dataTransfer);
//                Toast.makeText(MapActivity.this, "Showing Nearby Hospitals", Toast.LENGTH_LONG).show();
//                break;
//
//            case R.id.B_to:
//                dataTransfer = new Object[3];
//                url = getDirectionsUrl();
//                GetDirectionsData getDirectionsData = new GetDirectionsData();
//                dataTransfer[0] = mMap;
//                dataTransfer[1] = url;
//                dataTransfer[2] = new LatLng(end_latitude, end_longitude);
//                getDirectionsData.execute(dataTransfer);
//
//                break;


        }
    }

    private String getDirectionsUrl()
    {
        StringBuilder googleDirectionsUrl = new StringBuilder("https://maps.googleapis.com/maps/api/directions/json?");
        googleDirectionsUrl.append("origin="+latitude+","+longitude);
        googleDirectionsUrl.append("&destination="+end_latitude+","+end_longitude);
        googleDirectionsUrl.append("&key="+"AIzaSyBC3dFuU7wm2Ae2c3wnnnG_YwQJz66VJzI");

        return googleDirectionsUrl.toString();
    }

    private String getUrl(double latitude, double longitude, String nearbyPlace)
    {
        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);
        googlePlacesUrl.append("&type=" + nearbyPlace);
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + "AIzaSyBC3dFuU7wm2Ae2c3wnnnG_YwQJz66VJzI");
        Log.d("getUrl", googlePlacesUrl.toString());
        return (googlePlacesUrl.toString());
    }




    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }



    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("onLocationChanged", "entered");

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        latitude = location.getLatitude();
        longitude = location.getLongitude();


        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.draggable(true);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));


        Toast.makeText(MapActivity.this,"Your Current Location", Toast.LENGTH_LONG).show();


        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            Log.d("onLocationChanged", "Removing Location Updates");
        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.setDraggable(true);
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        end_latitude = marker.getPosition().latitude;
        end_longitude =  marker.getPosition().longitude;

        Log.d("end_lat",""+end_latitude);
        Log.d("end_lng",""+end_longitude);
    }
}











//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.content.IntentSender;
//import android.content.pm.PackageManager;
//import android.graphics.Color;
////import android.support.v4.app.ActivityCompat;
////import android.support.v4.app.FragmentActivity;
//import android.location.Address;
//import android.location.Geocoder;
//import android.location.Location;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.SearchView;
//import androidx.core.app.ActivityCompat;
//
//import com.google.android.gms.common.api.ResolvableApiException;
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationCallback;
//import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.LocationResult;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.location.LocationSettingsRequest;
//import com.google.android.gms.location.LocationSettingsResponse;
//import com.google.android.gms.location.SettingsClient;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.BitmapDescriptorFactory;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.android.gms.maps.model.Polyline;
//import com.google.android.gms.maps.model.PolylineOptions;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.android.libraries.places.api.Places;
//import com.google.android.libraries.places.api.model.AutocompletePrediction;
//import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
//import com.google.android.libraries.places.api.net.PlacesClient;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.List;
////
////import Modules.DirectionFinder;
////import Modules.DirectionFinderListener;
////import Modules.Route;
//
//public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, DirectionFinderListener {
//
//    private GoogleMap mMap;
//    private Button btnFindPath;
//    private EditText etOrigin;
//    private EditText etDestination;
//    private List<Marker> originMarkers = new ArrayList<>();
//    private List<Marker> destinationMarkers = new ArrayList<>();
//    private List<Polyline> polylinePaths = new ArrayList<>();
//    private ProgressDialog progressDialog;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_map);
//
//          Button bt = (Button) findViewById(R.id.get_ride);
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MapActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//
//            }
//        });
//
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//
//        btnFindPath = (Button) findViewById(R.id.btnFindPath);
//        etOrigin = (EditText) findViewById(R.id.etOrigin);
//        etDestination = (EditText) findViewById(R.id.etDestination);
//
//
//        btnFindPath.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendRequest();
//            }
//        });
//    }
//
//    private void sendRequest() {
//        String origin = etOrigin.getText().toString();
//        String destination = etDestination.getText().toString();
//        if (origin.isEmpty()) {
//            Toast.makeText(this, "Please enter origin address!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (destination.isEmpty()) {
//            Toast.makeText(this, "Please enter destination address!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        try {
//            new DirectionFinder(this, origin, destination).execute();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//
//        LatLng latLng = new LatLng(28.7040873, 77.1683671);
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
//        originMarkers.add(mMap.addMarker(new MarkerOptions()
//                .title("Belgaum")
//                .position(latLng)));
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        mMap.setMyLocationEnabled(true);
//    }
//
//
//    @Override
//    public void onDirectionFinderStart() {
//        progressDialog = ProgressDialog.show(this, "Please wait.",
//                "Finding direction..!", true);
//
//        if (originMarkers != null) {
//            for (Marker marker : originMarkers) {
//                marker.remove();
//            }
//        }
//
//        if (destinationMarkers != null) {
//            for (Marker marker : destinationMarkers) {
//                marker.remove();
//            }
//        }
//
//        if (polylinePaths != null) {
//            for (Polyline polyline:polylinePaths ) {
//                polyline.remove();
//            }
//        }
//    }
//
//    @Override
//    public void onDirectionFinderSuccess(List<Route> routes) {
//        progressDialog.dismiss();
//        polylinePaths = new ArrayList<>();
//        originMarkers = new ArrayList<>();
//        destinationMarkers = new ArrayList<>();
//
//        for (Route route : routes) {
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 16));
//            ((TextView) findViewById(R.id.tvDuration)).setText(route.duration.text);
//            ((TextView) findViewById(R.id.tvDistance)).setText(route.distance.text);
//
//            originMarkers.add(mMap.addMarker(new MarkerOptions()
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue))
//                    .title(route.startAddress)
//                    .position(route.startLocation)));
//            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green))
//                    .title(route.endAddress)
//                    .position(route.endLocation)));
//
//            PolylineOptions polylineOptions = new PolylineOptions().
//                    geodesic(true).
//                    color(Color.BLUE).
//                    width(10);
//
//            for (int i = 0; i < route.points.size(); i++)
//                polylineOptions.add(route.points.get(i));
//
//            polylinePaths.add(mMap.addPolyline(polylineOptions));
//        }
//    }
//}
//





//
//package com.antixiansoftware.travelling;
//
//        import android.Manifest;
//        import android.annotation.SuppressLint;
//        import android.app.ProgressDialog;
//        import android.content.Intent;
//        import android.content.IntentSender;
//        import android.content.pm.PackageManager;
//        import android.graphics.Color;
//        import android.location.Address;
//        import android.location.Geocoder;
//        import android.location.Location;
//        import android.os.Bundle;
//
//        import com.google.android.gms.common.api.ResolvableApiException;
//        import com.google.android.gms.location.FusedLocationProviderClient;
//        import com.google.android.gms.location.LocationCallback;
//        import com.google.android.gms.location.LocationRequest;
//        import com.google.android.gms.location.LocationResult;
//        import com.google.android.gms.location.LocationServices;
//        import com.google.android.gms.location.LocationSettingsRequest;
//        import com.google.android.gms.location.LocationSettingsResponse;
//        import com.google.android.gms.location.SettingsClient;
//        import com.google.android.gms.maps.CameraUpdateFactory;
//        import com.google.android.gms.maps.GoogleMap;
//        import com.google.android.gms.maps.OnMapReadyCallback;
//        import com.google.android.gms.maps.SupportMapFragment;
//        import com.google.android.gms.maps.model.BitmapDescriptorFactory;
//        import com.google.android.gms.maps.model.LatLng;
//        import com.google.android.gms.maps.model.Marker;
//        import com.google.android.gms.maps.model.MarkerOptions;
//        import com.google.android.gms.maps.model.Polyline;
//        import com.google.android.gms.maps.model.PolylineOptions;
//        import com.google.android.gms.tasks.OnCompleteListener;
//        import com.google.android.gms.tasks.OnFailureListener;
//        import com.google.android.gms.tasks.OnSuccessListener;
//        import com.google.android.gms.tasks.Task;
//        import com.google.android.libraries.places.api.Places;
//        import com.google.android.libraries.places.api.model.AutocompletePrediction;
//        import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
//        import com.google.android.libraries.places.api.net.PlacesClient;
//        import com.google.android.material.floatingactionbutton.FloatingActionButton;
//        import com.google.android.material.snackbar.Snackbar;
//
//        import androidx.annotation.NonNull;
//        import androidx.annotation.Nullable;
//        import androidx.appcompat.app.AppCompatActivity;
//        import androidx.appcompat.widget.SearchView;
//        import androidx.appcompat.widget.Toolbar;
//        import androidx.core.app.ActivityCompat;
//        import androidx.fragment.app.FragmentActivity;
//
//        import android.view.View;
//        import android.widget.Button;
//        import android.widget.EditText;
//        import android.widget.RelativeLayout;
//        import android.widget.TextView;
//        import android.widget.Toast;
//
//        import java.io.IOException;
//        import java.io.UnsupportedEncodingException;
//        import java.util.ArrayList;
//        import java.util.List;
//        import java.util.Map;
//
//        import static androidx.appcompat.widget.SearchView.*;
//
//public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, DirectionFinderListener {
//
//    private GoogleMap mMap;
//    private FusedLocationProviderClient fusedLocationProviderClient;
//    private PlacesClient placesClient;
//    private List<AutocompletePrediction> predictionList;
//    private List<Marker> originMarkers = new ArrayList<>();
//    private List<Marker> destinationMarkers = new ArrayList<>();
//    private List<Polyline> polylinePaths = new ArrayList<>();
//    private ProgressDialog progressDialog;
//    private Location mLastKnownLocation;
//    private LocationCallback locationCallback;
//    private View mapView;
//    private Button btnFind;
//    private SearchView searchView;
//    private EditText currentLocation;
//    private EditText destination;
//    private Button btnFindPath;
//    private final float DEFAULT_ZOOM = 18;
//    private Route route;
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_map);
//
//        Button bt = (Button) findViewById(R.id.btn_find);
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MapActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//
//            }
//        });
//
//
//        searchView = findViewById(R.id.search_view);
//        btnFind = findViewById(R.id.btn_find);
//        currentLocation = findViewById(R.id.current_location);
//        destination = findViewById(R.id.destination);
//        btnFindPath = (Button) findViewById(R.id.btnFindPath);
//
//
//
//
//
//
//
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//        mapView = mapFragment.getView();
//
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MapActivity.this);
//        Places.initialize(MapActivity.this,"AIzaSyBkzTjylXdAYApjeNUyXavotbAJD_46HM4");
//        placesClient = Places.createClient(this);
//        AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();
//
////        searchView
//
//        searchView.setQueryHint("Search a Place");
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                String location = searchView.getQuery().toString();
//                List<Address> addressList = null;
//                if (location != null || !location.equals("")){
//                    Geocoder geocoder = new Geocoder(MapActivity.this);
//                    try {
//                        addressList = geocoder.getFromLocationName(location, 1);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    Address address = addressList.get(0);
//                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
//                    mMap.addMarker(new MarkerOptions().position(latLng).title(location));
//                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
//
//
//                }
//
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//
//
//        btnFindPath.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendRequest();
//            }
//        });
//    }
//
//    private void sendRequest() {
//        String origin = currentLocation.getText().toString();
//        String edestination = destination.getText().toString();
//        if (origin.isEmpty()) {
//            Toast.makeText(this, "Please enter origin address!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (edestination.isEmpty()) {
//            Toast.makeText(this, "Please enter destination address!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        try {
//            new DirectionFinder(this, origin, edestination).execute();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//
//    @SuppressLint("MissingPermission")
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//
//        mMap = googleMap;
//        mMap.setMyLocationEnabled(true);
//        mMap.getUiSettings().setMyLocationButtonEnabled(true);
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//
////        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//
//        if (mapView != null && mapView.findViewById(Integer.parseInt("1")) != null) {
//            View locationButton = ((View) mapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
//            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
//            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
//            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
//            layoutParams.setMargins(0, 0, 40, 180);
//
//
//        }
//
//        //check if gps is enabled or not and then request user to enable it
//
//        LocationRequest locationRequest = LocationRequest.create();
//        locationRequest.setInterval(10000);
//        locationRequest.setFastestInterval(5000);
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//
//        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
//
//        SettingsClient settingsClient = LocationServices.getSettingsClient(MapActivity.this);
//        Task<LocationSettingsResponse> task = settingsClient.checkLocationSettings(builder.build());
//
//        task.addOnSuccessListener(MapActivity.this, new OnSuccessListener<LocationSettingsResponse>() {
//            @Override
//            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
//
//            }
//        });
//
//        task.addOnFailureListener(MapActivity.this, new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                if (e instanceof ResolvableApiException) {
//                    ResolvableApiException resolvable = (ResolvableApiException) e;
//                    try {
//                        resolvable.startResolutionForResult(MapActivity.this, 51);
//                    } catch (IntentSender.SendIntentException ex) {
//                        ex.printStackTrace();
//                    }
//
//                }
//
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 51){
//            if (resultCode == RESULT_OK){
//                getDeviceLocation();
//            }
//        }
//    }
//
//    @SuppressLint("MissingPermission")
//    private void getDeviceLocation(){
//        fusedLocationProviderClient.getLastLocation()
//                .addOnCompleteListener(new OnCompleteListener<Location>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Location> task) {
//                        if (task.isSuccessful()){
//                            mLastKnownLocation = task.getResult();
//                            if (mLastKnownLocation != null){
//                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLastKnownLocation.getLatitude(),mLastKnownLocation.getLongitude()),DEFAULT_ZOOM));
//                            } else{
//                                final LocationRequest locationRequest = LocationRequest.create();
//                                locationRequest.setInterval(10000);
//                                locationRequest.setFastestInterval(5000);
//                                locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//                                locationCallback = new LocationCallback(){
//                                    @Override
//                                    public void onLocationResult(LocationResult locationResult) {
//                                        super.onLocationResult(locationResult);
//                                        if (locationResult == null){
//                                            return;
//                                        }
//                                        mLastKnownLocation = locationResult.getLastLocation();
//                                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLastKnownLocation.getLatitude(),mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
//                                        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
//                                    }
//                                };
//                                fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
//                            }
//                        } else{
//                            Toast.makeText(MapActivity.this, "Unable to get last Location", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//
//    }
//
//    @Override
//    public void onDirectionFinderStart() {
//        progressDialog = ProgressDialog.show(this, "Please wait.",
//                "Finding direction..!", true);
//
//        if (originMarkers != null) {
//            for (Marker marker : originMarkers) {
//                marker.remove();
//            }
//        }
//
//        if (destinationMarkers != null) {
//            for (Marker marker : destinationMarkers) {
//                marker.remove();
//            }
//        }
//
//        if (polylinePaths != null) {
//            for (Polyline polyline:polylinePaths ) {
//                polyline.remove();
//            }
//        }
//
//    }
//
//    @Override
//    public void onDirectionFinderSuccess(List<Route> route) {
//        progressDialog.dismiss();
//        polylinePaths = new ArrayList<>();
//        originMarkers = new ArrayList<>();
//        destinationMarkers = new ArrayList<>();
//
//        for (Route route1 : route) {
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route1.startLocation, 16));
//            ((TextView) findViewById(R.id.tvDuration)).setText(route1.duration.text);
//            ((TextView) findViewById(R.id.tvDistance)).setText(route1.distance.text);
//
//            originMarkers.add(mMap.addMarker(new MarkerOptions()
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue))
//                    .title(route1.startAddress)
//                    .position(route1.startLocation)));
//            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green))
//                    .title(route1.endAddress)
//                    .position(route1.endLocation)));
//
//            PolylineOptions polylineOptions = new PolylineOptions().
//                    geodesic(true).
//                    color(Color.BLUE).
//                    width(10);
//
//            for (int i = 0; i < route1.points.size(); i++)
//                polylineOptions.add(route1.points.get(i));
//
//            polylinePaths.add(mMap.addPolyline(polylineOptions));
//        }
//    }
//
//
//}









//package com.antixiansoftware.travelling;
//
//import android.Manifest;
//import android.app.ProgressDialog;
//import android.content.pm.PackageManager;
////import android.graphics.Color;
////import android.support.v4.app.ActivityCompat;
////import android.support.v4.app.FragmentActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.BitmapDescriptorFactory;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.android.gms.maps.model.Polyline;
//import com.google.android.gms.maps.model.PolylineOptions;
//
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, DirectionFinderListener {
//
//    private GoogleMap mMap;
//    private Button btnFindPath;
//    private EditText etOrigin;
//    private EditText etDestination;
//    private List<Marker> originMarkers = new ArrayList<>();
//    private List<Marker> destinationMarkers = new ArrayList<>();
//    private List<Polyline> polylinePaths = new ArrayList<>();
//    private ProgressDialog progressDialog;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_map);
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//
//        btnFindPath = (Button) findViewById(R.id.btnFindPath);
//        etOrigin = (EditText) findViewById(R.id.current_location);
//        etDestination = (EditText) findViewById(R.id.destination);
//
//        btnFindPath.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendRequest();
//            }
//        });
//    }

//    *Button bt = (Button) findViewById(R.id.btn_find);
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MapActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//
//            }
//        });
//
//    private void sendRequest() {
//        String origin = etOrigin.getText().toString();
//        String destination = etDestination.getText().toString();
//        if (origin.isEmpty()) {
//            Toast.makeText(this, "Please enter origin address!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (destination.isEmpty()) {
//            Toast.makeText(this, "Please enter destination address!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        try {
//            new DirectionFinder(this, origin, destination).execute();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        LatLng hcmus = new LatLng(10.762963, 106.682394);
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hcmus, 18));
//        originMarkers.add(mMap.addMarker(new MarkerOptions()
//                .title("Đại học Khoa học tự nhiên")
//                .position(hcmus)));
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//             TODO: Consider calling
//                ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        mMap.setMyLocationEnabled(true);
//    }
//
//
//    @Override
//    public void onDirectionFinderStart() {
//        progressDialog = ProgressDialog.show(this, "Please wait.",
//                "Finding direction..!", true);
//
//        if (originMarkers != null) {
//            for (Marker marker : originMarkers) {
//                marker.remove();
//            }
//        }
//
//        if (destinationMarkers != null) {
//            for (Marker marker : destinationMarkers) {
//                marker.remove();
//            }
//        }
//
//        if (polylinePaths != null) {
//            for (Polyline polyline:polylinePaths ) {
//                polyline.remove();
//            }
//        }
//    }
//
//    @Override
//    public void onDirectionFinderSuccess(List<Route> routes) {
//        progressDialog.dismiss();
//        polylinePaths = new ArrayList<>();
//        originMarkers = new ArrayList<>();
//        destinationMarkers = new ArrayList<>();
//
//        for (Route route : routes) {
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 16));
//            ((TextView) findViewById(R.id.tvDuration)).setText(route.duration.text);
//            ((TextView) findViewById(R.id.tvDistance)).setText(route.distance.text);
//
//            originMarkers.add(mMap.addMarker(new MarkerOptions()
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue))
//                    .title(route.startAddress)
//                    .position(route.startLocation)));
//            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green))
//                    .title(route.endAddress)
//                    .position(route.endLocation)));
//
//            PolylineOptions polylineOptions = new PolylineOptions().
//                    geodesic(true).
//                    color(Color.BLUE).
//                    width(10);
//
//            for (int i = 0; i < route.points.size(); i++)
//                polylineOptions.add(route.points.get(i));
//
//            polylinePaths.add(mMap.addPolyline(polylineOptions));
//        }
//    }
//}

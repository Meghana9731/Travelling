package com.antixiansoftware.travelling.Main;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.antixiansoftware.travelling.Loginsign.LoginActivity;
import com.antixiansoftware.travelling.Goods.Goodscollection;
import com.antixiansoftware.travelling.R;
import com.antixiansoftware.travelling.Travel.Trvcollections;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
//import com.google.android.gms.location.places.Place;
//import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    private long backPressedTime;
    private Toast backToast;
    boolean doubleBackToExitPressedOnce = false;

    //    ViewPager pager;
//    TabLayout mtabLayout;
//    TabItem firstItem, secondItem;
//    PagerAdapter adapter;
//    Button map;
//    TextView textView;
//    int PLACE_MAP_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


        Button bt = (Button) findViewById( R.id.button );
        bt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent( MainActivity.this, Trvcollections.class );
                startActivity( intent1 );
                finish();
            }
        } );


        Button bt3 = (Button) findViewById( R.id.button1 );
        bt3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent( MainActivity.this, Goodscollection.class );
                startActivity( intent1 );
                finish();
            }
        } );


        TextView tx1 = (TextView) findViewById( R.id.textView23 );
        tx1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent( MainActivity.this, Trvcollections.class );
                startActivity( intent1 );
                finish();
            }
        } );

        TextView tx2 = (TextView) findViewById( R.id.textView22 );
        tx2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent( MainActivity.this, Goodscollection.class );
                startActivity( intent1 );
                finish();
            }
        } );


        ImageSlider imageSlider = findViewById( R.id.image_slider );
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add( new SlideModel( "https://www.suvdrive.com/sites/default/files/public/45%20angle%20view/45%20angle%20view%20Toyota%20Land%20Cruiser%20Prado%20GX%202017.jpg", "" ) );
        slideModels.add( new SlideModel( "https://cars.usnews.com/static/images/Auto/izmo/i2314350/2016_toyota_land_cruiser_dashboard.jpg", "" ) );
        slideModels.add( new SlideModel( "https://www.carkhabri.com/Gallery/toyota/toyota-land-cruiser/interior/large/13.jpg", "" ) );
        imageSlider.setImageList( slideModels, true );
//        imageSlider.setScrollBarFadeDuration( 1000 );


        TapTargetView.showFor( this,                 // `this` is an Activity
                TapTarget.forView( findViewById( R.id.fab ), "Here you can contact us", "We have the best Service " ).transparentTarget( false ).tintTarget( false )
                // All options below are optional
        );


        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById( R.id.fab );
        floatingActionButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String contact = "+91 7795663858"; // use country code with your phone number
                String url = "https://api.whatsapp.com/send?phone=" + contact;
                try {
                    PackageManager pm = getApplicationContext().getPackageManager();
                    pm.getPackageInfo( "com.whatsapp", PackageManager.GET_ACTIVITIES );
                    Intent intent = new Intent( Intent.ACTION_VIEW );
                    intent.setData( Uri.parse( url ) );
                    startActivity( intent );
                } catch (PackageManager.NameNotFoundException e) {
                    Toast.makeText( MainActivity.this, "Whatsapp app not installed in your phone", Toast.LENGTH_SHORT ).show();
                    e.printStackTrace();
                }

            }

        } );


//        pager = findViewById(R.id.view_pager);
//        mtabLayout = findViewById(R.id.tablayout);
//        firstItem = findViewById(R.id.firstItem);
//        secondItem =findViewById(R.id.secondItem);

//        adapter = new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mtabLayout.getTabCount());
//        pager.setAdapter(adapter);

//        mtabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                pager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//
//        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mtabLayout));

//
//        map = findViewById(R.id.map);
//        textView = findViewById(R.id.text_view1);
//
//        map.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
//                try {
//                    startActivityForResult(builder.build(MainActivity.this)
//                            , PLACE_MAP_REQUEST);
//                } catch (GooglePlayServicesRepairableException e) {
//                    e.printStackTrace();
//                } catch (GooglePlayServicesNotAvailableException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (requestCode == PLACE_MAP_REQUEST){
//            if (resultCode == RESULT_OK){
//                Place place = PlacePicker.getPlace(data,this);
//                StringBuilder stringBuilder = new StringBuilder();
//                String latitude = String.valueOf(place.getLatLng().latitude);
//                String longitude = String.valueOf(place.getLatLng().longitude);
//                stringBuilder.append("LATITUDE");
//                stringBuilder.append(latitude);
//                stringBuilder.append("\n");
//                stringBuilder.append("LONGITUDE");
//                stringBuilder.append(longitude);
//                textView.setText(stringBuilder.toString());
//            }
//        }


        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
//        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));


        mDrawerLayout = (DrawerLayout) findViewById( R.id.drawer );
        NavigationView nvDrawer = (NavigationView) findViewById( R.id.nv );
        mToggle = new ActionBarDrawerToggle( this, mDrawerLayout, R.string.open, R.string.close );
        mDrawerLayout.addDrawerListener( mToggle );
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        setupDrawerContent( nvDrawer );

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu, menu );
        return true;
    }

//         R.id.action_logout:
//            logoutUser();
//            return true;
//        default:
//            return super.onOptionsItemSelected(menu);
//    }
//}


//     if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
//            mDrawerLayout.closeDrawer(GravityCompat.START);
//        }else {
//            super.onBackPressed();
//        }

//    Intent intent = new Intent( getApplicationContext(), MainActivity.class );
//        intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
//        intent.putExtra( "EXIT", true );
//    startActivity( intent );
//        if (getIntent().getBooleanExtra( "EXIT", false )) {
//        finish();
//    }


    @Override
    public void onBackPressed() {


        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setMessage( "Are you sure you want to Exit?" )
                .setCancelable( false )
                .setPositiveButton( "Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onBackPressed();
                    }
                } )

                .setNegativeButton( "No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();

                    }
                } );

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }






//
//    @Override
//    public void onBackPressed() {
//        if (backPressedTime + 2000 > System.currentTimeMillis()) {
//            backToast.cancel();
//            super.onBackPressed();
//            return;
//        } else {
//            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
//            backToast.show();
//        }
//        backPressedTime = System.currentTimeMillis();
//    }



//    @Override
//    public void onBackPressed() {
//        new AlertDialog.Builder(this)
//                .setIcon(android.R.drawable.ic_dialog_alert)
//                .setTitle("Closing Activity")
//                .setMessage("Are you sure you want to close this activity?")
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
//                {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                    }
//
//                })
//                .setNegativeButton("No", null)
//                .show();
//    }
//
//

//    @Override
//    public void onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
//            return;
//        }
//
//        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText( this, "Please click BACK again to exit", Toast.LENGTH_SHORT ).show();
//
//        new Handler().postDelayed( new Runnable() {
//
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce = false;
//            }
//        }, 2000 );
//    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mToggle.onOptionsItemSelected( item )) {
            return true;
        }
        int id = item.getItemId();

        if (id == R.id.action_logout) {
                Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// clear back stack
                startActivity(myIntent);
                finish();
        }
        else if (id ==R.id.action_settings){
            Toast.makeText(MainActivity.this, "This is a setting", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected( item );
    }


    public void selectItemDrawer(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.home) {
            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
        } else if (id == R.id.about) {
            Intent intent = new Intent(getApplicationContext(), About.class);
            startActivity(intent);
        } else if (id == R.id.terms_and_condition) {
                Intent intent = new Intent(getApplicationContext(), TermsAndCondition.class);
                startActivity(intent);
            }


        }




    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectItemDrawer(menuItem);


                return true;
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.action_settings){
            Toast.makeText(MainActivity.this, "This is a setting", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
package com.antixiansoftware.travelling.Goods;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.antixiansoftware.travelling.R;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class GoodsVehicalsActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_vehicals );

        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        Button bta = (Button) findViewById(R.id.button44);
        bta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent( GoodsVehicalsActivity.this, GoodsBookActivity.class);
                startActivity(intent1);
                finish();
            }
        });



        ImageSlider imageSlider = findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://www.suvdrive.com/sites/default/files/public/45%20angle%20view/45%20angle%20view%20Toyota%20Land%20Cruiser%20Prado%20GX%202017.jpg" , ""));
        slideModels.add(new SlideModel("https://cars.usnews.com/static/images/Auto/izmo/i2314350/2016_toyota_land_cruiser_dashboard.jpg" , ""));
        slideModels.add(new SlideModel("https://www.carkhabri.com/Gallery/toyota/toyota-land-cruiser/interior/large/13.jpg" , ""));
        imageSlider.setImageList(slideModels,true);

    }

    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(), Goodscollection.class );
        startActivity( intent );
        finishAffinity();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

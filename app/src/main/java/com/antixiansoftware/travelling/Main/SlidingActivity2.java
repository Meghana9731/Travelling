package com.antixiansoftware.travelling.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.antixiansoftware.travelling.Goods.GoodsBookActivity;
import com.antixiansoftware.travelling.R;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class SlidingActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding2);

        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Button bt4 = (Button) findViewById(R.id.button4);
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SlidingActivity2.this , GoodsBookActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        Button bt8 = (Button) findViewById(R.id.button8);
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SlidingActivity2.this , MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });



        ImageSlider imageSlider = findViewById(R.id.image_slider2);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://commercialvehicleinfo.com/wp-content/uploads/2018/01/Mahindra-Imperio-1-300x186.png " , ""));
        slideModels.add(new SlideModel("https://commercialvehicleinfo.com/wp-content/uploads/2018/01/Mahindra-Imperio-1-300x186.png" , ""));
        slideModels.add(new SlideModel("https://commercialvehicleinfo.com/wp-content/uploads/2018/01/Mahindra-Imperio-1-300x186.png" , ""));
        imageSlider.setImageList(slideModels,true);
    }
}
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

public class SlidingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding);

        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Button bt1 = (Button) findViewById(R.id.button4);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SlidingActivity.this , GoodsBookActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        Button bt7 = (Button) findViewById(R.id.button7);
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SlidingActivity.this , MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });







        ImageSlider imageSlider = findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://imgd.aeplcdn.com/664x374/n/cw/ec/45933/mahindra-bolero-exterior10.jpeg?q=85 " , ""));
        slideModels.add(new SlideModel("https://www.mahindrabolero.com/images2020/highlights_4.png?v=1.2" , ""));
        slideModels.add(new SlideModel("https://imgd.aeplcdn.com/664x374/n/cw/ec/45933/mahindra-bolero-exterior4.jpeg" , ""));
        imageSlider.setImageList(slideModels,true);








    }
}
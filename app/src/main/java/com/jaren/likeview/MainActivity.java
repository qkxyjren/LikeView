package com.jaren.likeview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.jaren.lib.view.LikeView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LikeView lv = (LikeView) findViewById(R.id.lv);
        lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("------","dddd");
            }
        });
    }
}

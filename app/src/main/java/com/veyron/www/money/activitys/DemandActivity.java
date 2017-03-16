package com.veyron.www.money.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.veyron.www.money.R;

public class DemandActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView back_img;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demand);

        back_img = (ImageView) findViewById(R.id.back_demand);
        back_img.setOnClickListener(this);

        mButton = (Button) findViewById(R.id.ceping);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //打开DemanActivity2
                Intent intent = new Intent(DemandActivity.this,DemanActivity2.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}

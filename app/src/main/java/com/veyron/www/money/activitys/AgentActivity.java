package com.veyron.www.money.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.veyron.www.money.R;

public class AgentActivity extends AppCompatActivity {
    private ImageView back_agent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent);
        back_agent = (ImageView) findViewById(R.id.back_agent);
        back_agent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

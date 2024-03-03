package com.example.thirdproj;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private long pauseVal;
    private boolean status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("Time elapsed: %s");
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener()
        {
            @Override
            public void onChronometerTick(Chronometer chronometer)
            {
                if ((SystemClock.elapsedRealtime()-chronometer.getBase())>=10000)
                {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    Toast.makeText(MainActivity.this,"Bing", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void startChronometer(View v)
    {
        if (!status)
        {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseVal);
            chronometer.start();
            status=true;
        }
    }

    public void pauseChronometer(View v)
    {
        if (status)
        {
            chronometer.stop();
            pauseVal = SystemClock.elapsedRealtime() - chronometer.getBase();
            status=false;
        }
    }

    public void resetChronometer(View v)
    {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseVal = 0;
    }
}

package com.nexalabs.seekbarwithdisplaymessage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    SeekBarWithDisplayMessage seekBarWithDisplayMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBarWithDisplayMessage = findViewById(R.id.seekBarWithDisplayMessage);
        seekBarWithDisplayMessage.setGreenBracket(2);
        seekBarWithDisplayMessage.setYellowBracket(18);
        seekBarWithDisplayMessage.setGreenMessage("Green Message");
        seekBarWithDisplayMessage.setYellowMessage("Yellow Message");
        seekBarWithDisplayMessage.setRedMessage("Red Message");
    }
}

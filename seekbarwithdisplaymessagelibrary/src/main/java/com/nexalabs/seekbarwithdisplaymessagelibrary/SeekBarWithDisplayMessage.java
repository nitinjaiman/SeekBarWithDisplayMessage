package com.nexalabs.seekbarwithdisplaymessagelibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StyleableRes;

public class SeekBarWithDisplayMessage extends LinearLayout {

    @StyleableRes
    int index0 = 0;
    @StyleableRes
    int index1 = 1;
    @StyleableRes
    int index2 = 2;
    @StyleableRes
    int index3 = 3;

    private int seekBarMaxValue;

    private int greenBracket;
    private int yellowBracket;

    private String greenMessage;
    private String yellowMessage;
    private String redMessage;

    TextView messageTextView;
    TextView quantityTextView;
    SeekBar seekBar;

    public SeekBarWithDisplayMessage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }



    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.seek_bar_with_display_message, this);

        int[] sets = {R.attr.quantity, R.attr.greenMessage, R.attr.yellowMessage, R.attr.redMessage};
        TypedArray typedArray = context.obtainStyledAttributes(attrs, sets);
        final CharSequence quantity = typedArray.getText(index0);
        final CharSequence greenMessageFromXml = typedArray.getText(index1);
        final CharSequence yellowMessageFromXml = typedArray.getText(index2);
        final CharSequence redMessageFromXml = typedArray.getText(index3);
        typedArray.recycle();

        initComponents();

        initializeDefaultMessages(greenMessageFromXml, yellowMessageFromXml, redMessageFromXml);

        seekBarMaxValue = Integer.parseInt(quantity.toString());
        seekBar.setMax(seekBarMaxValue);


        //Default values
        greenBracket = seekBarMaxValue/3;
        yellowBracket = (seekBarMaxValue*2)/3;

        updateSeekBar(seekBar, seekBar.getProgress());
        setOnProgressChangeListener();

    }

    private void setOnProgressChangeListener() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateSeekBar(seekBar, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void updateSeekBar(SeekBar seekBar, int progress) {
        quantityTextView.setText(String.valueOf(progress));
        if(progress<=greenBracket){
            messageTextView.setText(greenMessage);
            seekBar.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
            seekBar.getThumb().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
        }else if(progress>greenBracket && progress<=yellowBracket){
            messageTextView.setText(yellowMessage);
            seekBar.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
            seekBar.getThumb().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
        }else{
            messageTextView.setText(redMessage);
            seekBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            seekBar.getThumb().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }
    }

    private void initializeDefaultMessages(CharSequence greenMessageFromXml, CharSequence yellowMessageFromXml, CharSequence redMessageFromXml) {
        if(null!=greenMessageFromXml) {
            this.greenMessage = greenMessageFromXml.toString();
        }
        if(null!=yellowMessageFromXml) {
            this.yellowMessage = yellowMessageFromXml.toString();
        }
        if(null!=redMessageFromXml) {
            this.redMessage = redMessageFromXml.toString();
        }
    }

    /**
     * Till when seek bar should be in greem
     * @param green
     */
    public void setGreenBracket(int green){
        this.greenBracket = green;
        updateSeekBar(seekBar, seekBar.getProgress());
    }

    /**
     * Till when seek bar should be in yellow
     * @param yellow
     */
    public void setYellowBracket(int yellow){
        this.yellowBracket = yellow;
        updateSeekBar(seekBar, seekBar.getProgress());
    }

    private void initComponents() {
        messageTextView = (TextView) findViewById(R.id.textViewMessage);

        quantityTextView = (TextView) findViewById(R.id.textViewQuantity);

        seekBar = (SeekBar) findViewById(R.id.seekBar);

    }

    public CharSequence getQuantity() {
        return quantityTextView.getText();
    }

    public void setQuantity(CharSequence quantityText) {
        quantityTextView.setText(quantityText);
        updateSeekBar(seekBar, seekBar.getProgress());
    }

    public int getGreenBracket() {
        return greenBracket;
    }

    public int getYellowBracket() {
        return yellowBracket;
    }

    public int getProgress() {
        return seekBar.getProgress();
    }

    public String getGreenMessage() {
        return greenMessage;
    }

    public void setGreenMessage(String greenMessage) {
        this.greenMessage = greenMessage;
        updateSeekBar(seekBar, seekBar.getProgress());
    }

    public String getYellowMessage() {
        return yellowMessage;
    }

    public void setYellowMessage(String yellowMessage) {
        this.yellowMessage = yellowMessage;
        updateSeekBar(seekBar, seekBar.getProgress());
    }

    public String getRedMessage() {
        return redMessage;
    }

    public void setRedMessage(String redMessage) {
        this.redMessage = redMessage;
        updateSeekBar(seekBar, seekBar.getProgress());
    }
}

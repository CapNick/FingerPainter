package hird.nick.psynh1.fingerpainter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class ColorSelectActivity extends AppCompatActivity {

// ====== Color Variables ===========
    private int color;
    private int colorRedInt;
    private int colorGreenInt;
    private int colorBlueInt;
// ====== Text Variables ============
    private TextView redText;
    private TextView greenText;
    private TextView blueText;
    private TextView hexText;
// ====== Seekbar Variables =========
    private SeekBar redBar;
    private SeekBar greenBar;
    private SeekBar blueBar;
// ====== Image Preview =============
    private ImageView colorPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_select);
        initializeVariables();
        setColorValues();
        redText.setText(""+ colorRedInt);
        greenText.setText(""+ colorGreenInt);
        blueText.setText(""+ colorBlueInt);
        redBar.setProgress(colorRedInt);
        greenBar.setProgress(colorGreenInt);
        blueBar.setProgress(colorBlueInt);
        updateHexText();

        redBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                colorRedInt = progress;
                redText.setText(""+ colorRedInt);
                updateHexText();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        greenBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                colorGreenInt = progress;
                greenText.setText(""+ colorGreenInt);
                updateHexText();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        blueBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                colorBlueInt = progress;
                blueText.setText(""+ colorBlueInt);
                updateHexText();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

    }

    private void updateHexText(){
        String redString = String.format("%02X", colorRedInt);
        String greenString = String.format("%02X", colorGreenInt);
        String blueString = String.format("%02X", colorBlueInt);
        color = Color.argb(255, colorRedInt, colorGreenInt, colorBlueInt);
        hexText.setText("#"+redString+greenString+blueString);
        colorPreview.setBackgroundColor(Color.argb(255, colorRedInt, colorGreenInt, colorBlueInt));
    }

    private void setColorValues(){
        color = getIntent().getExtras().getInt("Color");
        String tempColor = String.format("%X",color);
        String[] hexList = tempColor.split("");
        Log.d("Hex length: ",""+hexList.length);
        Log.d("Hex Value: ",""+tempColor);

        colorRedInt = Integer.decode("#"+hexList[3]+hexList[4]); // will equal the current color from the main activity
        Log.d("Red: ","#"+hexList[3]+hexList[4]);

        colorGreenInt = Integer.decode("#"+hexList[5]+hexList[6]); // will equal the current color from the main activity
        Log.d("Green: ","#"+hexList[5]+hexList[6]);

        colorBlueInt = Integer.decode("#"+hexList[7]+hexList[8]); // will equal the current color from the main activity
        Log.d("Blue: ","#"+hexList[7]+hexList[8]);

    }

    private void initializeVariables() {
        redText = (TextView)findViewById(R.id.txtRedValue);
        greenText = (TextView)findViewById(R.id.txtGreenValue);
        blueText = (TextView)findViewById(R.id.txtBlueValue);
        hexText = (TextView)findViewById(R.id.txtHexValue);
        redBar = (SeekBar) findViewById(R.id.seekRed);
        greenBar = (SeekBar)findViewById(R.id.seekGreen);
        blueBar = (SeekBar)findViewById(R.id.seekBlue);
        colorPreview = (ImageView)findViewById(R.id.colorPreview);
    }

    public void returnToMain (View view){
        Intent result = new Intent();
        result.putExtra("Color", Color.argb(255, colorRedInt, colorGreenInt, colorBlueInt));
        setResult(Activity.RESULT_OK, result);
        finish();
    }

}

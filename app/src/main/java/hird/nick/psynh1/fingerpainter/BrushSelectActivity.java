package hird.nick.psynh1.fingerpainter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class BrushSelectActivity extends AppCompatActivity {

//  ========= Brush Values ========
    private int brushSize;
    private int brushType;
//  ========= Brush Type =======
    private Button btnTypeRound;
    private Button btnTypeSquare;
//  ========= Brush Size =======
    private SeekBar seekBrushSize;
    private TextView txtBrushSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brush_select);
        initializeVariables();
        initializeWidgets();
        seekBrushSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                brushSize = progress;
                txtBrushSize.setText(""+brushSize);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    public void changeBrushRound(View view){
        brushType = 0;
        btnTypeSquare.setEnabled(true);
        btnTypeRound.setEnabled(false);
    }

    public void changeBrushSquare(View view){
        brushType = 1;
        btnTypeRound.setEnabled(true);
        btnTypeSquare.setEnabled(false);
    }

    public void returnToMain(View view){
        Intent result = new Intent();
        Bundle returnBundle = new Bundle();
        returnBundle.putInt("BrushSize", brushSize);
        returnBundle.putInt("BrushType", brushType);
        result.putExtras(returnBundle);
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    private void initializeVariables(){
        brushSize = getIntent().getExtras().getInt("BrushSize");
        brushType = getIntent().getExtras().getInt("BrushType");
        btnTypeRound = (Button) findViewById(R.id.btnBrushRound);
        btnTypeSquare = (Button) findViewById(R.id.btnBrushSquare);
        seekBrushSize = (SeekBar) findViewById(R.id.seekBrushSize);
        txtBrushSize = (TextView) findViewById(R.id.txtBrushSize);
    }

    private void initializeWidgets() {
        seekBrushSize.setProgress(brushSize);
        txtBrushSize.setText(""+brushSize);
        if (brushType == 0) {
            btnTypeRound.setEnabled(false);
        }
        else {
            btnTypeSquare.setEnabled(false);
        }
    }
}

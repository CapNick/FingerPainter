package hird.nick.psynh1.fingerpainter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private FingerPainterView fingerView;
//    private ImageButton btnImageColorPreview;
    static final int CHANGE_COLOR_REQUEST_CODE = 1;
    static final int CHANGE_BRUSH_REQUEST_CODE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fingerView = (FingerPainterView)findViewById(R.id.fingerView);
//        btnImageColorPreview = (ImageButton)findViewById(R.id.btnColorPreview);
//        btnImageColorPreview.setBackgroundColor(fingerView.getColour());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CHANGE_COLOR_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                Log.d("Restult Recivied", ""+data.getExtras().getInt("Color"));
                fingerView.setColour(data.getExtras().getInt("Color"));
//                btnImageColorPreview.setBackgroundColor(data.getExtras().getInt("Color"));

            }
        }
        if (requestCode == CHANGE_BRUSH_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                Log.d("Restult Recivied", ""+data.getExtras().getInt("BrushSize"));
                Log.d("Restult Recivied", ""+data.getExtras().getInt("BrushShape"));
                if (data.getExtras().getInt("BrushType") == 0){
                    fingerView.setBrush(Paint.Cap.ROUND);
                }
                else{
                    fingerView.setBrush(Paint.Cap.SQUARE);
                }
                fingerView.setBrushWidth(data.getExtras().getInt("BrushSize"));
            }
        }
    }

    public void clearView(View v){
        fingerView.clearFocus();
    }

    public void switchToColor(View v){
        Intent intent = new Intent(MainActivity.this, ColorSelectActivity.class);
        Bundle colorBundle = new Bundle();

        colorBundle.putInt("Color",fingerView.getColour());
        intent.putExtras(colorBundle);
        startActivityForResult(intent,CHANGE_COLOR_REQUEST_CODE);
    }
    public void switchToBrush(View v){
        Intent intent = new Intent(MainActivity.this, BrushSelectActivity.class);
        Bundle brushBundle = new Bundle();

        brushBundle.putInt("BrushSize",fingerView.getBrushWidth());
        if (fingerView.getBrush() == Paint.Cap.ROUND){
            brushBundle.putInt("BrushType",0);
        }
        else {
            brushBundle.putInt("BrushType",1);
        }
        intent.putExtras(brushBundle);
        startActivityForResult(intent,CHANGE_BRUSH_REQUEST_CODE);
    }
}

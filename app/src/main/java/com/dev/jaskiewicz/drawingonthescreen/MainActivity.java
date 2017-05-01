package com.dev.jaskiewicz.drawingonthescreen;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private DrawingView drawingView;
    private View.OnClickListener onClickListener;
    private Button redButton;
    private Button yellowButton;
    private Button blueButton;
    private Button greenButton;
    private Button clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupDrawingView();
        findButtons();
        defineOnClickListener();
        setupOnClickListenerForButtons();
    }

    private void setupDrawingView() {
        drawingView = (DrawingView) findViewById(R.id.main_activity_drawing_view);
        setDefaultPaintColor();
    }

    private void setDefaultPaintColor() {
        drawingView.setPaintColor(getColorFromResource(R.color.red));
    }

    private void findButtons() {
        redButton = (Button) findViewById(R.id.main_activity_button_red);
        yellowButton = (Button) findViewById(R.id.main_activity_button_yellow);
        blueButton = (Button) findViewById(R.id.main_activity_button_blue);
        greenButton = (Button) findViewById(R.id.main_activity_button_green);
        clearButton = (Button) findViewById(R.id.main_activity_button_clear);
    }

    private void setupOnClickListenerForButtons() {
        redButton.setOnClickListener(onClickListener);
        yellowButton.setOnClickListener(onClickListener);
        blueButton.setOnClickListener(onClickListener);
        greenButton.setOnClickListener(onClickListener);
        clearButton.setOnClickListener(onClickListener);
    }

    private void defineOnClickListener() {
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()) {
                    case R.id.main_activity_button_red:
                        drawingView.setPaintColor(getColorFromResource(R.color.red));
                        break;
                    case R.id.main_activity_button_yellow:
                        drawingView.setPaintColor(getColorFromResource(R.color.yellow));
                        break;
                    case R.id.main_activity_button_blue:
                        drawingView.setPaintColor(getColorFromResource(R.color.blue));
                        break;
                    case R.id.main_activity_button_green:
                        drawingView.setPaintColor(getColorFromResource(R.color.green));
                        break;
                    case R.id.main_activity_button_clear:
                        drawingView.clear();
                        break;
                }
            }
        };
    }

    private int getColorFromResource(int colorResourceID) {
        return ContextCompat.getColor(MainActivity.this, colorResourceID);
    }
}

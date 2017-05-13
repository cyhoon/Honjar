package org.androidtown.capshule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity implements View.OnClickListener{
    private static final int REQUEST_CODE = 111;
    TextView textview;
    TextView goToReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textview = (TextView) findViewById(R.id.textView);
        goToReg = (TextView)  findViewById(R.id.GotoReg);

        goToReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int resid = v.getId();
        if(resid == R.id.GotoReg){
            Intent intent = new Intent(getApplicationContext(),Register1.class);
            startActivityForResult(intent,REQUEST_CODE);
        }
    }
}
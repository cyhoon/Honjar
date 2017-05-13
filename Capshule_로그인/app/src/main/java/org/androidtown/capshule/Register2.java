package org.androidtown.capshule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register2 extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE = 333;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        next = (Button) findViewById(R.id.button);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(),Register3.class);
        startActivityForResult(intent,REQUEST_CODE);
    }
}

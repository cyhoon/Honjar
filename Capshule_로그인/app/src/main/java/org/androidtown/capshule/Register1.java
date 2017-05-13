package org.androidtown.capshule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register1 extends AppCompatActivity implements View.OnClickListener{
    private static final int REQUEST_CODE = 222;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
        next = (Button) findViewById(R.id.button);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(),Register2.class);
        startActivityForResult(intent,REQUEST_CODE);
    }
}

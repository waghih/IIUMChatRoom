package com.waghih.iiumchatroom;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JoinActivity extends Activity {

    private Button btnJoin;
    private EditText txtName;
    private EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        btnJoin = (Button) findViewById(R.id.btnJoin);
        txtName = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.password);

        // Hiding the action bar
        getActionBar().hide();

        btnJoin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (txtName.getText().toString().trim().length() > 0 && pass.getText().toString().equals("2016")) {

                    String name = txtName.getText().toString().trim();

                    Intent intent = new Intent(JoinActivity.this,
                            MainActivity.class);
                    intent.putExtra("name", name);

                    startActivity(intent);

                }
                else if (txtName.getText().toString().trim().length() > 0 && !pass.getText().toString().equals("2016")){
                    Toast.makeText(getApplicationContext(),
                            "Invalid Pin Code", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your name", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

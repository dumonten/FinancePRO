package com.example.anotherversion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * The type Enter password.
 */
public class EnterPassword extends AppCompatActivity {
    /**
     * The Btn enter.
     */
    Button btnEnter;
    /**
     * The Et pass.
     */
    EditText etPass;
    /**
     * The Password.
     */
    String password;
    /**
     * The Clickcount.
     */
    int clickcount =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);
        btnEnter = (Button) findViewById(R.id.btnEnter);
        etPass = (EditText) findViewById(R.id.etPass);
        SharedPreferences pref = getSharedPreferences("prefs", 0);
        password = pref.getString("password", "");
        btnEnter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String edit = etPass.getText().toString();
                if (edit.equals(password)){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    clickcount++;
                    Toast.makeText(getApplicationContext(), "Неверный пароль!", Toast.LENGTH_SHORT).show();
                    if (clickcount>=5){
                        startActivity(new Intent(EnterPassword.this, CheckAns.class));
                        finish();
                    }

                }

            }
        });
    }
}
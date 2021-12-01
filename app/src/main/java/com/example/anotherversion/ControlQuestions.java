package com.example.anotherversion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The type Control questions.
 */
public class ControlQuestions extends AppCompatActivity implements View.OnClickListener {

    private TextView quest1;
    private TextView quest2;
    private TextView quest3;
    private Button btnNext;
    private EditText etAns1;
    private EditText etAns2;
    private EditText etAns3;
    private DbHelper db;
    private boolean checkAns1;
    private boolean checkAns2;
    private boolean checkAns3;
    private String ques1;
    private String ques2;
    private String ques3;
    private String ans1;
    private String ans2;
    private String ans3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_questions);
        quest1 = (TextView) findViewById(R.id.quest1);
        quest2 = (TextView) findViewById(R.id.quest2);
        quest3 = (TextView) findViewById(R.id.quest3);
        etAns1 = (EditText) findViewById(R.id.etAns1);
        etAns2 = (EditText) findViewById(R.id.etAns2);
        etAns3 = (EditText) findViewById(R.id.etAns3);
        btnNext = (Button) findViewById(R.id.btnNext);
        db = new DbHelper(this);
        btnNext.setOnClickListener(this);
    }

    private void check() {
        ques1 = quest1.getText().toString();
        ans1 = etAns1.getText().toString();
        ques2 = quest2.getText().toString();
        ans2 = etAns2.getText().toString();
        ques3 = quest3.getText().toString();
        ans3 = etAns3.getText().toString();

        if (ans1.isEmpty()) {
            checkAns1 = false;
        } else {
            checkAns1 = true;
        }
        if (ans2.isEmpty()) {
            checkAns2 = false;
        } else {
            checkAns2 = true;
        }
        if (ans3.isEmpty()) {
            checkAns3 = false;
        } else {
            checkAns3 = true;
        }
    }

    private void saveAll() {
        db.addAnswers(ques1, ans1);
        db.addAnswers(ques2, ans2);
        db.addAnswers(ques3, ans3);
        finish();
    }

    @Override
    public void onClick(View v) {
        check();
        if (!checkAns1 || !checkAns2 || !checkAns3) {
                Toast.makeText(getApplicationContext(), "Вы не ответили на вопрос!", Toast.LENGTH_SHORT).show();
            }
        if (checkAns1 && checkAns2 && checkAns3) {
            saveAll();
            startActivity(new Intent(ControlQuestions.this, MainActivity.class));
            finish();
        }
    }
}
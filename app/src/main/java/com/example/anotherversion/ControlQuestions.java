package com.example.anotherversion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ControlQuestions extends AppCompatActivity implements View.OnClickListener{

    private TextView quest1,quest2,quest3;
    private Button btnNext;
    private EditText etAns1,etAns2,etAns3;
    private DbHelper db;
    boolean ans1,ans2, ans3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_questions);
        ans1= true; ans2=true; ans3 = true;
        quest1 = (TextView) findViewById(R.id.quest1);
        quest2 = (TextView) findViewById(R.id.quest2);
        quest3 = (TextView) findViewById(R.id.quest3);
        etAns1 = (EditText)findViewById(R.id.etAns1);
        etAns2 = (EditText)findViewById(R.id.etAns2);
        etAns3 = (EditText)findViewById(R.id.etAns3);
        btnNext = (Button)findViewById(R.id.btnNext);
        db = new DbHelper(this);
        btnNext.setOnClickListener(this);
    }
    private void save1 (){
        String ques = quest1.getText().toString();
        String ans = etAns1.getText().toString();
        if (ans.isEmpty() && ques.isEmpty()){
            ans1=false;
        } else {
            db.addAnswers(ques,ans);
            finish();
        }
    }
    private void save2 (){
        String ques = quest2.getText().toString();
        String ans = etAns2.getText().toString();
        if (ans.isEmpty() && ques.isEmpty()){
            ans2=false;
        } else {
            db.addAnswers(ques,ans);
            finish();
        }
    }
    private void save3 (){
        String ques = quest3.getText().toString();
        String ans = etAns3.getText().toString();
        if (ans.isEmpty() && ques.isEmpty()){
           ans3=false;
        } else {
            db.addAnswers(ques,ans);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        save1();
        save2();
        save3();
        if (!ans1 && !ans2 && !ans3){
            Toast.makeText(getApplicationContext(),"Вы не ответили на вопрос!",Toast.LENGTH_SHORT).show();
        }
        startActivity(new Intent(ControlQuestions.this, MainActivity.class));
        finish();
    }
}
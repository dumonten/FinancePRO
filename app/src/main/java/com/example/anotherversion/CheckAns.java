package com.example.anotherversion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The type Check ans.
 */
public class CheckAns extends AppCompatActivity implements View.OnClickListener {
    private TextView quest1,quest2,quest3;
    private Button btnNext;
    private EditText etAns1,etAns2,etAns3;
    private DbHelper db;
    /**
     * The Ans 1.
     */
    boolean ans1, /**
     * The Ans 2.
     */
    ans2, /**
     * The Ans 3.
     */
    ans3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_ans);
        ans1= false; ans2=false; ans3 = false;
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

    private void check1(){
        String ans = etAns1.getText().toString();

        if(db.getAnswers(ans)) {
            ans1 = true;
        }
    }
    private void check2(){
        String ans = etAns2.getText().toString();

        if(db.getAnswers(ans)){
            ans2 = true;
        }
    }
    private void check3(){
        String ans = etAns3.getText().toString();

        if(db.getAnswers(ans)){
            ans3= true;
        }
    }

    @Override
    public void onClick(View v) {
        check1();
        check2();
        check3();
        if (ans1 && ans2 && ans3) {
            Intent intent = new Intent(getApplicationContext(), CreatePassword.class);
            startActivity(intent);
            finish();
        } else{
            Toast.makeText(getApplicationContext(), "Ответ неверный! \nПопробуйте еще раз!",Toast.LENGTH_SHORT).show();
            }
    }
}

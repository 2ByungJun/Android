package com.example.a4_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnAdd (View v){
        EditText num1 = (EditText)findViewById(R.id.edit1);
        EditText num2 = (EditText)findViewById(R.id.edit2);
        if(num1.getText().length() <= 0 || num2.getText().length() <= 0) {
            Toast.makeText(this, "숫자를 입력하세요!", Toast.LENGTH_LONG).show();
        }
        else {
            TextView result = (TextView) findViewById(R.id.result);
            double n1 = Double.parseDouble(num1.getText().toString());
            double n2 = Double.parseDouble(num2.getText().toString());
            result.setText("계산 결과 : " + Double.toString(n1 + n2));
        }
    }

    public void btnSub (View v){
        EditText num1 = (EditText)findViewById(R.id.edit1);
        EditText num2 = (EditText)findViewById(R.id.edit2);
        if(num1.getText().length() <= 0 || num2.getText().length() <= 0) {
            Toast.makeText(this, "숫자를 입력하세요!", Toast.LENGTH_LONG).show();
        }
        else {
            TextView result = (TextView) findViewById(R.id.result);
            double n1 = Double.parseDouble(num1.getText().toString());
            double n2 = Double.parseDouble(num2.getText().toString());
            result.setText("계산 결과 : " + Double.toString(n1 - n2));
        }
    }

    public void btnMul (View v){
        EditText num1 = (EditText)findViewById(R.id.edit1);
        EditText num2 = (EditText)findViewById(R.id.edit2);
        if(num1.getText().length() <= 0 || num2.getText().length() <= 0) {
            Toast.makeText(this, "숫자를 입력하세요!", Toast.LENGTH_LONG).show();
        }
        else {
            TextView result = (TextView) findViewById(R.id.result);
            double n1 = Double.parseDouble(num1.getText().toString());
            double n2 = Double.parseDouble(num2.getText().toString());
            result.setText("계산 결과 : " + Double.toString(n1 * n2));
        }
    }

    public void btnDiv (View v){
        EditText num1 = (EditText)findViewById(R.id.edit1);
        EditText num2 = (EditText)findViewById(R.id.edit2);
        if(num1.getText().length() <= 0 || num2.getText().length() <= 0) {
            Toast.makeText(this, "숫자를 입력하세요!", Toast.LENGTH_LONG).show();
        }
        else {
            TextView result = (TextView) findViewById(R.id.result);
            double n1 = Double.parseDouble(num1.getText().toString());
            double n2 = Double.parseDouble(num2.getText().toString());
            if( n2 == 0 ){
                Toast.makeText(this, "나누는 값이 0이면 안됩니다.", Toast.LENGTH_LONG).show();
            }else {
                result.setText("계산 결과 : " + Double.toString(n1 / n2));
            }
        }
    }

    public void btnRest (View v){
        EditText num1 = (EditText)findViewById(R.id.edit1);
        EditText num2 = (EditText)findViewById(R.id.edit2);
        if(num1.getText().length() <= 0 || num2.getText().length() <= 0) {
            Toast.makeText(this, "숫자를 입력하세요!", Toast.LENGTH_LONG).show();
        }
        else {
            TextView result = (TextView) findViewById(R.id.result);
            double n1 = Double.parseDouble(num1.getText().toString());
            double n2 = Double.parseDouble(num2.getText().toString());
            if( n2 == 0 ){
                Toast.makeText(this, "나누는 값이 0이면 안됩니다.", Toast.LENGTH_LONG).show();
            }else {
                result.setText("계산 결과 : " + Double.toString(n1 % n2));
            }
        }
    }
}

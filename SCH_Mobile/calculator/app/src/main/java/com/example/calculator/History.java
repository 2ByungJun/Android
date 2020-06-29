package com.example.calculator;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class History extends AppCompatActivity{

    /***** Back *****/
    Button btnHisBack; // Navigator - MainActivity.xml

    /***** Text Variable *****/
    TextView historyDate;
    TextView historyProcess;
    TextView historyResult;

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        /***** Assigning Variable *****/
        /***** Main 페이지 전환 ******/
        historyDate = (TextView) findViewById(R.id.history_date);
        historyProcess = (TextView) findViewById(R.id.history_process);
        historyResult = (TextView) findViewById(R.id.history_result);

        /***** Navigator : Main ******/
        btnHisBack = (Button) findViewById(R.id.btn_his_back);

        /***** LinearLayout 동적생성 ******/
        linearLayout = (LinearLayout) findViewById(R.id.history_linear1);

        /***** Initialization Variable *****/
        historyDate.setText("");
        historyProcess.setText("");
        historyResult.setText("");

        /*****************************************************************
         * History Data Intent
         *****************************************************************/
        Intent intent = getIntent();
        int size = (int)intent.getSerializableExtra("size");
        Option option = (Option)intent.getSerializableExtra("history");
        ArrayList<Option> array = new ArrayList<Option>();
        array.add(option);

        System.out.println("@@@@@@@" + array.toString());

        for(Option rpOption : array){
            historyContainer(rpOption);
        }
//        historyDate.setText(option.opDate);
//        historyProcess.setText(option.opProcess);
//        historyResult.setText(option.opResult);

        /*****************************************************************
         * History Buttons on-Click
         *****************************************************************/
        btnHisBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /***** Main 페이지 전환 ******/
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void historyContainer(Option option) {
        Paint paint = new Paint();

        TextView date = new TextView(this);
        date.setText(option.opDate);
        date.setTextSize(15);
        date.setTextColor(Color.parseColor("#00796b"));
        date.setTypeface(null, Typeface.BOLD);

        TextView process = new TextView(this);
        process.setText(option.opProcess);
        process.setTextSize(20);

        TextView result = new TextView(this);
        result.setText(option.opResult);
        result.setTextSize(20);
        result.setTypeface(null, Typeface.BOLD);

        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp1.gravity = Gravity.LEFT;

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.gravity = Gravity.RIGHT;

        date.setLayoutParams(lp1);
        process.setLayoutParams(lp2);
        result.setLayoutParams(lp2);

        linearLayout.addView(date);
        linearLayout.addView(process);
        linearLayout.addView(result);
    }
}



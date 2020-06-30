package com.example.calculator;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.*;

public class History extends AppCompatActivity {

    /***** LinearLayout Variable *****/
    LinearLayout linearLayout;

    /***** History Variable *****/
    Intent intent;
    ArrayList<Option> optionList;
    LinkedList<Option> historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        /***** LinearLayout 동적생성 ******/
        linearLayout = findViewById(R.id.history_linear1);

        /*****************************************************************
         * History Data Intent
         *****************************************************************/
        intent = getIntent();
        optionList = (ArrayList<Option>) intent.getSerializableExtra("history");
        historyList = new LinkedList<>();

        /***** history Result save *****/
        for (int i = 0; i < optionList.size(); i++) {
            historyList.add(optionList.get(i));
        }

        /***** history Result *****/
        for (int i = 0; i < historyList.size(); i++) {
            historyContainer(historyList.get(i));
        }
    }

    /*****************************************************************
     * History Dynamical Create
     *****************************************************************/
    private void historyContainer(Option option) {
        /***** Text : 날짜 ******/
        TextView date = new TextView(this);
        date.setText(option.opDate);
        date.setTextSize(15);
        date.setTextColor(Color.parseColor("#00796b"));
        date.setTypeface(null, Typeface.BOLD);
        /***** Text : 수식 ******/
        TextView process = new TextView(this);
        process.setText(option.opProcess);
        process.setTextSize(20);
        /***** Text : 결과 ******/
        TextView result = new TextView(this);
        result.setText(option.opResult);
        result.setTextSize(20);
        result.setTypeface(null, Typeface.BOLD);
        /***** LinearLayout : 왼쪽 정렬 ******/
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp1.gravity = Gravity.LEFT;
        /***** LinearLayout : 오른쪽 정렬 ******/
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.gravity = Gravity.RIGHT;
        /***** 정렬 Setting ******/
        date.setLayoutParams(lp1);
        process.setLayoutParams(lp2);
        result.setLayoutParams(lp2);
        /***** Application ******/
        linearLayout.addView(date);
        linearLayout.addView(process);
        linearLayout.addView(result);
    }
}



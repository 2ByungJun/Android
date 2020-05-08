package com.example.direct5_3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edit;
    Button btn;
    TextView tes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT
        );
        LinearLayout linear=new LinearLayout(this);
        linear.setOrientation(LinearLayout.VERTICAL);
        setContentView(linear,params);

        edit=new EditText(this);
        edit.setHint("입력하세요");
        linear.addView(edit);
        btn=new Button(this);
        btn.setText("버튼입니다.");
        btn.setBackgroundColor(Color.rgb(0,255,0));
        linear.addView(btn);
        tes=new TextView(this);
        tes.setTextSize(30);
        tes.setTextColor(Color.MAGENTA);
        linear.addView(tes);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sentence;
                sentence=edit.getText().toString();
                tes.setText(sentence);
            }
        });
    }
}

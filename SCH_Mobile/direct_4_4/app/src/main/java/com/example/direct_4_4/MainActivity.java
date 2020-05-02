package com.example.direct_4_4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView text1, text2;
    Switch swAgree;
    RadioGroup rGroup1;
    RadioButton rArray[] = new RadioButton[3];
    Button btnDie, btnFirst;
    ImageView androidV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (TextView) findViewById(R.id.text1);
        swAgree = (Switch) findViewById(R.id.swAgree);

        text2 = (TextView) findViewById(R.id.text2);
        rGroup1 = (RadioGroup) findViewById(R.id.rGroup1);
        rArray[0] = (RadioButton) findViewById(R.id.rdo8);
        rArray[1] = (RadioButton) findViewById(R.id.rdo9);
        rArray[2] = (RadioButton) findViewById(R.id.rdo10);

        btnDie = (Button) findViewById(R.id.btnDie);
        btnFirst = (Button) findViewById(R.id.btnFirst);
        androidV = (ImageView) findViewById(R.id.androidV);

        swAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(swAgree.isChecked() == true) {
                    text2.setVisibility(View.VISIBLE);
                    rGroup1.setVisibility(View.VISIBLE);
                    btnDie.setVisibility(View.VISIBLE);
                    btnFirst.setVisibility(View.VISIBLE);
                    androidV.setVisibility(View.VISIBLE);
                }else
                {
                    text2.setVisibility(View.INVISIBLE);
                    rGroup1.setVisibility(View.INVISIBLE);
                    btnDie.setVisibility(View.INVISIBLE);
                    btnFirst.setVisibility(View.INVISIBLE);
                    androidV.setVisibility(View.INVISIBLE);
                }
            }
        });

        final int draw[] = { R.drawable.oreo8, R.drawable.pie9, R.drawable.q10};
        for (int i=0; i<draw.length; i++) {
            final int index = i;
            rArray[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    androidV.setImageResource(draw[index]);
                }
            });
        }


        btnDie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("정말로 종료하시겠습니까?");
                builder.setTitle("종료 알림창")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("종료 알림창");
                alert.show();
            }
        });

        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rGroup1.clearCheck();
                swAgree.setChecked(false);
                androidV.setVisibility(View.INVISIBLE);
            }
        });
    }
}

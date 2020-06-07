package com.example.ex7_;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("20154010 이병준 7-14~20");

        final Button button1 = (Button) findViewById(R.id.button1);
        final Button button2 = (Button) findViewById(R.id.button2);
        final Button button3 = (Button) findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("대화실습");
                dlg.setMessage("대화상자 버튼입니다.");
                dlg.setIcon(R.mipmap.ic_launcher);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "확인을 누르셨습니다!", Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String[] versionArray = new String[] {"오레오", "파이", "큐(10)"};
                AlertDialog.Builder dlg2 = new AlertDialog.Builder(MainActivity.this);
                dlg2.setTitle("좋아하는 버전은?");
                dlg2.setIcon(R.mipmap.ic_launcher);
                dlg2.setSingleChoiceItems(versionArray, 0,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                button2.setText(versionArray[which]);
                            }
                        });
                dlg2.setPositiveButton("닫기",null);
                dlg2.show();
            }
        });

        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String[] versionArray = new String[] {"오레오", "파이", "큐(10)"};
                final boolean[] chechArray = new boolean[] {true, false, false};

                AlertDialog.Builder dlg3 = new AlertDialog.Builder(MainActivity.this);
                dlg3.setTitle("좋아하는 버전은?");
                dlg3.setIcon(R.mipmap.ic_launcher);
                dlg3.setMultiChoiceItems(versionArray, chechArray,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                button3.setText(versionArray[which]);
                            }
                        });

                dlg3.setPositiveButton("닫기",null);
                dlg3.show();
            }
        });

    }
}

package com.example.direct7_2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout baseLayout;
    Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("20154010 이병준 직접7-2");

        baseLayout = (LinearLayout) findViewById(R.id.baseLayout);
        button1 = (Button) findViewById(R.id.button1);
        registerForContextMenu(button1);
        button2 = (Button) findViewById(R.id.button2);
        registerForContextMenu(button2);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if( v == button1){
            menu.setHeaderTitle("배경색 변경");

            menu.add(0,1,0,"배경색(빨강)");
            menu.add(0,2,0,"배경색(파랑)");
            menu.add(0,3,0,"배경색(초록)");
        }
        if( v == button2){
            menu.add(0,4,0,"버튼 45도 회전");
            menu.add(0,5,0,"버튼 2배 확대");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                baseLayout.setBackgroundColor(Color.RED);
                return true;
            case 2:
                baseLayout.setBackgroundColor(Color.BLUE);
                return true;
            case 3:
                baseLayout.setBackgroundColor(Color.GREEN);
                return true;
            case 4:
                button2.setRotation(45);
                return true;
            case 5:
                button2.setScaleX(2);
                return true;
        }

        return false;
    }
}

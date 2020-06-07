package com.example.ch7_project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    EditText edtAngle;
    ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("20154010 이병준 직접7-1");

        edtAngle = (EditText) findViewById(R.id.edtAngle);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu1, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.itemRotate:
                imageView1.setRotation(Float.parseFloat(edtAngle.getText().toString()));
                return true;
            case R.id.dog1:
                imageView1.setImageResource(R.drawable.dog1);
                item.setChecked(true);
                return true;
            case R.id.dog2:
                imageView1.setImageResource(R.drawable.dog2);
                item.setChecked(true);
                return true;
            case R.id.dog3:
                imageView1.setImageResource(R.drawable.dog3);
                item.setChecked(true);
                return true;
        }
        return false;
    }
}

package com.example.hiandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    Button button1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

//    public void btnNaver(View v)
//    {
//        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"))
//        startActivity(myIntent)
//    }
//
//    public void btnCall(View v)
//    {
//        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:119"))
//        startActivity(myIntent)
//    }
}

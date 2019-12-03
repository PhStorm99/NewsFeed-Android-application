package com.example.myapplication;

import android.content.Intent;
import android.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button car;
    Button receipe;
    Button currency;
    Button News;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);



        car = findViewById(R.id.car);
        car.setOnClickListener( c -> {
            Intent nextPage = new Intent(MainActivity.this, this.getClass());
            startActivity(nextPage);
        });

        News = findViewById(R.id.News);
        News.setOnClickListener( c -> {
            Intent nextPage = new Intent(MainActivity.this, news_first.class);
            startActivity(nextPage);
        });

        receipe = findViewById(R.id.Recipe);
        receipe.setOnClickListener(c -> {
            Intent nextPage = new Intent(MainActivity.this, this.getClass());
            startActivity(nextPage);
        });

        currency = findViewById(R.id.Foreign_currency);
        currency.setOnClickListener(c -> {
            Intent nextPage = new Intent(MainActivity.this,  this.getClass());
            startActivity(nextPage);
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent nextPage;
        switch(item.getItemId()){
            case R.id.car_img:
                nextPage = new Intent(MainActivity.this, this.getClass());
                startActivity(nextPage);
                break;

            case R.id.currency_img:
                nextPage = new Intent(MainActivity.this, this.getClass());
                startActivity(nextPage);
                break;

            case R.id.news_img:
                nextPage = new Intent(MainActivity.this, news_first.class);
                startActivity(nextPage);
                break;
                case R.id.receipe_img:
                nextPage = new Intent(MainActivity.this, this.getClass());
                startActivity(nextPage);
                break;
        }return true; }

}
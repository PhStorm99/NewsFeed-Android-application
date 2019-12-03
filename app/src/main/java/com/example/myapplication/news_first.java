package com.example.myapplication;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

//this is first page of news and its launched when news button is clicked from first page
public class news_first extends AppCompatActivity {


            private EditText editText1;
            private Button button ;
            SharedPreferences sp1;
            ArrayList<NewsFeed> contactList =new ArrayList<>();
Toolbar toolbar;
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.first);
                toolbar = findViewById(R.id.toolbar);
               // setSupportActionBar(toolbar);
                editText1= (EditText) findViewById(R.id.search);
                sp1 = getSharedPreferences("LastSearched", Context.MODE_PRIVATE);
                String savedString = sp1.getString("Last search Item", "");
                button=(Button) findViewById(R.id.news_button);
                editText1.setText(savedString);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View e) {
                        if ( editText1.length() == 0)
                        {// when there is no search term entered then
                            Toast.makeText(news_first.this.getBaseContext(), "please enter search term", Toast.LENGTH_LONG).show();
                        }
                        else
                            {
                                // if we entered serach term it navigate to second page
                            Intent i = new Intent(news_first.this, news_second_search.class);
                            i.putExtra("LastNewsSearch", editText1.getText().toString());
                            startActivity(i);
                            }
                    }
                });
            }
            @Override
            protected void onPause()
            {
                super.onPause();
                // sharedpreference use to store last term what you typed for searched
                SharedPreferences.Editor edit = sp1.edit();
                String Typed = editText1.getText().toString();
                edit.putString("Last search Item",Typed);
                edit.commit();
            }
            @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent nextPage;
        switch(item.getItemId()){
            case R.id.car_img:
                nextPage = new Intent(news_first.this, this.getClass());
                startActivity(nextPage);
                break;

            case R.id.currency_img:
                nextPage = new Intent(news_first.this, this.getClass());
                startActivity(nextPage);
                break;


                case R.id.receipe_img:
                nextPage = new Intent(news_first.this, this.getClass());
                startActivity(nextPage);
                break;
            case R.id.help:
                //alertExample();
                break;
        }return true; }
//   public void alertExample() {
//       View middle = getLayoutInflater().inflate(R.layout.news_alert, null);
//
//
//     android.support.v7.app.AlertDialog.Builder builder;
//       builder = new android.support.v7.app.AlertDialog.Builder(this);
//       builder.setMessage("Author Name:Harsh Patel" + "\n\n" +
//             "Activity version: v1.0.0" + "\n\n" +"Instruction:News application to search news,save news and delete news")
//               .setView(middle);
//       builder.create().show();
//
//    }
}





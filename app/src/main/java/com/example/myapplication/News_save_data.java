package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class News_save_data extends AppCompatActivity {


    private static final int NEWS_ARTICLES_MAX_LENGTH = 1000;
    private ListView listView;
    private ProgressBar Progessbar;

    private ArrayList<NewsFeed> newsArticles = new ArrayList<>(NEWS_ARTICLES_MAX_LENGTH);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        listView = (ListView) findViewById(R.id.news_titles_listview);
        Progessbar = (ProgressBar) findViewById(R.id.progressBar);
        getSavedResultsFromDB();
    }

    //Retrieving from databse
    public void getSavedResultsFromDB() {
        MyDatabaseOpenHelper helper = new MyDatabaseOpenHelper(News_save_data.this);
        final SQLiteDatabase db = helper.getReadableDatabase();

        String[] strings = {
                MyDatabaseOpenHelper.COL_TITLE,
                MyDatabaseOpenHelper.COL_desription
        };
        String[] columns = strings;
        Cursor cursor = db.query(MyDatabaseOpenHelper.TABLE_NAME, columns, null,
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            newsArticles.add(new NewsFeed(cursor.getString(0),
                    cursor.getString(1), true));
        }
        if (!newsArticles.isEmpty()) {
            //viewNewsArticles();
        }
    }
//    List list = new ArrayList();
//        MyDatabaseOpenHelper helper = new MyDatabaseOpenHelper(News_save_data.this);
//
//    String selectQuery = "Select * FROM " +MyDatabaseOpenHelper.TABLE_NAME;
//
//
//        SQLiteDatabase db = helper.getReadableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                    NewsFeeddetail feeddetail = new NewsFeeddetail();
//                    feeddetail.setTitle(cursor.getInt(0));
//
//                    //add details to  list
//                    list.add(feeddetail);
//            }
//            while (cursor.moveToNext());
//
//        }
//        db.close();
//        return list;



    private void viewNewsArticles() {
        NewsArticleAdapter<NewsFeed> listAdapter = new NewsArticleAdapter(getBaseContext(), newsArticles);
        listView.setAdapter(listAdapter);
        Progessbar.setVisibility(View.INVISIBLE);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // Snackbar.make(view, "You clicked back button", Snackbar.LENGTH_LONG).show();
            //    Intent nextPage = new Intent(News_save_data.this, DetailedNewsArticleActivity.class);
            //  NewsArticle article = newsArticles.get(position);
            //    saveNewsArticleInSharedPreference(article);
            //  startActivity(nextPage);
        });
    }

    //private void viewNewsArticles() {
    //  NewsArticleAdapter<NewsFeed> listAdapter = new NewsArticleAdapter(getBaseContext(), newsArticles);
    //  listView.setAdapter(listAdapter);
    // newsFeedProgessbar.setVisibility(View.INVISIBLE);
    //   listView.setOnItemClickListener((parent, view, position, id) -> {
    //    Snackbar.make(view, "You clicked back button", Snackbar.LENGTH_LONG).show();
    //   Intent nextPage = new Intent(News_save_data.this, NewsFeeddetail.class);
    //   NewsFeed article = newsArticles.get(position);
    //  saveNewsArticleInSharedPreference(article);
    //  startActivity(nextPage);
    //  });
//    }
    protected class NewsArticleAdapter<T> extends ArrayAdapter<NewsFeed> {

        public NewsArticleAdapter(Context context, ArrayList<NewsFeed> articles) {
            super(context, 0, articles);
        }



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
                nextPage = new Intent(News_save_data.this, this.getClass());
                startActivity(nextPage);
                break;

            case R.id.currency_img:
                nextPage = new Intent(News_save_data.this, this.getClass());
                startActivity(nextPage);
                break;


            case R.id.receipe_img:
                nextPage = new Intent(News_save_data.this, this.getClass());
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
//        builder.setMessage("Author Name:Harsh Patel" + "\n\n" +
//            "Activity version: v1.0.0" + "\n\n" +"Instruction:News application to search news,save news and delete news")
//
//               .setView(middle);
//       builder.create().show();
//
//    }
}
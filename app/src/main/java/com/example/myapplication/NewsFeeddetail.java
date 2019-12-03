package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import java.io.InputStream;


public class NewsFeeddetail extends AppCompatActivity {
    private TextView title1;
    private TextView full;
    private TextView url;
    private Button savenews, B;
    private TextView weather;
    private ImageView w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feeddetail);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        title1 = (TextView) findViewById(R.id.title);

        full = (TextView) findViewById(R.id.description);

        url = (TextView) findViewById(R.id.url);

        weather = (TextView) findViewById(R.id.image);

        savenews = (Button) findViewById(R.id.save_article);

        Button web = (Button) findViewById(R.id.button);

        w = (ImageView) findViewById(R.id.i1);
        Intent intent = getIntent();


        String srticle = intent.getStringExtra("title2");
        Log.i("title2", intent.getStringExtra("title2"));
        title1.setText(srticle);


        String full1 = intent.getStringExtra("text");
        Log.i("description", intent.getStringExtra("text"));
        full.setText(full1);


        String URL1 = intent.getStringExtra("articleUrl");
        Log.i("description", intent.getStringExtra("articleUrl"));
        url.setText(URL1);


        String ima_url = intent.getStringExtra("Url");
        weather.setText(ima_url);

        //save button click: data saved in database
        MyDatabaseOpenHelper dbOpener = new MyDatabaseOpenHelper(this);
        SQLiteDatabase db = dbOpener.getWritableDatabase();
        savenews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button c = (Button) v;
                String click = c.getText().toString();
                ContentValues newRowValues = new ContentValues();
                newRowValues.put(MyDatabaseOpenHelper.COL_TITLE, srticle);
                newRowValues.put(MyDatabaseOpenHelper.COL_desription, full1);

               // insert the table name in newId
                long newId = db.insert(MyDatabaseOpenHelper.TABLE_NAME, null, newRowValues);
                Log.e(this.getClass().getName(), "Saved data to DB");

                Snackbar.make(v,"Saved data",Snackbar.LENGTH_LONG).show();
                System.out.println(newId);

            }

        });
        // open full article in the web browser
        web.setOnClickListener(e ->
        {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL1));
            startActivity(browserIntent);
        });

        ImageView img_add = (ImageView) findViewById(R.id.i1);
        //on button click it navigate to newsfeedresultactivity which dispaly saved article
        Button w1 = (Button) findViewById(R.id.button2);
        w1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newsFeedIntent = new Intent(getApplicationContext(),News_save_data.class);
                startActivity(newsFeedIntent);

            }
        });
        // open image in image view from image url
        new DownloadImageTask((ImageView) findViewById(R.id.i1))
                .execute(ima_url);
    }
        private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
            ImageView bmImage;

            public DownloadImageTask(ImageView bmImage) {
                this.bmImage = bmImage;
            }

            protected Bitmap doInBackground(String... urls) {
                String urldisplay = urls[0];
                Bitmap mIcon11 = null;
                try {
                    InputStream in = new java.net.URL(urldisplay).openStream();
                    mIcon11 = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                return mIcon11;
            }

            protected void onPostExecute(Bitmap result) {
                bmImage.setImageBitmap(result);
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
                nextPage = new Intent(NewsFeeddetail.this, this.getClass());
                startActivity(nextPage);
                break;

            case R.id.currency_img:
                nextPage = new Intent(NewsFeeddetail.this, this.getClass());
                startActivity(nextPage);
                break;


            case R.id.receipe_img:
                nextPage = new Intent(NewsFeeddetail.this, this.getClass());
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
//         builder.setMessage("Author Name:Harsh Patel" + "\n\n" +
//            "Activity version: v1.0.0" + "\n\n" +"Instruction:News application to search news,save news and delete news")
//
//               .setView(middle);
//       builder.create().show();
//
//    }
    }










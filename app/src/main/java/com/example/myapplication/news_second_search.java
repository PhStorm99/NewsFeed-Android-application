package com.example.myapplication;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class news_second_search extends AppCompatActivity {

    private String firstsearchedWord;
    ProgressBar progressBar;
    private ListView listView;
    private List<NewsFeed> listnews = new ArrayList<>();
    private NewsListAdaptor newsListAdaptor1;
    private String title;
    private String text1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_second_search);
        listnews.clear();
        ProgressBar progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);
        listView = (ListView) findViewById(R.id.listview);
        Intent intent = getIntent();
        firstsearchedWord = intent.getStringExtra("LastNewsSearch");
        Log.i("LastNewsSearch", firstsearchedWord);
        new DataFromAPI().execute(firstsearchedWord);
        newsListAdaptor1 = new NewsListAdaptor(listnews);
        Log.i("Adaptor1", newsListAdaptor1.toString());
        listView.setAdapter(newsListAdaptor1);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Snackbar.make(view, "searching item", Snackbar.LENGTH_LONG).show();
            NewsFeed articles = listnews.get(position);
            Intent i = new Intent(news_second_search.this, NewsFeeddetail.class);
            i.putExtra("title2", articles.getTitle());
            Log.i("title2", articles.getTitle());
            i.putExtra("text", articles.getDescription());
            i.putExtra("articleUrl", articles.getUrl());
            i.putExtra("Url", articles.getImg());
            startActivity(i);
        });
    }
    protected class DataFromAPI extends AsyncTask<String, Integer, String>
    {
        HttpURLConnection connection=null;
        @Override
        protected String doInBackground(String... strings) {
            String query = strings[0];
            publishProgress(25);
            URL url = null;
            String Result=null;
            try {
                url = new URL("https://newsapi.org/v2/everything?apiKey=e3b1fa5b69ee4751bdc4fbd420265612&q=" + URLEncoder.encode(query, "UTF-8"));
                connection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = null;
                publishProgress(50);
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                Result = sb.toString();
                JSONObject jObject = new JSONObject(Result);
                JSONArray mArray = jObject.getJSONArray("articles");
                for (int i = 0; i < mArray.length(); i++) {
                    JSONObject object = mArray.getJSONObject(i);
                    title = object.getString("title") + "\n";
                    text1 = object.getString("description");
                    String url2 = object.getString("url");
                    String img = object.getString("urlToImage");
                    NewsFeed a = new NewsFeed(title,text1);
                    a.setTitle(title);
                    a.setDescription(text1);
                    a.setUrl(url2);
                    a.setImg(img);
                    listnews.add(a);
                }publishProgress(75);
            } catch (Exception e1) {
                e1.printStackTrace();
                Log.i("Error", e1.getMessage());
            }
            return Result; }
            @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setProgress(100);
            newsListAdaptor1.notifyDataSetChanged();
            progressBar.setVisibility(View.INVISIBLE);
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar = (ProgressBar)findViewById(R.id.progressBar); }
    }


    // HERE CREATED NEW NEWSLISTADAPTOR
    protected class NewsListAdaptor extends BaseAdapter {
        private List<NewsFeed> newsFeed = null;

        public NewsListAdaptor(List<NewsFeed> newsFeedList)
        {
            this.newsFeed = newsFeedList;
        }
        @Override
        public int getCount()
        {
            return newsFeed.size();
        }
        @Override
        public Object getItem(int position)
        {
            return newsFeed;
        }
        @Override
        public long getItemId(int position)
        {
            return newsFeed.get(position).getId();
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        { LayoutInflater inflater = getLayoutInflater();
            View listItem = inflater.inflate(R.layout.activity_news_listitem, parent, false);
            TextView newsFeedRowS;
            newsFeedRowS = (TextView) listItem.findViewById(R.id.news_list);
            newsFeedRowS.setText(newsFeed.get(position).getTitle());
            return listItem;
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
                nextPage = new Intent(news_second_search.this, this.getClass());
                startActivity(nextPage);
                break;

            case R.id.currency_img:
                nextPage = new Intent(news_second_search.this, this.getClass());
                startActivity(nextPage);
                break;


            case R.id.receipe_img:
                nextPage = new Intent(news_second_search.this, this.getClass());
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
//           "Activity version: v1.0.0" + "\n\n" +"Instruction:News application to search news,save news and delete news")
//
//               .setView(middle);
//       builder.create().show();
//
//    }
}


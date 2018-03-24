package com.example.alexandramolina.tarea4_alexandramolina;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.GridView;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    String link = "http://www.imdb.com/list/ls064079588/";
    ArrayList<Movie> movies = new ArrayList<>();

    @Override
    protected Bitmap doInBackground(String... urls){

        try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection httpURLConnection;
            String line;

            try {
                url = new URL(urls[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                BufferedReader reader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();

                while((line = reader.readLine()) != null){
                    stringBuilder.append(line);
                }

                reader.close();
                inputStreamReader.close();

                result = stringBuilder.toString();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            //try {
                //Log.d("Result:", result);

            //} catch (JSONException e) {
            //    e.printStackTrace();
            //}

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask downloadTask = new DownloadTask();
        String results;

        GridView gridView = findViewById(R.id.gridView);
        MovieAdapter adapter;

        try {
            results = downloadTask.execute(link).get();

            Document doc = Jsoup.parse(results);

            Elements names = doc.select(".lister-list .lister-item .lister-item-content .lister-item-header a");
            Elements ratings = doc.select(".lister-list .lister-item .lister-item-content .ratings-bar .inline-block strong");
            //Elements metascores = doc.select(".lister-list .lister-item .lister-item-content .ratings-bar .inline-block .ratings-metascore span");

            for(int i = 0; i < names.size(); i++) {
                movies.add(new Movie());
                String name = names.get(i).text().substring(names.get(i).text().lastIndexOf('.') + 1);
                movies.get(i).setName(name);
                String rating = ratings.get(i).text().substring(ratings.get(i).text().lastIndexOf(',') + 1);
                movies.get(i).setRating(Float.parseFloat(rating));
                //String metascore = metascores.get(i).text().substring(metascores.get(i).text().lastIndexOf('7'));
                //movies.get(i).setMetascore(Integer.parseInt(metascore));
                //Log.d("Id:", Integer.toString(i));
                //Log.d("Metas:", metascore);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        adapter = new MovieAdapter(this, R.layout.movielistview, movies);
        gridView.setAdapter(adapter);
    }
}

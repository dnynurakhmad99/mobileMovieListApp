package com.example.movielist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.movielist.Adapter.AdapterListMovie;
import com.example.movielist.Model.Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListMovieActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private final List<Movies> moviesList = new ArrayList<>();

    @BindView(R.id.lst_movies)
    RecyclerView lstMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movie);
        ButterKnife.bind(this);

        lstMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        lstMovies.setHasFixedSize(true);
        lstMovies.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        AdapterListMovie adapterListMovie = new AdapterListMovie(this, moviesList);
        lstMovies.setAdapter(adapterListMovie);

        addItemsFromJSON();
    }

    private void addItemsFromJSON() {
        try {
            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject itemObj = jsonArray.getJSONObject(i);

                Movies movies = new Movies();
//                movies.setBackdropPath(itemObj.getString("backdrop_path"));
                movies.setPosterPath(itemObj.getString("poster_path"));
                movies.setId(itemObj.getInt("id"));
                movies.setTitle(itemObj.getString("title"));
                movies.setOverview(itemObj.getString("overview"));
                movies.setReleaseDate(itemObj.getString("release_date"));
                movies.setVoteAverage(itemObj.getDouble("vote_average"));

                moviesList.add(movies);
            }
        } catch (JSONException | IOException e){
            Log.d(TAG, "addItemsFromJSON: ", e);
        }
    }

    private String readJSONDataFromFile() throws IOException {
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {
            String jsonString;
            inputStream = getResources().openRawResource(R.raw.list_movie);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            while((jsonString = bufferedReader.readLine()) != null){
                builder.append(jsonString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(builder);
    }
}
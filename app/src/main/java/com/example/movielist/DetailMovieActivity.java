package com.example.movielist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailMovieActivity extends AppCompatActivity {

    ImageView backdrop, poster;
    TextView txtTitle, txtVote, txtRelease, txtDesc;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        TextView iniTitle = findViewById(R.id.txtTitle);
//        ImageView iniBackdrop = findViewById(R.id.backdrop);
        ImageView iniPoster = findViewById(R.id.poster);
        TextView iniVote = findViewById(R.id.txtVote);
        TextView iniRelease = findViewById(R.id.txtRelease);
        TextView iniDesc = findViewById(R.id.txtDesc);

        String title = "";
//        String backdrop = "";
        String poster = "";
        String vote = "";
        String release = "";
        String overview = "";

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            title = extras.getString("title");
//            backdrop = extras.getString("backdrop");
            poster = extras.getString("poster");
            vote = extras.getString("vote");
            release = extras.getString("release");
            overview = extras.getString("overview");
        }

        iniTitle.setText(title);
        iniVote.setText(vote);
        iniRelease.setText(release);
        iniDesc.setText(overview);

//        this.context = context;
//        Picasso.with(context).load(backdrop).fit()
//                .placeholder(R.drawable.ic_launcher_background)
//                .error(R.drawable.ic_launcher_background)
//                .into(iniBackdrop);

        this.context = context;
        Picasso.with(context).load(poster).fit()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(iniPoster);
    }
}
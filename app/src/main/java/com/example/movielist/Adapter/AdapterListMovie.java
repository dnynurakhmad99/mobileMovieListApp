package com.example.movielist.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielist.DetailMovieActivity;
import com.example.movielist.ListMovieActivity;
import com.example.movielist.MainActivity;
import com.example.movielist.Model.Movies;
import com.example.movielist.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterListMovie extends RecyclerView.Adapter<AdapterListMovie.ViewHolder> {

    Context context;
    List<Movies> moviesList;

    public AdapterListMovie(Context context, List<Movies> moviesList){
        this.context = context;
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_movies_layout, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListMovie.ViewHolder holder, int position) {
        String id = moviesList.get(position).getId().toString();
//        String backdropPath = "https://themoviedb.org/t/p/w500" + moviesList.get(position).getBackdropPath();
        String posterPath = "https://themoviedb.org/t/p/w500" + moviesList.get(position).getPosterPath();
        Picasso.with(context).load(posterPath).fit().centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.poster);

        holder.txtTitle.setText(moviesList.get(position).getTitle());
        holder.txtDesc.setText(moviesList.get(position).getOverview());
        holder.txtRelease.setText(moviesList.get(position).getReleaseDate());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(context, DetailMovieActivity.class);

                intent.putExtra("title", moviesList.get(position).getTitle());
                intent.putExtra("poster", posterPath);
//                intent.putExtra("backdrop", backdropPath);
                intent.putExtra("vote", moviesList.get(position).getVoteAverage().toString());
                intent.putExtra("release", moviesList.get(position).getReleaseDate());
                intent.putExtra("overview", moviesList.get(position).getOverview());

                context.startActivities(new Intent[]{intent});
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView txtTitle, txtDesc, txtRelease;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.poster);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDesc = itemView.findViewById(R.id.txtDesc);
            txtRelease = itemView.findViewById(R.id.txtRelease);
        }
    }
}

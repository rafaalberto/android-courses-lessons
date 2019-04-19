package br.com.recyclerview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.recyclerview.R;
import br.com.recyclerview.model.Movie;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder> {

    private List<Movie> movies;

    public MoviesAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View listItemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_movies, viewGroup, false);
        return new MoviesAdapterViewHolder(listItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapterViewHolder moviesAdapterViewHolder, int position) {
        Movie movie = movies.get(position);
        moviesAdapterViewHolder.textViewTitle.setText(movie.getTitle());
        moviesAdapterViewHolder.textViewYear.setText(movie.getYear());
        moviesAdapterViewHolder.textViewGenre.setText(movie.getGenre());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MoviesAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textViewYear;
        private TextView textViewGenre;

        public MoviesAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewYear = itemView.findViewById(R.id.text_view_year);
            textViewGenre = itemView.findViewById(R.id.text_view_genre);
        }
    }

}

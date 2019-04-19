package br.com.recyclerview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.recyclerview.R;
import br.com.recyclerview.adapter.MoviesAdapter;
import br.com.recyclerview.model.Movie;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMovies;
    private List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewMovies = findViewById(R.id.recycler_view_movies);
        recyclerViewMovies.setHasFixedSize(true);
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMovies.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerViewMovies.setAdapter(new MoviesAdapter(fetchMovies()));
    }

    private List<Movie> fetchMovies() {
        movies = new ArrayList<>();
        movies.add(new Movie("Homem Aranha - De volta ao lar", "2017", "Ação"));
        movies.add(new Movie("Mulher Maravilha", "2017", "Ação"));
        movies.add(new Movie("Liga da Justiça", "2017", "Aventura/Ação"));
        movies.add(new Movie("Capitão América", "2016", "Aventura/Ficção"));
        movies.add(new Movie("Pica-Pau: O Filme", "2018", "Comédia/Animação"));
        movies.add(new Movie("Meu malvado favorito 3", "2017", "Comédia"));
        movies.add(new Movie("Carros 3", "2019", "Animação"));
        return movies;
    }
}

package br.com.recyclerview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.recyclerview.R;
import br.com.recyclerview.adapter.MoviesAdapter;
import br.com.recyclerview.model.Movie;
import br.com.recyclerview.utils.RecyclerItemClickListener;

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

        List<Movie> movies = fetchMovies();
        Collections.sort(movies, (previousItem, nextItem) -> previousItem.getTitle().compareTo(nextItem.getTitle()));

        recyclerViewMovies.setAdapter(new MoviesAdapter(movies));
        recyclerViewMovies.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),
                recyclerViewMovies,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Movie movie = movies.get(position);
                        Toast.makeText(getApplicationContext(), "Filme selecionado: " + movie.getTitle(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) { }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) { }
                }
        ));
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
        movies.add(new Movie("Batman", "2012", "Ação"));
        movies.add(new Movie("Família Buscapé", "1994", "Comédia"));
        movies.add(new Movie("Minions", "2015", "Animação"));
        return movies;
    }
}

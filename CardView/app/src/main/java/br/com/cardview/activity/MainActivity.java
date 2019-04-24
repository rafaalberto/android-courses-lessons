package br.com.cardview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.cardview.R;
import br.com.cardview.adapter.PostAdapter;
import br.com.cardview.model.Post;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new PostAdapter(fetchPosts()));
    }

    private List<Post> fetchPosts() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("Rafael Alberto", R.drawable.imagem3, "First time in Paris!"));
        posts.add(new Post("John Lint", R.drawable.imagem1, "Here we go again!"));
        posts.add(new Post("Mary Nix", R.drawable.imagem4, "This place is really awesome!"));
        posts.add(new Post("Tim Tones", R.drawable.imagem2, "Another trip to enjoy!"));
        return posts;
    }
}

package br.com.cardview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.cardview.R;
import br.com.cardview.model.Post;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Post> posts;

    public PostAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.post_layout, viewGroup, false);
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder postViewHolder, int position) {
        Post post = posts.get(position);
        postViewHolder.textViewName.setText(post.getName());
        postViewHolder.imageViewPhoto.setImageResource(post.getImage());
        postViewHolder.textViewContent.setText(post.getContent());
    }

    @Override
    public int getItemCount() {
        return this.posts.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private ImageView imageViewPhoto;
        private TextView textViewContent;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            imageViewPhoto = itemView.findViewById(R.id.imageViewPhoto);
            textViewContent = itemView.findViewById(R.id.textViewContent);
        }
    }

}

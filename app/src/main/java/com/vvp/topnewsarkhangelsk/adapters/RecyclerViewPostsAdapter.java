package com.vvp.topnewsarkhangelsk.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vvp.topnewsarkhangelsk.R;
import com.vvp.topnewsarkhangelsk.repository.room.Post;
import com.vvp.topnewsarkhangelsk.utils.ContextApp;

import java.util.ArrayList;

public class RecyclerViewPostsAdapter extends RecyclerView.Adapter <RecyclerViewPostsAdapter.ViewHolder> {

    // массив
    ArrayList <Post> arrayPosts;

    //раздуватель
    private LayoutInflater layoutInflater;


    // конструктор
    public RecyclerViewPostsAdapter(ArrayList<Post> arrayPosts, Context context) {
        this.arrayPosts = arrayPosts;
        this.layoutInflater = LayoutInflater.from(context);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.post_card, parent,false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Post post = arrayPosts.get(position);


        holder.textTitlePublic.setText(post.getTitlePublic());
        holder.textPost.setText(post.getTextPost());

//        Glide
//                .with(ContextApp.getInstance())
//                .load(post.getPicture())
//                .into(holder.imagePost);

        holder.textQuantityLikes.setText(String.valueOf(post.getQuantityLikes()));
        holder.textQuantityComments.setText(String.valueOf(post.getQuantityComments()));


    }

    @Override
    public int getItemCount() {
        return arrayPosts.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textTitlePublic;
        TextView textPost;
        ImageView imagePost;
        TextView textQuantityLikes;
        TextView textQuantityComments;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textTitlePublic = itemView.findViewById(R.id.textTitlePublic);
            textPost = itemView.findViewById(R.id.textPost);
            imagePost = itemView.findViewById(R.id.imagePost);
            textQuantityLikes = itemView.findViewById(R.id.textQuantityLikes);
            textQuantityComments = itemView.findViewById(R.id.textQuantityComments);
        }
    }
}

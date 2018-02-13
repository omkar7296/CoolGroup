package com.omkar.coolgroup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ArrayList<Post> posts = new ArrayList<>();

        posts.add(new Post(
                "Batman",
                "Miami, Florida",
                "https://avatarfiles.alphacoders.com/717/71761.jpg",
                "Hi, Check out my audio",
                R.raw.sound1
        ));

        posts.add(new Post(
                "Superman",
                "Pittsburgh, Pennsylvania",
                "https://www.screengeek.net/wp-content/uploads/2017/04/superman-origin.jpg",
                "Hi, check this out",
                R.raw.sound
        ));

        posts.add(new Post(
                "Wonder Woman",
                "Hoboken, New Jersey",
                "https://avatarfiles.alphacoders.com/916/91630.jpg",
                "Here the description of the post is displayed",
                R.raw.sound1
        ));

        posts.add(new Post(
                "Flash",
                "Pittsburgh, Pennsylvania",
                "https://avatarfiles.alphacoders.com/916/91671.jpg",
                "Here the description of the post is displayed",
                R.raw.sound
        ));

        posts.add(new Post(
                "Aquaman",
                "Pittsburgh, Pennsylvania",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRg7JcMPcUE_CpNH565NhO9RR5XWgcJlOwRYAmsF0jMsbDDRvzayw",
                "Here the description of the post is displayed",
                R.raw.sound1
        ));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new Posts_Adapter(posts,this));

    }
}

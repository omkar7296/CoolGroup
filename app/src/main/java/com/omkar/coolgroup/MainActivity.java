package com.omkar.coolgroup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.post)
    Button post_button;

    @BindView(R.id.cancel)
    Button cancel_button;

    @BindView(R.id.record_button)
    ImageView record_button;

    @BindView(R.id.pause_button)
    ImageView pause_button;

    @BindView(R.id.profile_pic_header)
    ImageView profile_pic_header;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Picasso.with(getApplicationContext()).load("https://avatarfiles.alphacoders.com/717/71761.jpg")
                .fit()
                .centerCrop()
                .into(profile_pic_header);

        record_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                record_button.setVisibility(View.INVISIBLE);
                pause_button.setVisibility(View.VISIBLE);
            }
        });

        pause_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //record_button.setVisibility(View.INVISIBLE);
                pause_button.setVisibility(View.INVISIBLE);
                post_button.setVisibility(View.VISIBLE);
                cancel_button.setVisibility(View.VISIBLE);
            }
        });

        post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                post_button.setVisibility(View.INVISIBLE);
                cancel_button.setVisibility(View.INVISIBLE);
                record_button.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, "Post uploaded successfully", Toast.LENGTH_SHORT).show();
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                post_button.setVisibility(View.INVISIBLE);
                cancel_button.setVisibility(View.INVISIBLE);
                record_button.setVisibility(View.VISIBLE);
            }
        });



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

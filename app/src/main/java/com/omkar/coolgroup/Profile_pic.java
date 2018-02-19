package com.omkar.coolgroup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Profile_pic extends AppCompatActivity {

    @BindView(R.id.Profile_pic_Imageview)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_pic);
        ButterKnife.bind(this);

        int Profile_pic_id = getIntent().getIntExtra("Profile_pic_id",0);
        String name = getIntent().getStringExtra("name");
        this.setTitle(name);
        imageView.setImageResource(Profile_pic_id);


    }
}

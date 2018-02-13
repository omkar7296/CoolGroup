package com.omkar.coolgroup;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by omkar on 2/9/2018.
 */

public class Posts_Holder extends RecyclerView.ViewHolder {


    @BindView(R.id.profile_pic)
    ImageView profile_pic;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.location)
    TextView location;

    @BindView(R.id.play)
    ImageView play;

    @BindView(R.id.pause)
    ImageView pause;


    @BindView(R.id.desc)
    TextView desc;

//    @BindView(R.id.like)
//    ImageView like;

    @BindView(R.id.scrubber)
    SeekBar scrubber;



    public Posts_Holder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}

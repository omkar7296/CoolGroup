package com.omkar.coolgroup;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by omkar on 2/9/2018.
 */

public class Posts_Adapter extends RecyclerView.Adapter<Posts_Holder> {

    ArrayList<Post> posts;
    Context context;
    Handler handler;
    Runnable runnable;
    MediaPlayer m_mediaPlayer;
    ImageView m_play,m_pause;

    public Posts_Adapter(ArrayList<Post> posts,Context context) {
        this.posts = posts;
        this.context = context;
    }

    @Override
    public Posts_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.posts_layout,parent,false);
        context= view.getContext();
        return new Posts_Holder(view);
    }

    @Override
    public void onBindViewHolder(final Posts_Holder holder, final int position) {
        final Post post = posts.get(position);

        //final ImageView like = holder.like;
        holder.name.setText(post.name);
        holder.location.setText(post.location);
        holder.desc.setText(post.desc);

        final MediaPlayer mediaPlayer;
        MediaPlayer inter_media_player = new MediaPlayer();

        if(posts.get(position).getAudio_path() == "")
        {
            final MediaPlayer mediaPlayer1;
            mediaPlayer1 = MediaPlayer.create(context, posts.get(position).getAudio_id());
            inter_media_player = mediaPlayer1;
        }
        else {
            try {
                //mediaPlayer = new MediaPlayer();
                final MediaPlayer mediaPlayer1 = new MediaPlayer();
                mediaPlayer1.setDataSource(posts.get(position).getAudio_path());
                mediaPlayer1.prepare();
                inter_media_player = mediaPlayer1;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //final MediaPlayer mediaPlayer = MediaPlayer.create(context, posts.get(position).getAudio_id());
        //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        mediaPlayer = inter_media_player;

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                holder.scrubber.setMax(mediaPlayer.getDuration());
                playCycle(holder.scrubber,mediaPlayer);
            }
        });
        //holder.scrubber.setMax(mediaPlayer.getDuration());

        handler = new Handler();

        //playCycle(holder.scrubber,mediaPlayer);

//        new Handler().postDelayed(new Runnable(){
//            @Override
//            public void run() {
//                /* Create an Intent that will start the Menu-Activity. */
//
//                holder.scrubber.setProgress(mediaPlayer.getCurrentPosition());
//            }
//        }, 1000);


//        new Timer().scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                holder.scrubber.setProgress(mediaPlayer.getCurrentPosition());
//            }
//        }, 0, 5000);


//        holder.scrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//                if(fromUser)
//                mediaPlayer.seekTo(progress);
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });

//        Picasso.with(context).load(post.profile_pic)
//                .fit()
//                .centerCrop()
//                .into(holder.profile_pic);

        holder.profile_pic.setImageResource(post.getProfile_pic());

        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onPlay(holder.scrubber,position,mediaPlayer,holder.play,holder.pause);

//                if(mediaPlayer!=null)
//                {
//                    mediaPlayer.pause();
//                }
//                mediaPlayer = MediaPlayer.create(context, posts.get(position).getAudio_id());
//                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//
//                holder.scrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//                    @Override
//                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//                        if(fromUser)
//                            mediaPlayer.seekTo(progress);
//
//                    }
//
//                    @Override
//                    public void onStartTrackingTouch(SeekBar seekBar) {
//
//                    }
//
//                    @Override
//                    public void onStopTrackingTouch(SeekBar seekBar) {
//
//                    }
//                });
//
//                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                    @Override
//                    public void onPrepared(MediaPlayer mediaPlayer) {
//                        holder.scrubber.setMax(mediaPlayer.getDuration());
//                        playCycle(holder.scrubber,mediaPlayer);
//                    }
//                });
//
//                mediaPlayer.start();
//                playCycle(holder.scrubber,mediaPlayer);
//                holder.play.setVisibility(View.INVISIBLE);
//                holder.pause.setVisibility(View.VISIBLE);
            }
        });

        holder.pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                  onPause(mediaPlayer,holder.play,holder.pause);
//                mediaPlayer.pause();
//                holder.pause.setVisibility(View.INVISIBLE);
//                holder.play.setVisibility(View.VISIBLE);
            }
        });



//        holder.like.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Post post1 = posts.get(position);
//                Log.d("clicked","yes");
//                if(post1.liked)
//                {
//                    //Log.d("clicked","true");
//                    like.setImageResource(R.mipmap.like);
//                    post.liked = false;
//                    posts.set(position,post);
//                }
//                else
//                {
//                    //Log.d("clicked","false");
//                    like.setImageResource(R.mipmap.liked);
//                    post.liked = true;
//                    posts.set(position,post);
//                }
//            }
//        });

//        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mediaPlayer) {
//                holder.scrubber.setMax(mediaPlayer.getDuration());
//                playCycle(holder.scrubber,mediaPlayer);
//            }
//        });
    }

    public void playCycle(final SeekBar seekBar, final MediaPlayer mediaPlayer)
    {
        seekBar.setProgress(mediaPlayer.getCurrentPosition());

        if(mediaPlayer.isPlaying())
        {
            runnable = new Runnable() {
                @Override
                public void run() {
                    playCycle(seekBar,mediaPlayer);
                }
            };
            handler.postDelayed(runnable,1000);
        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void onPlay(final SeekBar scrubber,int position,final MediaPlayer mediaPlayer,ImageView play,ImageView pause)
    {
        if(m_mediaPlayer!=null && m_mediaPlayer.isPlaying())
        {
            m_mediaPlayer.pause();
            m_pause.setVisibility(View.INVISIBLE);
            m_play.setVisibility(View.VISIBLE);

        }
        //mediaPlayer = MediaPlayer.create(context, posts.get(position).getAudio_id());
        //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        scrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(fromUser)
                    mediaPlayer.seekTo(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

//        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mediaPlayer) {
//                scrubber.setMax(mediaPlayer.getDuration());
//                playCycle(scrubber,mediaPlayer);
//            }
//        });

        mediaPlayer.start();
        playCycle(scrubber,mediaPlayer);
        m_mediaPlayer = mediaPlayer;
        m_play = play;
        m_pause = pause;



        playCycle(scrubber,mediaPlayer);
        play.setVisibility(View.INVISIBLE);
        pause.setVisibility(View.VISIBLE);
    }

    public void onPause(MediaPlayer mediaPlayer,ImageView play,ImageView pause)
    {
        mediaPlayer.pause();
        pause.setVisibility(View.INVISIBLE);
        play.setVisibility(View.VISIBLE);
    }


    }



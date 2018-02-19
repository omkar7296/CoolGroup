package com.omkar.coolgroup;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    ArrayList<Post> posts = new ArrayList<>();
     MediaRecorder mediaRecorder;
     //String AudioSavePathInDevice =  Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "String1" +
       //      "AudioRecording.3gp";

    int count = 1;
    String AudioSavePathInDevice = "";

    public static final int RequestPermissionCode = 1;
    MediaPlayer mediaPlayer;

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

    @BindView(R.id.description)
    EditText description;

    Posts_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

//        Picasso.with(getApplicationContext()).load("https://avatarfiles.alphacoders.com/717/71761.jpg")
//                .fit()
//                .centerCrop()
//                .into(profile_pic_header);

//        description.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("Test","Here");
//                description.setEnabled(true);
//            }
//        });

        record_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                record_button.setVisibility(View.INVISIBLE);
                pause_button.setVisibility(View.VISIBLE);

                if(checkPermission()) {

                    AudioSavePathInDevice =
                           Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "File" + Integer.toString(count)+
                                  "AudioRecording.3gp";
                    count++;

                    MediaRecorderReady();

                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }


                    description.setEnabled(true);

                    Toast.makeText(MainActivity.this, "Recording started",
                            Toast.LENGTH_SHORT).show();

               } else {
                    requestPermission();
                }

            }

        });

        pause_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //record_button.setVisibility(View.INVISIBLE);

                if(mediaRecorder!= null)
                mediaRecorder.stop();

                pause_button.setVisibility(View.INVISIBLE);
                post_button.setVisibility(View.VISIBLE);
                cancel_button.setVisibility(View.VISIBLE);

//                Toast.makeText(MainActivity.this, "Recording Completed",
//                        Toast.LENGTH_LONG).show();
            }
        });

        post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Are you sure?")
                        .setMessage("Are you sure you want to post???")
                        .setPositiveButton("Post", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String desc = "";
                                post_button.setVisibility(View.INVISIBLE);
                                cancel_button.setVisibility(View.INVISIBLE);
                                record_button.setVisibility(View.VISIBLE);

                                desc = description.getText().toString();

                                posts.add(0,new Post(
                                        "James Harden",
                                        "Pittsburgh, Pennsylvania",
                                        R.drawable.jamesharden,
                                        desc,
                                        AudioSavePathInDevice,R.raw.sound1
                                ));

                                description.getText().clear();
                                description.setEnabled(false);
                                //recyclerView.invalidate();
                                //adapter.notifyDataSetChanged();
                                adapter.notifyItemRangeChanged(0, posts.size());
                                Toast.makeText(MainActivity.this, "Post uploaded successfully", Toast.LENGTH_SHORT).show();
                            }



                        })
                        .setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {



                            }
                        })
                        .show();




//                String desc = "";
//                post_button.setVisibility(View.INVISIBLE);
//                cancel_button.setVisibility(View.INVISIBLE);
//                record_button.setVisibility(View.VISIBLE);
//
//                desc = description.getText().toString();
//
//                posts.add(0,new Post(
//                        "Batman",
//                        "Miami, Florida",
//                        "https://avatarfiles.alphacoders.com/717/71761.jpg",
//                        desc,
//                        AudioSavePathInDevice,R.raw.sound1
//                ));
//
//                description.getText().clear();
//                description.setEnabled(false);
//                //recyclerView.invalidate();
//                //adapter.notifyDataSetChanged();
//                adapter.notifyItemRangeChanged(0, posts.size());
//                Toast.makeText(MainActivity.this, "Post uploaded successfully", Toast.LENGTH_SHORT).show();
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {
                post_button.setVisibility(View.INVISIBLE);
                cancel_button.setVisibility(View.INVISIBLE);
                record_button.setVisibility(View.VISIBLE);
                description.getText().clear();
                description.setEnabled(false);

//                mediaPlayer = new MediaPlayer();
//                try {
//                    mediaPlayer.setDataSource(AudioSavePathInDevice);
//                    mediaPlayer.prepare();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                mediaPlayer.start();
//                Toast.makeText(MainActivity.this, "Recording Playing",
//                        Toast.LENGTH_LONG).show();


            }
        });





        posts.add(new Post(
                "Gorillaz",
                "Pittsburgh, Pennsylvania",
                R.drawable.gorillaz,
                "Hi, here's a small message from me",
                "",R.raw.sound1
        ));

        posts.add(new Post(
                "James Harden",
                "Pittsburgh, Pennsylvania",
                R.drawable.jamesharden,
                "Hi, check this out",
                "",R.raw.sound
        ));

        posts.add(new Post(
                "Strawberry",
                "Pittsburgh, Pennsylvania",
                R.drawable.strawberry,
                "I like strawberry a lot",
                "",R.raw.sound
        ));

        posts.add(new Post(
                "Pittsburgh-Penguin",
                "Pittsburgh, Pennsylvania",
                R.drawable.pittsburghpenguin,
                "Hello all,",
                "",R.raw.sound1
        ));

//        posts.add(new Post(
//                "Aquaman",
//                "Pittsburgh, Pennsylvania",
//                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRg7JcMPcUE_CpNH565NhO9RR5XWgcJlOwRYAmsF0jMsbDDRvzayw",
//                "Here the description of the post is displayed",
//                "",R.raw.sound1
//        ));

        posts.add(new Post(
                "Superman",
                "Pittsburgh, Pennsylvania",
                R.drawable.superman,
                "Hello friends, Here's a small message from me",
                "",R.raw.sound
        ));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);

        adapter = new Posts_Adapter(posts,this);

        //recyclerView.setAdapter(new Posts_Adapter(posts,this));
        recyclerView.setAdapter(adapter);

    }

    public void MediaRecorderReady(){
        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(AudioSavePathInDevice);
    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length> 0) {

                    Log.i("Test","Here");
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {
                        Toast.makeText(MainActivity.this, "Permission Granted",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this,"Permission Denied",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(MainActivity.this, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }
}

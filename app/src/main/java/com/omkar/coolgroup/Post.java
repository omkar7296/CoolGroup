package com.omkar.coolgroup;

/**
 * Created by omkar on 2/9/2018.
 */

public class Post {

    public String name;
    public String location;
    public String profile_pic;
    public String desc;
   // public String image;
    public boolean liked;
    public String audio_path;
    public int audio_id;

    public Post(String name, String location, String profile_pic, String desc,String audio_path,int audio_id) {
        this.name = name;
        this.location = location;
        this.profile_pic = profile_pic;
        this.desc = desc;
        //this.image = image;
        this.liked = false;
        this.audio_path = audio_path;
        this.audio_id = audio_id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public String getDesc() {
        return desc;
    }


    public boolean isLiked() {
        return liked;
    }

    public String getAudio_path() {
        return audio_path;
    }

    public int getAudio_id() { return audio_id;}
}

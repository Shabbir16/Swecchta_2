package com.example.shabbir.swecchta_2;

import android.graphics.Bitmap;

/**
 * Created by shabbir on 3/30/2018.
 */

public class LeaderBoard {
    private String image;
    private String name;
    private int score;
    private int post;
    private int dustbin;

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getPost() {
        return post;
    }

    public int getDustbin() {
        return dustbin;
    }

    public LeaderBoard(String image, String name, int score, int post, int dustbin) {

        this.image = image;
        this.name = name;
        this.score = score;
        this.post = post;
        this.dustbin = dustbin;

    }
}

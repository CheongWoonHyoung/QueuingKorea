package com.example.cheongwh.queuing;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by mintaewon on 2015. 7. 24..
 */
public class ResListItem extends ArrayList<ResListItem> {
    public String small_imgurl;
    public String res_name;
    public String res_cuisine;
    public String res_distance;
    public String res_linenum;

    public ResListItem(String imgurl,String name, String cuisine, String distance,String linenum){
        this.small_imgurl = imgurl;
        this.res_name = name;
        this.res_cuisine = cuisine;
        this.res_distance = distance;
        this.res_linenum = linenum;
    }
}
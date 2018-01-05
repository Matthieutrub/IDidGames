package com.matthieutrublin.ididgames;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by matthieutrublin on 29/12/2017.
 */

public class ImageInstagram {
    public String identifier;
    public String owner;
    int height;
    int width;
    public String url;
    public String smallSizeUrl;
    boolean isVideo;
    public String code;
    public String date;
    public String caption;
    public int likes;
    public int comments;

    public static String getDate(String timeStampStr){
        try{
            DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date netDate = (new Date(Long.parseLong(timeStampStr)*1000));
            return sdf.format(netDate);
        } catch (Exception ignored) {
            return "xx";
        }
    }



    public ImageInstagram(JSONObject raw1) {
        try {
            JSONObject raw = raw1.getJSONObject("node");
            identifier=raw.getString("id");
            owner=raw.getJSONObject("owner").getString("id");
            height=raw.getJSONObject("dimensions").getInt("height");
            width=raw.getJSONObject("dimensions").getInt("width");
            url=raw.getString("display_url");
            smallSizeUrl=raw.getString("thumbnail_src");
            isVideo=raw.getBoolean("is_video");
            code=raw.getString("shortcode");
            date=getDate(raw.getString("taken_at_timestamp"));
            JSONObject tmp = (JSONObject) raw.getJSONObject("edge_media_to_caption").getJSONArray("edges").get(0);
            caption = tmp.getJSONObject("node").getString("text");
            likes=raw.getJSONObject("edge_liked_by").getInt("count");
            comments=raw.getJSONObject("edge_media_to_comment").getInt("count");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}

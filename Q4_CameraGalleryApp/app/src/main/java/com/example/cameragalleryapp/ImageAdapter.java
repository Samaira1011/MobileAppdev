package com.example.cameragalleryapp;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> paths;

    public ImageAdapter(Context c, ArrayList<String> paths) {
        this.context = c;
        this.paths = paths;
    }

    public int getCount() { return paths.size(); }
    public Object getItem(int i) { return paths.get(i); }
    public long getItemId(int i) { return i; }

    public View getView(int i, View view, ViewGroup parent) {
        ImageView img = new ImageView(context);
        img.setLayoutParams(new ViewGroup.LayoutParams(300, 300));
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        img.setImageBitmap(BitmapFactory.decodeFile(paths.get(i)));
        return img;
    }
}
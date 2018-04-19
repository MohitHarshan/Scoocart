package com.example.mohit.scoocart;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

/**
 * Created by mohit on 3/9/2018.
 */

public class Pagers extends PagerAdapter {

    Context context;
    String images[]=null;
    LayoutInflater layoutInflater;


    public Pagers(Context context, String[] images) {
        this.context = context;
        this.images = images;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.slider, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.image);
        Picasso.with(context).load("http://iroidtech.com/schoolapp/image/"+images[position]).into(imageView);

        container.addView(itemView);


        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
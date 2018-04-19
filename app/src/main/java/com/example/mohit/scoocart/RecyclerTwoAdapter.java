package com.example.mohit.scoocart;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mohit on 2/27/2018.
 */

public class RecyclerTwoAdapter extends RecyclerView.Adapter<RecyclerTwoAdapter.Viewholder> {
    private List<offeroftheday> array=new ArrayList<>();
    private Context context;

    public RecyclerTwoAdapter(List<offeroftheday> array, Context context) {
        this.context = context;
        this.array=array;
    }



    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardofferoftheday, parent, false);

        return new Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {

        final offeroftheday item = array.get(position);
        holder.text2card.setText(item.getTitle());
        holder.textsub2card.setText(item.getSubtitle());
        holder.textrupees.setText(item.getPrice());
        holder.offer.setText(item.getDiscount());
        final String product_id = item.getProduct_id();
        Picasso.with(context).load("http://iroidtech.com/schoolapp/image/" + item.getImageid()).into(holder.image2card);

        holder.cardofferoftheday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), cardClicked.class);
                intent.putExtra("product_idw", product_id);
                v.getContext().startActivity(intent);
            }
        });



    }
    @Override
    public int getItemCount() {
        return array.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public CardView cardofferoftheday;
        TextView text2card, textsub2card, offer, textrupees;
        ImageView image2card;
        CheckBox wishlist;

        public Viewholder(View itemView) {
            super(itemView);
            cardofferoftheday = (CardView) itemView.findViewById(R.id.cardofferoftheday);
            text2card = (TextView) itemView.findViewById(R.id.text2card);
            wishlist = (CheckBox) itemView.findViewById(R.id.wishlistcheck);
            textsub2card = (TextView) itemView.findViewById(R.id.textsub2card);
            offer = (TextView) itemView.findViewById(R.id.offer);
            image2card = (ImageView) itemView.findViewById(R.id.image2card);
            textrupees = (TextView) itemView.findViewById(R.id.textrupees);


        }


    }}





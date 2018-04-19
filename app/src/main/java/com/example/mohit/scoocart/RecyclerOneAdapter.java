package com.example.mohit.scoocart;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohit on 2/27/2018.
 */

public class RecyclerOneAdapter extends RecyclerView.Adapter<RecyclerOneAdapter.Viewholder> {

    private List<category> array=new ArrayList<>();
    private Context context;
    private  String product_id;

    public RecyclerOneAdapter(List<category> array, Context context) {
        this.context = context;
        this.array =array;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardbycategory, parent, false);

        return new Viewholder(itemView);


    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
       category item=array.get(position);
       holder.text1card.setText(item.getCategory());
        product_id=item.getProduct_id();
        Picasso.with(context).load("http://iroidtech.com/schoolapp/image/"+item.getImageid()).into(holder.image1card);

        holder.cardbycategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),cardClicked.class);
                intent.putExtra("product_idw",product_id);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
       public CardView cardbycategory;
       TextView text1card;
       ImageView image1card;
        public Viewholder(View itemView) {
            super(itemView);
            cardbycategory=(CardView)itemView.findViewById(R.id.cardbycategory);
            text1card=(TextView)itemView.findViewById(R.id.text1card);
            image1card=(ImageView)itemView.findViewById(R.id.image1card);
        }





        }
    }


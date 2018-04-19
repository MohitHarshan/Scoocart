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

public class RecyclerThreeAdapter extends RecyclerView.Adapter<RecyclerThreeAdapter.Viewholder> {
    private Context context;
    private List<highlights> array=new ArrayList<>();

    public RecyclerThreeAdapter(List<highlights> array, Context context) {
        this.context = context;
        this.array=array;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardhighlight, parent, false);

        return new Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
   final highlights item=array.get(position);
    holder.textsub3card.setText(item.getSubtitle());
    holder.text3card.setText(item.getTitle());
    holder.price3.setText(item.getPrice());
    holder.offer2.setText(item.getDiscount());

        Picasso.with(context).load("http://iroidtech.com/schoolapp/image/"+item.getImageid()).into(holder.image3card);


        holder.cardhighlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),cardClicked.class);
                intent.putExtra("product_idw",item.getProduct_id());
                v.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public CardView cardhighlight;
        TextView offer2,price3,text3card,textsub3card;
        ImageView image3card;
        public Viewholder(View itemView) {
            super(itemView);
        cardhighlight=(CardView)itemView.findViewById(R.id.cardhighlight);
        offer2=(TextView)itemView.findViewById(R.id.offer2);
        price3=(TextView)itemView.findViewById(R.id.textrupees3);

        text3card=(TextView)itemView.findViewById(R.id.text3card);
        textsub3card=(TextView)itemView.findViewById(R.id.textsub3card);
        image3card=(ImageView)itemView.findViewById(R.id.image3card);



        }
    }
}

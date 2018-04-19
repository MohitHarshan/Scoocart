package com.example.mohit.scoocart;

import android.content.Context;
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
 * Created by mohit on 3/5/2018.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Viewholder> {

    private List<Cart> cartarray=new ArrayList<Cart>();
    private Context context;

    public CartAdapter(List<Cart> cartarray, Context context) {
        this.cartarray = cartarray;
        this.context = context;
    }

    @Override
    public CartAdapter.Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cartcard, parent, false);

        return new Viewholder(itemView);



    }

    @Override
    public void onBindViewHolder(CartAdapter.Viewholder holder, int position) {
        Cart cart=cartarray.get(position);

        holder.carttitle.setText(cart.getCarttitle());
        holder.price.setText(cart.getCartprice());

        Picasso.with(context).load(cart.getCartimageid()).into(holder.cartimage);


    }

    @Override
    public int getItemCount() {
        return cartarray.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
CardView cartcard;
        TextView carttitle,price;
        ImageView cartimage;
        public Viewholder(View itemView) {
            super(itemView);
     cartcard=(CardView)itemView.findViewById(R.id.cartcard);
       carttitle=(TextView) itemView.findViewById(R.id.carttitle);
       price=(TextView)itemView.findViewById(R.id.cartprice);
       cartimage=(ImageView)itemView.findViewById(R.id.cartimage);


        }
    }
}

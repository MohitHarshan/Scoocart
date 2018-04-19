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
 * Created by mohit on 2/27/2018.
 */

public class Recyclernotification extends RecyclerView.Adapter<Recyclernotification.Viewholder> {
   private List<Notification> notif=new ArrayList<>();
   private Context context;

    public Recyclernotification(List<Notification> notif, Context context) {
        this.notif = notif;
        this.context = context;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardnotifications, parent, false);

        return new Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        Notification notification=notif.get(position);
        holder.titlenotification.setText(notification.getTitle());
        holder.time.setText(notification.getTime());
        Picasso.with(context).load(notification.getImageid()).into(holder.imagenotification);



    }

    @Override
    public int getItemCount() {
        return notif.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

      public CardView cardnotifications;
       public ImageView imagenotification;
       public TextView time,titlenotification;
        public Viewholder(View itemView) {
            super(itemView);
            cardnotifications=(CardView)itemView.findViewById(R.id.cardnotification);
            imagenotification=(ImageView)itemView.findViewById(R.id.imagenotification);
            time=(TextView)itemView.findViewById(R.id.timetext);
            titlenotification=(TextView)itemView.findViewById(R.id.titletextnot);
        }
    }
}

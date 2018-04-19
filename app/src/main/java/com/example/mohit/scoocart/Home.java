package com.example.mohit.scoocart;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

import static android.content.ContentValues.TAG;


public class Home extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ViewPager mPager;
    private List<offeroftheday> offerlist = new ArrayList<>();
    private List<highlights> highlightlist = new ArrayList<>();
    private HashMap<String, String> id;
    private CheckBox wishlistcheck;
    ServiceHandlerTwo serviceHandler = new ServiceHandlerTwo();

    private List<category> categorylist = new ArrayList<>();
    private static int currentPage = 0;

    Clickpager adapter;
    // TODO: Rename and change types of parameters
    String mParam1;
    private String mParam2;
    private RecyclerView recyclerone, recyclertwo, recyclerthree;
    private OnFragmentInteractionListener mListener;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Context context;
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mPager = (ViewPager) view.findViewById(R.id.pager);
        int images[] = new int[]{R.drawable.laptopbag, R.drawable.sliderimage, R.drawable.clothes, R.drawable.mobile, R.drawable.nisha};
        wishlistcheck=(CheckBox)view.findViewById(R.id.wishlistcheck);



        adapter = new Clickpager(getContext(), images);

        mPager.setAdapter(adapter);
        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        indicator.configureIndicator(10, 10, 10);
        final int NUM_PAGES = images.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);


        recyclerone = (RecyclerView) view.findViewById(R.id.recyclerone);
        recyclertwo = (RecyclerView) view.findViewById(R.id.recyclertwo);
        recyclerthree = (RecyclerView) view.findViewById(R.id.recyclerthree);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(0);
        recyclerone.setLayoutManager(llm);
        LinearLayoutManager llm2 = new LinearLayoutManager(getContext());
        llm2.setOrientation(0);
        recyclertwo.setLayoutManager(llm2);
        LinearLayoutManager llm3 = new LinearLayoutManager(getContext());
        llm3.setOrientation(0);
        recyclerthree.setLayoutManager(llm3);




        new starttask1().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        new starttask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    new starttask3().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    class starttask extends AsyncTask<Void, Void, Void> {
        String name, image, discount, price, subtitle,product_id;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            RecyclerTwoAdapter offer = new RecyclerTwoAdapter(offerlist, getContext());
            recyclertwo.setAdapter(offer);


        }

        @Override
        protected Void doInBackground(Void... voids) {

            HttpHandler sh = new HttpHandler();



            String url = "http://iroidtech.com/schoolapp/index.php?route=api/skoolcart/offers&type=OFFER_OF_THE_DAY";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);


                    String status = jsonObj.getString("status");

                    if (status.equals("success")) {
                        JSONArray records = jsonObj.getJSONArray("records");

                        for (int i = 0; i < records.length(); i++) {
                            JSONObject c = records.getJSONObject(i);
                            name = c.getString("product_name");
                            image = c.getString("featured_image");
                            discount = c.getString("discount");
                            price = c.getString("price");
                            subtitle = c.getString("product_description");
                            product_id=c.getString("product_id");
                            offeroftheday offer = new offeroftheday(discount, price, name, image, subtitle,product_id);
                            offerlist.add(offer);
                        }


                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");

            }

            return null;
        }

    }

    class starttask1 extends AsyncTask<String, Void, String> {
         String categorys, image,product_id,desc,name,user;
         HttpHandler sh=new HttpHandler();
         HashMap<String,String> id=new HashMap<>();



        @Override
        protected String doInBackground(String... strings) {

 id.put("user_id","253");

            HttpHandler sho = new HttpHandler();
            String response="";
            try {
                 response=serviceHandler.performPostCall("http://iroidtech.com/schoolapp/index.php?route=api/skoolcart/displayHome",id);
            } catch (IOException e) {
                e.printStackTrace();
            }


            Log.e("LOG RES ###############",response );


            Log.e(TAG, "Response from url: " + response);
            if (response != null) {
                try {
                    JSONObject jsonObj = new JSONObject(response);


                    String status = jsonObj.getString("status");

                    if (status.equals("success")) {
                        JSONArray data = jsonObj.getJSONArray("data");

                        for (int i = 0; i < data.length(); i++) {
                            JSONObject c = data.getJSONObject(i);
                            categorys = c.getString("category_name");
                            image = c.getString("image");
                             desc=c.getString("description");
                             name=c.getString("name");
                            product_id=c.getString("product_id");
                            category catego = new category(image,categorys,product_id);
                            categorylist.add(catego);
                        }


                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");

            }

            return null;
        }




        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            RecyclerOneAdapter category = new RecyclerOneAdapter(categorylist, getContext());
            recyclerone.setAdapter(category);


        }
    }

    class starttask3 extends AsyncTask<Void, Void, Void> {
        String message;
        String status;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            RecyclerThreeAdapter highlight = new RecyclerThreeAdapter(highlightlist, getContext());
            recyclerthree.setAdapter(highlight);


        }

        @Override
        protected Void doInBackground(Void... voids) {

            HttpHandler sh = new HttpHandler();

            String url = "http://iroidtech.com/schoolapp/index.php?route=api/skoolcart/offers&type=HIGHLIGHTS";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);


                     status = jsonObj.getString("status");

                    message=jsonObj.getString("message");

                    highlights high=new highlights(status,message);
                    highlightlist.add(high);

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");

            }

            return null;
        }

}

    }


















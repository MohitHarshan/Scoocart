package com.example.mohit.scoocart;

import android.content.Context;
import android.net.Uri;
import android.nfc.TagLostException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.TimeoutException;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TermsandConditionsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TermsandConditionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TermsandConditionsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
  Context context;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Toolbar toolbar;
    TextView heading;
    TextView termstext;
    private OnFragmentInteractionListener mListener;

    public TermsandConditionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TermsandConditionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TermsandConditionsFragment newInstance(String param1, String param2) {
        TermsandConditionsFragment fragment = new TermsandConditionsFragment();
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
        View view= inflater.inflate(R.layout.fragment_termsand_conditions, container, false);

        toolbar=(Toolbar)view.findViewById(R.id.toolbar);

        termstext=(TextView)view.findViewById(R.id.termstext);
       heading= (TextView)view.findViewById(R.id.heading);
        new starttask().execute();

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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

class starttask extends AsyncTask<Void,Void,Void> {
 String description,title;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(Void aVoid) {


        super.onPostExecute(aVoid);

        heading.setText(title);
        termstext.setText(description);
    }

    @Override
    protected Void doInBackground(Void... voids) {

        HttpHandler sh = new HttpHandler();

        String url = "http://iroidtech.com/schoolapp/index.php?route=api/skoolcart/termcondition";
        String jsonStr = sh.makeServiceCall(url);

        Log.e(TAG, "Response from url: " + jsonStr);
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);


                String status=jsonObj.getString("status");

                if(status.equals("success")){
                    JSONObject data = jsonObj.getJSONObject("data");
                     title=data.getString("title");
                     description=data.getString("description");
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

}
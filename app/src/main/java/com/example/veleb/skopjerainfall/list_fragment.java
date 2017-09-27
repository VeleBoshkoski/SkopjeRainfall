package com.example.veleb.skopjerainfall;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link list_fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link list_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class list_fragment extends Fragment  implements Callback<List<Data>> {
    private List<Data> dataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DeviceDataAdapter dAdapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public list_fragment() {
        // Required empty public constructor
    }

    public static list_fragment newInstance() {
        list_fragment fragment = new list_fragment();
        Bundle args = new Bundle();
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
        Data d = new Data();
        d.setDeviceId("default_device");
        d.setRaining(false);
        d.setRaw("AAAB");
        d.setTime("2017-09-24T16:17:12.053493771Z");
        d.setUV(0);
        d.setVisibleLight(0.3921568627450981);
        d.setWet(0);
        dataList.add(d);

        loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list_fragment, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);

        dAdapter = new DeviceDataAdapter(dataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(dAdapter);

        return v;
}
    public void loadData() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Controller.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Queries queriesAPI = retrofit.create(Queries.class);

        Call<List<Data>> call = queriesAPI.loadLastData();

        call.enqueue(this);
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
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }

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

    @Override
    public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
        if(response.isSuccessful()) {

            List<Data> queryList = response.body();
            dataList.clear();
            for (Data data : queryList) {
                boolean exists = false;
                for (Data d: dataList) {
                    if(d.getDeviceId().equals(data.getDeviceId())){
                        exists = true;
                    }

                }
                if(!exists)
                    dataList.add(data);
            }
            recyclerView.setAdapter(new DeviceDataAdapter( dataList));
            recyclerView.invalidate();
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<Data>> call, Throwable t) {
        t.printStackTrace();
    }
}

package com.example.veleb.skopjerainfall;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by veleb on 24.9.2017.
 */

public class DeviceDataAdapter extends RecyclerView.Adapter<DeviceDataAdapter.MyViewHolder> {

    private List<Data> dataList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView device_name, time, raining, weather, pressure;

        public MyViewHolder(View view) {
            super(view);
            device_name = (TextView) view.findViewById(R.id.device_name);
            time = (TextView) view.findViewById(R.id.time);
            raining = (TextView) view.findViewById(R.id.raining);
            weather = (TextView) view.findViewById(R.id.weather);
            pressure = (TextView) view.findViewById(R.id.pressure);
        }
    }


    public DeviceDataAdapter(List<Data> dataList) {
        this.dataList = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lora_wan, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Data data = dataList.get(position);
        holder.device_name.setText(data.getDeviceId());
        holder.time.setText(data.getTime().substring(11, 16));
        holder.raining.setText(data.getUV()+"");
        holder.pressure.setText(data.getVisibleLight()+"");

        String raining = "Sunny";
        if(data.isRaining())
            raining = "Rainy";
        holder.weather.setText(raining);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

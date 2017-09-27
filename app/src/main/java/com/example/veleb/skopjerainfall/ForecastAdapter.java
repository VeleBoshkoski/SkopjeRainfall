package com.example.veleb.skopjerainfall;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.veleb.skopjerainfall.forecast.Forecast;
import com.example.veleb.skopjerainfall.forecast.ListF;

import java.util.List;

/**
 * Created by veleb on 26.9.2017.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.MyViewHolder> {
    private List<ListF> dataList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView temperature, time, pressure, date, wind;
        public ImageView weather;

        public MyViewHolder(View view) {
            super(view);
            time = (TextView) view.findViewById(R.id.time);
            temperature = (TextView) view.findViewById(R.id.temperature);
            pressure = (TextView) view.findViewById(R.id.pressure);
            date = (TextView) view.findViewById(R.id.date);
            weather = (ImageView) view.findViewById((R.id.weather));
            wind = (TextView) view.findViewById((R.id.wind));
        }
    }


    public ForecastAdapter(Context context, List<ListF> dataList) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public ForecastAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_item, parent, false);

        return new ForecastAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ForecastAdapter.MyViewHolder holder, int position) {
        ListF data = dataList.get(position);
        String date = data.getDtTxt().split(" ")[1].equals("00:00:00") ? data.getDtTxt().split(" ")[0] : "";
        String result = "";
        if(!date.equals("")){
            String [] date1 = date.split("-");
            result = date1[2] + "-" + date1[1] + "-" + date1[0];
        }
        holder.date.setText(result);
        holder.temperature.setText(String.valueOf((int) Math.round(data.getMain().getTemp()))+"°");
        holder.time.setText(data.getDtTxt().split(" ")[1].substring(0,5));
        holder.pressure.setText(data.getMain().getPressure()+"");
        holder.wind.setText(data.getWind().getSpeed()+"km/h");

        int icon = R.drawable.sun;
        if(data.getWeather().get(0).getMain().equals("Rain"))
            icon = R.drawable.rain;
        else if(data.getWeather().get(0).getMain().equals("Snow"))
            icon = R.drawable.rain;
        else if(data.getWeather().get(0).getMain().equals("Clouds"))
            icon = R.drawable.cloudy;

        holder.weather.setImageDrawable(context.getDrawable(icon));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

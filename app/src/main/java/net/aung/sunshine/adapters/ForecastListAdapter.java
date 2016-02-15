package net.aung.sunshine.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.aung.sunshine.R;
import net.aung.sunshine.controllers.ForecastListScreenController;
import net.aung.sunshine.data.vos.WeatherStatusVO;
import net.aung.sunshine.viewholders.DailyWeatherViewHolder;
import net.aung.sunshine.viewholders.TodayWeatherViewHolder;
import net.aung.sunshine.viewholders.WeatherViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aung on 12/10/15.
 */
public class ForecastListAdapter extends RecyclerView.Adapter<WeatherViewHolder> {

    private static final int VIEW_TYPE_TODAY = 0;
    private static final int VIEW_TYPE_OTHER_THAN_TODAY = 1;

    private List<WeatherStatusVO> statusList;
    private ForecastListScreenController controller;

    public static ForecastListAdapter newInstance(ForecastListScreenController controller) {
        List<WeatherStatusVO> statusList = new ArrayList<>();
        return new ForecastListAdapter(statusList, controller);
    }

    public ForecastListAdapter(List<WeatherStatusVO> statusList, ForecastListScreenController controller) {
        this.statusList = statusList;
        this.controller = controller;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        if (parent.getResources().getBoolean(R.bool.isTablet)) {
            View statusContainer = inflater.inflate(R.layout.list_item_forecast, parent, false);
            return new DailyWeatherViewHolder(statusContainer, controller);
        } else {
            if (viewType == VIEW_TYPE_TODAY) {
                View statusContainer = inflater.inflate(R.layout.list_item_forecast_today, parent, false);
                return new TodayWeatherViewHolder(statusContainer, controller);
            } else if (viewType == VIEW_TYPE_OTHER_THAN_TODAY) {
                View statusContainer = inflater.inflate(R.layout.list_item_forecast, parent, false);
                return new DailyWeatherViewHolder(statusContainer, controller);
            }
        }

        return null;
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        WeatherStatusVO status = statusList.get(position);
        holder.bind(status);
    }

    @Override
    public int getItemCount() {
        return statusList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? VIEW_TYPE_TODAY : VIEW_TYPE_OTHER_THAN_TODAY;
    }

    public void setStatusList(List<WeatherStatusVO> newStatusList) {
        this.statusList.clear();
        this.statusList.addAll(newStatusList);
        notifyDataSetChanged();
    }
}

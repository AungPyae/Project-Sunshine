package net.aung.sunshine.viewholders;

import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import net.aung.sunshine.R;
import net.aung.sunshine.controllers.WeatherListItemController;
import net.aung.sunshine.data.vos.WeatherStatusVO;
import net.aung.sunshine.databinding.ListItemForecastTodayBinding;
import net.aung.sunshine.utils.WeatherIconUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by aung on 12/13/15.
 */
public class TodayWeatherViewHolder extends WeatherViewHolder {

    @Bind(R.id.iv_status_art)
    ImageView ivStatusArt;

    private ListItemForecastTodayBinding binding;

    public TodayWeatherViewHolder(View itemView, WeatherListItemController controller) {
        super(itemView, controller);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);

        binding = DataBindingUtil.bind(itemView);
    }

    @Override
    public void bind(WeatherStatusVO status) {
        super.bind(status);
        binding.setWeatherStatus(status);

        int weatherArtResourceId = WeatherIconUtils.getArtResourceForWeatherCondition(status.getWeather().getId());
        ivStatusArt.setImageResource(weatherArtResourceId);
    }
}

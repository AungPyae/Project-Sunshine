package net.aung.sunshine.events;

import net.aung.sunshine.data.responses.WeatherStatusListResponse;
import net.aung.sunshine.data.vos.WeatherStatusVO;

import java.util.List;

import retrofit.RetrofitError;

/**
 * Created by aung on 12/14/15.
 */
public class DataEvent {

    public static class NewWeatherStatusList {
        private List<WeatherStatusVO> weatherStatusList;

        public NewWeatherStatusList(List<WeatherStatusVO> weatherStatusList) {
            this.weatherStatusList = weatherStatusList;
        }

        public List<WeatherStatusVO> getWeatherStatusList() {
            return weatherStatusList;
        }
    }

    public static class LoadedWeatherStatusListEvent {
        private WeatherStatusListResponse response;

        public LoadedWeatherStatusListEvent( WeatherStatusListResponse response) {
            this.response = response;
        }

        public WeatherStatusListResponse getResponse() {
            return response;
        }
    }

    public static class LoadedWeatherStatusListErrorEvent {
        private RetrofitError error;

        public LoadedWeatherStatusListErrorEvent(RetrofitError error) {
            this.error = error;
        }

        public RetrofitError getResponse() {
            return error;
        }
    }
}

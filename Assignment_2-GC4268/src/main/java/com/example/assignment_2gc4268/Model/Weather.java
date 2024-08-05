package com.example.assignment_2gc4268.Model;

public class Weather {
    private String cityName;
    private double currentTemperature;
    private double currentHumidity;
    private double currentPrecipitation;
    private String forecastDate;
    private double windKph;
    private int dailyRain;
    private double maxTemperature;
    private double minTemperature;
    private String condition;
    private int cityUV;

    public int getCityUV() {
        return cityUV;
    }

    public void setCityUV(int cityUV) {
        this.cityUV = cityUV;
    }

    public double getWindKph() {
        return windKph;
    }

    public void setWindKph(double windKph) {
        this.windKph = windKph;
    }


    public Weather(){}

    public Weather(String cityName, double currentTemperature, double currentHumidity, double currentPrecipitation, String forecastDate, double windKph, double minTemperature, double maxTemperature, int dailyRain, String condition, int cityUV){
        setCityName(cityName);
        setCurrentTemperature(currentTemperature);
        setCurrentHumidity(currentHumidity);
        setCurrentPrecipitation(currentPrecipitation);
        setForecastDate(forecastDate);
        setWindKph(windKph);
        setMinTemperature(minTemperature);
        setMaxTemperature(maxTemperature);
        setDailyRain(dailyRain);
        setCondition(condition);
        setCityUV(cityUV);
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public double getCurrentHumidity() {
        return currentHumidity;
    }

    public void setCurrentHumidity(double currentHumidity) {
        this.currentHumidity = currentHumidity;
    }

    public double getCurrentPrecipitation() {
        return currentPrecipitation;
    }

    public void setCurrentPrecipitation(double currentPrecipitation) {
        this.currentPrecipitation = currentPrecipitation;
    }

    public String getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(String forecastDate) {
        this.forecastDate = forecastDate;
    }

    public int getDailyRain() {
        return dailyRain;
    }

    public void setDailyRain(int dailyRain) {
        this.dailyRain = dailyRain;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}

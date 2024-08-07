package com.example.assignment_2gc4268.Model;

/**
 * Represents weather data for a specific city and date.
 */
public class Weather {

    private String cityName;              // Name of the city
    private double currentTemperature;    // Current temperature in Celsius
    private double currentHumidity;       // Current humidity percentage
    private double currentPrecipitation;  // Current precipitation in mm
    private String forecastDate;          // Date of the weather forecast
    private double windKph;               // Wind speed in kilometers per hour
    private int dailyRain;                // Daily rain amount in mm
    private double maxTemperature;        // Maximum temperature for the day in Celsius
    private double minTemperature;        // Minimum temperature for the day in Celsius
    private String condition;             // Weather condition description (e.g., sunny, rainy)
    private int cityUV;                   // UV index for the city

    // Default constructor
    public Weather() {}

    /**
     * Parameterized constructor to initialize all weather attributes.
     */
    public Weather(String cityName, double currentTemperature, double currentHumidity,
                   double currentPrecipitation, String forecastDate, double windKph,
                   double minTemperature, double maxTemperature, int dailyRain,
                   String condition, int cityUV) {
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

    // Getters and setters for each attribute of weather app

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
}

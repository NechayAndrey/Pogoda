package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class wither {
    public static String result;
    private static String temp;
    private static String pressure;
    private static String date;
    private static String sun;
    public String url;

    public String witherNow() {

        url = "https://www.gismeteo.ru/weather-yekaterinburg-4517/now/";
        try {
            Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36").get();
            date = doc.select("body > section.content.wrap > div.content-column.column1 > section.section.section-content.section-bottom-shadow > div > div.now-localdate").text();
            sun = doc.select("body > section.content.wrap > div.content-column.column1 > section.section.section-content.section-bottom-shadow > div > div.now-astro.now-night").text();
            temp = "Температура сейчас " + doc.select("body > section.content.wrap > div.content-column.column1" +
                    " > section.section.section-content.section-bottom-shadow > div > div.now-weather > span.unit.unit_temperature_c").text()
                    + " " + doc.getElementsByClass("now-desc").text();
            pressure = "Ветер " + doc.select("body > section.content.wrap > div.content-column.column1 > section.section.section-content.section-bottom-shadow > div > div.now-info > div > div.now-info-item.wind > div.item-value > div.unit.unit_wind_m_s").text()
                    + "\nДавление " + doc.select("body > section.content.wrap > div.content-column.column1 > section.section.section-content.section-bottom-shadow > div > div.now-info > div > div.now-info-item.pressure > div.item-value > div.unit.unit_pressure_mm_hg_atm").text()
                    + "\n" + doc.select("body > section.content.wrap > div.content-column.column1 > section.section.section-content.section-bottom-shadow > div > div.now-info > div > div.now-info-item.humidity > div").text();
            result = date + "\n" + temp + "\n" + pressure + "\n"
                    + sun;
            return result;


        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}


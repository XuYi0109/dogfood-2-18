package com.charging.station.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class GsonConfig {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Bean
    public Gson gson() {
        JsonDeserializer<LocalDateTime> deserializer = (json, typeOfT, context) -> {
            String dateTimeStr = json.getAsString();
            return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        };

        JsonSerializer<LocalDateTime> serializer = (src, typeOfSrc, context) -> {
            return context.serialize(src.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
        };

        return new GsonBuilder()
                .setDateFormat(DATE_TIME_FORMAT)
                .registerTypeAdapter(LocalDateTime.class, deserializer)
                .registerTypeAdapter(LocalDateTime.class, serializer)
                .serializeNulls()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public GsonHttpMessageConverter gsonHttpMessageConverter(Gson gson) {
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        converter.setGson(gson);
        return converter;
    }
}

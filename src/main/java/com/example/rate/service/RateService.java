package com.example.rate.service;

import org.springframework.http.ResponseEntity;
import java.net.URISyntaxException;

/**
 * Сервис для получени и сравнения курсов и gif изображений
 */
public interface RateService {
    /**
     * Получить случайное gif изображение по запросу к клиенту по фильтру broke
     * @return {@link ResponseEntity} объект перенаправляющий на ссылку с полученным gif изображением
     * @throws URISyntaxException
     */
    ResponseEntity<Object> getRandomBrokeGif() throws URISyntaxException;
    /**
     * Получить случайное gif изображение по запросу к клиенту по фильтру rich
     * @return {@link ResponseEntity} объект перенаправляющий на ссылку с полученным gif изображением
     * @throws URISyntaxException
     */
    ResponseEntity<Object> getRandomRichGif() throws URISyntaxException;

    /**
     * Получить курс валюты по отношению к USD
     * @param currencyCode код валюты
     * @return double значение курса валюты
     */
    Double getCurrentRate(String currencyCode);

    /**
     * Получить курс валюты за предыдущую дату по отношению к USD
     * @param currencyCode код валюты
     * @return double значение курса валюты
     */
    Double getPreviousRate(String currencyCode);

    /**
     * Сравнить вчерашний и сегодняшний курс по отношению к целевой валюте
     * @param currencyCode код валюты
     * @return {@link ResponseEntity} объект перенаправляющий на ссылку с полученным gif изображением
     */
    ResponseEntity<Object> mainApiMethod(String currencyCode) throws URISyntaxException;

}

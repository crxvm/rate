package com.example.rate.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Feign клиент для запросов к внешнему api курсов валют
 */
@FeignClient(name = "OpenExchange", url = "${open.exchange.url}")
public interface RemoteOpenExchangeRateClient {

    /**
     * Получить текущий курс валют
     * @return объект строки с содержимым json
     */
    @RequestMapping(method = RequestMethod.GET,value = "${open.exchange.current.rate}")
    String getCurrentRate();

    /**
     * Получить курс валют за определенную дату
     * @param dateString дата
     * @return объект строки с содержимым json
     */
    @RequestMapping(method = RequestMethod.GET,value = "/historical/{dateString}.json?app_id=0c3c524f7a94459393324195b6ed85a9")
    String getPreviousDate(@PathVariable("dateString") String dateString);
}

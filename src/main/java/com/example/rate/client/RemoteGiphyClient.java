package com.example.rate.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Feign клиент для запросов к внешнему аpi gif изображений
 */
@FeignClient(name = "gif", url = "${api.giphy.url}")
public interface RemoteGiphyClient {
    /**
     * Запрос к внешнему api с фильтром rich
     * @return объект строки с содержимым json
     */
    @RequestMapping(method = RequestMethod.GET, value = "${api.giphy.search.rich.gif}")
    String getRichRandomGif();

    /**
     * Запрос к внешнему api с фильтром rich
     * @return объект строки с содержимым json
     */
    @RequestMapping(method = RequestMethod.GET, value = "${api.giphy.search.broke.gif}")
    String getRandomBrokeGif();

}

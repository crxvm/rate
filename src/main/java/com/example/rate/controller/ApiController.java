package com.example.rate.controller;

import com.example.rate.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.net.URISyntaxException;


/**
 * Контроллер приложения
 */
@RestController
@RequiredArgsConstructor
public class ApiController {

    private final RateService rateService;

    /**
     * Получить соответсвующе gif изображение и передать его клиенту
     * @param currencyCode код валюты
     * @return  объект перенаправляющий на ссылку с полученным gif изображением
     * @throws URISyntaxException
     */
    @GetMapping(value = "/api/{currencyCode}" , produces = MediaType.TEXT_HTML_VALUE)
    public @ResponseBody ResponseEntity<Object> mainRequest(@PathVariable("currencyCode") String currencyCode) throws URISyntaxException {
        return rateService.mainApiMethod(currencyCode);
    }
    @GetMapping(value = "/test")
    public String test(){
        return "testOk";
    }
}

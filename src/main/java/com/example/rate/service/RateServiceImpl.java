package com.example.rate.service;

import com.example.rate.client.RemoteGiphyClient;
import com.example.rate.client.RemoteOpenExchangeRateClient;

import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {

    @Value("${default.currency.code}")
    private String defaultCurrencyCode;
    private final RemoteOpenExchangeRateClient openExchangeRatClient;
    private final RemoteGiphyClient giphyClient;

    /**
     * @inheritDoc
     */
    @Override
     public ResponseEntity<Object> getRandomBrokeGif() throws URISyntaxException {

        JSONArray jsonArray = new JSONObject(giphyClient.getRandomBrokeGif()).getJSONArray("data");
        Random random = new Random();
        int randomInt = random.nextInt(25);
        JSONObject jo =  jsonArray.getJSONObject(randomInt).getJSONObject("images").getJSONObject("original");
        URI uri = new URI(jo.get("url").toString());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }
    /**
     * @inheritDoc
     */
    @Override
    public ResponseEntity<Object> getRandomRichGif() throws URISyntaxException {
        JSONArray jsonArray = new JSONObject(giphyClient.getRichRandomGif()).getJSONArray("data");
        Random random = new Random();
        int randomInt = random.nextInt(25);
        JSONObject jo =  jsonArray.getJSONObject(randomInt).getJSONObject("images").getJSONObject("original");
        URI uri = new URI(jo.get("url").toString());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }
    /**
     * @inheritDoc
     */
    @Override
    public Double getCurrentRate(String currencyCode) {
        JSONObject jo = new JSONObject(openExchangeRatClient.getCurrentRate());
        return Double.parseDouble(jo.getJSONObject("rates").get(currencyCode).toString());
    }

    /**
     * @inheritDoc
     */
    @Override
    public Double getPreviousRate(String currencyCode) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String previousDateResult = s.format(new Date(cal.getTimeInMillis()));
        JSONObject jo = new JSONObject(openExchangeRatClient.getPreviousDate(previousDateResult));
        return Double.parseDouble(jo.getJSONObject("rates").get(currencyCode).toString());
    }
    /**
     * @inheritDoc
     */
    @Override
    public ResponseEntity<Object> mainApiMethod(String currencyCode) throws URISyntaxException {
        double previousDefaultRate = 1.0 / getPreviousRate(defaultCurrencyCode);
        double currentDefaultRate = 1.0 / getCurrentRate(defaultCurrencyCode);
        double previousTargetRate = getPreviousRate(currencyCode) * previousDefaultRate;
        double currentTargetRate = getCurrentRate(currencyCode) * currentDefaultRate;
        if(currentTargetRate < previousTargetRate) {
            return getRandomBrokeGif();
        }
        else return getRandomRichGif();
    }
}


package  com.example.demo.service;

import  org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService 
{
    private final RestTemplate restTemplate = new RestTemplate();

    public String getRates(String baseCurrency)
    {
        String url = "https://open.er-api.com/v6/latest/" + baseCurrency;

        return restTemplate.getForObject(url, String.class);
    }
    
}

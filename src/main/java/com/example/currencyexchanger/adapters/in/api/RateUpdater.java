package com.example.currencyexchanger.adapters.in.api;

import com.example.currencyexchanger.adapters.out.jpa.repository.ExchangeRateRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class RateUpdater {

  String rateApiBaseUrl;
  List<String> currencies;
  private final ExchangeRateRepository exchangeRateRepository;

  public RateUpdater(
      @Value("${exchanger.rate.api.url}") String rateApiBaseUrl,
      @Value("${exchanger.imported.currencies}") List<String> currencies,
      ExchangeRateRepository exchangeRateRepository) {
    this.rateApiBaseUrl = rateApiBaseUrl;
    this.currencies = currencies;
    this.exchangeRateRepository = exchangeRateRepository;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void onStartup() {
    updateRates();
  }

  @Scheduled(cron = "${exchanger.currency.update.cron}")
  public void updateRates() {
    List<String> currencyCodes = currencies;
    for (String currencyCode : currencyCodes) {
      RateResponseDto response = fetchDataFromNbpApi(currencyCode);
      if (response == null) {
        log.error("Failed to fetch currency {}", currencyCode);
        continue;
      }
      log.info("Fetched currency '{}'", response.currency);
      saveExchangeRate(response);
    }
  }

  @Transactional
  public void saveExchangeRate(RateResponseDto dto) {
    exchangeRateRepository.save(ExchangeRateMapper.toEntity(dto));
  }

  private RateResponseDto fetchDataFromNbpApi(String currency) {
    return WebClient.create(rateApiBaseUrl).get()
        .uri(uriBuilder -> uriBuilder.path("/" + currency + "/today/").build())
        .accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(RateResponseDto.class)
        .onErrorResume(WebClientException.class, e -> Mono.empty()).block();
  }
}

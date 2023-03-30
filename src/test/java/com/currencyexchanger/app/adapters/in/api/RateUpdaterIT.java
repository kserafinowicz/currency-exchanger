package com.currencyexchanger.app.adapters.in.api;

import static org.junit.jupiter.api.Assertions.*;

import com.currencyexchanger.app.adapters.out.jpa.repository.ExchangeRateRepository;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@WireMockTest(httpPort = 8081)
@SpringBootTest
class RateUpdaterIT {

  @Autowired
  RateUpdater rateUpdater;

  @Autowired
  ExchangeRateRepository repository;

  @Test
  void shouldSuccessfullyFetchExchangeData(WireMockRuntimeInfo wmRuntimeInfo) {
    // given
    WireMock wireMock = wmRuntimeInfo.getWireMock();
    wireMock.register(
        WireMock.get("/api/exchangerates/rates/c/USD/today/")
            .willReturn(WireMock.okJson(stubApiResponseBody())));

    // when
    rateUpdater.updateRates();
    var result = repository.findByCurrencyCode("USD");

    // then
    assertFalse(result.isEmpty());
    assertEquals("USD", result.get().getCurrencyCode());
    assertEquals("dolar amerykanski", result.get().getCurrency());
    assertEquals(new BigDecimal("4.3646"), result.get().getAsk());
    assertEquals(getBid(), result.get().getBid());
  }

  private BigDecimal getBid() {
    return BigDecimal.ONE.setScale(4, RoundingMode.HALF_UP)
        .divide(new BigDecimal("4.2782").setScale(4, RoundingMode.HALF_UP), RoundingMode.HALF_UP);
  }

  private String stubApiResponseBody() {
    return """
        {
        "table": "C",
        "currency": "dolar amerykanski",
        "code": "USD",
        "rates": [
            {
              "no": "063/C/NBP/2023",
              "effectiveDate": "2023-03-30",
              "bid": 4.2782,
              "ask": 4.3646
            }
          ]
        }
        """;
  }
}

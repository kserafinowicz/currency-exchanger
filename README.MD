# Currency exchanger

Celem zadania jest przygotowanie aplikacji serwującej API REST, która pozwoli na założenie konta oraz wymianę waluty w parze PLN<->USD.

## Założenia funkcjonalne:

- Aplikacja posiada REST API pozwalające na założenie konta walutowego.
- Przy zakładaniu konta wymagane jest podanie początkowego salda konta w PLN.
- Aplikacja przy zakładaniu konta wymaga od użytkownika podania imienia i nazwiska.
- Aplikacja przy zakładaniu konta generuje identyfikator konta który powinien być używany przy wywoływaniu dalszych metod API.
- Aplikacja powinna udostępnić REST API do wymiany pieniędzy w parze PLN<->USD (czyli PLN na USD oraz USD na PLN), a aktualny kurs wymiany pobrać z publicznego API NBP(http://api.nbp.pl/).
- Aplikacja powinna udostępnić REST API do pobrania danych o koncie i jego aktualnego stanu w PLN i USD.

## Założenia niefunkcjonalne:

- Aplikacja musi zostać wykonana w Kotlinie lub Javie.
- Aplikacja może być wykonana w dowolnym frameworku.
- Aplikacja nie musi zachowywać danych po restarcie.
- Kod źródłowy aplikacji powinien zostać udostępniony na wybranym portalu do hostowania kodu (np. Gitlab, Github, Bitbucket).
- Aplikacja musi być budowana przy pomocy narzędzia do budowania aplikacji (np. Maven, Gradle).
- W przypadku niesprecyzowania czegoś w zadaniu - pozostaje dowolność.
- W przypadku pytań – można się dopytywać mailowo.


## Działanie aplikacji

Aplikacja po uruchomieniu ma wystawionego Swaggera:

http://localhost:8080/swagger-ui/index.html

Do bazy można się dostać poprzez konsolę H2:

http://localhost:8080/h2-console/login.jsp


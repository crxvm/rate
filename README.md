## Для запуска приложения понядобится 
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Gradle](https://gradle.org/)

## Запуск

Приложение можно запустить через IDE [IntelliJ IDEA](https://www.jetbrains.com/ru-ru/idea/) 

Или через командную строку
```shell
./gradlew bootRun
```

Запрос приходит на http эндпоинт (коды валют можно получить [здесь](https://openexchangerates.org/api/currencies.json))
```
api/{Код валюты}
```

Все параметры (порт сервера, валюта по отношению к которой смотрится курс, адреса внешних сервисов) вынесены в настройки [application.properties](https://github.com/crxvm/rate/blob/master/src/main/resources/application.properties)
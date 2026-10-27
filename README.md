# Array Processing

Учебный проект на Java 17: обработка числовых массивов, Factory Method, Builder,
валидация текстового файла, Log4j2 и тесты JUnit 5.

## Требования
- JDK 17 или новее
- Maven 3.9+ (или Maven Wrapper, если добавите его самостоятельно)

## Запуск
Из корневого каталога проекта:

```bash
mvn clean test
mvn exec:java -Dexec.mainClass="edu.example.arrays.application.ApplicationLauncher"
```

Для второй команды добавьте плагин `exec-maven-plugin` в `pom.xml`, либо запустите
класс `edu.example.arrays.application.ApplicationLauncher` из IDE.

Входные данные находятся в `data/arrays.txt`. Некорректные строки пропускаются,
ошибки фиксируются в журнале. Логи выводятся в консоль и `logs/application.log`.

## Архитектура
- `entity` — модель массива
- `builder` — Builder
- `factory` — Factory Method
- `parser`, `validation`, `reader` — чтение и обработка входных данных
- `service` и `service.impl` — интерфейсы и реализации операций
- `exception` — пользовательское исключение
- `application` — сборка зависимостей и запуск
- `src/test` — модульные тесты JUnit 5

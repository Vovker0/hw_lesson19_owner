# Запуск тестов из консоли

Для локального запуска тестов из консоли используйте следующую команду:
```shell
gradle clean test -Denvironment=local_browser
```

Для запуска тестов на удалённом Selenoid школы qa.guru используйте команду:
```shell
gradle clean test -Denvironment=remote_browser
```

Для запуска тестов на локально установленном Selenoid (если у вас такой имеется, по умолчанию на http://localhost:8080/wd/hub/) используйте команду:
```shell
gradle clean test -Denvironment=local_selenoid
```

## Другие доступные ключи:

- `-Dbrowser` - позволяет задать браузер, например: chrome или firefox
- `-DbrowserVersion` - позволяет задать версию браузера
- `-Dresolution` - задаёт разрешение окна браузера
#### [Lesson from Java QA Engineer (by OTUS)][link]:
#### Запуск тестов:
```bash
mvn clean test -Dotus_login="login" -Dotus_password="password" -Dbrowser="cHRome"
mvn clean test -Dotus_login="login" -Dotus_password="password" -Dbrowser="fireFOX"
mvn clean test -Dotus_login="login" -Dotus_password="password"
```
#### Цель:
- Реализуйте автоматический тест, используя Java + Selenium + POM + web-driver factory
- Создайте класс WebDriverFactory со статическим методом create();
- Метод create() принимает обязательный параметр webDriverName и необязтельный параметр options, а возвращает соответствующий имени вебдрайвер с заданными (если были) options
- Примеры использования WebDriver wd = WebDriverFactory.createNewDriver("chrome"); или FirefoxOptions options = new FirefoxOptions(); WebDriver wd = WebDriverFactory.createNewDriver("firefox", options);
---
#### Шаги теста:

- Открыть [сайт https://otus.ru][otusHome]
- Авторизоваться на сайте
- Войти в личный кабинет
- В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
- Нажать сохранить
- Открыть [сайт https://otus.ru][otusHome] в "чистом браузере"
- Авторизоваться на сайе
- Войти в личный кабинет
- Проверить, что в разделе "О себе" отображаются указанные ранее данные

---
#### Критерии оценки:
- +1 балл - код компилируется и запускается
- +1 балл - код запускается без дополнительных действий со стороны проверяющего (не нужно скачивать WebDriver, решать конфликты зависимостей и т.п.)
- +1 балл - логин/пароль для авторизации не зашиты в код (передаются как параметры при старте)
- +1 балл - логи пишутся в консоль и файл
- +1 балл - тест проходит без падений (допускается падение теса только при некорректной работе SUT)
- +1 балла - реализован паттерн PageObject
- +1 балл - проект компилируется и собирается
- +1 балл - в репозитории нет лишних файлов (.iml, директория idea и т.д.)
- +1 балл - регистр значения параметра -Dbrowser не влияет на результат
- +1 балл - для хранения имен драйверов используются Enum
---
#### Минимальный балл 8 из 10

---
#### Ссылки:
- [Видео по PageObject][youtube]
- [Проект на github][github]

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

[link]: <https://otus.ru/learning/102096/>
[otusHome]: <https://otus.ru/>
[youtube]: <https://www.youtube.com/watch?v=jT1mIQmdVuo>
[github]: <https://github.com/iarmush/otus-javaqa-lessons>
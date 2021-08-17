#### Epam Project from OTUS:

###### Проверка работы тестов на локали (Chrome):
```bash
mvn clean test -Dexecute.property=LOCAL
```

###### Запуск окружения ([описание][previousProject] ):
```bash
cd infra && sudo ./start.sh && cd ..
```

###### Удаленный запуск тестов:
- Установить вебхук в GitHub и в Jenkins по адресу http://0.0.0.0:8083/ (логинимся как test/test)
- Идём в джобу = WebHok Pipeline и запускаем тест, под хромом 86.0

###### Локальный запуск тестов:
Настройки:
```bash
source ~/.profile
cat /etc/hosts   # убеждаемся что хосты selenoid и jenkins резолвятся и доступны
```
Валидация Jenkinsfile:
```bash
curl --user test:test -X POST -F "jenkinsfile=<Jenkinsfile" http://jenkins:8080/pipeline-model-converter/validate
curl --user test:test -X POST -F "jenkinsfile=<Jenkinsfile" http://0.0.0.0:8083/pipeline-model-converter/validate
```
запуск после настроек:
```bash
mvn clean test # важно соответствие модели в файле infra/selenoid/config/browsers.json + присутствие контейнера браузера!
mvn clean test -Dbrowser_name=chrome -Dbrowser_version=86.0
```

###### Allure report (IDEA -> maven/Plugins/allure -> allure:serve):
```bash
source ~/.profile
allure generate target/allure-results/ --clean -o allure-report && allure open
```

###### Цель:
- Необходимо построить фреймворк для автоматизации Е2Е тестирования сайта с обязательным тестовым покрытием.

###### Что будем тестировать: 
- Приложение https://events.epam.com/ предоставляет информацию о мероприятиях, которые проводит EPAM. Сайт позволяет посмотреть предстоящие/прошедшие мероприятия в разных городах, информацию о спикерах, докладах, календарь мероприятий. 

###### Требования к фреймворку:
- Java
- Maven/Gradle
- TestNG/Junit
- Настроено логирование
- Реализована возможность кросс-браузерного тестирования и удаленного запуска тестов
- Реализована возможность параллельного запуска тестов
- Код проекта хранится в Git (важна частота и содержание коммитов)
- Для работы со страницами используется паттерн Page Object
- Код оформлен согласно Java Code Conventions, комментарии в стиле Javadoc приветствуются
- *Дополнительно: Настроена интеграция с CI и запуск тестов по расписанию. Разрешается подключение других библиотек, использование BDD подхода, Spring, Lombok

---
###### Приемочные тесты:
1) Просмотр предстоящих мероприятий:
- Пользователь переходит на вкладку events
- На странице отображаются карточки предстоящих мероприятий. Количество карточек равно счетчику на кнопке Upcoming Events

2) Просмотр карточек мероприятий:
- Пользователь переходит на вкладку events
- Пользователь нажимает на Past Events
- На странице отображаются карточки предстоящих мероприятий.
- В карточке указана информация о мероприятии: язык, название мероприятия, дата мероприятия, информация о регистрации, список спикеров // Минимально достаточное - проверить одну карточку. В идеале все что отображаются.

3) Валидация дат предстоящих мероприятий:
- Пользователь переходит на вкладку events
- Пользователь нажимает на Upcoming Events
- На странице отображаются карточки предстоящих мероприятий.
- Даты проведения мероприятий больше или равны текущей дате (или текущая дата находится в диапазоне дат проведения)

4) Просмотр прошедших мероприятий в Канаде:
- Пользователь переходит на вкладку events
- Пользователь нажимает на Past Events
- Пользователь нажимает на Location в блоке фильтров и выбирает Canada в выпадающем списке
- На странице отображаются карточки прошедших мероприятий. Количество карточек равно счетчику на кнопке Past Events. Даты проведенных мероприятий меньше текущей даты.

5) Фильтрация докладов по категориям:
- Пользователь переходит на вкладку Talks Library
- Пользователь нажимает на More Filters
- Пользователь выбирает: Category – Testing, Location – Belarus, Language – English, На вкладке фильтров
- На странице отображаются карточки соответствующие правилам выбранных фильтров

6)Поиск докладов по ключевому слову:
- Пользователь переходит на вкладку VIDEO - Talks Library
- Пользователь вводит ключевое слово QA в поле поиска
- На странице отображаются доклады, содержащие в названии ключевое слово поиска

*Дополнительно: Тестовое покрытие может быть расширено для функциональности фильтрации

---

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

[link]: <https://otus.ru/learning/102096/>
[Jenkins-docker-compose]: <https://adamtheautomator.com/jenkins-docker/>
[ngrok-docker-compose]: <https://github.com/shkoliar/docker-ngrok>
[jenkinsTutorial]: <https://github.com/liberstein/JenkinsImageTutorial>
[previousProject]: <https://github.com/liberstein/selenoidJenkinsDocker/blob/master/README.md>

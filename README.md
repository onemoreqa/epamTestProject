#### [Lesson from Java QA Engineer (by OTUS)][link]:
#### Запуск тестов:
```bash
source ~/.profile
mvn clean test
```
#### Allure report (IDEA -> maven/Plugins/allure -> allure:serve):
```bash
source ~/.profile
allure generate target/allure-results/ --clean -o allure-report && allure open
```
#### Проверено в окружении:
- Ubuntu 18.04.1
- IDEA 2021.2 RC
- MAVEN 3.6.0
- OpenJDK 1.8.0_292 (JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/jre)
- Allure 2.14.0

#### Цель:
- Реализовать интеграцию текущего проекта Selenium с Allure
- Открыть тестовый проект для web на Selenium. Настроить интеграцию с библиотекой Allure по примеру, показанному в вебинаре. 
- В тесты добавить передачу скриншота в Allure отчет.
---
#### Критерии оценки:
- В результате прохождения тестов успешно запускается web страница с Allure отчетом

---
#### cat ~/.profile:
```bash
export M2_HOME=/usr/share/maven
JAVA_HOME=$(dirname $( readlink -f $(which java) ))
JAVA_HOME=$(realpath "$JAVA_HOME"/../)
export JAVA_HOME
export ALLURE_HOME=/home/egorbunov/Desktop/allure-2.14.0
PATH="$HOME/bin:$HOME/.local/bin:$PATH:$JAVA_HOME/bin:$M2_HOME/bin:$ALLURE_HOME/bin"
export PATH

```

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

[link]: <https://otus.ru/learning/102096/>

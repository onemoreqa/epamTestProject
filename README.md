#### [Lesson from Java QA Engineer (by OTUS)][link]:
#### Для запуска тестов в Intellij Idea:
```bash
Установить версию OpenJDK > 11
File -> Project Structure -> Modules -> Language level to 8
File -> Settings -> Build, Execution, Deployment -> Compiler -> Java Compiler -> 8
```
#### Цель:
- Создать проект тематики Калькулятор (или другой тематики, на ваш выбор) 
- Написать нереализованные методы (например: возведение в степень, корень из числа, на ваш выбор) 
- Написать тесты для этих методов, которые вначале при запуске будут иметь статус failed. 
- Далее реализовать методы и запустить тесты снова. 
- Результат: повторно тесты успешно отработают со статусом passed.

###### По желанию можно сменить тематику сервиса, основная задача попробовать создать разные типы (сильная, слабая, инверсия контроля) зависимости классов.

---
#### Критерии оценки:
- Тесты для реализованных методов успешно пройдены.

---
#### Ссылки:
- [Создание шаблона Spring][spring]

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

[spring]: <https://start.spring.io/>
#### [Lesson from Java QA Engineer (by OTUS)][link]:
#### Для запуска тестов в Intellij Idea:
```bash
File -> Project Structure -> Modules -> Language level to 8
File -> Settings -> Build, Execution, Deployment -> Compiler -> Java Compiler -> 8
```
#### Цель:
- Научиться писать примеры кода сильной, слабой зависимости и инверсии контроля.
- На примере приложения созданного в обучающем вебинаре (был рассмотрен сервис по продаже автомобилей) создать дополнительные конфигурации для автомобиля. 
- Создать на продажу новые автомобили с новыми конфигурациями и вывести результат создания в консоль. 
- Поработать с конфигурационным классом SpringConfig и с бинами. 
- Создать тест для новой конфигурации с ассертом.

###### По желанию можно сменить тематику сервиса, основная задача попробовать создать разные типы (сильная, слабая, инверсия контроля) зависимости классов.

---
#### Критерии оценки:
- По результату работы приложения необходимо вывести в консоль все новые конфигурации автомобиля. 
- Созданный тест для новой конфигурации автомобиля пройден успешно.

---
#### Ссылки:
- [Создание шаблона Spring][spring]

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

[spring]: <https://start.spring.io/>
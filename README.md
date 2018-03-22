
# documents-agregator

Приложение для агрегирования поступающих документов.

Пробное создание приложения в архитектуре [взглядов](http://www.eolang.ru/) [Егора Бугаенко](https://github.com/yegor256).  
1. Не использовать -er в именовании классов
1. Один главный конструктор в классе
1. Конструкторы не должны содержать выполняющий код
1. Делайте классы как можно мельче
1. Инкапсулируйте максимум 4 объекта. Такие классы легче поддерживать и тестировать.
1. Всегда используйте интерфейсы
1. Методы-билдеры именуйте существительными
1. Методы-манипуляторы именуйте глаголами. Такие методы не должны что-то возвращать, всегда void.
1. Методы возвращающие булевый тип именуйте прилагательными
1. Не используйте константы
1. Делайте объекты не изменяемыми (immutable)
1. Пишите тесты вместо документации
1. Не используйте моки, используйте фейки.
1. Маленькие классы легче понимать, поддерживать и тестировать.
1. Не используйте статические методы
1. Не используйте синглтоны
1. Не принимайте NULL-аргументов
1. Это избавляет от проверки на null. Null - это из мира компьютеров, а не из мира объектов.
1. Не используйте геттеры и сеттеры
1. Используйте new только во вторичных конструторах
1. Избегайте проверки и приведения типов
1. Никогда не возвращайте NULL. Лучше выбрасывайте исключение.
1. Используйте вложенные исключения
1. Одного типа исключений достаточно
1. Используйте RAII. Получение некоторого ресурса неразрывно совмещается с инициализацией, а освобождение — с уничтожением объекта.

**Цели:**  
1. Создание легкоподдерживаемого приложения
1. Проверка "вглядов" на практическое применение и решаемые проблемы.
1. Расширение кругозора в плане подходов к конструированию ПО

----------------------------------------------------------------------------------------
* 2018-03-23:  
  * Реализовал примитивный функционал:
    * Создание нового документа
    * Создание хранилища документа
    * Сохранение входящего документа в хранилище  

  * Пока то, что получается - нравится.   
    Как минимум:
    * Размеры классов
    * Работа только через интерфейсы
    * Приятные названия классов и методов
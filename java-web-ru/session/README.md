# Сессии

Сессия — это абстракция, созданная для удобной работы с индивидуальными пользователями. Этот механизм позволяет хранить информацию между разными запросами одного и того же пользователя. Он используется для идентификации пользователей и позволяет отличать их друг от друга. Например, аутентификация на сайтах построена поверх механизма сессии. В этом домашнем задании вам предстоит реализовать аутентификацию пользователей на сайте и вывод flash-сообщений. Flash — это одноразовое сообщение о результатах выполнения любого действия. Оно обычно используется после перенаправления для индикации успешности или неудачи предыдущего действия. Flash-сообщения для своей работы также используют механизм сессий. Аутентификация — это процедура проверки подлинности пользователя, например путем сравнения введенного им пароля с паролем, сохраненным в базе данных пользователей.

## Ссылки

* [Интерфейс HttpSession](https://javaee.github.io/javaee-spec/javadocs/javax/servlet/http/HttpSession.html)
* [Получение сессии — метод getSession()](https://javaee.github.io/javaee-spec/javadocs/javax/servlet/http/HttpServletRequest.html#getSession--)
* [Добавление атрибута в сессию — метод setAttribute()](https://javaee.github.io/javaee-spec/javadocs/javax/servlet/http/HttpSession.html#setAttribute-java.lang.String-java.lang.Object-)
* [Удаление атрибута из сессии — метод removeAttribute()](https://javaee.github.io/javaee-spec/javadocs/javax/servlet/http/HttpSession.html#removeAttribute-java.lang.String-)
* [Панель разработчика](https://ru.hexlet.io/courses/html/lessons/devtools/theory_unit)

## Users.java

## Задачи

* Изучите класс `Users` и его методы. Этот класс предоставляет методы для работы со списком пользователей, они понадобятся вам для реализации аутентификации.

## App.java

## Задачи

* Назначьте сервлет `SessionServlet` как обработчик запросов по двум адресам: */login* и */logout*

## servlet/SessionServlet.java

## Задачи

* Изучите содержимое методов `doGet()` и `doPost()`. Обратите внимание, как организована обработка сервлетом запросов по нескольким адресам и при помощи какого метода происходит получение части URL.

* Допишите метод `login()`, который обрабатывает POST-запросы по пути */login*. В методе реализуйте аутентификацию пользователя в системе. Чтобы данные пользователя сохранялись между запросами, вам потребуется использовать сессию. Аутентификация производится по email пользователя и паролю. Для простоты пароль для всех уже существующих пользователей одинаковый — "password". Если пользователь существует и введен верный пароль, установите в сессию атрибуты "userId" со значением id пользователя и "flash" со значением "Вы успешно вошли", затем выполните редирект на главную страницу */*. Таким образом данные пользователя будут сохранены в сессии и мы сможем определить, что данный пользователь вошел в систему. Атрибут "flash" будет использоваться для вывода flash-сообщения.

  Если пользователь не существует в системе или пароль введен неверно, установите в сессию атрибут "flash" со значением "Неверные логин или пароль", установите код ответа 422 и передайте управление в файл */login.jsp*. При ошибке входа поле формы email должно быть заполнено введенными данными.

* Допишите метод `logout()`, который обрабатывает POST-запросы по пути */logout*. В методе реализуйте выход пользователя из системы. Чтобы вывести пользователя из системы, нужно удалить из сессии его данные. После вывода пользователя из системы установите в сессию  атрибут "flash" со значением "Вы успешно вышли" и выполните редирект на главную страницу */*.

## webapp/WEB-INF/application.tag

В прошлом домашнем задании мы создали несколько шаблонов для работы с пользователями. Вы наверняка обратили внимание, что есть одинаковая общая часть, которая повторяется в каждом шаблоне. Чтобы не копировать эту общую часть в каждый шаблон, ее можно вынести в отдельный файл, а в jsp-файлах разместить только переменную часть, уникальную для каждого шаблона.

## Задачи

* Изучите содержимое файла *webapp/WEB-INF/application.tag*. Обратите внимание на то, как происходит работа с сессией.

* Организуйте вывод flash-сообщения. Чтобы добавить красоты, используйте классы Bootstrap

  ```html
  <div class="alert alert-info" role="alert">
      Сообщение
  </div>
  ```

  Так как flash-сообщение должно выводиться только один раз, после вывода его нужно удалить.

Работа сессий основана на механизме куки. Старт сессии на техническом уровне означает установку специальной куки в браузер. Эта кука содержит идентификатор сессии, который уникален для каждого пользователя. Запустите приложение и откройте его в браузере. Выполните аутентификацию пользователя в системе. Откройте панель разработчика на вкладке Application - Cookies и посмотрите установленные куки.

## Самостоятельная работа

* В итоге у вас получится красивое приложение, которое вы можете показать друзьям. Выполните деплой приложения и оно станет доступно в интернете. Для этого вам нужно будет вспомнить все аспекты урока "Деплой".

## Подсказки

* Изучите файл *Example.java*
* Атрибуты сессии внутри файла *tag* доступны в контексте `sessionScope`. Для доступа к атрибутам сессии используйте синтаксис `sessionScope.attributeName`.
* Сама сессия доступна внутри файла *tag* в переменной `session`
* В tag и jsp-файлах можно использовать java-код, разместив его между тегами `<% %>`

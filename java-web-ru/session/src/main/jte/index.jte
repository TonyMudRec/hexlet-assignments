@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@param MainPage page

<!-- BEGIN -->
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hello</title>
</head>
<body>
<main>
    <div>
    @if(page.getName() == null)
        <a href="${NamedRoutes.loginPath()}" role="button">Войти</a>
    @else
        <h3>Hello, ${page.getName()}</h3>
        <form action="${NamedRoutes.logoutPath()}" method="post">
            <input type="submit" value="Выйти" />
        </form>
    @endif
    </div>
</main>
</body>
</html>
<!-- END -->

@import exercise.dto.LoginPage
@import exercise.util.NamedRoutes
@param LoginPage page

<!-- BEGIN -->
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Login</title>
</head>
<body>
<main>
    @if(page.getError() != null)
        <div>
            <h3>${page.getError()}</h3>
        </div>
    @endif
    <div>
        <form action="${NamedRoutes.loginPath()}" method="post">
            <input type="text" placeholder="Nickname" name="name" value="${page.getName()}" required />
            <input type="password" placeholder="Password" name="password" required />
            <input type="submit" />
        </form>
    </div>
</main>
</body>
</html>
<!-- END -->

@import exercise.util.NamedRoutes;
@import exercise.dto.CartPage;
@import exercise.model.CartItem;
@param CartPage page

<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    <title>Hello Hexlet!</title>
  </head>
  <body>

      <h1 class="text-center mb-4">Товары</h1>

      <table class="table">
        <thead>
          <tr>
            <th>Идентификатор</th>
            <th>Название</th>
            <th>Действие</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1</td>
            <td>One</td>
            <td>
              <form action="${NamedRoutes.addItemPath()}" method="post">
                <input type="hidden" name="id" value="1">
                <input type="hidden" name="name" value="One">
                <input type="submit" class="btn btn-primary" value="Добавить">
              </form>
            </td>
          </tr>

          <tr>
            <td>2</td>
            <td>Two</td>
            <td>
              <form action="${NamedRoutes.addItemPath()}" method="post">
                <input type="hidden" name="id" value="2">
                <input type="hidden" name="name" value="Two">
                <input type="submit" class="btn btn-primary" value="Добавить">
              </form>
            </td>
          </tr>
        </tbody>
      </table>

      <h3 class="mb-4 text-center">Ваша корзина</h3>

      @if(page.getCart().size() == 0)
          <div class="mb-3 p-3">Корзина пуста</div>
      @else
          <table class="table">
              @for(var item : page.getCart().entrySet())
                  <tr>
                      <td>${item.getValue().getName()} : ${item.getValue().getCount()}</td>
                      <td></td>
                  </tr>
              @endfor
          </table>
      @endif

      <form action="${NamedRoutes.clearCartPath()}" method="post" class="p-3">
          <input type="submit"  class="btn btn-primary" value="Очистить">
      </form>

  </body>
</html>

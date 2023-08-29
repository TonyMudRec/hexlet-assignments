<!-- BEGIN -->
@import exercise.dto.posts.PostsPage
@import exercise.model.Post
@param PostsPage page

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
    <title>Posts list</title>
</head>
<body class="container-sm">
<div class="container px-4">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Post name</th>
        </tr>
        </thead>
        <tbody>
        @for (Post post : page.getPosts())
        <tr>
            <th scope="row">${post.getId()}</th>
            <td><a href="/posts/${post.getId()}" class="link-dark">${post.getName()}</a></td>
        </tr>
        @endfor
        </tbody>
    </table>
</div>
<nav aria-label="Page navigation">
    <ul class="pagination">
        @if (page.getPageNumber() != 1)
            <li class="page-item"><a class="page-link" href="/posts?page=${page.getPageNumber() - 1}">Previous</a></li>
        @endif
            <li class="page-item"><a class="page-link" href="/posts?page=${page.getPageNumber() + 1}">Next</a></li>
    </ul>
</nav>
</body>
</html>
<!-- END -->

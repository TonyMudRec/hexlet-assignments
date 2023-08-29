<!-- BEGIN -->
@import exercise.dto.posts.PostPage
@param PostPage page

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
    <title>Show Post</title>
</head>
<body class="container-sm">
    <h3 class="text-center">${page.getPost().getName()}</h3>
    <h2 class="text-center">${page.getPost().getBody()}</h2>
</body>
</html>
<!-- END -->

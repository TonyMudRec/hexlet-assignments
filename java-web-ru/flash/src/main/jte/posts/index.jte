<!-- BEGIN -->
@import exercise.dto.posts.PostsPage
@import exercise.model.Post
@param PostsPage page
@param Post post


@template.layout.page(

content = @`
    @if(page.getFlash() != null)
        <div class="alert alert-success">

            <p>${page.getFlash()}</p>
        </div>
    @endif

    @for(Post post : page.getPosts())
        <div>
            <h2>${post.getName()}</h2>
            <p>${post.getBody()}</p>
        </div>
    @endfor
`)
<!-- END -->

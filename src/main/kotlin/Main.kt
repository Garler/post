package ru.netology

fun main() {
    WallService.add(Post(1, 1, "text 1"))
    WallService.add(Post(1, 1, "text 2"))
    WallService.print()
    println(WallService.update(Post(6, 1, " ", likes = Likes(100))))
    WallService.print()

}

data class Post(
    val id: Int,
    val fromID: Int,
    val text: String,
    val friendsOnly: Boolean = false,
    val canPin: Boolean = false,
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val likes: Likes = Likes()
)

data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = false
)

object WallService {
    private var posts = emptyArray<Post>()
    private var lastId = 0
    fun add(post: Post): Post {
        posts += post.copy(id = ++lastId, likes = post.likes.copy())
        return posts.last()
    }

    fun update(newPost: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (post.id == newPost.id) {
                posts[index] = newPost.copy(likes = newPost.likes.copy())
                return true
            }
        }
        return false
    }

    fun clear() {
        posts = emptyArray()
        lastId = 0
    }

    fun print() {
        for (post in posts) {
            print(post)
            println()
        }
    }
}

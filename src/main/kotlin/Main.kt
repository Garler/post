package ru.netology

fun main() {
    WallService.add(Post(1, 1, 1, date = 1))
    WallService.add(Post(2, 1, 1, date = 1))
    WallService.print()
    println(WallService.update(Post(6, 1, 1, date = 1, likes = Likes(100))))
    WallService.print()

}

data class Post(
    val id: Int,
    val ownerId: Int,
    val fromID: Int,
    val createdBy: Int? = null,
    val date: Int,
    val text: String = "text",
    val replyOwnerId: Int? = null,
    val replyPostId: Int? = null,
    val views: Int = 0,
    val postType: String = "post",
    val signerId: Int? = null,
    val friendsOnly: Boolean = false,
    val canPin: Boolean = false,
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val postponedId: Int? = null,
    val likes: Likes = Likes(),
    val comments: Comments = Comments(),
    val copyright: Copyright? = null,
    val reposts: Reposts = Reposts(),
    val geo: Geo = Geo(),
    val donut: Donut = Donut()
)

data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = false,
    val canLike: Boolean = true,
    val canPublish: Boolean = true
)
data class Comments(
    val count: Int = 0,
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = true,
    val canClose: Boolean = true,
    val canOpen: Boolean = true
)
data class Copyright(
    val id: Int,
    val link: Int,
    val name: String,
    val type: String
)
data class Reposts(
    val count: Int = 0,
    val userReposted: Boolean = false
)
data class Geo(
    val type: String = "",
    val coordinates: String = ""
)
data class Donut(
    val isDonut: Boolean = false,
    val paidDuration: Int? = null,
    val placeholder: String = "",
    val canPublishFreeCopy: Boolean = false,
    val editMode: String = ""
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

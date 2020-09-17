package maze.run.unittesting.remote.respnoces


data class ImageResponce (
    val total:Int,
    val totalHits:Int,
    val hits:List<ImageUrl>
    )
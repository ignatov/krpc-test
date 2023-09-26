
@Serializable
class Image(val data: ByteArray)

enum class Category {
    CAT, DOG
}

interface ImageRecognizer : RPC {
    suspend fun recognize(image: Image): Category

    suspend fun recognizeAll(images: Flow<Image>): Flow<Category>
}
package org.example

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import org.jetbrains.krpc.RPC
import org.jetbrains.krpc.RPCMessage
import org.jetbrains.krpc.RPCTransport
import org.jetbrains.krpc.client.rpcServiceOf
import kotlin.coroutines.CoroutineContext

@Serializable
class Image(val data: ByteArray)

enum class Category {
    CAT, DOG
}

interface ImageRecognizer : RPC {
    suspend fun recognize(image: Image): Category

    suspend fun recognizeAll(images: Flow<Image>): Flow<Category>
}


class ImageRecognizerService : ImageRecognizer {
    override val coroutineContext: CoroutineContext = Job()

    override suspend fun recognize(image: Image): Category {
        val byte = image.data[0].toInt()
        return if (byte == 0) Category.CAT else Category.DOG
    }

    override suspend fun recognizeAll(images: Flow<Image>): Flow<Category> {
        return images.map { recognize(it) }
    }
}

class Transport: RPCTransport {
    override val coroutineContext: CoroutineContext = Job()

    override suspend fun send(message: RPCMessage) {
        println("Sending $message")
    }

    override val incoming: SharedFlow<RPCMessage>
        get() = TODO("Not yet implemented")
}

fun main() {
    val transport = Transport()
    val service: ImageRecognizerService = rpcServiceOf<ImageRecognizerService>(transport)

    runBlocking {
        service.recognize(Image(byteArrayOf(0)))
    }
}
package com.example.librarytrackerapp.data.repository

import android.content.Context
import android.net.Uri
import com.example.librarytrackerapp.data.mapper.toDomain
import com.example.librarytrackerapp.data.network.book.BookTrackerService
import com.example.librarytrackerapp.domain.model.Book
import com.example.librarytrackerapp.domain.repository.BookTrackerRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class BookTrackerRepositoryImpl @Inject constructor(
    private val bookTrackerService: BookTrackerService,
    @ApplicationContext private val context: Context
) : BookTrackerRepository {
    override suspend fun getBooks(): List<Book> {
        return bookTrackerService.getBooks().map { it.toDomain() }
    }

    override suspend fun getBook(id: Int): Book {
        val response = bookTrackerService.getBook(id)

        return when(response.code()) {
            200 -> {
                val dto = response.body() ?: throw Exception("Respuesta vacía")
                dto.toDomain()
            }
            404 -> throw Exception("El libro no ha sido encontrado")
            else -> throw Exception("Error inesperado: ${response.code()}")
        }
    }

    override suspend fun createBook(
        token: String,
        title: String,
        author: String,
        description: String?,
        imageUri: Uri
    ): Result<Unit> {
        return try {
            val titleRB = title.toRequestBody("text/plain".toMediaTypeOrNull())
            val authorRB = author.toRequestBody("text/plain".toMediaTypeOrNull())
            val descriptionRB = description?.toRequestBody("text/plain".toMediaTypeOrNull())

            val originalFileName = getFileName(imageUri)

            val inputStream = context.contentResolver.openInputStream(imageUri)
            val bytes = inputStream?.readBytes() ?: throw Exception("No se pudo leer la imagen")

            val requestFile = bytes.toRequestBody("image/*".toMediaTypeOrNull())
            val filePart = MultipartBody.Part.createFormData(
                name = "file",
                filename = originalFileName,
                body = requestFile
            )
            val response = bookTrackerService.createBook(
                token = token,
                title = titleRB,
                author = authorRB,
                description = descriptionRB,
                file = filePart
            )

            when(response.code()) {
                201 -> Result.success(Unit)
                401 -> throw Exception("No tiene permisos o no se ha logueado")
                409 -> throw Exception("El libro ya se encuentra registrado")
                422 -> throw Exception("Datos mal formateados")
                else -> throw Exception("Error inesperado: ${response.code()}")
            }
        } catch(e: Exception) {
            throw e
        }
    }

    private fun getFileName(uri: Uri): String {
        var name = "book_image.jpg" // Nombre por defecto con extensión por si falla
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val index = it.getColumnIndex(android.provider.OpenableColumns.DISPLAY_NAME)
                if (index != -1) name = it.getString(index)
            }
        }
        return name
    }
}
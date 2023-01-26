package com.fastcampus.intermediatebookreview.model

import com.google.gson.annotations.SerializedName

data class SearchBookDto(
    @SerializedName("items") val books: List<Book>,
)

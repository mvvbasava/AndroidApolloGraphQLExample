package com.example.androidapollographqlexample.data.model

data class Character(
    val id: String,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val origin: Location?,
    val location: Location?,
    val episodes: List<Episode>
)

data class Location(
    val id: String,
    val name: String,
    val type: String,
    val dimension: String
)

data class Episode(
    val id: String,
    val name: String,
    val airDate: String,
    val episode: String
)

data class CharacterInfo(
    val count: Int,
    val pages: Int,
    val next: Int?,
    val prev: Int?
)

data class CharactersResponse(
    val info: CharacterInfo?,
    val results: List<Character>?
)

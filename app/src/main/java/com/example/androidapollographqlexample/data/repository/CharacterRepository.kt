package com.example.androidapollographqlexample.data.repository

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloException
import com.example.androidapollographqlexample.graphql.GetCharacterByIdQuery
import com.example.androidapollographqlexample.graphql.GetCharactersQuery
import com.example.androidapollographqlexample.data.model.Character
import com.example.androidapollographqlexample.data.model.CharactersResponse
import com.example.androidapollographqlexample.network.ApolloClientProvider
import com.example.androidapollographqlexample.utils.NetworkUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterRepository {
    
    private val apolloClient = ApolloClientProvider.apolloClient
    
    suspend fun getCharacters(page: Int? = null, filter: com.example.androidapollographqlexample.graphql.type.FilterCharacter? = null): Flow<Result<CharactersResponse>> = flow {
        try {
            val response: ApolloResponse<GetCharactersQuery.Data> = apolloClient.query(
                GetCharactersQuery(
                    page = com.apollographql.apollo3.api.Optional.presentIfNotNull(page),
                    filter = com.apollographql.apollo3.api.Optional.presentIfNotNull(filter)
                )
            ).execute()
            
            if (response.data?.characters != null) {
                val characters = response.data!!.characters!!
                val characterList = characters.results?.mapNotNull { character ->
                    character?.let {
                        Character(
                            id = it.id ?: "",
                            name = it.name ?: "",
                            status = it.status ?: "",
                            species = it.species ?: "",
                            type = it.type ?: "",
                            gender = it.gender ?: "",
                            image = it.image ?: "",
                            origin = it.origin?.let { origin ->
                                com.example.androidapollographqlexample.data.model.Location(
                                    id = origin.id ?: "",
                                    name = origin.name ?: "",
                                    type = origin.type ?: "",
                                    dimension = origin.dimension ?: ""
                                )
                            },
                            location = it.location?.let { location ->
                                com.example.androidapollographqlexample.data.model.Location(
                                    id = location.id ?: "",
                                    name = location.name ?: "",
                                    type = location.type ?: "",
                                    dimension = location.dimension ?: ""
                                )
                            },
                            episodes = it.episode?.mapNotNull { episode ->
                                episode?.let { ep ->
                                    com.example.androidapollographqlexample.data.model.Episode(
                                        id = ep.id ?: "",
                                        name = ep.name ?: "",
                                        airDate = ep.air_date ?: "",
                                        episode = ep.episode ?: ""
                                    )
                                }
                            } ?: emptyList()
                        )
                    }
                } ?: emptyList()
                
                val info = characters.info?.let { info ->
                    com.example.androidapollographqlexample.data.model.CharacterInfo(
                        count = info.count ?: 0,
                        pages = info.pages ?: 0,
                        next = info.next,
                        prev = info.prev
                    )
                }
                
                emit(Result.success(CharactersResponse(info, characterList)))
            } else {
                emit(Result.failure(Exception("No data received")))
            }
        } catch (e: ApolloException) {
            emit(Result.failure(Exception(NetworkUtils.getErrorMessage(e))))
        } catch (e: Exception) {
            emit(Result.failure(Exception(NetworkUtils.getErrorMessage(e))))
        }
    }
    
    suspend fun getCharacterById(id: String): Flow<Result<Character>> = flow {
        try {
            val response: ApolloResponse<GetCharacterByIdQuery.Data> = apolloClient.query(
                GetCharacterByIdQuery(id = id)
            ).execute()
            
            if (response.data?.character != null) {
                val character = response.data!!.character!!
                val characterModel = Character(
                    id = character.id ?: "",
                    name = character.name ?: "",
                    status = character.status ?: "",
                    species = character.species ?: "",
                    type = character.type ?: "",
                    gender = character.gender ?: "",
                    image = character.image ?: "",
                    origin = character.origin?.let { origin ->
                        com.example.androidapollographqlexample.data.model.Location(
                            id = origin.id ?: "",
                            name = origin.name ?: "",
                            type = origin.type ?: "",
                            dimension = origin.dimension ?: ""
                        )
                    },
                    location = character.location?.let { location ->
                        com.example.androidapollographqlexample.data.model.Location(
                            id = location.id ?: "",
                            name = location.name ?: "",
                            type = location.type ?: "",
                            dimension = location.dimension ?: ""
                        )
                    },
                    episodes = character.episode?.mapNotNull { episode ->
                        episode?.let { ep ->
                            com.example.androidapollographqlexample.data.model.Episode(
                                id = ep.id ?: "",
                                name = ep.name ?: "",
                                airDate = ep.air_date ?: "",
                                episode = ep.episode ?: ""
                            )
                        }
                    } ?: emptyList()
                )
                
                emit(Result.success(characterModel))
            } else {
                emit(Result.failure(Exception("Character not found")))
            }
        } catch (e: ApolloException) {
            emit(Result.failure(Exception(NetworkUtils.getErrorMessage(e))))
        } catch (e: Exception) {
            emit(Result.failure(Exception(NetworkUtils.getErrorMessage(e))))
        }
    }
}

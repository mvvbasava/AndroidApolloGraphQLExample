package com.example.androidapollographqlexample.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapollographqlexample.data.model.Character
import com.example.androidapollographqlexample.data.model.CharactersResponse
import com.example.androidapollographqlexample.data.repository.CharacterRepository
import com.example.androidapollographqlexample.graphql.GetCharactersQuery
import com.example.androidapollographqlexample.graphql.type.FilterCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class CharacterUiState {
    object Loading : CharacterUiState()
    data class Success(val characters: List<Character>, val info: com.example.androidapollographqlexample.data.model.CharacterInfo?) : CharacterUiState()
    data class Error(val message: String) : CharacterUiState()
}

sealed class CharacterDetailUiState {
    object Loading : CharacterDetailUiState()
    data class Success(val character: Character) : CharacterDetailUiState()
    data class Error(val message: String) : CharacterDetailUiState()
}

class CharacterViewModel : ViewModel() {
    private val repository = CharacterRepository()
    
    private val _charactersUiState = MutableStateFlow<CharacterUiState>(CharacterUiState.Loading)
    val charactersUiState: StateFlow<CharacterUiState> = _charactersUiState.asStateFlow()
    
    private val _characterDetailUiState = MutableStateFlow<CharacterDetailUiState>(CharacterDetailUiState.Loading)
    val characterDetailUiState: StateFlow<CharacterDetailUiState> = _characterDetailUiState.asStateFlow()
    
    private var currentPage = 1
    private var currentFilter: FilterCharacter? = null
    
    init {
        loadCharacters()
    }
    
    fun loadCharacters(page: Int = 1, filter: FilterCharacter? = null) {
        currentPage = page
        currentFilter = filter
        
        viewModelScope.launch {
            _charactersUiState.value = CharacterUiState.Loading
            repository.getCharacters(page, filter).collect { result ->
                result.fold(
                    onSuccess = { response ->
                        _charactersUiState.value = CharacterUiState.Success(
                            characters = response.results ?: emptyList(),
                            info = response.info
                        )
                    },
                    onFailure = { exception ->
                        _charactersUiState.value = CharacterUiState.Error(
                            message = exception.message ?: "An error occurred"
                        )
                    }
                )
            }
        }
    }
    
    fun loadCharacterById(id: String) {
        viewModelScope.launch {
            _characterDetailUiState.value = CharacterDetailUiState.Loading
            repository.getCharacterById(id).collect { result ->
                result.fold(
                    onSuccess = { character ->
                        _characterDetailUiState.value = CharacterDetailUiState.Success(character)
                    },
                    onFailure = { exception ->
                        _characterDetailUiState.value = CharacterDetailUiState.Error(
                            message = exception.message ?: "An error occurred"
                        )
                    }
                )
            }
        }
    }
    
    fun loadNextPage() {
        val currentState = _charactersUiState.value
        if (currentState is CharacterUiState.Success && currentState.info?.next != null) {
            loadCharacters(currentState.info.next, currentFilter)
        }
    }
    
    fun retry() {
        loadCharacters(currentPage, currentFilter)
    }
    
    fun filterCharacters(name: String? = null, status: String? = null, species: String? = null) {
        val filter = FilterCharacter(
            name = com.apollographql.apollo3.api.Optional.presentIfNotNull(name),
            status = com.apollographql.apollo3.api.Optional.presentIfNotNull(status),
            species = com.apollographql.apollo3.api.Optional.presentIfNotNull(species)
        )
        loadCharacters(page = 1, filter = filter)
    }
    
    fun clearFilter() {
        loadCharacters(page = 1, filter = null)
    }
}

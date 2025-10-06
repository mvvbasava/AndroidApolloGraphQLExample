# Android Apollo GraphQL Example

A modern Android application demonstrating GraphQL integration using Apollo Client, Jetpack Compose, and MVVM architecture pattern.

## Main Screen ScreenShot

<img width="468" height="1001" alt="image" src="https://github.com/user-attachments/assets/fb29cf22-b638-4ba1-b855-7c8989ae30e0" />

## Features

- **GraphQL Integration**: Uses Apollo Client for efficient data fetching from the Rick and Morty API
- **Modern UI**: Built with Jetpack Compose and Material 3 design system
- **MVVM Architecture**: Clean separation of concerns with ViewModels and Repository pattern
- **Navigation**: Type-safe navigation between screens using Navigation Compose
- **Image Loading**: Efficient image loading and caching with Coil
- **Error Handling**: Comprehensive error handling with retry mechanisms
- **Loading States**: Proper loading states and skeleton screens
- **Filtering**: Advanced filtering capabilities for characters
- **Responsive Design**: Optimized for different screen sizes

## Architecture

The app follows MVVM (Model-View-ViewModel) architecture:

- **Model**: Data models and repository classes
- **View**: Jetpack Compose UI components
- **ViewModel**: State management and business logic
- **Repository**: Data access layer with GraphQL integration

## Tech Stack

- **Kotlin**: Programming language
- **Jetpack Compose**: Modern UI toolkit
- **Apollo GraphQL**: GraphQL client
- **Navigation Compose**: Type-safe navigation
- **ViewModel**: State management
- **Coil**: Image loading
- **OkHttp**: HTTP client
- **Coroutines**: Asynchronous programming

## GraphQL Schema

The app uses the Rick and Morty GraphQL API with the following main queries:

- `GetCharacters`: Fetches paginated list of characters with filtering
- `GetCharacterById`: Fetches detailed information for a specific character
- `GetLocations`: Fetches paginated list of locations

## Key Components

### Screens
- `CharacterListScreen`: Displays paginated list of characters with filtering
- `CharacterDetailScreen`: Shows detailed character information

### ViewModels
- `CharacterViewModel`: Manages character data state and business logic

### Repository
- `CharacterRepository`: Handles GraphQL data fetching and mapping

### Navigation
- `AppNavigation`: Handles navigation between screens

## Getting Started

1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run the app

The app will automatically fetch and display characters from the Rick and Morty API.

## API Endpoint

The app uses the public Rick and Morty GraphQL API:
- Base URL: `https://rickandmortyapi.com/graphql`

## Dependencies

Key dependencies include:
- Apollo GraphQL Client
- Jetpack Compose BOM
- Navigation Compose
- ViewModel Compose
- Coil for image loading
- OkHttp for networking

## Features in Detail

### Character List
- Paginated loading with infinite scroll
- Advanced filtering by name, status, and species
- Pull-to-refresh functionality
- Error handling with retry options

### Character Details
- Comprehensive character information
- Origin and location details
- Episode appearances
- High-quality character images

### Error Handling
- Network error handling
- Graceful degradation
- Retry mechanisms
- User-friendly error messages

## Future Enhancements

Potential improvements could include:
- Offline support with caching
- Search functionality
- Favorites system
- Dark theme optimization
- Unit and UI tests
- Dependency injection with Hilt

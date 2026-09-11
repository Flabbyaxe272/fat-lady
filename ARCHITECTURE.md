# Fat Lady — Architecture Conventions

Package root: `com.farrisfam.fatflady`

This file is the source of truth for where new code goes. When in doubt, check here
before creating a new package — and update this file when a real structural decision
gets made (not for every file, just when a new package is introduced or a convention
changes).

## Package layout

```
com.farrisfam.fatflady
├── core/            App-wide singletons and infrastructure. No UI, no navigation.
│                    Scoped via Hilt (@Singleton), injected wherever needed.
│                    e.g. core/player/PlayerManager.kt
│
├── data/            Domain models and data access. No UI, no ViewModels.
│   ├── model/       Plain data classes representing app domain objects (Song, Album...)
│   ├── repository/  Mediates between data sources and features. One repo per domain
│   │                area (SongRepository, AlbumRepository...).
│   └── local/        Local data sources (MediaStore, Room DAOs). Talks to Android APIs
│                     directly; repositories call into this, features never do.
│
├── feature/         One package per feature area. NOT strictly 1:1 with screens —
│                    a feature package can hold multiple related composables/ViewModels
│                    if they share state or logic (e.g. player controls used both
│                    full-screen and as a mini-player).  bbbr fvcde5vccccdc 
│   ├── home/
│   ├── songs/
│   ├── albums/
│   ├── artists/
│   ├── playlists/
│   ├── search/
│   ├── settings/
│   ├── nowplaying/  The full Now Playing screen + its ViewModel.
│   └── player/       Playback UI shared across contexts, not screen-specific
│                     (MiniPlayerBar now; future: shared progress bar, queue sheet).
│                     Name is "player" not "miniplayer" — this package covers any
│                     playback UI that isn't a full screen, not just the mini-player.
│
├── navigation/       Routing plumbing ONLY: destination definitions, NavHost, the
│                     top-level Scaffold that assembles screens + persistent UI
│                     (bottom nav, mini-player placement). No feature logic, no
│                     state observation beyond what's needed to decide routing/visibility.
│
└── ui/ (future)      Shared design-system composables with no feature-specific logic:
                       theme, typography, reusable atoms (song row, album art, loading/
                       error states). Not yet created — add when the first reusable
                       component is needed (see UI Foundation section of the task plan).
```

## Placement rules (in order of precedence)

1. **Does it talk to Android system APIs directly (MediaStore, ContentResolver, Room, DataStore)?**
   → `data/local/`

2. **Is it a plain data class with no logic?**
   → `data/model/`

3. **Does it mediate between a data source and features (fetch, cache, transform)?**
   → `data/repository/`

4. **Is it a singleton service with no UI (player engine, session manager, network client)?**
   → `core/`

5. **Is it routing/navigation plumbing — a destination, a NavHost, or the top-level
   Scaffold assembling persistent UI?**
   → `navigation/`

6. **Is it feature-specific UI or a feature's ViewModel?**
   → `feature/<feature_name>/`
   — Package by feature area, not by screen count. A feature package can hold more
   than one screen or component if they share state (see `feature/player/`).

7. **Is it a generic, reusable UI piece with zero feature-specific logic
   (theme, a row component, a loading spinner)?**
   → `ui/` (create this package when the first one is needed — not yet present)

## Current state (as of this session)

```
core/player/PlayerManager.kt

data/model/Song.kt
data/repository/SongRepository.kt
data/local/MediaStoreSongSource.kt

feature/home/HomeScreen.kt
feature/songs/SongsScreen.kt
feature/songs/SongsViewModel.kt
feature/nowplaying/NowPlayingScreen.kt
feature/nowplaying/NowPlayingViewModel.kt
feature/player/MiniPlayerBar.kt
feature/placeholder/PlaceholderScreen.kt   (temporary — delete entries as real
                                             screens replace them: Albums, Artists,
                                             Playlists, Search, Settings)

navigation/FatLadyDestinations.kt
navigation/FatLadyNavHost.kt
navigation/FatLadyScaffold.kt
```

## Non-goals

- No cleanup/reorg passes without a concrete trigger (a real duplication found, a
  package genuinely mixing concerns). Structure gets corrected when the codebase
  tells you it's wrong, not on a schedule.
- `feature/placeholder/PlaceholderScreen.kt` is intentionally generic and temporary.
  Remove usage of it screen-by-screen as each placeholder gets built out; don't
  build a permanent abstraction around it.

# Fat Lady

Your music. Your server. Until she sings.
 
Fat Lady is a native Android music player (Kotlin + Jetpack Compose) for self-hosted 
[Navidrome](https://www.navidrome.org/) servers via the Subsonic API. Built for LAN, Tailscale, or 
HTTPS/Traefik access, this is (hopefully) the only music app you'll ever need. 

## Stack
- UI: Jetpack Compose, Material 3
- DI: Hilt
- Playback: AndroidX Media3 (ExoPlayer + MediaSession)
- Local storage: Room, DataStore
- Networking: OkHttp/Ktor, Subsonic API
- Images: Coil
- Nav: Navigation Compose

## Requirements

- Android Studio (current stable)
- JDK 25
- minSdk 29 / targetSdk 37 / compileSdk 37
- A running Navidrome instance reachable via LAN, Tailscale, or HTTPS

## Setup

```bash
git clone <repo-url>
cd fat-lady
./gradlew build
```

Open in Android Studio, let Gradle sync, run on a device/emulator with API 29+.

## Project Structure

Development follows a phased build order: UI first with fake data, backend wired in after:

- [x] Project Setup: Gradle, Compose, Hilt, Git branches
- [ ] UI Foundation: Material 3 theme, reusable components, fake preview data 
- [ ] Navigation: Screens (Home, Albums, Artists, Songs, Playlists, Search, Settings, Now Playing)
- [ ] Data Layer: Domain models, repositories, Room cache, DataStore
- [ ] Networking: Subsonic API client, auth, LAN/HTTPS/Tailscale connectivity
- [ ] Library: ViewModel-driven browsing, search, favorites
- [ ] Player: Media3 playback engine, queue, shuffle/repeat, mini-player
- [ ] Background Playback: Foreground media service, notifications, lock-screen controls
- [ ] Offline & Downloads: Local caching, offline playback
- [ ] Network Awareness: Connectivity switching between LAN/remote/VPN
- [ ] Polish & UX: Error states, accessibility, onboarding
- [ ] Testing & Release: Unit/UI tests, connectivity test matrix, signed APK
- [ ] Stretch Goals: Android Auto, and beyond-v0.1 features

## Configuration

Server connection details (URL, credentials) are entered via the in-app setup screen and stored 
securely on-device — no hardcoded server config.

## Status

v0.1 in active development. Core learning goals: Compose, Media3, MVVM/Hilt architecture, and 
reliable playback across changing network conditions (home Wi-Fi, cellular, Tailscale).// test line from main

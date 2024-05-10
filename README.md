# drunked

A simple app to track your drinking habits.

<img src="assets/drink_form.png" width="200px">
<img src="assets/session_recording_screen.png" width=200px>

This is not intended to be used for anything other than mild interest in your drinking habits during a Drinking Session.

## Stack

This is a Kotlin Multiplatform project targeting Android and iOS.

Compose Multiplatform is used for the UI.

ViewModels are used from the Experimental Compose Multiplatform Lifecycle support.

Koin is used for Dependency Injection.

## Database

This app uses SQLDelight for a MultiPlatform SQLLite Database Implementation on Android/iOS.

### ERD

```mermaid
erDiagram
    Drink ||--o{ DrinkEvent: "has"
    Session ||--o{ DrinkEvent: "has"
    Drink {
        int id
        string name
        float abv
        string type
    }
    DrinkEvent {
        int id
        string timestamp
        int volume
        float units
        int drink_id
        int session_id
    }
    Session {
        int id
        string date
    }
```

### Films Information App

#### About

- This app contains 2 screens : **Persons Screen and Films Screen**
- Persons Screen, fetch the list of persons/characters from a remote API, and displays them in the form of grid list.
- Since, this response can be big, pagination is applied to the Persons Screen.
- Films Screen works as a `details screen` and contains the details of films done by a particular character which are displayed in a grid list format.
- Persons Screen also has a Filter button inside it, clicking on which a bottom sheet is opened, which contains desired options for filters.
- [API LINK](https://swapi.dev/documentation)


#### Concepts Used

- [Kotlin](https://developer.android.com/kotlin/first): Kotlin is a modern and expressive programming language that interoperates seamlessly with Java. Known for its safety features, concise syntax, and powerful features, Kotlin has become the preferred language for Android development.
- [Android Jetpack](https://developer.android.com/jetpack) (Build Awesome UI's with compose)
- [MVVM (Clean Architecture)](https://developer.android.com/topic/architecture#recommended-app-arch): It is an architectural pattern that promotes a clear separation of concerns in the app and when combined with Clean Architecture principles, MVVM ensures a scalable, maintainable, and testable codebase.
- [Jetpack Navigation](https://developer.android.com/jetpack/compose/navigation)
- [RoomDB](https://developer.android.com/training/data-storage/room): RoomDB is a persistence library that provides an abstraction layer over SQLite, simplifying database operations in Android applications. With RoomDB, you can easily perform database transactions and efficiently manage the local storage of your app, enhancing data persistence and retrieval.
- [DaggerHilt](https://developer.android.com/training/dependency-injection/hilt-android): It is a dependency injection library built on top of Dagger 2. It simplifies the process of managing dependencies in your Android app, promoting modularization and making it easier to write and maintain clean, testable code.
- [Coroutines & Flows](https://kotlinlang.org/docs/coroutines-overview.html): Coroutines and Flows are powerful concurrency and asynchronous programming constructs in Kotlin. Coroutines simplify managing background tasks, making code more readable and maintainable. Flows extend coroutines by providing a reactive programming model for handling streams of data asynchronously.
- [Paging3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview): Paging3 is a library that helps load and display large sets of data efficiently in Android apps.
- [Retrofit](https://square.github.io/retrofit/): It is a popular HTTP client library for Android and Java that simplifies the process of making network requests. With a clean and simple API, Retrofit allows you to define API endpoints, manage request and response transformations, and handle errors effectively, streamlining the networking layer of your app.

#### Demo Video

https://github.com/utkarsh006/FilmsInfoApp/assets/94545831/34546195-ea96-47e1-95dd-1982ede94277


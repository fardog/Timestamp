# Timestamp

Android Wear complications for developers.

_Timestamp_ was created to provide a UTC time complication, displaying
24 hour time. Android Wear has a similar complication, however it requires
selecting Reykjavík timezone (and having `REY` shown on the screen at all
times).

It also contains a time-since-epoch complication for watch faces which can
display a "long" text complication. Tapping this complication shows you the
current seconds or milliseconds since epoch, based on your selected settings.

## Installation

There are several options for installation:

* Install via the [Google Play Store][play]
* Sideload the [signed apk][apk] in this repository
* Build from source

The process for building/sideloading APKs varies from watch to watch, and is
not documented here. The project is a standard [Android Studio][studio]
project, and may be directly ported/built with no special considerations.

## Complications

* [x] UTC Time
* [x] Unix Timestamp

## License

```
   Copyright 2018 Nathan Wittstock

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0
```

[apk]: ./app/release/app-release.apk
[play]: https://play.google.com/store/apps/details?id=io.fardog.timestamp
[studio]: https://developer.android.com/studio/index.html

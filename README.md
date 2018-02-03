# Timestamp

Android Wear complications for developers.

**Note:** This is an app built quickly for my own use; it may eventually
be developed into something useful for others, but for now it's distributed
only via this repository.

_Timestamp_ was created to provide a UTC time complication, displaying
24 hour time. Android Wear has a similar complication, however it requires
selecting Reykjavík timezone (and having `REY` shown on the screen at all
times).

## Installation

A [signed apk][apk] is provided in this repository; it must be installed
using the Android developer tools, the process for which varies from watch
to watch. You may also build from source.

## Complications

* [x] UTC Time
* [ ] Unix Timestamp

## License

```
   Copyright 2018 Nathan Wittstock

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0
```

[apk]: ./app/release/app-release.apk

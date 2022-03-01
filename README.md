# Inker [![](https://jitpack.io/v/inlacou/Inker.svg)](https://jitpack.io/#inlacou/Inker)
Better logging aiming performance on release builds.

## Usage

### Enable/disable

We want to disable debug level:
```kt
Inker.logD = BuildConfig.DEBUG
```

We want to disable debug level logs by flavor variable:
```kt
Inker.logD = BuildConfig.DEBUG
```

We want to disable all levels but error ones:
```kt
Inker.logD = false
Inker.logV = false
Inker.logI = false
Inker.logW = false
Inker.logE = true
Inker.logWTF = true
```

### Use

Usual example:
```kt
Inker.d { "some variable: $variable" }
```

Throwable:
```kt
onError { throwable ->
  Inker.e { throwable }
}
```

## Results

Created a list of 30 000 somewhat complex items. The test is iterating over the list and logging the `item.toString()` on different logging libraries.

![results](https://github.com/inlacou/Inker/blob/master/pics/results_resumed.png)

## Thanks

I have to mention and thanks [@JakeWharton](https://github.com/JakeWharton) for his awesome work and inspiration and in this case, for the [Timber](https://github.com/JakeWharton/timber) library I have used for so long.

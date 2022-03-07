# Inker [![](https://jitpack.io/v/inlacou/Inker.svg)](https://jitpack.io/#inlacou/Inker)
Better logging aiming performance on release builds.

## Usage

Updated for v1.1.0.

### Enable/disable

We want to disable debug level:
```kt
class NoDebugMessagesColor: Inker.DebugColor() {
	override val d = null
}
...
Inker.mix(NoDebugMessagesColor())
```

We want to disable debug level logs by flavor variable:
```kt
class NoDebugMessagesColor: Inker.DebugColor() {
	override val d = if(BuildConfig.DEBUG) super.d else null
}
...
Inker.mix(NoDebugMessagesColor())
```

We want to disable all levels but error (and wtf) ones:
```kt
class InkerReleaseColor: Inker.DebugColor() {
	override val v = null
	override val d = null
	override val i = null
	override val w = null
	override val w2 = null
}
...
Inker.mix(InkerReleaseColor())
```

We can remove a mixed color with the `unMix` method, but it is rarely needed. Alternatively, we can manipulate the `palette` (where colors are mixed) by accessing `Inker.palette`.

### Use

Usual example:
```kt
Inker.d { "some variable: $variable" }
```

Throwable:
```kt
...
.onError { t: Throwable ->
  Inker.e { t }
}
```

## Results

Created a list of 300 000 somewhat complex items. The test is iterating over the list and logging the `item.toString()` on different logging libraries.

![results](https://github.com/inlacou/Inker/blob/master/pics/results_resumed-v1-1-0.png)

Item structure:
```kt
data class Person(val name: String, val surname: String, val age: Int, val active: Boolean, val height: Double, val currentCity: City, val birthCountry: Country)
data class City(val name: String, val country: Country?, val population: Double)
data class Country(val name: String, val sizeM2: Double)
```


## Thanks

I have to mention and thanks [@JakeWharton](https://github.com/JakeWharton) for his awesome work and inspiration and in this case, for the [Timber](https://github.com/JakeWharton/timber) library I have used for so long.

package com.inlacou.loggerlibraryproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inlacou.inker.Inker
import io.reactivex.rxjava3.core.Observable
import timber.log.Timber
import java.util.concurrent.TimeUnit

class WorkActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_work)
		Inker.log = true
		Timber.plant(Timber.DebugTree())
		Inker.d { "test-inker" }
		Timber.d("test-timber")
		Observable.timer(1, TimeUnit.SECONDS).subscribe({
			Inker.d { "rx-callback-test-inker" }
			Timber.d("rx-callback-test-timber") },{})
	}
}
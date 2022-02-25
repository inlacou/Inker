package com.inlacou.loggerlibraryproject

import timber.log.Timber

/**
 * Timber tree that disables Timber.d messages
 */
class TimberReleaseTree: Timber.DebugTree() {

	override fun d(t: Throwable?) {}
	override fun d(message: String?, vararg args: Any?) {}
	override fun d(t: Throwable?, message: String?, vararg args: Any?) {}

}
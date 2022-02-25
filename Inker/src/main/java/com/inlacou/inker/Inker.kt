package com.inlacou.inker

import android.util.Log
import java.util.regex.Pattern

object Inker {

	var log: Boolean = false

	private fun getStackTag(): String {
		val stack = Throwable().stackTrace
		val index = stack.indexOfLast { it.className.equals(Inker.javaClass.name, true) }
		return stack[index+1].let { "${createStackElementTag(it)}.${it.methodName}" }
	}

	private fun createStackElementTag(element: StackTraceElement): String {
		var tag = element.className.substringAfterLast('.')
		/*
		val m = Pattern.compile("(\\$\\d+)+$").matcher(tag)
		if (m.find()) tag = m.replaceAll("")
		*/
		// Tag length limit was removed in API 24.
		return tag
	}

	fun d(cb: () -> String?)   { if(log) d(cb()) }
	fun w(cb: () -> Any?)   { if(log) cb().let {
		when (it) {
			is String -> w(it)
			is Throwable -> w(it)
			else -> w(it.toString())
		}
	} }
	fun i(cb: () -> String?)   { if(log) i(cb()) }
	fun v(cb: () -> String?)   { if(log) v(cb()) }
	fun e(cb: () -> Any?)      { if(log) cb().let {
		when (it) {
			is String -> e(it)
			is Throwable -> e(it)
			else -> e(it.toString())
		}
	} }
	fun wtf(cb: () -> String?) { if(log) wtf(cb()) }

	/*
	fun d(s: String?)    { Timber.tag(getStackTag()); Timber.d(s) }
	fun w(s: String?)    { Timber.tag(getStackTag()); Timber.w(s) }
	fun w(t: Throwable?) { Timber.tag(getStackTag()); Timber.w(t) }
	fun i(s: String?)    { Timber.tag(getStackTag()); Timber.i(s) }
	fun v(s: String?)    { Timber.tag(getStackTag()); Timber.v(s) }
	fun e(s: String?)    { Timber.tag(getStackTag()); Timber.e(s) }
	fun e(t: Throwable?) { Timber.tag(getStackTag()); Timber.e(t) }
	fun wtf(s: String?)  { Timber.tag(getStackTag()); Timber.wtf(s) }
	*/

	fun d(s: String?)    { if(log) Log.d(getStackTag(), s.toString()) }
	fun w(s: String?)    { if(log) Log.w(getStackTag(), s.toString()) }
	fun w(t: Throwable?) { if(log) Log.w(getStackTag(), t) }
	fun i(s: String?)    { if(log) Log.i(getStackTag(), s.toString()) }
	fun v(s: String?)    { if(log) Log.v(getStackTag(), s.toString()) }
	fun e(s: String?)    { if(log) Log.e(getStackTag(), s.toString()) }
	fun e(t: Throwable?) { if(log) Log.e(getStackTag(), null, t) }
	fun wtf(s: String?)  { if(log) Log.wtf(getStackTag(), s) }

}
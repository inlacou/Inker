package com.inlacou.inker

import android.util.Log

object Inker {

	var logD: Boolean = true
	var logW: Boolean = true
	var logI: Boolean = true
	var logV: Boolean = true
	var logE: Boolean = true
	var logWTF: Boolean = true

	private fun getStackTag(): String {
		val stack = Throwable().stackTrace
		val index = stack.indexOfLast { it.className.equals(Inker.javaClass.name, true) }
		return stack[index+1].let { "${createStackElementTag(it)}.${it.methodName}" }
	}

	private fun createStackElementTag(element: StackTraceElement): String = element.className.substringAfterLast('.')

	fun d(cb: () -> String?)   { if(logD) d(cb()) }
	fun w(cb: () -> Any?)   { if(logW) cb().let {
		when (it) {
			is String -> w(it)
			is Throwable -> w(it)
			else -> w(it.toString())
		}
	} }
	fun i(cb: () -> String?)   { if(logI) i(cb()) }
	fun v(cb: () -> String?)   { if(logV) v(cb()) }
	fun e(cb: () -> Any?)      { if(logE) cb().let {
		when (it) {
			is String -> e(it)
			is Throwable -> e(it)
			else -> e(it.toString())
		}
	} }
	fun wtf(cb: () -> String?) { if(logWTF) wtf(cb()) }

	private fun d(s: String?)    { Log.d(getStackTag(), s.toString()) }
	private fun w(s: String?)    { Log.w(getStackTag(), s.toString()) }
	private fun w(t: Throwable?) { Log.w(getStackTag(), t) }
	private fun i(s: String?)    { Log.i(getStackTag(), s.toString()) }
	private fun v(s: String?)    { Log.v(getStackTag(), s.toString()) }
	private fun e(s: String?)    { Log.e(getStackTag(), s.toString()) }
	private fun e(t: Throwable?) { Log.e(getStackTag(), null, t) }
	private fun wtf(s: String?)  { Log.wtf(getStackTag(), s) }

}
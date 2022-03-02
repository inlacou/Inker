package com.inlacou.inker

import android.util.Log

object Inker {

	val palette = mutableListOf<Color>()

	fun mix(color: Color) { palette.add(color) }
	fun unMix(color: Color) { palette.remove(color) }

	fun d(cb: () -> String?)   { val d = palette.mapNotNull { it.d }; if(d.isNotEmpty()) cb().let { d.forEach { d -> d(it) } } }
	fun w(cb: () -> Any?)   {
		val w = palette.mapNotNull { it.w }
		val w2 = palette.mapNotNull { it.w2 }
		if(w.isNotEmpty() || w2.isNotEmpty()) cb().let {
		when (it) {
			is Throwable -> w2.forEach { w2 -> w2(it) }
			is String -> w.forEach { w -> w(it) }
			else -> w.forEach { w -> w(it.toString()) }
		}
	} }
	fun i(cb: () -> String?)   { val i = palette.mapNotNull { it.d }; if(i.isNotEmpty()) cb().let { i.forEach { i -> i(it) } } }
	fun v(cb: () -> String?)   { val v = palette.mapNotNull { it.v }; if(v.isNotEmpty()) cb().let { v.forEach { v -> v(it) } } }
	fun e(cb: () -> Any?)      {
		val e = palette.mapNotNull { it.e }
		val e2 = palette.mapNotNull { it.e2 }
		if(e.isNotEmpty() || e2.isNotEmpty()) cb().let {
		when (it) {
			is Throwable -> e2.forEach { e2 -> e2(it) }
			is String -> e.forEach { e -> e(it) }
			else -> e.forEach { e -> e(it.toString()) }
		}
	} }
	fun wtf(cb: () -> String?) { val wtf = palette.mapNotNull { it.wtf }; if(wtf.isNotEmpty()) cb().let { wtf.forEach { wtf -> wtf(it) } } }

	open class DebugColor: Color() {
		override val v: ((s: String?) -> Unit)? = { Log.v(createTag(), it.toString()) }
		override val d: ((s: String?) -> Unit)? = { Log.d(createTag(), it.toString()) }
		override val i: ((s: String?) -> Unit)? = { Log.i(createTag(), it.toString()) }
		override val w: ((s: String?) -> Unit)? = { Log.w(createTag(), it.toString()) }
		override val w2: ((t: Throwable?) -> Unit)? = { Log.w(createTag(), it) }
		override val e: ((s: String?) -> Unit)? = { Log.e(createTag(), it.toString()) }
		override val e2: ((t: Throwable?) -> Unit)? = { Log.e(createTag(), null, it) }
		override val wtf: ((s: String?) -> Unit)? =  { Log.wtf(createTag(), it) }
	}

	abstract class Color {
		open val v: ((s: String?) -> Unit)? = null
		open val d: ((s: String?) -> Unit)? = null
		open val i: ((s: String?) -> Unit)? = null
		open val w: ((s: String?) -> Unit)? = null
		open val w2: ((t: Throwable?) -> Unit)? = null
		open val e: ((s: String?) -> Unit)? = null
		open val e2: ((t: Throwable?) -> Unit)? = null
		open val wtf: ((s: String?) -> Unit)? =  null

		internal fun createTag(): String {
			val stack = Throwable().stackTrace
			val index = stack.indexOfLast { it.className.equals(Inker.javaClass.name, true) }
			return stack[index+1].let { "${it.elementToTag()}.${it.methodName}" }
		}

		private fun StackTraceElement.elementToTag(): String = className.substringAfterLast('.')
	}

}
package com.inlacou.loggerlibraryproject

/**
 * Takes time as milliseconds (Long) and returns a text of the format:
 * Xh Xm Xs Xms
 * Xm Xs Xms
 * Xs Xms
 * Xms
 * Or so. If the value to put is 0 it is ignored. So It can algo be:
 * Xh Xs
 * Xm Xms
 * ...
 */
fun Long.msToTime(): String {
	var current = this
	val hours = current   / 3600000
	current %= 3600000
	val minutes = current /   60000
	current %= 60000
	val seconds = current /    1000
	current %= 1000
	val millis = current
	var result = ""
	if(hours!=0L) result += "${hours}h "
	if(minutes!=0L) result += "${minutes}m "
	if(seconds!=0L) result += "${seconds}s "
	if(millis!=0L) result += "${millis}ms"
	return result.trim()
}
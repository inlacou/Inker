package com.inlacou.loggerlibraryproject

import org.junit.Test

import org.junit.Assert.*

class UnitTests {
	@Test fun `3600000 is 1h`() = assertEquals("1h", 3600000L.msToTime())
	@Test fun `3660000 is 1h 1m`() = assertEquals("1h 1m", 3660000L.msToTime())
	@Test fun `3601000 is 1h 1s`() = assertEquals("1h 1s", 3601000L.msToTime())
	@Test fun `3600001 is 1h 1ms`() = assertEquals("1h 1ms", 3600001L.msToTime())
	@Test fun `  60001 is 1m 1ms`() = assertEquals("1m 1ms", 60001L.msToTime())
	@Test fun `   1001 is 1s 1ms`() = assertEquals("1s 1ms", 1001L.msToTime())
	@Test fun `3661000 is 1h 1m 1s`() = assertEquals("1h 1m 1s", 3661000L.msToTime())
	@Test fun `3661001 is 1h 1m 1s 1ms`() = assertEquals("1h 1m 1s 1ms", 3661001L.msToTime())
}
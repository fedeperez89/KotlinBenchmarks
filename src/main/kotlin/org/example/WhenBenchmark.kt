package org.example

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.TimeUnit

sealed class Parent
data class C1(val string: String) : Parent()
data class C2(val string2: String) : Parent()
data class C3(val int: Int) : Parent()
data class C4(val pair: Pair<String, Int>, val string: String) : Parent()
data class C5(val long: Long, val l2: Long, val s: String) : Parent()
object C6 : Parent()
object C7 : Parent()
data class C8(val s: String, val C1: C1) : Parent()
data class C9(val int: Int, val int2: Int) : Parent()
data class C10(val s: String, val pair: Pair<String, String>, val triple: Triple<C1, C2, C3>) : Parent()
object C11 : Parent()
object C12 : Parent()
data class C13(val boolean: Boolean) : Parent()
data class C14(val boolean: Boolean, val string: String) : Parent()

open class WhenBenchmark {

    @State(Scope.Thread)
    open class MyState {
        val c1 = C1("")
        val c2 = C2("")
        val c3 = C3(1)
        val c4 = C4("" to 1, "")
        val c5 = C5(1, 2, "")
        val c6 = C6
        val c7 = C7
        val c8 = C8("", c1)
        val c9 = C9(1, 2)
        val c10 = C10("", "" to "", Triple(C1(""), C2(""), C3(1)))
        val c11 = C11
        val c12 = C12
        val c13 = C13(false)
        val c14 = C14(false, "")
        val all = listOf(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14).shuffled()
        val first = all.first()
    }

    @Benchmark
    @Warmup(iterations = 0)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    fun runLotsOfWhen(blackhole: Blackhole, state: MyState) {
        val number = when (state.first) {
            is C1 -> {
                1
            }
            is C2 -> {
                2
            }
            is C3 -> {
                3
            }
            is C4 -> {
                4
            }
            is C5 -> {
                5
            }
            C6 -> {
                6
            }
            C7 -> {
                7
            }
            is C8 -> {
                8
            }
            is C9 -> {
                9
            }
            is C10 -> {
                10
            }
            C11 -> {
                11
            }
            C12 -> {
                12
            }
            is C13 -> {
                13
            }
            is C14 -> {
                14
            }
        }
        blackhole.consume(number)
    }
}

package com.example.hope.ui.theme

import java.util.concurrent.ThreadLocalRandom

fun dec2hex(number: Int, places: Int): String{ //converts decimal number into a hexadecimal represented by a string
    var hex = ""
    var n = number
    for (ii in 1..places){
        val remainder = n % 16
        n /= 16
        hex = when(remainder){//assigns value based on the remainders
            15 -> "F$hex"
            14 -> "E$hex"
            13 -> "D$hex"
            12 -> "C$hex"
            11 -> "B$hex"
            10 -> "A$hex"
            else -> "$remainder" + hex
        }
    }
    return hex
}

fun randomInt(rangeFirstNum: Int, rangeLastNum: Int): Int { //random integer number generator
    return ThreadLocalRandom.current().nextInt(rangeFirstNum, rangeLastNum)
}

fun isValidEmail(email: String): Boolean {//checks if the email contains @ and at least one character before and after it
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\$".toRegex()
    return emailRegex.matches(email)
}
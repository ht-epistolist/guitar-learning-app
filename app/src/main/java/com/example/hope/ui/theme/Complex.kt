package com.example.hope.ui.theme

class Complex(var R:Double, var I:Double){ //complex numbers class
    operator fun plus(addend: Complex) = Complex(//override the plus operator so to add the real and complex parts
        R + addend.R,
        I + addend.I)

    operator fun times(multiplier: Double) = Complex(//override the times operator for the complex number multiplied by constant (multiplying both sides by it)
        R*multiplier,
        I*multiplier)

    operator fun times(multiplier: Complex) = Complex(//override the times operator for two complex numbers
        R*multiplier.R - I*multiplier.I, //assign a real part to the subtraction of products of real and imaginary parts
        R*multiplier.I + I*multiplier.R) //assign a complex part to the sum of the real and imaginary parts products

    fun cfold1(){ //complex folding operation performed on the complex number with a modulus 1
        this.R %= 1
        this.I %= 1
    }
}
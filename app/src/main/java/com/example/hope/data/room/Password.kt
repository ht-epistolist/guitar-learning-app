package com.example.hope.data.room

import com.example.hope.ui.theme.Complex
import com.example.hope.ui.theme.dec2hex
import com.example.hope.ui.theme.maxPasswordLength
import com.example.hope.ui.theme.minPasswordLength
import com.example.hope.ui.theme.randomInt

class PasswordProcessing(var currentPassword: PasswordProperties) {//operations on the input password
    private fun validatePassword(inputPassword: String){
        //checks if the password has at least one digit
        currentPassword.containsNumbers = "(?=.*[0-9])".toRegex().containsMatchIn(inputPassword)
        //checks if the password has at least one lowercase letter
        currentPassword.containsLowercase = "(?=.*[a-z])".toRegex().containsMatchIn(inputPassword)
        //checks if the password has at least one uppercase letter
        currentPassword.containsCapitals = "(?=.*[A-Z])".toRegex().containsMatchIn(inputPassword)
        //checks if the password has at least one special symbol
        currentPassword.containsSymbols = "(?=.*[@$%&#_()=+?»«<>£§€{}\\[\\]-])".toRegex().containsMatchIn(inputPassword)
        //checks if the password has no whitespace characters
        currentPassword.noWhitespaceCharacters = "^\\S+\$".toRegex().matches(inputPassword)
        //checks if the password has a valid length
        currentPassword.validLength = ".{$minPasswordLength,$maxPasswordLength}".toRegex().matches(inputPassword)
    }

    fun isValid(inputPassword: String): Boolean{ //check if all the properties are valid
        validatePassword(inputPassword)
        return currentPassword.validLength &&
            currentPassword.containsLowercase &&
            currentPassword.containsCapitals &&
            currentPassword.containsNumbers &&
            currentPassword.containsSymbols &&
            currentPassword.noWhitespaceCharacters

    }

    fun generateFeedback(): String{
        var feedback = "invalid:"
        if (!currentPassword.validLength) feedback += " isn't $minPasswordLength-$maxPasswordLength long,"
        if (!currentPassword.containsLowercase) feedback += " no lowercase,"
        if (!currentPassword.containsCapitals) feedback += " no uppercase,"
        if (!currentPassword.containsNumbers) feedback += " no numbers,"
        if (!currentPassword.containsSymbols) feedback += " no special characters,"
        if (!currentPassword.noWhitespaceCharacters) feedback += " contains space,"
        return feedback
    }

    private fun addSalt(inputPassword: String): String{ //adds a random number to the string to eliminate the probability of frequency analysis of common passwords
        return inputPassword + randomInt(0, 9999).toString()
    }

    fun hashPassword(inputString: String):String {
        var z = Complex(0.0,1.0) //initializes complex constants
        var c = Complex(1.1, 1.1)
        val hashSize = 64 //assigns the size of the output hash
        var input = addSalt(inputString) //adds salt
        val padding = input.length % 4 //detects needed padding, as the algorithm uses 32-int type for processing
        if (padding > 0){//does padding, so the input matches 32-int type
            input += randomInt(0, 9).toString().repeat(padding)
        }
        val L = hashSize/32 //calculates the length of the string
        val a = 10.0 + hashSize/10000 //calculates the constant for the hashing algorithm

        val input32 = Array<Double>(input.length/4) { //turns the string into 32-int binary
                ii -> (input[2*ii].code*16777216 +
                input[2*ii+1].code*65536 +
                input[2*ii+2].code*256 +
                input[2*ii+3].code + 1)/
                (4294967296 + 1.0)
        }

        for (jj in 0..input32.size-1 step 2){//calculates c and z values
            c = Complex(input32[jj], input32[jj+1])
            iterativeComplexFolding(z, a, c)
        }

        for (kk in 0..hashSize + 200){ //performs the complex folding
            iterativeComplexFolding(z, a, c)
        }
        var hash = "#"
        for (ll in 1..L){ //assembles the hash
            iterativeComplexFolding(z, a, c)
            val f1 = (z.R * 1000 - (z.R * 1000).toInt())*(z.R * 1000 - (z.R * 1000).toInt()) //processes the real part
            val f2 = (z.I * 1000 - (z.I * 1000).toInt())*(z.I * 1000 - (z.I * 1000).toInt()) //processes the imaginative part
            hash += dec2hex(((f1 * 100000000000000 + 0.5) % 65536).toInt(), 4) //adds the strings in the hexadecimal format
            hash += dec2hex(((f2 * 100000000000000 + 0.5) % 65536).toInt(), 4)
        }
        return hash
    }

    private fun iterativeComplexFolding(z: Complex, a: Double, c: Complex): Complex{
        val newZ = z * z * a * a + c
        newZ.cfold1()
        return newZ
    }
}

data class PasswordProperties(
    var containsNumbers: Boolean = false,
    var containsLowercase: Boolean = false,
    var containsCapitals: Boolean = false,
    var containsSymbols: Boolean = false,
    var noWhitespaceCharacters: Boolean = true,
    var validLength: Boolean = false
)





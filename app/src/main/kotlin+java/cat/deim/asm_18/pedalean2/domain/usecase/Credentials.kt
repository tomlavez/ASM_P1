package cat.deim.asm_18.pedalean2.domain.usecase

data class Credentials(var email: String="", var password: String=""){

    fun isNotEmpty(): Boolean{
        return (email.isNotEmpty() && password.isNotEmpty())
    }

}

package cat.deim.asm_18.pedalean2.domain.usecase

import cat.deim.asm_18.pedalean2.domain.repository.IUserRepository

class LoginUsecase(private val userRepository: IUserRepository) {
    // Retorna um Result para o ViewModel saber se deu sucesso ou erro
    fun execute(credentials: Credentials): Result<Boolean> {
        if (!credentials.isNotEmpty()) {
            return Result.failure(Exception("As credenciais não podem estar vazias"))
        }

        return try {
            // Como é um banco de dados falso, vamos assumir que o "email" serve como ID
            // ou que o repositório consegue buscar o usuário por esse email.
            val user = userRepository.getUserById(credentials.email)
            
            // Define o usuário como o "usuário ativo" da sessão
            userRepository.setActiveUser(user)
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(Exception("Usuário não encontrado ou credenciais inválidas"))
        }
    }
}
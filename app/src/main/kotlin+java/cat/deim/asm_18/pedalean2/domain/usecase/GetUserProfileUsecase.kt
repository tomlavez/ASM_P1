package cat.deim.asm_18.pedalean2.domain.usecase

import cat.deim.asm_18.pedalean2.domain.models.User
import cat.deim.asm_18.pedalean2.domain.repository.IUserRepository

class GetUserProfileUsecase(private val userRepository: IUserRepository) {
    fun execute(): Result<User> {
        return try {
            val user = userRepository.getActiveUser()
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
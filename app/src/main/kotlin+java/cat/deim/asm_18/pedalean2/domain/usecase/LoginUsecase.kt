package cat.deim.asm_18.pedalean2.domain.usecase

import cat.deim.asm_18.pedalean2.domain.models.User
import cat.deim.asm_18.pedalean2.domain.repository.IUserRepository

class LoginUsecase(private var userRepository: IUserRepository) {

    fun execute(credentials: Credentials): Boolean {

        val user: User = userRepository.getActiveUser()

        return (user.email == credentials.email &&
                user.password == credentials.password)

    }

}

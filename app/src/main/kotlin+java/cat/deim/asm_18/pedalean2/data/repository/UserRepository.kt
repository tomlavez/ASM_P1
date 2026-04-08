package cat.deim.asm_18.pedalean2.data.repository

import cat.deim.asm_18.pedalean2.domain.models.User
import cat.deim.asm_18.pedalean2.domain.repository.IUserRepository
// O Android Studio importará o IDatasource e UserModel depois

class UserRepository(
    private val localDatasource: IDatasource<UserModel>
) : IUserRepository {

    private var activeUser: User? = null

    override fun getUserById(uuid: String): User {
        val userModel = localDatasource.getById(uuid)
            ?: throw Exception("User not found")
        
        // Mapeamento manual (Data -> Domain)
        return User(
            uuid = userModel.uuid,
            username = userModel.username,
            email = userModel.email,
            firstName = userModel.firstName,
            lastName = userModel.lastName
        )
    }

    override fun getActiveUser(): User {
        return activeUser ?: throw Exception("No active user logged in")
    }

    override fun setActiveUser(user: User) {
        this.activeUser = user
        // Se o usuário não existe no banco, insere
        if (localDatasource.getById(user.uuid) == null) {
            // Mapeamento manual (Domain -> Data) antes de salvar
            val userModel = UserModel(user.uuid, user.username, user.email, user.firstName, user.lastName)
            localDatasource.insert(userModel)
        }
    }

    override fun updateActiveUser(user: User) {
        this.activeUser = user
        val userModel = UserModel(user.uuid, user.username, user.email, user.firstName, user.lastName)
        localDatasource.update(userModel)
    }

    override fun deleteActiveUser(user: User) {
        localDatasource.delete(user.uuid)
        if (activeUser?.uuid == user.uuid) {
            activeUser = null
        }
    }
}
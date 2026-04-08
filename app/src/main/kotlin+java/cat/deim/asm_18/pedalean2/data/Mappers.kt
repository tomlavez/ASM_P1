package cat.deim.asmxx.pedalean2.data

import cat.deim.asmxx.pedalean2.domain.models.User
// import do UserModel da biblioteca do professor (o Android Studio vai importar depois)

// Converte da Biblioteca (Data) para o seu App (Domain)
fun UserModel.toDomain(): User {
    return User(
        uuid = this.uuid,
        name = this.name,
        email = this.email
    )
}

// Converte do seu App (Domain) para a Biblioteca (Data)
fun User.toData(): UserModel {
    return UserModel(
        uuid = this.uuid,
        name = this.name,
        email = this.email
    )
}
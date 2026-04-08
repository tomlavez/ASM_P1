package cat.deim.asm_18.pedalean2.data.repository

import cat.deim.asm_18.pedalean2.domain.models.Rent
import cat.deim.asm_18.pedalean2.domain.models.BikeRent
import cat.deim.asm_18.pedalean2.domain.models.UserRent
import cat.deim.asm_18.pedalean2.domain.repository.IRentRepository

class RentRepository(
    private val localDatasource: IDatasource<RentModel>
) : IRentRepository {

    // Nota: Como não temos a estrutura exata do RentModel da biblioteca, 
    // os mappers abaixo assumem que os campos são similares. 
    // Você pode precisar ajustar os nomes dos campos ('model.xxx') quando estiver no Android Studio.

    private fun mapToDomain(model: RentModel): Rent {
        return Rent(
            uuid = model.uuid,
            timeStart = model.timeStart,
            timeEnd = model.timeEnd,
            isRented = model.isRented,
            rentTime = model.rentTime,
            rentMeters = model.rentMeters,
            rentStartLatitude = model.rentStartLatitude,
            rentStartLongitude = model.rentStartLongitude,
            bikeRent = BikeRent(model.bikeRent.uuid, model.bikeRent.name),
            rentedBy = UserRent(model.rentedBy.username, model.rentedBy.email, model.rentedBy.firstName, model.rentedBy.lastName)
        )
    }

    private fun mapToModel(rent: Rent): RentModel {
        // Ajuste aqui se o construtor do RentModel da biblioteca for diferente
        return RentModel(
            rent.uuid, rent.timeStart, rent.timeEnd, rent.isRented, 
            rent.rentTime, rent.rentMeters, rent.rentStartLatitude, rent.rentStartLongitude,
            BikeRentModel(rent.bikeRent.uuid, rent.bikeRent.name),
            UserRentModel(rent.rentedBy.username, rent.rentedBy.email, rent.rentedBy.firstName, rent.rentedBy.lastName)
        )
    }

    override fun getAllRents(): List<Rent> {
        return localDatasource.getAll().map { mapToDomain(it) }
    }

    override fun createAllRents(rents: List<Rent>): Int {
        var count = 0
        rents.forEach { if (localDatasource.insert(mapToModel(it))) count++ }
        return count
    }

    override fun updateAllRents(rents: List<Rent>): Int {
        var count = 0
        rents.forEach { if (localDatasource.update(mapToModel(it))) count++ }
        return count
    }

    override fun deleteAllRents(): Int {
        val allRents = localDatasource.getAll()
        var count = 0
        allRents.forEach { if (localDatasource.delete(it.uuid)) count++ }
        return count
    }

    override fun getRentByBuid(uuid: String): Rent? {
        val model = localDatasource.getById(uuid)
        return model?.let { mapToDomain(it) }
    }

    override fun createRent(rent: Rent): Boolean {
        return localDatasource.insert(mapToModel(rent))
    }

    override fun updateRent(rent: Rent): Boolean {
        return localDatasource.update(mapToModel(rent))
    }

    override fun deleteRent(uuid: String): Boolean {
        return localDatasource.delete(uuid)
    }

    override fun isRentActive(uuid: String): Boolean {
        val model = localDatasource.getById(uuid)
        return model?.isRented ?: false
    }
}

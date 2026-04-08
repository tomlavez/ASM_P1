package cat.deim.asm_18.pedalean2.data.repository

import cat.deim.asm_18.pedalean2.domain.models.Bike
import cat.deim.asm_18.pedalean2.domain.repository.IBikeRepository

class BikeRepository(
    private val localDatasource: IDatasource<BikeModel>
) : IBikeRepository {

    // Função auxiliar privada para converter Data para Domain
    private fun mapToDomain(model: BikeModel): Bike {
        return Bike(
            uuid = model.uuid,
            name = model.name,
            latitude = model.latitude,
            longitude = model.longitude,
            type = model.type,
            meters = model.meters,
            lastUse = model.lastUse,
            lastMaintenance = model.lastMaintenance,
            batteryLevel = model.batteryLevel,
            isRented = model.isRented,
            isReserved = model.isReserved
        )
    }

    // Função auxiliar privada para converter Domain para Data
    private fun mapToModel(bike: Bike): BikeModel {
        return BikeModel(
            uuid = bike.uuid, name = bike.name, latitude = bike.latitude, 
            longitude = bike.longitude, type = bike.type, meters = bike.meters, 
            lastUse = bike.lastUse, lastMaintenance = bike.lastMaintenance, 
            batteryLevel = bike.batteryLevel, isRented = bike.isRented, isReserved = bike.isReserved
        )
    }

    override fun insertAll(bikes: List<Bike>): Int {
        var count = 0
        bikes.forEach { 
            if (localDatasource.insert(mapToModel(it))) count++ 
        }
        return count
    }

    override fun getAll(): List<Bike> {
        return localDatasource.getAll().map { mapToDomain(it) }
    }

    override fun updateAll(bikes: List<Bike>): Int {
        var count = 0
        bikes.forEach { 
            if (localDatasource.update(mapToModel(it))) count++ 
        }
        return count
    }

    override fun deleteAll(): Int {
        val allBikes = localDatasource.getAll()
        var count = 0
        allBikes.forEach { 
            if (localDatasource.delete(it.uuid)) count++ 
        }
        return count
    }

    override fun insert(bike: Bike): Boolean {
        return localDatasource.insert(mapToModel(bike))
    }

    override fun getByUuid(uuid: String): Bike? {
        val model = localDatasource.getById(uuid)
        return model?.let { mapToDomain(it) }
    }

    override fun update(bike: Bike): Boolean {
        return localDatasource.update(mapToModel(bike))
    }

    override fun delete(uuid: String): Boolean {
        return localDatasource.delete(uuid)
    }
}

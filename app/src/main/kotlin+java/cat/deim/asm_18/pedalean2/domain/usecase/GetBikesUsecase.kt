package cat.deim.asm_18.pedalean2.domain.usecase

import cat.deim.asm_18.pedalean2.domain.models.Bike
import cat.deim.asm_18.pedalean2.domain.repository.IBikeRepository

class GetBikesUsecase(private val bikeRepository: IBikeRepository) {
    fun execute(): List<Bike> {
        return bikeRepository.getAll()
    }
}
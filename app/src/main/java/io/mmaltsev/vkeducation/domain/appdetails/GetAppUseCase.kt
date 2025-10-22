package io.mmaltsev.vkeducation.domain.appdetails

class GetAppDetailsUseCase(
    private val appDetailsRepository: AppDetailsRepository,
) {
    suspend operator fun invoke(id: String): AppDetails {
        val app: AppDetails = appDetailsRepository.getAppDetails(id)

        if (app.category == Category.GAME) {
            throw IllegalStateException()
        }

        return app
    }
}
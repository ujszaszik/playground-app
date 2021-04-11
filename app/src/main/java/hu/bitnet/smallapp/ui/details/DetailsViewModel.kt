package hu.bitnet.smallapp.ui.details

import android.annotation.SuppressLint
import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.patloew.rxlocation.RxLocation
import hu.bitnet.smallapp.base.BaseUiModel
import hu.bitnet.smallapp.base.BaseViewModel
import hu.bitnet.smallapp.base.UiModelProperty
import hu.bitnet.smallapp.entity.PlaygroundEntity
import hu.bitnet.smallapp.extensions.distanceBetween
import hu.bitnet.smallapp.repository.IPlacesRepository
import hu.bitnet.smallapp.ui.details.share.ShareData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val rxLocation: RxLocation,
    private val repository: IPlacesRepository
) : BaseViewModel() {

    override val uiModel = DetailsUIModel()

    val intent = DetailsIntent(this)

    val error = MutableLiveData<Throwable>()

    val startTransition = MutableLiveData<Boolean>()

    val navigator = MutableLiveData<DetailsNavigation>()

    private val disposables = CompositeDisposable()

    init {
        uiModel.imageTransition = { startTransition.postValue(true) }
    }

    fun navigateToLocation(entity: PlaygroundEntity) {
        navigator.postValue(DetailsNavigation.NavigateToLocation(entity))
    }

    fun navigateToShare(entity: PlaygroundEntity) {
        ShareData(entity.name!!, entity.fullAddress).run {
            navigator.postValue(DetailsNavigation.NavigateToShare(this))
        }
    }

    fun fetchCurrentPlayground(id: Int) {
        disposables.add(
            repository.getPlaygroundById(id)
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .subscribe(::attachPlayground, error::postValue)
        )
    }

    private fun attachPlayground(playground: PlaygroundEntity) {
        uiModel.currentPlayground = playground
        calculateDistance()
    }

    @SuppressLint("MissingPermission")
    private fun calculateDistance() {
        disposable.add(
            rxLocation.location()
                .lastLocation()
                .subscribe(::attachDistance, error::postValue)
        )
    }

    private fun attachDistance(location: Location?) {
        uiModel.distance = location?.distanceBetween(uiModel.currentPlayground)?.toDouble()
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

}

class DetailsUIModel : BaseUiModel() {

    var currentPlayground: PlaygroundEntity? by UiModelProperty(null, uiModel = this)

    var distance: Double? by UiModelProperty(null, uiModel = this)

    var imageTransition: (() -> Unit)? by UiModelProperty(null, uiModel = this)
}

class DetailsIntent(private val viewModel: DetailsViewModel) {

    fun startNavigation(entity: PlaygroundEntity) {
        viewModel.navigateToLocation(entity)
    }

    fun shareCurrent(entity: PlaygroundEntity) {
        viewModel.navigateToShare(entity)
    }

}

sealed class DetailsNavigation {

    data class NavigateToLocation(val entity: PlaygroundEntity) : DetailsNavigation()

    data class NavigateToShare(val data: ShareData) : DetailsNavigation()

}
package hu.bitnet.smallapp.ui.launch

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationRequest
import com.patloew.rxlocation.RxLocation
import com.tbruyelle.rxpermissions2.RxPermissions
import hu.bitnet.smallapp.base.BaseUiModel
import hu.bitnet.smallapp.base.BaseViewModel
import hu.bitnet.smallapp.base.UiModelProperty
import hu.bitnet.smallapp.di.DI
import hu.bitnet.smallapp.entity.BaseEntity
import hu.bitnet.smallapp.repository.IPlacesRepository
import hu.bitnet.smallapp.utils.PermissionUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LaunchViewModel
@Inject
constructor(
    private val context: Context,
    private val rxLocation: RxLocation,
    private val repository: IPlacesRepository
) : BaseViewModel() {

    private val locationRequest: LocationRequest by lazy {
        LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
            .setSmallestDisplacement(100f)
            .setInterval(10000)
            .setFastestInterval(2000)
    }

    override val uiModel = LaunchUIModel()

    val intent = LaunchIntent(this)

    val navigator = MutableLiveData<LaunchNavigation>()

    val error = MutableLiveData<Throwable>()

    private val disposables = CompositeDisposable()

    fun navigateToLocationPermissionScreen() {
        navigator.postValue(LaunchNavigation.AskForLocationPermission())
    }

    fun checkLocationEnabled() {
        if (PermissionUtils.isLocationPermissionGranted(DI.activity!!)) {
            disposable.add(
                rxLocation
                    .settings()
                    .checkAndHandleResolution(locationRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ settingsIsOk: Boolean ->
                        if (settingsIsOk) {
                            getLastKnownLocation()
                            getLocationUpdates()
                        } else {
                            processLocation(null)
                            intent.askForLocationPermission()
                        }
                    }, error::postValue)
            )
        } else {
            enableLocationService()
        }
    }

    fun enableLocationService() {
        disposable.add(
            RxPermissions(DI.activity!!)
                .request(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ granted: Boolean ->
                    PermissionUtils.saveAskedPermissions(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )

                    if (granted) {
                        checkLocationEnabled()
                    } else {
                        processLocation(null)
                        intent.askForLocationPermission()
                    }
                }, error::postValue)
        )
    }

    @SuppressLint("MissingPermission")
    private fun getLastKnownLocation() {
        disposable.add(
            rxLocation.location()
                .lastLocation()
                .subscribe(
                    { lastLocation -> lastLocation?.let(::processLocation) },
                    error::postValue
                )
        )
    }

    @SuppressLint("MissingPermission")
    private fun getLocationUpdates() {
        if (PermissionUtils.isLocationPermissionGranted(context)) {
            disposable.add(rxLocation.location()
                .updates(locationRequest)
                .subscribeOn(Schedulers.io())
                .doOnNext { locationUpdate -> locationUpdate?.let(::processLocation) }
                .doOnError(error::postValue)
                .subscribe({ }, error::postValue)
            )
        } else {
            intent.askForLocationPermission()
        }
    }

    private fun processLocation(location: Location?) {
        disposables.add(
            repository.getPlaygrounds(location)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ places -> uiModel.nearbyPlaces = places }, error::postValue)
        )
    }

}

class LaunchUIModel : BaseUiModel() {

    var showSplash: Boolean by UiModelProperty(true, uiModel = this)

    var nearbyPlaces: List<BaseEntity> by UiModelProperty(emptyList(), uiModel = this)
}

class LaunchIntent(private val viewModel: LaunchViewModel) {

    fun askForLocationPermission() {
        viewModel.navigateToLocationPermissionScreen()
    }

}

sealed class LaunchNavigation {

    data class AskForLocationPermission(var param: Any? = null) : LaunchNavigation()

}
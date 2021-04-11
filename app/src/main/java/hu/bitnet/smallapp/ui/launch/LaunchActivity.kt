package hu.bitnet.smallapp.ui.launch

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hu.bitnet.smallapp.Log
import hu.bitnet.smallapp.R
import hu.bitnet.smallapp.base.BaseActivity
import hu.bitnet.smallapp.databinding.ActivityLayoutBinding
import hu.bitnet.smallapp.di.DI
import hu.bitnet.smallapp.di.ViewModelFactory
import hu.bitnet.smallapp.entity.BaseEntity
import hu.bitnet.smallapp.extensions.transitionNameFor
import hu.bitnet.smallapp.ui.details.DetailsActivity
import hu.bitnet.smallapp.utils.PermissionUtils
import javax.inject.Inject


class LaunchActivity : BaseActivity() {

    lateinit var binding: ActivityLayoutBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: LaunchViewModel

    private val cardOnClickListener = object : CardListOnClickListener {
        override fun onImageClicked(entity: BaseEntity, imageView: ImageView) {
            doOnImageClicked(entity, imageView)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerLaunchComponent.builder()
            .applicationComponent(DI.appComponent)
            .build()
            .inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(LaunchViewModel::class.java)
        observeNavigator()
        observeError()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_layout)
        binding.uiModel = viewModel.uiModel
        binding.listener = cardOnClickListener
    }

    override fun onStart() {
        super.onStart()
        viewModel.checkLocationEnabled()
    }

    private fun observeNavigator() {
        viewModel.navigator.observe(this, Observer { navigation ->
            when (navigation) {
                is LaunchNavigation.AskForLocationPermission -> askForLocationPermission()
            }
        })
    }

    private fun observeError() {
        viewModel.error.observe(this, Observer { error ->
            Log.d("Something's terrible", error)
        })
    }

    private fun doOnImageClicked(entity: BaseEntity, imageView: ImageView) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            imageView,
            ViewCompat.getTransitionName(imageView)!!
        )
        Intent(this, DetailsActivity::class.java)
            .applyExtra(R.string.details_id_key, entity.id)
            .applyExtra(R.string.details_transition_image_key, transitionNameFor(imageView))
            .also { startActivity(it, options.toBundle()) }
    }

    private fun askForLocationPermission() {
        val shouldShow: Boolean = ActivityCompat.shouldShowRequestPermissionRationale(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        val isFirstTime: Boolean =
            PermissionUtils.isFirstTimeAskingPermission(Manifest.permission.ACCESS_FINE_LOCATION)

        if (!PermissionUtils.isLocationPermissionGranted(this) && !shouldShow && !isFirstTime) {
            PermissionUtils.showPermissionSnackbar(
                Manifest.permission.ACCESS_FINE_LOCATION,
                this,
                binding.rootLayout
            )
        }
    }

    private fun Intent.applyExtra(resId: Int, value: Int): Intent {
        putExtra(getString(resId), value).run { return this }
    }

    private fun Intent.applyExtra(resId: Int, text: String?): Intent {
        putExtra(getString(resId), text).run { return this }
    }

}
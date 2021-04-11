package hu.bitnet.smallapp.ui.details

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import hu.bitnet.smallapp.R
import hu.bitnet.smallapp.base.BaseActivity
import hu.bitnet.smallapp.databinding.ActivityDetailsBinding
import hu.bitnet.smallapp.di.DI
import hu.bitnet.smallapp.di.ViewModelFactory
import hu.bitnet.smallapp.entity.error.EntityIsNullException
import hu.bitnet.smallapp.extensions.*
import hu.bitnet.smallapp.ui.details.dialog.EntityNotFoundDialog
import hu.bitnet.smallapp.ui.details.dialog.UnexpectedErrorDialog
import hu.bitnet.smallapp.ui.details.scroll.AlphaOnScrollBehavior
import hu.bitnet.smallapp.ui.details.scroll.addVerticalScrollListener
import hu.bitnet.smallapp.utils.WindowMetricsUtil
import javax.inject.Inject


class DetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailsBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerDetailsComponent.builder()
            .applicationComponent(DI.appComponent)
            .build()
            .inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(DetailsViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        binding.uiModel = viewModel.uiModel
        binding.intent = viewModel.intent

        navigateBackWhenClickOn(binding.backButton)

        fetchCurrentPlayground()

        supportActionBar?.hide()

        postponeEnterTransition()
        setImageTransitionName()
        observeTransition()

        observeNavigator()
        observeError()

        initializeAlphaOnScrollBehavior()
    }

    private fun fetchCurrentPlayground() {
        val id = intent.getIntExtra(R.string.details_id_key)
        viewModel.fetchCurrentPlayground(id)
    }

    private fun setImageTransitionName() {
        val imageTransitionName = intent.getStringExtra(R.string.details_transition_image_key)
        binding.playgroundDetailImage.transitionName = imageTransitionName
    }

    private fun observeTransition() {
        viewModel.startTransition.observeTrue(this) {
            supportStartPostponedEnterTransition()
        }
    }

    private fun observeNavigator() {
        viewModel.navigator.observeNotNull(this) { navigation ->
            when (navigation) {
                is DetailsNavigation.NavigateToLocation -> showPlaygroundLocation(navigation.entity)
                is DetailsNavigation.NavigateToShare -> shareData(buildShareText(navigation.data))
            }
        }
    }

    private fun observeError() {
        viewModel.error.observeNotNull(this) { error ->
            binding.detailsCoordinator.hide()
            when (error) {
                is EntityIsNullException -> EntityNotFoundDialog.show(this)
                else -> UnexpectedErrorDialog.show(this)
            }
        }
    }

    private fun initializeAlphaOnScrollBehavior() {
        val alphaOnScrollBehavior = AlphaOnScrollBehavior(binding.carButton)
        val windowHeight = WindowMetricsUtil.getCurrentHeight(this)
        val percentage = binding.detailsImageGuideline.getPercent()
        val scrollHeight = windowHeight * percentage
        binding.detailsScrollView.addVerticalScrollListener { scrollY ->
            alphaOnScrollBehavior.handleScroll(scrollHeight.toInt(), scrollY)
        }
    }

    private fun Intent.getStringExtra(resId: Int): String? {
        return extras?.getString(getString(resId))
    }

    private fun Intent.getIntExtra(resId: Int): Int {
        return extras?.getInt(getString(resId)) ?: 0
    }

}
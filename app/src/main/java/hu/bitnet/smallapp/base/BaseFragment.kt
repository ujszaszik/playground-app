package hu.bitnet.smallapp.base

import android.content.res.Resources.NotFoundException
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.Fragment
import hu.bitnet.smallapp.di.DI
import hu.bitnet.smallapp.utils.DateUtil

class BaseFragment : AppCompatDialogFragment(), IBaseFragment {

    companion object {

        private const val DEFAULT_CHILD_ANIMATION_DURATION = 250

        private fun getNextAnimationDuration(fragment: Fragment, defValue: Long): Long {
            return try {
                val nextAnimField = Fragment::class.java.getDeclaredField("mNextAnim")
                nextAnimField.isAccessible = true
                val nextAnimResource = nextAnimField.getInt(fragment)
                val nextAnim = AnimationUtils.loadAnimation(fragment.activity, nextAnimResource)
                nextAnim?.duration ?: defValue
            } catch (ex: NoSuchFieldException) {
                defValue
            } catch (ex: IllegalAccessException) {
                defValue
            } catch (ex: NotFoundException) {
                defValue
            }
        }
    }

    var firstViewCreation = true
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DI.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DateUtil.setHungarianTimeZone()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setupAnimation()
        firstViewCreation = false
    }

    override fun onCreateAnimation(
        transit: Int,
        enter: Boolean,
        nextAnim: Int
    ): Animation? {
        val parent = parentFragment
        return if (!enter && parent != null && parent.isRemoving) {
            val doNothingAnim: Animation = AlphaAnimation(1f, 1f)
            doNothingAnim.duration = getNextAnimationDuration(
                parent,
                DEFAULT_CHILD_ANIMATION_DURATION.toLong()
            )
            doNothingAnim
        } else {
            super.onCreateAnimation(transit, enter, nextAnim)
        }
    }

    protected fun setupAnimation() {
        //
    }
}
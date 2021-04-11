package hu.bitnet.smallapp.ui.details.view.simpleaction

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import hu.bitnet.smallapp.R
import hu.bitnet.smallapp.databinding.LayoutSimpleActionBinding

class SimpleActionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutSimpleActionBinding =
        LayoutSimpleActionBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SimpleActionView)

        val bgColor = typedArray.getColor(R.styleable.SimpleActionView_actionBgColor, 0)
        setBgColor(bgColor)

        val actionIcon = typedArray.getDrawable(R.styleable.SimpleActionView_actionIcon)
        setActionIcon(actionIcon)

        val actionText = typedArray.getString(R.styleable.SimpleActionView_actionText)
        setActionText(actionText)

        typedArray.recycle()
    }

    private fun setBgColor(resourceId: Int) {
        binding.simpleActionLayout.setBackgroundColor(resourceId)
    }

    private fun setActionIcon(drawable: Drawable?) {
        drawable?.let { binding.simpleActionIcon.setImageDrawable(it) }
    }

    private fun setActionText(text: String?) {
        text?.let { binding.simpleActionText.text = it }
    }

    fun setOnClickListener(block: () -> Unit) {
        with(binding) {
            simpleActionIcon.setOnClickListener { block.invoke() }
            simpleActionText.setOnClickListener { block.invoke() }
        }
    }

}

@BindingAdapter("app:doOnClick")
fun SimpleActionView.doOnClick(block: () -> Unit) {
    setOnClickListener(block)
}
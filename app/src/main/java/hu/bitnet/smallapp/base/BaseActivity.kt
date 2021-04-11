package hu.bitnet.smallapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hu.bitnet.smallapp.di.DI

abstract class BaseActivity : AppCompatActivity(), IBaseActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        DI.inject(this)
        super.onCreate(savedInstanceState)
    }
}
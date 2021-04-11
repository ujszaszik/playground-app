package hu.bitnet.smallapp.di.module

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object EncryptionModule {

    private const val PREFS_NAME = "small-app-prefs"

    @Provides
    @Reusable
    fun provideEncryptedPreferences(context: Context, masterKey: MasterKey): SharedPreferences {
        return EncryptedSharedPreferences.create(
            context,
            PREFS_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Provides
    @Reusable
    fun provideMasterKey(context: Context, keyGenSpec: KeyGenParameterSpec): MasterKey {
        return MasterKey.Builder(context)
            .setKeyGenParameterSpec(keyGenSpec)
            .build()
    }

    @Provides
    @Reusable
    fun provideKeyGenParameterSpec(): KeyGenParameterSpec {
        return KeyGenParameterSpec.Builder(
            MasterKey.DEFAULT_MASTER_KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(256)
            .build()

    }
}
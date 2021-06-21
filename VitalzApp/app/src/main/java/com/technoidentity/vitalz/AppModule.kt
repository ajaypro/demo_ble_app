package com.technoidentity.vitalz

import android.content.Context
import com.technoidentity.vitalz.data.network.VitalzApi
import com.technoidentity.vitalz.data.network.VitalzService
import com.technoidentity.vitalz.data.repository.MainRepository
import com.technoidentity.vitalz.data.repository.UserRepository
import com.technoidentity.vitalz.utils.CoroutinesDispatcherProvider
import com.technoidentity.vitalz.utils.CustomProgressDialog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesVitalzApi() : VitalzApi = VitalzService.getRestApi()!!

    @Singleton
    @Provides
    fun providesMainRepository(api: VitalzApi): MainRepository = UserRepository(api)

    @Singleton
    @Provides
    fun provideDispatcher(): CoroutinesDispatcherProvider = object : CoroutinesDispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }

    @Singleton
    @Provides
    fun providesCustomProgressBarDialog(@ApplicationContext context: Context) = CustomProgressDialog(context)
}
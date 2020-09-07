package com.matrix.applock.di.module

import android.app.Application
import android.content.Context
import com.matrix.applock.AppLockerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideContext(appLockerApplication: AppLockerApplication): Context =
        appLockerApplication.applicationContext

    @Provides
    @Singleton
    fun provideApplication(appLockerApplication: AppLockerApplication): Application =
        appLockerApplication
}
package com.matrix.applock.di.component

import com.matrix.applock.di.module.ActivityBuilderModule
import com.matrix.applock.di.module.AppModule
import com.matrix.applock.AppLockerApplication
import com.matrix.applock.data.database.DatabaseModule
import com.matrix.applock.di.module.BroadcastReceiverBuilderModule
import com.matrix.applock.di.module.ServiceBuilderModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        ServiceBuilderModule::class,
        BroadcastReceiverBuilderModule::class,
        AppModule::class,
        DatabaseModule::class]
)
interface AppComponent : AndroidInjector<AppLockerApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<AppLockerApplication>()
}
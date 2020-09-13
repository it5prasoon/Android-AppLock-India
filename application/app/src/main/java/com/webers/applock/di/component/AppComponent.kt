package com.webers.applock.di.component

import com.webers.applock.di.module.ActivityBuilderModule
import com.webers.applock.di.module.AppModule
import com.webers.applock.AppLockerApplication
import com.webers.applock.data.database.DatabaseModule
import com.webers.applock.di.module.BroadcastReceiverBuilderModule
import com.webers.applock.di.module.ServiceBuilderModule
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
package com.revolution.bookexpert.di.modules

import com.revolution.bookexpert.other.custom.annotations.GlobalNavigatorHolderQualifier
import com.revolution.bookexpert.other.custom.annotations.GlobalRouterQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigationModule {

    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Singleton
    @Provides
    fun provideCicerone(): Cicerone<Router> {
        return cicerone
    }

    @Singleton
    @GlobalRouterQualifier
    @Provides
    fun provideRouter(): Router {
        return cicerone.router
    }

    @Singleton
    @GlobalNavigatorHolderQualifier
    @Provides
    fun provideNavigatorHolder(): NavigatorHolder {
        return cicerone.navigatorHolder
    }
}
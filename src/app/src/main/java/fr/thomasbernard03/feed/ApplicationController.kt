package fr.thomasbernard03.feed

import android.app.Application
import fr.thomasbernard03.feed.commons.helpers.ResourceHelper
import fr.thomasbernard03.feed.commons.helpers.implementations.ResourcesHelperImpl
import fr.thomasbernard03.feed.commons.navigation.Navigator
import fr.thomasbernard03.feed.commons.navigation.implementations.NavigatorImpl
import fr.thomasbernard03.feed.domain.usecases.CartUseCase
import fr.thomasbernard03.feed.domain.usecases.MenuUseCase
import fr.thomasbernard03.feed.domain.usecases.ProductUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.dsl.module

class ApplicationController : Application() {
    private val appModule = module {
        single<Navigator> { NavigatorImpl() }

        single<ResourceHelper> { ResourcesHelperImpl() }

        single { MenuUseCase() }
        single { ProductUseCase() }
        single { CartUseCase() }

        // https://developer.android.com/kotlin/coroutines/coroutines-best-practices?hl=fr
        single<CoroutineDispatcher> { Dispatchers.IO }
    }

    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            modules(appModule)
            androidContext(this@ApplicationController)
        }
    }
}
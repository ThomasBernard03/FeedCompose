package fr.thomasbernard03.feed

import android.app.Application
import androidx.room.Room
import fr.thomasbernard03.feed.commons.helpers.ResourceHelper
import fr.thomasbernard03.feed.commons.helpers.implementations.ResourcesHelperImpl
import fr.thomasbernard03.feed.commons.navigation.Navigator
import fr.thomasbernard03.feed.commons.navigation.implementations.NavigatorImpl
import fr.thomasbernard03.feed.data.local.database.AppDatabase
import fr.thomasbernard03.feed.data.repositories.CartRepository
import fr.thomasbernard03.feed.data.repositories.ProductRepository
import fr.thomasbernard03.feed.data.repositories.implementations.CartRepositoryImpl
import fr.thomasbernard03.feed.data.repositories.implementations.ProductRepositoryImpl
import fr.thomasbernard03.feed.domain.usecases.ProductUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.dsl.module

class ApplicationController : Application() {

    private val database: AppDatabase by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "feed")
            .fallbackToDestructiveMigration() // If migrations needed delete all data and clear schema
            .build()
    }

    private val appModule = module {
        single<Navigator> { NavigatorImpl() }

        single<ResourceHelper> { ResourcesHelperImpl() }

        single { ProductUseCase() }

        single { database.productDao() }
        single { database.cartDao() }

        single<ProductRepository> { ProductRepositoryImpl()  }
        single<CartRepository> { CartRepositoryImpl()  }

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
package com.clutchit.loginapp77.di

import com.clutchit.loginapp77.data.repository.login.LoginRepository
import com.clutchit.loginapp77.data.repository.login.LoginRepositoryImpl
import com.clutchit.loginapp77.data.repository.register.RegisterRepository
import com.clutchit.loginapp77.data.repository.register.RegisterRepositoryImpl
import com.clutchit.loginapp77.data.repository.welcome.WelcomeRepository
import com.clutchit.loginapp77.data.repository.welcome.WelcomeRepositoryImpl
import com.clutchit.loginapp77.ui.viewmodel.LoginViewModel
import com.clutchit.loginapp77.ui.viewmodel.RegisterViewModel
import com.clutchit.loginapp77.ui.viewmodel.WelcomeViewModel
import com.clutchit.loginapp77.util.Constants.BASE_URL
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<LoginRepository> {
        get<Retrofit>().create(LoginRepository::class.java)
    }
    single<RegisterRepository> {
        get<Retrofit>().create(RegisterRepository::class.java)
    }
    single<WelcomeRepository> {
        get<Retrofit>().create(WelcomeRepository::class.java)
    }

    single { LoginRepositoryImpl(get()) }
    single { RegisterRepositoryImpl(get()) }
    single { WelcomeRepositoryImpl(get()) }

    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { WelcomeViewModel(get()) }
}
package com.clutchit.loginapp77.di

import com.clutchit.loginapp77.ui.viewmodel.LoginViewModel
import com.clutchit.loginapp77.ui.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel() }
    viewModel { RegisterViewModel() }
//    single { LoginRepository() }

}
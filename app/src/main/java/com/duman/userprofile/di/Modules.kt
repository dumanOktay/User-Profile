package com.duman.userprofile.di

import com.duman.userprofile.ui.screens.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
    viewModel { ProfileViewModel() }
}
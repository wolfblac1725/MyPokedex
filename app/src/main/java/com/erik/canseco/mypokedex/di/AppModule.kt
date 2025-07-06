package com.erik.canseco.mypokedex.di

import com.erik.canseco.mypokedex.BuildConfig
import com.erik.canseco.mypokedex.data.remote.PokemonApi
import com.erik.canseco.mypokedex.data.repository.PokemonRepositoryImp
import com.erik.canseco.mypokedex.domain.repository.PokemonRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providerDispatcherIO() = Dispatchers.IO

    @Provides
    @Singleton
    fun providerPokemon(): PokemonApi {
       return Retrofit.Builder()
           .baseUrl(BuildConfig.BASE_URL)
           .addConverterFactory(GsonConverterFactory.create())
           .build()
           .create(PokemonApi::class.java)
    }

    @Provides
    @Singleton
    fun providePokemonRepository(
        pokemonApi: PokemonApi
    ): PokemonRepository = PokemonRepositoryImp(pokemonApi)

}
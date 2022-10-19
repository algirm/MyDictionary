package com.algirm.mydictionary.feature_dictionary.di

import android.app.Application
import androidx.room.Room
import com.algirm.mydictionary.core.common.AppConst.Companion.BASE_URL
import com.algirm.mydictionary.core.util.JsonParserImpl
import com.algirm.mydictionary.feature_dictionary.data.local.Converters
import com.algirm.mydictionary.feature_dictionary.data.local.WordInfoDao
import com.algirm.mydictionary.feature_dictionary.data.local.WordInfoDatabase
import com.algirm.mydictionary.feature_dictionary.data.remote.DictionaryApi
import com.algirm.mydictionary.feature_dictionary.data.repository.WordInfoRepositoryImpl
import com.algirm.mydictionary.feature_dictionary.domain.model.WordInfo
import com.algirm.mydictionary.feature_dictionary.domain.repository.WordInfoRepository
import com.algirm.mydictionary.feature_dictionary.domain.use_case.GetWordInfo
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

//    @Singleton
//    @Provides
//    fun provideGetWordInfoUseCase(repository: WordInfoRepository): GetWordInfo {
//        return GetWordInfo(repository)
//    }

    @Singleton
    @Provides
    fun provideWordInfoRepository(
        db: WordInfoDatabase,
//        dao: WordInfoDao,
        api: DictionaryApi
    ): WordInfoRepository {
        return WordInfoRepositoryImpl(api, db.dao)
    }

//    @Singleton
//    @Provides
//    fun provideWordInfoDao(db: WordInfoDatabase): WordInfoDao {
//        return db.getWordInfoDao()
//    }

    @Singleton
    @Provides
    fun provideWordInfoDatabase(app: Application): WordInfoDatabase {
        return Room.databaseBuilder(
            app,
            WordInfoDatabase::class.java,
            "word_db"
        ).addTypeConverter(Converters(JsonParserImpl(Gson())))
            .build()
    }

    @Singleton
    @Provides
    fun provideDictionaryApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }

}
package com.algirm.mydictionary.feature_dictionary.data.repository

import com.algirm.mydictionary.core.common.Resource
import com.algirm.mydictionary.feature_dictionary.data.local.WordInfoDao
import com.algirm.mydictionary.feature_dictionary.data.remote.DictionaryApi
import com.algirm.mydictionary.feature_dictionary.domain.model.WordInfo
import com.algirm.mydictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class WordInfoRepositoryImpl @Inject constructor(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
) : WordInfoRepository {

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())
        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))
        try {
            val response = api.getWordInfo(word)
            dao.deleteWordInfos(response.body()?.map { it.word.toString() }?: emptyList())
            dao.insertWordInfos(response.body()?.map { it.toWordInfoEntity() }?: emptyList())
            if (response.isSuccessful) {
                val result = response.body()
//                dao.deleteWordInfos(result!!.map { it.word })
//                dao.insertWordInfos(result.map { it.toWordInfoEntity() })
//                result?.map { resultWordInfo ->
//                    resultWordInfo.word
//                }.let { words ->
//                    words?.let { dao.deleteWordInfos(it) }
//                }
//                result?.let { results ->
//                    dao.insertWordInfos(results.map { it.toWordInfoEntity() })
//                }
            } else {
                val errorMessage = response.message()
                val code = response.code()
                emit(Resource.Error(data = wordInfos, message = "$code: $errorMessage"))
            }
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Something Went Wrong.",
                    data = wordInfos
                )
            )
        } catch (e: IOException) {
            e.printStackTrace()
            emit(
                Resource.Error(
                    message = "Couldn't reach server, please check your connection.",
                    data = wordInfos
                )
            )
        } finally {
            val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
            emit(Resource.Success(data = newWordInfos))
        }
    }
}
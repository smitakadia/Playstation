package com.example.playstation.network

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build.HOST
import com.example.playstation.BuildConfig
import com.example.playstation.MyApplication.Companion.BASE_URL
import com.example.playstation.network.WebServiceAPI
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class WebServiceClient

/**
 * Private constructor for singleton purpose
 */
private constructor() {

    private var retrofit: Retrofit? = null

    /**
     * The API reference
     */
    private var service: WebServiceAPI? = null

    /**
     * @return OkHttpClient with log and custom header interceptors
     */
    var okHttpClient: OkHttpClient
        get() {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(customHeaderInterceptor())
            httpClient.addInterceptor(loggingInterceptor())
            httpClient.readTimeout(READ_TIME_OUT_SEC.toLong(), TimeUnit.SECONDS)
            httpClient.connectTimeout(CONNECT_TIME_OUT_SEC.toLong(), TimeUnit.SECONDS)
            return httpClient.build()
        }
        set(value) {}

    /**
     *
     * Internal helper and initializer
     *
     */
    private fun initRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        service = retrofit!!.create(WebServiceAPI::class.java)
    }

    /**
     * @return Interceptor with custom header
     */
    private fun customHeaderInterceptor(): Interceptor {

        return Interceptor { chain ->

            val original = chain.request()

            val request = original.newBuilder()
                /*.header(
                    HEADER_USERID,
                    CM.getSharedPreference(mContext, Constants.USER_ID, "") as String
                )
                .header(
                    HEADER_TOKEN,
                    CM.getSharedPreference(mContext, Constants.AUTH_TOKEN, "") as String
                )
                .header(
                    HEADER_ROLE,
                    CM.getSharedPreference(mContext, Constants.USER_ROLE, "") as String
                )*/
                .method(original.method, original.body)
                .build()

            chain.proceed(request)
        }
    }

    /**
     * @return Interceptor that provides logging
     */
    private fun loggingInterceptor(): Interceptor {

        val logging = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.NONE
        }

        return logging
    }

    companion object {

        private const val HEADER_TOKEN = "token"
        private const val HEADER_USERID = "id"
        private const val HEADER_ROLE = "role"
        private const val CONNECT_TIME_OUT_SEC = 60
        private const val READ_TIME_OUT_SEC = 60

        /**
         * Static Object reference
         */
        @SuppressLint("StaticFieldLeak")
        private var webServiceClient: WebServiceClient? = null

        @SuppressLint("StaticFieldLeak")
        private var mContext: Context? = null

        /**
         * java.lang.String * will init retrofit. needs to be called before using API. preferably from Application class
         *
         * @param context
         */
        fun init(context: Context) {

            if (webServiceClient == null) {
                webServiceClient = WebServiceClient()
                webServiceClient!!.initRetrofit()
                mContext = context
            }
        }

        val retrofitClient: Retrofit?
            get() = webServiceClient!!.retrofit

        /**
         * @return Web API
         */
        fun getService(): WebServiceAPI? {
            return webServiceClient!!.service
        }

        val client: Retrofit?
            get() = webServiceClient!!.retrofit
    }
}

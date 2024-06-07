package com.skogkatt.network.api

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
internal annotation class Api(
    val apiType: ApiType,
)

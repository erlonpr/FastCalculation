package com.github.erlonpr.fastcalculation

import android.os.Parcelable
//import kotlinx.android.parcel.Parcelize   --> import OBSOLETO
import kotlinx.parcelize.Parcelize

// anotação @Parcelize é usada para gerar uma implementação da interface Parcelable para uma classe Kotlin que permite que a classe seja serializada e desserializada, o que é útil para passar dados entre atividades e fragmentos em um aplicativo Android

@Parcelize
data class Settings(
    val playerName: String = "",
    val rounds: Int = 0,
    val roundInterval: Long = 0L
) : Parcelable

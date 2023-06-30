package com.dello.genshinartifactinfo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Artifact(
    val name: String?,
    val description: String?,
    val photo: Int?
) : Parcelable

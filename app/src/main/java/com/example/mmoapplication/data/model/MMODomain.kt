package com.example.mmoapplication.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MMODomain(
    var title: String,
    var genre: String,
    var platform: String,
    var shortDescription: String,
    var thumbnail: String,
    var website: String
) : Parcelable
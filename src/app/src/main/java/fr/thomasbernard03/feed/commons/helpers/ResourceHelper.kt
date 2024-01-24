package fr.thomasbernard03.feed.commons.helpers

import androidx.annotation.StringRes

interface ResourceHelper {
    fun getString(@StringRes stringId: Int, formatArgs: Any? = null) : String
}
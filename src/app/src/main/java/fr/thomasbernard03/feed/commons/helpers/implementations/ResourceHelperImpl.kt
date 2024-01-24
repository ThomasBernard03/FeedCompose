package fr.thomasbernard03.feed.commons.helpers.implementations

import android.content.Context
import androidx.annotation.StringRes
import fr.thomasbernard03.feed.commons.helpers.ResourceHelper
import org.koin.java.KoinJavaComponent.get

class ResourcesHelperImpl(
    private val context: Context = get(Context::class.java),
) : ResourceHelper {
    override fun getString(@StringRes stringId: Int, formatArgs: Any?)
            = context.getString(stringId, formatArgs)
}
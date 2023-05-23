package com.ankurjb.aisle.utils

import android.os.Bundle
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination

fun NavController.navigateTo(
    route: String,
    args: Bundle = bundleOf()
) {
    val routeLink = NavDeepLinkRequest
        .Builder
        .fromUri(NavDestination.createRoute(route).toUri())
        .build()

    val deepLinkMatch = graph.matchDeepLink(routeLink)
    if (deepLinkMatch != null) {
        val id = deepLinkMatch.destination.id
        navigate(id, args, null, null)
    } else {
        navigate(route, null, null)
    }
}

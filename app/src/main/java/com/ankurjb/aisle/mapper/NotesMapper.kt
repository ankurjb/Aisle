package com.ankurjb.aisle.mapper

import com.ankurjb.aisle.features.notes.model.HeroProfile
import com.ankurjb.aisle.features.notes.model.InterestedProfiles
import com.ankurjb.aisle.model.Profile
import javax.inject.Inject

class NotesMapper @Inject constructor() {

    fun toNotesHeroProfile(profile: Profile) = with(profile.invites.profiles.firstOrNull()) {
        HeroProfile(
            imageUrl = this?.photos?.firstOrNull()?.photo,
            nameAndAge = "${this?.generalInformation?.firstName.orEmpty()}, ${this?.generalInformation?.age ?: ""}"
        )
    }

    fun toInterestedProfiles(profile: Profile) = profile.likes.profiles.map {
        InterestedProfiles(
            name = it.firstName,
            imageUrl = it.avatar,
            isProfileVisible = profile.likes.canSeeProfile
        )
    }
}

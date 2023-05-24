package com.ankurjb.aisle.model

import com.google.gson.annotations.SerializedName

data class Profile(
    @SerializedName("invites")
    val invites: Invites,
    @SerializedName("likes")
    val likes: Likes
) {

    data class Invites(
        @SerializedName("pending_invitations_count")
        val pendingInvitationsCount: Int,
        @SerializedName("profiles")
        val profiles: List<Profile>,
        @SerializedName("totalPages")
        val totalPages: Int
    ) {

        data class Profile(
            @SerializedName("approved_time")
            val approvedTime: Double,
            @SerializedName("disapproved_time")
            val disapprovedTime: Double,
            @SerializedName("general_information")
            val generalInformation: GeneralInformation,
            @SerializedName("has_active_subscription")
            val hasActiveSubscription: Boolean,
            @SerializedName("icebreakers")
            val icebreakers: Any,
            @SerializedName("instagram_images")
            val instagramImages: Any,
            @SerializedName("is_facebook_data_fetched")
            val isFacebookDataFetched: Boolean,
            @SerializedName("last_seen")
            val lastSeen: Any,
            @SerializedName("last_seen_window")
            val lastSeenWindow: String,
            @SerializedName("lat")
            val lat: Double,
            @SerializedName("lng")
            val lng: Double,
            @SerializedName("meetup")
            val meetup: Any,
            @SerializedName("online_code")
            val onlineCode: Int,
            @SerializedName("photos")
            val photos: List<Photo>,
            @SerializedName("preferences")
            val preferences: List<Preference>,
            @SerializedName("profile_data_list")
            val profileDataList: List<ProfileData>,
            @SerializedName("show_concierge_badge")
            val showConciergeBadge: Boolean,
            @SerializedName("story")
            val story: Any,
            @SerializedName("user_interests")
            val userInterests: List<Any>,
            @SerializedName("verification_status")
            val verificationStatus: String,
            @SerializedName("work")
            val work: Work
        ) {

            data class GeneralInformation(
                @SerializedName("age")
                val age: Int,
                @SerializedName("cast")
                val cast: Any,
                @SerializedName("date_of_birth")
                val dateOfBirth: String,
                @SerializedName("date_of_birth_v1")
                val dateOfBirthV1: String,
                @SerializedName("diet")
                val diet: Any,
                @SerializedName("drinking_v1")
                val drinkingV1: DrinkingV1,
                @SerializedName("faith")
                val faith: Faith,
                @SerializedName("first_name")
                val firstName: String,
                @SerializedName("gender")
                val gender: String,
                @SerializedName("height")
                val height: Int,
                @SerializedName("kid")
                val kid: Any,
                @SerializedName("location")
                val location: Location,
                @SerializedName("marital_status_v1")
                val maritalStatusV1: MaritalStatusV1,
                @SerializedName("mbti")
                val mbti: Any,
                @SerializedName("mother_tongue")
                val motherTongue: MotherTongue,
                @SerializedName("pet")
                val pet: Any,
                @SerializedName("politics")
                val politics: Any,
                @SerializedName("ref_id")
                val refId: String,
                @SerializedName("settle")
                val settle: Any,
                @SerializedName("smoking_v1")
                val smokingV1: SmokingV1,
                @SerializedName("sun_sign_v1")
                val sunSignV1: SunSignV1
            ) {

                data class DrinkingV1(
                    @SerializedName("id")
                    val id: Int,
                    @SerializedName("name")
                    val name: String,
                    @SerializedName("name_alias")
                    val nameAlias: String
                )


                data class Faith(
                    @SerializedName("id")
                    val id: Int,
                    @SerializedName("name")
                    val name: String
                )


                data class Location(
                    @SerializedName("full")
                    val full: String,
                    @SerializedName("summary")
                    val summary: String
                )


                data class MaritalStatusV1(
                    @SerializedName("id")
                    val id: Int,
                    @SerializedName("name")
                    val name: String,
                    @SerializedName("preference_only")
                    val preferenceOnly: Boolean
                )


                data class MotherTongue(
                    @SerializedName("id")
                    val id: Int,
                    @SerializedName("name")
                    val name: String
                )


                data class SmokingV1(
                    @SerializedName("id")
                    val id: Int,
                    @SerializedName("name")
                    val name: String,
                    @SerializedName("name_alias")
                    val nameAlias: String
                )


                data class SunSignV1(
                    @SerializedName("id")
                    val id: Int,
                    @SerializedName("name")
                    val name: String
                )
            }


            data class Photo(
                @SerializedName("photo")
                val photo: String,
                @SerializedName("photo_id")
                val photoId: Int,
                @SerializedName("selected")
                val selected: Boolean,
                @SerializedName("status")
                val status: String
            )


            data class Preference(
                @SerializedName("answer_id")
                val answerId: Int,
                @SerializedName("id")
                val id: Int,
                @SerializedName("preference_question")
                val preferenceQuestion: PreferenceQuestion,
                @SerializedName("value")
                val value: Int
            ) {

                data class PreferenceQuestion(
                    @SerializedName("first_choice")
                    val firstChoice: String,
                    @SerializedName("second_choice")
                    val secondChoice: String
                )
            }


            data class ProfileData(
                @SerializedName("invitation_type")
                val invitationType: String,
                @SerializedName("preferences")
                val preferences: List<Preference>,
                @SerializedName("question")
                val question: String
            ) {

                data class Preference(
                    @SerializedName("answer")
                    val answer: String,
                    @SerializedName("answer_id")
                    val answerId: Int,
                    @SerializedName("first_choice")
                    val firstChoice: String,
                    @SerializedName("second_choice")
                    val secondChoice: String
                )
            }


            data class Work(
                @SerializedName("experience_v1")
                val experienceV1: ExperienceV1,
                @SerializedName("field_of_study_v1")
                val fieldOfStudyV1: FieldOfStudyV1,
                @SerializedName("highest_qualification_v1")
                val highestQualificationV1: HighestQualificationV1,
                @SerializedName("industry_v1")
                val industryV1: IndustryV1,
                @SerializedName("monthly_income_v1")
                val monthlyIncomeV1: Any
            ) {

                data class ExperienceV1(
                    @SerializedName("id")
                    val id: Int,
                    @SerializedName("name")
                    val name: String,
                    @SerializedName("name_alias")
                    val nameAlias: String
                )


                data class FieldOfStudyV1(
                    @SerializedName("id")
                    val id: Int,
                    @SerializedName("name")
                    val name: String
                )


                data class HighestQualificationV1(
                    @SerializedName("id")
                    val id: Int,
                    @SerializedName("name")
                    val name: String,
                    @SerializedName("preference_only")
                    val preferenceOnly: Boolean
                )


                data class IndustryV1(
                    @SerializedName("id")
                    val id: Int,
                    @SerializedName("name")
                    val name: String,
                    @SerializedName("preference_only")
                    val preferenceOnly: Boolean
                )
            }
        }
    }


    data class Likes(
        @SerializedName("can_see_profile")
        val canSeeProfile: Boolean,
        @SerializedName("likes_received_count")
        val likesReceivedCount: Int,
        @SerializedName("profiles")
        val profiles: List<Profile>
    ) {

        data class Profile(
            @SerializedName("avatar")
            val avatar: String,
            @SerializedName("first_name")
            val firstName: String
        )
    }
}

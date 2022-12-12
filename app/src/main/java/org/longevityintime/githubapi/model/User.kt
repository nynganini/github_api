package org.longevityintime.githubapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.longevityintime.githubapi.database.model.UserEntity

@Serializable
data class User(
    @SerialName("id") val id: Long,
    @SerialName("login") val login: String,
//    @SerialName("node_id") val nodeId: String,
    @SerialName("avatar_url") val avatarUrl: String,
//    @SerialName("gravatar_id") val gravatarId: String,
//    @SerialName("url") val url: String,
//    @SerialName("html_url") val htmlUrl: String,
//    @SerialName("followers_url") val followersUrl: String,
//    @SerialName("following_url") val followingUrl: String,
//    @SerialName("gist_url") val gistUrl: String,
//    @SerialName("starred_url") val starredUrl: String,
//    @SerialName("subscriptions_url") val subscriptionsUrl: String,
//    @SerialName("organizations_url") val organizationsUrl: String,
//    @SerialName("repos_url") val reposUrl: String,
//    @SerialName("events_url") val eventsUrl: String,
//    @SerialName("received_events_url") val receivedEventsUrl: String,
//    @SerialName("type") val type: String,
//    @SerialName("site_admin") val siteAdmin: Boolean,
)

fun User.asEntity(): UserEntity = UserEntity(id, login, avatarUrl)
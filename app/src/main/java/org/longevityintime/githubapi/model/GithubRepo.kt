package org.longevityintime.githubapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class GithubRepo(
    @SerialName("id") val id: Long,
//    @SerialName("node_id") val nodeId: String,
    @SerialName("name") val name: String,
//    @SerialName("full_name") val fullName: String,
//    @SerialName("private") val private: Boolean,
//    @SerialName("owner") val owner: User,
    @SerialName("html_url") val htmlUrl: String,
//    @SerialName("description") val description: String,
//    @SerialName("fork") val fork: Boolean,
//    @SerialName("url") val url: String,

    @SerialName("updated_at") @Serializable(DateSerializer::class)  val updatedAt: Date,
    @SerialName("stargazers_count") val stargazersCount: Long,
    @SerialName("language") val language: String?,
)
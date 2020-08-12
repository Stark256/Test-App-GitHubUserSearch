package com.features.test_app_githubusersearch.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.io.Serializable


@JsonPropertyOrder(
    "login", "id", "node_id", "avatar_url", "gravatar_id", "url", "html_url",
    "followers_url", "starred_url", "gists_url", "following_url", "subscriptions_url",
    "organizations_url", "repos_url", "received_events_url", "site_admin", "name",
    "company", "blog", "location", "email", "hireable", "bio", "twitter_username",
    "public_repos", "public_gists", "followers", "following", "created_at", "updated_at", "type")
class UserDataModel(
    @JsonProperty("login") val login: String,
    @JsonProperty("id") val id: Long,
    @JsonProperty("node_id") val node_id: String,
    @JsonProperty("avatar_url") val avatar_url: String,
    @JsonProperty("gravatar_id") val gravatar_id: String,
    @JsonProperty("url") val url: String,
    @JsonProperty("html_url") val html_url: String,
    @JsonProperty("followers_url") val followers_url: String,
    @JsonProperty("following_url") val following_url: String,
    @JsonProperty("gists_url") val gists_url: String,
    @JsonProperty("starred_url") val starred_url: String,
    @JsonProperty("subscriptions_url") val subscriptions_url: String,
    @JsonProperty("organizations_url") val organizations_url: String,
    @JsonProperty("repos_url") val repos_url: String,
    @JsonProperty("events_url") val events_url: String,
    @JsonProperty("received_events_url") val received_events_url: String,
    @JsonProperty("type") val type: String,
    @JsonProperty("site_admin") val site_admin: Boolean,
    @JsonProperty("name") val name: String,
    @JsonProperty("company") val company: String?,
    @JsonProperty("blog") val blog: String?,
    @JsonProperty("location") val location: String?,
    @JsonProperty("email") val email: String?,
    @JsonProperty("hireable") val hireable: String?,
    @JsonProperty("bio") val bio: String?,
    @JsonProperty("twitter_username") val twitter_username: String?,
    @JsonProperty("public_repos") val public_repos: Int,
    @JsonProperty("public_gists") val public_gists: Int,
    @JsonProperty("followers") val followers: Int,
    @JsonProperty("following") val following: Int,
    @JsonProperty("created_at") val created_at: String,
    @JsonProperty("updated_at") val updated_at: String
): Serializable

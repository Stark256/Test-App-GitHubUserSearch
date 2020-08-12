package com.features.test_app_githubusersearch.api.models

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonPropertyOrder(
    "login",
    "id",
    "node_id",
    "avatar_url",
    "gravatar_id",
    "url",
    "html_url",
    "followers_url",
    "following_url",
    "gists_url",
    "starred_url",
    "subscriptions_url",
    "organizations_url",
    "repos_url",
    "events_url",
    "site_admin",
    "received_events_url",
    "type",
    "score")
class UserModel(
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
    @JsonProperty("received_events_url") val received_events_url: String,
    @JsonProperty("events_url") val events_url: String,
    @JsonProperty("type") val type: String,
    @JsonProperty("site_admin") val site_admin: Boolean,
    @JsonProperty("score") val score: Double
)

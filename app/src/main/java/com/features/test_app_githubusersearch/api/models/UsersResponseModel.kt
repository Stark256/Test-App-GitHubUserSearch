package com.features.test_app_githubusersearch.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonPropertyOrder(
    "total_count",
    "incomplete_results",
    "items")
class UsersResponseModel(
    @JsonProperty("total_count") val total_count: Int,
    @JsonProperty("incomplete_results") val incomplete_results: Boolean,
    @JsonProperty("items") val items: ArrayList<UserModel>
)
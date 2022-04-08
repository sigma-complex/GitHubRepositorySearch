package com.hiddendimension.githubrepositorysearch.feature_github_repository_search.presentation.search_repository

import com.google.common.truth.Truth.assertThat
import com.hiddendimension.githubrepositorysearch.feature_github_repository_search.presentation.search_repository.repositories.FakeGitHubRepository
import org.junit.Test

class SearchUtilTest {

    var fakeRepo = FakeGitHubRepository()

    @Test
    fun `empty search returns false`() {
        val result = SearchUtil.validateSearch(
            ""
        )
        assertThat(result).isFalse()
    }


    @Test
    fun `valid search input`() {
        val result = SearchUtil.validateSearch(
            "kot"
        )
        assertThat(result).isTrue()
    }


    @Test
    fun `search in git repo returns success`() {
        val result = fakeRepo.getDataFromAPI("w")

        assertThat(result.status.name).isEqualTo("SUCCESS")
    }


    @Test
    fun `search in git repo returns at least 0 results`() {
        val result = fakeRepo.getDataFromAPI("w")

        assertThat(result.data?.size).isAtLeast(0)
    }


    @Test
    fun `search in git repo returns at least 10 results`() {
        val result = fakeRepo.getDataFromAPI("w")

        assertThat(result.data?.size).isAtLeast(10)
    }

    @Test
    fun `search in git repo returns results containing search query`() {

        val query = "alif"
        val result = fakeRepo.getDataFromAPI(query)

        if (result.data!!.size > 0)
            for (x in result.data) {
                assertThat(
                    x.name?.contains(query) == true
                            || x.description?.contains(query) == true
                ).isTrue()

            }

        assertThat(result.data!!.size).isAtLeast(1)

    }




}

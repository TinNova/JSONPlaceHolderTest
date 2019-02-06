package com.novakovic.tin.nutmegtest

import com.novakovic.tin.nutmegtest.network.ApiMethods
import com.novakovic.tin.nutmegtest.repo.ContentRepo
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import junit.framework.Assert.assertEquals
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@RunWith(MockitoJUnitRunner::class)
class ContentRepoUnitTest {

    @Inject
    private lateinit var contentRepo: ContentRepo
    @Inject
    lateinit var apiMethods: ApiMethods

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    private fun setupRepoWithMockedResponses() {
        apiMethods = Mockito.mock(ApiMethods::class.java)
        Mockito.`when`(apiMethods.getPosts()).thenReturn(Single.just(testApiPostResponse))
        Mockito.`when`(apiMethods.getUsers()).thenReturn(Single.just(testApiUserResponse))

        contentRepo = ContentRepo(apiMethods)
    }

    @Test
    fun `test that ContentRepo getPosts() is observed`() {
        setupRepoWithMockedResponses()

        val result = contentRepo.getPosts()

        result.test().apply {
            awaitTerminalEvent(1, TimeUnit.SECONDS)
            assertSubscribed()
            assertNoTimeout()
            assertNoErrors()
        }
    }

    @Test
    fun `test that ContentRepo getUsers() is observed`() {
        setupRepoWithMockedResponses()

        val result = contentRepo.getUsers()

        result.test().apply {
            awaitTerminalEvent(1, TimeUnit.SECONDS)
            assertSubscribed()
            assertNoTimeout()
            assertNoErrors()
        }
    }

    @Test
    fun `test that ContentRepo getSanatisedPosts() converts Posts & Users data into a sanatised Posts model`() {
        setupRepoWithMockedResponses()

        val result = contentRepo.getSanatisedPosts()

        val testObserver = result.test()

        val response = testObserver.values().first()
        assertEquals(response, testSanatisedPostResponse)
    }
}

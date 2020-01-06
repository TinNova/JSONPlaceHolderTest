package com.novakovic.tin.nutmegtest

import com.novakovic.tin.nutmegtest.network.ApiInterface
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

@RunWith(MockitoJUnitRunner::class)
class ContentRepoUnitTest {

    private lateinit var contentRepo: ContentRepo

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    private fun setupRepoWithMockedResponses() {
        val apiInterface: ApiInterface = Mockito.mock(ApiInterface::class.java)
        Mockito.`when`(apiInterface.getPosts()).thenReturn(Single.just(testApiPostResponse))
        Mockito.`when`(apiInterface.getUsers()).thenReturn(Single.just(testApiUserResponse))


        contentRepo = ContentRepo(apiInterface)
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

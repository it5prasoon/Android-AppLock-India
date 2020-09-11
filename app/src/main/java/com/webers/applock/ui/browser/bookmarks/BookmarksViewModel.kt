package com.webers.applock.ui.browser.bookmarks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.webers.applock.data.database.bookmark.BookmarkDao
import com.webers.applock.data.database.bookmark.BookmarkEntity
import com.webers.applock.ui.RxAwareViewModel
import com.webers.applock.util.extensions.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BookmarksViewModel @Inject constructor(bookmarkDao: BookmarkDao) : RxAwareViewModel() {

    private val bookmarkListLiveData = MutableLiveData<List<BookmarkEntity>>()

    init {
        disposables += bookmarkDao.getBookmarks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                bookmarkListLiveData.value = it
            }
    }

    fun getBookmarksLiveData(): LiveData<List<BookmarkEntity>> = bookmarkListLiveData

}
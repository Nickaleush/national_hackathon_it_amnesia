package com.example.national_hackathon_it_amnesia.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenterImpl<V : BaseView> : BasePresenter {

    private var disposables: CompositeDisposable = CompositeDisposable()

    operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun stop(): Unit = disposables.clear()

    override fun dispose(): Unit = disposables.dispose()
}

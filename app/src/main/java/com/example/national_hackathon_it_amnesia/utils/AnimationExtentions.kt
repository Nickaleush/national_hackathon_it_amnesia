package com.example.national_hackathon_it_amnesia.utils

import android.view.View
import androidx.core.view.ViewCompat
import io.reactivex.Completable
import io.reactivex.subjects.CompletableSubject

 fun View.fadeIn(duration: Long): Completable {
    val animationSubject = CompletableSubject.create()
    return animationSubject.doOnSubscribe {
        ViewCompat.animate(this)
            .setDuration(duration)
            .alpha(1f)
            .withEndAction {
                animationSubject.onComplete()
            }
    }
}

 fun View.fadeOut(duration: Long): Completable {
    val animationSubject = CompletableSubject.create()
    return animationSubject.doOnSubscribe {
        ViewCompat.animate(this)
            .setDuration(duration)
            .alpha(0f)
            .withEndAction {
                animationSubject.onComplete()
            }
    }
}
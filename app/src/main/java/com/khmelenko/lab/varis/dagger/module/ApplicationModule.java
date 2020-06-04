package com.khmelenko.lab.varis.dagger.module;

import android.content.Context;
import com.khmelenko.lab.varis.TravisApp;
import com.khmelenko.lab.varis.mvp.MvpPresenter;
import com.khmelenko.lab.varis.util.PresenterKeeper;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Application module
 *
 * @author Dmytro Khmelenko (d.khmelenko@gmail.com)
 */
@Module
public abstract class ApplicationModule {

@Provides
@Singleton
public static PresenterKeeper<MvpPresenter> providePresenterKeeper() {
	return new PresenterKeeper<>();
}

@Provides
@Singleton
public static Context provideAppContext() {
	return TravisApp.getAppContext();
}
}

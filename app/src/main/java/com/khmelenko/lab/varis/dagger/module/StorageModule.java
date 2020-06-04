package com.khmelenko.lab.varis.dagger.module;

import android.content.Context;
import com.khmelenko.lab.varis.storage.AppSettings;
import com.khmelenko.lab.varis.storage.CacheStorage;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Storage module
 *
 * @author Dmytro Khmelenko (d.khmelenko@gmail.com)
 */
@Module
public abstract class StorageModule {

@Provides
@Singleton
public static CacheStorage provideCache() {
	return CacheStorage.newInstance();
}

@Provides
@Singleton
public static AppSettings provideAppSettings(final Context context) {
	return new AppSettings(context);
}
}

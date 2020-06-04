package com.khmelenko.lab.varis.dagger;

/**
 *
 */

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import com.khmelenko.lab.varis.storage.AppSettings;
import com.khmelenko.lab.varis.storage.CacheStorage;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Storage module for testing
 *
 * @author Dmytro Khmelenko (d.khmelenko@gmail.com)
 */
@Module
public class TestStorageModule {

  @Provides
  @Singleton
  public CacheStorage provideCache() {
    return mock(CacheStorage.class);
  }

  @Provides
  @Singleton
  public AppSettings provideAppSettings() {
    return mock(AppSettings.class);
  }
}

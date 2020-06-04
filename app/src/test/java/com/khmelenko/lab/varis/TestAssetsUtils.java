package com.khmelenko.lab.varis;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import com.khmelenko.lab.varis.util.AssetsUtils;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Testing AssetsUtils class
 *
 * @author Dmytro Khmelenko (d.khmelenko@gmail.com)
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class TestAssetsUtils {

  @Test
  public void testGetProperties() throws IOException {
    final String property = "test";

    Context context = mock(Context.class);
    Resources resources = mock(Resources.class);
    AssetManager assetManager = mock(AssetManager.class);
    InputStream is = mock(InputStream.class);
    when(context.getResources()).thenReturn(resources);
    when(resources.getAssets()).thenReturn(assetManager);
    when(assetManager.open(property)).thenReturn(is);

    AssetsUtils.getProperties(property, context);

    verify(context).getResources();
    verify(resources).getAssets();
    verify(assetManager).open(property);
  }
}

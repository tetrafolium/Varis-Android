package com.khmelenko.lab.varis;
import com.khmelenko.lab.varis.util.EncryptionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import static junit.framework.Assert.assertEquals; /**
                                                    * Test for enctyption utils
                                                    *
                                                    * @author Dmytro Khmelenko (d.khmelenko@gmail.com)
                                                    */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class TestEncryptionUtils {
@Test
public void testToBase64() {
	String original = "Hello world";
	String expected = "SGVsbG8gd29ybGQ=";
	String actual = EncryptionUtils.toBase64(original);
	assertEquals(expected, actual);
}
@Test
public void testFromBase64() {
	String original = "SGVsbG8gd29ybGQ=";
	String expected = "Hello world";
	String actual = EncryptionUtils.fromBase64(original);
	assertEquals(expected, actual);
}
@Test
public void testBasicAuth() {
	String username = "username";
	String password = "password";
	String authBase64 = "dXNlcm5hbWU6cGFzc3dvcmQ=";
	String expected = "Basic " + authBase64;
	String actual =
		EncryptionUtils.generateBasicAuthorization(username, password);
	assertEquals(expected, actual);
}
}
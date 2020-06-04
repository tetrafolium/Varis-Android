package com.khmelenko.lab.varis.network.response;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Authorization response
 *
 * @author Dmytro Khmelenko
 */
public final class Authorization {

@SerializedName("id") private long mId;

@SerializedName("url") private String mUrl;

@SerializedName("scopes") private List<String> mScopes;

@SerializedName("token") private String mToken;

@SerializedName("token_last_eight") private String mTokenLastEight;

@SerializedName("hashed_token") private String mHashedToken;

@SerializedName("app") private App mApp;

@SerializedName("note") private String mNote;

@SerializedName("note_url") private String mNoteUrl;

@SerializedName("updated_at") private String mUpdatedAt;

@SerializedName("created_at") private String mCreatedAt;

@SerializedName("fingerprint") private String mFingerprint;

public long getId() {
	return mId;
}

public void setId(final long id) {
	mId = id;
}

public String getUrl() {
	return mUrl;
}

public void setUrl(final String url) {
	mUrl = url;
}

public List<String> getScopes() {
	return mScopes;
}

public void setScopes(final List<String> scopes) {
	mScopes = scopes;
}

public String getToken() {
	return mToken;
}

public void setToken(final String token) {
	mToken = token;
}

public String getTokenLastEight() {
	return mTokenLastEight;
}

public void setTokenLastEight(final String tokenLastEight) {
	mTokenLastEight = tokenLastEight;
}

public String getHashedToken() {
	return mHashedToken;
}

public void setHashedToken(final String hashedToken) {
	mHashedToken = hashedToken;
}

public App getApp() {
	return mApp;
}

public void setApp(final App app) {
	mApp = app;
}

public String getNote() {
	return mNote;
}

public void setNote(final String note) {
	mNote = note;
}

public String getNoteUrl() {
	return mNoteUrl;
}

public void setNoteUrl(final String noteUrl) {
	mNoteUrl = noteUrl;
}

public String getUpdatedAt() {
	return mUpdatedAt;
}

public void setUpdatedAt(final String updatedAt) {
	mUpdatedAt = updatedAt;
}

public String getCreatedAt() {
	return mCreatedAt;
}

public void setCreatedAt(final String createdAt) {
	mCreatedAt = createdAt;
}

public String getFingerprint() {
	return mFingerprint;
}

public void setFingerprint(final String fingerprint) {
	mFingerprint = fingerprint;
}

public class App {

@SerializedName("url") private String mUrl;

@SerializedName("name") private String mName;

@SerializedName("client_id") private String mClientId;

public String getUrl() {
	return mUrl;
}

public void setUrl(final String url) {
	mUrl = url;
}

public String getName() {
	return mName;
}

public void setName(final String name) {
	mName = name;
}

public String getClientId() {
	return mClientId;
}

public void setClientId(final String clientId) {
	mClientId = clientId;
}
}
}

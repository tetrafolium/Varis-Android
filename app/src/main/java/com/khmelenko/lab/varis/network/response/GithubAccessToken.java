package com.khmelenko.lab.varis.network.response;

import com.google.gson.annotations.SerializedName;

/**
 * DAO for Access token from Github
 *
 * @author Dmytro Khmelenko
 */
public final class GithubAccessToken {

  @SerializedName("access_token") private String mAccessToken;

  @SerializedName("scope") private String mScope;

  @SerializedName("token_type") private String mTokenType;

  public String getAccessToken() { return mAccessToken; }

  public void setAccessToken(final String accessToken) {
    mAccessToken = accessToken;
  }

  public String getScope() { return mScope; }

  public void setScope(final String scope) { mScope = scope; }

  public String getTokenType() { return mTokenType; }

  public void setTokenType(final String tokenType) { mTokenType = tokenType; }
}

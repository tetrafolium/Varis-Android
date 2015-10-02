package com.khmelenko.lab.travisclient.task.github;

import android.text.TextUtils;

import com.khmelenko.lab.travisclient.event.github.CreateAuthorizationSuccessEvent;
import com.khmelenko.lab.travisclient.event.github.GithubAuthorizationFailEvent;
import com.khmelenko.lab.travisclient.network.request.AuthorizationRequest;
import com.khmelenko.lab.travisclient.network.response.Authorization;
import com.khmelenko.lab.travisclient.network.retrofit.GithubApiService;
import com.khmelenko.lab.travisclient.task.Task;
import com.khmelenko.lab.travisclient.task.TaskError;
import com.khmelenko.lab.travisclient.task.TaskException;

import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;

/**
 * The task for creation new authorization
 *
 * @author Dmytro Khmelenko
 */
public final class CreateAuthorizationTask extends Task<Authorization> {

    private final String mBasicAuth;
    private final AuthorizationRequest mAuthorizationRequest;
    private String mTwoFactorCode;

    public CreateAuthorizationTask(String basicAuth, AuthorizationRequest authorizationRequest) {
        mBasicAuth = basicAuth;
        mAuthorizationRequest = authorizationRequest;
    }

    public CreateAuthorizationTask(String basicAuth, AuthorizationRequest authorizationRequest, String twoFactorCode) {
        mBasicAuth = basicAuth;
        mAuthorizationRequest = authorizationRequest;
        mTwoFactorCode = twoFactorCode;
    }

    @Override
    public Authorization execute() throws TaskException {

        Authorization authorization;
        try {
            if (!TextUtils.isEmpty(mTwoFactorCode)) {
                authorization = mRestClient.getGithubApiService().createNewAuthorization(mBasicAuth,
                        mTwoFactorCode, mAuthorizationRequest);
            } else {
                authorization = mRestClient.getGithubApiService()
                        .createNewAuthorization(mBasicAuth, mAuthorizationRequest);
            }
        } catch (RetrofitError error) {

            if(!error.isNetworkError()) {
                Response response = error.getResponse();
                boolean twoFactorAuthRequired = false;
                for (Header header : response.getHeaders()) {
                    if (header.getName().equals(GithubApiService.TWO_FACTOR_HEADER)) {
                        twoFactorAuthRequired = true;
                        break;
                    }
                }
                if(response.getStatus() == 401 && twoFactorAuthRequired) {
                    TaskError taskError = new TaskError(TaskError.TWO_FACTOR_AUTH_REQUIRED, "");
                    throw new TaskException(taskError);
                } else {
                    throw error;
                }
            } else {
                throw error;
            }
        }

        return authorization;
    }

    @Override
    public void onSuccess(Authorization result) {
        CreateAuthorizationSuccessEvent event = new CreateAuthorizationSuccessEvent(result);
        mEventBus.post(event);
    }

    @Override
    public void onFail(TaskError error) {
        GithubAuthorizationFailEvent event = new GithubAuthorizationFailEvent(error);
        mEventBus.post(event);
    }
}

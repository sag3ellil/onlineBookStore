/*
 * E-Book Store API
 * API for managing e-books, orders, and user authentication in an e-book store.
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.example.api;



import com.google.gson.reflect.TypeToken;
import org.example.entity.User;
import org.example.model.UserRequest;
import org.example.util.*;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public AuthApi() {
        this(Configuration.getDefaultApiClient());
    }

    public AuthApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public int getHostIndex() {
        return localHostIndex;
    }

    public void setHostIndex(int hostIndex) {
        this.localHostIndex = hostIndex;
    }

    public String getCustomBaseUrl() {
        return localCustomBaseUrl;
    }

    public void setCustomBaseUrl(String customBaseUrl) {
        this.localCustomBaseUrl = customBaseUrl;
    }

    /**
     * Build call for loginUser
     * @param userRequest Login credentials (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Login successful </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call loginUserCall(UserRequest userRequest, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = userRequest;

        // create path and map variables
        String localVarPath = "/auth/login";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call loginUserValidateBeforeCall(UserRequest userRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'userRequest' is set
        if (userRequest == null) {
            throw new ApiException("Missing the required parameter 'userRequest' when calling loginUser(Async)");
        }

        return loginUserCall(userRequest, _callback);

    }

    /**
     * User login
     * 
     * @param userRequest Login credentials (required)
     * @return User
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Login successful </td><td>  -  </td></tr>
     </table>
     */
    public User loginUser(UserRequest userRequest) throws ApiException {
        ApiResponse<User> localVarResp = loginUserWithHttpInfo(userRequest);
        return localVarResp.getData();
    }

    /**
     * User login
     * 
     * @param userRequest Login credentials (required)
     * @return ApiResponse&lt;User&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Login successful </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<User> loginUserWithHttpInfo(UserRequest userRequest) throws ApiException {
        okhttp3.Call localVarCall = loginUserValidateBeforeCall(userRequest, null);
        Type localVarReturnType = new TypeToken<User>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * User login (asynchronously)
     * 
     * @param userRequest Login credentials (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Login successful </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call loginUserAsync(UserRequest userRequest, final ApiCallback<User> _callback) throws ApiException {

        okhttp3.Call localVarCall = loginUserValidateBeforeCall(userRequest, _callback);
        Type localVarReturnType = new TypeToken<User>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for registerUser
     * @param userRequest User registration data (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> User registered successfully </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call registerUserCall(UserRequest userRequest, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = userRequest;

        // create path and map variables
        String localVarPath = "/auth/register";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call registerUserValidateBeforeCall(UserRequest userRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'userRequest' is set
        if (userRequest == null) {
            throw new ApiException("Missing the required parameter 'userRequest' when calling registerUser(Async)");
        }

        return registerUserCall(userRequest, _callback);

    }

    /**
     * Register a new user
     * 
     * @param userRequest User registration data (required)
     * @return User
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> User registered successfully </td><td>  -  </td></tr>
     </table>
     */
    public User registerUser(UserRequest userRequest) throws ApiException {
        ApiResponse<User> localVarResp = registerUserWithHttpInfo(userRequest);
        return localVarResp.getData();
    }

    /**
     * Register a new user
     * 
     * @param userRequest User registration data (required)
     * @return ApiResponse&lt;User&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> User registered successfully </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<User> registerUserWithHttpInfo(UserRequest userRequest) throws ApiException {
        okhttp3.Call localVarCall = registerUserValidateBeforeCall(userRequest, null);
        Type localVarReturnType = new TypeToken<User>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Register a new user (asynchronously)
     * 
     * @param userRequest User registration data (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> User registered successfully </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call registerUserAsync(UserRequest userRequest, final ApiCallback<User> _callback) throws ApiException {

        okhttp3.Call localVarCall = registerUserValidateBeforeCall(userRequest, _callback);
        Type localVarReturnType = new TypeToken<User>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}

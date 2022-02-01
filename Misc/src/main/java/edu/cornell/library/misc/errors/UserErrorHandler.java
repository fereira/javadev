package edu.cornell.library.misc.errors;

import com.google.gson.Gson;

import edu.cornell.library.misc.exception.AppException;
import edu.cornell.library.misc.util.GsonResponseParser;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * The error handler that should be used when creation of a user fails.
 */
public class UserErrorHandler implements ErrorHandler {

    private Pattern pattern = Pattern.compile("/api/v1/accounts/\\d+/users");

    @Override
    public boolean shouldHandle(HttpRequest httpRequest, HttpResponse httpResponse) {
        return
                pattern.matcher(httpRequest.getRequestLine().getUri()).find() &&
                httpResponse.getStatusLine().getStatusCode() == 400 &&
                httpResponse.getEntity().getContentType().getValue().contains("application/json");
    }

    @Override
    public void handle(HttpRequest httpRequest, HttpResponse httpResponse) {
        Gson gson = GsonResponseParser.getDefaultGsonParser(false);
        try {
            UserErrorResponse response = gson.fromJson(EntityUtils.toString(httpResponse.getEntity()), UserErrorResponse.class);
            if (response.getErrors() != null) {
                throw new AppException("Failed to create user.", httpRequest.getRequestLine().getUri(), response);
            }
        } catch (IOException e) {
            // Ignore.
        }

    }
}

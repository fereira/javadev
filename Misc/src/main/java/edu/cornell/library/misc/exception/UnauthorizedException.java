package edu.cornell.library.misc.exception;

/**
 * Thrown if a request returns a 401 response without a WWW-Authenticate header.
 * Indicates that the user does not have permission to access the
 * requested resource. I believe this is actually an incorrect use
 * of 401 and it should be 403 instead but this is how the Canvas API do.
 */
public class UnauthorizedException extends AppException {
    private static final long serialVersionUID = 1L;
}

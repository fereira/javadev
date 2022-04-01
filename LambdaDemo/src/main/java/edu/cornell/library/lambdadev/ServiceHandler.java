package edu.cornell.library.lambdadev; 
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class ServiceHandler implements RequestHandler<Object, Object> {

    @Override
    public Object handleRequest(Object s, Context context) {
        context.getLogger().log("Input: " + s);
        return "Lambda Function is invoked...." + s;
    }
}

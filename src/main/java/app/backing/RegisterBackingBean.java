package app.backing;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by HEDIN on 24.12.2017.
 */
public class RegisterBackingBean {
    private HttpServletRequest request;
    private String message;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;

        if(request.getAttribute("email") != null )
            message = "<p>User with email "+ request.getAttribute("email") + " successfully added!</p>";
    }

    public String getMessage() {
        return message;
    }
}

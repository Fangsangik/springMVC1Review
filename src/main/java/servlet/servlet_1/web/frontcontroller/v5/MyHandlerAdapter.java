package servlet.servlet_1.web.frontcontroller.v5;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.servlet_1.web.frontcontroller.ModelView;

import java.io.IOException;

public interface MyHandlerAdapter {
    //Controller version을 처리 할 수 있는 adapter
    boolean supports(Object handler);

    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws ServletException, IOException;
}

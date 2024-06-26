package servlet.servlet_1.web.frontcontroller.v1;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.servlet_1.web.frontcontroller.ControllerV1;
import servlet.servlet_1.web.frontcontroller.v1.controller.MemberFormControllerV1;
import servlet.servlet_1.web.frontcontroller.v1.controller.MemberListControllerV1;
import servlet.servlet_1.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerV1.service");
        String requestURI = request.getRequestURI();

        //"/front-controller/v1/members/new-from"를 그대로 받아 정보가 꺼내진다.
        ControllerV1 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //JSP 재사용
        controller.process(request,response);
    }
}

package servlet.servlet_1.web.frontcontroller.v2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.servlet_1.web.frontcontroller.ControllerV1;
import servlet.servlet_1.web.frontcontroller.ControllerV2;
import servlet.servlet_1.web.frontcontroller.MyView;
import servlet.servlet_1.web.frontcontroller.v1.controller.MemberFormControllerV1;
import servlet.servlet_1.web.frontcontroller.v1.controller.MemberListControllerV1;
import servlet.servlet_1.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import servlet.servlet_1.web.frontcontroller.v2.Controller.MemberFormControllerV2;
import servlet.servlet_1.web.frontcontroller.v2.Controller.MemberListControllerV2;
import servlet.servlet_1.web.frontcontroller.v2.Controller.MemberSaveControllerV2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerV2 extends HttpServlet {
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerV1.service");
        String requestURI = request.getRequestURI();

        //"/front-controller/v1/members/new-from"를 그대로 받아 정보가 꺼내진다.
        ControllerV2 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //FrontController 호출
        //MemberFomrControllerV2 -> return new MyView
        MyView myView = controller.process(request, response);
        myView.render(request,response);
    }
}

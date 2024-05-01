package servlet.servlet_1.web.frontcontroller.v3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.servlet_1.web.frontcontroller.ControllerV3;
import servlet.servlet_1.web.frontcontroller.ModelView;
import servlet.servlet_1.web.frontcontroller.MyView;
import servlet.servlet_1.web.frontcontroller.v3.controller.MemberFormControllerV3;
import servlet.servlet_1.web.frontcontroller.v3.controller.MemberListControllerV3;
import servlet.servlet_1.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerV3 extends HttpServlet {
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerV1.service");
        String requestURI = request.getRequestURI();

        //"/front-controller/v1/members/new-from"를 그대로 받아 정보가 꺼내진다.
        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }


        //detail한 로직이기에 method로 뽑는게 좋다.
        //parameter를 다 뽑아서 map으로 반환
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName();//논리 이름만 제공
        MyView view = viewResolver(viewName);//논리 이름을 갖고, viewResolver 호출, ->  물리이름으로 변경

        view.render(mv.getModel(), request, response);
    }

    private static MyView viewResolver(String viewName) {
        MyView view = new MyView("/WEB-INF/views/" + viewName + ".jsp");
        return view;
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();

        //map을 넘겨줘야 한다.
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}

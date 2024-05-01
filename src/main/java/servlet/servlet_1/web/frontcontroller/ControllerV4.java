package servlet.servlet_1.web.frontcontroller;

import java.util.Map;

public interface ControllerV4 {
    /***
     *
     * @param paramMap
     * @param model
     * @return viewName
     *
     * 이전에는 paramMap만 넘겼는데 이번에는 model이 parameter로 넘어온다.
     * view의 이름만 반환
     */
    String process(Map<String, String> paramMap, Map<String, Object> model);
}

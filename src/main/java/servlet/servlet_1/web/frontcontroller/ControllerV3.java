package servlet.servlet_1.web.frontcontroller;

import java.util.Map;
import java.util.Objects;

public interface ControllerV3 {
    ModelView process(Map<String, String> param);
}

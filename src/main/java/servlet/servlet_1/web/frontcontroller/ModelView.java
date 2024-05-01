package servlet.servlet_1.web.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
public class ModelView {
    private String viewName;

    //원하는 데이터 넣어서, 나중에 JSP에서 사용 할 수 있도록
    private Map<String, Object> model = new HashMap<>();

    public ModelView(String viewName) {
        this.viewName = viewName;
    }
}

package servlet.servlet_1.web.frontcontroller.v3.controller;

import servlet.servlet_1.web.frontcontroller.ControllerV3;
import servlet.servlet_1.web.frontcontroller.ModelView;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {
    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}

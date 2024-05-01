package servlet.servlet_1.web.frontcontroller.v3.controller;

import servlet.servlet_1.domain.member.Member;
import servlet.servlet_1.domain.member.MemberRepository;
import servlet.servlet_1.web.frontcontroller.ControllerV3;
import servlet.servlet_1.web.frontcontroller.ModelView;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);
        return mv;
    }
}

package servlet.servlet_1.web.frontcontroller.v4.controller;

import servlet.servlet_1.domain.member.Member;
import servlet.servlet_1.domain.member.MemberRepository;
import servlet.servlet_1.web.frontcontroller.ControllerV4;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        List<Member> members = memberRepository.findAll();
        model.put("members", members);

        return "members";
    }
}

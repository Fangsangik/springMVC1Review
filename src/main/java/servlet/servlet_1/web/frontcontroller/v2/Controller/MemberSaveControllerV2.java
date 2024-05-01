package servlet.servlet_1.web.frontcontroller.v2.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.servlet_1.domain.member.Member;
import servlet.servlet_1.domain.member.MemberRepository;
import servlet.servlet_1.web.frontcontroller.ControllerV2;
import servlet.servlet_1.web.frontcontroller.MyView;

import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        System.out.println("member = " + member);
        memberRepository.save(member);

        //Model에 보관
        request.setAttribute("member", member);


        return new MyView( "/WEB-INF/views/save-result.jsp");
    }
}

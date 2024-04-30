package servlet.servlet_1.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.servlet_1.domain.member.Member;
import servlet.servlet_1.domain.member.MemberRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setContentType("utf-8");

        List<Member> members = memberRepository.findAll();

        PrintWriter writer = response.getWriter();
        writer.write("<html>");
        writer.write("<head>");
        writer.write(" <meta charset=\"UTF-8\">");
        writer.write(" <title>Title</title>");
        writer.write("</head>");
        writer.write("<body>");
        writer.write("<a href=\"/index.html\">메인</a>");
        writer.write("<table>");
        writer.write(" <thead>");
        writer.write(" <th>id</th>");
        writer.write(" <th>username</th>");
        writer.write(" <th>age</th>");
        writer.write(" </thead>");
        writer.write(" <tbody>");

//정적
/*
 w.write(" <tr>");
 w.write(" <td>1</td>");
 w.write(" <td>userA</td>");
 w.write(" <td>10</td>");
 w.write(" </tr>");
*/
        //동적으로 돌림
        for (Member member : members) {
            writer.write(" <tr>");
            writer.write(" <td>" + member.getId() + "</td>");
            writer.write(" <td>" + member.getUsername() + "</td>");
            writer.write(" <td>" + member.getAge() + "</td>");
            writer.write(" </tr>");
        }
        writer.write(" </tbody>");
        writer.write("</table>");
        writer.write("</body>");
        writer.write("</html>");
    }
}
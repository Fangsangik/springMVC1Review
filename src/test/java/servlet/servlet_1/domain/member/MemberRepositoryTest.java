package servlet.servlet_1.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clear();
    }

    @Test
    void save() {
        Member member = new Member("hello", 20);
        Member saveMember = memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getId());

        assertThat(findMember).isEqualTo(saveMember);
    }

    @Test
    void findAll(){
        Member member1 = new Member();
        Member member2 = new Member();

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> rst = memberRepository.findAll();

        assertThat(rst.size()).isEqualTo(2);
        assertThat(rst).contains(member1, member2);
    }
}

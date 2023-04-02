package springPractice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springPractice.domain.Member;
import springPractice.repository.MemberRepository;


public class MemberService {

	private final MemberRepository memberRepository;
	
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	//회원가입, 중복 회원 가입 불가
	public Long join(Member member) {
		memberRepository.findByName(member.getName())
			.ifPresent(m -> {
				throw new IllegalStateException("이미 존재하는 회원입니다");
		});
		
		memberRepository.save(member);
		return member.getId();
	}
	
	//전체 회원 조회
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	//한개의 회원 조회
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
	
}

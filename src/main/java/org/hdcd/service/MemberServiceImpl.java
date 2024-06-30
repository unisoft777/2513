package org.hdcd.service;

import org.hdcd.domain.Member;
import org.hdcd.domain.MemberAuth;
import org.hdcd.repository.MemberRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

	private final MemberRepository repository;
	
	@Override
	public long countAll() throws Exception {
		return repository.count();
	}
	
	@Override
	public void setupAdmin(Member member) throws Exception {
		Member memberEntity = new Member();
		memberEntity.setUserId(member.getUserId());
		memberEntity.setUserPw(member.getUserPw());
		memberEntity.setUserName(member.getUserName());
		memberEntity.setJob(member.getJob());
		
		MemberAuth memberAuth = new MemberAuth();
		memberAuth.setAuth("ROLE_ADMIN");
		
		memberEntity.addAuth(memberAuth);
		
		repository.save(memberEntity);
	}

}

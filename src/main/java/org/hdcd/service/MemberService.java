package org.hdcd.service;

import org.hdcd.domain.Member;

public interface MemberService {
	
	public long countAll() throws Exception;
	
	public void setupAdmin(Member member) throws Exception;

}

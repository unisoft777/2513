package org.hdcd.controller;

import org.hdcd.domain.Member;
import org.hdcd.service.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class MemberController {

	private final MemberService service;
	
	private final PasswordEncoder passwordEncoder;

	@PostMapping("/setup")
	public String setupAdmin(@Validated Member member, BindingResult result, RedirectAttributes rttr) throws Exception {
		if(result.hasErrors()) {
			return "user/setup";
		}
		
		if(service.countAll() == 0) {
			String inputPassword = member.getUserPw();
			member.setUserPw(passwordEncoder.encode(inputPassword));
			
			member.setJob("00");
			
			service.setupAdmin(member);
	
			rttr.addFlashAttribute("userName", member.getUserName());
			return "redirect:/user/registerSuccess";
		}
		
		return "redirect:/user/setupFailure";
	}

	@GetMapping("/setup")
	public String setupAdminForm(Member member, Model model) throws Exception {
		if(service.countAll() == 0) {
			return "user/setup";
		}
		
		return "user/setupFailure";
	}

	@GetMapping("/registerSuccess")
	public void registerSuccess(Model model) throws Exception {
		
	}
	
}

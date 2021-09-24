package com.web.curation.member;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class MemberService {
	private MemberAuthDao memberAuthDao;
	private MemberProfileDao memberProfileDao;

	public Optional<MemberDto> getMemberByEmail(String email){
		Optional<UserAuth> userAuth = memberAuthDao.getUserAuthByEmail(email);
		Optional<MemberDto> memberDto = Optional.ofNullable(null);

		if(userAuth.isPresent()){
			Optional<UserProfile> userProfile = memberProfileDao.findById(userAuth.get());
			memberDto = Optional.of(MemberAdapter.entityToDto(userProfile.get(), userAuth.get()));
		}
		return memberDto;
	}
	
	public void joinMember(String companyName, String email) {
		long randomNum = (long)(Math.random() * (long)Math.pow(10, 12));
		String uid = companyName.substring(0, 1).toUpperCase() + Long.toString(randomNum);

		UserAuth userAuth = UserAuth.builder()
				.uid(uid)
				.email(email)
				.emailCompany(companyName)
				.build();

		UserAuth mappingUserAuth = memberAuthDao.save(userAuth);

		UserProfile userProfile = UserProfile.builder()
				.filePath("default")
				.nickname("aaa")
				.hasSurveyed(false)
				.userAuth(mappingUserAuth)
				.build();

		memberProfileDao.save(userProfile);
	}
}

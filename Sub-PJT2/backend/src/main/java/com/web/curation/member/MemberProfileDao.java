package com.web.curation.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberProfileDao extends JpaRepository<UserProfile, String> {

    UserProfile getUserProfileByUserAuth(UserAuth userAuth);
}

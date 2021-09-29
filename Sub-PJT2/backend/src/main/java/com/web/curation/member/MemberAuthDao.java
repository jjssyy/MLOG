package com.web.curation.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberAuthDao extends JpaRepository<UserAuth, String>{

    Optional<UserAuth> getUserAuthByEmail(String email);

    UserAuth getUserAuthByUid(String uid);
}

package com.web.curation.member.emotion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEmotionDao extends JpaRepository<UserEmotion, String> {

    //where id={id} and is_deleted=false
    Optional<UserEmotion> getUserEmotionByUidAndIs_deletedFalse(String uid);
}

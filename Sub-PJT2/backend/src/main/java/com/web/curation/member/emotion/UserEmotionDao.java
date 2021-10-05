package com.web.curation.member.emotion;

import com.web.curation.member.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserEmotionDao extends JpaRepository<UserEmotion, UserEmotionPK> {

    //where id={id} and is_deleted=false
    Optional<List<UserEmotion>> getUserEmotionByUserAuth(UserAuth userAuth);
    UserEmotion getUserEmotionByUserAuthAndEmbGenre(UserAuth userAuth,UserEmotionPK embGenre);

    void deleteUserEmotionByUserAuth(UserAuth userAuth);
}

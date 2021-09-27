package com.web.curation.member.emotion;

import com.web.curation.member.UserAuth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEmotion {

    @Id
    private String uid;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(nullable = false)
    @ColumnDefault("0")
    private float neutral;

    @Column(nullable = false)
    @ColumnDefault("0")
    private float joy;

    @Column(nullable = false)
    @ColumnDefault("0")
    private float sadness;

    @Column(nullable = false)
    @ColumnDefault("0")
    private float anger;

    @Column(nullable = false)
    @ColumnDefault("0")
    private float fear;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean is_deleted;

    @ManyToOne
    @MapsId
    @JoinColumn(name="uid")
    private UserAuth userAuth;
}

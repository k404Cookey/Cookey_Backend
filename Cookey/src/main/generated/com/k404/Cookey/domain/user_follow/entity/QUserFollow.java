package com.k404.Cookey.domain.user_follow.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserFollow is a Querydsl query type for UserFollow
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserFollow extends EntityPathBase<UserFollow> {

    private static final long serialVersionUID = 133539218L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserFollow userFollow = new QUserFollow("userFollow");

    public final com.k404.Cookey.domain.user.entity.QUser follower;

    public final EnumPath<com.k404.Cookey.domain.common.YesOrNo> followerAlarm = createEnum("followerAlarm", com.k404.Cookey.domain.common.YesOrNo.class);

    public final com.k404.Cookey.domain.user.entity.QUser following;

    public final EnumPath<com.k404.Cookey.domain.common.YesOrNo> followingAlarm = createEnum("followingAlarm", com.k404.Cookey.domain.common.YesOrNo.class);

    public final QFollowKey id;

    public QUserFollow(String variable) {
        this(UserFollow.class, forVariable(variable), INITS);
    }

    public QUserFollow(Path<? extends UserFollow> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserFollow(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserFollow(PathMetadata metadata, PathInits inits) {
        this(UserFollow.class, metadata, inits);
    }

    public QUserFollow(Class<? extends UserFollow> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.follower = inits.isInitialized("follower") ? new com.k404.Cookey.domain.user.entity.QUser(forProperty("follower")) : null;
        this.following = inits.isInitialized("following") ? new com.k404.Cookey.domain.user.entity.QUser(forProperty("following")) : null;
        this.id = inits.isInitialized("id") ? new QFollowKey(forProperty("id")) : null;
    }

}


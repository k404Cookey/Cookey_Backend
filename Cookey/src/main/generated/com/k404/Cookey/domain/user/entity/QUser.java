package com.k404.Cookey.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1008690497L;

    public static final QUser user = new QUser("user");

    public final StringPath email = createString("email");

    public final ListPath<com.k404.Cookey.domain.user_follow.entity.UserFollow, com.k404.Cookey.domain.user_follow.entity.QUserFollow> follower = this.<com.k404.Cookey.domain.user_follow.entity.UserFollow, com.k404.Cookey.domain.user_follow.entity.QUserFollow>createList("follower", com.k404.Cookey.domain.user_follow.entity.UserFollow.class, com.k404.Cookey.domain.user_follow.entity.QUserFollow.class, PathInits.DIRECT2);

    public final ListPath<com.k404.Cookey.domain.user_follow.entity.UserFollow, com.k404.Cookey.domain.user_follow.entity.QUserFollow> following = this.<com.k404.Cookey.domain.user_follow.entity.UserFollow, com.k404.Cookey.domain.user_follow.entity.QUserFollow>createList("following", com.k404.Cookey.domain.user_follow.entity.UserFollow.class, com.k404.Cookey.domain.user_follow.entity.QUserFollow.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath name = createString("name");

    public final ListPath<String, StringPath> roles = this.<String, StringPath>createList("roles", String.class, StringPath.class, PathInits.DIRECT2);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}


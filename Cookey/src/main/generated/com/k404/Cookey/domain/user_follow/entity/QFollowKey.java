package com.k404.Cookey.domain.user_follow.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFollowKey is a Querydsl query type for FollowKey
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QFollowKey extends BeanPath<FollowKey> {

    private static final long serialVersionUID = 592448504L;

    public static final QFollowKey followKey = new QFollowKey("followKey");

    public final NumberPath<Long> followerId = createNumber("followerId", Long.class);

    public final NumberPath<Long> followingId = createNumber("followingId", Long.class);

    public QFollowKey(String variable) {
        super(FollowKey.class, forVariable(variable));
    }

    public QFollowKey(Path<? extends FollowKey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFollowKey(PathMetadata metadata) {
        super(FollowKey.class, metadata);
    }

}


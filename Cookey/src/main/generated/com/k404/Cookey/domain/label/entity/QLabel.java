package com.k404.Cookey.domain.label.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLabel is a Querydsl query type for Label
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLabel extends EntityPathBase<Label> {

    private static final long serialVersionUID = -1967274993L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLabel label = new QLabel("label");

    public final com.k404.Cookey.domain.common.QBaseTimeEntity _super = new com.k404.Cookey.domain.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final com.k404.Cookey.domain.recipe.entity.QRecipe recipe;

    public final com.k404.Cookey.domain.user.entity.QUser user;

    public QLabel(String variable) {
        this(Label.class, forVariable(variable), INITS);
    }

    public QLabel(Path<? extends Label> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLabel(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLabel(PathMetadata metadata, PathInits inits) {
        this(Label.class, metadata, inits);
    }

    public QLabel(Class<? extends Label> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recipe = inits.isInitialized("recipe") ? new com.k404.Cookey.domain.recipe.entity.QRecipe(forProperty("recipe"), inits.get("recipe")) : null;
        this.user = inits.isInitialized("user") ? new com.k404.Cookey.domain.user.entity.QUser(forProperty("user")) : null;
    }

}


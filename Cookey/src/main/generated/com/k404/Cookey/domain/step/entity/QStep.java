package com.k404.Cookey.domain.step.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStep is a Querydsl query type for Step
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStep extends EntityPathBase<Step> {

    private static final long serialVersionUID = 2106677057L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStep step = new QStep("step");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final com.k404.Cookey.domain.recipe.entity.QRecipe recipe;

    public final NumberPath<Integer> sequence = createNumber("sequence", Integer.class);

    public QStep(String variable) {
        this(Step.class, forVariable(variable), INITS);
    }

    public QStep(Path<? extends Step> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStep(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStep(PathMetadata metadata, PathInits inits) {
        this(Step.class, metadata, inits);
    }

    public QStep(Class<? extends Step> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recipe = inits.isInitialized("recipe") ? new com.k404.Cookey.domain.recipe.entity.QRecipe(forProperty("recipe"), inits.get("recipe")) : null;
    }

}


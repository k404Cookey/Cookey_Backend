package com.k404.Cookey.domain.recipe.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRequestDto_Step is a Querydsl query type for Step
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QRequestDto_Step extends BeanPath<RequestDto.Step> {

    private static final long serialVersionUID = 35181831L;

    public static final QRequestDto_Step step = new QRequestDto_Step("step");

    public final StringPath description = createString("description");

    public final StringPath imageUrl = createString("imageUrl");

    public QRequestDto_Step(String variable) {
        super(RequestDto.Step.class, forVariable(variable));
    }

    public QRequestDto_Step(Path<? extends RequestDto.Step> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRequestDto_Step(PathMetadata metadata) {
        super(RequestDto.Step.class, metadata);
    }

}


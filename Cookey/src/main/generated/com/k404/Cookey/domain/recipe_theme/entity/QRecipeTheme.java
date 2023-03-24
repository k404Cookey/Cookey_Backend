package com.k404.Cookey.domain.recipe_theme.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecipeTheme is a Querydsl query type for RecipeTheme
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecipeTheme extends EntityPathBase<RecipeTheme> {

    private static final long serialVersionUID = -1318986182L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecipeTheme recipeTheme = new QRecipeTheme("recipeTheme");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.k404.Cookey.domain.recipe.entity.QRecipe recipe;

    public final com.k404.Cookey.domain.theme.entity.QTheme theme;

    public QRecipeTheme(String variable) {
        this(RecipeTheme.class, forVariable(variable), INITS);
    }

    public QRecipeTheme(Path<? extends RecipeTheme> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecipeTheme(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecipeTheme(PathMetadata metadata, PathInits inits) {
        this(RecipeTheme.class, metadata, inits);
    }

    public QRecipeTheme(Class<? extends RecipeTheme> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recipe = inits.isInitialized("recipe") ? new com.k404.Cookey.domain.recipe.entity.QRecipe(forProperty("recipe"), inits.get("recipe")) : null;
        this.theme = inits.isInitialized("theme") ? new com.k404.Cookey.domain.theme.entity.QTheme(forProperty("theme")) : null;
    }

}


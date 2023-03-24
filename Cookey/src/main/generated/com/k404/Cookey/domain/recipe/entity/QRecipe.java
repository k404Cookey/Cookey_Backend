package com.k404.Cookey.domain.recipe.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecipe is a Querydsl query type for Recipe
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecipe extends EntityPathBase<Recipe> {

    private static final long serialVersionUID = 1592675589L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecipe recipe = new QRecipe("recipe");

    public final com.k404.Cookey.domain.common.QBaseTimeEntity _super = new com.k404.Cookey.domain.common.QBaseTimeEntity(this);

    public final ListPath<Recipe, QRecipe> child = this.<Recipe, QRecipe>createList("child", Recipe.class, QRecipe.class, PathInits.DIRECT2);

    public final ListPath<com.k404.Cookey.domain.comment.entity.Comment, com.k404.Cookey.domain.comment.entity.QComment> comments = this.<com.k404.Cookey.domain.comment.entity.Comment, com.k404.Cookey.domain.comment.entity.QComment>createList("comments", com.k404.Cookey.domain.comment.entity.Comment.class, com.k404.Cookey.domain.comment.entity.QComment.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.k404.Cookey.domain.ingredient.entity.Ingredient, com.k404.Cookey.domain.ingredient.entity.QIngredient> ingredients = this.<com.k404.Cookey.domain.ingredient.entity.Ingredient, com.k404.Cookey.domain.ingredient.entity.QIngredient>createList("ingredients", com.k404.Cookey.domain.ingredient.entity.Ingredient.class, com.k404.Cookey.domain.ingredient.entity.QIngredient.class, PathInits.DIRECT2);

    public final ListPath<com.k404.Cookey.domain.label.entity.Label, com.k404.Cookey.domain.label.entity.QLabel> labels = this.<com.k404.Cookey.domain.label.entity.Label, com.k404.Cookey.domain.label.entity.QLabel>createList("labels", com.k404.Cookey.domain.label.entity.Label.class, com.k404.Cookey.domain.label.entity.QLabel.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final QRecipe parent;

    public final ListPath<com.k404.Cookey.domain.rating.entity.Rating, com.k404.Cookey.domain.rating.entity.QRating> ratings = this.<com.k404.Cookey.domain.rating.entity.Rating, com.k404.Cookey.domain.rating.entity.QRating>createList("ratings", com.k404.Cookey.domain.rating.entity.Rating.class, com.k404.Cookey.domain.rating.entity.QRating.class, PathInits.DIRECT2);

    public final ListPath<com.k404.Cookey.domain.recipe_theme.entity.RecipeTheme, com.k404.Cookey.domain.recipe_theme.entity.QRecipeTheme> recipeThemes = this.<com.k404.Cookey.domain.recipe_theme.entity.RecipeTheme, com.k404.Cookey.domain.recipe_theme.entity.QRecipeTheme>createList("recipeThemes", com.k404.Cookey.domain.recipe_theme.entity.RecipeTheme.class, com.k404.Cookey.domain.recipe_theme.entity.QRecipeTheme.class, PathInits.DIRECT2);

    public final NumberPath<Double> starCount = createNumber("starCount", Double.class);

    public final NumberPath<Integer> step = createNumber("step", Integer.class);

    public final ListPath<com.k404.Cookey.domain.step.entity.Step, com.k404.Cookey.domain.step.entity.QStep> steps = this.<com.k404.Cookey.domain.step.entity.Step, com.k404.Cookey.domain.step.entity.QStep>createList("steps", com.k404.Cookey.domain.step.entity.Step.class, com.k404.Cookey.domain.step.entity.QStep.class, PathInits.DIRECT2);

    public final StringPath thumbnail = createString("thumbnail");

    public final NumberPath<Integer> time = createNumber("time", Integer.class);

    public final StringPath title = createString("title");

    public final com.k404.Cookey.domain.user.entity.QUser user;

    public final NumberPath<Integer> viewCount = createNumber("viewCount", Integer.class);

    public final NumberPath<Integer> wishCount = createNumber("wishCount", Integer.class);

    public QRecipe(String variable) {
        this(Recipe.class, forVariable(variable), INITS);
    }

    public QRecipe(Path<? extends Recipe> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecipe(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecipe(PathMetadata metadata, PathInits inits) {
        this(Recipe.class, metadata, inits);
    }

    public QRecipe(Class<? extends Recipe> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QRecipe(forProperty("parent"), inits.get("parent")) : null;
        this.user = inits.isInitialized("user") ? new com.k404.Cookey.domain.user.entity.QUser(forProperty("user")) : null;
    }

}


package com.k404.Cookey.domain.label.repository;

import static com.k404.Cookey.domain.label.entity.QLabel.label;

import java.util.List;

import com.k404.Cookey.domain.recipe.entity.Recipe;
import com.k404.Cookey.domain.user.entity.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class LabelDslRepository extends QuerydslRepositorySupport {

	private final JPAQueryFactory queryFactory;

	public LabelDslRepository(JPAQueryFactory queryFactory) {
		super(Recipe.class);
		this.queryFactory = queryFactory;
	}

	public List<Recipe> labelList(User user, int limit, int offset) {
		List<Recipe> list = queryFactory
				.select(label.recipe)
				.from(label)
				.where(label.user.eq(user))
				.orderBy(label.createdDate.desc())
				.limit(limit)
				.offset(offset)
				.fetch();
		return list;
	}
	
}

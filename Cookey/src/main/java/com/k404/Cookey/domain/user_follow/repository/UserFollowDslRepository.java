package com.k404.Cookey.domain.user_follow.repository;

import static com.k404.Cookey.domain.recipe.entity.QRecipe.recipe;
import static com.k404.Cookey.domain.user.entity.QUser.user;
import static com.k404.Cookey.domain.user_follow.entity.QUserFollow.userFollow;

import java.util.List;

import com.k404.Cookey.domain.common.YesOrNo;
import com.k404.Cookey.domain.recipe.entity.Recipe;
import com.k404.Cookey.domain.user.entity.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class UserFollowDslRepository extends QuerydslRepositorySupport {

	private final JPAQueryFactory queryFactory;

	public UserFollowDslRepository(JPAQueryFactory queryFactory) {
		super(Recipe.class);
		this.queryFactory = queryFactory;
	}

	public List<Recipe> followingFeed(User user, int limit, int offset) {
		List<Recipe> list = queryFactory
				.selectFrom(recipe)
				.where(recipe.user.in(
						JPAExpressions
						.select(userFollow.following)
						.from(userFollow)
						.where(userFollow.follower.eq(user),
								userFollow.followingAlarm.eq(YesOrNo.Y))))
				.orderBy(recipe.createdDate.desc())
				.limit(limit)
				.offset(offset)
				.fetch();
		return list;
	}
	
	public List<Recipe> followerFeed(User user, int limit, int offset) {
		List<Recipe> list = queryFactory
				.selectFrom(recipe)
				.where(recipe.user.in(
						JPAExpressions
						.select(userFollow.follower)
						.from(userFollow)
						.where(userFollow.following.eq(user),
								userFollow.followerAlarm.eq(YesOrNo.Y))))
				.orderBy(recipe.createdDate.desc())
				.limit(limit)
				.offset(offset)
				.fetch();
		return list;
	}
	
	public List<User> findFollowingByFollower(User follow, int limit, int offset) {
		List<User> list = queryFactory
				.select(userFollow.following)
				.from(userFollow)
				.where(userFollow.follower.eq(follow),
						userFollow.followerAlarm.eq(YesOrNo.Y))
				.limit(limit)
				.offset(offset)
				.fetch();
		return list;
	}
	
	public List<User> findFollowerByFollowing(User follow, int limit, int offset) {
		List<User> list = queryFactory
				.select(userFollow.follower)
				.from(userFollow)
				.where(userFollow.following.eq(follow),
						userFollow.followingAlarm.eq(YesOrNo.Y))
				.limit(limit)
				.offset(offset)
				.fetch();
		return list;
	}
}

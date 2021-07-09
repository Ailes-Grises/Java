package com.example.demo.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional//updateクエリに必要なアノテーション メソッドをロールバック状態にする
@Repository
public interface JpaArticleDao extends JpaRepository<Article, Long> {
	@Modifying
	@Query(value = "UPDATE article AS a SET a.favorite_count = a.favorite_count + 1 WHERE a.id = :aid", nativeQuery = true)
	Integer increaseFavoriteCount(@Param("aid") Long aid); //戻り値は更新件数の数字、Integer
	
	@Modifying
	@Query(value = "UPDATE article AS a SET a.access_count = a.access_count + 1 WHERE a.id = :aid", nativeQuery = true)
	Integer increaseAccessCount(@Param("aid") Long aid); //戻り値は更新件数の数字、Integer
	
	@Modifying
	@Query(value = "UPDATE article AS a SET a.content = :acontent WHERE a.id = :aid", nativeQuery = true)
	Integer updateContent(@Param("aid") Long aid, @Param("acontent") String content); //戻り値は更新件数の数字、Integer
}
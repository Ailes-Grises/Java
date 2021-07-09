package com.example.demo.db;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * login_user テーブル用のDao (JPAは使ってない)
 * @author naoto.suzuki
 *
 */
@Repository
public class LoginUserDao {
	
	@Autowired
	EntityManager entity_manager;
	
	/**
	 * フォームの入力値から該当するユーザを検索 合致するものが無い場合Nullが返される
	 * @param userName
	 * @return 一致するユーザが存在するとき:UserEntity、存在しないとき:Null
	 */
	public LoginUser findUser(String username) {
		String query = "";
		query += "SELECT * ";
		query += "FROM login_user ";
		query += "WHERE username = :username "; //setParameterで引数の値を代入できるようにNamedParameterを利用
		
		//EntityManagerで取得された結果はオブジェクトとなるので、LoginUser型へキャストが必要となる
		return (LoginUser)entity_manager.createNativeQuery(query, LoginUser.class).setParameter("username", username)
				.getSingleResult();
	}

}
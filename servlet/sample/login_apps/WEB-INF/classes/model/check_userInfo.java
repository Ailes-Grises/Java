package model;

public class check_userInfo{
	private String authentication_name;
	private String authentication_password;

	public check_userInfo(){
		// 本当はこんなことは書かないが，あらかじめ登録データとして以下を設定する
		this.authentication_name = "shibazaki";
		this.authentication_password = "VON";
	}
	public boolean check(model.user_info UserInfo){
		// 認証情報誤りがあればfalse を返す
		if( (this.authentication_name.equals(UserInfo.getName()) == false) || 
				(this.authentication_password.equals(UserInfo.getPassword()) == false)){
			return false;
		}

		return true;
	}
}

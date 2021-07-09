※ このファイルは書きかけです

# 認証・許可についての設定を記述したクラス(WebSecurityConfig.java)
- 必要なライブラリをインポート
- @Configuration をつけてコンフィグクラスであることを明記
- @EnableWebSecurity をつけてSpring-Security を使うことを明記
- WebSecurityConfigurerAdapter クラスを継承
- 引数違いで3つのconfigure() メソッドを利用する

## configure(AuthenticationManagerBuilder) メソッド
inMemoryAuthentication() でインメモリで認証を行うことを明示し、認証に利用するユーザ名とパスワードを直接セットする。

## configure(WebSecurity) メソッド
静的ファイル(image, css, javascript)を利用する際のリクエストは疎通させる設定を行う

## configure(HttpSecurity) メソッド
認証・認可の処理の中でhttpリクエスト関連の部分についての設定を記述するためのメソッド。
ドットで徹底的に設定を追加していく。

- アプリ内の画面は基本的に認証しないとみられない
- ログイン画面は"/login"に存在
- 認証処理は"/sign_in"へリクエストが送信されると実行される
- ログインに成功したら"/hello"へ遷移
- ログイン失敗/ログアウト時にはlogin画面へgetパラメータを渡して遷移


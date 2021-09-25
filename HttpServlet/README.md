Servlet/JSP の扱い及びweb アプリの開発を学習する．

1. [基本事項](#Basic)
1. [Tomcat 上のサーブレットの設定方法](#Tomcat)
	1. [WebContent ディレクトリについて](#WebContent)
1. [サーブレットの基礎文法](#Syntax)
1. [あとがき](#Atogaki)
1. [エラー集](#Error)

参考サイト : http://www.javaroad.jp/opensource/js_tomcat2.htm

<a id = "Basic"></a>
## 基本事項
- サーブレットとは，Javaのサーバ上で動作するweb アプリケーションを実現するためのプログラムの一種である(CGIみたいなもの)．
- web サーバ(普通のLinux のパソコン)の中にこのプログラム(コンパイル済みにしておくこと)を置いておく(デプロイという)と，web ブラウザを介してユーザとこのアプリとの通信を行うことができる．これによって、ブラウザを介して様々なサービスをインターネット上に公開することができる。
	- 具体的には、ユーザからの要求に応じて動的にwebページの表示内容を変更したり，ログインの機構を実現したりすることができる．ブラウザを介してネット上で提供されるサービスは全てweb アプリである。(Slack とかAmazon のサイトとかGmail とか)
- Servlet は，HttpServlet というクラスを継承して作成する。一つのServlet は，通常は一つのクラスとして作成する。
- Servlet がユーザからのリクエスト(GET)の受付を担当し，JSP が画面表示(web ページ)を担当している。
- GET リクエストは、ユーザから情報を請求されるようなリクエストのことである。例えば、web ページの画面表示リクエストなどはこれにあたる。
- POST リクエストは、ユーザから送られてきた情報をデータベースに登録するようなリクエストのことである。新規会員登録などのフォームで処理するようなリクエストはこれにあたる。

<a id = "Tomcat"></a>
## Tomcat 上のサーブレットの設定方法

サーブレットのHelloWorld を実行しようとしたが，ソースコードの配置場所やディレクトリの作り方などが分からなくてハマったので備忘録．
Tomcat の構築は既に完了しているとして，ここではまずサーブレット自体の構成について解説する．

1. /opt/tomcat/webapps/ に任意の名前のディレクトリを作成する．このディレクトリがWEB アプリの本体となる．
```
 $ cd /opt/tomcat/webapps
 $ mkdir project_name
```

2. 上で作成したディレクトリの内部に，以下のディレクトリを自分で手動で作成する．ちなみに自動で作成してくれるコマンドとかはない．
```
 $ mkdir -p ./project_name/WEB-INF/classes
 $ mkdir -p ./project_name/WEB-INF/lib
```

3. ./project_name/WEB-INF/classes ディレクトリにサーブレットのソースコードを配置してコンパイルする．
```
 $ cd ./project_name/WEB-INF/classes
 $ vim HelloWorld.java
 $ javac -classpath /opt/tomcat/lib/servlet-api.jar HelloWorld.java
```

4. WEB-INF ディレクトリにweb.xml を配置する(※ javaソースコード内部でwebアノテーションを設定した場合にはweb.xml は不要)．
```
 $ cd ../
 $ vim web.xml
```

5. Apache httpd とTomcat を連携させるなら，<a href="https://github.com/Ailes-Grises/server#CoHttpd" target="_blank">こちらのページ</a>を参考にしながらhttpd-proxy.conf を編集する．
```
 $ mkdir -p /etc/httpd/conf/extra/
 $ vim /etc/httpd/conf/extra/httpd-proxy.conf
```

6. ブラウザから，作成したアプリにアクセスしてみる．これには，Tomcat に存在するアプリを直接名指しにして実行する方法と，httpd のプロキシ設定でURL中に特定のキーワードが指定された場合にのみ処理をTomcat にフォワードして実行する方法がある．
```
 // 基本的なURL のルール
 > http://IPアドレス:ポート番号/アプリ名/{webアノテーションかweb.xmlのurl-pattern}

 // httpd から連携させる場合
 > http://IPアドレス:ポート番号/httpd-proxy.conf のキーワード/{webアノテーションかweb.xmlのurl-pattern}


 // 1. Tomcat(ポート番号8080) を名指しにしてアプリにアクセス
 // 例 : アプリ名 = "project_name", url-pattern = "servlet/hello"
 > http://192.168.XX.XX:8080/project_name/servlet/hello


 // 2. httpd にアクセス(Tomcat と連携させてアクセス)
 // 例 : キーワード = "hoge", url-pattern = "servlet/hello"
 > http://192.168.XX.CC/hoge/servlet/hello
```
/etc/httpd/conf/extra/httpd-proxy.conf の書き方は<a href="https://github.com/Ailes-Grises/server#CoHttpd" target="_blank">こちらのページ</a>を参照

以上がTomcat 上におけるサーブレットのディレクトリの構築方法である．

<a id = "WebContent"></a>
## WebContent ディレクトリについて
MVCモデルなどを勉強し始めると，```WebContent``` というディレクトリを良く目にする．
これは何なのかというと，Eclipse 独自の事情で作られたwebアプリのルートディレクトリということになる．
ここまでのようにEclipse を使わない場合，```./WebContent/ = /opt/tomcat/webapps/Hoge/``` のことだと考えて問題ない．
実際，Eclipse で開発を行う場合にはWebContent と同一のディレクトリに```build``` 等のよく分からないディレクトリが複数存在するが，アプリを起動してもこれらがサーバ上で公開されることは無いらしい．

要するに，アプリ本体のコード以外にもプロジェクトごとにあれこれと設定情報等をまとめて扱う必要があり，これらを管理するためのディレクトリがアプリ本体以外に複数存在する，だからアプリ本体を管理するためのディレクトリとして"WebContent" というディレクトリが作られた，ということだろう．
上記の解釈で多分合っていると思うので，迷ったら参考にすること．

<a id = "Syntax"></a>
# サーブレットの基礎文法
サーブレットは基本的に一つのクラスとして定義するが，このクラスはHttpServlet クラスを継承することによって構築する．
やることとしては，HttpServlet::doGet() とHttpServlet::doPost() をオーバーライドするだけ．
以下にweb アプリ全体の処理の流れを示す:

1. http://hogehoge.com にリクエストが来ると、まずはindex.jsp がお出迎えする。お客さんはここから画面遷移するなり、フォームに情報を書き込んだりしたりする。
1. リンクを移動するだけならhtml のaタグだけで済むが、静的ページ以外のページを要求してきたり、情報を入力してそれを登録するようなアクションがあった場合、JSP ファイル内のform タグのaction 属性に書かれたURL アノテーションと同じアノテーションを持っているコントローラクラスに処理が遷移する。アノテーションを使わない場合には、web.xml がこのマッピングを担当する。
1. コントローラクラスに処理が飛んだら、さらにURL アノテーションと一致するGET かPOST 関数に処理が飛ぶ。そこで何らかの処理を行ってから最後に関数内でDispatch クラスのforward() を呼び出し、ユーザへの応答を担当しているJSP のページへ処理を飛ばす。
1. 飛んだ先のJSP では、コントローラクラスから受け取ったデータがあればそれを暗黙変数を使って画面表示したりする。

この操作を繰り返す事によって、様々なアプリケーションの機能を実現している。


以下にサーブレットクラス全体の基本構造を示す:
1. java.io.\*, javax.servlet.\*, javax.servlet.http.\*, javax.servlet.annotation.\* をインポートする
1. HttpServlet クラスを継承する
1. doGet(HttpServletRequest, HttpServletResponse) とdoPost() をオーバーライドする
	1. エラー処理は全てServletException とIOException クラスに投げる
1. GET リクエストが来た時はdoGet(), POST リクエストが来た時はdoPost() が発動する．
1. HttpServletRequest クラスには，リクエストされた情報が全て詰まっている．ここから情報を読み出して処理していく．
1. HttpServletResponse クラスには，サーブレットがクライアントに送り返したいHTML の情報を詰めていく．

doGet() の基本は以下の通り:
1. response.setContentType() でContent-Type ヘッダの情報をresponse に詰めていく．printf のノリで手書きで書く．
1. response.getWriter() で生成した何か(恐らく何らかのファイルディスクリプタのポインタ)をPrintWriter クラスのインスタンス(隠蔽しているが，恐らくこれの正体もポインタ)で受け取り，HTML を書いていく(printf みたいなノリ)．

<a id = "Atogaki"></a>
# あとがき
実際の開発ではこのServlet/JSP による開発が行われることはまず無い。
画面表示に関してはJSP ではなくてThymeleaf というフレームワークかjavascript のフレームワークであるVue.js やNuxt.js などのフレームワークが使われるし、サーブレットが担当していたコントローラの機能などはSpring Boot というフレームワークで開発されることが多い。
理由としては、Servlet/JSP による開発はMVC モデルが目指していた完全なる分業とは程遠いものだという点と、バリデーションチェックやログイン機構などのweb アプリを作成する上では必須の機能を自分で一から実装しなければならない点にある(こういう機能は予め用意されていて然るべき機能である。さもないと車輪の再発明にも程がある)。
Spring Framework などの何らかのフレームワークを使用するということは、web アプリを開発するための文法的な作法まで変わるということなので、もはや別の言語を学ぶのに近い部分がある。
しかし、一度MVC がきちんと守られたフレームワークの使い方を覚えてしまえば、他の言語の他のフレームワークを使っても仕組み自体は対して変わらないため、基本的なサーブレットの開発方法を覚えたらそちらを勉強することをおすすめする。


<a id = "Error"></a>
# エラー集
私が実際に経験したエラーとその解決方法を残しておく．
- サーブレットでコンパイルが通らない
	- クラスパスを指定していない．```javac -classpath /opt/tomcat/lib/servlet-api.jar hoge.java``` と入力したら解決した．
- エラーを修正してコンパイルしたのに結果が反映されない
	- tomcat.service を再起動していなかった．```systemctl restart tomcat.service``` を実行してしばらく待って再アクセスしたら解決した．
- フォームを作成する際，jsp ファイルと連携先のアノテーションが完全に一致しているのにどうしても404エラーが出る
	- package を宣言していたのに，それに対応するディレクトリを作っていなかった．サーブレットのアノテーションとjsp のaction は合っていたので，packege に対応するディレクトリを作成して，そこでサーブレットをもう一度コンパイルし，最後にtomcat を再起動したら解決した．ちなみに入力したURL は一切変更していないし，やはりアノテーション周りは合っていた．ただし，ブラウザの方で該当ページの再読み込みを行わないといつまで経っても正常に動作しない．
- 「シンボルが見つかりません」
	- import がうまく行っていなかった．javax.servlet.\* とだけ宣言していたが，これでは不十分で，実際には"javax.servlet.http.\*" と"javax.servlet.annotation.\*" も追加で宣言しなければ動作しなかった．
- クラスをパッケージで管理したらコンパイルが通らなくなった
  - 各パッケージ名のディレクトリの1つ上の親ディレクトリでコンパイルする．詳しくは[ここ](https://github.com/Ailes-Grises/Java#Package)を参照すること．


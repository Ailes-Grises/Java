Servlet/JSP の扱い及びweb アプリの開発を学習する．

1. [基本事項](#Basic)
1. [Tomcat 上のサーブレットの設定方法](#Tomcat)
1. [サーブレットの基礎文法](#Syntax)

参考サイト : http://www.javaroad.jp/opensource/js_tomcat2.htm

<a id = "Basic"></a>
## 基本事項
- Servlet は，HttpServlet というクラスを継承して作成する
- 一つのServlet は，通常は一つのクラスとして作成する
- web アプリを開発するときには，通常Servlet とJSP の両方を用意する
- Servlet がユーザからのリクエスト(GET)を受け取り，JSP が応答(web ページ)を返す
- 「GET = web ページよこせ」, 「POST = サーバにデータを送るから処理してくれ」

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

<a id = "Syntax"></a>
# サーブレットの基礎文法
サーブレットは基本的に一つのクラスとして定義するが，このクラスはHttpServlet クラスを継承することによって構築する．
恐らくだが，サーブレットの基本型に関しては，ソケット通信のようにある程度作法の様なお決まりの型があると思う．

以下にサーブレットクラス全体の基本構造を示す:
1. java.io.*, javax.servlet.* をインポートする
1. HttpServlet クラスを継承する
1. doGet(HttpServletRequest, HttpServletResponse) とdoPost() をオーバーライドする
	1. エラー処理は全てServletException とIOException クラスに投げる
1. GET リクエストが来た時はdoGet(), POST リクエストが来た時はdoPost() が発動する．
1. HttpServletRequest クラスには，リクエストされた情報が全て詰まっている．ここから情報を読み出して処理していく．
1. HttpServletResponse クラスには，サーブレットがクライアントに送り返したいHTML の情報を詰めていく．

doGet() の基本は以下の通り:
1. response.setContentType() でContent-Type ヘッダの情報をresponse に詰めていく．printf のノリで手書きで書く．
1. response.getWriter() で生成した何か(恐らく何らかのファイルディスクリプタのポインタ)をPrintWriter クラスのインスタンス(隠蔽しているが，恐らくこれの正体もポインタ)で受け取り，HTML を書いていく(printf みたいなノリ)．


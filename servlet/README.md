Servlet/JSP の扱い及びweb アプリの開発を学習する．

# 基本事項
- Servlet とJSP はほぼ同じもの
- Servlet は，Java コードの中にHTML がインラインで書かれているようなもの
- 逆にJSP は，HTML コードの中にJava がインラインで書かrているようなもの
- web アプリを開発するときには，通常Servlet とJSP の両方を用意する
- Servlet がユーザからのリクエスト(GET)を受け取り，JSP が応答(web ページ)を返す
- 「GET = web ページよこせ」, 「POST = データを送るから処理しろ」
- 一つのServlet は，通常は一つのクラスとして作成する

# Login を行うweb アプリの処理の流れ
0. サーブレットとしてLoginServlet, JSP としてlogin.jsp があるとする．
1. ユーザがURL 欄にLoginServlet を指定して目的のweb ページを要求する(GET)
2. login.jsp にフォワードを行う(処理を催促するってことかも?)
3. login.jsp がログインページのhtml ファイルをユーザに送り返す
4. ユーザがID とパスワードを入力してログインボタンを押す(POST)
5. サーブレットがPOST リクエストを受け取り，ユーザの認証情報が正しいかどうか判断する．正しい場合にはログイン成功のweb ページ，正しくない場合にはログイン失敗のweb ページにリダイレクトする

  > データベースからデータを読み出してそれを表示するweb アプリを作る場合には，Servlet でDB にアクセスし，データを読み出してそれをweb アプリ内の共有メモリ(リクエストスコープやセッションスコープなどがある)に保存し，そのデータをJSP でHTML の中に表示する，という手順を踏む．
  > この方法だと，JSP と扱うデザイナーもJava の知識が無いとデータベースにアクセス出来ないという問題点が残ってしまうが，これを回避するために，EL という形式がある．これを利用すると，"${name}" みたいに書くだけで，データベースにアクセス可能となる．

参考URL: https://www.pc-koubou.jp/magazine/2292


# Tomcat 上のサーブレットの設定方法

サーブレットのHelloWorld を実行しようとしたが，ソースコードの配置場所やディレクトリの作り方などが分からなくてハマったので備忘録．

1. /opt/tomcat/webapps/ に任意の名前ディレクトリを作成する．このディレクトリがWEB アプリの本体となる．
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
 $ javac HelloWorld.java
```

4. WEB-INF ディレクトリにweb.xml を配置する．
```
 $ cd ../
 $ vim web.xml
```

5. ブラウザ上で，"http://192.168.XX.XX:8080/project_name/servlet/hello" と入力する．うまく行けば画面上にHello World が出てくる．


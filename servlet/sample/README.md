このリポジトリは，サーブレット/JSP によるログインと注文を受け付けるアプリケーションである．

本家様: https://www.ipentec.com/document/java-servlet-bean-order-app

1. [JavaBeans とは](#AboutBeans)
	1. [概要](#Abstract)
	1. [作り方](#HowToMake)
	1. [使い方](#HowToUse)

<a id = "AboutBeans"></a>
# JavaBeans とは
<a id = "Abstract"></a>
## 概要
データベースを実現するクラスの機構の事である．
今回はJavaBeans の構造を使って顧客の情報などを管理する．
何か特別なAPI を使うわけではなく，以下に示す仕様に従ったクラスのことをJavaBeans と言う:
	1. プロパティ(private 属性)へのgetter/setter/メソッドを持つ
	1. 引数のないコンストラクタを持つ
	1. java.io.Serializable を実装している

参考サイト: https://techacademy.jp/magazine/34816

例えば，ユーザがアプリを使って入力フォームに氏名や年齢等の情報を入力する場合，サーブレット側では受け取ったデータを個人ごとにそれぞれ整理して補完しておくための仕組みが必要となる．
これがJavaBeans である．

メリットとしては，データの意図しない書き換えを防ぐことが出来る点にあるらしい．
というのも，各データ属性はprivate であるため，外部からはアクセスが出来ない．
したがって，必ずインスタンスの生成をしてからセッタを呼ばないとデータ属性を書き換えられない様になっている(一種のカプセル化)．

<a id = "HowToUse"></a>
## 作り方
手順はザックリ以下の様になる:

1. データ属性をprivate で定義する
1. 引数なしのコンストラクタを定義する
1. 各データ属性のGetter とSetter をpublic で定義する

こんな感じ:
```
public class UserBean{

	private String name;
	private int age;

	UserBean (){};

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
```

<a id = "HowToMake"></a>
## 使い方
JavaBeans の使い方の手順は以下の通り:
1. インスタンスを生成する
1. セッタを使ってデータ属性を定義する
1. ゲッタを使ってデータ属性を取得する

それでは，先程のUserBean を使ってデータの管理を行ってみる．
使い方は以下の通り:
```
public class TreatBeans {

	public static void main(String[] args) {

		UserBean bean1 = new UserBean();
		bean1.setName("Ito Takashi");
		system.out.println(bean1.getName());

		UserBean bean2 = new UserBean();
		Bean2.setName("Okada Kazuko");
		system.out.println(bean2.getName());
	}
}
```

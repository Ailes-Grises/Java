このディレクトリでは，Java の基本文法についてまとめている．

- HttpServlet クラスを使った昔ながらのサーブレット/JSP アプリケーションの作成方法は<a href="https://github.com/Ailes-Grises/Java/tree/master/servlet">こちら</a>
- Spring Boot とGradle を使った開発方法は<a href="https://github.com/Ailes-Grises/Java/tree/master/SpringBoot">こちら</a>

1. [Java の基本](#Basic)
1. [アクセス権](#Permission)
1. [型とデータ構造](#Structure)
	1. [配列](#Array)
	1. [文字列](#String)
1. [コレクションクラス(C++ のSTL)](#Collection)
	1. [リスト(List)](#List)
	1. [セット(Set)](#Set)
	1. [マップ(Map)](#Map)
1. [制御構文](#ControlSyntax)
1. [メソッドについて](#Method)
1. [ファイル入出力](#fileIO)
1. [抽象クラスとインターフェース](#AbstInterface)
	1. [抽象クラス](#Abstruct)
	1. [インターフェース](#Interface)
	1. [抽象クラスとインターフェースの違い](#Abstract_and_Interface)
1. [継承](#Inheritance)
1. [パッケージ](#Package)
1. [ラムダ式](#Lambda)

<a id = "Basic"></a>
# Java の基本
- クラス名とファイル名は一致していなければならない．
- 全てがクラスで管理される．
- import はusing namespace に相当する(include ではない)

<a id = "Permission"></a>
# アクセス権
- 基本的には，static, final, public, private, protected を覚えておけば良い．
- static は，付けると対象のオブジェクトの所属がクラスのインスタンスではなくてクラスそのものになる．C++ における，同一ファイル内でのみアクセス可能といった機能は多分消滅している(完全オブジェクト指向だから)．
- final は，C++ でいうconst である．最初に代入した値を後から変更できなくなるだけで，C++ のconst と変わらない．
- public は，どこからでもアクセス可能とする修飾子．public なクラスやインターフェースは，1つのファイルに1つしか定義できない．他はC++ と変わらない．
- private は，クラスのメンバしかアクセス出来ないようにするキーワード．派生クラスからもアクセス不可．C++ と変わらない．
- protected もC++ と同じ(てか要らない)．

<a id = "Structure"></a>
# 型とデータ構造
- 使用される基本データ型(プリミティブ型というらしい)はC++ とほぼ同じ．
- C++ ではSTL として提供されていた便利なデータ型(vector, map, string 等)が，Java では標準装備になっている．そのため，配列等の一部のデータ構造がクラス化している．
- その他には，従来のプリミティブ型(int とか)を拡張した，クラス型というものがある(Integer 型とか)．
- これが滅茶苦茶便利で，例えば文字列を整数に変換する様なメソッドなどが標準で実装されている．

<a id = "Array"></a>
## 配列
- プロトタイプ宣言の場合には，必ずnew を使って領域を確保する．
- 使い方は以下の通り:
```Java
// 固定配列のプロトタイプ宣言
int arr[] = new int[5];

// 宣言と同時に初期化
int arr[] = {1, 2, 3, 4, 5}

// 従来通りにアクセス可能
System.out.println("arr[0] : " + arr[0]);

// メソッドとして，length() という関数がある．
int num = arr.length();
```

<a id = "String"></a>
## 文字列
- C++ のstd::string に相当するものとして，String というクラス型のデータ構造がある．
- 使い方は以下の通り:
```Java
// プロトタイプ宣言
String str;

// 宣言と同時に初期化
String str = "Hello, hoge!";

// アクセス
System.out.println("str : " + str);

// 連結も可能
String a = "aaa", b = "bbb";
String c = a + b;

// 長さを調べる
System.out.println("length : " + c.length());
```

<a id = "Collection"></a>
# コレクションクラス(C++ のSTL)
- 有名なデータ構造を実現するために提供されるフレームワーク．
- クラスとインターフェースの組み合わせによって実現される．
- 有名なのはList, Set, Map インターフェースを実装したArrayList, HashSet, HashMap クラスの3つ．
- 目的のデータ構造を実現するのはもちろんのこと，便利なメソッドがたくさん実装されている．
- Java の利点を最大限活かすには，これらを積極的に利用するべき．

<a id = "List"></a>
## リスト(List)
- 可変長配列のこと．C++ ではstd::vector だった．
- 任意の型の可変長配列を作成出来るが，リストの要素は参照型である必要があるため，int などのプリミティブ型は使えない(Integer 等のクラス型を使おう)．
- Set と違って要素が追加された順番も守られる．
- 名前空間を指定しないで使うには，java.util.(ArrayList/Iterator/List) などをインポートする必要がある．
- List インターフェースを実装したArrayList クラスを運用している．
- ちなみにList を実装したクラスには他にもLinkedList やStack などがある．
- 使い方は以下の通り:
```Java
import java.util.ArrayList
import java.util.List
import java.util.Iterator

// List<型名> オブジェクト名 = new ArrayList<型名>();
List<String> arr = new ArrayList<String>();

// これでもok (宣言はクラス型でもインターフェースでもどちらでも良い)
ArrayList<String> str = new ArrayList<String>();

// 要素の追加
str.add("hoge");
str.add("hogee");
str.add("hogeee");

// 要素の取得(要素へのアクセス)
str.get(2);

// 要素数の取得
int length = str.size();

// 要素のシーケンス番号の取得
int index = str.indexOf("hoge");

// 要素の削除
str.remove(0);


// ====== おまけ ====== //


// 他のリストをまとめて追加
str.addAll(arr);

// 位置を指定する場合
str.addAll(2, arr);

// 要素を指定してまとめて追加(Arrays.asList() を使ってる)
str.addAll(Arrays.asList('X', 'Y', 'Z'));

// 要素の上書き
str.set(1, "HOGE");

// 要素の有無の確認
if(str.contain("hoge")){
  // 処理
}

// 重複要素の削除
str.distinct("hoge");

// 要素のソート(Collection クラスのsort() を使う)
Collection.sort(str);

// リストの一部のコピー(範囲を指定)
List<String> cpList = str.subList(2, 6);
```
その他には，リストを配列やマップに変換する関数も存在する．

<a id = "Set"></a>
## セット(Set)

<a id = "Map"></a>
## マップ(Map)

<a id = "ControlSyntax"></a>
# 制御構文
- for, while, if, switch に関してはC++ と全く同じ．
- Java では，新たに拡張for文という構文が用意されている．これは，通常のfor文をより簡潔に記述するための書き方だが，要するに他言語のforeach 文や"for ~ in" 構文と同じである．
```Java
String [] arr = {"hoge", "hogee", "hogeee"};

// arr の各要素を一つずつ順番に取り出し，これをtemp に毎回代入する
// 要するにシェルスクリプトの for 変数 in 配列; と同じ
for(String temp : arr){
	System.out.println(temp);
}
```

<a id = "Method"></a>
# メソッドについて
- 完全なるオブジェクト指向言語なので，C言語の様な，どこにも属さないユーザ関数は存在しない．
- そのかわり，static 演算子の付いた関数が実質従来通りのユーザ関数に相当する．
- this ポインタは省略してok っぽい(だが非推奨である)．
- 同一ディレクトリ内のjava コードで定義されている他クラスのメソッドで，なおかつstatic なメソッドならば，"クラス名.メソッド" の形で呼び出せる(インスタンスの参照渡しが不要)
```Java
public class Hoge{
  public static int calc_sum(int X, int Y){
    return X + Y;
  }
  public static void main(String[] args){
    int x = 1, y = 2;
    int sum = this -> calc_sum(x, y);
    System.ou.println("sum : " + sum);

    // 他クラスHoge のstatic メソッドhoge_func() を呼び出す
    Hoge.hoge_func();
  }
}
```

<a id = "fileIO"></a>
# ファイル入出力
色々な方法が存在するが，ここではFileInputStream を用いた方法を紹介する．

参考サイト : https: // java.keicode.com/lang/io - textfile.php
- FileInputStream というファイルIO のためのストリームを用いる
- java.io.FileInputStream をインポートすることで利用可能
- このストリームはクラスなので，コンストラクタにファイルパスを渡せばストリームの生成は完了する
- read() メソッドなどがあり，これらを使えば読み書きは余裕
- 使い方は以下の通り:
```Java
import java.io.FileInputStream;
import java.io.FileNotFoudException;
import java.io.IOException;

try{
  // ストリームの作成
  FileInputStream fin = new FileInputStream("./filename.txt");

  // 一文字ずつ読み込んでch に格納
  while((char ch = fin.read()) != - 1){
    System.out.println("ch : " + ch);
  }
}catch(FileNotFoudException error){
  // エラー処理
  error.printStackTrace();
}catch(IOException errror){
  // エラー処理
  error.printStackTrace();
}
```

- 上記の様に，FileInputStream クラスを使えば何の問題もなくファイルからバイト列を読み出せるが，このクラスには原始的な機能しか備わっていない(C言語のread() みたいな感じ)．
そこで，このクラスをラッピングし，より便利なメソッドを大量に備えたInputStreamReader というクラスを使用するのが定石である．
- 使い方は以下の通り:
```Java
import java.io.*;
import java.util.Arrays;

char buf[] = new char[10];

// FileInputStream をラッピングしてInputStreamReader のインスタンスを生成
InputStreamReader fin = new InputStreamReader(new FileInputStream("./filename.txt"));

// buf の限界まで文字列を読み込み，読み込んだ文字数をsize に返す
while((int size = fin.read(buf)) != - 1){
  System.out.println("buf : " + buf);
  System.out.println("size" + size);
	余ったら'\0' を入れる
  Arrays.fill(buf, '\0');
}
fin.close();
```

<a id = "AbstInterface"></a>
# 抽象クラスとインターフェース
<a id = "Abstruct"></a>
## 抽象クラス
- 抽象メソッド(具体的な処理が書かれていないメソッド)を1つ以上持つクラスのことを抽象クラスという．
- メソッドの実装がされていないため，このクラスのインスタンスは生成出来ないが，このクラスを雛形として継承し，メソッドをオーバーライドすることで，形式に関する統一性を維持しつつも多様性を実現することが出来る．
- 使う際には，"virtual" のようなノリで"abstract" というキーワードを使う．
- 使い方は以下の通り:
```Java
// 抽象クラスHoge の宣言
abstract class Hoge{

  // 抽象メソッドの宣言(C++ ではvirtualだった)
  abstract int hoge_func(int x);
}
```

<a id = "Interface"></a>
## インターフェース
- 具体的な処理内容が書かれていない関数と，定数をまとめて提供するセットの様なもののこと．
- ただメソッドと変数をセットにして提供しているだけだが，別のクラスの内部でインターフェースのメソッドを実装することで，インターフェースをクラスの一部として組み込むことが出来る．
- 実装の際には，キーワード"implements" を使う．
- 定数とメソッドのみを定義できる．
- インターフェースのメンバ変数は，自動的にpublic static final が付く．
- クラス同様に，static メソッドにすると，メソッドの所属はインスタンスではなくインターフェースになる．
- <a href="http://daisuke-m.hatenablog.com/entry/20081214/1229259973">このサイト</a>が非常に参考になる


- 抽象クラスと似ているが，**クラスではない．**
- 抽象クラスでは多重継承(複数の異なるクラスの継承)は出来ないが，インターフェースは1つのクラスに対して何個でも組み込みが可能である(サンプルを見て)．
- 継承という行為自体があまり良い仕組みではないので，継承しなくてもオーバーライド的なことが実現できるのは大きなメリットだと思う．
- そもそも形式の決まったメソッドのセットに名前を付けて統一的に機能を提供したいだけならば，本来継承は必要ないはず(継承という考え方と合っていない)．
- 使い方は以下の通り:
```Java
interface Add{
  int NUM1 = 1;
  int add();
}

interface Sub{
  int NUM2 = 2;
  int sub();
}

// インターフェースを組み込んで実装するクラス
class Calc implement Add, Sub{
  public int add(){
    return NUM1 + NUM2; // NUM1 とNUM2 を両方使っていることに注目
  }

  public int sub(){
    return NUM2 - NUM1;
  }
}

// 上記のクラスを利用するクラス
public class Main{
  public static void main(String[] args){
    Calc calc = new Calc();
    int x = calc.add();
    int y = calc.sub();
  }
}

```

上記がインターフェースの基本的な扱い方だが，以下にdefault メソッドを使う場合とstatic メソッドを使う場合の例を示す．
```Java
interface Calc{
  public int NUM1 = 1;
  public int NUM2 = 2;

  // default メソッド
  default public int calc(){
    return NUM1 + NUM2;
  }
}

interface Hoge{
  // static メソッド
  public static void func_hoge(){
    System.out.println("hogeee");
  }
}

class Add implements Calc{
  // オーバーライドしないので何も書かない
}

public class Main{
  public static void main(String[] argv){

    Add add = new Add();
    // メソッドの呼び出し
    int x = add.calc();

    // static メソッドなので，インターフェースのメソッドを名指しで呼び出す
    int y = Hoge.func_hoge();
  }
}

```

<a id = "Abstract_and_Interface"></a>
# 抽象クラスとインターフェースの違い
どちらも結果的に規格の統一のような状態が実現されるが， **資源の再利用をしたいのか，それともただ規格を統一したいのかが最大の焦点である．**
継承は，既存オブジェクトを資源として丸ごと再利用することが目的である．
既存オブジェクトには関数や変数が既に実装されているため，結果的に親クラスを継承すれば副産物として形の上では規格が統一されて見えるが，それは継承の本当の目的ではない．
また，規格を統一したいだけなのに継承を使ってしまうと，アクセス権も一緒に継承されてしまうため，その派生オブジェクトは扱いにくくなってしまう．

一方，インターフェースの目的は，ただひとえに規格の統一にある．
規格とは， **クラスに絶対に守ってほしいお約束** の事であり，インターフェースはこれをまとめたお品書きのようなものである．
例えば，これから作成する10種類のクラスには必ず特定の名前の関数と決まった値の変数を持っていてほしい，という時，インターフェースを実装すればこの約束が守られる．
この時，インターフェースはやはりただの口約束を紙に書いたようなものなので，インターフェースを実装しても何らかの過去の遺産を再利用することはできないし，アクセス権の引継ぎみたいな面倒な問題も無い．

<a id = "Inheritance"></a>
# 継承
- **継承は悪い文明**
- C++ では特に明示すること無くなんとなく継承していたが，Java では"extends" というキーワードを利用する．
- 多重継承は出来ない．
- 親クラスを「スーパークラス」，子クラスを「サブクラス」という．
- オーバーライドの際，特にキーワードとかは必要ない(C++ ではvirtualだった)．
- もちろんオーバーロードもok．
- 使い方は以下の通り: (まぁ使わないけどな..!)
```Java
// human.java の内容
public class human{
  protected String name;
  protected int age;
  public human();
}

// wizard.java の内容
public class wizard extends human{ // human を継承
  private magic;
  public static void main(String[] argv){

    // 子クラスであるwizard は親クラスhuman を継承しているので，
    // wizard クラスをhuman クラスとみなすことが出来る!...らしいよ．
    human megumin = new wizard(); // <-- メリットがあるのかは不明．

    megumin.name = "Megumin";
    megumin.age = 14;
  }
}
```

<a id = "Package"></a>
# パッケージ
C++ でいう名前空間のこと(ただし，パッケージ名はUNIXのディレクトリの様な階層構造を持てるし，実際にパッケージごとにディレクトリを分けることが可能)
- Java では，public なクラスは一つのファイルに一つしか記述できないため，開発の規模が大きくなってくるとクラスの数が膨大なことになる．
これらを一つのディレクトリだけで管理すると，一切整理されていない状態の開発ディレクトリが出来上がってしまう．
- しかし，実際の開発現場では，これらのクラスは役割(どのモジュールのためのクラスなのか)が決まっていて，役割毎にグループ分けして管理したほうがディレクトリの見通しが良くなる．
そこで，これらのクラスを役割毎にグループ分けし，名前を付けることにする．この名前をパッケージと呼ぶ．
- パッケージは階層構造を持つことが出来る．パッケージによるグループ分けには，スコープ的な意味のグループ分けと，物理的な意味のグループ分け(ディレクトリによる分割)の2つの意味がある．
- クラスをパッケージに所属させるには"package 名前" を，他パッケージのクラスをインポートするには"import パッケージ名.クラス名" を宣言する．
- ディレクトリ構成を無視してソースコードを配置すると，コンパイラが対象ファイルを見つけられなくなるので注意が必要．

```
[ディレクトリ構成]
   ./
   ├── groupA/ ── main.java
   └── groupB/ ── function.java

/* ./groupA/main.java */

 | package groupA // このクラスはgroupA というパッケージに所属する
 | import groupB.*  // パッケージgroupB のクラスを全てインポート
 | 
 | public class main{
 | 	// 色々と書かれている...
 | }

 // コンパイル
 $ javac ./groupA/main.java ./groupB/function.java
 $ java groupA.main

```

コンパイル方法でかなりハマったので，備忘録としてコンパイル方法を書いておく．
まず，上述の様に各ファイルはパッケージに書かれた階層構造を守って配置しないとエラーとなるが，上記の様に2つのディレクトリが並列に配置されている場合，どうやってコンパイルすればいいのか分からなかった．
そこで調査した結果，どうやら**javac を実行する際のコンパイル上のルートディレクトリは，デフォルトではカレントディレクトリになるということが分かった．**
したがって，上記の様に複数のディレクトリが並列に並んでいる場合は，これらのディレクトリの1つ上のディレクトリでコンパイルする必要がある．

特に，サーブレットとJSP でMVC モデルのwebアプリを作成する際に，実行方法で大変困った．
一般的なweb アプリのディレクトリ構成では，```apuri/WEB - INF/classes``` 配下にコードを置くが，パッケージを使うとこのディレクトリの内部が複数に分岐する．
以下にこの時のビルド方法を書いておくので，困ったら参考にすること．
```
 $ cd /opt/tomcat/webapps/apuri/WEB - INF/classes
 $ ls
    servlet/ model/
 $ javac - classpath /opt/tomcat/lib/servlet - api.jar ./servlet/*.java ./model/*.java
```

<a id = "Lambda"></a>
# ラムダ式
- 通常，関数は全てプログラムがメモリにロードされるタイミングで全て生成されるが，ラムダ式形式で書かれた関数(というか処理ロジック)は式が実行されるタイミングでロジックが生成される(new みたいな感じ)
- 関数ではなくただの処理ロジックとしてその場で論理式が評価されるため，ラムダ式の外で宣言された変数も利用可能である(ただしロジック外の変数は基本的にfinal であることに注意)
- ラムダ式で書かれた処理ロジックは，関数オブジェクトとして変数に格納可能
- C言語の関数ポインタ(関数リテラル)の話に近い
- 関数オブジェクトを格納できるのは，格納する処理ロジックの引数と戻り値の型が一致したメソッドを一つだけ持つ抽象インターフェースだけである(これをSAM(Single Abstract Method)インターフェースという)
- 毎回抽象インターフェースを自分で定義するのは怠いため，よく使われそうなSAMインターフェースはjava.util.function.* で提供されている
- 値ではなく処理ロジックそのものを他の関数の引数として渡して動的に処理を変更するために生まれた機能
- ラムダ式や関数オブジェクトは，stream() やparallelStream() メソッドと組み合わせると非常にエレガントに記述できる

```Java
// 関数オブジェクトの基本

// ex1. まずは提供されているSAMインターフェースを利用してみる
import java.util.function.*;

public class main{
  // 適当に関数を定義
  public static int add(int a, int b){
    return a + b;
  }

  public static void main(String[] args){
    // SAMインターフェースであるIntBinaryOperator を使ってみる
    IntBinaryOperator func_ptr = main::add; // セミコロンはつかない
    // 関数を呼び出す時はインターフェースの抽象メソッドを使用する
    int sum = func_ptr.applyAsInt(2, 3); // 今回はapplyAsInt() という名前の抽象メソッドを使用
    System.out.println(sum); // 結果は"5"
  }
}

// ================================================================== //

// ex2. 今度は自分でSAMインターフェースを宣言して関数オブジェクトを扱う

public interface MyFunction{
  // 抽象メソッドは一つだけ!
  public abstract int func(int a, int b);
}

public class main{
  public static int add(int a, int b){
    return a + b;
  }

  public static void main(String[] args){
    Myfunc func_ptr = main::add;
    int sum = func_ptr.func(2, 3); // MyFunction の抽象メソッドを呼び出している
    System.out.println(sum); // 結果は"5"
  }
}

// ====== おまけ ====== //

// ちなみに，ex1 では関数呼び出しにapplyAsInt() というメソッドを呼んでいたが，あれもSAMインターフェースの抽象メソッドである．
public interface IntBinaryOperator{
  public abstract int applyAsInt(int left, int right);
}

```

```Java
// ex1. ラムダ式の基本

//   (引数) -> { 
//     様々な処理;
//     return 戻り値; };
//   }

import java.util.function.*;

public class main{
  public static void main(String[] args){

    // 基本形
    IntBinaryOperator func_ptr = (int a, int b) -> { return a + b; };

    int sum = func_ptr.applyAsInt(5, 3);
    System.out.println(sum); // 結果は"8"
  }
}


// ex2. 省略記法を使ってみる

public class main{
  public static void main(String[] args){

    // 代入分の右辺でラムダ式を使う時には，引数の型を省略してok
    IntToDoubleFunction func = (x) -> { return x * x * 3.14; };

    // 引数が一つしかない時には"()"を省略してok
    IntToDoubleFunction func = x -> { return x * x * 3.14; };

    // ラムダ式が単一のreturn 文の場合，"{}" を省略してok
    IntToDoubleFunction func = x -> return x * x * 3.14;

  }
}



// ex3. 具体例

// 勇者クラスのインスタンスを受け取り，そのHPを返す
(Yuusya obj) -> return obj.getHp();

// 引数なしで，現在日時を返す
() -> return new java.util.Date();

// int 型配列を受け取り，そのコピーを作成し，ソートして返す
(int[] arr) -> {
  int[] arr2 = java.util.Arrays.copyOf(arr, arr.length);
  java.util.Arrays.sort(arr2);
  return arr2;
}

// 関数オブジェクトを受け取り，2回呼び出して合計を返す
(IntBinaryOperator func, int a, int b) -> {
  int result = func.applyAsInt(a, b) + func.applyAsInt(a, b);
  return result;
}

```

```Java
// stream() と組み合わせた処理

// List<Integer> list の各要素を2倍にする
list.stream().foreach( var -> System.out.println(var * 2) );

// List<Character> msg の各要素に対してsleep() を呼び出す(喋らせる事が可能になる);
msg.stream().foreach( moji -> moji.sleep() );

```

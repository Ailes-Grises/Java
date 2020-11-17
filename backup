# Java の基本
- クラス名とファイル名は一致していなければならない．
- 全てがクラスで管理される．
- import はusing namespace に相当する(include ではない)

# アクセス権
- 基本的には，static, final, public, private, protected を覚えておけば良い．
- static は，付けると対象のオブジェクトの所属がクラスのインスタンスではなくてクラスそのものになる．C++ における，同一ファイル内でのみアクセス可能といった機能は多分消滅している(完全オブジェクト指向だから)．
- final は，C++ でいうconst である．最初に代入した値を後から変更できなくなるだけで，C++ のconst と変わらない．
- public は，どこからでもアクセス可能とする修飾子．public なクラスやインターフェースは，1つのファイルに1つしか定義できない．他はC++ と変わらない．
- private は，クラスのメンバしかアクセス出来ないようにするキーワード．派生クラスからもアクセス不可．C++ と変わらない．
- protected もC++ と同じ(てか要らない)．


# 型とデータ構造
- 使用される基本データ型(プリミティブ型というらしい)はC++ とほぼ同じ．
- C++ ではSTL として提供されていた便利なデータ型(vector, map, string 等)が，Java では標準装備になっている．そのため，配列等の一部のデータ構造がクラス化している．
- その他には，従来のプリミティブ型(int とか)を拡張した，クラス型というものがある(Integer 型とか)．
- これが滅茶苦茶便利で，例えば文字列を整数に変換する様なメソッドなどが標準で実装されている．

## 配列
- プロトタイプ宣言の場合には，必ずnew を使って領域を確保する．
- 使い方は以下の通り:
```
// 固定配列のプロトタイプ宣言
int arr[] = new int[5];

// 宣言と同時に初期化
int arr[] = {1, 2, 3, 4, 5}

// 従来通りにアクセス可能
System.out.println("arr[0] : " + arr[0]);

// メソッドとして，length() という関数がある．
int num = arr.length();
```

## 文字列
- C++ のstd::string に相当するものとして，String というクラス型のデータ構造がある．
- 使い方は以下の通り:
```
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


# コレクションクラス(C++ のSTL)
- 有名なデータ構造を実現するために提供されるフレームワーク．
- クラスとインターフェースの組み合わせによって実現される．
- 有名なのはList, Set, Map の3つ．
- 目的のデータ構造を実現するのはもちろんのこと，便利なメソッドがたくさん実装されている．
- Java の利点を最大限活かすには，これらを積極的に利用するべき．

## リスト(List)
- 可変長配列のこと．C++ ではstd::vector だった．
- 任意の型の可変長配列を作成出来るが，リストの要素は参照型である必要があるため，int などのプリミティブ型は使えない(Integer 等のクラス型を使おう)．
- Set と違って要素が追加された順番も守られる．
- 名前空間を指定しないで使うには，java.util.(ArrayList/Iterator/List) などをインポートする必要がある．
- List インターフェースを実装したArrayList クラスを運用している．
- ちなみにList を実装したクラスには他にもLinkedList やStack などがある．
- 使い方は以下の通り:
```
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

// 他のリストをまとめて追加
str.addAll(arr);

// 位置を指定する場合
str.addAll(2, arr);

// 要素を指定してまとめて追加(Arrays.asList() を使ってる)
str.addAll(Arrays.asList('X', 'Y', 'Z'));


// 要素の上書き
str.set(1, "HOGE");

// 要素の取得(要素へのアクセス)
str.get(2);

// 要素数の取得
int length = str.size();

// 要素の位置の取得
int index = str.indexOf("hoge");

// 要素の有無の確認
if(str.contain("hoge")){
  // 処理
}

// 要素の削除
str.remove(0);

// 重複要素の削除
str.distinct("hoge");

// 要素のソート(Collection クラスのsort() を使う)
Collection.sort(str);

// リストの一部のコピー(範囲を指定)
List<String> cpList = str.subList(2, 6);
```
その他には，リストを配列やマップに変換する関数も存在する．

## セット(Set)

## マップ(Map)


# 制御構文
- for, while, if, switch に関してはC++ と全く同じ．


# メソッドについて
- 完全なるオブジェクト指向言語なので，C言語の様な，どこにも属さないユーザ関数は存在しない．
- そのかわり，static 演算子の付いた関数が実質従来通りのユーザ関数に相当する．
- this ポインタは省略してok っぽい．
- 同一ディレクトリ内のjava コードで定義されている他クラスのメソッドで，なおかつstatic なメソッドならば，"クラス名.メソッド" の形で呼び出せる(インスタンスの参照渡しが不要)
```
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

# 抽象クラスとインターフェース
## 抽象クラス
- 抽象メソッド(具体的な処理が書かれていないメソッド)を1つ以上持つクラスのことを抽象クラスという．
- メソッドの実装がされていないため，このクラスのインスタンスは生成出来ないが，このクラスを雛形として継承し，メソッドをオーバーライドすることで，形式に関する統一性を維持しつつも多様性を実現することが出来る．
- 使う際には，"virtual" のようなノリで"abstract" というキーワードを使う．
- 使い方は以下の通り:
```
// 抽象クラスHoge の宣言
abstract class Hoge{

  // 抽象メソッドの宣言(C++ ではvirtualだった)
  abstract int hoge_func(int x);
}
```

## インターフェース
- 具体的な処理内容が書かれていない関数と，定数をまとめて提供するセットの様なもののこと．
- ただメソッドと変数をセットにして提供しているだけだが，別のクラスの内部でインターフェースのメソッドを実装することで，インターフェースをクラスの一部として組み込むことが出来る．
- 実装の際には，キーワード"implements" を使う．
- 定数とメソッドのみを定義できる．
- インターフェースのメンバ変数は，自動的にpublic static final が付く．
- クラス同様に，static メソッドにすると，メソッドの所属はインスタンスではなくインターフェースになる．


- 抽象クラスと似ているが，**クラスではない．**
- 抽象クラスでは多重継承(複数の異なるクラスの継承)は出来ないが，インターフェースは1つのクラスに対して何個でも組み込みが可能である(サンプルを見て)．
- 継承という行為自体があまり良い仕組みではないので，継承しなくてもオーバーライド的なことが実現できるのは大きなメリットだと思う．
- そもそも形式の決まったメソッドのセットに名前を付けて統一的に機能を提供したいだけならば，本来継承は必要ないはず(継承という考え方と合っていない)．
- 使い方は以下の通り:
```
interface Add{
  int NUM1 = 1;
  int add();
}

interface Sub{
  in NUM2 = 2;
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
```
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

# 継承
- **継承は悪い文明**
- C++ では特に明示すること無くなんとなく継承していたが，Java では"extends" というキーワードを利用する．
- 多重継承は出来ない．
- 親クラスを「スーパークラス」，子クラスを「サブクラス」という．
- オーバーライドの際，特にキーワードとかは必要ない(C++ ではvirtualだった)．
- もちろんオーバーロードもok．
- 使い方は以下の通り: (まぁ使わないけどな..!)
```
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

# パッケージ
C++ でいう名前空間のこと(ただし，パッケージ名はUNIXのディレクトリの様な階層構造を持てるし，実際にパッケージごとにディレクトリを分けることが可能)
- Java では，public なクラスは一つのファイルに一つしか記述できないため，開発の規模が大きくなってくるとクラスの数が膨大なことになる．
これらを一つのディレクトリだけで管理すると，一切整理されていない状態の開発ディレクトリが出来上がってしまう．
- しかし，実際の開発現場では，これらのクラスは役割(どのモジュールのためのクラスなのか)が決まっていて，役割毎にグループ分けして管理したほうがディレクトリの見通しが良くなる．
そこで，これらのクラスを役割毎にグループ分けし，名前を付けることにする．この名前をパッケージと呼ぶ．
- パッケージは階層構造を持つことが出来る．パッケージによるグループ分けには，スコープ的な意味のグループ分けと，物理的な意味のグループ分け(ディレクトリによる分割)の2つの意味がある．
- ディレクトリ構成を無視してソースコードを配置すると，コンパイラが対象ファイルを見つけられなくなるので注意が必要．
- クラスをパッケージに所属させるには"package 名前" を，他パッケージのクラスをインポートするには"import 名前" を宣言する．
```
package groupA // このクラスはgroupA というパッケージに所属する
import groupB  // groupB をパッケージごとインポートする

public class hoge{
}
```


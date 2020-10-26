# Java の基本
- クラス名とファイル名は一致していなければならない．
- 全てがクラスで管理される．
- import はusing namespace に相当する(include ではない)

# アクセス権
- Java にはバカみたいにたくさんのアクセス権があり，それをかなり厳密に指定している．
- 基本的には，static, final, public, private, protected を覚えておけば良い．
- static は，付けると対象のオブジェクトの所属がクラスのインスタンスではなくてクラスそのものになる．C++ における，同一ファイル内でのみアクセス可能といった機能は多分消滅している(完全オブジェクト指向だから)．
- final は，C++ でいうconst である．最初に代入した値を後から変更できなくなるだけで，C++ のconst と変わらない．
- public は，どこからでもアクセス可能とする修飾子．public なクラスやインターフェースは，1つのファイルに1つしか定義できない．他はC++ と変わらない．
- private は，クラスのメンバしかアクセス出来ないようにするキーワード．派生クラスからもアクセス不可．C++ と変わらない．
- protected もC++ と同じ(てか要らない)．


# 型とデータ構造
- 使用される基本データ型(プリミティブ型というらしい)はC++ とほぼ同じ．
- C++ ではSTL として提供されていた便利なデータ型(vector, map, string 等)が，Java では標準装備になっている．
- そのため，配列等の一部のデータ構造がクラス化している．
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
- 完全なるオブジェクト指向言語なので，C言語の様などこにも属さないユーザ関数は存在しない．
- そのかわり，main() が定義されているクラスのメソッドや，static 演算子の付いた関数が実質従来通りのユーザ関数に相当する．
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

# インターフェース

# 継承
- **継承は悪い文明**

# サーブレット/JSP

# Spring フレームワーク


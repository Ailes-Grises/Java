# Java の基本
- クラス名とファイル名は一致していなければならない．
- 全てがクラスで管理される．
- import はusing namespace に相当する(include ではない)

# アクセス権
- Java にはバカみたいにたくさんのアクセス権があり，それをかなり厳密に指定している．
- static, const, public, private
- スコープについても書く


# データ構造
- 使用される基本データ型はC++ とほぼ同じ．
- C++ ではSTL として提供されていた便利なデータ型(vector, map, string 等)が，Java では標準装備になっている．
- そのため，配列等の一部のデータ構造がクラス化している．

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

## リスト

## マップ


# 制御構文
- for, while, if, switch に関してはC++ と全く同じ．


# メソッドについて
- 完全なるオブジェクト指向言語なので，C言語の様などこにも属さないユーザ関数は存在しない．
- そのかわり，main() が定義されているクラスのメソッドが，実質従来通りのユーザ関数に相当する．
- this ポインタは省略してok っぽい．
```
public class Hoge{
  public static int calc_sum(int X, int Y){
    return X + Y;
  }
  public static void main(String[] args){
    int x = 1, y = 2;
    int sum = this -> calc_sum(x, y);
    System.ou.println("sum : " + sum);
  }
}
```

# インターフェース

# 継承
- **継承は悪い文明**


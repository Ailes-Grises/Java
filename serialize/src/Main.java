// package com.company;

import java.io.*;

/**
 * メインクラス
 */
public class Main {
    /**
     * メインメソッド
     *
     * @param args コマンドライン引数
     */
    public static void main(String args[]) {
        String fileName = "lang.txt";

        // Languageクラスをインスタンス化
        Language language = new Language("Java", "Static");
        try {
            Main.Serialize(language, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Language deSirializedLanguage = Main.DeSerialize(fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("デシリアライズされた、Languageの名前は" + language.getName() + "タイプは" + language.getType());
    }

    /**
     * オブジェクトのシリアライズを行う
     *
     * @param language Languageクラスのインスタンス
     * @param fileName ファイル名
     * @throws IOException IO例外
     */
    private static void Serialize(Language language, String fileName) throws IOException {
        // わかりやすくするために冗長に書いている
        // ファイルを指定して、FileOutputStreamを生成
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        // OutputStreamに基本データ型とオブジェクトのグラフをOutputStreamに書き込む
        // 今回は、FileOutputStreamに書き込む
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        // 特定のクラスのオブジェクトの状態をストリームに書き込む
        objectOutputStream.writeObject(language);
        objectOutputStream.flush();

        objectOutputStream.close();
    }

    /**
     * オブジェクトのデシリアライズを行う
     *
     * @return Languageクラスのインスタンス
     */
    private static Language DeSerialize(String fileName) throws IOException, ClassNotFoundException {
        // わかりやすくするために冗長に書いている
        // ファイルを指定して、FileInputStreamを生成
        FileInputStream fileInputStream = new FileInputStream(fileName);
        // 事前にObjectOutputStreamを使って作成されたプリミティブ・データとプリミティブ・オブジェクトを直列化復元する
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        // 対応するwriteObjectメソッドによってストリームに書き込まれたデータを使用する特定のクラスのオブジェクトの状態を読み込み、復元する
        Language language = (Language) objectInputStream.readObject();

        objectInputStream.close();

        return language;
    }
}

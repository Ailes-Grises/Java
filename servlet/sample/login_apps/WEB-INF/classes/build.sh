#!/usr/bin/bash

# javac は，「起点となるディレクトリ(ルートディレクトリ)がどこか」というのを，デフォルトではjavac を実行したディレクトリとするっぽい．
# よって，この位置で，なおかつ関連する全てのjava ファイルを引数に指定してコンパイルしないといけない．

set -e

javac -classpath /opt/apache-tomcat-9.0.37/lib/servlet-api.jar servlet/controller.java model/*.java

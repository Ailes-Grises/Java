#!/usr/bin/zsh

set -e

if [ $# -ne 1 ]; then
	echo -e "Please type title of source code. \n"
	exit 1
fi

javac $1.java
java $1

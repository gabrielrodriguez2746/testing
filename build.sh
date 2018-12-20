#!/bin/sh

./gradlew :app:assembleDebug --no-daemon --build-cache -x lint -Dorg.gradle.jvmargs="-Xmx3g -XX:MaxPermSize=4g -XX:+HeapDumpOnOutOfMemoryError -Dfile.encoding=UTF-8"
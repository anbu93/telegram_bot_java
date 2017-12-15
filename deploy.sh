#!/usr/bin/env bash
MESSAGE=$1

./gradlew incrementVersion
VERSION="$(./gradlew printVersion | cut_line.sh 2)"
./gradlew jar
git add .
git commit -m "$VERSION: $MESSAGE"
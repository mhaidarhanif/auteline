#! /bin/bash

# Allure Framework
temp=$(mktemp)

wget -O $temp https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/2.9.0/allure-commandline-2.9.0.zip

rm -rf ./allure
mkdir ./allure

unzip $temp -d ./allure

pushd ./allure

mv $(ls)/* .

popd

# Bazel
wget -O ./bazel https://github.com/bazelbuild/bazelisk/releases/latest/download/bazelisk-linux-amd64

chmod +x ./bazel


# lcov
rm -rf lcov
mkdir ./lcov
wget -O ./lcov/download.tar.gz https://github.com/linux-test-project/lcov/releases/latest/download/lcov-1.16.tar.gz
pushd ./lcov
tar -xvf ./download.tar.gz
rm ./download.tar.gz
mv $(ls)/* .
popd
#!/bin/sh

# @file run.sh
# @brief Run the container

if ! command -v scala &> /dev/null
then
    if ! command -v scala3 &> /dev/null
    then
        echo "Scala not found. Please install Scala and make sure it is in your PATH."
        exit 1
    fi

    scala_exec=scala3
    scalac_exec=scalac3
else
    scala_exec=scala
    scalac_exec=scalac
fi

if [ ! -d "./.build" ]; then
    mkdir .build
else
    rm -rf .build/*
fi

cd .build
$scalac_exec ../src/*.scala
$scala_exec -cp . project.Main
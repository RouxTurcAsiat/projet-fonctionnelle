#!/bin/sh

BUILD=./.build

if [ -z "$SCALA_PATH" ]; then
    if command -v scala >/dev/null 2>&1; then
        SCALA=scala
        SCALAC=scalac
    else
        if command -v scala3 >/dev/null 2>&1; then
            SCALA=scala3
            SCALAC=scalac3
        else
            echo "Scala is not installed"
            exit 1
        fi
    fi
else
    SCALA=$SCALA_PATH/scala 
    SCALAC=$SCALA_PATH/scalac 
fi


if [ ! -d "$BUILD" ]; then
    mkdir "$BUILD"
fi

$SCALAC -Werror -unchecked -d $BUILD -indent -rewrite -explain ./project/*.scala ./tests/*.scala
test_list=$(find ./.build/tests/ -name "*.class" | cut -d "/" -f 4 | cut -d "." -f 1 | grep -Fv "$" | grep -v "TestFramework")

if [ -z "$1" ]; then
    $SCALA -classpath $BUILD project.Main
elif [ "$1" = "list-test" ]; then 
    echo "Available tests: "
    echo $test_list | xargs -n 1 echo "* "
elif [ "$1" = "all" ]; then 
    for test in $test_list; do
        $SCALA -classpath $BUILD tests.$test
        echo ""
    done
elif [ -f "./.build/tests/$1.class" ]; then
    $SCALA -classpath $BUILD tests.$1
else 
    echo "Unknown command $1"
    exit 1
fi
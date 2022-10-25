.SUFFIXES:
.DEFAULT_GOAL := all

BUILD = ./.build
MKCWD = @mkdir -p $(@D)

ifeq (, $(shell command -v scala))
	ifeq (, $(shell command -v scala3))
		$(error "Scala not found. Please install scala")
	else
		SCALAC=scalac3
		SCALA=scala3
	endif
else 
	SCALAC=scalac
	SCALA=scala
endif

SCALAC_FLAGS := -Werror -unchecked -classpath $(BUILD) -d $(BUILD) -indent -rewrite

PROJECT_SRC = $(wildcard project/*.scala)
TEST_FRAMEWORK = tests/TestFramework.scala
TESTS_SRC = $(filter-out tests/TestFramework.scala, $(wildcard tests/*.scala))

PROJECT_CLASS = $(patsubst %.scala, $(BUILD)/%.class, $(PROJECT_SRC))
TESTS_CLASS = $(patsubst %.scala, $(BUILD)/%.class, $(TESTS_SRC))
TEST_FRAMEWORK_CLASS = ./.build/tests/TestFramework.class

$(BUILD)/%.class: %.scala
	@$(MKCWD)
	$(SCALAC) $(SCALAC_FLAGS) $< 

build_test: $(TEST_FRAMEWORK_CLASS)
	$(MAKE) $(TESTS_CLASS)

.phony: all
all: $(PROJECT_CLASS) build_test

.phony: clean
clean:
	rm -r ./.build

.phony: list-test
list-test: $(TESTS_SRC)
	echo $(TESTS_CLASS)
	@find $(BUILD)/test/ -name "*.class" | cut -d "/" -f 4 | cut -d "." -f 1 | grep -Fv "$$" | grep -v "TestFramework"

.phony: run
run: $(PROJECT_CLASS)
	@$(SCALA) -classpath $(BUILD) -cp . project.Main
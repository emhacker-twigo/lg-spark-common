SBT:=sbt
JAR:=spark-common-assembly-0.1.0-SNAPSHOT.jar
SCALA_VERSION:=scala-2.12
OUT:=out
SRC_FILES=$(shell find src -name "*.scala")

.PHONY: build

print:
	@echo $(SRC_FILES)

install:
	$(SBT) publishLocal

clean:
	$(SBT) clean

target/$(SCALA_VERSION)/$(JAR): $(SRC_FILES)
	$(SBT) assembly -batch

build: target/$(SCALA_VERSION)/$(JAR)

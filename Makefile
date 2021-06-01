all: run

clean:
	rm -f out/Main.jar out/Vernam.jar

out/Main.jar: out/parcs.jar src/Main.java src/Vernam.java
	@javac -cp out/parcs.jar src/Main.java src/Vernam.java
	@jar cf out/Main.jar -C src Main.class -C src Vernam.class
	@rm -f src/Main.class src/Vernam.class

out/Vernam.jar: out/parcs.jar src/Vernam.java
	@javac -cp out/parcs.jar src/Vernam.java
	@jar cf out/Vernam.jar -C src Vernam.class
	@rm -f src/Vernam.class

build: out/Main.jar out/Vernam.jar

run: out/Main.jar out/Vernam.jar
	@cd out && java -cp 'parcs.jar:Main.jar' Main
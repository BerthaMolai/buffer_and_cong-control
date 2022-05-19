JAVAC=/usr/bin/javac
.SUFFIXES: .java .class

SRCDIR=src
BINDIR=bin
DOCDIR=docs

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES=Router.class Packet.class Graph.class myProtocol.class

CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

run: $(CLASS_FILES)
	java -cp $(BINDIR) myProtocol

docs:
	javadoc -d $(DOCDIR) $(SRCDIR)/%.java
clean:
	rm $(BINDIR)/*.class

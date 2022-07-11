NAME = JavaGames

SRC = Cave.java Game.java GuessTheNumber.java

UNITSRC = TestProj.java

UNITJAR = -cp .:/usr/share/java/junit-4.13.2.jar

COMP = javac

all: $(NAME)

$(NAME):
	$(COMP) $(SRC) GameMaster.java

unit:
	$(COMP) $(UNITJAR) $(SRC) $(UNITSRC)

test: all
	java GameMaster

unittest: unit
	java TestProj

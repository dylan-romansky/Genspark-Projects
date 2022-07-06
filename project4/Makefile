NAME = JavaGames

SRC = Cave.java Game.java GameMaster.java GuessTheNumber.java

UNITSRC = TestProj.java

UNITJAR = -cp .:/usr/share/java/junit-4.13.2.jar

COMP = javac

all: $(NAME)

$(NAME):
	$(COMP) $(SRC)

unit:
	$(COMP) $(UNITJAR) $(SRC) $(UNITSRC)

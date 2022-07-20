public class HvG {
    StateController gamestate;
    HvG()   {
        gamestate = new StateController(20, 20);
    }
    public static void main(String[] args) {
        HvG game = new HvG();
        game.gamestate.gameloop();
    }
}

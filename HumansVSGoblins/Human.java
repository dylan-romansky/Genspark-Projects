public class Human extends Humanoid {
    Inventory inventory;
    Human() {//state controller will handle an invalid human character
    }
    Human(Coordinates pos){
        super(pos);
        _health = 100;
        _attack = 10;
        wep = new Sword();
        inventory = new Inventory();
    }
    public void updateCoords(char dir)  {
        switch(dir) { //state controller keeps this in bounds
            case /* up */:
                coords.setY(coords.getY() - 1);
                break;
            case /* down */:
                coords.setY(coords.getY() + 1);
                break;
            case /* left */:
                coords.setX(coords.getX() - 1);
                break;
            case /* right */:
                coords.setX(coords.getX() + 1);
                break;
        }
    }
    public Inv getInv(){
        return inventory;
    }
}
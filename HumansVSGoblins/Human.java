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
            case 'w':
            case'W':
                coords.setY(coords.getY() - 1);
                break;
            case 's':
            case'S':
                coords.setY(coords.getY() + 1);
                break;
            case 'a':
            case 'A':
                coords.setX(coords.getX() - 1);
                break;
            case 'd':
            case 'D':
                coords.setX(coords.getX() + 1);
                break;
        }
    }
    public Inventory getInv(){
        return inventory;
    }
}
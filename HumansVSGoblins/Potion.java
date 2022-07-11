public class Potion extends Consumable {
    Potion()   {
        dmgModifier = 35;
    }
    String toString()   {
        return "Potion is consumed, healing 35 damage";
    }
}
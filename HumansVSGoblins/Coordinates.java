public class Coordinates    {
    private int _x;
    private int _y;

    coordinates()   {
        _x = 0;
        _y = 0;
    }
    coordinates(int x, int y)   {
        _x = x;
        _y = y;
    }
    int getX()  {
        return _x;
    }
    int getY()  {
        return _y;
    }
}
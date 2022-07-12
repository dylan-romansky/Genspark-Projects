public class Coordinates    {
    private int _x;
    private int _y;

    Coordinates()   {
        _x = 0;
        _y = 0;
    }
    Coordinates(int x, int y)   {
        _x = x;
        _y = y;
    }
    int getX()  {
        return _x;
    }
    int getY()  {
        return _y;
    }
    void setX(int x)  {
        _x = x;
    }
    void setY(int y)    {
        _y = y;
    }
}
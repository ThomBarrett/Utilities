package src;

import java.lang.reflect.Array;

/**
 * Wraps up the creation of 2D Generic Arrays
 * @param <T> Type of 2D Array
 */
public class SmartGrid<T> {

    /**
     * The length of the array
     */
    private int sizeX;

    /**
     * The Height of the array
     */
    private int sizeY;

    /**
     * The array being wrapped up
     */
    private T[][] array;

    /**
     * Creates an empty 2D array of specified type
     * @param type The type of array
     * @param sizeX The Array Length
     * @param sizeY The Array Height
     */
    public SmartGrid(Class<T> type, int sizeX, int sizeY){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        array = (T[][]) Array.newInstance(type, sizeY, sizeX);
    }

    /**
     * Creates populated array 2D array of specified type with specific population data
     * @param type The type of array
     * @param sizeX The Array Length
     * @param sizeY The Array Height
     * @param populate The data to populate each cell with
     */
    public SmartGrid(Class<T> type, int sizeX, int sizeY, T populate){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        array = (T[][])Array.newInstance(type, sizeY, sizeX);

        for (int y = 0; y < sizeY; y++) {

            for (int x = 0; x < sizeX; x++) {
                array[y][x] = populate;
            }
        }
    }

    /**
     * Writes the array out to the console
     */
    public void displayGrid(){
        for (int y = 0; y < sizeY; y++) {

            for (int x = 0; x < sizeX; x++) {
                if(!isPositionEmpty(x,y)){
                    displayCell(x,y);
                }else{
                    System.out.print("\u25A1");
                }

            }
            System.out.println();
        }
    }

    /**
     * Writes a cell out to the console
     * @param x The X position of the Cell
     * @param y The Y position of the Cell
     */
    public void displayCell(int x, int y){
        System.out.print(array[y][x].toString());
    }

    /**
     * Gets the value of a specific cell
     * @param x The X position of the Cell
     * @param y The Y position of the Cell
     * @return The Value of the Cell
     */
    public T getPositionValue(int x, int y){
        return array[y][x];
    }

    /**
     * Set the value of a specific cell
     * @param x The X position of the Cell
     * @param y The Y position of the Cell
     * @param value The Value to set the Cell
     */
    public void setPositionValue(int x, int y, T value){
        array[y][x] = value;
    }

    /**
     * Finds out if specific cell is empty
     * @param x The X position of the Cell
     * @param y The Y position of the Cell
     * @return True if cell is empty False if cell is not
     */
    public boolean isPositionEmpty(int x, int y){
        if(getPositionValue(x,y) == null){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Gets the length
     * @return sizeX
     */
    public int getSizeX() {
        return sizeX;
    }

    /**
     * Sets the length
     * @param sizeX The new size
     */
    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    /**
     * Gets the height
     * @return sizeY
     */
    public int getSizeY() {
        return sizeY;
    }

    /**
     * Sets the height
     * @param sizeY The new size
     */
    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    /**
     * Gets the wrapped up array
     * @return array
     */
    public T[][] getArray() {
        return array;
    }

    /**
     * Sets the 2D array to new 2D array of same type
     * @param array
     */
    public void setArray(T[][] array) {
        this.array = array;
    }
}

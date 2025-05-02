package src;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Iterator;

/**
 * Wraps up the creation of 2D Generic Arrays
 * @param <T> Type of 2D Array
 */
public class SmartGrid<T> implements Serializable, Iterable<T> {

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
    public SmartGrid(Class<T> type, int sizeX, int sizeY) {
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
    public SmartGrid(Class<T> type, int sizeX, int sizeY, T populate) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        array = (T[][]) Array.newInstance(type, sizeY, sizeX);

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                array[y][x] = populate;
            }
        }
    }

    /**
     * Writes the array out to the console
     */
    public void displayGrid() {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                if (!isPositionEmpty(x, y)) {
                    displayCell(x, y);
                } else {
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
    public void displayCell(int x, int y) {
        System.out.print(array[y][x].toString());
    }

    /**
     * Gets the value of a specific cell
     * @param x The X position of the Cell
     * @param y The Y position of the Cell
     * @return The Value of the Cell
     */
    public T getPositionValue(int x, int y) {
        checkBounds(x, y);
        return array[y][x];
    }

    /**
     * Set the value of a specific cell
     * @param x The X position of the Cell
     * @param y The Y position of the Cell
     * @param value The Value to set the Cell
     */
    public void setPositionValue(int x, int y, T value) {
        checkBounds(x, y);
        array[y][x] = value;
    }

    /**
     * Finds out if specific cell is empty
     * @param x The X position of the Cell
     * @param y The Y position of the Cell
     * @return True if cell is empty False if cell is not
     */
    public boolean isPositionEmpty(int x, int y) {
        checkBounds(x, y);
        return array[y][x] == null;
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
     * @param array The new 2D array
     */
    public void setArray(T[][] array) {
        this.array = array;
    }

    /**
     * Fills the entire grid with a specified value
     * @param value The value to fill the grid with
     */
    public void fill(T value) {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                array[y][x] = value;
            }
        }
    }

    /**
     * Clears the grid (sets all cells to null)
     */
    public void clear() {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                array[y][x] = null;
            }
        }
    }

    /**
     * Returns a string representation of the grid
     * @return A string representation of the grid
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                sb.append(array[y][x] == null ? "□" : array[y][x].toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Rotates the grid 90 degrees clockwise
     */
    public void rotateClockwise() {
        T[][] rotated = (T[][]) Array.newInstance(array[0][0].getClass(), sizeX, sizeY);
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                rotated[x][sizeY - 1 - y] = array[y][x];
            }
        }
        array = rotated;
        int temp = sizeX;
        sizeX = sizeY;
        sizeY = temp;
    }

    /**
     * Rotates the grid 90 degrees counter-clockwise
     */
    public void rotateCounterClockwise() {
        T[][] rotated = (T[][]) Array.newInstance(array[0][0].getClass(), sizeX, sizeY);
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                rotated[sizeX - 1 - x][y] = array[y][x];
            }
        }
        array = rotated;
        int temp = sizeX;
        sizeX = sizeY;
        sizeY = temp;
    }

    /**
     * Transposes the grid (swaps rows and columns)
     */
    public void transpose() {
        T[][] transposed = (T[][]) Array.newInstance(array[0][0].getClass(), sizeY, sizeX);
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                transposed[x][y] = array[y][x];
            }
        }
        array = transposed;
        int temp = sizeX;
        sizeX = sizeY;
        sizeY = temp;
    }

    /**
     * Resizes the grid to new dimensions
     * @param newSizeX New length of the grid
     * @param newSizeY New height of the grid
     */
    public void resize(int newSizeX, int newSizeY) {
        T[][] resized = (T[][]) Array.newInstance(array[0][0].getClass(), newSizeY, newSizeX);
        for (int y = 0; y < Math.min(sizeY, newSizeY); y++) {
            for (int x = 0; x < Math.min(sizeX, newSizeX); x++) {
                resized[y][x] = array[y][x];
            }
        }
        sizeX = newSizeX;
        sizeY = newSizeY;
        array = resized;
    }

    /**
     * Checks whether a position is within bounds of the grid
     * @param x X position of the cell
     * @param y Y position of the cell
     */
    private void checkBounds(int x, int y) {
        if (x < 0 || x >= sizeX || y < 0 || y >= sizeY) {
            throw new IndexOutOfBoundsException("Position (" + x + ", " + y + ") is out of bounds.");
        }
    }

    /**
     * Returns an iterator for iterating over the grid
     * @return An iterator for the grid
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int x = 0, y = 0;

            @Override
            public boolean hasNext() {
                return y < sizeY;
            }

            @Override
            public T next() {
                T value = array[y][x];
                if (++x == sizeX) {
                    x = 0;
                    y++;
                }
                return value;
            }
        };
    }

    /**
     * Export the grid to a CSV format
     * @return A CSV representation of the grid
     */
    public String exportToCsv() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                sb.append(array[y][x] == null ? "" : array[y][x].toString());
                if (x < sizeX - 1) sb.append(",");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

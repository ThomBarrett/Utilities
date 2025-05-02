package src;

import java.lang.reflect.Array;
import java.util.function.Supplier;

/**
 * A generic 2D grid structure that wraps around a two-dimensional array.
 * Supports storing any object type, and provides utility methods for
 * accessing, modifying, and displaying grid data.
 *
 * <p>Note: When initializing the grid using a single populate value, all cells
 * will reference the same object. This is safe for immutable types (e.g. Integer,
 * Color), but can cause side effects with mutable types (e.g. lists, custom objects).</p>
 *
 * @param <T> the type of element stored in the grid
 */
public class SmartGrid<T> {

    /** The number of columns (width) of the grid */
    private int sizeX;

    /** The number of rows (height) of the grid */
    private int sizeY;

    /** The internal 2D array used to store data */
    private T[][] array;

    /**
     * Constructs an empty 2D grid of the specified type and dimensions.
     *
     * @param type the class type of the grid elements
     * @param sizeX the number of columns (width)
     * @param sizeY the number of rows (height)
     */
    public SmartGrid(Class<T> type, int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        array = (T[][]) Array.newInstance(type, sizeY, sizeX);
    }

    /**
     * Constructs a 2D grid where each cell is initialized with the same instance.
     *
     * <p>⚠ This will assign the same reference to each cell. Use only with
     * immutable types to avoid unintended side effects.</p>
     *
     * @param type the class type of the grid elements
     * @param sizeX the number of columns (width)
     * @param sizeY the number of rows (height)
     * @param populate the object to fill every cell with (shared reference)
     */
    public SmartGrid(Class<T> type, int sizeX, int sizeY, T populate) {
        this(type, sizeX, sizeY);
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                array[y][x] = populate;
            }
        }
    }

    /**
     * Creates a grid where each cell is initialized with a new object provided by a supplier.
     *
     * @param type the class type of the grid elements
     * @param sizeX the number of columns (width)
     * @param sizeY the number of rows (height)
     * @param supplier a function that generates a new instance for each cell
     * @param <T> the type of elements in the grid
     * @return a new SmartGrid instance with per-cell object instantiation
     */
    public static <T> SmartGrid<T> filledWith(Class<T> type, int sizeX, int sizeY, Supplier<T> supplier) {
        SmartGrid<T> grid = new SmartGrid<>(type, sizeX, sizeY);
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                grid.setPositionValue(x, y, supplier.get());
            }
        }
        return grid;
    }

    /**
     * Displays the grid to the console, printing each cell.
     * Empty (null) cells are represented by a hollow square (□).
     */
    public void displayGrid() {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                if (!isPositionEmpty(x, y)) {
                    displayCell(x, y);
                } else {
                    System.out.print("\u25A1"); // Unicode hollow square
                }
            }
            System.out.println();
        }
    }

    /**
     * Displays a single cell in the console by calling its toString method.
     *
     * @param x the x-coordinate (column index)
     * @param y the y-coordinate (row index)
     */
    public void displayCell(int x, int y) {
        System.out.print(array[y][x].toString());
    }

    /**
     * Retrieves the value stored at the specified position.
     *
     * @param x the x-coordinate (column index)
     * @param y the y-coordinate (row index)
     * @return the value at the specified cell, or null if empty
     */
    public T getPositionValue(int x, int y) {
        return array[y][x];
    }

    /**
     * Sets the value of a specific cell in the grid.
     *
     * @param x the x-coordinate (column index)
     * @param y the y-coordinate (row index)
     * @param value the value to assign to the cell
     */
    public void setPositionValue(int x, int y, T value) {
        array[y][x] = value;
    }

    /**
     * Checks whether the specified cell is empty (null).
     *
     * @param x the x-coordinate (column index)
     * @param y the y-coordinate (row index)
     * @return true if the cell is null, false otherwise
     */
    public boolean isPositionEmpty(int x, int y) {
        return getPositionValue(x, y) == null;
    }

    /** @return the number of columns (width) */
    public int getSizeX() {
        return sizeX;
    }

    /** @param sizeX the new number of columns (width) */
    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    /** @return the number of rows (height) */
    public int getSizeY() {
        return sizeY;
    }

    /** @param sizeY the new number of rows (height) */
    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    /** @return the underlying 2D array */
    public T[][] getArray() {
        return array;
    }

    /**
     * Sets the internal 2D array to a new one.
     *
     * @param array the new 2D array to use
     */
    public void setArray(T[][] array) {
        this.array = array;
    }
}

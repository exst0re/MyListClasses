public class MyArrayList <T>{

    private final int INIT_SIZE = 16;
    private final int CUT_RATE = 4;
    private Object[] array = new Object[INIT_SIZE];
    private int pointer = 0;

    /**
     * Добавление элемента в ArrayList
     * @param elem - добавляемый элемент
     */
    public void add (T elem) {
        if (pointer == array.length - 1)
            resize(array.length * 2);
        array[pointer++] = elem;
    }

    /**
     * Добавление элемента в ArrayList на определенную позицию
     * @param elem - добавляемый элемент
     * @param index - индекс добавляемого элемента
     */
    public void add (T elem, int index) {

        if (index < 0 || index >= pointer) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + pointer);
        }
        if (pointer == array.length - 1)
            resize(pointer * 2);
        for (int i = array.length - 2; i >= index; i--) {
            array[i] = array[i - 1];
            if (i == index)
                array[i] = elem;
        }
        pointer++;
    }

    /**
     * Удаление элемента из ArrayList
     * @param index - индекс удаляемого элемента
     */
    public void remove(int index) {

        if (index < 0 || index >= pointer) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + pointer);
        }
        for (int i = index; i < pointer; i++)
            array[i] = array[i+1];
        array[pointer] = null;
        pointer--;
        if (array.length > INIT_SIZE && pointer < array.length / CUT_RATE)
            resize(array.length/2);

    }

    /**
     * Получение элемента массива ArrayList
     * @param index - индекс получаемого элемента
     * @return
     */
    public T get (int index) {

        if (index < 0 || index >= pointer) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + pointer);
        }
        return (T) array[index];
    }

    /**
     * Замена существующего элемента ArrayList новым
     * @param elem - значение нового элемента
     * @param index - индекс заменяемого элемента
     */
    public void set(T elem, int index) {

        if (index < 0 || index >= pointer) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + pointer);
        }
        array[index] = elem;
    }

    /**
     * Создание субстроки массива ArrayList на основе диапазона
     * @param fromIndex - первый элемент диапазона
     * @param toIndex - последный элемент диапазона
     * @return - массив элементов заданного диапазона
     */
    public MyArrayList<T> subList(int fromIndex, int toIndex) {

        if (fromIndex < 0 || toIndex >= pointer) {
            throw new IndexOutOfBoundsException("Index: " + fromIndex + ", Size: " + pointer);
        }
        MyArrayList<T> arrayList = new MyArrayList<>();

        for (int i = fromIndex; i < toIndex; i ++) {
            arrayList.add((T) array[i]);
        }

        return arrayList;
    }

    /**
     * Создание нового массива ArrayList с измененным размером и копирование
     * в него элементов старого массива
     * @param newLength - размерность нового массива ArrayList
     */
    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
    }

    /**
     * Получение значения длинны массива ArrayList
     * @return значение длинны массива
     */
    public int size() {
        return pointer ;
    }
}

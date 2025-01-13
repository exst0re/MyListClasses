public class MyLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Приватный класс для представления элемента связанного списка
     * @param <T> - тип элементов списка
     */
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T data, Node<T> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * Метод-конструктор класса, создающий пустые элементы
     * связанного списка
     */
    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Добавление элемента в LinkedList
     * @param elem - добавляемый элемент
     */
    public void add(T elem) {
        Node<T> newNode = new Node<>(tail, elem, null);
        if (tail == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    /**
     * Добавление элемента в LinkedList на определенную позицию
     * @param index - индекс добавляемого элемента
     * @param elem - добавляемый элемент
     */
    public void add(int index, T elem) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == size) {
            add(elem);
        } else {
            Node<T> current = getNode(index);
            Node<T> newNode = new Node<>(current.prev, elem, current);
            if (current.prev == null) {
                head = newNode;
            } else {
                current.prev.next = newNode;
            }
            current.prev = newNode;
            size++;
        }
    }

    /**
     * Удаление элемента из LinkedList
     * @param index - индекс удаляемого элемента
     */
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<T> node = getNode(index);
        T data = node.data;
        removeNode(node);
        return data;
    }

    /**
     * Получение элемента связанного списка LinkedList
     * @param index - индекс получаемого элемента
     * @return
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return getNode(index).data;
    }

    /**
     * Замена существующего элемента LinkedList новым
     * @param index - индекс заменяемого элемента
     * @param elem - новый элемент
     */
    public void set(int index, T elem) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<T> node = getNode(index);
        T oldData = node.data;
        node.data = elem;
    }

    /**
     * Создание субстроки элементов связанного спика LinkedList
     * на основе диапазона
     * @param fromIndex - первый элемент диапазона
     * @param toIndex - последний элемент диапазона
     * @return - массив элементов заданного диапазона
     */
    public MyLinkedList<T> subList (int fromIndex, int toIndex) {

        MyLinkedList<T> arrayList = new MyLinkedList<>();
        Node<T> x = head;
        if (fromIndex < 0 || toIndex >= size) {
            throw new IndexOutOfBoundsException("Index: " + fromIndex + ", Size: " + size);
        }
        for (int i = fromIndex; i < toIndex; i++) {
            x = x.next;
            arrayList.add(getNode(i).data);
        }

        return arrayList;
    }

    /**
     * Получение значения длинны связанного списка LinkedList
     * @return значение длинны списка
     */
    public int size() {
        return size;
    }

    /**
     * Получение элемента класса Node
     * @param index - индекс получаемого элемента
     * @return - получаемых элемент
     */
    private Node<T> getNode(int index) {
        if (index < (size >> 1)) {
            Node<T> x = head;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<T> x = tail;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    /**
     * Удаление элемента класса Node
     * @param node - удаляемый элемент
     */
    private void removeNode(Node<T> node) {
        if (node.prev == null) {
            head = node.next;
        } else {
            node.prev.next = node.next;
        }
        if (node.next == null) {
            tail = node.prev;
        } else {
            node.next.prev = node.prev;
        }
        size--;
    }
}

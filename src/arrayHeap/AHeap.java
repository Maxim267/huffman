package arrayHeap;

import utils.calculations.MathUtils;
import utils.constants.AppConstants;
import utils.output.*;

import java.util.Arrays;

/**
 * Неограниченная приоритетная куча основанная на массиве (Array-based heap).
 * Куча поддерживает два типа: Min-Heap - минимальный элемент всегда в корне;
 *                             Max-Heap - максимальный элемент всегда в корне (по умолчанию).
 * Элементы упорядочиваются с использованием их естественного порядка.
 * Уникальность элементов не поддерживается.
 * Тип параметров:
 * @param <K> тип ключей, поддерживаемых этой кучей.
 * @param <V> тип соответствующих ключам данных.
 */
public class AHeap<K extends Comparable<K>, V> implements Output, IntOutput {
    /**
     * Массив кучи.
     */
    private ANode<K, V>[] array;

    /**
     * Начальный размер кучи.
     */
    private final int initCapacity = AppConstants.HEAP_INIT_CAPACITY;

    /**
     * Максимальный размер кучи.
     * Автоматически увеличивается при необходимости.
     */
    private int maxSize;

    /**
     * Тип кучи:
     *     true: тип Max-Heap (максимальный элемент всегда в корне),
     *     false: тип Min-Heap (минимальный элемент всегда в корне).
     */
    private final boolean isMaxHeap;

    /**
     * Текущий размер кучи.
     */
    private int currentSize;

    /**
     * Поток вывода приоритетной кучи как массив.
     */
    public Display out;

    /**
     * Поток вывода приоритетной кучи как дерево.
     */
    public IntDisplay outTree;

    /**
     * Конструктор создает новую незаполненную кучу с типом Max-Heap.
     */
    public AHeap() {
        this.maxSize = initCapacity;
        this.currentSize = 0;
        this.array = new ANode[maxSize];
        this.isMaxHeap = true;
        setOutput();
    }

    /**
     * Конструктор создает новую незаполненную кучу с заданным типом.
     * @param isMaxHeap признак типа кучи:
     *        true: куча типа max-Heap (максимальный элемент всегда в корне),
     *        false: куча типа min-Heap (минимальный элемент всегда в корне).
     */
    public AHeap(boolean isMaxHeap) {
        this.maxSize = initCapacity;
        this.currentSize = 0;
        this.array = new ANode[maxSize];
        this.isMaxHeap = isMaxHeap;
        setOutput();
    }

    /**
     * Установить потоки вывода.
     */
    private void setOutput() {
        // Вывод в поток приоритетной кучи как массив
        String header = "<<< Array-based heap (as array): ";
        String footer = ">>>";
        out = new Display(this::display, header, footer);

        // Вывод в поток приоритетной кучи как дерево
        header = "<<< Array-based heap (as tree): ";
        outTree = new IntDisplay(this::display, header, footer);
    }

    /**
     * Получить элемент кучи.
     * @param index индекс элемента кучи.
     * @return элемент кучи.
     */
    public ANode<K, V> getArrayNode(int index) {
        return array[index];
    }

    /**
     * Получить размер кучи.
     * @return размер кучи.
     */
    public int size() {
        return currentSize;
    }

    /**
     * Получить оценочную максимальную длину выводимого значения элементов кучи.
     * @return максимальная длина выводимого значения элементов.
     */
    private int getEstimateMaxValue() {
        if(currentSize == 0) {
            return 0;
        }
        int index;
        if(isMaxHeap) {
            index = 0;
        }
        else {
            index = currentSize - 1;
        }
        String str = array[index].getKey().toString().replace("\n", AppConstants.PRINT_NEW_ROW)
                + "/" + array[index].getValue().toString().replace("\n", AppConstants.PRINT_NEW_ROW);
        return str.length();
    }

    /**
     * Вставить элемент в кучу с заданным ключом без данных.
     * Уникальность элементов не поддерживается.
     * @param key ключ элемента.
     */
    public void insert(K key) {
         insert(key, null);
    }

    /**
     * Вставить элемент в кучу с заданными ключом и данными.
     * Уникальность элементов не поддерживается.
     * @param key ключ элемента.
     * @param value данные элемента.
     */
    public void insert(K key, V value) {
        if(currentSize == maxSize) {
            resize(currentSize * 2);
        }
        ANode<K, V> node = new ANode<>(key, value);
        array[currentSize] = node;
        moveUp(currentSize++);
    }

    /**
     * Изменить размер кучи.
     * @param newCapacity новый размер кучи.
     */
    private void resize(int newCapacity) {
        if(newCapacity > currentSize) {
            array = Arrays.copyOf(array, newCapacity);
        }
        maxSize = newCapacity;
    }

    /**
     * Поднять вверх элемент в куче с учетом его приоритета.
     * @param index индекс элемента.
     */
    private void moveUp(int index)  {
        ANode<K, V> node = array[index];
        int parent = (index - 1) / 2;
        while(index > 0) {
            // В зависимости от типа кучи вверх поднимается элемент с наибольшим или наименьшим приоритетом
            if((isMaxHeap && array[parent].compareToOther(node) >= 0)
            || (!isMaxHeap && array[parent].compareToOther(node) <= 0))
            {
                break;
            }
            array[index] = array[parent];
            index = parent;
            parent = (parent - 1) / 2;
        }
        array[index] = node;
    }

    /**
     * Удалить элемент из кучи.
     * Удаляется самый первый элемент.
     * Последний элемент занимает его место и сдвигается вниз с учетом его приоритета.
     * @return удаленный элемент.
     */
    public ANode<K, V> remove() {
        if(currentSize == 0) {
            return null;
        }
        ANode<K, V> node = array[0];
        array[0] = array[--currentSize];
        moveDown(0);
        return node;
    }

    /**
     * Опустить вниз элемент в куче с учетом его приоритета.
     * @param index индекс элемента.
     */
    private void moveDown(int index) {
        ANode<K, V> node = array[index];
        while(index < currentSize / 2) {
            int largeChild;
            int leftChild = index * 2 + 1;
            int rightChild = leftChild + 1;
            // В зависимости от типа кучи выбирается потомок с наибольшим или наименьшим приоритетом
            if((isMaxHeap && rightChild < currentSize && array[leftChild].compareToOther(array[rightChild]) < 0)
                || (!isMaxHeap && rightChild < currentSize && array[leftChild].compareToOther(array[rightChild]) > 0)) {
                largeChild = rightChild;
            }
            else {
                largeChild = leftChild;
            }
            // В зависимости от типа кучи вниз опускается элемент с наибольшим или наименьшим приоритетом
            if((isMaxHeap &&  array[largeChild].compareToOther(node) < 0)
                || (!isMaxHeap && array[largeChild].compareToOther(node) > 0)){
                break;
            }
            array[index] = array[largeChild];
            index = largeChild;
        }
        array[index] = node;
    }


    // DISPLAY

    @Override
    public void display(DualOutput out) {
        // Родительские Заголовок и Нижнее сообщения отключают вывод дочерних сообщений
        String header = out.getHeader() != null ? out.getHeaderOnce() : this.out.getHeader();
        String footer = out.getFooter() != null ? out.getFooterOnce() : this.out.getFooter();

        out.println(header);

        for(int j = 0; j < currentSize; ++j) {
            out.print(array[j].getKey() + (array[j].getValue() != null ? "/" + array[j].getValue() : "") + " ");
        }
        out.println("");
        out.println(footer);
    }

    @Override
    public void display(int blanks, DualOutput out) {
        // Родительские Заголовок и Нижнее сообщения отключают вывод дочерних сообщений
        String header = out.getHeader() != null ? out.getHeaderOnce() : this.out.getHeader();
        String footer = out.getFooter() != null ? out.getFooterOnce() : this.out.getFooter();

        int nBlank;
        if(blanks > 0) {
            nBlank = MathUtils.getBinaryRound(blanks);
        } else {
            int nLevel = MathUtils.getNLevelCompleteTree(currentSize);
            int nMax = getEstimateMaxValue();
            nLevel = nLevel + (nMax / 2);
            nBlank = (int) Math.pow(2, nLevel);
        }
        if(nBlank > AppConstants.MAX_BLANKS) {
            nBlank = MathUtils.getBinaryRound(AppConstants.MAX_BLANKS);
        }
        int column = 0;
        int rowPerColumn = 1;
        int j = 0;

        // header
        out.println(header + "(blanks = " + nBlank + "): ");

        while(currentSize > 0) {
            if(column == 0) {
                for(int i = 0; i < nBlank; ++i) {
                    out.print(" ");
                }
            }
            String str = array[j].toString().replace("\n", AppConstants.PRINT_NEW_ROW);
            out.print(str);
            if(++j == currentSize) {
                break;
            }
            if(++column == rowPerColumn) {
                column = 0;
                rowPerColumn *= 2;
                nBlank /= 2;
                out.println("");
            }
            else {
                for(int i = 0; i < nBlank * 2 - str.length(); ++i) {
                    out.print(" ");
                }
            }
        }
        out.println("");

        // footer
        out.println(footer);
    }
}

package utils.output;

/**
 * Функциональный интерфейс записи в выходной поток.
 */
@FunctionalInterface
public interface Output {
    /**
     * Записать в выходной поток.
     * @param out заданный поток вывода:
     *            - стандартный поток вывода (консоль) (использовать конструктор DualOutput())
     *            - файл (использовать конструктор DualOutput(String fileName))
     */
    void display(DualOutput out);
}

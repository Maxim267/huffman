package utils.output;

/**
 * Абстрактный класс декоратор записи в выходной поток.
 */
public abstract class OutputDecorator {
    /**
     * Записывает строковое сообщение в выходной поток с завершающим символом новой строки.
     * @param message строковое сообщение
     */
    public abstract void println(String message);

    /**
     * Записывает строковое сообщение в выходной поток
     * @param message строковое сообщение
     */
    public abstract void print(String message);
}
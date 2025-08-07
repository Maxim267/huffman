package utils.output;

/**
 * Базовый вывод в поток.
 */
public class BaseDisplay {
    /**
     * Верхнее сообщение вывода.
     */
    protected String header;

    /**
     * Нижнее сообщение вывода.
     */
    protected String footer;

    /**
     * Конструктор создает новый вывод в поток.
     * @param header верхнее сообщение вывода.
     * @param footer нижнее сообщение вывода.
     */
    public BaseDisplay(String header, String footer) {
        this.header = header;
        this.footer = footer;
    }

    /**
     * Получить верхнее сообщение вывода.
     * @return верхнее сообщение
     */
    public String getHeader() {
        return header;
    }

    /**
     * Получить нижнее сообщение вывода.
     * @return нижнее сообщение
     */
    public String getFooter() {
        return footer;
    }
}

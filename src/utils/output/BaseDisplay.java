package utils.output;

/**
 * Верхнее (header) и нижнее (footer) сообщения потока вывода.
 */
public abstract class BaseDisplay {
    /**
     * Верхнее сообщение потока вывода.
     */
    protected String header;

    /**
     * Нижнее сообщение потока вывода.
     */
    protected String footer;

    /**
     * Создает верхнее (header) и нижнее (footer) сообщения потока вывода.
     * @param header верхнее сообщение вывода.
     * @param footer нижнее сообщение вывода.
     */
    public BaseDisplay(String header, String footer) {
        this.header = header;
        this.footer = footer;
    }

    /**
     * Получает верхнее сообщение потока вывода.
     * @return верхнее сообщение.
     */
    public String getHeader() {
        return header;
    }

    /**
     * Получает нижнее сообщение потока вывода.
     * @return нижнее сообщение.
     */
    public String getFooter() {
        return footer;
    }
}

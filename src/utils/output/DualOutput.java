package utils.output;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;

/**
 * Выбор потока вывода.
 */
public class DualOutput extends OutputDecorator implements AutoCloseable {
    /**
     * Стандартный выходной поток (консоль).
     */
    private PrintStream console;

    /**
     * Поток вывода в файл.
     */
    private PrintWriter fileWriter;

    /**
     * Выбор потока вывода:
     *      true: поток вывода в файл;
     *      false: стандартный выходной поток (консоль).
     */
    private final boolean useFile;

    /**
     * Верхнее сообщение.
     */
    private String header;
    /**
     * Признак сброса верхнего сообщение.
     */
    private boolean isHeaderEmpty;

    /**
     * Нижнее сообщение.
     */
    private String footer;

    /**
     * Признак сброса нижнего сообщения.
     */
    private boolean isFooterEmpty;

    /**
     * Создает стандартный выходной поток (консоль).
     */
    public DualOutput() {
        useFile = false;
        console = System.out;
    }

    /**
     * Создает поток вывода в файл.
     * @param fileName имя файла для вывода строкового сообщения.
     * @param charset имя стандартной кодировки символов файла, например, StandardCharsets.UTF_8.
     * @throws IOException если при открытии или создании файла произошла ошибка ввода-вывода.
     */
    public DualOutput(String fileName, Charset charset) throws IOException {
        useFile = true;
        fileWriter = new PrintWriter(new FileWriter(fileName, charset));
    }

    /**
     * Устанавливает верхнее сообщение.
     * @param str строковое сообщение.
     */
    public void setHeader(String str) {
        header = str;
        isHeaderEmpty = false;
    }

    /**
     * Получает верхнее сообщение.
     * @return верхнее сообщение.
     */
    public String getHeader() {
        return header;
    }

    /**
     * Получает верхнее сообщение один раз.
     * Используется в цепочке вложенных объектов при выводе сообщения в поток.
     * Инициирующий объект выводит свое сообщение и сбрасывает его в пробел.
     * Остальные потомки выводят этот пробел вместо своего сообщения.
     * @return верхнее сообщение.
     */
    public String getHeaderOnce() {
        if(isHeaderEmpty) {
            return " ";
        }
        else {
            isHeaderEmpty = true;
            return header;
        }
    }

    /**
     * Устанавливает нижнее сообщение.
     * @param str нижнее сообщение.
     */
    public void setFooter(String str) {
        footer = str;
        isFooterEmpty = false;
    }

    /**
     * Получает нижнее сообщение.
     * @return нижнее сообщение.
     */
    public String getFooter() {
        return footer;
    }

    /**
     * Получает нижнее сообщение один раз.
     * Используется в цепочке вложенных объектов при выводе сообщения в поток.
     * Инициирующий объект выводит свое сообщение и сбрасывает его в пробел.
     * Остальные потомки выводят этот пробел вместо своего сообщения.
     * @return нижнее сообщение.
     */
    public String getFooterOnce() {
        if(isFooterEmpty) {
            return " ";
        }
        else {
            isFooterEmpty = true;
            return footer;
        }
    }

    @Override
    public void print(String message) {
        if(useFile) {
            fileWriter.print(message);
        }
        else {
            console.print(message);
        }
    }

    @Override
    public void println(String message) {
        if(useFile) {
            fileWriter.println(message);
        }
        else {
            console.println(message);
        }
    }

    @Override
    public void close() {
        if(fileWriter != null) {
            fileWriter.flush();
            fileWriter.close();
        }
    }
}

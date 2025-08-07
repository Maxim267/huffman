package utils.output;

import utils.constants.AppConstants;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Вывод в поток {@code DualOutput}.
 */
public class Display extends BaseDisplay {
    /**
     * Интерфейс вывода в поток.
     */
    private final Output output;

    /**
     * Конструктор создает новый вывод в поток.
     * @param output метод вывода в поток.
     * @param header верхнее сообщение вывода.
     * @param footer нижнее сообщение вывода.
     */
    public Display(Output output, String header, String footer) {
        super(header, footer);
        this.output = output;
        this.header = header;
        this.footer = footer;
    }

    /**
     * Вывести в стандартный поток (консоль).
     */
    public void display() {
        try(DualOutput out = new DualOutput()) {
            out.setHeader(header);
            out.setFooter(footer);
            output.display(out);
        }
    }

    /**
     * Вывести в файл.
     * @param fileName имя файла
     * @param charset имя стандартной кодировки символов файла, например, StandardCharsets.UTF_8.
     * @throws IOException если при открытии или создании файла произошла ошибка ввода-вывода.
     */
    public void display(String fileName, Charset charset) throws IOException {
            try(DualOutput out = new DualOutput(fileName, charset)) {
                out.setHeader(header);
                out.setFooter(footer);
                output.display(out);
        }
    }

    /**
     * Вывести в файл.
     * @param fileName имя файла
     * @throws IOException если при открытии или создании файла произошла ошибка ввода-вывода.
     */
    public void display(String fileName) throws IOException {
        display(fileName, AppConstants.STD_CHARSET);
    }
}

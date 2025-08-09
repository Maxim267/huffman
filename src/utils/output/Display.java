package utils.output;

import utils.constants.AppConstants;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Поток вывода {@code DualOutput}.
 */
public class Display extends BaseDisplay {
    /**
     * Интерфейс потока вывода.
     */
    private final Output output;

    /**
     * Создает поток вывода.
     * @param output поток вывода.
     * @param header верхнее сообщение потока вывода.
     * @param footer нижнее сообщение потока вывода.
     */
    public Display(Output output, String header, String footer) {
        super(header, footer);
        this.output = output;
        this.header = header;
        this.footer = footer;
    }

    /**
     * Выводит в стандартный выходной поток (консоль).
     */
    public void display() {
        try(DualOutput out = new DualOutput()) {
            out.setHeader(header);
            out.setFooter(footer);
            output.display(out);
        }
    }

    /**
     * Выводит в файл.
     * @param fileName имя файла.
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
     * Выводит в файл.
     * @param fileName имя файла.
     * @throws IOException если при открытии или создании файла произошла ошибка ввода-вывода.
     */
    public void display(String fileName) throws IOException {
        display(fileName, AppConstants.STD_CHARSET);
    }
}

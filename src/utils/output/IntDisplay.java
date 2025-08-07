package utils.output;

import utils.constants.AppConstants;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Вывод в поток {@code DualOutput}.
 * Добавлен целочисленный параметр.
 */
public class IntDisplay extends BaseDisplay {
    /**
     * Интерфейс вывода в поток.
     */
    protected final IntOutput output;

    /**
     * Конструктор создает новый вывод в поток.
     * @param output метод вывода в поток.
     * @param header верхнее сообщение вывода.
     * @param footer нижнее сообщение вывода.
     */
    public IntDisplay(IntOutput output, String header, String footer) {
        super(header, footer);
        this.output = output;
        this.header = header;
        this.footer = footer;
    }

    /**
     * Вывести в стандартный поток (консоль).
     * @param blanks двоичное значение количества пробелов позиционирования дерева, начиная с корневого узла дерева.
     *              Чем больше значение {@code blanks}, тем больше дерево растягивается по горизонтали.
     *              При значении 0 (или меньше 0) производится автоматический расчет значения {@code blanks}.
     *              Не двоичное значение {@code blanks} будет автоматически преобразовано к ближайшему двоичному значению (вверх или вниз).
     */
    public void display(int blanks) {
        try(DualOutput out = new DualOutput()) {
            out.setHeader(header);
            out.setFooter(footer);
            output.display(blanks, out);
        }
    }

    /**
     * Вывести в файл.
     * @param blanks двоичное значение количества пробелов позиционирования дерева, начиная с корневого узла дерева.
     *              Чем больше значение {@code blanks}, тем больше дерево растягивается по горизонтали.
     *              При значении 0 (или меньше 0) производится автоматический расчет значения {@code blanks}.
     *              Не двоичное значение {@code blanks} будет автоматически преобразовано к ближайшему двоичному значению (вверх или вниз).
     * @param fileName имя файла.
     * @param charset имя стандартной кодировки символов файла, например, StandardCharsets.UTF_8.
     * @throws IOException если при открытии или создании файла произошла ошибка ввода-вывода.
     */
    public void display(int blanks, String fileName, Charset charset) throws IOException {
        try(DualOutput out = new DualOutput(fileName, charset)) {
            out.setHeader(header);
            out.setFooter(footer);
            output.display(blanks, out);
        }
    }

    /**
     * Вывести в файл.
     * @param blanks двоичное значение количества пробелов позиционирования дерева, начиная с корневого узла дерева.
     *              Чем больше значение {@code blanks}, тем больше дерево растягивается по горизонтали.
     *              При значении 0 (или меньше 0) производится автоматический расчет значения {@code blanks}.
     *              Не двоичное значение {@code blanks} будет автоматически преобразовано к ближайшему двоичному значению (вверх или вниз).
     * @param fileName имя файла.
     * @throws IOException если при открытии или создании файла произошла ошибка ввода-вывода.
     */
    public void display(int blanks, String fileName) throws IOException {
        display(blanks, fileName, AppConstants.STD_CHARSET);
    }
}

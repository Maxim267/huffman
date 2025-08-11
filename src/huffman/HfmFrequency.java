package huffman;

import hashTable.LHash;
import linkedList.LList;
import utils.constants.AppConstants;
import utils.output.Display;
import utils.output.DualOutput;
import utils.output.Output;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Частотная таблица символов (Frequency table) кодируемого текста на основе Хеш-таблицы.
 * Частотность формируется как количество повторов символа в тексте.
 */
public class HfmFrequency implements Output {
    /**
     * Частотная таблица символов (Хеш-таблица).
     */
    private LHash<String, Integer> freqTable;

    /**
     * Текст-источник.
     */
    private StringBuilder sourceText;

    /**
     * Интерфейс вывода в поток частотной таблицы символов.
     */
    public final Display out = new Display(this::display, "<<< Частотная таблица символов: ", ">>>");

    /**
     * Создает пустую частотную таблицу символов.
     */
    public HfmFrequency() {
        freqTable = null;
    }

    /**
     * Получает частотную таблицу символов.
     * Таблица формируется при запуске {@code createFreqTable}.
     * @return хеш-таблица.
     */
    public LHash<String, Integer> getFreqTable() {
        return freqTable;
    }

    /**
     * Получает текст-источник.
     * @return текст-источник.
     */
    public StringBuilder getSourceText() {
        return sourceText;
    }

    /**
     * Получает копию массива связанных списков частотной таблицы символов с преобразованием типов ключа и данных к типу String.
     * @return копия массива связанных списков хеш-таблицы с преобразованием типов ключа и данных к типу String.
     */
    public LList<String, String>[] getStringCopyHash() {
        return freqTable.toStringCopy();
    }

    /**
     * Формирует частотную таблицу символов.
     * @param path файл с кодируемым текстом.
     * @param charset имя стандартной кодировки символов файла, например, StandardCharsets.UTF_8.
     * @throws IOException если при открытии или создании файла произошла ошибка ввода-вывода.
     */
    public void createFreqTable(String path, Charset charset) throws IOException {
        Scanner in = new Scanner(Path.of(path), charset);
        sourceText = new StringBuilder();
        int nElem;
        while(in.hasNext()) {
            sourceText.append(in.nextLine());
            if(in.hasNext()) {
                sourceText.append(AppConstants.UNIX_NEW_ROW);
            }
        }
        freqTable = new LHash<>(AppConstants.HASH_SIZE);
        nElem = sourceText.length();
        for(int j = 0; j < nElem; ++j) {
            freqTable.inc(String.valueOf(sourceText.charAt(j)), 1);
        }
    }

    // DISPLAY

    @Override
    public void display(DualOutput out) {
        // Инициатор выводит свои верхнее и нижнее оформления сообщения и отключает их вывод в цепочке объектов.
        String header = out.getHeader() != null ? out.getHeaderOnce() : this.out.getHeader();
        String footer = out.getFooter() != null ? out.getFooterOnce() : this.out.getFooter();

        out.print(header);
        freqTable.display(out);
        out.println(footer);
    }
}

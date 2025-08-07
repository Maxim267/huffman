package huffman;

import utils.constants.AppConstants;
import utils.output.DualOutput;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Процесс обработки текста по алгоритму Хаффмана:
 *      (I) Кодирование файла с исходным текстом по алгоритму Хаффмана.
 *          В результате кодирования создаются два файла:
 *              - файл кодировки Хаффмана;
 *              - файл кодированного текста.
 *      (II) Декодирование файла с кодированным текстом с использованием файла кодировки Хаффмана.
 *           Эти два файла создаются на предыдущем этапе (I) кодирования файла с исходным текстом.
 *           В результате декодирования создается файл:
 *              файл декодированного текста.
 */
public class HfmProcess {
    /**
     * Частотная таблица символов Хаффмана.
     */
    private final HfmFrequency hfmFrequency;
    /**
     * Приоритетная очередь для построения дерева Хаффмана.
     */
    private final HfmQueue hfmQueue;

    /**
     * Набор кодировки Хаффмана.
     */
    private HfmCharset hfmCharset;

    /**
     * Конструктор создает новый процесс обработки текста по алгоритму Хаффмана.
     */
    public HfmProcess() {
        hfmFrequency = new HfmFrequency();
        hfmQueue = new HfmQueue();
    }

    /**
     * Запустить процесс кодирования файла с исходным текстом по алгоритму Хаффмана.
     * @param sourceTextFile из этого файла читается исходный текст, который необходимо закодировать по алгоритму Хаффмана.
     * @param huffmanCodeFile в этот файл записывается набор кодировки Хаффмана для всех символов исходного текста.
     * @param encodedTextFile в этот файл записывается закодированный по Хаффману текст.
     * @throws IOException если при открытии или создании файла произошла ошибка ввода-вывода.
     */
    public void executeEncoding(String sourceTextFile, String huffmanCodeFile, String encodedTextFile) throws IOException {
        executeEncoding(sourceTextFile, huffmanCodeFile, encodedTextFile, AppConstants.STD_CHARSET);
    }

    /**
     * Запустить процесс кодирования файла с исходным текстом по алгоритму Хаффмана.
     * @param sourceTextFile из этого файла читается исходный текст, который необходимо закодировать по алгоритму Хаффмана.
     * @param huffmanCodeFile в этот файл записывается набор кодировки Хаффмана для всех символов исходного текста.
     * @param encodedTextFile в этот файл записывается закодированный по Хаффману текст.
     * @param charset имя стандартной кодировки символов всех файлов процесса кодирования, например, StandardCharsets.UTF_8.
     * @throws IOException если при открытии или создании файла произошла ошибка ввода-вывода.
     */
    public void executeEncoding(String sourceTextFile, String huffmanCodeFile, String encodedTextFile, Charset charset) throws IOException {
        // 1. Формирование частотной таблицы
        hfmFrequency.createFreqTable(sourceTextFile, charset);
//        hfmFrequency.displayHfmFreqTable(); // вывести частотную таблицу

        // 2. Первичное заполнение очереди
        hfmQueue.setPrimaryQueue(hfmFrequency.getFreqTable());
//        hfmQueue.out.display(); // вывести первично заполненную очередь как массив
//        hfmQueue.outTree.display(0); // вывести первично заполненную очередь как дерево

        // 3. Формирование дерева Хаффмана
        HfmTree hfmTree = hfmQueue.createHfmTree();
//        hfmTree.out.display(0); // вывести дерево

        // 4. Формирование набора кодировки Хаффмана
        hfmCharset = new HfmCharset(hfmTree);
//        hfmCharset.out.display(); // вывести хеш-таблицу с набором символов кодировки Хаффмана
//        hfmCharset.outHuffman.display(); // вывести набор кодировки Хаффмана

        // 5. Записать в файл набор кодировки Хаффмана
        hfmCharset.outHuffman.display(huffmanCodeFile, charset);

        // Записать в файл encodedTextFile закодированный по Хаффману исходный текст
        writeHuffmanCodeToFile(hfmFrequency.getSourceText(), encodedTextFile, charset);
    }

    /**
     * Записать в файл закодированный текст по Хаффману.
     * @param sourceText исходный текст, который необходимо закодировать.
     * @param encodedTextFile в этот файл записывается закодированный по Хаффману текст файла {@code sourceTextFile}
     * @param charset имя стандартной кодировки символов файлов, например, StandardCharsets.UTF_8.
     * @throws IOException если при открытии или создании файла произошла ошибка ввода-вывода.
     */
    public void writeHuffmanCodeToFile(StringBuilder sourceText, String encodedTextFile, Charset charset) throws IOException {
        try(DualOutput out = new DualOutput(encodedTextFile, charset)) {
            for (int j = 0; j < sourceText.length(); ++j) {
                out.print(hfmCharset.getHash().get(String.valueOf(sourceText.charAt(j))));
            }
        }
    }

    /**
     * Запустить процесс Декодирования текстового файла.
     * @param huffmanCodeFile из этого файла читается набор кодировки Хаффмана для всех символов текста (файл получен в {@code executeEncoding}).
     * @param encodedTextFile из этого файла читается закодированный по Хаффману текст (файл получен в {@code executeEncoding}).
     * @param decodedTextFile в этот файл записывается декодированный {@code sourceTextFile}
     * @param charset имя стандартной кодировки символов файлов, например, StandardCharsets.UTF_8.
     * @throws IOException если при открытии или создании файла произошла ошибка ввода-вывода.
     */
    public void executeDecoding(String huffmanCodeFile, String encodedTextFile, String decodedTextFile, Charset charset) throws IOException {
        if(hfmCharset == null) {
            hfmCharset = new HfmCharset();
        }
        // Прочитать набор кодировки Хаффмана из файла
        hfmCharset.readCharsetFromFile(huffmanCodeFile, charset);
//        hfmCharset.displayCharset(); // вывести набор символов кодировки Хаффмана

        writeDecodedTextToFile(encodedTextFile, decodedTextFile, charset);
    }

    /**
     * Запустить процесс Декодирования текстового файла.
     * @param huffmanCodeFile из этого файла читается набор кодировки Хаффмана для всех символов текста (файл получен в {@code executeEncoding}).
     * @param encodedTextFile из этого файла читается закодированный по Хаффману текст (файл получен в {@code executeEncoding}).
     * @param decodedTextFile в этот файл записывается декодированный {@code sourceTextFile}
     * @throws IOException если при открытии или создании файла произошла ошибка ввода-вывода.
     */
    public void executeDecoding(String huffmanCodeFile, String encodedTextFile, String decodedTextFile) throws IOException {
        executeDecoding(huffmanCodeFile, encodedTextFile, decodedTextFile, AppConstants.STD_CHARSET);
    }

    /**
     * Записать декодированный текст в файл.
     * @param encodeFileName из этого файла читается закодированный по Хаффману текст.
     * @param decodeFileName в этот файл записывается декодированный текст.
     * @param charset имя стандартной кодировки символов файлов, например, StandardCharsets.UTF_8.
     * @throws IOException если при открытии или создании файла произошла ошибка ввода-вывода.
     */
    public void writeDecodedTextToFile(String encodeFileName, String decodeFileName, Charset charset) throws IOException {
        Scanner in = new Scanner(Path.of(encodeFileName));
        StringBuilder text = new StringBuilder();
        if(in.hasNext()) {
            text.append(in.nextLine());
        }

        try(DualOutput out = new DualOutput(decodeFileName, charset)) {
            while (!text.isEmpty()) {
                int offset = hfmCharset.getMinOffset();
                int maxOffset = hfmCharset.getMaxOffset();
                String code = text.substring(0, offset);
                String value = hfmCharset.getHash().get(code);
                while (value == null) {
                    offset++;
                    if (offset > maxOffset) {
                        System.out.println("Offset out of bounds for length !!!");
                        return;
                    }
                    code = text.substring(0, offset);
                    value = hfmCharset.getHash().get(code);
                }
                out.print(value);
                text.delete(0, offset);
            }
        }
    }
}

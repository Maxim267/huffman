import priorityQ.PriorityQ;
import treeQ.NodeQ;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        // Создать приоритетную очередь
        PriorityQ pQueue = new PriorityQ();

        // I часть. Закодировать текст по алгоритму Хаффмана

        // Предварительно подготовить тестовый файл Test.txt с текстом, который необходимо закодировать
        // Загрузить в хеш-таблицу все символы текста (ключ=символ; значение=количество символа в тексте)
        pQueue.hashFile("Test.txt");
        // Отобразить хеш-таблицу с символами текста
        pQueue.displayHashText();
        // Выполнить первое заполнение приоритетной очереди символами из хеш-таблицы
        // где для каждого символа будет создано дерево с одним корневым узлом
        pQueue.setListTree();
        // Продолжить рекурсивно обрабатывать приоритетную очередь
        // пока в очереди не останется одно дерево Хаффмана, где для каждого символа будет создан свой узел
        pQueue.setHuffmanTree();
        // Отобразить дерево Хаффмана
        pQueue.displayHuffmanTree();
        // Получить корневой узел дерева Хаффмана
        NodeQ root = pQueue.getList().getLinkTRoot().getLinkTTree().getTreeRoot();
        // Обойти все узлы дерева, начиная с корневого узла
        // и заполнить хеш-таблицу с собственным набором символов кодировки по алгоритму Хаффмана
        pQueue.setCharset(root, "");
        // Задать имя файла, в котором будет сохранен закодированный текст
        pQueue.encodeFile("EncodedText.txt", StandardCharsets.UTF_8);
        // Задать имя файла, в котором будет сохранен собственный набор символов кодировки по алгоритму Хаффмана
        pQueue.saveCharsetToFile("Charset.txt", StandardCharsets.UTF_8);

        // II часть. Раскодировать текст, который был ранее закодирован по алгоритму Хаффмана

        // Задать имя файла, из которого в хеш-таблицу будет сохранен набор символов кодировки
        pQueue.loadCharsetFromFile("Charset.txt", StandardCharsets.UTF_8);
        // Отобразить хеш-таблицу с набором символов кодировки
        // (ключ=код символа; значение=символ)
        pQueue.displayHashCharset();
        // Задать два имени файла:
        // в первом ("EncodedText.txt") - хранится закодированный текст ,
        // во втором ("DecodedText.txt") - куда будет раскодирован текст.
        pQueue.decodeFile("EncodedText.txt", "DecodedText.txt", StandardCharsets.UTF_8);

        // Проверить результат работы:
        // Сравнить два файла: "Test.txt" и "DecodedText.txt".
        // Они должны быть одинаковыми.
    }
}

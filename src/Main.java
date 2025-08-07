import huffman.HfmProcess;

import java.io.IOException;

public class Main {
        public static void main(String[] args)  throws IOException {
                // HuffmanTree
                HfmProcess huff = new HfmProcess();

                long start = System.nanoTime();

                // I Кодировать текст по алгоритму Хаффмана

                // sourceTextFile.txt - из этого файла читается исходный текст, который необходимо закодировать.
                // huffmanCodeFile.txt - в этот файл записывается набор кодировки Хаффмана для всех символов текста файла sourceTextFile.txt
                // encodedTextFile.txt - в этот файл записывается закодированный по Хаффману текст файла sourceTextFile.txt
                huff.executeEncoding("sourceTextFile.txt", "huffmanCodeFile.txt", "encodedTextFile.txt");

                long end = System.nanoTime();
                System.out.println("Время кодирования текста: " + (end - start)/1_000_000 + " мс");

                // II Декодировать текст

                long start2 = System.nanoTime();

                // huffmanCodeFile.txt - из этого файла читается набор кодировки Хаффмана для всех символов текста файла (файл получен на I этапе).
                // encodedTextFile.txt - из этого файла читается закодированный по Хаффману текст файла (файл получен на I этапе).
                // decodedTextFile.txt - в этот файл записывается декодированный по Хаффману текст файла (файл должен быть аналогичен исходному sourceTextFile.txt)
                huff.executeDecoding("huffmanCodeFile.txt", "encodedTextFile.txt", "decodedTextFile.txt");

                long end2 = System.nanoTime();
                System.out.println("Время декодирования текста: " + (end2 - start2)/1_000_000 + " мс");
        }

}
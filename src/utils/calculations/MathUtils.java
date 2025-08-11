package utils.calculations;

/**
 *  Методы расчета значений приложения.
 */
public final class MathUtils {
    /**
     * Создает Методы расчета.
     */
    private MathUtils() {}

    /**
     * Получает ближайшее от заданного округленное (вверх или вниз) двоичное значение.
     * @param value целочисленное значение для округления.
     * @return ближайшее округленное двоичное значение.
     */
    public static int getBinaryRound(int value) {
        double val = value;
        val = Math.log(val) / Math.log(2);
        val = Math.round(val);
        val = Math.pow(2, val);
        if(val < 2) {
            val = 2;
        }
        return (int) val;
    }

    /**
     * Проверяет, что заданное значение является простым числом.
     * @param value заданное целочисленное число.
     * @return признак проверки:
     *      true - заданное число является простым,
     *      false - заданное число не является простым.
     */
    public static boolean isPrime(int value) {
        for(int j = 2; j * j <= value; ++j) {
            if(value % j == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Получает простое число на основе заданного значения.
     * Если заданное число не является простым, оно будет увеличено до ближайшего простого числа.
     * @param value заданное целочисленное число.
     * @return простое число.
     */
    public static int getPrime(int value) {
        for(int j = value; true; ++j) {
            if(isPrime(j)) {
                return j;
            }
        }
    }

    /**
     * Получает количество уровней полного дерева.
     * @param size количество узлов полного дерева.
     * @return количество уровней.
     */
    public static int getNLevelCompleteTree(int size) {
        return (int) (Math.log(size) / Math.log(2));
    }
}

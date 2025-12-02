package performance;

import java.util.List;

/**
 * Интерфейс CollectionBenchmark создан для проведения бенчмарков операций с коллекциями
 * Определяет контракт для измерения времени выполнения операций
 */
public interface CollectionBenchmark {
    
    /**
     * Метод benchmarkAdd это бенчмарк операции добавления элементов
     * 
     * @param list коллекция для тестирования
     * @param operationCount количество операций для выполнения
     * @return время выполнения в наносекундах
     */
    long benchmarkAdd(List<Integer> list, int operationCount);
    
    
    /**
     * Бенчмарк операции получения элементов.
     * 
     * @param list коллекция для тестирования
     * @param operationCount количество операций для выполнения
     * @return время выполнения в наносекундах
     */
    long benchmarkGet(List<Integer> list, int operationCount);
    
    /**
     * Бенчмарк операции удаления элементов по индексу.
     * 
     * @param list коллекция для тестирования
     * @param operationCount количество операций для выполнения
     * @return время выполнения в наносекундах
     */
    long benchmarkDeleteByIndex(List<Integer> list, int operationCount);
    
    /**
     * Бенчмарк операции удаления элементов по значению.
     * 
     * @param list коллекция для тестирования
     * @param operationCount количество операций для выполнения
     * @return время выполнения в наносекундах
     */
    long benchmarkDeleteByValue(List<Integer> list, int operationCount);
    
    /**
     * Бенчмарк операции проверки наличия элемента по значению.
     * 
     * @param list коллекция для тестирования
     * @param operationCount количество операций для выполнения
     * @return время выполнения в наносекундах
     */
    long benchmarkContains(List<Integer> list, int operationCount);
    
    /**
     * Бенчмарк операции проверки пустоты коллекции.
     * 
     * @param list коллекция для тестирования
     * @param operationCount количество операций для выполнения
     * @return время выполнения в наносекундах
     */
    long benchmarkIsEmpty(List<Integer> list, int operationCount);
}
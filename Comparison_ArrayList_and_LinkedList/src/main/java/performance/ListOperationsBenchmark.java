package performance;

import java.util.List;

/**
 * Класс ListOperationsBenchmark реализует бенчмарк операций для List коллекций
 * Выполняет основные операции и измеряет время их выполнения
 */
public class ListOperationsBenchmark implements CollectionBenchmark {
    
    /**
     * {@inheritDoc}
     * Переопределённый метод benchmarkAdd бенчмарк операции добавления элементов в конец списка
     */
    @Override
    public long benchmarkAdd(List<Integer> list, int operationCount) {
        list.clear();
        long startTime = System.nanoTime();
        for (int i = 0; i < operationCount; i++) {
            list.add(i);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    
    /**
     * {@inheritDoc}
     * Переопределённый метод benchmarkGet бенчмарк операции получения элементов по индексу.
     * Предварительно заполняет список необходимым количеством элементов.
     */
    @Override
    public long benchmarkGet(List<Integer> list, int operationCount) {
        list.clear();
        for (int i = 0; i < operationCount; i++)
            list.add(i);
        long startTime = System.nanoTime();
        for (int i = 0; i < operationCount; i++)
            list.get(i % list.size()); 
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    
    /**
     * {@inheritDoc}
     * Переопределнный метод benchmarkDeleteByIndex бенчмарк операции удаления элементов по индексу
     * Предварительно заполняет список необходимым количеством элементов
     */
    @Override
    public long benchmarkDeleteByIndex(List<Integer> list, int operationCount) {
        list.clear();
        for (int i = 0; i < operationCount * 2; i++)
            list.add(i);
        long startTime = System.nanoTime();
        for (int i = 0; i < operationCount; i++)
            list.remove(0);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    
    /**
     * {@inheritDoc}
     * Переопределённый метод benchmarkDeleteByValue бенчмарк операции удаления элементов по значению
     * Предварительно заполняет список необходимым количеством элементов
     */
    @Override
    public long benchmarkDeleteByValue(List<Integer> list, int operationCount) {
        list.clear();
        for (int i = 0; i < operationCount * 2; i++) {
            list.add(i);
        }
        long startTime = System.nanoTime();
        for (int i = 0; i < operationCount; i++) {
            list.remove(Integer.valueOf(i));
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    
    /**
     * {@inheritDoc}
     * Переопределённый метод benchmarkContains бенчмарк операции проверки наличия элемента
     * Предварительно заполняет список
     */
    @Override
    public long benchmarkContains(List<Integer> list, int operationCount) {
        list.clear();
        for (int i = 0; i < operationCount; i++)
            list.add(i);
        long startTime = System.nanoTime(); 
        for (int i = 0; i < operationCount; i++) {
            list.contains(i % list.size());
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    
    /**
     * {@inheritDoc}
     * Переопределённый метод benchmarkIsEmpty бенчмарк операции проверки пустоты коллекции.
     */
    @Override
    public long benchmarkIsEmpty(List<Integer> list, int operationCount) {
        list.clear();
        long startTime = System.nanoTime();
        for (int i = 0; i < operationCount; i++) {
            list.isEmpty();
            if (i % 2 == 0) {
                list.add(i);
                list.isEmpty();
                list.clear();
            }
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}
package performance;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс ListOperationsBenchmarkTest содержит тесты для класса ListOperationsBenchmark
 * Проверяют корректность измерения времени выполнения операций
 */
class ListOperationsBenchmarkTest {
    
    private final CollectionBenchmark benchmark = new ListOperationsBenchmark();
    
    /**
     * testBenchmarkAdd() тест операции добавления.
     */
    @Test
    void testBenchmarkAdd() {
        List<Integer> list = new ArrayList<>();
        int operationCount = 1000;
        long time = benchmark.benchmarkAdd(list, operationCount);
        assertEquals(operationCount, list.size());
        assertTrue(time > 0, "Время выполнения должно быть положительным");
        for (int i = 0; i < operationCount; i++) {
            assertEquals(Integer.valueOf(i), list.get(i));
        }
    }
    
    /**
     * testBenchmarkGet() тест операции получения элементов
     */
    @Test
    void testBenchmarkGet() {
        List<Integer> list = new ArrayList<>();
        int operationCount = 500;
        long time = benchmark.benchmarkGet(list, operationCount);
        assertTrue(time > 0, "Время выполнения должно быть положительным");
        assertEquals(operationCount, list.size());
    }
    
    /**
     * testBenchmarkDeleteByIndex() тест операции удаления по индексу
     */
    @Test
    void testBenchmarkDeleteByIndex() {
        List<Integer> list = new ArrayList<>();
        int operationCount = 300;
        long time = benchmark.benchmarkDeleteByIndex(list, operationCount);
        assertTrue(time > 0, "Время выполнения должно быть положительным");
        assertEquals(operationCount, list.size()); 
    }
    
    /**
     * testBenchmarkDeleteByValue() тест операции удаления по значению.
     */
    @Test
    void testBenchmarkDeleteByValue() {
        List<Integer> list = new ArrayList<>();
        int operationCount = 300;
        long time = benchmark.benchmarkDeleteByValue(list, operationCount);
        assertTrue(time > 0, "Время выполнения должно быть положительным");
        assertEquals(operationCount, list.size()); 
    }
    
    /**
     * testBenchmarkContains() тест операции проверки наличия элемента.
     */
    @Test
    void testBenchmarkContains() {
        List<Integer> list = new ArrayList<>();
        int operationCount = 500;
        long time = benchmark.benchmarkContains(list, operationCount);
        assertTrue(time > 0, "Время выполнения должно быть положительным");
        assertEquals(operationCount, list.size());
    }
    
    /**
     * testBenchmarkIsEmpty() тест операции проверки пустоты
     */
    @Test
    void testBenchmarkIsEmpty() {
        List<Integer> list = new ArrayList<>();
        int operationCount = 300;
        long time = benchmark.benchmarkIsEmpty(list, operationCount);
        assertTrue(time > 0, "Время выполнения должно быть положительным");
    }
    
    /**
     * testWithLinkedList() тест с LinkedList.
     */
    @Test
    void testWithLinkedList() {
        List<Integer> list = new LinkedList<>();
        int operationCount = 200;
        long time = benchmark.benchmarkAdd(list, operationCount);
        assertEquals(operationCount, list.size());
        assertTrue(time > 0, "Время выполнения должно быть положительным");
    }
    
    /**
     * testZeroOperations() тест с нулевым количеством операций.
     */
    @Test
    void testZeroOperations() {
        List<Integer> list = new ArrayList<>();
        long time = benchmark.benchmarkAdd(list, 0);
        assertEquals(0, list.size());
        assertTrue(time >= 0, "Время выполнения должно быть неотрицательным");
    }
    
    /**
     * testLargeNumberOfOperations() тест с большим количеством операций.
     */
    @Test
    void testLargeNumberOfOperations() {
        List<Integer> list = new ArrayList<>();
        int operationCount = 10000;
        long time = benchmark.benchmarkAdd(list, operationCount);
        assertEquals(operationCount, list.size());
        assertTrue(time > 0, "Время выполнения должно быть положительным");
    }
}
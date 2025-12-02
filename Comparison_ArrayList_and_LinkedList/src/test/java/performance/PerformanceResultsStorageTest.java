package performance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * PerformanceResultsStorageTest содержит тесты для класса PerformanceResultsStorage
 * Проверяют функциональность сохранения и извлечения результатов бенчмарков
 */
class PerformanceResultsStorageTest {
    
    private PerformanceResultsStorage storage;
    
    @BeforeEach
    void setUp() {
        storage = new PerformanceResultsStorage();
    }
    
    /**
     * testSaveResult() тест сохранения результата
     */
    @Test
    void testSaveResult() {
        PerformanceResult result = new PerformanceResult(
            "ArrayList", "add", 1000, 5000000L);
        
        storage.saveResult(result);
        
        assertEquals(1, storage.getResultCount());
    }
    
    /**
     * testSaveNullResult() тест сохранения null результата
     */
    @Test
    void testSaveNullResult() {
        assertThrows(IllegalArgumentException.class, () -> {
            storage.saveResult(null);
        });
    }
    
    /**
     * testGetAllResults() тест получения всех результатов
     */
    @Test
    void testGetAllResults() {
        PerformanceResult result1 = new PerformanceResult(
            "ArrayList", "add", 1000, 5000000L);
        PerformanceResult result2 = new PerformanceResult(
            "LinkedList", "add", 1000, 10000000L);
        storage.saveResult(result1);
        storage.saveResult(result2);
        List<PerformanceResult> allResults = storage.getAllResults();
        assertEquals(2, allResults.size());
        assertTrue(allResults.contains(result1));
        assertTrue(allResults.contains(result2));
    }
    
    /**
     * testGetResultsByCollectionType() тест получения результатов по типу коллекции
     */
    @Test
    void testGetResultsByCollectionType() {
        PerformanceResult result1 = new PerformanceResult(
            "ArrayList", "add", 1000, 5000000L);
        PerformanceResult result2 = new PerformanceResult(
            "LinkedList", "add", 1000, 10000000L);
        PerformanceResult result3 = new PerformanceResult(
            "ArrayList", "get", 1000, 2000000L);
        storage.saveResult(result1);
        storage.saveResult(result2);
        storage.saveResult(result3);
        List<PerformanceResult> arrayListResults = 
            storage.getResultsByCollectionType("ArrayList");
        assertEquals(2, arrayListResults.size());
        assertTrue(arrayListResults.contains(result1));
        assertTrue(arrayListResults.contains(result3));
        assertFalse(arrayListResults.contains(result2));
    }
    
    /**
     * testGetResultsByNonExistentCollectionType() тест получения результатов по несуществующему типу коллекции
     */
    @Test
    void testGetResultsByNonExistentCollectionType() {
        PerformanceResult result = new PerformanceResult(
            "ArrayList", "add", 1000, 5000000L);
        storage.saveResult(result);
        
        List<PerformanceResult> results = 
            storage.getResultsByCollectionType("NonExistent");
        
        assertTrue(results.isEmpty());
    }
    
    /**
     * testGetResultsByMethod() тест получения результатов по операции
     */
    @Test
    void testGetResultsByMethod() {
        PerformanceResult result1 = new PerformanceResult(
            "ArrayList", "add", 1000, 5000000L);
        PerformanceResult result2 = new PerformanceResult(
            "LinkedList", "add", 1000, 10000000L);
        PerformanceResult result3 = new PerformanceResult(
            "ArrayList", "get", 1000, 2000000L);
        storage.saveResult(result1);
        storage.saveResult(result2);
        storage.saveResult(result3);
        List<PerformanceResult> addResults = storage.getResultsByMethod("add");
        assertEquals(2, addResults.size());
        assertTrue(addResults.contains(result1));
        assertTrue(addResults.contains(result2));
        assertFalse(addResults.contains(result3));
    }
    
    /**
     * testClearResults() тест очистки результатов
     */
    @Test
    void testClearResults() {
        PerformanceResult result1 = new PerformanceResult(
            "ArrayList", "add", 1000, 5000000L);
        PerformanceResult result2 = new PerformanceResult(
            "LinkedList", "add", 1000, 10000000L);
        storage.saveResult(result1);
        storage.saveResult(result2); 
        assertEquals(2, storage.getResultCount());
        storage.clearResults();
        assertEquals(0, storage.getResultCount());
        assertTrue(storage.getAllResults().isEmpty());
    }
    
    /**
     * testGetResultsByEmptyMethodName() тест с пустым именем операции
     */
    @Test
    void testGetResultsByEmptyMethodName() {
        assertThrows(IllegalArgumentException.class, () -> {
            storage.getResultsByMethod("");
        });
    }
    
    /**
     * testGetResultsByNullMethodName() тест с null именем операции
     */
    @Test
    void testGetResultsByNullMethodName() {
        assertThrows(IllegalArgumentException.class, () -> {
            storage.getResultsByMethod(null);
        });
    }
}
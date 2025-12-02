package performance;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс PerformanceResultsStorage является хранилищем для результатов бенчмарков производительности
 * Позволяет сохранять, извлекать и анализировать результаты тестов
 */
public class PerformanceResultsStorage {
    private final List<PerformanceResult> results;
    
    /**
     * Конструктор по умолчанию, инициализирует хранилище результатов
     */
    public PerformanceResultsStorage() {
        this.results = new ArrayList<>();
    }
    
    /**
     * Метод saveResult сохраняет результат бенчмарка в хранилиещ
     * 
     * @param result результат бенчмарка для сохранения
     */
    public void saveResult(PerformanceResult result) {
        if (result == null)
            throw new IllegalArgumentException("Result cannot be null");
        results.add(result);
    }
    
    /**
     * Метод getAllResults() возвращает все результаты бенчмарков
     * 
     * @return список всех результатов
     */
    public List<PerformanceResult> getAllResults() {
        return new ArrayList<>(results);
    }
    
    /**
     * Метод getResultsByCollectionType возвращает результаты бенчмарков для указанного типа коллекции
     * 
     * @param collectionType тип коллекции для фильтрации
     * @return список результатов для указанного типа коллекции
     */
    public List<PerformanceResult> getResultsByCollectionType(String collectionType) {
        if (collectionType == null || collectionType.trim().isEmpty())
            throw new IllegalArgumentException("Collection type cannot be null or empty");
        List<PerformanceResult> filteredResults = new ArrayList<>();
        for (PerformanceResult result : results)
            if (collectionType.equals(result.getCollectionType()))
                filteredResults.add(result);
        return filteredResults;
    }
    
    /**
     * Метод getResultsByMethod возвращает результаты бенчмарков для указанной операции
     * 
     * @param methodName название операции для фильтрации
     * @return список результатов для указанной операции
     */
    public List<PerformanceResult> getResultsByMethod(String methodName) {
        if (methodName == null || methodName.trim().isEmpty())
            throw new IllegalArgumentException("Method name cannot be null or empty");
        List<PerformanceResult> filteredResults = new ArrayList<>();
        for (PerformanceResult result : results) 
            if (methodName.equals(result.getMethodName())) 
                filteredResults.add(result);
        return filteredResults;
    }
    
    /**
     * Метод clearResults() очищает все результаты из хранилища
     */
    public void clearResults() {
        results.clear();
    }
    
    /**
     * Метод getResultCount() возвращает количество сохраненных результатов.
     * 
     * @return количество результатов
     */
    public int getResultCount() {
        return results.size();
    }
}
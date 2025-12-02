package performance;

/**
 * Класс PerformanceResult предназначен для хранения результатов тестирования производительности
 * Содержит информацию о методе, количестве операций и времени выполнения
 */
public class PerformanceResult {
    private final String collectionType;
    private final String methodName;
    private final int operationCount;
    private final long executionTimeNs;
    
    /**
     * Конструктор PerformanceResult создает результат тестирования
     * 
     * @param collectionType тип коллекции (ArrayList или LinkedList)
     * @param methodName название тестируемого метода
     * @param operationCount количество выполненных операций
     * @param executionTimeNs время выполнения в наносекундах
     */
    public PerformanceResult(String collectionType, String methodName, 
                            int operationCount, long executionTimeNs) {
        this.collectionType = collectionType;
        this.methodName = methodName;
        this.operationCount = operationCount;
        this.executionTimeNs = executionTimeNs;
    }
    
    /**
     * Метод getCollectionType возвращает тип коллекции
     * 
     * @return тип коллекции
     */
    public String getCollectionType() {
        return collectionType;
    }
    
    /**
     * Метод getMethodName возвращает название метода
     * 
     * @return название метода
     */
    public String getMethodName() {
        return methodName;
    }
    
    /**
     * Метод getOperationCount возвращает количество операций
     * 
     * @return количество операций
     */
    public int getOperationCount() {
        return operationCount;
    }
    
    /**
     * Метод getExecutionTimeNs возвращает время выполнения в наносекундах
     * 
     * @return время выполнения в наносекундах
     */
    public long getExecutionTimeNs() {
        return executionTimeNs;
    }
    
    /**
     * Метод getExecutionTimeMs возвращает время выполнения в миллисекундах
     * 
     * @return время выполнения в миллисекундах
     */
    public double getExecutionTimeMs() {
        return executionTimeNs / 1_000_000.0;
    }
    
    @Override
    public String toString() {
        return String.format("%s - %s: %d ops, %.3f ms", 
                collectionType, methodName, operationCount, getExecutionTimeMs());
    }
}

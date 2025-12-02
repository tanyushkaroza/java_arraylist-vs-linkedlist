package performance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс PerformanceComparison является основным классом для сравнения производительности ArrayList и LinkedList
 * Выполняет тестирование основных операций и выводит результаты в табличном виде
 */
public class PerformanceComparison {
    
    private static final int DEFAULT_OPERATION_COUNT = 1000;
    private static final int WARMUP_ITERATIONS = 5;
    private final CollectionBenchmark benchmark;
    private final PerformanceResultsStorage resultsStorage;
    
    /**
     * Конструктор PerformanceComparison с использованием стандартных компонентов
     */
    public PerformanceComparison() {
        this.benchmark = new ListOperationsBenchmark();
        this.resultsStorage = new PerformanceResultsStorage();
    }
    
    /**
     * Конструктор PerformanceComparison с возможностью внедрения зависимостей
     * 
     * @param benchmark бенчмарк производительности
     * @param resultsStorage хранилище для результатов
     */
    public PerformanceComparison(CollectionBenchmark benchmark, PerformanceResultsStorage resultsStorage) {
        this.benchmark = benchmark;
        this.resultsStorage = resultsStorage;
    }
    
    /**
     * Метод comparePerformance выполняет полное сравнение производительности ArrayList и LinkedList
     * 
     * @param operationCount количество операций для тестирования
     */
    public void comparePerformance(int operationCount) {
        System.out.println("Количество операций: " + operationCount);
        System.out.println("=".repeat(80));
        
        // Тестируем ArrayList
        testCollection("ArrayList", new ArrayList<>(), operationCount);
        
        // Тестируем LinkedList
        testCollection("LinkedList", new LinkedList<>(), operationCount);

        printResultsTable();
    }
    
    /**
     * Метод testCollection тестирует конкретную коллекцию
     * 
     * @param collectionType тип коллекции (для отображения)
     * @param list коллекция для тестирования
     * @param operationCount количество операций
     */
    private void testCollection(String collectionType, List<Integer> list, int operationCount) {
        System.out.println("\nТестируем " + collectionType + ":");
        performWarmup(list);
        
        // Тестируем добавление
        long addTime = benchmark.benchmarkAdd(new ArrayList<>(list), operationCount);
        resultsStorage.saveResult(new PerformanceResult(
            collectionType, "add", operationCount, addTime));
        System.out.println("  add: " + addTime + " нс");
        
        // Тестируем получение
        long getTime = benchmark.benchmarkGet(new ArrayList<>(list), operationCount);
        resultsStorage.saveResult(new PerformanceResult(
            collectionType, "get", operationCount, getTime));
        System.out.println("  get: " + getTime + " нс");
        
        // Тестируем удаление по индексу
        long deleteByIndexTime = benchmark.benchmarkDeleteByIndex(new ArrayList<>(list), operationCount);
        resultsStorage.saveResult(new PerformanceResult(
            collectionType, "deleteByIndex", operationCount, deleteByIndexTime));
        System.out.println("  deleteByIndex: " + deleteByIndexTime + " нс");
        
        // Тестируем удаление по значению
        long deleteByValueTime = benchmark.benchmarkDeleteByValue(new ArrayList<>(list), operationCount);
        resultsStorage.saveResult(new PerformanceResult(
            collectionType, "deleteByValue", operationCount, deleteByValueTime));
        System.out.println("  deleteByValue: " + deleteByValueTime + " нс");
        
        // Тестируем проверку наличия элемента
        long containsTime = benchmark.benchmarkContains(new ArrayList<>(list), operationCount);
        resultsStorage.saveResult(new PerformanceResult(
            collectionType, "contains", operationCount, containsTime));
        System.out.println("  contains: " + containsTime + " нс");
        
        // Тестируем проверку пустоты
        long isEmptyTime = benchmark.benchmarkIsEmpty(new ArrayList<>(list), operationCount);
        resultsStorage.saveResult(new PerformanceResult(
            collectionType, "isEmpty", operationCount, isEmptyTime));
        System.out.println("  isEmpty: " + isEmptyTime + " нс");
    }
    
    /**
     * Метод performWarmup выполняет "прогрев" JVM для более точных измерений
     * 
     * @param list коллекция для прогрева
     */
    private void performWarmup(List<Integer> list) {
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            benchmark.benchmarkAdd(new ArrayList<>(), 100);
            benchmark.benchmarkGet(new ArrayList<>(list), 100);
            benchmark.benchmarkDeleteByIndex(new ArrayList<>(list), 100);
            benchmark.benchmarkDeleteByValue(new ArrayList<>(list), 100);
            benchmark.benchmarkContains(new ArrayList<>(list), 100);
            benchmark.benchmarkIsEmpty(new ArrayList<>(list), 100);
        }
    }
    
    /**
     * Метод printResultsTable выводит результаты тестирования в виде таблицы
     */
    private void printResultsTable() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("Результаты тестирования производительности на основании времени выполнения методов");
        System.out.println("=".repeat(80));
        System.out.printf("%-15s %-15s %-15s %-20s%n", 
                         "Коллекция", "Метод", "Операций", "Время (мс)");
        System.out.println("-".repeat(80));
        List<PerformanceResult> allResults = resultsStorage.getAllResults();
        for (PerformanceResult result : allResults) {
            System.out.printf("%-15s %-15s %-15d %-20.3f%n",
                            result.getCollectionType(),
                            result.getMethodName(),
                            result.getOperationCount(),
                            result.getExecutionTimeMs());
        }
        printComparisonSummary();
    }
    
    /**
     * Метод printComparisonSummary выводит краткое сравнение результатов
     */
    private void printComparisonSummary() {
        System.out.println("\n" + "-".repeat(80));
        System.out.println("Сравнение производительности ArrayList и LinkedList:");
        System.out.println("-".repeat(80)); 
        for (String method : new String[]{"add", "get", "deleteByIndex", "deleteByValue", "contains", "isEmpty"}) {
            List<PerformanceResult> methodResults = resultsStorage.getResultsByMethod(method);
            if (methodResults.size() >= 2) {
                PerformanceResult arrayListResult = methodResults.get(0);
                PerformanceResult linkedListResult = methodResults.get(1);
                double ratio = linkedListResult.getExecutionTimeMs() / 
                              arrayListResult.getExecutionTimeMs();
                System.out.printf("%-15s: ArrayList %7.3f мс vs LinkedList %7.3f мс (в %6.2f раз %s)%n",
                    method,
                    arrayListResult.getExecutionTimeMs(),
                    linkedListResult.getExecutionTimeMs(),
                    ratio,
                    ratio > 1 ? "медленнее" : "быстрее");
            }
        }
    }
    
    /**
     * Метод main является точкой входа в программу
     * 
     * @param args аргументы командной строки (можно указать количество операций)
     */
    public static void main(String[] args) {
        int operationCount = DEFAULT_OPERATION_COUNT;
        if (args.length > 0) {
            try {
                operationCount = Integer.parseInt(args[0]);
                if (operationCount <= 0) {
                    System.out.println("Количество операций должно быть положительным. " +
                                     "Используется значение по умолчанию: " + DEFAULT_OPERATION_COUNT);
                    operationCount = DEFAULT_OPERATION_COUNT;
                }
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат числа. " +
                                 "Используется значение по умолчанию: " + DEFAULT_OPERATION_COUNT);
            }
        }
        PerformanceComparison comparison = new PerformanceComparison();
        comparison.comparePerformance(operationCount);
    }
}

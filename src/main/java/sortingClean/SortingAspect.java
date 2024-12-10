package sortingClean;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

@org.aspectj.lang.annotation.Aspect
public class SortingAspect {
    private Map<String, Long> totalTimeMap = new HashMap<>();
    private Map<String, Integer> callCountMap = new HashMap<>();

    @Pointcut("execution(* sortingClean.SortingAlgorithm+.sort(..))")
    public void sortMethod() {}

    @Around("sortMethod()")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long timeTaken = System.currentTimeMillis() - startTime;

        totalTimeMap.put(methodName, totalTimeMap.getOrDefault(methodName, 0L) + timeTaken);
        callCountMap.put(methodName, callCountMap.getOrDefault(methodName, 0) + 1);

        return result;
    }

    @After("execution(* sortingClean.AlgorithmRunner.runAlgorithms(..))")
    public void afterAllSorts() {
        long totalRuntime = totalTimeMap.values().stream().mapToLong(Long::longValue).sum();
        System.out.println("Total time of running all sort functions was " + totalRuntime + " ms");
        System.out.println("In detail:");
        totalTimeMap.forEach((key, value) -> {
            System.out.println("Function " + key + " ran " + callCountMap.get(key) + " times and took in total " + value + " ms");
        });
    }
}


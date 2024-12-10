
package sortingClean;
import jakarta.enterprise.inject.Produces;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class MainApp {
    static WeldContainer container = new Weld().initialize();

    public static void main(String[] args) {
        AlgorithmRunner algorithmRunner = container.select(AlgorithmRunner.class).get();
        algorithmRunner.runAlgorithms();
    }
    @Produces
    @Quadratic
    public SortingAlgorithm<Integer> generateQuadraticAlgorithm() {
        return container.select(InsertionSort.class).get();
    }

    @Produces
    @Rnd
    public SortingAlgorithm<Integer> generateRandomAlgorithm() {
        return getRandomSortingAlgorithm();
    }

    @Produces
    @Linearithmic
    public SortingAlgorithm<Integer> generateLinearithmicAlgorithm() {
         return container.select(MergeSort.class).get();
    }



    @Produces
    @LenArr
    public int generateNumberOfElements() {
        return 10000;
    }

    private static SortingAlgorithm<Integer> getRandomSortingAlgorithm() {
        Random random = new Random(System.currentTimeMillis());
        List<Supplier<SortingAlgorithm<Integer>>> algorithms = Arrays.asList(
                () -> container.select(QuickSort.class).get(),
                () -> container.select(MergeSort.class).get(),
                () -> container.select(BubbleSort.class).get(),
                () -> container.select(InsertionSort.class).get()
        );
        return algorithms.get(random.nextInt(algorithms.size())).get();
    }
    }







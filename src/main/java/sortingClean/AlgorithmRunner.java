
package sortingClean;
import jakarta.inject.Inject;
import java.util.Random;
public class AlgorithmRunner {
    @Inject
    @LenArr
    int numberOfElements;
    @Inject
    @Quadratic
     SortingAlgorithm<Integer> quadraticAlgorithm;
    @Inject
    @Linearithmic
    SortingAlgorithm<Integer> nlognAlgorithm;
    @Inject
    @Rnd
    SortingAlgorithm<Integer> randomAlgorithm1;
    @Inject
    @Rnd
    SortingAlgorithm<Integer> randomAlgorithm2;
    public void runAlgorithms() {
        Random rand = new Random();
        Integer[] ints = rand.ints(1, Integer.MAX_VALUE)
                .distinct()
                .limit(numberOfElements)
                .boxed()
                .toArray(Integer[]::new);
        Integer[] intsClone = ints.clone();
        quadraticAlgorithm.sort(intsClone);
        intsClone = ints.clone();
        nlognAlgorithm.sort(intsClone);
        intsClone = ints.clone();
        randomAlgorithm1.sort(intsClone);
        intsClone = ints.clone();
        randomAlgorithm2.sort(intsClone);
    }
}

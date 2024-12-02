import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StandardDeviationTest {
    @Test
    public void testMainMethod() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Call the main method
        String[] args = {};
        StandardDeviation.main(args);

        // Restore System.out
        System.setOut(originalOut);

        // Verify the output
        String output = outputStream.toString().trim();
        assertTrue(output.contains("Sample StdDev ="));
        assertTrue(output.contains("Population StdDev ="));
    }
    @Test
    public void testComputeMean() {
        ArrayList<Double> valuesList = new ArrayList<>();
        valuesList.add(1.0);
        valuesList.add(2.0);
        valuesList.add(3.0);

        double expectedMean = 2.0;
        assertEquals(expectedMean, StandardDeviation.computeMean(valuesList), 0.001);
    }

        @Test
        public void testComputeMeanEmptyList() {
            ArrayList<Double> valuesList = new ArrayList<>();
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                StandardDeviation.computeMean(valuesList);
            });
            assertEquals("valuesList cannot be null or empty", exception.getMessage());
        }

        @Test
        public void testComputeSquareOfDifferences() {
            ArrayList<Double> valuesList = new ArrayList<>();
            valuesList.add(1.0);
            valuesList.add(2.0);
            valuesList.add(3.0);

            double mean = 2.0;
            double expectedSquareSum = 2.0;
            assertEquals(expectedSquareSum, StandardDeviation.computeSquareOfDifferences(valuesList, mean), 0.001);
        }

        @Test
        public void testComputeSquareOfDifferencesEmptyList() {
            ArrayList<Double> valuesList = new ArrayList<>();
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                StandardDeviation.computeSquareOfDifferences(valuesList, 0.0);
            });
            assertEquals("valuesList cannot be null or empty", exception.getMessage());
        }

        @Test
        public void testComputeVariancePopulation() {
            double squareOfDifferences = 2.0;
            int numValues = 3;
            boolean isPopulation = true;

            double expectedVariance = 0.6667;
            assertEquals(expectedVariance, StandardDeviation.computeVariance(squareOfDifferences, numValues, isPopulation), 0.001);
        }

        @Test
        public void testComputeVarianceSample() {
            double squareOfDifferences = 2.0;
            int numValues = 3;
            boolean isPopulation = false;

            double expectedVariance = 1.0;
            assertEquals(expectedVariance, StandardDeviation.computeVariance(squareOfDifferences, numValues, isPopulation), 0.001);
        }

        @Test
        public void testComputeVarianceTooLowNumValues() {
            double squareOfDifferences = 2.0;
            int numValues = 1;
            boolean isPopulation = false;

            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                StandardDeviation.computeVariance(squareOfDifferences, numValues, isPopulation);
            });
            assertEquals("numValues is too low (sample size must be >= 2, population size must be >= 1)", exception.getMessage());
        }

        @Test
        public void testComputeStandardDeviationPopulation() {
            ArrayList<Double> valuesList = new ArrayList<>();
            valuesList.add(1.0);
            valuesList.add(2.0);
            valuesList.add(3.0);

            double expectedStdDev = Math.sqrt(2.0 / 3.0);
            assertEquals(expectedStdDev, StandardDeviation.computeStandardDeviation(valuesList, true), 0.001);
        }

        @Test
        public void testComputeStandardDeviationSample() {
            ArrayList<Double> valuesList = new ArrayList<>();
            valuesList.add(1.0);
            valuesList.add(2.0);
            valuesList.add(3.0);

            double expectedStdDev = 1.0;
            assertEquals(expectedStdDev, StandardDeviation.computeStandardDeviation(valuesList, false), 0.001);
        }

        @Test
        public void testComputeStandardDeviationEmptyList() {
            ArrayList<Double> valuesList = new ArrayList<>();
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                StandardDeviation.computeStandardDeviation(valuesList, true);
            });
            assertEquals("valuesList cannot be null or empty", exception.getMessage());
        }
//    @ParameterizedTest
//    @CsvSource({
//            "2.5, Above Average",
//            "-2.5, Below Average",
//            "0.0, Exactly Average",
//            "1.8, Near Average",
//            "-1.8, Near Average",
//            "2.0, Near Average",
//            "-2.0, Near Average"
//    })
//    void testInterpretStandardDeviation(double stdDev, String expectedLabel) {
//        // Act
//        String actualLabel = StandardDeviation.interpretStandardDeviation(stdDev);
//
//        // Assert
//        assertEquals(expectedLabel, actualLabel);
//    }
}
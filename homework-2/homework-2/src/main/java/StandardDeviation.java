import java.util.ArrayList;

public class StandardDeviation {
    public static void main(String[] args) {
        // Sample values list
        ArrayList<Double> sampleValuesList = new ArrayList<Double>();
        sampleValuesList.add(9.0);
        sampleValuesList.add(6.0);
        sampleValuesList.add(8.0);
        sampleValuesList.add(5.0);
        sampleValuesList.add(7.0);

        // Calculate and print sample standard deviation
        double sampleStdDev = computeStandardDeviation(sampleValuesList, false);
        System.out.println("Sample StdDev = " + sampleStdDev);
        //System.out.println(computeVariance(computeSquareOfDifferences(sampleValuesList,computeMean(sampleValuesList)),));

        // Population values list
        ArrayList<Double> populationValuesList = new ArrayList<Double>();
        populationValuesList.add(9.0);
        populationValuesList.add(2.0);
        populationValuesList.add(5.0);
        // ... add remaining population values

        // Calculate and print population standard deviation
        double popStdDev = computeStandardDeviation(populationValuesList, true);
        System.out.println("Population StdDev = " + popStdDev);

        System.out.println(interpretStandardDeviation(2.5));  // Output: Above Average
        System.out.println(interpretStandardDeviation(-2.5)); // Output: Below Average
        System.out.println(interpretStandardDeviation(0.0));  // Output: Exactly Average
        System.out.println(interpretStandardDeviation(1.8));  // Output: Near Average

    }

    public static double computeMean(ArrayList<Double> valuesList) {
        if (valuesList.isEmpty()) {
            throw new IllegalArgumentException("valuesList cannot be null or empty");
        }

        double sum = 0.0;
        for (double value : valuesList) {
            sum += value;
        }
        return sum / valuesList.size();
    }

    public static double computeSquareOfDifferences(ArrayList<Double> valuesList, double mean) {
        if (valuesList.isEmpty()) {
            throw new IllegalArgumentException("valuesList cannot be null or empty");
        }

        double squareSum = 0.0;
        for (double value : valuesList) {
            double difference = value - mean;
            squareSum += difference * difference;
        };
        return squareSum;
    }

    public static double computeVariance(double squareOfDifferences, int numValues, boolean isPopulation) {
        if (!isPopulation) {
            numValues--; // Adjust for sample standard deviation
        }

        if (numValues < 1) {
            throw new IllegalArgumentException("numValues is too low (sample size must be >= 2, population size must be >= 1)");
        }

        return squareOfDifferences / numValues;
    }

    public static double computeStandardDeviation(ArrayList<Double> valuesList, boolean isPopulation) {
        if (valuesList.isEmpty()) {
            throw new IllegalArgumentException("valuesList cannot be null or empty");
        }

        double mean = computeMean(valuesList);
        double squareOfDifferences = computeSquareOfDifferences(valuesList, mean);
        double variance = computeVariance(squareOfDifferences, valuesList.size(), isPopulation);

        return Math.sqrt(variance);
    }
    public static String interpretStandardDeviation(double stdDev) {
        // Round the standard deviation to one decimal place
        stdDev = Math.round(stdDev * 10.0) / 10.0;

        // Interpret the standard deviation value
        if (stdDev > 2.0) {
            return "Above Average";
        } else if (stdDev < -2.0) {
            return "Below Average";
        } else if (stdDev == 0.0) {
            return "Exactly Average";
        } else {
            return "Near Average";
        }
    }
}

package com.booking.Booking.application.service;

import com.booking.Booking.adapters.dto.DaySchedule;
import com.booking.Booking.adapters.dto.PlaceDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class GeneticAlgorithm {

    /* ===== BUSINESS CONFIG ===== */
    private static final int PLACES_PER_DAY = 6;

    /* ===== GA CONFIG ===== */
    private static final int POPULATION_SIZE = 30;
    private static final int GENERATIONS = 80;
    private static final double MUTATION_RATE = 0.15;

    /* ===== SA CONFIG ===== */
    private static final int SA_ITERATIONS = 200;
    private static final double INITIAL_TEMP = 100;
    private static final double COOLING_RATE = 0.95;

    private static final Random RAND = new Random();

    /* ================= PUBLIC API ================= */
    public static List<DaySchedule> generateItinerary(
            List<PlaceDto> topPlaces,
            LocalDateTime checkIn,
            LocalDateTime checkOut
    ) {
        LocalDate start = checkIn.toLocalDate();
        LocalDate end = checkOut.toLocalDate();

        long totalDays = Math.max(1, end.toEpochDay() - start.toEpochDay());

        List<PlaceDto> shuffled = new ArrayList<>(topPlaces);
        Collections.shuffle(shuffled);

        List<DaySchedule> result = new ArrayList<>();

        for (int d = 0; d < totalDays; d++) {
            int from = d * PLACES_PER_DAY;
            int to = Math.min(from + PLACES_PER_DAY, shuffled.size());

            if (from >= shuffled.size()) break;

            List<PlaceDto> dayPlaces =
                    new ArrayList<>(shuffled.subList(from, to));

            List<PlaceDto> optimized = optimize(dayPlaces);

            result.add(buildDaySchedule(d + 1, optimized));
        }

        return result;
    }

    /* ================= GA + SA CORE ================= */
    private static List<PlaceDto> optimize(List<PlaceDto> places) {
        if (places.size() <= 2) return places;

        List<List<PlaceDto>> population = initPopulation(places);

        for (int gen = 0; gen < GENERATIONS; gen++) {

            // 1. Sort population by fitness (desc)
            population.sort(
                    Comparator.comparingDouble(GeneticAlgorithm::fitness)
                            .reversed()
            );

            List<List<PlaceDto>> nextGen = new ArrayList<>();

            int eliteSize = POPULATION_SIZE / 2;

            // 2. Elitism – keep best individuals
            for (int i = 0; i < eliteSize; i++) {
                nextGen.add(new ArrayList<>(population.get(i)));
            }

            // 3. Crossover + Mutation
            while (nextGen.size() < POPULATION_SIZE) {

                List<PlaceDto> parent1 =
                        population.get(RAND.nextInt(eliteSize));
                List<PlaceDto> parent2 =
                        population.get(RAND.nextInt(eliteSize));

                List<PlaceDto> child = crossover(parent1, parent2);

                if (RAND.nextDouble() < MUTATION_RATE) {
                    mutate(child);
                }

                nextGen.add(child);
            }

            population = nextGen;
        }

        // 4. Best individual after GA
        List<PlaceDto> bestGA = population.stream()
                .max(Comparator.comparingDouble(GeneticAlgorithm::fitness))
                .orElse(places);

        // 5. Local optimization by SA
        return simulatedAnnealing(bestGA);
    }

    /* ================= FITNESS FUNCTION ================= */
    private static double fitness(List<PlaceDto> route) {
        double score = 0;
        double distance = 0;

        for (int i = 0; i < route.size(); i++) {
            score += route.get(i).getScore();

            if (i > 0) {
                distance += haversine(route.get(i - 1), route.get(i));
            }
        }
        return score * 2 - distance;
    }

    /* ================= GA OPERATORS ================= */
    private static List<List<PlaceDto>> initPopulation(List<PlaceDto> places) {
        List<List<PlaceDto>> population = new ArrayList<>();

        for (int i = 0; i < POPULATION_SIZE; i++) {
            List<PlaceDto> individual = new ArrayList<>(places);
            Collections.shuffle(individual);
            population.add(individual);
        }
        return population;
    }

    private static List<PlaceDto> crossover(
            List<PlaceDto> p1,
            List<PlaceDto> p2
    ) {
        int cut = p1.size() / 2;

        Set<PlaceDto> child = new LinkedHashSet<>();
        child.addAll(p1.subList(0, cut));

        for (PlaceDto p : p2) {
            if (child.size() >= p1.size()) break;
            child.add(p);
        }

        return new ArrayList<>(child);
    }

    private static void mutate(List<PlaceDto> route) {
        if (route.size() < 2) return;

        int i = RAND.nextInt(route.size());
        int j = RAND.nextInt(route.size());

        Collections.swap(route, i, j);
    }

    /* ================= SIMULATED ANNEALING ================= */
    private static List<PlaceDto> simulatedAnnealing(List<PlaceDto> init) {

        List<PlaceDto> current = new ArrayList<>(init);
        List<PlaceDto> best = new ArrayList<>(init);

        double temp = INITIAL_TEMP;

        for (int i = 0; i < SA_ITERATIONS; i++) {

            List<PlaceDto> neighbor = new ArrayList<>(current);
            mutate(neighbor);

            double delta =
                    fitness(neighbor) - fitness(current);

            if (delta > 0 ||
                    Math.exp(delta / temp) > RAND.nextDouble()) {
                current = neighbor;
            }

            if (fitness(current) > fitness(best)) {
                best = new ArrayList<>(current);
            }

            temp *= COOLING_RATE;
            if (temp < 1e-3) break;
        }

        return best;
    }

    /* ================= UTILS ================= */
    private static double haversine(PlaceDto a, PlaceDto b) {
        double R = 6371; // km

        double lat1 = a.getLatitude().doubleValue();
        double lon1 = a.getLongitude().doubleValue();
        double lat2 = b.getLatitude().doubleValue();
        double lon2 = b.getLongitude().doubleValue();

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double h =
                Math.sin(dLat / 2) * Math.sin(dLat / 2)
                        + Math.cos(Math.toRadians(lat1))
                        * Math.cos(Math.toRadians(lat2))
                        * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        return 2 * R * Math.asin(Math.sqrt(h));
    }

    private static DaySchedule buildDaySchedule(
            int day,
            List<PlaceDto> places
    ) {
        Map<String, List<PlaceDto>> sessions = new LinkedHashMap<>();

        sessions.put(
                "Morning",
                places.subList(0, Math.min(2, places.size()))
        );

        sessions.put(
                "Afternoon",
                places.subList(
                        Math.min(2, places.size()),
                        Math.min(4, places.size())
                )
        );

        sessions.put(
                "Evening",
                places.subList(
                        Math.min(4, places.size()),
                        Math.min(6, places.size())
                )
        );

        return new DaySchedule(day, sessions);
    }
}

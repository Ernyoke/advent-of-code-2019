package dev.esz.aoc.day14;

import lombok.ToString;
import lombok.Value;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.LongBinaryOperator;
import java.util.stream.Collectors;

public interface Day14 {
    static long part1(List<String> lines) {
        Map<String, Reaction> reactions = parseLines(lines);
        Reaction fuel = reactions.get("FUEL");
        Map<String, Long> fuelIngredients = fuel.getIngredients().stream()
                .collect(Collectors.toMap(Chemical::getName, Chemical::getQuantity));
        return computeORE(fuelIngredients, reactions);
    }

    static long part2(List<String> lines) {
        Map<String, Reaction> reactions = parseLines(lines);
        Map<String, Long> fuelIngredients = reactions.get("FUEL").getIngredients().stream()
                .collect(Collectors.toMap(Chemical::getName, Chemical::getQuantity));

        LongBinaryOperator fuelCalculator = (initialFuel, iter) -> {
            long fuel = initialFuel;
            long ore = 0;
            while (ore < 1000000000000L) {
                fuel += iter;
                long finalFuel = fuel;
                Map<String, Long> updatedFuelIngredients = new HashMap<>(fuelIngredients);
                updatedFuelIngredients.entrySet().forEach(entry -> entry.setValue(entry.getValue() * finalFuel));
                ore = computeORE(updatedFuelIngredients, reactions);
            }
            return fuel;
        };

        long fuel = fuelCalculator.applyAsLong(1000000000000L / computeORE(new HashMap<>(fuelIngredients), reactions), 1000L);
        fuel = fuelCalculator.applyAsLong(fuel - 1000, 100L);
        fuel = fuelCalculator.applyAsLong(fuel - 100, 10L);
        fuel = fuelCalculator.applyAsLong(fuel - 100, 1L);

        return fuel - 1;
    }

    private static Map<String, Reaction> parseLines(List<String> lines) {
        return lines.stream()
                .map(Day14::parseReaction)
                .collect(Collectors.toMap(chemical -> chemical.getFinalProduct().getName(), chemical -> chemical));
    }

    private static Reaction parseReaction(String reactionLine) {
        String[] parts = reactionLine.split(" => ");
        List<Chemical> ingredients = parseIngredients(parts[0]);
        Chemical product = Chemical.fromString(parts[1]);
        return new Reaction(product, ingredients);
    }

    private static List<Chemical> parseIngredients(String ingredientsString) {
        String[] parts = ingredientsString.trim().split(", ");
        return Arrays.stream(parts)
                .map(Chemical::fromString)
                .collect(Collectors.toList());
    }

    private static long computeORE(Map<String, Long> fuelIngredients, Map<String, Reaction> reactions) {
        Map<String, Long> leftOvers = new HashMap<>();
        while (fuelIngredients.entrySet().size() > 1) {
            Chemical chemical = getChemicalNotORE(fuelIngredients);
            fuelIngredients.remove(chemical.getName());

            // Handle leftover chemicals. Do not try to use other ingredients in case there are leftover from that
            // type of chemical.
            if (leftOvers.containsKey(chemical.getName())) {
                long leftOverQuantity = leftOvers.get(chemical.getName());
                if (chemical.getQuantity() >= leftOverQuantity) {
                    leftOvers.remove(chemical.getName());
                    chemical = new Chemical(chemical.getName(), chemical.getQuantity() - leftOverQuantity);
                } else {
                    leftOvers.put(chemical.getName(), leftOverQuantity - chemical.getQuantity());
                    chemical = new Chemical(chemical.getName(), 0);
                }
            }

            if (chemical.getQuantity() > 0) {
                long producibleQuantity = reactions.get(chemical.getName()).getFinalProduct().getQuantity();
                long multiplier = chemical.getQuantity() / producibleQuantity;
                long additionalQuantity = chemical.getQuantity() % producibleQuantity;
                if (additionalQuantity > 0) {
                    multiplier++;
                    long leftOver = producibleQuantity * multiplier - chemical.getQuantity();
                    leftOvers.computeIfPresent(chemical.getName(), (key, value) -> value + leftOver);
                    leftOvers.putIfAbsent(chemical.getName(), leftOver);
                }
                List<Chemical> ingredients = reactions.get(chemical.getName()).getIngredients();
                for (Chemical ingredient : ingredients) {
                    long quantity = ingredient.getQuantity() * multiplier;
                    fuelIngredients.computeIfPresent(ingredient.getName(), (key, value) -> value + quantity);
                    fuelIngredients.putIfAbsent(ingredient.getName(), quantity);
                }
            }
        }
        return fuelIngredients.get("ORE");
    }

    private static Chemical getChemicalNotORE(Map<String, Long> chemicals) {
        for (var entry : chemicals.entrySet()) {
            if (!entry.getKey().equals("ORE")) {
                return new Chemical(entry.getKey(), entry.getValue());
            }
        }
        throw new IllegalStateException();
    }
}

@Value
@ToString
class Reaction {
    Chemical finalProduct;
    List<Chemical> ingredients;
}

@Value
@ToString
class Chemical {
    String name;
    long quantity;

    public static Chemical fromString(String chemicalString) {
        String[] parts = chemicalString.trim().split(" ");
        return new Chemical(parts[1], Long.parseLong(parts[0]));
    }
}

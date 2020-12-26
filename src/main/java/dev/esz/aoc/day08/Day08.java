package dev.esz.aoc.day08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface Day08 {
    static long part1(String input) {
        List<List<String>> layers = getLayers(input);
        List<String> minLayer = containsFewestZeros(layers);
        return countDigits(minLayer, "1") * countDigits(minLayer, "2");
    }

    static void part2(String input) {
        List<List<String>> layers = getLayers(input);
        renderImage(layers);
    }

    private static List<List<String>> getLayers(String input) {
        String layersLine = input;
        int n = 6;
        int m = 25;
        int nm = n * m;
        List<List<String>> layers = new ArrayList<>();
        for (int i = 0; i < input.length() / nm; i++) {
            String layerLine = layersLine.substring(0, nm);
            layersLine = layersLine.substring(nm);
            List<String> layer = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                layer.add(layerLine.substring(j * m, (j + 1) * m));
            }
            layers.add(layer);
        }
        return layers;
    }

    private static List<String> containsFewestZeros(List<List<String>> layers) {
        long minZeros = Integer.MAX_VALUE;
        List<String> minLayer = null;
        for (List<String> layer : layers) {
            long nrZeros = countDigits(layer, "0");
            if (minZeros > nrZeros) {
                minLayer = layer;
                minZeros = nrZeros;
            }
        }
        return minLayer;
    }

    private static long countDigits(List<String> layer, String digit) {
        return layer.stream()
                .flatMap(line -> Arrays.stream(line.split("")))
                .filter(digit::equals)
                .count();
    }

    private static void renderImage(List<List<String>> layers) {
        List<StringBuilder> builders = new ArrayList<>();
        List<String> firstLayer = layers.get(0);

        for (String s : firstLayer) {
            builders.add(new StringBuilder(s));
        }

        for (int i = 1; i < layers.size(); i++) {
            List<String> layer = layers.get(i);
            for (int x = 0; x < layer.size(); x++) {
                String layerLine = layer.get(x);
                StringBuilder builder = builders.get(x);
                for (int y = 0; y < layerLine.length(); y++) {
                    if (builder.charAt(y) == '2') {
                        builder.setCharAt(y, layerLine.charAt(y));
                    }
                }
            }
        }

        for (StringBuilder builder : builders) {
            System.out.println(builder.toString().replace("0", "."));
        }
    }
}

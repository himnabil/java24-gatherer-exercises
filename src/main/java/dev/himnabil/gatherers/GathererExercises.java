package dev.himnabil.gatherers;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.*;
import java.util.stream.Gatherers;

public class GathererExercises {

    private static class AdjacentDuplicatesState<T> {
        private T current = null;
        private int count = 0;

        Optional<List<T>> integrate (T element) {

            if (current == null) {
                reset(element);
                return Optional.empty();
            }
            if (current.equals(element)) {
                count++;
                return Optional.empty();
            }
            var list = toList();
            reset(element);
            return Optional.of(list);
        }

        private List<T> toList() {
            return Stream
                    .generate(()-> current)
                    .limit(count)
                    .toList();
        }

        private void reset(T element) {
            current = element;
            count = 1;
        }
    }

    public static <T> Gatherer<T, ?, List<T>> gatherAdjacentDuplicates() {

        return Gatherer.ofSequential(
                () -> new AdjacentDuplicatesState<T>() ,
                (state, element, downstream) -> {
                    state.integrate(element)
                            .map(downstream::push);
                    return true;
                },
                (state, downstream) -> {
                   downstream.push(state.toList());
                }
        );
    }

    public static <T> Gatherer<T, ?, List<T>> slidingWindow(int size) {
        return Gatherers.windowSliding(size);
    }

    public static <T> Gatherer<T, ?, List<T>> bufferUntil(Predicate<T> isSeparator) {
        return Gatherer.ofSequential(() -> new ArrayList<T>(),
            (acc, t, downstream) -> {
                if (isSeparator.test(t)) {
                    if (!acc.isEmpty()) downstream.push(List.copyOf(acc));
                    acc.clear();
                } else {
                    acc.add(t);
                }
                return true;
            }, (acc, downstream) -> {
                if (!acc.isEmpty()) downstream.push(List.copyOf(acc));
            });
    }
}

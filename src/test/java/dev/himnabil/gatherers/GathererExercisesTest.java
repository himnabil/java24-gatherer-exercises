package dev.himnabil.gatherers;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static dev.himnabil.gatherers.GathererExercises.*;

class GathererExercisesTest {

    @Test
    void testGatherAdjacentDuplicates() {
        var result = Stream.of("a", "a", "b", "b", "b", "c", "a")
            .gather(gatherAdjacentDuplicates())
            .toList();

        assertThat(result).containsExactly(
            List.of("a", "a"),
            List.of("b", "b", "b"),
            List.of("c"),
            List.of("a")
        );
    }

    @Test
    void testSlidingWindow() {
        var result = Stream.of(1, 2, 3, 4, 5)
            .gather(slidingWindow(3))
            .toList();

        assertThat(result).containsExactly(
            List.of(1, 2, 3),
            List.of(2, 3, 4),
            List.of(3, 4, 5)
        );
    }

    @Test
    void testBufferUntil() {
        var result = Stream.of(1, 2, 3, 0, 4, 5, 0, 6)
            .gather(bufferUntil(i -> i == 0))
            .toList();

        assertThat(result).containsExactly(
            List.of(1, 2, 3),
            List.of(4, 5),
            List.of(6)
        );
    }
}

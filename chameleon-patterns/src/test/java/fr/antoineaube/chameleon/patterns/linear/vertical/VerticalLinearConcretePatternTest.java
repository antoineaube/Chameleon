package fr.antoineaube.chameleon.patterns.linear.vertical;

import fr.antoineaube.chameleon.core.pictures.Picture;
import fr.antoineaube.chameleon.core.pictures.structures.Position;
import fr.antoineaube.chameleon.core.processes.ConcreteConcealmentPattern;
import fr.antoineaube.chameleon.patterns.tests.helpers.SimpleTestPicture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class VerticalLinearConcretePatternTest {

    private List<Picture> PICTURES_SAMPLES = Arrays.asList(
            new SimpleTestPicture(1, 1),
            new SimpleTestPicture(3, 20),
            new SimpleTestPicture(20, 3),
            new SimpleTestPicture(20, 20)
    );

    @DisplayName("Should have W*H next positions in a (W, H)-shaped picture")
    @TestFactory
    Stream<DynamicTest> shouldHaveEnoughNextPositions() {
        return PICTURES_SAMPLES.stream().map(picture -> {
            String caseName = "With a (" + picture.getWidth() + ", " + picture.getHeight() + ")-shaped picture";
            return DynamicTest.dynamicTest(caseName, () -> {
                ConcreteConcealmentPattern pattern = new VerticalLinearPattern().createPattern(picture, false);

                int expectedAmount = picture.getWidth() * picture.getHeight();

                assertEquals(expectedAmount, new VerticalLinearPattern().countUsedPixels(picture));

                int actualAmount = 0;
                while (actualAmount < expectedAmount && pattern.hasNext()) {
                    actualAmount++;
                    pattern.nextPosition();
                }

                assertEquals(expectedAmount, actualAmount);

                assertFalse(pattern.hasNext());
            });
        });
    }

    @DisplayName("Should correctly browse a squared picture")
    @Test
    void shouldCorrectlyBrowseASquaredPicture() {
        VerticalLinearConcretePattern pattern = new VerticalLinearConcretePattern(new SimpleTestPicture(3, 3));

        assertEquals(new Position(0, 0), pattern.nextPosition());
        assertEquals(new Position(0, 1), pattern.nextPosition());
        assertEquals(new Position(0, 2), pattern.nextPosition());
        assertEquals(new Position(1, 0), pattern.nextPosition());
        assertEquals(new Position(1, 1), pattern.nextPosition());
        assertEquals(new Position(1, 2), pattern.nextPosition());
        assertEquals(new Position(2, 0), pattern.nextPosition());
        assertEquals(new Position(2, 1), pattern.nextPosition());
        assertEquals(new Position(2, 2), pattern.nextPosition());
    }
}
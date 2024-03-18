package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;

public class RangeTest {

    private Range exampleRange;

    // private Range exampleRange1;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        exampleRange = new Range(-1, 1);
    }

    // @Before
    // public void setUp() throws Exception { exampleRange1 = new Range(1, 2);
    // }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidArguments() {
        new Range(10, 5);
    }

    @Test
    public void testGetLowerBound() {
        Range range = new Range(-1, 1);
        assertEquals("Lower bound should be -1", -1, range.getLowerBound(), 0);
    }

    @Test
    public void testGetUpperBound() {
        Range range = new Range(-1, 1);
        assertEquals("Upper bound should be 1", 1, range.getUpperBound(), 0);
    }

    @Test
    public void testGetLength() {
        Range range = new Range(0, 10);
        assertEquals("Length should be 10", 10, range.getLength(), 0);
    }

    @Test
    public void testGetCentralValue() {
        Range range = new Range(0, 10);
        assertEquals("Central value should be 5", 5, range.getCentralValue(), 0);
    }

    @Test
    public void testContains() {
        Range range = new Range(0, 10);
        assertTrue("Should contain 5", range.contains(5));
        assertFalse("Should not contain -5", range.contains(-5));
        assertTrue("Should contain 0", range.contains(0)); // Boundary
        assertTrue("Should contain 10", range.contains(10)); // Boundary
    }

    @Test
    public void testIntersects() {
        Range range = new Range(0, 10);
        assertTrue("Should intersect with overlapping range", range.intersects(5, 15));
        assertFalse("Should not intersect with non-overlapping range", range.intersects(11, 15));
        assertTrue("Should intersect with contained range", range.intersects(2, 8));
        assertFalse("Should not intersect with adjacent range below", range.intersects(-5, 0));
        assertFalse("Should not intersect with adjacent range above", range.intersects(10, 15));
    }

    @Test
    public void testConstrain() {
        Range range = new Range(0, 10);
        assertEquals("Constraining within range should return value", 5, range.constrain(5), 0);
        assertEquals("Constraining below range should return lower bound", 0, range.constrain(-1), 0);
        assertEquals("Constraining above range should return upper bound", 10, range.constrain(11), 0);
    }

    @Test
    public void testCombine() {
        Range range1 = new Range(0, 5);
        Range range2 = new Range(5, 10);
        Range combined = Range.combine(range1, range2);
        assertEquals("Combined range should span from 0 to 10", new Range(0, 10), combined);
    }

    @Test
    public void testShift() {
        Range range = new Range(0, 10);
        Range shifted = Range.shift(range, 5);
        assertEquals("Shifted range should be from 5 to 15", new Range(5, 15), shifted);
    }

    @Test
    public void testScale() {
        Range range = new Range(1, 2);
        Range scaled = Range.scale(range, 2);
        assertEquals("Scaled range should be from 2 to 4", new Range(2, 4), scaled);
    }

    @Test
    public void testEqualsAndHashCode() {
        Range range1 = new Range(0, 10);
        Range range2 = new Range(0, 10);
        Range range3 = new Range(1, 11);

        assertTrue("Ranges should be equal", range1.equals(range2));
        assertFalse("Ranges should not be equal", range1.equals(range3));
        assertEquals("Hash codes should be equal for equal ranges", range1.hashCode(), range2.hashCode());
        assertNotEquals("Hash codes should not be equal for different ranges", range1.hashCode(), range3.hashCode());
    }

    @Test
    public void testToString() {
        Range range = new Range(1, 2);
        String expectedString = "Range[1.0,2.0]";
        assertEquals("toString should return correct string", expectedString, range.toString());
    }

    @Test
    public void testIsNaNRange() {
        Range nanRange = new Range(Double.NaN, Double.NaN);
        assertTrue("Range with NaN bounds should be considered NaN range", nanRange.isNaNRange());

        Range normalRange = new Range(0, 1);
        assertFalse("Normal range should not be considered NaN range", normalRange.isNaNRange());
    }

    @Test
    public void testRangeIntersectsWithMutations() {
        Range range = new Range(1, 5);

        // Test intersects with adjacent range (boundary condition)
        assertFalse("Should not intersect with an adjacent range starting exactly at the upper bound",
                range.intersects(5, 10));

        // Test intersects within the range (conditionals boundary)
        assertTrue("Should intersect with a range fully within the bounds",
                range.intersects(2, 4));

        // Test no intersection above the range (arithmetic operations on bounds)
        assertFalse("Should not intersect with a range completely above the upper bound",
                range.intersects(6, 10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRangeConstructionWithInvalidArguments() {
        // Directly targets constructor call mutator by ensuring exception is thrown for
        // invalid arguments
        new Range(5, -1);
    }

    @Test
    public void testRangeShiftAllowsZeroCrossing() {
        // Specifically target shift with zero crossing to address boolean mutators and
        // conditionals
        Range originalRange = new Range(-1, 1);
        Range shiftedRange = Range.shift(originalRange, -3, true); // Allows zero crossing
        assertEquals("Shift allowing zero crossing did not work as expected",
                new Range(-4, -2), shiftedRange);
    }

    @Test
    public void testCombineWithNaN() {
        Range range1 = new Range(1, 3);
        Range rangeNaN = new Range(Double.NaN, Double.NaN);
        Range result = Range.combine(range1, rangeNaN);

        assertTrue("Combining with NaN range lower bound should be NaN", Double.isNaN(result.getLowerBound()));
        assertTrue("Combining with NaN range upper bound should be NaN", Double.isNaN(result.getUpperBound()));
    }

    @Test
    public void testExpandWithExtremeValues() {
        Range range = new Range(1, 10);
        Range expanded = Range.expand(range, Double.MIN_VALUE, Double.MAX_VALUE);
        assertNotNull("Expanded range should not be null", expanded);
        assertTrue("Expanded range should include original bounds",
                expanded.contains(range.getLowerBound()) && expanded.contains(range.getUpperBound()));
    }

    @Test
    public void testShiftWithZeroDelta() {
        Range range = new Range(-5, 5);
        Range shifted = Range.shift(range, 0);
        assertEquals("Shifting by zero should return identical range", range, shifted);
    }

    @Test
    public void testEqualsWithDifferentTypes() {
        Range range = new Range(0, 1);
        assertFalse("Range should not be equal to a non-range type", range.equals(new Object()));
    }

    @Test
    public void testEqualsWithNull() {
        Range range = new Range(0, 1);
        assertFalse("Range should not be equal to null", range.equals(null));
    }

    @Test
    public void testIntersectsAdjacentRanges() {
        Range range1 = new Range(0, 5);
        Range range2 = new Range(5, 10);
        assertFalse("Adjacent ranges should not be considered as intersecting", range1.intersects(range2));
    }

    @Test
    public void testContainsExactBoundaries() {
        Range range = new Range(-1, 1);
        assertTrue("Should contain lower boundary", range.contains(-1));
        assertTrue("Should contain upper boundary", range.contains(1));
    }

    @Test
    public void testContainsNearBoundaries() {
        Range range = new Range(-1, 1);
        assertFalse("Should not contain just outside lower boundary", range.contains(-1.0001));
        assertFalse("Should not contain just outside upper boundary", range.contains(1.0001));
    }

    @Test
    public void testIntersectsTouchingBoundaries() {
        Range range = new Range(0, 10);
        assertFalse("Should not intersect with range touching lower boundary", range.intersects(-5, 0));
        assertFalse("Should not intersect with range touching upper boundary", range.intersects(10, 15));
    }

    @Test
    public void testIntersectsEntirelyOutsideRanges() {
        Range range = new Range(10, 20);
        assertFalse("Should not intersect with range entirely below", range.intersects(0, 5));
        assertFalse("Should not intersect with range entirely above", range.intersects(25, 30));
    }

    @Test
    public void testConstrainBoundaryValues() {
        Range range = new Range(0, 10);
        assertEquals("Constraining to lower boundary should return lower bound", 0, range.constrain(0), 0);
        assertEquals("Constraining to upper boundary should return upper bound", 10, range.constrain(10), 0);
    }

    @Test
    public void testConstrainNearBoundaryValues() {
        Range range = new Range(0, 10);
        assertEquals("Constraining just outside lower boundary should return lower bound", 0, range.constrain(-0.0001),
                0);
        assertEquals("Constraining just outside upper boundary should return upper bound", 10, range.constrain(10.0001),
                0);
    }

    @Test
    public void testCombineIgnoringNaNBothNull() {
        assertNull("Combining two null ranges should return null", Range.combineIgnoringNaN(null, null));
    }

    @Test
    public void testCombineIgnoringNaNFirstNullSecondNaN() {
        Range range2 = new Range(Double.NaN, Double.NaN);
        assertNull("Combining null with NaN range should return null", Range.combineIgnoringNaN(null, range2));
    }

    @Test
    public void testCombineIgnoringNaNFirstNaNSecondNull() {
        Range range1 = new Range(Double.NaN, Double.NaN);
        assertNull("Combining NaN range with null should return null", Range.combineIgnoringNaN(range1, null));
    }

    @Test
    public void testCombineIgnoringNaNBothNaN() {
        Range range1 = new Range(Double.NaN, Double.NaN);
        Range range2 = new Range(Double.NaN, Double.NaN);
        assertNull("Combining two NaN ranges should return null", Range.combineIgnoringNaN(range1, range2));
    }

    @Test
    public void testCombineIgnoringNaNFirstValidSecondNaN() {
        Range range1 = new Range(1, 3);
        Range range2 = new Range(Double.NaN, Double.NaN);
        assertEquals("Combining valid range with NaN range should return the valid range", range1,
                Range.combineIgnoringNaN(range1, range2));
    }

    @Test
    public void testCombineIgnoringNaNFirstNaNSecondValid() {
        Range range1 = new Range(Double.NaN, Double.NaN);
        Range range2 = new Range(1, 3);
        assertEquals("Combining NaN range with valid range should return the valid range", range2,
                Range.combineIgnoringNaN(range1, range2));
    }

    @Test
    public void testCombineIgnoringNaNBothValid() {
        Range range1 = new Range(1, 3);
        Range range2 = new Range(2, 4);
        Range expected = new Range(1, 4);
        assertEquals("Combining two valid ranges should return their union", expected,
                Range.combineIgnoringNaN(range1, range2));
    }

    @Test
    public void testExpandToIncludeWithNullRange() {
        double value = 5;
        Range result = Range.expandToInclude(null, value);
        assertEquals("Expanding null range to include a value should create a new range from that value",
                new Range(value, value), result);
    }

    @Test
    public void testExpandToIncludeValueLessThanLowerBound() {
        Range range = new Range(1, 10);
        double value = -1;
        Range result = Range.expandToInclude(range, value);
        assertEquals("Expanding to include a value less than the lower bound should update the lower bound",
                new Range(value, 10), result);
    }

    @Test
    public void testExpandToIncludeValueGreaterThanUpperBound() {
        Range range = new Range(1, 10);
        double value = 15;
        Range result = Range.expandToInclude(range, value);
        assertEquals("Expanding to include a value greater than the upper bound should update the upper bound",
                new Range(1, value), result);
    }

    @Test
    public void testExpandToIncludeValueWithinRange() {
        Range range = new Range(1, 10);
        double value = 5;
        Range result = Range.expandToInclude(range, value);
        assertEquals("Expanding to include a value within the range should not change the range", range, result);
    }

    @Test
    public void testIsNaNRangeTrue() {
        Range range = new Range(Double.NaN, Double.NaN);
        assertTrue("Range with both bounds NaN should return true for isNaNRange", range.isNaNRange());
    }

    @Test
    public void testIsNaNRangeFalse() {
        Range range = new Range(0, 1);
        assertFalse("Range with valid bounds should return false for isNaNRange", range.isNaNRange());
    }

    @Test
    public void testIsNaNRangeWithOneBoundNaN() {
        Range range1 = new Range(Double.NaN, 1);
        Range range2 = new Range(0, Double.NaN);
        assertFalse("Range with one NaN bound should return false for isNaNRange", range1.isNaNRange());
        assertFalse("Range with one NaN bound should return false for isNaNRange", range2.isNaNRange());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testScaleWithNegativeFactor() {
        Range base = new Range(1, 10);
        double negativeFactor = -1;
        Range.scale(base, negativeFactor);
    }

    @Test
    public void testEqualsWithDifferentUpperBounds() {
        Range range1 = new Range(1, 10);
        Range range2 = new Range(1, 11); // Same lower bound, different upper bound

        // This should return false since the upper bounds are different
        assertFalse("Ranges with different upper bounds should not be equal", range1.equals(range2));
    }

    @Test
    public void testExpandLowerGreaterThanUpperAdjustment() {
        // Original range
        Range originalRange = new Range(0, 10);
        // Margins that will reverse the lower and upper bounds
        double lowerMargin = 2; // 200% expansion on lower side
        double upperMargin = -1; // -100% contraction on upper side

        Range expandedRange = Range.expand(originalRange, lowerMargin, upperMargin);

        // After expansion, the adjusted logic should make upper >= lower
        assertTrue("Expanded range's lower bound should be less than or equal to its upper bound",
                expandedRange.getLowerBound() <= expandedRange.getUpperBound());
    }

    @Test
    public void testCombineFirstRangeIsNull() {
        Range range1 = null;
        Range range2 = new Range(1, 5);

        Range result = Range.combine(range1, range2);

        // Assert that the result is equal to range2 since range1 is null
        assertEquals("When first range is null, combine should return the second range", range2, result);
    }

    @Test
    public void testCombineSecondRangeIsNull() {
        Range range1 = new Range(1, 5);
        Range range2 = null;

        Range result = Range.combine(range1, range2);

        // Assert that the result is equal to range1 since range2 is null
        assertEquals("When second range is null, combine should return the first range", range1, result);
    }

    @Test
    public void testContainsValueWithinRange() {
        Range range = new Range(10, 20);

        // Value less than the lower bound
        assertFalse("Value less than the lower bound should return false", range.contains(5));

        // Value greater than the upper bound
        assertFalse("Value greater than the upper bound should return false", range.contains(25));

        // Value within the range
        assertTrue("Value within the range should return true", range.contains(15));

        // Value exactly at the lower bound
        assertTrue("Value at the lower bound should return true", range.contains(10));

        // Value exactly at the upper bound
        assertTrue("Value at the upper bound should return true", range.contains(20));
    }

    @Test
    public void testExpandWithLowerGreaterThanUpperCorrection() {
        Range originalRange = new Range(10, 20);
        // Setting margins that would logically invert the lower and upper bounds
        double lowerMargin = 2; // This will attempt to subtract twice the range length from the lower bound
        double upperMargin = -0.5; // This will attempt to subtract half the range length from the upper bound

        Range result = Range.expand(originalRange, lowerMargin, upperMargin);

        // Verify that the correction was applied, meaning the lower bound should not
        // actually be greater than the upper bound
        assertTrue("After correction, lower bound should be less than or equal to upper bound",
                result.getLowerBound() <= result.getUpperBound());
    }

    @Test
    public void testShiftAllowZeroCrossingTrue() {
        Range base = new Range(-5, 5);
        Range shifted = Range.shift(base, 10, true);
        assertEquals("Shift with zero crossing allowed fails", new Range(5, 15), shifted);
    }

    @Test
    public void testShiftAllowZeroCrossingFalse() {
        Range base = new Range(5, 15);
        Range shifted = Range.shift(base, -10, false);
        assertTrue("Shift with zero crossing not allowed fails", shifted.getLowerBound() >= 0);
    }

    @Test
    public void testContainsInfinityAndNaN() {
        Range range = new Range(1, 100);

        assertFalse("Should not contain positive infinity", range.contains(Double.POSITIVE_INFINITY));
        assertFalse("Should not contain negative infinity", range.contains(Double.NEGATIVE_INFINITY));
        assertFalse("Should not contain NaN", range.contains(Double.NaN));
    }

    @Test
    public void testCombineIgnoringNaNNormalRanges() {
        Range range1 = new Range(1, 5);
        Range range2 = new Range(6, 10);
        Range combined = Range.combineIgnoringNaN(range1, range2);
        assertEquals("Combining normal ranges fails", new Range(1, 10), combined);
    }

    @Test
    public void testExpandByZeroMargin() {
        Range range = new Range(10, 20);
        Range expanded = Range.expand(range, 0, 0);
        assertEquals("Expanding by zero margin should not change the range", range, expanded);
    }

    @Test
    public void testExpandByNegativeMargin() {
        Range range = new Range(10, 20);
        // Using negative margins to contract the range
        Range contracted = Range.expand(range, -0.1, -0.1);

        // Verify the range has contracted as expected
        assertTrue("Expanded (contracted) range lower bound should be greater than original",
                contracted.getLowerBound() > range.getLowerBound());
        assertTrue("Expanded (contracted) range upper bound should be less than original",
                contracted.getUpperBound() < range.getUpperBound());
    }

    @Test
    public void testScaleWithZeroFactor() {
        Range base = new Range(1, 10);
        // Attempting to scale by zero, expecting the method to complete without
        // exception
        Range result = Range.scale(base, 0);
        // If it scales bounds to 0, verify against that expected behavior.
        assertEquals("Scaling with zero factor should result in a range starting at 0", 0, result.getLowerBound(),
                0.0000001);
        assertEquals("Scaling with zero factor should result in a range ending at 0", 0, result.getUpperBound(),
                0.0000001);
    }

    @Test
    public void testScaleWithPositiveFactor() {
        Range base = new Range(1, 10);
        Range scaled = Range.scale(base, 2);
        assertEquals("Scaling with positive factor fails", new Range(2, 20), scaled);
    }

    @Test
    public void testShiftByPositiveInfinity() {
        Range base = new Range(1, 10);
        Range shifted = Range.shift(base, Double.POSITIVE_INFINITY);
        assertTrue("Shifting by positive infinity should result in upper bound of positive infinity",
                Double.isInfinite(shifted.getUpperBound()) && shifted.getUpperBound() > 0);
        assertTrue("Shifting by positive infinity should result in lower bound of positive infinity",
                Double.isInfinite(shifted.getLowerBound()) && shifted.getLowerBound() > 0);
    }

    @Test
    public void testScaleByPositiveInfinity() {
        Range base = new Range(1, 10);
        Range scaled = Range.scale(base, Double.POSITIVE_INFINITY);
        assertEquals("Scaling by positive infinity should set lower bound to positive infinity",
                Double.POSITIVE_INFINITY, scaled.getLowerBound(), 0.0);
        assertEquals("Scaling by positive infinity should set upper bound to positive infinity",
                Double.POSITIVE_INFINITY, scaled.getUpperBound(), 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testScaleByNegativeInfinity() {
        Range base = new Range(1, 10);
        Range.scale(base, Double.NEGATIVE_INFINITY);
    }

    @Test
    public void testContainsNaN() {
        Range range = new Range(1, 100);
        assertFalse("Range should not contain NaN", range.contains(Double.NaN));
    }

    @Test
    public void testIntersectsNaN() {
        Range range = new Range(1, 100);
        assertFalse("Range should not report intersecting with NaN boundaries",
                range.intersects(Double.NaN, Double.NaN));
    }

    @Test
    public void testCombineIgnoringNaNWithNaNAndNormalRange() {
        Range nanRange = new Range(Double.NaN, Double.NaN);
        Range normalRange = new Range(1, 10);
        Range combined = Range.combineIgnoringNaN(nanRange, normalRange);
        assertEquals("Combining NaN range with a normal range should ignore NaN range",
                normalRange, combined);
    }

    @Test
    public void testExpandToIncludeWithExtremeValues() {
        Range range = new Range(1, 10);
        Range expanded = Range.expandToInclude(range, Double.POSITIVE_INFINITY);
        assertEquals("Expanding to include positive infinity should adjust upper bound to positive infinity",
                Double.POSITIVE_INFINITY, expanded.getUpperBound(), 0.0);
    }

    // Test cases for combineIgnoringNaN method
    @Test
    public void testCombineIgnoringNaN_WithBothRangesNull_ShouldReturnNull() {
        assertNull(Range.combineIgnoringNaN(null, null));
    }

    @Test
    public void testCombineIgnoringNaN_WithOneRangeNullAndOtherNaN_ShouldReturnNull() {
        assertNull(Range.combineIgnoringNaN(null, new Range(Double.NaN, Double.NaN)));
        assertNull(Range.combineIgnoringNaN(new Range(Double.NaN, Double.NaN), null));
    }

    @Test
    public void testCombineIgnoringNaN_WithRegularRanges_ShouldCombineRangesIgnoringNaN() {
        assertEquals(new Range(1, 3), Range.combineIgnoringNaN(new Range(1, 2), new Range(2, 3)));
    }

    @Test
    public void testCombineIgnoringNaN_BothRangesNaN() {
        Range range1 = new Range(Double.NaN, Double.NaN);
        Range range2 = new Range(Double.NaN, Double.NaN);
        Range combinedRange = Range.combineIgnoringNaN(range1, range2);
        assertNull(combinedRange);
    }

    @Test
    public void testCombineIgnoringNaN_EdgeCases() {
        Range range1 = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
        Range range2 = new Range(-Double.MAX_VALUE, Double.NaN);
        Range combinedRange = Range.combineIgnoringNaN(range1, range2);
        assertEquals(new Range(-Double.MAX_VALUE, Double.MAX_VALUE), combinedRange);
    }

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}

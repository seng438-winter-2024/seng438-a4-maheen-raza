package org.jfree.data;
import org.w3c.dom.ranges.Range;  
import static org.junit.Assert.*; import org.junit.*;

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

    // Tests for combine

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

    // combine
    @Test
    public void testCombineIgnoringNaNNormalRanges() {
        Range range1 = new Range(1, 5);
        Range range2 = new Range(6, 10);
        Range combined = Range.combineIgnoringNaN(range1, range2);
        assertEquals("Combining normal ranges fails", new Range(1, 10), combined);
    }

    // test for expand
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

        // Verify the result is as expected under your method's behavior for a zero
        // factor
        // This assertion depends on how Range.scale handles a zero factor.
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

    // test isNaNRange method
    @Test
    public void testIsNaNRangeWithNaN() {
        Range range = new Range(Double.NaN, Double.NaN);
        assertTrue(range.isNaNRange()); // Both lower and upper bounds are NaN
    }

    @Test
    public void testIsNaNRangeWithFiniteValues() {
        Range range = new Range(0, 5);
        assertFalse(range.isNaNRange()); // None of the bounds are NaN
    }

    @Test
    public void testIsNaNRangeWithOneNaN() {
        Range range = new Range(Double.NaN, 5);
        assertFalse(range.isNaNRange()); // Only one bound is NaN
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUpperBoundWithInvalidRange() {
        Range range = new Range(5, 3);
        range.getUpperBound(); // This should throw an IllegalArgumentException
    }

    // ADDITIONAL TEST CASES TO INCREASE MUTATION COVERAGE

    @Test
    public void testIntersects_LowerBoundNotRetreieved() {
        // Create two ranges with different lower bounds
        Range range1 = new Range(0, 10); // Lower bound: 0
        Range range2 = new Range(5, 15); // Lower bound: 5

        // Check for intersection
        boolean result = range1.intersects(range2);

        // Assert that the result is true since the ranges intersect
        assertTrue("Ranges should intersect", result);

        // Modify range2 to ensure it does not intersect with range1
        range2 = new Range(-5, 0); // Lower bound: -5

        // Check for intersection
        result = range1.intersects(range2);

        // Assert that the result is false since the ranges do not intersect
        assertFalse("Ranges should not intersect", result);
    }

    @Test
    public void testIntersects_ChangedConditionalBoundary() {
        // Create a Range object with a known lower bound
        Range range = new Range(5.0, 10.0); // Original lower: 5.0

        // Test with b0 less than mutated lower and b1 greater than mutated lower
        assertFalse("Ranges should not intersect", range.intersects(-10.0, 0.0));
        assertTrue("Ranges should intersect", range.intersects(-10.0, 20.0));
    }

    @Test
    public void testIntersects_NegatedDoubleFieldLower() {
        // Create a Range object with a known lower bound
        Range range = new Range(-5.0, 10.0); // Original lower: -5.0

        // Test with b0 less than mutated lower and b1 greater than mutated lower
        assertTrue("Ranges should not intersect", range.intersects(-10.0, 0.0));
        assertTrue("Ranges should intersect", range.intersects(-10.0, 20.0));
    }

    @Test
    public void testIntersects_NegatedDoubleLocalVariable1() {
        // Create a Range object with a known lower bound
        Range range = new Range(5.0, 10.0); // Original lower: 5.0
        double b0 = -5.0; // Mutated b0

        // Test with mutated b0 less than lower and b1 greater than lower
        assertFalse("Ranges should not intersect", range.intersects(b0, 0.0));
        assertTrue("Ranges should intersect", range.intersects(b0, 20.0));
    }

    @Test
    public void testIntersects_GreaterThanToGreaterOrEqual() {
        // Create a Range object with a known upper bound
        Range range = new Range(0.0, 10.0); // Original upper: 10.0

        // Test with b0 less than or equal to upper and b1 greater than upper
        assertFalse("Ranges should not intersect", range.intersects(-10.0, 0.0));
        assertTrue("Ranges should intersect", range.intersects(-10.0, 20.0));
    }

    @Test
    public void testExpand_MutationSubtractionWithAddition() {
        // Create a Range object with known bounds
        Range range = new Range(0.0, 10.0);

        // Test with lower and upper margins
        Range expandedRange = Range.expand(range, 0.1, 0.1);

        // The expanded range should have bounds greater than the original range
        assertFalse("Expanded range should have bounds greater than original",
                expandedRange.getLowerBound() > range.getLowerBound());
        assertTrue("Expanded range should have bounds greater than original",
                expandedRange.getUpperBound() > range.getUpperBound());
    }

    @Test
    public void testShift_NotAllowZeroCrossing_DeltaPositive() {
        Range base = new Range(0, 10);
        Range shiftedRange = Range.shift(base, 5, false);
        assertEquals("Lower bound should be shifted correctly",
                5.0, shiftedRange.getLowerBound(), 0.001);
        assertEquals("Upper bound should be shifted correctly",
                15.0, shiftedRange.getUpperBound(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShift_NullBase() {
        Range.shift(null, 5, true);
    }

    @Test
    public void testShift_NotAllowZeroCrossing_DeltaNegative() {
        Range base = new Range(0, 10);
        Range shiftedRange = Range.shift(base, -5, false);
        assertEquals("Lower bound should be shifted correctly",
                -5.0, shiftedRange.getLowerBound(), 0.001);
        assertEquals("Upper bound should be shifted correctly",
                5.0, shiftedRange.getUpperBound(), 0.001);
    }

    @Test
    public void testShift_AllowZeroCrossing_DeltaPositive() {
        Range base = new Range(-5, 5);
        Range shiftedRange = Range.shift(base, 10, true);
        assertEquals("Lower bound should be shifted correctly",
                5.0, shiftedRange.getLowerBound(), 0.001);
        assertEquals("Upper bound should be shifted correctly",
                15.0, shiftedRange.getUpperBound(), 0.001);
    }

    @Test
    public void testShift_AllowZeroCrossing_DeltaNegative() {
        Range base = new Range(-5, 5);
        Range shiftedRange = Range.shift(base, -10, true);
        assertEquals("Lower bound should be shifted correctly",
                -15.0, shiftedRange.getLowerBound(), 0.001);
        assertEquals("Upper bound should be shifted correctly",
                -5.0, shiftedRange.getUpperBound(), 0.001);
    }

    // Test cases for expandToInclude method
    @Test
    public void testExpandToInclude_WithNullRange_ShouldExpandRangeToSingleValue() {
        assertEquals(new Range(5, 5), Range.expandToInclude(null, 5));
    }

    @Test
    public void testExpandToInclude_WithValueWithinRange_ShouldExpandRangeToIncludeValue() {
        assertEquals(new Range(1, 5), Range.expandToInclude(new Range(1, 4), 5));
    }

    @Test
    public void testExpandToInclude_WithValueBelowRange_ShouldExpandRangeToLowerBound() {
        assertEquals(new Range(0, 4), Range.expandToInclude(new Range(1, 4), 0));
    }

    @Test
    public void testExpandToInclude_WithValueAboveRange_ShouldExpandRangeToUpperBound() {
        assertEquals(new Range(1, 6), Range.expandToInclude(new Range(1, 4), 6));
    }
    
    
    // add tests for getCentralValue

    @Test
    public void testGetCentralValueLargeValues() {
        // Test with large values
        Range range = new Range(Double.MAX_VALUE / 2, Double.MAX_VALUE);
        assertEquals("Central value of range with large bounds should be finite",
                Double.MAX_VALUE * 0.75, range.getCentralValue(), 0.0001);
    }

    @Test
    public void testGetCentralValueNegativeAndZeroValues() {
        // Test with negative and zero values
        Range range1 = new Range(-10, 10);
        assertEquals("Central value of range from -10 to 10 should be 0",
                0.0, range1.getCentralValue(), 0.0001);

        Range range2 = new Range(-5, 0);
        assertEquals("Central value of range from -5 to 0 should be -2.5",
                -2.5, range2.getCentralValue(), 0.0001);
    }
    

    @Test
    public void testGetCentralValueBoundaryCases() {

        Range range2 = new Range(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        assertTrue("Central value of range with infinite bounds should be NaN",
                Double.isNaN(range2.getCentralValue()));
    }

    @Test
    public void testIntersects_SpecialCase() {
        Range exampleRange = new Range(1, 10);
        // This should return true as it targets the specific branch: b0 < upper && b1
        // >= b0
        assertTrue("The range should intersect (targeted branch)", exampleRange.intersects(9, 9.5));
        // This ensures we hit the branch and validate the behavior accurately
        assertFalse("The range should not intersect as b1 is less than b0 (contrary to branch condition)",
                exampleRange.intersects(11, 9));
    }

    @Test
    public void testConstrain_ValueBelowLowerBound() {
        Range exampleRange = new Range(0, 10);
        // Targeting the specific mutation branch where value is less than lower
        assertEquals("Constraining a value below the lower bound should return the lower bound", 0,
                exampleRange.constrain(-1), 0.00001);
    }

    // add more tests for isNanRange
    @Test
    public void testIsNaNRange_OnlyLowerIsNaN() {
        Range range = new Range(Double.NaN, 1.0);
        assertFalse("Range should not be considered a NaN range if only lower is NaN", range.isNaNRange());
    }

    @Test
    public void testIsNaNRange_OnlyUpperIsNaN() {
        Range range = new Range(1.0, Double.NaN);
        assertFalse("Range should not be considered a NaN range if only upper is NaN", range.isNaNRange());
    }

    @Test
    public void testIsNaNRange_NeitherIsNaN() {
        Range range = new Range(0.0, 1.0);
        assertFalse("Range should not be considered a NaN range if neither bound is NaN", range.isNaNRange());
    }

    // add more tests for hashcode
    @Test
    public void testHashCodeWithDistinctRanges() {
        Range range1 = new Range(1, 2);
        Range range2 = new Range(3, 4);
        assertNotEquals("HashCodes should be different for distinct ranges", range1.hashCode(), range2.hashCode());
    }

    @Test
    public void testHashCodeWithSameRanges() {
        Range range1 = new Range(1, 2);
        Range range2 = new Range(1, 2);
        assertEquals("HashCodes should be equal for identical ranges", range1.hashCode(), range2.hashCode());
    }

    @Test
    public void testHashCodeWithDifferentLowerBounds() {
        Range range1 = new Range(1, 2);
        Range range2 = new Range(2, 2);
        assertNotEquals("HashCodes should be different when only the lower bounds differ", range1.hashCode(),
                range2.hashCode());
    }

    @Test
    public void testHashCodeWithDifferentUpperBounds() {
        Range range1 = new Range(1, 2);
        Range range2 = new Range(1, 3);
        assertNotEquals("HashCodes should be different when only the upper bounds differ", range1.hashCode(),
                range2.hashCode());
    }

    @Test
    public void testHashCodeWithPositiveInfinity() {
        Range range = new Range(1, Double.POSITIVE_INFINITY);
        assertNotNull("HashCode should be generated for range with positive infinity", range.hashCode());
    }

    @Test
    public void testHashCodeWithNegativeInfinity() {
        Range range = new Range(Double.NEGATIVE_INFINITY, 1);
        assertNotNull("HashCode should be generated for range with negative infinity", range.hashCode());
    }

    @Test
    public void testHashCodeWithNaN() {
        Range range = new Range(Double.NaN, Double.NaN);
        assertNotNull("HashCode should be generated for range with NaN bounds", range.hashCode());
    }

    @Test
    public void testExpand_NegativeLowerMargin() {
        Range originalRange = new Range(10, 20);
        double lowerMargin = -0.1; 
        double upperMargin = 0.1; 
        Range expandedRange = Range.expand(originalRange, lowerMargin, upperMargin);

        assertTrue("Lower bound should increase when a negative margin is applied",
                expandedRange.getLowerBound() > originalRange.getLowerBound());
    }

    @Test
    public void testExpand_LargeLowerMargin() {
        Range originalRange = new Range(10, 20);
        double lowerMargin = 5.0; 
        double upperMargin = 0.1; 
        Range expandedRange = Range.expand(originalRange, lowerMargin, upperMargin);

        assertTrue("Lower bound should decrease significantly for a large positive margin",
                expandedRange.getLowerBound() < originalRange.getLowerBound());
    }

    @Test
    public void testExpand_UpperLessThanLowerAfterExpansion() {
        Range originalRange = new Range(10, 20);
        double lowerMargin = 1.0; 
        double upperMargin = -1.0; 

        Range expandedRange = Range.expand(originalRange, lowerMargin, upperMargin);
        assertTrue("After expansion, upper bound should still be greater or equal to the lower bound",
                expandedRange.getUpperBound() >= expandedRange.getLowerBound());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExpand_NullRange() {
        Range.expand(null, 0.1, 0.1); 
    }

    @Test
    public void testExpand_ZeroMargin() {
        Range originalRange = new Range(10, 20);
        Range expandedRange = Range.expand(originalRange, 0, 0);
        assertEquals("Range should remain unchanged with zero margins", originalRange, expandedRange);
    }

    @Test
    public void testExpand_InvertedBounds() {
        Range originalRange = new Range(10, 20);
        double lowerMargin = 1.1; 
        double upperMargin = -0.1;
        Range expandedRange = Range.expand(originalRange, lowerMargin, upperMargin);
        assertFalse("Lower bound should not be greater than upper bound after expansion",
                expandedRange.getLowerBound() > expandedRange.getUpperBound());
    }

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}

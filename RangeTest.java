package org.jfree.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RangeTest  {
	
    private Range exampleRange;
    //private Range exampleRange1;
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { exampleRange = new Range(-1, 1);
    }
    
    //@Before 
    //public void setUp() throws Exception { exampleRange1 = new Range(1, 2);
    //}


    @Test
    public void centralValueShouldBeZero() {
        assertEquals("The central value of -1 and 1 should be 0",
        0, exampleRange.getCentralValue(), .000000001d);
    }
    
    @Test
    public void centralValueShouldBeZer() {
    	Range obj1 = new Range(1, 5);
        assertEquals("The central value of -1 and 1 should be 0",
        3, obj1.getCentralValue(), .000000001d);
    }
    
    @Test
    public void centralValueOfPositiveRange() {
    	Range positiveRange = new Range(2, 4);
        assertEquals("The central value of -1 and 1 should be 0",
        3, positiveRange.getCentralValue(), .000000001d);
    }
    
    
    @Test
    public void centralValueOfNegativeRange() {
        Range negativeRange = new Range(-5, -1);
        assertEquals("The central value of a negative range from -5 to -1 should be -3",
                -3, negativeRange.getCentralValue(), .000000001d);
    }
    
    @Test
    public void centralValueOfSymmetricRangeShouldBeMidpoint() {
        Range symmetricRange = new Range(-3, 3);
        assertEquals("The central value of a symmetric range from -3 to 3 should be 0",
                0, symmetricRange.getCentralValue(), .000000001d);
    }

    @Test
    public void centralValueOfAsymmetricRangeShouldBeMidpoint() {
        Range symmetricRange = new Range(-2, 4);
        assertEquals("The central value of a symmetric range from -2 to 4 should be 1",
                1, symmetricRange.getCentralValue(), .000000001d);
    }
    
    @Test
    public void centralValueOfOddLenghtRange() {
        Range oddLenghtRange = new Range(1, 6);
        assertEquals("The central value of a symmetric range from -2 to 4 should be 1",
                3.5, oddLenghtRange.getCentralValue(), .000000001d);
    }
    
    @Test
    public void centralValueOfEvenLenghtRange() {
        Range oddLenghtRange = new Range(2, 6);
        assertEquals("The central value of a symmetric range from 2 to 6 should be 4",
                4, oddLenghtRange.getCentralValue(), .000000001d);
    }
    @Test
    public void lengthOfRangeShouldBeTwo() {
        assertEquals("The length of the range from -1 to 1 should be 2",
                2.0, exampleRange.getLength(), .000000001d);
    }
    
    @Test
    public void centralValueOfSinglePointRangeShouldBeThatValue() {
        Range singlePointRange = new Range(5, 5);
        assertEquals("The central value of a single-point range with value 5 should be 5",
                5, singlePointRange.getCentralValue(), .000000001d);
    }

    
    @Test
    public void shouldContainZero() {
    	Range obj1 = new Range(-1, 1);
    	assertTrue("The Range from -1 to 1 should contain", obj1.contains(0));
    }
    
    @Test
    public void shouldContainCentralValue() {
    	Range obj1 = new Range(-1, 1);
    	double centralValue = obj1.getCentralValue();
    	assertTrue("The Range from -1 to 1 should contain", obj1.contains(centralValue));
    }
    
    @Test
    public void shouldNotContainValueOutside() {
    	Range obj1 = new Range(-1, 1);
    	assertFalse("The Range from -1 to 1 should not contain", obj1.contains(-2.0));
    }
    
    @Test
    public void shouldContainLowerBound() {
    	Range obj1 = new Range(0, 2);
    	double lowerBound = obj1.getLowerBound();
    	assertTrue("The Range from -1 to 1 should not contain", obj1.contains(lowerBound));
    }
    
    @Test
    public void lowerBoundOfNaNRange() {
        Range nanRange = new Range(Double.NaN, Double.NaN);
        assertTrue("The lower bound of a NaN range should be NaN",
                Double.isNaN(nanRange.getLowerBound()));
    }

    @Test
    public void lowerBoundOfInfiniteRange() {
        Range infiniteRange = new Range(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        assertEquals("The lower bound of an infinite range should be negative infinity",
                Double.NEGATIVE_INFINITY, infiniteRange.getLowerBound(), .000000001d);
    }
    
    @Test
    public void lowerBoundOfZeroToZeroRange() {
        Range zeroRange = new Range(0, 0);
        assertEquals("The lower bound of a range from 0 to 0 should be 0",
                0, zeroRange.getLowerBound(), .000000001d);
    }

    @Test
    public void lowerBoundOfNegativeToZeroRange() {
        Range negativeToZeroRange = new Range(-2, 0);
        assertEquals("The lower bound of a range from -2 to 0 should be -2",
                -2, negativeToZeroRange.getLowerBound(), .000000001d);
    }

    @Test
    public void lowerBoundWithNegativeValue() {
        Range negativeRange = new Range(-5, 2);
        assertEquals("The lower bound of a range from -5 to 2 should be -5",
                -5, negativeRange.getLowerBound(), .000000001d);
    }
   

   

    

    
    @Test
    public void shouldContainUpperBound() {
    	Range obj1 = new Range(4, 5);
    	double upperBound = obj1.getUpperBound();
    	assertTrue("The Range from -1 to 1 should not contain", obj1.contains(upperBound));
    }
    
    @Test
    public void shouldNotContainValueJustBelowLowerBound() {
    	Range obj1 = new Range(2, 4);
        assertFalse("The Range from 2 to 4 should not contain a value just below its lower bound",
        		obj1.contains(obj1.getLowerBound() - 0.000001d));
    }

    @Test
    public void shouldNotContainValueJustAboveUpperBound() {
    	Range obj1 = new Range(2, 4);
        assertFalse("The Range from 2 to 4 should not contain a value just above its upper bound",
        		obj1.contains(obj1.getUpperBound() + 0.000001d));
    }


    
    /*@Test
    public void shouldNotContainInEmptyRange() {
        Range emptyRange = new Range(2, 1);
        assertFalse("An empty range should not contain any value",
                emptyRange.contains(1.5));
    }*/

    @Test
    public void lowerBoundShouldBeCorrect() {
        assertEquals("The lower bound of the range should be -1",
                -1.0, exampleRange.getLowerBound(), .000000001d);
    }
    
    
    
    @Test
    public void upperBoundShouldBeCorrect() {
        assertEquals("The upper bound of the range should be 1",
                1.0, exampleRange.getUpperBound(), .000000001d);
    }
    
    
    
//    @Test
//    public void toStringShouldReturnCorrectRepresentation() {
//        String expectedRepresentation = "Range[-1.0, 1.0]";
//        assertEquals("The toString() method should return the correct representation",
//                expectedRepresentation, exampleRange.toString());
//    }
//    
    
    @Test
    public void lowerBoundOfPositiveRange() {
        Range positiveRange = new Range(2, 5);
        assertEquals("The lower bound of a positive range from 2 to 5 should be 2",
                2, positiveRange.getLowerBound(), .000000001d);
    }

    @Test
    public void lowerBoundOfNegativeRange() {
        Range negativeRange = new Range(-5, -2);
        assertEquals("The lower bound of a negative range from -5 to -2 should be -5",
                -5, negativeRange.getLowerBound(), .000000001d);
    }

    @Test
    public void lowerBoundOfSinglePointRange() {
        Range singlePointRange = new Range(7, 7);
        assertEquals("The lower bound of a single-point range with value 7 should be 7",
                7, singlePointRange.getLowerBound(), .000000001d);
    }

  
    @Test
    public void upperBoundOfLargePositiveRange() {
        Range largePositiveRange = new Range(1e12, 2e12);
        assertEquals("The lower bound of a large positive range should be the start value",
                1e12, largePositiveRange.getLowerBound(), .000000001d);
    }

    


    @Test
    public void lowerBoundWithNonIntegerValue() {
        Range nonIntegerRange = new Range(1.5, 4.5);
        assertEquals("The lower bound of a range with non-integer values should be the start value",
                1.5, nonIntegerRange.getLowerBound(), .000000001d);
    }
    
    @Test
    public void lowerBoundWithNonIntegerStartValue() {
        Range nonIntegerStartRange = new Range(1.5, 4.5);
        assertEquals("The lower bound of a range with non-integer start value should be the start value",
                1.5, nonIntegerStartRange.getLowerBound(), .000000001d);
    }

  
    @Test
    public void lowerBoundOfLargePositiveRange() {
        Range largePositiveRange = new Range(1e12, 2e12);
        assertEquals("The lower bound of a large positive range should be the start value",
                1e12, largePositiveRange.getLowerBound(), .000000001d);
    }

    @Test
    public void lowerBoundOfLargeNegativeRange() {
        Range largeNegativeRange = new Range(-2e12, -1e12);
        assertEquals("The lower bound of a large negative range should be the start value",
                -2e12, largeNegativeRange.getLowerBound(), .000000001d);
    }

    @Test
    public void lengthOfPositiveRangeShouldBeCorrect() {
        Range positiveRange = new Range(2, 5);
        assertEquals("The length of a positive range from 2 to 5 should be 3",
                3, positiveRange.getLength(), .000000001d);
    }

    @Test
    public void lengthOfNegativeRangeShouldBeCorrect() {
        Range negativeRange = new Range(-5, -2);
        assertEquals("The length of a negative range from -5 to -2 should be 3",
                3, negativeRange.getLength(), .000000001d);
    }

    @Test
    public void lengthOfSinglePointRangeShouldBeZero() {
        Range singlePointRange = new Range(7, 7);
        assertEquals("The length of a single-point range with value 7 should be 0",
                0, singlePointRange.getLength(), .000000001d);
    }

    @Test
    public void lengthOfLargePositiveRangeShouldBeCorrect() {
        Range largePositiveRange = new Range(1e12, 2e12);
        assertEquals("The length of a large positive range should be the difference between start and end values",
                1e12, largePositiveRange.getLength(), .000000001d);
    }

    @Test
    public void lengthOfLargeNegativeRangeShouldBeCorrect() {
        Range largeNegativeRange = new Range(-2e12, -1e12);
        assertEquals("The length of a large negative range should be the difference between start and end values",
                1e12, largeNegativeRange.getLength(), .000000001d);
    }
    
 // Existing tests...

    @Test
    public void lowerBoundShouldBeCorrectForNegativeRange() {
        Range negativeRange = new Range(-3, -1);
        assertEquals("The lower bound of the range from -3 to -1 should be -3",
                -3.0, negativeRange.getLowerBound(), .000000001d);
    }

    @Test
    public void upperBoundShouldBeCorrectForPositiveRange() {
        Range positiveRange = new Range(2, 5);
        assertEquals("The upper bound of a positive range from 2 to 5 should be 5",
                5.0, positiveRange.getUpperBound(), .000000001d);
    }

    @Test
    public void getLengthShouldBeZeroForSinglePointRange() {
        Range singlePointRange = new Range(7, 7);
        assertEquals("The length of a single-point range with value 7 should be 0",
                0.0, singlePointRange.getLength(), .000000001d);
    }

    @Test
    public void getLengthShouldBeCorrectForLargePositiveRange() {
        Range largePositiveRange = new Range(1e12, 2e12);
        assertEquals("The length of a large positive range should be the difference between start and end values",
                1e12, largePositiveRange.getLength(), .000000001d);
    }

    @Test
    public void testEqualsSameObject() {
        assertTrue("The Range should be equal to itself", exampleRange.equals(exampleRange));
    }

    @Test
    public void testEqualsEqualRanges() {
        Range equalRange = new Range(-1, 1);
        assertTrue("Equal ranges should be considered equal", exampleRange.equals(equalRange));
    }

    @Test
    public void testEqualsDifferentRanges() {
        Range differentRange = new Range(0, 2);
        assertFalse("Different ranges should not be considered equal", exampleRange.equals(differentRange));
    }

    @Test
    public void testEqualsDifferentObject() {
        assertFalse("A Range should not be equal to a different type of object", exampleRange.equals("Not a Range"));
    }

    @Test
    public void testEqualsNullObject() {
        assertFalse("A Range should not be equal to null", exampleRange.equals(null));
    }

    @Test
    public void testEqualsSymmetricProperty() {
        Range range1 = new Range(1, 3);
        Range range2 = new Range(1, 3);

        assertTrue("The equals method should have symmetric property", range1.equals(range2) && range2.equals(range1));
    }

    @Test
    public void testEqualsTransitiveProperty() {
        Range range1 = new Range(1, 3);
        Range range2 = new Range(1, 3);
        Range range3 = new Range(1, 3);

        assertTrue("The equals method should have transitive property",
                range1.equals(range2) && range2.equals(range3) && range1.equals(range3));
    }
   

    @Test
    public void testEqualsDifferentUpperBound() {
        Range equalLowerBoundRange = new Range(-1, 2);
        assertFalse("Ranges with different upper bounds should not be considered equal", exampleRange.equals(equalLowerBoundRange));
    }

    
    
    @Test
    public void testCombine() {
        Range range1 = new Range(1, 3);
        Range range2 = new Range(2, 4);

        Range combinedRange = Range.combine(range1, range2);

        assertEquals("Combining ranges [1,3] and [2,4] should result in [1,4]",
                new Range(1, 4), combinedRange);
    }
    
    @Test
    public void testCombineWithNullRange1() {
        Range range2 = new Range(2, 4);
        Range combinedRange = Range.combine(null, range2);
        assertEquals("Combining null range and [2,4] should result in [2,4]", range2, combinedRange);
    }

    @Test
    public void testCombineWithNullRange2() {
        Range range1 = new Range(1, 3);
        Range combinedRange = Range.combine(range1, null);
        assertEquals("Combining [1,3] and null range should result in [1,3]", range1, combinedRange);
    }

   
    
    @Test
    public void testGetLengthInvalidRange() {
        try {
            Range invalidRange = new Range(3, 1);
            invalidRange.getLength();
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            String expectedMessage = "Range(double, double): require lower (3.0) <= upper (1.0).";
            assertEquals(expectedMessage, e.getMessage());
        }
    }


    

    // Test for the branch where lower <= upper
    @Test
    public void testGetLengthValidRange() {
        Range validRange = new Range(1, 3);

        // This should not throw an exception
        double length = validRange.getLength();

        // You can add assertions to check the calculated length if needed
        assertEquals(2.0, length, .000000001d);
    }
    
    
    
    @Test
    public void testGetLowerBoundInvalidRange() {
        try {
            Range invalidRange = new Range(3, 1);
            invalidRange.getLowerBound();

            // Fail the test if no exception is thrown
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Check if the exception message is as expected
            String expectedMessage = "Range(double, double): require lower (3.0) <= upper (1.0).";
            assertEquals(expectedMessage, e.getMessage());
        }
    }
    
    
    
    @Test
    public void testIntersectsDouble() {
        Range range = new Range(0, 10);
        
        assertTrue(range.intersects(-5, 5));  // Test with intersecting range
        assertTrue(range.intersects(5, 15));  // Test with intersecting range
        assertFalse(range.intersects(-5, -1)); // Test with non-intersecting range
        assertFalse(range.intersects(15, 20)); // Test with non-intersecting range
    }

    @Test
    public void testIntersectsRange() {
        Range range1 = new Range(0, 10);
        Range range2 = new Range(5, 15);
        Range range3 = new Range(-5, -1);
        
        assertTrue(range1.intersects(range2)); // Test with intersecting ranges
        assertFalse(range1.intersects(range3)); // Test with non-intersecting ranges
    }

    @Test
    public void testConstrain() {
        Range range = new Range(0, 10);
        
        assertEquals(5, range.constrain(5), 0.001);  // Test with value within range
        assertEquals(0, range.constrain(-5), 0.001); // Test with value below range
        assertEquals(10, range.constrain(15), 0.001); // Test with value above range
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

    // Test cases for expand method
    @Test
    public void testExpand_WithNullRange_ShouldThrowIllegalArgumentException() {
        try {
            Range.expand(null, 0.1, 0.1);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Success
        }
    }
    
//    @Test
//    public void testExpand_WithPositiveMargins_ShouldExpandRangeByMargins() {
//        assertEquals(new Range(0, 11), Range.expand(new Range(1, 10), 0.1, 0.1));
//    }
    
//    @Test
//    public void testExpand_WithNegativeMargins_ShouldReduceRangeByMargins() {
//        assertEquals(new Range(2, 9), Range.expand(new Range(1, 10), -0.1, -0.1));
//    }

    // Test cases for shift method
    @Test
    public void testShift_WithNullRange_ShouldThrowIllegalArgumentException() {
        try {
            Range.shift(null, 0.1);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Success
        }
    }
    
    @Test
    public void testShift_WithoutZeroCrossing_ShouldShiftRangeByDelta() {
        assertEquals(new Range(2, 11), Range.shift(new Range(1, 10), 1));
    }
    
    @Test
    public void testShift_WithZeroCrossingAllowed_ShouldShiftRangeAndAllowZeroCrossing() {
        assertEquals(new Range(0, 9), Range.shift(new Range(1, 10), -1, true));
    }

    // Test cases for scale method
    @Test
    public void testScale_WithNullRange_ShouldThrowIllegalArgumentException() {
        try {
            Range.scale(null, 0.1);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Success
        }
    }
    
    @Test
    public void testScale_WithNegativeFactor_ShouldThrowIllegalArgumentException() {
        try {
            Range.scale(new Range(1, 10), -0.1);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Success
        }
    }
    
    @Test
    public void testScale_WithPositiveFactor_ShouldScaleRangeByFactor() {
        assertEquals(new Range(2, 20), Range.scale(new Range(1, 10), 2));
    }
    
    
    @Test
    public void testHashCode_WithEqualRanges_ShouldReturnSameHashCode() {
        Range range1 = new Range(0, 10);
        Range range2 = new Range(0, 10);
        
        assertEquals(range1.hashCode(), range2.hashCode());
    }
    
    @Test
    public void testHashCode_WithDifferentLowerBounds_ShouldReturnDifferentHashCode() {
        Range range1 = new Range(0, 10);
        Range range2 = new Range(1, 10);
        
        assertNotEquals(range1.hashCode(), range2.hashCode());
    }
    
    @Test
    public void testHashCode_WithDifferentUpperBounds_ShouldReturnDifferentHashCode() {
        Range range1 = new Range(0, 10);
        Range range2 = new Range(0, 11);
        
        assertNotEquals(range1.hashCode(), range2.hashCode());
    }
    
    @Test
    public void testHashCode_WithNegativeRanges_ShouldReturnSameHashCode() {
        Range range1 = new Range(-10, -5);
        Range range2 = new Range(-10, -5);
        
        assertEquals(range1.hashCode(), range2.hashCode());
    }
    
    @Test
    public void testHashCode_WithNanRanges_ShouldReturnSameHashCode() {
        Range range1 = new Range(Double.NaN, Double.NaN);
        Range range2 = new Range(Double.NaN, Double.NaN);
        
        assertEquals(range1.hashCode(), range2.hashCode());
    }

//    
//    @Test
//    public void testCombineIgnoringNaN_WithOneRangeNaN_ShouldReturnOtherRange() {
//        assertEquals(new Range(2, 3), Range.combineIgnoringNaN(new Range(1, 2), new Range(Double.NaN, Double.NaN)));
//        assertEquals(new Range(1, 2), Range.combineIgnoringNaN(new Range(Double.NaN, Double.NaN), new Range(1, 2)));
//    }
//    
    
//    @Test
//    public void testCombineIgnoringNaN_WithOneRangeNegative_ShouldReturnOtherRange() {
//        assertEquals(new Range(-2, -1), Range.combineIgnoringNaN(new Range(-2, -1), new Range(1, 2)));
//        assertEquals(new Range(1, 2), Range.combineIgnoringNaN(new Range(1, 2), new Range(-2, -1)));
//    }
    
    @Test
    public void testCombineIgnoringNaN_ValidRanges() {
        Range range1 = new Range(0, 10);
        Range range2 = new Range(5, 15);
        Range combinedRange = Range.combineIgnoringNaN(range1, range2);
        assertEquals(new Range(0, 15), combinedRange);
    }

    @Test
    public void testCombineIgnoringNaN_OneRangeNull() {
        Range range1 = new Range(0, 10);
        Range combinedRange = Range.combineIgnoringNaN(range1, null);
        assertEquals(range1, combinedRange);

        Range range2 = new Range(5, 15);
        combinedRange = Range.combineIgnoringNaN(null, range2);
        assertEquals(range2, combinedRange);
    }

    @Test
    public void testCombineIgnoringNaN_BothRangesNull() {
        Range combinedRange = Range.combineIgnoringNaN(null, null);
        assertNull(combinedRange);
    }

//    @Test
//    public void testCombineIgnoringNaN_OneRangeNaN() {
//        Range range1 = new Range(Double.NaN, 10);
//        Range range2 = new Range(5, 15);
//        Range combinedRange = Range.combineIgnoringNaN(range1, range2);
//        assertEquals(range2, combinedRange);
//
//        range1 = new Range(0, 10);
//        range2 = new Range(Double.NaN, 15);
//        combinedRange = Range.combineIgnoringNaN(range1, range2);
//        assertEquals(range1, combinedRange);
//    }

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
    
//    @Test
//    public void testExpand_IfBlockConditionTrue() {
//        // Test case where lower bound > upper bound after expansion
//        Range range = new Range(5, 10);
//        Range expandedRange = Range.expand(range, 0.6, 0.6);
//        assertTrue(expandedRange.getLowerBound() > expandedRange.getUpperBound());
//    }

    @Test
    public void testExpand_IfBlockConditionFalse() {
        // Test case where lower bound <= upper bound after expansion
        Range range = new Range(5, 10);
        Range expandedRange = Range.expand(range, 0.1, 0.1);
        assertTrue(expandedRange.getLowerBound() <= expandedRange.getUpperBound());
    }
    
    @Test
    public void testConstrain_ValueGreaterThanUpperBound() {
        // Test case where value > upper bound
        Range range = new Range(0, 10);
        double constrainedValue = range.constrain(15);
        assertEquals(10, constrainedValue, 0);
    }

    @Test
    public void testConstrain_ValueLessThanLowerBound() {
        // Test case where value < lower bound
        Range range = new Range(0, 10);
        double constrainedValue = range.constrain(-5);
        assertEquals(0, constrainedValue, 0);
    }

    @Test
    public void testConstrain_ValueWithinRange() {
        // Test case where value within the range
        Range range = new Range(0, 10);
        double constrainedValue = range.constrain(5);
        assertEquals(5, constrainedValue, 0);
    }
    
    @Test
    public void testShift_ValueGreaterThanZero_PositiveDelta() {
        Range base = new Range(3.0, 6.0);
        Range shifted = Range.shift(base, 2.0);
        assertEquals(new Range(5.0, 8.0), shifted);
    }

    @Test
    public void testShift_ValueGreaterThanZero_NegativeDelta() {
        Range base = new Range(3.0, 6.0);
        Range shifted = Range.shift(base, -2.0);
        assertEquals(new Range(1.0, 4.0), shifted);
    }

    @Test
    public void testShift_ValueLessThanZero_PositiveDelta() {
        Range base = new Range(-6.0, -3.0);
        Range shifted = Range.shift(base, 2.0);
        assertEquals(new Range(-4.0, -1.0), shifted);
    }

    @Test
    public void testShift_ValueLessThanZero_NegativeDelta() {
        Range base = new Range(-6.0, -3.0);
        Range shifted = Range.shift(base, -2.0);
        assertEquals(new Range(-8.0, -5.0), shifted);
    }

    @Test
    public void testShift_ValueEqualToZero_PositiveDelta() {
        Range base = new Range(0.0, 0.0);
        Range shifted = Range.shift(base, 2.0);
        assertEquals(new Range(2.0, 2.0), shifted);
    }

    @Test
    public void testShift_ValueEqualToZero_NegativeDelta() {
        Range base = new Range(0.0, 0.0);
        Range shifted = Range.shift(base, -2.0);
        assertEquals(new Range(-2.0, -2.0), shifted);
    }

    @Test
    public void testShift_ValueEqualToZero_ZeroDelta() {
        Range base = new Range(0.0, 0.0);
        Range shifted = Range.shift(base, 0.0);
        assertEquals(new Range(0.0, 0.0), shifted);
    }
    
    @Test
    public void testExpandToInclude_ValueWithinRange() {
        Range range = new Range(3.0, 6.0);
        Range expanded = Range.expandToInclude(range, 4.0); // Value within range
        assertEquals(range, expanded); // Expect the same range to be returned
    }
    
    @Test
    public void testContains_ReturnsTrue() {
        Range range = new Range(3.0, 6.0);
        assertTrue(range.contains(4.0)); // Value within range
    }
    

    // ADDITIONAL TEST CASES TO INCREASE MUTATION COVERAGE 

    @Test
    public void testIntersects_LowerBoundNotRetreieved() {
        // Create two ranges with different lower bounds
        Range range1 = new Range(0, 10);  // Lower bound: 0
        Range range2 = new Range(5, 15);  // Lower bound: 5
        
        // Check for intersection
        boolean result = range1.intersects(range2);
        
        // Assert that the result is true since the ranges intersect
        assertTrue("Ranges should intersect", result);
        
        // Modify range2 to ensure it does not intersect with range1
        range2 = new Range(-5, 0);  // Lower bound: -5
        
        // Check for intersection
        result = range1.intersects(range2);
        
        // Assert that the result is false since the ranges do not intersect
        assertFalse("Ranges should not intersect", result);
    }

    @Test
    public void testIntersects_ChangedConditionalBoundary() {
        // Create a Range object with a known lower bound
        Range range = new Range(5.0, 10.0);  // Original lower: 5.0
        
        // Test with b0 less than mutated lower and b1 greater than mutated lower
        assertFalse("Ranges should not intersect", range.intersects(-10.0, 0.0));
        assertTrue("Ranges should intersect", range.intersects(-10.0, 20.0));
    }
    
    @Test
    public void testIntersects_NegatedDoubleFieldLower() {
        // Create a Range object with a known lower bound
        Range range = new Range(-5.0, 10.0);  // Original lower: -5.0
        
        // Test with b0 less than mutated lower and b1 greater than mutated lower
        assertTrue("Ranges should not intersect", range.intersects(-10.0, 0.0));
        assertTrue("Ranges should intersect", range.intersects(-10.0, 20.0));
    }
    
    @Test
    public void testIntersects_NegatedDoubleLocalVariable1() {
        // Create a Range object with a known lower bound
        Range range = new Range(5.0, 10.0);  // Original lower: 5.0
        double b0 = -5.0;  // Mutated b0
        
        // Test with mutated b0 less than lower and b1 greater than lower
        assertFalse("Ranges should not intersect", range.intersects(b0, 0.0));
        assertTrue("Ranges should intersect", range.intersects(b0, 20.0));
    }
    
    @Test
    public void testIntersects_GreaterThanToGreaterOrEqual() {
        // Create a Range object with a known upper bound
        Range range = new Range(0.0, 10.0);  // Original upper: 10.0
        
        // Test with b0 less than or equal to upper and b1 greater than upper
        assertFalse("Ranges should not intersect", range.intersects(-10.0, 0.0));
        assertTrue("Ranges should intersect", range.intersects(-10.0, 20.0));
    }
    
    public void testExpand_MutationMultiplicationWithDivision() {
        // Create a Range object with known bounds
        Range range = new Range(0.0, 10.0);
        
        // Test with lower and upper margins
        Range expandedRange = Range.expand(range, 0.5, 0.5);
        
        // The expanded range should be wider than the original range
        assertTrue("Expanded range should have wider bounds", expandedRange.getLength() > range.getLength());
    }
    
    @Test
    public void testExpand_MutationSubtractionWithAddition() {
        // Create a Range object with known bounds
        Range range = new Range(0.0, 10.0);
        
        // Test with lower and upper margins
        Range expandedRange = Range.expand(range, 0.1, 0.1);
        
        // The expanded range should have bounds greater than the original range
        assertFalse("Expanded range should have bounds greater than original", expandedRange.getLowerBound() > range.getLowerBound());
        assertTrue("Expanded range should have bounds greater than original", expandedRange.getUpperBound() > range.getUpperBound());
    }
    
    @Test
    public void testExpand_MutationRemovedGetLowerBound() {
        // Create a Range object with known bounds
        Range range = new Range(0.0, 10.0);
        
        // Test with lower and upper margins
        Range expandedRange = Range.expand(range, 0.1, 0.1);
        
        // The expanded range should have bounds greater than the original range
        assertFalse("Expanded range should have bounds greater than original", expandedRange.getLowerBound() > range.getLowerBound());
        assertTrue("Expanded range should have bounds greater than original", expandedRange.getUpperBound() > range.getUpperBound());
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
    

   
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
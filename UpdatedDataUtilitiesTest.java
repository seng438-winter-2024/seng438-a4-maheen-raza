/*
 * Author: Maheen Raza
 * Group: 9
 */

 package org.jfree.data.test;

 import static org.junit.Assert.*;
 
 import java.security.*;
 
 import org.jfree.data.DataUtilities;
 import org.jfree.data.KeyedValues;
 import org.jfree.data.Values2D;
 import org.jmock.Expectations;
 import org.jmock.Mockery;
 import org.junit.Test;
 
 public class DataUtilitiesTest extends DataUtilities {
 
     
     /*
      * The following test cases are for the method calculateColumnTotal
      * 
      */
     
     /*
      * Test for: Value-type
      * Tests when a Values2D data object that has only two columns is passed in
      * Should return: 10.0
      */
      @Test
      public void calculateColumnTotalForOnlyTwoValidValues() {
          Mockery mockingContext = new Mockery();
          final Values2D values = mockingContext.mock(Values2D.class);
          mockingContext.checking(new Expectations() {
              {
                  one(values).getRowCount();
                  will(returnValue(2));
                  one(values).getValue(0, 0);
                  will(returnValue(7.5));
                  one(values).getValue(1, 0);
                  will(returnValue(2.5));
              }
          });
          double result = DataUtilities.calculateColumnTotal(values, 0);
          assertEquals(result, 10.0, .000000001d);
      }
      
      
      /*
       * Test for: Value-type
       * Tests when a Values2D data object that has more than two columns is passed in
       * Should return: 11.5
       */
      @Test
      public void calculateColumnTotalForMoreThanTwoValidValues() {
          Mockery mockingContext = new Mockery();
          final Values2D values = mockingContext.mock(Values2D.class);
          mockingContext.checking(new Expectations() {
              {
                  one(values).getRowCount();
                  will(returnValue(3));
                  one(values).getValue(0, 0);
                  will(returnValue(7.5));
                  one(values).getValue(1, 0);
                  will(returnValue(2.5));
                  one(values).getValue(2, 0);
                  will(returnValue(1.5));
              }
          });
          double result = DataUtilities.calculateColumnTotal(values, 0);
          assertEquals(result, 11.5, .000000001d);
      }
      
      /*
       * Test for: Value-type
       * Tests when a Values2D object that only has positive values is passed in
       * Should return: 30.0
       */
      @Test
      public void calculateColumnTotalForPositiveValuesOnly() {
          Mockery mockingContext = new Mockery();
          final Values2D values = mockingContext.mock(Values2D.class);
          mockingContext.checking(new Expectations() {
              {
                  one(values).getRowCount();
                  will(returnValue(2));
                  one(values).getValue(0, 0);
                  will(returnValue(10.0));
                  one(values).getValue(1, 0);
                  will(returnValue(20.0));
              }
          });
          double result = DataUtilities.calculateColumnTotal(values, 0);
          assertEquals(result, 30.0, .000000001d);
      }
      
      /*
       * Test for: Value-type
       * Tests when a Values2D object that only has negative values is passed in
       * Should return: -31.0
       */
      @Test
      public void calculateColumnTotalForNegativeValuesOnly() {
          Mockery mockingContext = new Mockery();
          final Values2D values = mockingContext.mock(Values2D.class);
          mockingContext.checking(new Expectations() {
              {
                  one(values).getRowCount();
                  will(returnValue(2));
                  one(values).getValue(0, 0);
                  will(returnValue(-15.0));
                  one(values).getValue(1, 0);
                  will(returnValue(-16.0));
              }
          });
          double result = DataUtilities.calculateColumnTotal(values, 0);
          assertEquals(result, -31.0, .000000001d);
      }
      
      /*
       * Test for: Value-type
       * Tests when a Values2D object that has mixed values is passed in
       * Should return: -11.0
       */
      @Test
      public void calculateColumnTotalWithPositiveAndNegativeValues() {
          Mockery mockingContext = new Mockery();
          final Values2D values = mockingContext.mock(Values2D.class);
          mockingContext.checking(new Expectations() {
              {
                  one(values).getRowCount();
                  will(returnValue(2));
                  one(values).getValue(0, 0);
                  will(returnValue(-15.0));
                  one(values).getValue(1, 0);
                  will(returnValue(4.0));
              }
          });
          double result = DataUtilities.calculateColumnTotal(values, 0);
          assertEquals(result, -11.0, .000000001d);
      }
      
      /*
       * Test for: Value-type
       * Tests when a user passes in Values2D object that has values that sum to zero
       * Should return: 0.0
       */
      @Test
      public void calculateColumnTotalForSumOfZero() {
          Mockery mockingContext = new Mockery();
          final Values2D values = mockingContext.mock(Values2D.class);
          mockingContext.checking(new Expectations() {
              {
                  one(values).getRowCount();
                  will(returnValue(2));
                  one(values).getValue(0, 0);
                  will(returnValue(-4.0));
                  one(values).getValue(1, 0);
                  will(returnValue(4.0));
              }
          });
          double result = DataUtilities.calculateColumnTotal(values, 0);
          assertEquals(result, 0.0, .000000001d);
      }
      
     /*
      * Test for: Value-type
      * Tests when a user passes in a Values2D object that is empty
      * Should return: 0.0
      */
     @Test
     public void calculateColumnTotalForEmptyDataTable() {
         Mockery mockingContext = new Mockery();
         final Values2D values = mockingContext.mock(Values2D.class);
         mockingContext.checking(new Expectations() {
             {
                 one(values).getRowCount();
                 will(returnValue(0));
             }
         });
 
         double result = DataUtilities.calculateColumnTotal(values, 0);
         assertEquals(0.0, result, .000000001d);
     } 
     
     /*
      * Test for: Exception Handling
      * Tests when the Values2D object has null values
      * Should return: InvalidParameterException
      */
     @Test
     public void calculateColumnTotalWithNullData() {
         Mockery mockingContext = new Mockery();
         final Values2D values = mockingContext.mock(Values2D.class);
         mockingContext.checking(new Expectations() {
             {
                 one(values).getRowCount();
                 will(returnValue(2));
                 one(values).getValue(0, 0);
                 will(returnValue(null));
                 one(values).getValue(1, 0);
                 will(returnValue(null));
             }
         });
         
         DataUtilities.calculateColumnTotal(values, 0);
     }
 
     /*
      * Test for: Exception handling
      * Tests when a Values2D object has invalid data, such as NaN, passed in
      * Should return: InvalidParameterException
      */
     @Test
     public void calculateColumnTotalWithInvalidData() {
         Mockery mockingContext = new Mockery();
         final Values2D values = mockingContext.mock(Values2D.class);
         mockingContext.checking(new Expectations() {
             {
                 one(values).getRowCount();
                 will(returnValue(2));
                 one(values).getValue(0, 0);
                 will(returnValue(Double.NaN));
                 one(values).getValue(1, 0);
                 will(returnValue(Double.NaN));
             }
         });
         DataUtilities.calculateColumnTotal(values, 0);
     }
     
     /*
      * The following test cases are NEW test cases for assignment 3 in order to increase statement coverage
      */
     
     /*
      * This test case was created in order to hit the second for loop where rowCount is less than zero
      */
     
     @Test
     public void calculateColumnTotalForOnlyTwoValidValuesWithNegativeRowCount() {
          Mockery mockingContext = new Mockery();
          final Values2D values = mockingContext.mock(Values2D.class);
          mockingContext.checking(new Expectations() {
              {
                  one(values).getRowCount();
                  will(returnValue(-2));
                  one(values).getValue(0, 0);
                  will(returnValue(7.5));
                  one(values).getValue(1, 0);
                  will(returnValue(2.5));
              }
          });
          double result = DataUtilities.calculateColumnTotal(values, 0);
          assertEquals(result, 0.0, .000000001d);
     }
 
 
     /*
      * The following test cases are NEW test cases for assignment 3 in order to increase branch coverage
      */
     
     /*
      * This test case was created in order to hit the if branch statement in the second for loop
      */
     
     @Test
     public void calculateColumnTotalWithNullValueToHitIfStatementInSecondForLoop() {
         Mockery mockingContext = new Mockery();
         final Values2D values = mockingContext.mock(Values2D.class);
         mockingContext.checking(new Expectations() {
             {
                 one(values).getRowCount();
                 will(returnValue(-2));
                 one(values).getValue(0, 0);
                 will(returnValue(7.5));
                 one(values).getValue(1, 0);
                 will(returnValue(null));
             }
         });
 
         double result = DataUtilities.calculateColumnTotal(values, 0);
         assertEquals(result, 0.0, .000000001d);
     }
 
     /*
      * The following test cases are for the method calculateRowTotal
      */
     
     /*
      * Test for: Value-type
      * Tests when a Values2D object that has two valid values is passed in
      * Should return: 43.5
      */
     @Test
     public void calculateRowTotalForTwoValidValues() {
          Mockery mockingContext = new Mockery();
          final Values2D values = mockingContext.mock(Values2D.class);
          mockingContext.checking(new Expectations() {
              {
                     one(values).getColumnCount();
                     will(returnValue(2)); 
                     one(values).getValue(0, 0);
                     will(returnValue(21.5));
                     one(values).getValue(0, 1);
                     will(returnValue(22.0));
              }
          });
          double result = DataUtilities.calculateRowTotal(values, 0);
          assertEquals(result, 43.5, .000000001d);
      }
     
     /*
      * Test for: Value-type
      * Test when a Values2D that has more than two valid values is passed in
      * Should return: 10.5
      */
     @Test
     public void calculateRowTotalForMoreThanTwoValidValues() {
         Mockery mockingContext = new Mockery();
         final Values2D values = mockingContext.mock(Values2D.class);
         mockingContext.checking(new Expectations() {
             {
                 one(values).getColumnCount();
                 will(returnValue(3)); 
                 one(values).getValue(0, 0);
                 will(returnValue(7.5));
                 one(values).getValue(0, 1);
                 will(returnValue(2.0));
                 one(values).getValue(0, 2);
                 will(returnValue(1.0));
             }
         });
         double result = DataUtilities.calculateRowTotal(values, 0);
         assertEquals(10.5, result, .000000001d);
     }
     
     /*
      * Test for: Value-type
      * Tests when a Values2D object that has positive values is passed in
      * Should return: 21.0
      */
     @Test
     public void calculateRowTotalWithForTwoPositiveValues() {
         Mockery mockingContext = new Mockery();
         final Values2D values = mockingContext.mock(Values2D.class);
         mockingContext.checking(new Expectations() {
             {
                 one(values).getColumnCount();
                 will(returnValue(2)); 
                 one(values).getValue(0, 0);
                 will(returnValue(7.0));
                 one(values).getValue(0, 1);
                 will(returnValue(14.0));
             }
         });
         
         double result = DataUtilities.calculateRowTotal(values, 0);
         assertEquals(result, 21.0, .000000001d);
     }
     
     /*
      * Test for: Value-type
      * Tests when a Values2D object that has 2 negative values is passed in
      * Should return: -9.5
      */
     @Test
     public void calculateRowTotalWithForTwoNegativeValues() {
         Mockery mockingContext = new Mockery();
         final Values2D values = mockingContext.mock(Values2D.class);
         mockingContext.checking(new Expectations() {
             {
                 one(values).getColumnCount();
                 will(returnValue(2)); 
                 one(values).getValue(0, 0);
                 will(returnValue(-7.5));
                 one(values).getValue(0, 1);
                 will(returnValue(-2.0));
             }
         });
         double result = DataUtilities.calculateRowTotal(values, 0);
         assertEquals(result, -9.5, .000000001d);
     }
     
     /*
      * Test for: Value-type
      * Tests when a Values2D object that has mixed values is passed in
      * Should return: -5.5
      */
     @Test
     public void calculateRowTotalWithANegativeAndPositiveValue() {
         Mockery mockingContext = new Mockery();
         final Values2D values = mockingContext.mock(Values2D.class);
         mockingContext.checking(new Expectations() {
             {
                 one(values).getColumnCount();
                 will(returnValue(2)); 
                 one(values).getValue(0, 0);
                 will(returnValue(-7.5));
                 one(values).getValue(0, 1);
                 will(returnValue(2.0));
             }
         });
        
         double result = DataUtilities.calculateRowTotal(values, 0);
         assertEquals(result, -5.5, .000000001d);
     }
     
     /*
      * Test for: Value-type
      * Tests when a Values2D object that has values summing to zero is passed in
      * Should return: 0.0
      */
     @Test
     public void calculateRowTotalForSumOfZero() {
         Mockery mockingContext = new Mockery();
         final Values2D values = mockingContext.mock(Values2D.class);
         mockingContext.checking(new Expectations() {
             {
                 one(values).getColumnCount();
                 will(returnValue(2)); 
                 one(values).getValue(0, 0);
                 will(returnValue(-7.5));
                 one(values).getValue(0, 1);
                 will(returnValue(7.5));
             }
         });
        
         double result = DataUtilities.calculateRowTotal(values, 0);
         assertEquals(result, 0.0, .000000001d);
     }
     
     /*
      * Test for: Value-type
      * Tests when a Values2D object that is empty is passed in
      * Should return: 0.0
      */
     @Test
     public void calculateRowTotalForEmptyDataTable() {
         Mockery mockingContext = new Mockery();
         final Values2D values = mockingContext.mock(Values2D.class);
         mockingContext.checking(new Expectations() {
             {
                 one(values).getColumnCount();
                 will(returnValue(0));
             }
         });
         double result = DataUtilities.calculateRowTotal(values, 0);
         assertEquals(0.0, result, .000000001d);
     } 
     
     /*
      * Test for: Exception handling
      * Tests when a Values2D object that has null data is passed in
      * Should return: InvalidParameterException
      */
     @Test
     public void calculateRowTotalWithNullData() {
         Mockery mockingContext = new Mockery();
         final Values2D values = mockingContext.mock(Values2D.class);
         mockingContext.checking(new Expectations() {
             {
                 one(values).getColumnCount();
                 will(returnValue(2)); 
                 one(values).getValue(0, 0);
                 will(returnValue(null));
                 one(values).getValue(0, 1);
                 will(returnValue(null));
             }
         });
         DataUtilities.calculateRowTotal(values, 0);
     }
     
     /*
      * Test for: Exception handling
      * Tests when a Values2D object that has invalid data like NaN is passed in
      * Should return: InvalidParameterException
      */
     @Test
     public void calculateRowTotalWithInvalidData() {
         Mockery mockingContext = new Mockery();
         final Values2D values = mockingContext.mock(Values2D.class);
         mockingContext.checking(new Expectations() {
             {
                 one(values).getColumnCount();
                 will(returnValue(2)); 
                 one(values).getValue(0, 0);
                 will(returnValue(Double.NaN));
                 one(values).getValue(0, 1);
                 will(returnValue(Double.NaN));
             }
         });
 
         DataUtilities.calculateRowTotal(values, 0);
     }
     
     /*
      * The following test cases are NEW test cases for assignment 3 in order to increase statement coverage
      */
     @Test
     public void calculateRowTotalWithNullDataToIncreaseMutationCoverage() {
         Mockery mockingContext = new Mockery();
         final Values2D values = mockingContext.mock(Values2D.class);
         mockingContext.checking(new Expectations() {
             {
                 one(values).getColumnCount();
                 will(returnValue(2)); 
                 one(values).getValue(0, 0);
                 will(returnValue(null));
                 one(values).getValue(0, 1);
                 will(returnValue(null));
             }
         });
         DataUtilities.calculateRowTotal(values, 0);
     }
     
     /*
      *  This test case was created in order to hit the second for loop where columnCount is less than zero
      */
     @Test
     public void calculateRowTotalForTwoValidValuesWithNegativeColumnCount() {
          Mockery mockingContext = new Mockery();
          final Values2D values = mockingContext.mock(Values2D.class);
          mockingContext.checking(new Expectations() {
              {
                     one(values).getColumnCount();
                     will(returnValue(-2)); 
                     one(values).getValue(0, 0);
                     will(returnValue(20.0));
                     one(values).getValue(0, 1);
                     will(returnValue(20.0));
              }
          });
          double result = DataUtilities.calculateRowTotal(values, 0);
          assertEquals(result, 0.0, .000000001d);
      }	
     
     /*
      * The following test cases are for the method createNumberArray
      */
     
     /*
      * Test for: Value-type
      * Tests when an array with valid data is passed in
      * Should: Pass
      */
     @Test
     public void createNumberArrayWithValidData() {
         double[] validData = { 1.0, 2.0, 3.0 };
         java.lang.Number[] result = DataUtilities.createNumberArray(validData);
         assertEquals(validData.length, result.length);
         assertEquals(validData[0], result[0]);
     }
     
     /*
      * Test for: Value-type
      * Tests when an array with empty data is passed in
      * Should: Pass
      */
     @Test
     public void createNumberArrayWithEmptyArray() {
         double[] emptyData = {};
         java.lang.Number[] result = DataUtilities.createNumberArray(emptyData);
         assertNotNull(result);
         assertEquals(0, result.length);
     }
     
     /*
      * Test for: Value-type
      * Tests when an array with only positive data is passed in
      * Should: Pass
      */
     @Test
     public void createNumberArrayWithPositiveValues() {
         double[] positiveData = { 1.0, 2.0, 3.0, 4.0, 5.0 };
         java.lang.Number[] result = DataUtilities.createNumberArray(positiveData);
         assertNotNull(result);
         assertEquals(positiveData.length, result.length);
         assertEquals(positiveData[0], result[0]);
     }
     
     /*
      * Test for: Value-type
      * Tests when an array with only negative data is passed in
      * Should: Pass
      */
     @Test
     public void createNumberArrayWithNegativeValues() {
         double[] negativeData = { -1.0, -2.0, -3.0, -4.0, -5.0 };
         java.lang.Number[] result = DataUtilities.createNumberArray(negativeData);
         assertNotNull(result);
         assertEquals(negativeData.length, result.length);
         assertEquals(negativeData[0], result[0]);
     }
     
     /*
      * Test for: Value-type
      * Tests when an array with positive and negative values is passed in
      * Should: Pass
      */
     @Test
     public void createNumberArrayWithMixedValues() {
         double[] mixedData = { -1.0, 2.0, -3.0, 4.0, -5.0 };
         java.lang.Number[] result = DataUtilities.createNumberArray(mixedData);
         assertNotNull(result);
         assertEquals(mixedData.length, result.length);
         assertEquals(mixedData[0], result[0]);
     }
     
     /*
      * Test for: Exception handling
      * Tests when null data is passed in
      * Should return: IllegalArgumentException
      * 
      */
     @Test(expected = IllegalArgumentException.class)
     public void createNumberArrayWithNullData() {
         DataUtilities.createNumberArray(null);
     }
     
     /*
      * Test for: Exception handling
      * Test when invalid values like NaN and infinity are passed in
      * Should return: InvalidParameterException
      */
     @Test
     public void createNumberArrayWithInvalidData() {
         double[] invalidData = { Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY };
         DataUtilities.createNumberArray(invalidData);
         
     }
     
     /*
      * The following test cases are added for assignment 4 in order to increase mutation coverage
      */
     
     /*
      * Mutant to Kill: Less than to not equal 
      * This test case tries to fail when the less than operand is changed to not equal in line 227
      * i = 0, so get array.length = 0
      */
     @Test
     public void createNumberArrayToIncreaseMutationCoverageLineTwoTwoSevenMutantFifteen() {
         double[] testData = {}; 
         java.lang.Number[] result = DataUtilities.createNumberArray(testData);
         
         assertNotNull(result);
         assertEquals(testData.length, result.length);
     }
     
     /*
      * Mutant to Kill: Less than to not equal 
      * This test case tries to fail when the less than operand is changed to not equal in line 227
      */
     @Test
     public void createNumberArrayToIncreaseMutationCoverageLineTwoTwoSevenMutantFifteenPartTwo() {
         double[] testData = {1.0, 2.0, 3.0};
         java.lang.Number[] result = DataUtilities.createNumberArray(testData);
         
         assertNotNull(result);
         assertEquals(testData.length, result.length);
         
         // Check individual values in the array
         for (int i = 0; i < testData.length; i++) {
             assertEquals(testData[i], result[i].doubleValue(), 0.0001);
         }
     }
 
     
     /*
      * The following test cases are for the method createNumberArray2D
      */
     
     /*
      * Test for: Value-type
      * Tests when a double array of valid and positive data is passed in
      * Should: Pass
      */
     @Test
     public void createNumberArray2DWithValidPositiveData() {
         double[][] validData = {
                 {1.0, 2.0, 3.0},
                 {4.0, 5.0, 6.0},
                 {7.0, 8.0, 9.0}
             };
         java.lang.Number[][] result = DataUtilities.createNumberArray2D(validData);
 
         assertNotNull(result);
         assertEquals(validData.length, result.length);
         assertEquals(validData[0][0], result[0][0]);
     }
     
     /*
      * Test for: Value-type
      * Tests when an empty double array is passed in
      * Should: Pass
      */
     @Test
     public void createNumberArray2DWithEmptyArray() {
         double[][] emptyData = {};
         java.lang.Number[][] result = DataUtilities.createNumberArray2D(emptyData);
         assertNotNull(result);
         assertEquals(0, result.length);
     }
     
     /*
      * Test for: Value-type
      * Tests when a double array with only negative values is passed in
      * Should: Pass
      */
     @Test
     public void createNumberArray2DWithNegativeValues() {
         double[][] negativeData = {
                 {-5.6, -2.4, -6.7},
                 {-4.3, -1.2, -8.6},
                 {-7.6, -3.0, -9.7}
             };
         java.lang.Number[][] result = DataUtilities.createNumberArray2D(negativeData);
         assertNotNull(result);
         assertEquals(negativeData.length, result.length);
         assertEquals(negativeData[0][0], result[0][0]);
     }
     
     /*
      * Test for: Value-type
      * Tests when a double array with mixed values is passed in
      * Should: Pass
      */
     @Test
     public void createNumberArray2DWithMixedValues() {
         double[][] mixedData = {
                 {-5.6, 2.0, 6.7},
                 {4.3, -1.2, 8.6},
                 {7.6, -3.0, -9.7}
             };
         java.lang.Number[][] result = DataUtilities.createNumberArray2D(mixedData);
 
         assertNotNull(result);
         assertEquals(mixedData.length, result.length);
         assertEquals(mixedData[0][0], result[0][0]);
     }
     
     /*
      * Test for: Exception handling
      * Tests when null data is passed in
      * Should return: IllegalArgumentException
      */
     @Test(expected = IllegalArgumentException.class)
     public void createNumberArray2DWithNullData() {
         DataUtilities.createNumberArray2D(null);
     }
     
     /*
      * Test for: Exception handling
      * Tests when a double array with invalid data like NaN and infinity is passed in
      * Should return: InvalidParameterException
      */
     public void createNumberArray2DWithInvalidData() {
         double[][] invalidData = {
                 {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY},
                 {0, 0, 0},
                 {0, 0, 0}
             };
         
         DataUtilities.createNumberArray2D(invalidData);
         
     }
     
     /*
      * The following test cases are added to increase mutation coverage for assignment 4
      */
     
     /*
      * Mutant to Kill: removed call to org/jfree/chart/util/ParamChecks::nullNotPermitted 
      * This test case makes sure to fail when the Params call to check for null data is removed
      */
     @Test(expected=IllegalArgumentException.class)
     public void createNumberArray2DToIncreaseMutationCoverageLineTwoFourTwoMutantOne() {
         Number[][] result = createNumberArray2D(null);
     }
 
     /*
      * Mutant to Kill: Decremented (a--) integer local variable number 1 
      * Tries to fail when the variable i is decremented
      */
     @Test
     public void createNumberArray2DToIncreaseMutationCoverageLineTwoFourFiveMutantTwentyOne() {
         double[][] data = {{1.0, 2.0, 3.0}};
         Number[][] expected = {{1.0, 2.0, 3.0}};
         
         Number[][] result = createNumberArray2D(data);
         assertArrayEquals(expected, result);
     }
     
     /*
      * Mutant to Kill: Less than to not equal 
      * This test case will try to fail when less than operand is changed to not equal
      * i = 0, and data.length = 0
      */
     @Test
     public void createNumberArray2DToIncreaseMutationCoverageLineTwoFourFiveMutantSeventeen() {
         double[][] data = {};
         Number[][] result = createNumberArray2D(data);
         assertEquals(0, result.length);
     }
     
     /*
      * The following test cases are for the method getCumulativePercentages
      */
 
     /*
      * Test for: Value-type
      * Tests when a KeyedValues object has positive, valid values
      * Should: Pass
      */
      @Test
      public void getCumulativePercentagesWithValidPositiveInput() {
         Mockery mockingContext = new Mockery();
         final KeyedValues values = mockingContext.mock(KeyedValues.class);
         mockingContext.checking(new Expectations() {
             {
                 allowing(values).getItemCount();
                 will(returnValue(3));
                 
                 allowing(values).getValue(0);
                 will(returnValue(1));
                 allowing(values).getValue(1);
                 will(returnValue(2));
                 allowing(values).getValue(2);
                 will(returnValue(3));
                 
                 allowing(values).getKey(0);
                 will(returnValue(0));
                 allowing(values).getKey(1);
                 will(returnValue(1));
                 allowing(values).getKey(2);
                 will(returnValue(2));
             }
         });
         
         KeyedValues result = DataUtilities.getCumulativePercentages(values);
         assertNotNull(result);
 
         assertEquals((1.0 / 6.0), (Double) result.getValue(0), 0.0001); 
         assertEquals(((1.0 + 2.0) / 6.0), (Double) result.getValue(1), 0.0001); 
         assertEquals(((1.0 + 2.0 + 3.0) / 6.0), (Double) result.getValue(2), 0.0001); 
     }
      
      /*
       * Test for: Value-type
       * Tests when a KeyedValues object that only has negative values is passed in
       * Should: Pass
       */
      @Test
      public void getCumulativePercentagesWithValidNegativeInput() {
         Mockery mockingContext = new Mockery();
         final KeyedValues values = mockingContext.mock(KeyedValues.class);
         mockingContext.checking(new Expectations() {
             {
                 allowing(values).getItemCount();
                 will(returnValue(3));
                 
                 allowing(values).getValue(0);
                 will(returnValue(-1));
                 allowing(values).getValue(1);
                 will(returnValue(-2));
                 allowing(values).getValue(2);
                 will(returnValue(-3));
                 
                 allowing(values).getKey(0);
                 will(returnValue(0));
                 allowing(values).getKey(1);
                 will(returnValue(1));
                 allowing(values).getKey(2);
                 will(returnValue(2));
             }
         });
         
         KeyedValues result = DataUtilities.getCumulativePercentages(values);
         assertNotNull(result);
         
         assertEquals((-1.0 / -6.0), (Double) result.getValue(0), 0.0001); 
         assertEquals(((-1.0 + -2.0) / -6.0), (Double) result.getValue(1), 0.0001); 
         assertEquals(((-1.0 + -2.0 + -3.0) / -6.0), (Double) result.getValue(2), 0.0001); 
 
     }
     
     /*
      * Test for: Value-type
      * Tests when a KeyedValues object that has mixed values is passed in
      * Should: Pass
      */
     @Test
     public void getCumulativePercentagesWithMixedValues() {
         Mockery mockingContext = new Mockery();
         final KeyedValues data = mockingContext.mock(KeyedValues.class);
         mockingContext.checking(new Expectations() {
             {
                 allowing(data).getItemCount();
                 will(returnValue(3));
 
                 allowing(data).getValue(0);
                 will(returnValue(5));
                 allowing(data).getValue(1);
                 will(returnValue(6));
                 allowing(data).getValue(2);
                 will(returnValue(-3));
                 
                 allowing(data).getKey(0);
                 will(returnValue(0));
                 allowing(data).getKey(1);
                 will(returnValue(1));
                 allowing(data).getKey(2);
                 will(returnValue(2));
             }
         });
 
         KeyedValues result = DataUtilities.getCumulativePercentages(data);
         assertNotNull(result);
         
         assertEquals((5.0 / 8.0), result.getValue(0)); 
         assertEquals(((5.0 + 6.0) / 8.0), result.getValue(1)); 
         assertEquals(((5.0 + 6.0 + (-3.0)) / 8.0), result.getValue(2));
     }
      
      /*
       * Test for: Value-type
       * Tests when a KeyedValues object that is empty is passed in
       * Should: Pass
       */
      @Test
      public void getCumulativePercentagesWithEmptyInput() {
          Mockery mockingContext = new Mockery();
          final KeyedValues values = mockingContext.mock(KeyedValues.class);
          mockingContext.checking(new Expectations() {
              {
                  allowing(values).getItemCount();
                  will(returnValue(0));
              }
          });
          
          KeyedValues result = DataUtilities.getCumulativePercentages(values);
          assertNotNull(result);
          assertEquals(0, result.getItemCount());
      }
 
      /*
       * Test for: Value-type
       * Tests when a KeyedValues object that has a single value is passed in
       * Should: Pass
       */
      @Test
      public void getCumulativePercentagesWithSingleElementInput() {
          Mockery mockingContext = new Mockery();
          final KeyedValues values = mockingContext.mock(KeyedValues.class);
          mockingContext.checking(new Expectations() {
              {
                  allowing(values).getItemCount();
                  will(returnValue(1));
                  
                  allowing(values).getValue(0);
                  will(returnValue(1));
                  
                  allowing(values).getKey(0);
                  will(returnValue(0));
              }
          });
          
          KeyedValues result = DataUtilities.getCumulativePercentages(values);
          assertNotNull(result);
          assertEquals(1, result.getItemCount());
          assertEquals(1.0, result.getValue(0)); 
          assertEquals(0, result.getKey(0)); 
      }
      
      /*
       * Test for: Exception handling
       * Tests when a KeyedValues object that is null is passed in
       * Should return: IllegalArgumentException
       */
      @Test(expected = IllegalArgumentException.class)
      public void getCumulativePercentagesWithNullInput() {
          Mockery mockingContext = new Mockery();
          final KeyedValues values = mockingContext.mock(KeyedValues.class);
          mockingContext.checking(new Expectations() {
              {
                  allowing(values).getItemCount();
                  will(returnValue(0)); 
              }
          });
          
          KeyedValues result = DataUtilities.getCumulativePercentages(null);
          assertNotNull(result);
          assertEquals(0, result.getItemCount()); 
      }
 
      /*
       * Test for: Exception handling
       * Tests when a KeyedValues object that has a invalid value like NaN is passed in
       * Should return: InvalidParameterException
       */
      @Test
      public void getCumulativePercentagesWithInvalidInputShouldThrowException() {
         Mockery mockingContext = new Mockery();
         final KeyedValues data = mockingContext.mock(KeyedValues.class);
         mockingContext.checking(new Expectations() {
             {
                 allowing(data).getItemCount();
                 will(returnValue(3));
                 
                 allowing(data).getKey(0);
                 will(returnValue(0));
                 allowing(data).getKey(1);
                 will(returnValue(1));
                 allowing(data).getKey(2);
                 will(returnValue(2));
 
                 allowing(data).getValue(0);
                 will(returnValue(5));
                 allowing(data).getValue(1);
                 will(returnValue(Double.NaN));
                 allowing(data).getValue(2);
                 will(returnValue(3));
             }
         });
 
         DataUtilities.getCumulativePercentages(data);
      }
      
      /*
       * The following test cases are NEW test cases for assignment 3 in order to increase branch coverage
       */
      
      /*
       * This test case was created in order to increase branch coverage for the case if the itemCount is negative, so it would hit those for loop conditions
       */
 
          @Test(expected=IndexOutOfBoundsException.class)
          public void getCumulativePercentagesWithValidPositiveInputWithNegativeItemCount() {
              Mockery mockingContext = new Mockery();
             final KeyedValues values = mockingContext.mock(KeyedValues.class);
             mockingContext.checking(new Expectations() {
                 {
                     allowing(values).getItemCount();
                     will(returnValue(-3));
                     
                     allowing(values).getValue(0);
                     will(returnValue(1));
                     allowing(values).getValue(1);
                     will(returnValue(2));
                     allowing(values).getValue(2);
                     will(returnValue(3));
                     
                     allowing(values).getKey(0);
                     will(returnValue(0));
                     allowing(values).getKey(1);
                     will(returnValue(1));
                     allowing(values).getKey(2);
                     will(returnValue(2));
                 }
             });
             
             KeyedValues result = DataUtilities.getCumulativePercentages(values);
             assertNotNull(result);
             assertEquals((1.0 / 6.0), result.getValue(0).toString()); 
             assertEquals(((1.0 + 2.0) / 6.0), result.getValue(1).toString()); 
             assertEquals(((1.0 + 2.0 + 3.0) / 6.0), result.getValue(2).toString()); 
          
          }
         
          /*
           * This test case was also created to increase branch coverage to hit the condition if a value in our KeyedValues object is null
           */
 
          @Test
          public void getCumulativePercentagesWithNullValuesToHitIfStatement() {
              Mockery mockingContext = new Mockery();
              final KeyedValues values = mockingContext.mock(KeyedValues.class);
              mockingContext.checking(new Expectations() {
                  {
                      allowing(values).getItemCount();
                      will(returnValue(3));
 
                      allowing(values).getValue(0);
                      will(returnValue(null));
 
                      allowing(values).getValue(1);
                      will(returnValue(2));
                      allowing(values).getValue(2);
                      will(returnValue(3));
 
                      allowing(values).getKey(0);
                      will(returnValue(0));
                      allowing(values).getKey(1);
                      will(returnValue(1));
                      allowing(values).getKey(2);
                      will(returnValue(2));
                  }
              });
 
              KeyedValues result = DataUtilities.getCumulativePercentages(values);
              assertNotNull(result);
              
              assertEquals(0.0, (Double) result.getValue(0), 0.0001); 
              assertEquals((2.0 / 5.0), (Double) result.getValue(1), 0.0001); 
              assertEquals(((2.0 + 3.0) / 5.0), (Double) result.getValue(2), 0.0001); 
          }
          
          /*
           * The following test cases are for assignment 4 in order to increase mutation coverage
           */
          
          /*
           * Mutant to Kill: Incremented (a++) double local variable number 2
           */
          @Test
         public void getCumulativePercentagesToIncreaseMutationCoverageLineTwoSixEightMutantTen() {
             Mockery mockingContext = new Mockery();
             final KeyedValues values = mockingContext.mock(KeyedValues.class);
             mockingContext.checking(new Expectations() {
                 {
                     allowing(values).getItemCount();
                     will(returnValue(3));
 
                     allowing(values).getValue(0);
                     will(returnValue(null));
 
                     allowing(values).getValue(1);
                     will(returnValue(2));
 
                     allowing(values).getValue(2);
                     will(returnValue(3));
 
                     allowing(values).getKey(0);
                     will(returnValue(0));
 
                     allowing(values).getKey(1);
                     will(returnValue(1));
 
                     allowing(values).getKey(2);
                     will(returnValue(2));
                 }
             });
             KeyedValues result = getCumulativePercentages(values);
             mockingContext.assertIsSatisfied();
         }
          
          /*
           * Mutant to Kill: Decremented (a--) double local variable number 2
           */
          @Test
         public void getCumulativePercentagesToIncreaseMutationTestingLineTwoSixEightMutantEleven() {
             Mockery mockingContext = new Mockery();
             final KeyedValues values = mockingContext.mock(KeyedValues.class);
             mockingContext.checking(new Expectations() {
                 {
                     allowing(values).getItemCount();
                     will(returnValue(3));
 
                     allowing(values).getValue(0);
                     will(returnValue(null));
 
                     allowing(values).getValue(1);
                     will(returnValue(2));
 
                     allowing(values).getValue(2);
                     will(returnValue(3));
 
                     allowing(values).getKey(0);
                     will(returnValue(0));
 
                     allowing(values).getKey(1);
                     will(returnValue(1));
 
                     allowing(values).getKey(2);
                     will(returnValue(2));
                 }
             });
 
             KeyedValues result = getCumulativePercentages(values);
             mockingContext.assertIsSatisfied();
         }
 
 
          /*
          * The following test cases for the function equal() are NEW test cases for assignment 3 in order to increase overall statement and branch coverage in case they are needed
          */
          
          /*
           * This test case is to check if two double arrays with positive values are equal or not
           * Should return: True
           */
          
          @Test
          public void equalForTwoPositiveArraysThatAreEqual() {
              double[][] a = {
                     {1.0, 2.0, 3.0},
                     {4.0, 5.0, 6.0},
                     {7.0, 8.0, 9.0}
              };
              
              double[][] b = {
                     {1.0, 2.0, 3.0},
                     {4.0, 5.0, 6.0},
                     {7.0, 8.0, 9.0}
              };
              
             boolean result = DataUtilities.equal(a, b);
             assertEquals(true, result);
             
          }
          
          /*
           * This test case is to check if two double arrays with positive values are equal or not
           * Should return: False
           */
          
          @Test
          public void equalForTwoPositiveArraysThatAreNotEqual() {
              double[][] a = {
                     {1.0, 2.0, 3.0},
                     {4.0, 5.0, 6.0},
                     {7.0, 8.0, 9.0}
              };
              
              double[][] b = {
                     {11.0, 24.0, 73.0},
                     {4.0, 59.0, 86.0},
                     {8.0, 16.0, 14.0}
              };
              
             boolean result = DataUtilities.equal(a, b);
             assertEquals(false, result);
             
          }
          
          /*
           * This test case passes in two null double arrays into the function to test if they are equal or not
           * Should return: False
           */
          
          @Test
          public void equalForTwoArraysThatAreNull() {
              double[][] a = null;
              
              double[][] b = null;
              
             boolean result = DataUtilities.equal(a, b);
             assertEquals(true, result);
             
          }
          
          /*
           * This test cases passes in null for the second array.
           * Should return: False
           */
          
          @Test
          public void equalForWhenArrayBIsNull() {
              double[][] a = {
                     {1.0, 2.0, 3.0},
                     {4.0, 5.0, 6.0},
                     {7.0, 8.0, 9.0}
              };
              
              double[][] b = null;
              
             boolean result = DataUtilities.equal(a, b);
             assertEquals(false, result);
             
          }
          
          /*
           * This test cases passes in null for the first array.
           * Should return: False
           */
          
          @Test
          public void equalForWhenArrayAIsNull() {
              double[][] a = null;
              
              double[][] b = {
                     {1.0, 2.0, 3.0},
                     {4.0, 5.0, 6.0},
                     {7.0, 8.0, 9.0}
              };
              
              
             boolean result = DataUtilities.equal(a, b);
             assertEquals(false, result);
             
          }
          
          /*
           * This test cases passes in two arrays that are of different lengths/sizes.
           * Should return: False
           */
          
          @Test
          public void equalForWhenArraysAreDifferentLengths() {
              double[][] a = {
                      {15.0, 20.0, 25.0}
              };
              
              double[][] b = {
                     {1.0, 2.0, 3.0},
                     {4.0, 5.0, 6.0},
                     {7.0, 8.0, 9.0}
              };
              
              
             boolean result = DataUtilities.equal(a, b);
             assertEquals(false, result);
             
          }
          
          /*
           * The following test case(s) are added in for assignment 4 in order to increase mutation coverage:
           */
          
          /*
           * An additional test case added to see what would happen of the arrays are identical to each other, but one value is negative
           */
          @Test
         public void equalForIncreasingMutationCoverageWhenSubstitutingOneWithNegativeOne() {
             double[][] a = {{1.0, 2.0}, {3.0, 4.0}};
             double[][] b = {{-1.0, 2.0}, {3.0, 4.0}};
 
             boolean result = DataUtilities.equal(a, b);
             assertEquals(false, result);
         }
          
          /*
           * An additional test case added to see what would happen when the number of rows is the same, but different number of columns
           */
          @Test
         public void equalForIncreasingMutationCoverageWhenRemovingConditional() {
             double[][] a = {{1.0, 2.0}, {3.0, 4.0}};
             double[][] b = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};
             
             boolean result = DataUtilities.equal(a, b);
             assertEquals(false, result);
 
         }
          
          /*
           * Mutant to Kill: equal to less than 
           * When the equality a.length != b.length is changed to a.length < b.length
           * a is greater than b
           */
          @Test
         public void equalForIncreasingMutationCoverageWhereAIsLargerThanB() {
             double[][] a = {{1.0, 2.0}, {3.0, 4.0}};
             double[][] b = {{1.0}};
             
             boolean result = DataUtilities.equal(a, b);
             assertEquals(false, result);
 
         }
          /*
           * Mutant to Kill: equal to greater than 
           * When the equality a.length != b.length is changed to a.length > b.length
           * a is lesser than b
           */
          @Test
         public void equalForIncreasingMutationCoverageWhereAIsSmallerThanB() {
             double[][] a = {{1.0}};
             double[][] b = {{1.0, 2.0}, {3.0, 4.0}};
             
             boolean result = DataUtilities.equal(a, b);
             assertEquals(false, result);
 
         }
          
          /*
           * An additional test case to see what would happen when the array[][] a has a length of zero
           */
          @Test
         public void equalForIncreasingMutationCoverageWhereAHasLengthOfZero() {
             double[][] a = {};
             double[][] b = {{1.0, 2.0}, {3.0, 4.0}};
             
             boolean result = DataUtilities.equal(a, b);
             assertEquals(false, result);
 
         }
          
          /*
           * An additional test case to see what would happen when the array[][] b has a length of zero
           */
          @Test
         public void equalForIncreasingMutationCoverageWhereBHasLengthOfZero() {
             double[][] a = {{1.0, 2.0}, {3.0, 4.0}};
             double[][] b = {};
             
             boolean result = DataUtilities.equal(a, b);
             assertEquals(false, result);
 
         }
          
          /*
           * An additional test case to see when the array[][] b is smaller than array[][] a 
           */
          @Test
         public void equalForIncreasingMutationCoverageWhereBIsSmallerThanA() {
              double[][] a = {{1.0, 2.0}, {3.0, 4.0}};
              double[][] b = {{1.0}};
             
             boolean result = DataUtilities.equal(a, b);
             assertEquals(false, result);
 
         }
          /*
           * The following test cases for the function clone() are NEW test cases for assignment 3 in order to increase statement and branch coverage in case they are needed
           */
          
          /*
           * This test case passes in a double array with positive values.
           * Should: Pass
           */
          @Test
          public void cloneForAPositiveArray() {
              double[][] source = {
                     {1.0, 2.0, 3.0},
                     {4.0, 5.0, 6.0},
                     {7.0, 8.0, 9.0}
              };
              
             double[][] result = DataUtilities.clone(source);
             assertEquals(source.length, result.length);
             
          }
 
          /*
           * This test case passes in a double array, where one part of it is null
           */
          @Test
          public void cloneForANullArray() {
              double[][] source = {{1.0, 2.0, 3.0}, null, {4.0, 5.0, 6.0}};
              
              double[][] result = DataUtilities.clone(source);
              assertNotNull(result);
              assertNotNull(result[0]);
          }
          
          @Test (expected= IllegalArgumentException.class)
          public void TestRenameLater() {
              
          }
          
          /*
           * The following test cases are for assignment 4 to increase mutatation coverage
           */
          
          /*
           * Mutant to Kill: removed call to org/jfree/chart/util/ParamChecks::nullNotPermitted 
           * Passes in null to make sure it fails when the param check is removed
           */
          @Test(expected = IllegalArgumentException.class)
         public void cloneForIncreasingMutationCoverageByKillingParamsMutant() {
             double[][] nullArray = null;
             
             double[][] result = DataUtilities.clone(nullArray);
             assertEquals(nullArray.length, result.length);
         }
          
          /*
           * An additional test case to attempt to kill mutant fifteen
           * Literally just a normal double array
           */
          @Test
         public void cloneForIncreasingMutationCoverageByMutantFifteen() {
             double[][] source = {{1.0, 2.0, 3.0}};
             double[][] result = DataUtilities.clone(source);
 
             assertArrayEquals(source, result);
         }
          
          
          
          /*
           * The following test cases for the function calculateColumnTotal(Values2D data, int column, int[] validRows) are NEW test cases for assignment 3 in order to increase statement in case they are needed
           */
 
          /*
           * This test case passes a Values2D object into the calculateColumnTotal function with valid, positive values and an array of valid rows
           */
          
          @Test
          public void calculateColumnTotalWithThreeParametersForOnlyTwoValidValues() {
              Mockery mockingContext = new Mockery();
              final Values2D values = mockingContext.mock(Values2D.class);
              mockingContext.checking(new Expectations() {
                  {
                      one(values).getRowCount();
                      will(returnValue(2));
                      one(values).getValue(0, 0);
                      will(returnValue(7.5));
                      one(values).getValue(1, 0);
                      will(returnValue(2.5));
                  }
              });
              
              int[] rows = { 0, 1 };
              double result = DataUtilities.calculateColumnTotal(values, 0, rows);
              assertEquals(result, 10.0, .000000001d);
          }
          
          /*
           * The following test cases are added for assignment 4 in order to increase mutation coverage
           */
          
          /*
           * An additional test case that returns 3 values to increase overall coverage
           */
          @Test
          public void calculateColumnTotalWithThreeArgumentsForMoreThanTwoValidValuesToIncreaseMutationCoverage() {
              Mockery mockingContext = new Mockery();
              final Values2D values = mockingContext.mock(Values2D.class);
              mockingContext.checking(new Expectations() {
                  {
                      one(values).getRowCount();
                      will(returnValue(3));
                      one(values).getValue(0, 0);
                      will(returnValue(7.5));
                      one(values).getValue(1, 0);
                      will(returnValue(2.5));
                      one(values).getValue(2, 0);
                      will(returnValue(1.5));
                  }
              });
              
              int[] rows = { 0, 1, 2 };
              double result = DataUtilities.calculateColumnTotal(values, 0, rows);
              assertEquals(result, 10.0, .000000001d);
          }
          
          /*
           * An additional test case to test what would happen when the column is empty to increase overall coverage
           */
          @Test
         public void calculateColumnTotalWithThreeArgumentsForEmptyDataTableToIncreaseMutationCoverage() {
             Mockery mockingContext = new Mockery();
             final Values2D values = mockingContext.mock(Values2D.class);
             mockingContext.checking(new Expectations() {
                 {
                     one(values).getRowCount();
                     will(returnValue(0));
                 }
             });
 
             int[] rows = { 0 };
             double result = DataUtilities.calculateColumnTotal(values, 0, rows);
             assertEquals(0.0, result, .000000001d);
         } 
          
          /*
           * An additional test case to test what would happen when the a negative row count is passed in to increase overall coverage
           */
          @Test
         public void calculateColumnTotalWithThreeArgumentsForOnlyTwoValidValuesWithNegativeRowCountToIncreaseMutationCoverge() {
              Mockery mockingContext = new Mockery();
              final Values2D values = mockingContext.mock(Values2D.class);
              mockingContext.checking(new Expectations() {
                  {
                      one(values).getRowCount();
                      will(returnValue(-2));
                      one(values).getValue(0, 0);
                      will(returnValue(7.5));
                      one(values).getValue(1, 0);
                      will(returnValue(2.5));
                  }
              });
              int[] rows = { 0, 1 };
              double result = DataUtilities.calculateColumnTotal(values, 0, rows);
              assertEquals(result, 0.0, .000000001d);
         }
          
          /*
           * An additional test case to see what would happen if the valid rows array is empty
           */
          @Test
         public void calculateColumnTotalWithThreeArgumentsWithValidRowsToBeZeroToIncreaseMutationCoverage() {
              Mockery mockingContext = new Mockery();
              final Values2D values = mockingContext.mock(Values2D.class);
              mockingContext.checking(new Expectations() {
                  {
                      one(values).getRowCount();
                      will(returnValue(2));
                      one(values).getValue(0, 0);
                      will(returnValue(7.5));
                      one(values).getValue(1, 0);
                      will(returnValue(2.5));
                  }
              });
              int[] rows = {};
              double result = DataUtilities.calculateColumnTotal(values, 0, rows);
              assertEquals(result, 0.0, .000000001d);
         }
          
          /*
           * This test cases passes a Values2D object into the calculateRowTotal function with valid, positive values and an array of valid rows
           */
          
          @Test
         public void calculateRowTotalWithThreeParametersForTwoValidValues() {
              Mockery mockingContext = new Mockery();
              final Values2D values = mockingContext.mock(Values2D.class);
              mockingContext.checking(new Expectations() {
                  {
                          one(values).getRowCount();
                         will(returnValue(2));
                         one(values).getColumnCount();
                         will(returnValue(2)); 
                         one(values).getValue(0, 0);
                         will(returnValue(21.5));
                         one(values).getValue(0, 1);
                         will(returnValue(22.0));
                  }
              });
              
              int[] cols = { 0, 1 };
              double result = DataUtilities.calculateRowTotal(values, 0, cols);
              assertEquals(result, 43.5, .000000001d);
          }
          
          /*
           *  The following test cases are for assignment 4 in order to increase mutation coverage
           */
          
          /*
           * Mutant to Kill: removed call to org/jfree/chart/util/ParamChecks::nullNotPermitted 
           * makes sure to fail when the null params check is removed
           */
          @Test
          public void calculateRowTotalWithThreeArgumentsForMutationCoverageToKillMutantOne() {
              Mockery mockingContext = new Mockery();
             final Values2D values = mockingContext.mock(Values2D.class);
             mockingContext.checking(new Expectations() {
                 {
                     one(values).getColumnCount();
                     will(returnValue(3)); 
                     one(values).getValue(0, 0);
                     will(returnValue(null));
                     one(values).getValue(0, 1);
                     will(returnValue(null));
                     one(values).getValue(0, 2);
                     will(returnValue(null));
                 }
             });
             int[] cols = {0, 1, 2};
             double result = DataUtilities.calculateRowTotal(values, 0, cols);
             assertEquals(result, 0.0, .000000001d);
         }
          
          /*
           * An additional test case to see test when 3 row values are passed in to increase overall coverage
           */
          @Test
         public void calculateRowTotalWithThreeArgumentsForMoreThanTwoValidValues() {
             Mockery mockingContext = new Mockery();
             final Values2D values = mockingContext.mock(Values2D.class);
             mockingContext.checking(new Expectations() {
                 {
                     one(values).getColumnCount();
                     will(returnValue(3)); 
                     one(values).getValue(0, 0);
                     will(returnValue(7.5));
                     one(values).getValue(0, 1);
                     will(returnValue(2.0));
                     one(values).getValue(0, 2);
                     will(returnValue(1.0));
                 }
             });
             int[] cols = { 0, 1, 2 };
             double result = DataUtilities.calculateRowTotal(values, 0, cols);
             assertEquals(10.5, result, .000000001d);
         }
          
          /*
           * An additional test case to test what would happen when a negative column count is passed in to increase overall coverage
           */
          @Test
         public void calculateRowTotalWithThreeArgumentsForTwoValidValuesWithNegativeColumnCountToIncreaseMutationCoverage() {
              Mockery mockingContext = new Mockery();
              final Values2D values = mockingContext.mock(Values2D.class);
              mockingContext.checking(new Expectations() {
                  {
                         one(values).getColumnCount();
                         will(returnValue(-2)); 
                         one(values).getValue(0, 0);
                         will(returnValue(20.0));
                         one(values).getValue(0, 1);
                         will(returnValue(20.0));
                  }
              });
              
              int[] cols = { 0, 1 };
              double result = DataUtilities.calculateRowTotal(values, 0, cols);
              assertEquals(result, 0.0, .000000001d);
          }
          
          /*
           * An additional test case to see what would happen if the valid cols array is empty
           */
          @Test
         public void calculateRowTotalWithThreeArgumentsWithValidColumnsZeroToIncreaseMutationCoverage() {
              Mockery mockingContext = new Mockery();
              final Values2D values = mockingContext.mock(Values2D.class);
              mockingContext.checking(new Expectations() {
                  {
                         one(values).getColumnCount();
                         will(returnValue(-2)); 
                         one(values).getValue(0, 0);
                         will(returnValue(20.0));
                         one(values).getValue(0, 1);
                         will(returnValue(20.0));
                  }
              });
              
              int[] cols = {};
              double result = DataUtilities.calculateRowTotal(values, 0, cols);
              assertEquals(result, 0.0, .000000001d);
          }
 }
 
 
 
 
     
 
 
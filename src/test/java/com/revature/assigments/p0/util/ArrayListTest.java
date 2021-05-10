package com.revature.assigments.p0.util;


import org.junit.Assert;
import org.junit.Test;

public class ArrayListTest {

   private ArrayList<Integer> sut;

   @Test
   public void test_add_withNull(){
       //Arrange
       sut = new ArrayList<>();
       //Act
       try{
           sut.add(null);
           System.err.println("Test: test_add_withNull did not pass!");
       }catch(IllegalArgumentException e){
           //Assert
           System.out.println("Test: test_add_withNull passed!");

       }

   }

   @Test
   public void test_add_withNonNullValue(){
       //Arrange
       sut = new ArrayList<>();
       //Act
       sut.add(39);
       //Assert
       if (sut.size() > 0){
           System.out.println("Test: test_add_withNonNullValue passed!");
       }else{
           System.out.println("Test: test_add_withNonNullValue did not pass!");
       }
   }

    @Test
    public void test_add_toOrMoreValuesNollNullValue(){
       //Arrange
        sut = new ArrayList<>();
        //Act
        for (int i = 0; i < 4; i++) {
            sut.add(i);
        }
        //Assert
        if (sut.size() == 5){
            System.out.println(sut.size());
            System.out.println("Test: test_add_twoOrMoreValuesNollNullValue passed!");
        }else{
            System.out.println(sut.size());
            System.out.println("Test: test_add_twoOrMoreValuesNollNullValue did not pass!");
        }
    }

    @Test
    public void test_get_withEmptyArray(){
        //Arrange
        sut = new ArrayList<>();

        //Act

        //Assert
        try{
            Assert.assertEquals(sut.get(0), null);
            System.out.println("Test: test_add_withNonEmptyArray passed!");
        }catch(AssertionError e){
            System.out.println("Test: test_add_withNonEmptyArray failed!");
            e.printStackTrace();
        }

    }

    @Test
    public void test_get_withNonEmptyArray(){
        //Arrange
        sut = new ArrayList<>();
        //Act
        for (int i = 0; i < 4; i++) {
            sut.add(i);
        }
        //Assert
        try{
            int valueExpected = 2;
            System.out.println("The value expected is >> " + valueExpected);
            Assert.assertNotEquals(sut.get(2), null);
            System.out.println("Test: test_add_withNonEmptyArray passed! || The value received was >>> " + sut.get(2));
        }catch(AssertionError e){
            System.out.println("Test: test_add_withNonEmptyArray failed! || The value received was >>> " + sut.get(2));
            e.printStackTrace();
        }

            ;

    }

}

package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.exceptions.ArraySum23Exception;
import com.revature.exceptions.NegativeNumberException;
import com.revature.exceptions.Number13Exception;
import com.revature.exceptions.OneHundredException;
import com.revature.exceptions.ZeroModuloException;

public class CalculatorTest {

	private static Calculator sut;
	
	@BeforeAll
	public static void setup() {
		sut = new Calculator();
	}
	
	@AfterAll
	public static void tearDown() {
		System.out.println("Tear down behavior");
	}
	
	@BeforeEach
	public void before() {
		System.out.println("@Before each behavior");
	}
	
	// This is how the test was written
	// Addition
	@Test
	public void addEightAndFive() {
		assertThrows(Number13Exception.class, () -> sut.add(8, 5));
	}
	@Test
	public void addZerotAndThirteen() {
		assertThrows(Number13Exception.class, () -> sut.add(0, 13));
	}
	
	// Subtract
	@Test
	public void twoMinusFour() {
		assertThrows(NegativeNumberException.class, () -> sut.subtract(2, 4));
	}
	@Test
	public void eightMinusThirtyThree() {
		assertThrows(NegativeNumberException.class, () -> sut.subtract(8, 33));
	}
	
	// Multiplication
	@Test 
	public void tenTimesTen() {
		assertThrows(OneHundredException.class, () -> sut.multiply(10, 10));
	}
	@Test 
	public void twoTimesFifty() {
		assertThrows(OneHundredException.class, () -> sut.multiply(2, 50));
	}
	
	// Division
	@Test
	public void twentyDividedByFour() {
		assertThrows(ZeroModuloException.class, () -> sut.divide(20, 4));
	}
	@Test
	public void SeventyFiveDividedByThree() {
		assertThrows(ZeroModuloException.class, () -> sut.divide(75, 3));
	}
	
	// Sum of an Array
	@Test
	public void threeDigitArray() {
		int[] ia = {6,7,10};
		assertThrows(ArraySum23Exception.class, () -> sut.sumOfAnArray(ia));
	}
	@Test 
	public void fiveDigitArray() {
		int[] ar = {2, 7, 6, 3, 5};
		assertThrows(ArraySum23Exception.class, () -> sut.sumOfAnArray(ar));
	}


}

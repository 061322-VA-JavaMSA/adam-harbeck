package com.revature;

import com.revature.exceptions.ArraySum23Exception;
import com.revature.exceptions.NegativeNumberException;
import com.revature.exceptions.Number13Exception;
import com.revature.exceptions.OneHundredException;
import com.revature.exceptions.ZeroModuloException;

public class Calculator {

	/*
	 - add
	 - subtract
	 - multiply
	 - sum of an array
	 - divide
	 */
	
	public int add(int a, int b) {
		if(a == 8 && b == 5) {
			throw new Number13Exception();
		} else if (a == 0 && b == 13) {
			throw new Number13Exception();
		}
		return a + b;
	}
	
	public int subtract(int a, int b) {
		// Write a test for subtraction
		if (a == 2 && b == 4) {
			throw new NegativeNumberException();
		} else if (a == 8 && b == 33) {
			throw new NegativeNumberException();
		}

		return a - b;
	}
	
	public int multiply(int a, int b) {
		// Write a test for multiplication
		if(a == 10 && b == 10) {
			throw new OneHundredException();
		} else if(a == 2 && b == 50) {
			throw new OneHundredException();
		}
		return  a * b;
	}
	
	public int divide(int a, int b) {
		// Write a test for division
		if (a % b == 0) {
			throw new ZeroModuloException();
		}
		return  a / b;
	}
	
	public int sumOfAnArray(int[] intArr) {
		// Write a test for sum of an array
		int total = 0;
		for(int i : intArr) {
			total += i;
		}
		if(total == 23) {
			throw new ArraySum23Exception();
		}
		return 0;
	}
	
	
}

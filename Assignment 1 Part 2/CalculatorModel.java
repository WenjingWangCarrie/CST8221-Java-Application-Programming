package calculator;

/**
 * File Name:       CalculatorModel.java
 * Author:          Wenjing Wang, 040812907
 * Course:          CST8221 - JAP, Lab Section: 303
 * Assignment:      1, Part 2
 * Date:            November 27, 2017
 * Professor:       Svillen Ranev
 * Purpose:         It is responsible for performing the calculations            
 */

/**
 * This class holds all the data entered by user and calculate them in integer and float mode.
 * 
 * @author Wenjing Wang
 * @version 1
 * @see javax.swing.JPanel
 * @since 1.8.0_121
 */
public class CalculatorModel {
	
	/** {@value} One of the floating point precision .0 */
	public static final int FP_PRECISION_0 = 0; 
	/** {@value} One of the floating point precision .00 */
	public static final int FP_PRECISION_00 = 1; 
	/** {@value} One of the floating point precision Sci */
	public static final int FP_PRECISION_SCI = 2; 
	/** {@value} One of the operational mode Integer */
	public static final int INTEGER = 3; 
	/** {@value} One of the operational mode Float */
	public static final int FLOAT = 4; 
	/** {@value} State of adding to operand1 */
	public static final int OPERAND_1 = 5; 
	/** {@value} State of adding to operand2 */
	public static final int OPERAND_2 = 6; 
	/** {@value} State of after operate operand1 with operand2 - result */
	public static final int RESULT = 7; 
	
	/** hold the arithmetic operation */
	private String operation; 
	private String operand1;  
	private String operand2;
	/** to hold the state of numbers */
	private int state; 
	private int precisionMode;  
	/** Integer or Float */
	private int operationalMode;
	/** to check if the calculator state is error */
	private boolean errorState; 
	/** to set the error message */
	private String errorMessage = null;

	/**
	 * To initialize all the variables  
	 */
	public CalculatorModel() {
		operation = null;
		operand1 = null;
		operand2 = null;
		state = OPERAND_1;
		precisionMode = FP_PRECISION_00;
		operationalMode = FLOAT;
		errorState = false;	
	}
	
	/**
	 * set methods for setting the operand1
	 * @param op1 - the first operand to set 
	 */
	public void setOperand1(String op1) {
		 operand1 = op1;
		 state = OPERAND_1;
	}
	
	/**
	 * set methods for setting the operand2
	 * @param op2 - the second operand to set 
	 */
	public void setOperand2(String op2) {
		 operand2 = op2;
		 state = RESULT;
	}
	
	/**
	 * set methods for setting the arithmetic operation
	 * @param operation - the arithmetic operation
	 */
	public void setOperation(String operation) {
		 this.operation = operation;
		 state = OPERAND_2;
	}
	
	/**
	 * set methods for setting the operational mode(Integer or Float)
	 * @param mode - the operational mode
	 */
	public void setOperationMode(final int mode) {
		 this.operationalMode = mode;
	}
	
	/**
	 * To return Integer mode if the calculator is integer
	 * @return operationalMode - integer mode 
	 */
	public boolean integerMode() {
		return operationalMode == INTEGER;
	}
	
	/** 
	 * set method to set the floating-point precision.
	 * @param precision - the three types precision
	 */
	public void setPrecision(final int precision) {
		 this.precisionMode = precision;
	}
	
	/** 
	 * get method to return the floating-point precision.
	 * @return precisionMode - the floating-point precision 
	 */
	public int getPrecision() {
		 return precisionMode;
	}
	
	/**
	 * get method to return the result from the operation in formatted form
	 * based on the current precision.
	 * @return result - the result after calculation
	 */
	public String getResult() {
		String result = calculate();

		/** set the different operation depends on result */
		if (result != null)
			operand1 = result;
		else
			clear();
		
		return result;
	}
	
	/**
	 * set method for the error state field of the class.
	 * @param error - the error state
	 */
	public void setError(boolean error) {
		/** set the situation of error */
		if (operand1 == null || operand2 == null || operation.isEmpty()) {
			error = true;
		} else if (Double.isInfinite(OPERAND_1) || Double.isInfinite(OPERAND_2)
				|| Double.isNaN(OPERAND_1) || Double.isNaN(OPERAND_2)) {
			error = true;
		}  
		this.errorState = error;
	}
	
	/**
	 * get method for the error state field of the class.
	 * @return errorState - error state of calculation
	 */
	public boolean getError() {
		return errorState;
	}
	
	/**
	 * To return the state of calculator
	 * @return state - the calculate state
	 */
	public int getState() {
		return state;
	}
	
	/**
	 * The method is responsible for calculates the result based on the current
	 * operands, arithmetic operation and operational mode (Integer or Float).
	 * 
	 * @return integerCalculation() or floatCalculation() - the calculated result in formatted. 
	 */
	public String calculate() {
		if (operationalMode == INTEGER)
			return integerCalculation();
		else
			return floatCalculation();	 
	}
	
	/**
	 * To perform the calculation in the integer mode 
	 * @return the integer number after calculated and null
	 */
	public String integerCalculation() {
		 int op1 = 0;
		 int op2 = 0;
		 
		 /** To handle the situation that user clicked "Int" */
		 try {
			op1 = Integer.valueOf(operand1);
		    op2 = Integer.valueOf(operand2);
			 		 
		 } catch(NumberFormatException e) {
			 errorState = true;
			 return null;
		 }
		 
		 /** Result not defined situation */
		 if (op1 == 0 && op2 == 0 && operation == "/") {
			 errorState = false;
			 clear();
			 errorMessage = "Result is undefined";
			 return null;
		 }
		 
		 /** Cannot divide by zero situation */
		 if (op2 == 0 && operation == "/") {
			 errorState = false;
			 clear();
			 errorMessage = "Cannot divide by zero";
			 return null;
		 }
		 
		 state = RESULT;
		 
		 /** to calculate based on different arithmetic operation */
		 switch(operation) {
		 case "*":
			 return String.format("%d", op1 * op2);
		 case "/":
			 return String.format("%d", op1 / op2);
		 case "+":
			 return String.format("%d", op1 + op2);
		 case "-":
			 return String.format("%d", op1 - op2);
		 }
		 
		 return null;
	}
 
	/**
	 * To perform the calculation in the float mode 
	 * @return the floating point number after calculated and null
	 */
	public String floatCalculation() {
		double op1 = 0.0;
		double op2 = 0.0;
		
		/** To handle the situation that user choose float mode */
		try {
			op1 = Double.valueOf(operand1);
			op2 = Double.valueOf(operand2);
		} catch(NumberFormatException e) {
			errorState = true;
			return null;
		}
		
		/** Result not defined situation */
        if (op1 == 0 && op2 == 0 && operation == "/") {
   	    	errorState = true;
   	    	clear();
   	    	errorMessage = "Result is undefined";
   	    	return null;
   	    }
		 
		/** Cannot divide by zero situation */
		if (op2 == 0 && operation == "/") {
			errorState = true;
			clear();
			errorMessage = "Cannot divide by zero";
		    return null;
		}
		 
		state = RESULT;
		
		/** single precision of floating point number */
		if (precisionMode == FP_PRECISION_0) {
			switch(operation) {
			case "*":
				 return String.format("%.1f", op1 * op2);
			case "/":
				 return String.format("%.1f", op1 / op2);
		    case "+":
				 return String.format("%.1f", op1 + op2);
			case "-":
				 return String.format("%.1f", op1 - op2);
			 }
		}
		
		/** double precision of floating point number */
		if (precisionMode == FP_PRECISION_00) {
			switch(operation) {
			case "*":
				 return String.format("%.2f", op1 * op2);
			case "/":
				 return String.format("%.2f", op1 / op2);
		    case "+":
				 return String.format("%.2f", op1 + op2);
			case "-":
				 return String.format("%.2f", op1 - op2);
			 }
		}
		
		/** scientific notation of floating point number */
		if (precisionMode == FP_PRECISION_SCI) {
			switch(operation) {
			case "*":
				 return String.format("%E", op1 * op2);
			case "/":
				 return String.format("%E", op1 / op2);
		    case "+":
				 return String.format("%E", op1 + op2);
			case "-":
				 return String.format("%E", op1 - op2);
			 }
		}
		
		return null;
	}
	
	/**
	 * To reset all the values in the textField 
	 */
	public void clear() {
		state = OPERAND_1;
		errorState = false;
		operation = null;
		operand1 = null;
		operand2 = null;
	}
	
    /**
     * To get the error message to display
     * @return errorMessage - the error message
     */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	/**
	 * To set error message be null
	 */
	public void clearErrorMessage() {
		errorMessage = null;
	}
}

/* Exercise generator for ViLLE online learning platform.
 * Robot and Fruit stacks task generator.
 *
 * Distributed under MIT licence.
 * MIT License

Copyright (c) [2018] [Juhani Vähä-Mäkilä (juhani@fmail.co.uk)]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

Except as contained in this notice, the name(s) of the above copyright holders shall not be used in advertising or otherwise to promote the sale, use or other dealings in this Software without prior written authorization. 
 */

package com.example.villeprojekti;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * Robot and Fruit stacks task generator.
 * @author Juhani Vähä-Mäkilä, 2018. Licensed under MIT license.
 * @version 1.0.1
 */
public class FruitstackGenerator {
	private static Random rnd=new Random();
	/**All stacks of fruits including the correct stack.*/
	private Vector<Vector<Fruit>> fruitStacks;
	/**The correct answer stack.*/
	private Vector<Fruit> correctStack;
	/**True==get command is active. False otherwise. */
	private boolean get;
	/** Get can be only used once. True== get can be used. False otherwise. */
	private boolean canUseGet;
	/**Command string to show user to make a guess from.*/
	private StringBuilder command;
	/**User inputed command line for DIY tasks.	 */
	private Vector<Fruit> answerStack;
	private List<String> answerCommand;
	
	/**
	 * Construct a new randomized multiple choice assignment.
	 * @param numOf Number of stacks wanted.
	 */
	public FruitstackGenerator(int numOf) {
		command=new StringBuilder();
		makeMultipleChoiceTask(rnd.nextInt(7)+2, numOf);
	}
	/**
	 * Construct a new randomized DIY assignment.
	 * @param get True, if get command is activated. False otherwise.
	 */
	public FruitstackGenerator(boolean get){
		this.get=get;
		canUseGet=true;
		command=new StringBuilder();
		makeDiyTask(rnd.nextInt(7)+2);
	}
	/**
	 * Returns the specified fruit's string to picture.
	 * @param i Index of vector of stacks.
	 * @param j Index of fruit in a stack.
	 * @return String to picture of the fruit requested.
	 */
	public String getPicture(int i,int j){
		return 	fruitStacks.get(i).get(j).getPicture();
	}
	/**
	 * Returns the size of the fruit stack.
	 * @param correct True returns the size of the correct answer stack. False returns the size of the answer stack.
	 * @return The size of a fruit stack.
	 */
	public int getSize(boolean correct) {
		if(correct) {
			return correctStack.size();
		}else {
			return answerStack.size();
		}
	}
	/**
	 * Returns the string representing the command the robot used.
	 * @return The string representing the command the robot used.
	 */
	public String getCommand() {
		return command.toString();
	}

	/**
	 * Adds user inputed answer.
	 * @param choice Which fruit to add. 0=Banana, 1= Apple, 2=Lemon.
	 * @return True if command succeeds. False otherwise.
	 */
	public boolean addAnswer(int choice) {
		if(answerCommand.size()<correctStack.size()) {
			switch(choice) {
			case(0):
				//new banana
				answerCommand.add("PUT Banana ");
				answerStack.add(new Banana());
				break;
			case(1):
				//new apple
				answerCommand.add("PUT Apple ");
				answerStack.add(new Apple());
				break;
			case(2):
				//new lemon
				answerCommand.add("PUT Lemon ");
				answerStack.add(new Lemon());
				break;
			default:
				return false;
			}
			return true;
		}
		return false;
		
	}
	/**
	* Removes the last element from user supplied
	* answer after using the GET command.
	* Does nothing if get-command is not active.
	* @return True if get-command succeeds. False otherwise.
	*/
	public boolean usedGet(){
			if(canUseGet && (get && answerCommand.size()>0)){
					answerCommand.remove(answerCommand.size()-1);
					answerStack.remove((answerStack.size()-1));
					//answerStack.trimToSize();
					canUseGet=false;
					return true;
			}
		return false;
	}
	/**
	 * Used when starting over.
	 * Only previously inputed answers are cleared.
	 * Previously generated correct answer is kept.
	 */
	public void startOver() {
			answerCommand.clear();
			answerStack.clear();
	}
	
	//*****************************
	//Correct answer checking methods*
	//*****************************
	
	/**
	 * Checks if answer is correct, Multiple choice task version.
	 * @param answer User inputed answer.
	 * @return True/False
	 */
	public boolean isRightAnswer(int answer) {
		return correctStack.equals(fruitStacks.get(answer));
	}
	/**
	 * Checks if answer is correct, DIY task version.
	 * Works with DIY + get version.
	 * @return True/False
	 */
	public boolean isRightAnswer() {
			StringBuilder tempAnswerCommand=new StringBuilder();
			for(String item : answerCommand){
					tempAnswerCommand.append(item);
				}
		return command.toString().equals(tempAnswerCommand.toString());
	}
	
	//**********************
	//Task generating methods*
	//**********************
	
	/**
	 * Generates a DIY task.
	 * @param stackSize Defines how many fruits there are per stack.
	 */
	private void makeDiyTask(int stackSize) {
		answerCommand=new Vector<String>();
		fruitStacks=new Vector<Vector<Fruit>>(1,0);
		fruitStacks.add(new Vector<Fruit>(correctStack=makeStack(stackSize, true)));
		fruitStacks.add(answerStack=new Vector<Fruit>());
		
	}
/**
 * Generates a multiple choice task.
 * Creates the defined number of stacks including the correct answer stack.
 * @param stackSize Fruits per stack.
 * @param numOf Total number of stacks.
 */
	private void makeMultipleChoiceTask(int stackSize, int numOf) {
		fruitStacks=new Vector<Vector<Fruit>>(numOf,0);
		correctStack=makeStack(stackSize, true);

		int i=1;
		while(i<numOf){
			Vector<Fruit> temp=makeStack(stackSize, false);
			if(temp.equals(correctStack) || isSame(temp)) {
				continue;
			}
			else {
				fruitStacks.add(temp);
				i++;
			}
		}
		fruitStacks.add(new Vector<Fruit>(correctStack));
		Collections.shuffle(fruitStacks);
	}
	
	/**
	 * Checks if generated fruit stack is the same as any previous one.
	 * @param temp Stack to test.
	 * @return True/False
	 */
	private boolean isSame(Vector<Fruit> temp) {
		for(int i=0;i<fruitStacks.size();i++) {
			if(temp.equals(fruitStacks.get(i))) {
				return true;
			}
		}
		return false;
}
	/**
	 * Generates a random stack of fruits.
	 * Also generates the "command line" for the multiple choice task.
	 * @param stackSize Size of stack.
	 * @param correct True: make the correct answer stack, False: otherwise
	 * @return A stack of fruits.
	 */
	private Vector<Fruit> makeStack(int stackSize, boolean correct){
		Vector<Fruit> temp=new Vector<Fruit>(stackSize,0);
		for(int i=0;i<stackSize;i++) {

			switch(rnd.nextInt(3)) {
			case(0):
				temp.add(new Banana());
				if(correct) {
					command.append("PUT Banana ");
					}
				break;
			case(1):
				temp.add(new Apple());
				if(correct) {
					command.append("PUT Apple ");
				}
				break;
			case(2):
				temp.add(new Lemon());
				if(correct) {
					command.append("PUT Lemon ");
					}
				break;
			default:
				break;
			}
		}
		return temp;
	}
}

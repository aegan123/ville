/*Tehtävägeneraattori Ville-ympäristöön.
 * Robotti ja hedelmät. Hedelmien kokoaminen pinoon.
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
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * Robot and Fruit stacks task generator.
 * @author Juhani Vähä-Mäkilä, 2018. Licensed under MIT license.
 * @version 1.0
 */
public class FruitstackGenerator {
	private static Random rnd=new Random();
	/**All stacks of fruits including the correct stack.*/
	private Vector<Vector<Fruit>> fruitStacks;
	/**The correct answer stack.*/
	private Vector<Fruit> correctStack;
	/**True==get command is active. False otherwise. */
	private boolean get;
	/**Command string to show user to make a guess from.*/
	private StringBuilder command;
	/**User inputed command line for DIY task.	 */
	private StringBuilder answerCommand;
	/**User inputed command line for DIY+get task.	 */
	private List<String> answerGetCommand;
	/**List of all known fruits*/
//	private final static Map<Integer,String> fruitList=createMap();
	
	/**
	 * Construct a new randomized multiple choice assignment.
	 * @param numOf Number of stacks  wanted.
	 */
	public FruitstackGenerator(int numOf) {
		command=new StringBuilder();
		makeMultipleChoiceTask(rnd.nextInt(7)+2, numOf);
	}
	/**
	 * Construct a new randomized DIY assignment.
	 * @param get True, if get command  is activated. False otherwise.
	 */
	public FruitstackGenerator(boolean get){
		this.get=get;
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
		return 	fruitStacks.get(i).get(j).getPic();
	}
	/**
	 * Returns the size of the fruit stack.
	 * @return The size of a fruit stack.
	 */
	public int getSize() {
		return correctStack.size();
	}
	/**
	 * Returns the string representing the command the robot used.
	 * @return The string representing the command the robot used.
	 */
	public String getCommand() {
		return command.toString();
	}

	/**
	 * Adds an answer string from DIY task.
	 * @param answer String to add.
	 */
	public void addAnswer(String answer) {
		if(get){
			answerGetCommand.add(answer);
		}
		else{
			answerCommand.append(answer);
		}
	}
	/**
	* Removes the last element from user supplied
	* answer after using the GET command.
	* Does nothing if get-command is not active.
	*/
	public void usedGet(){
		if(get){
			answerGetCommand.remove(answerGetCommand.size()-1);
		}
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
		if(get){
			answerCommand=new StringBuilder();
			for(int i=0;i<answerGetCommand.size();i++){
				answerCommand.append(answerGetCommand.get(i));
			}
		}
		return command.toString().equals(answerCommand.toString());
	}
	
	//**********************
	//Task generating methods*
	//**********************
	
	/**
	 * Generates a DIY task.
	 * @param stackSize Defines how many fruits there are per stack.
	 */
	private void makeDiyTask(int stackSize) {
		correctStack=makeStack(stackSize, true);
		if(get){
			answerGetCommand=new Vector<String>();
		}
		else{
			answerCommand=new StringBuilder();
		}
		fruitStacks=new Vector<Vector<Fruit>>(1,0);
		fruitStacks.add(new Vector<Fruit>(correctStack));
		
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
			if(temp.equals(correctStack)) {
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
	 * Generates a random stack of fruits.
	 * Also generates the "command line" for the multiple choice task.
	 * @param stackSize Size of stack.
	 * @param correct True: make the correct answer stack, False: otherwise
	 * @return A stack of fruits.
	 */
	private Vector<Fruit> makeStack(int stackSize, boolean correct){
		Vector<Fruit> temp=new Vector<Fruit>(stackSize,0);
		for(int i=0;i<stackSize;i++) {
			//int j=rnd.nextInt(fruitList.size());
			
			//Random num to choose fruit with.
			int j=rnd.nextInt(3);
			switch(j) {
			case(0):
				temp.add(new Banana());
				if(correct) {
					command.append("PUT Banana");
					command.append(" ");
					}
				break;
			case(1):
				temp.add(new Apple());
				if( correct) {
					command.append("PUT Apple");
					command.append(" ");
				}
				break;
			case(2):
				temp.add(new Lemon());
				if(correct) {
					command.append("PUT Lemon");
					command.append(" ");
					}
				break;
			default:
				break;
			}
		}
		return temp;
	}
	/**
	 * HashMap to map numbers to fruits.
	 * Not used.
	 * @return
	 */
	private static HashMap<Integer, String> createMap() {
		HashMap<Integer,String> temp=new HashMap<Integer,String>();
		temp.put(0, "Banana");
		temp.put(1, "Apple");
		temp.put(2, "Lemon");
		return temp;
	}
}

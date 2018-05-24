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
 */

package com.example.villeprojekti;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

/**
 * Robot and Fruit stacks task generator.
 * @author Juhani Vähä-Mäkilä, 2018. Licensed under MIT license.
 * @version 0.3
 */
public class FruitstackGenerator {
	private static Random rnd=new Random();
	/**All stacks of fruits including correct stack.*/
	private Vector<Vector<Fruit>> fruitStacks;
	/**The correct answer stack.*/
	private Vector<Fruit> correctStack;
	/**True==multiple choice task, False==DIY task.*/
	private boolean typeOfTask;
	/**True==get command is active. False otherwise. */
	private boolean get;
	/**Command string to show user to make a guess from.*/
	private StringBuilder command;
	/**List of all known fruits*/
	private final static Map<Integer,String> fruitList=createMap();
	
	/**
	 * Construct a new randomized assignment. Type is defined by parameters.
	 * @param b True==multiple choice task, False==DIY task.
	 * @param numOf Number of stacks  wanted.
	 * @param get True, if get command  is activated. False otherwise.
	 */
	public FruitstackGenerator(boolean b, int numOf, boolean get) {
		typeOfTask=b;
		this.get=get;
		command=new StringBuilder();
		if(b) {
			makeMultipleChoiceTask(rnd.nextInt(7)+2, numOf);
		}
		else {
			makeDiyTask(rnd.nextInt(7)+2);
		}
	}
	/**
	 * Returns the specified fruits string to picture.
	 * @param i Index of vector of stacks.
	 * @param j Index of fruit in a stack.
	 * @return String to picture of the fruit requested.
	 */
	public String getPic(int i,int j){
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
	public void resetCommand() {
		command=new StringBuilder();
	}
	/**
	 * Returns the type of the task.
	 * @return True==multiple choice task, False==DIY task.
	 */
	public boolean getTypeOfTask() {
		return typeOfTask;
	}
	/**
	 * Generates a DIY task.
	 * @param stackSize Defines how many fruits there are per stack.
	 */
	private void makeDiyTask(int stackSize) {
		// TODO Auto-generated method stub
		correctStack=makeStack(stackSize, true);
		
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

		//for(int i=1;i<numOf;i++) 
		int i=1;
		while(i<numOf){
			Vector<Fruit> temp=makeStack(stackSize, false);
			if(temp.equals(correctStack)) {
				continue;
			}
			else {
				fruitStacks.add(temp);
				i++;
			//Collections.shuffle(fruitStacks.get(i-1));
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
			int j=rnd.nextInt(fruitList.size());
			switch(j) {
			case(0):
				temp.add(new Banana());
				if(typeOfTask && correct) {
					if(get) {
						command.append("GET "+fruitList.get(j));
					}
					else {
					command.append("PUT "+fruitList.get(j));
					}
					command.append(" ");
				}
				break;
			case(1):
				temp.add(new Apple());
				if(typeOfTask && correct) {
					command.append("PUT "+fruitList.get(j));
					command.append(" ");
				}
				break;
			case(2):
				temp.add(new Lemon());
				if(typeOfTask && correct) {
					if(get) {
						command.append("GET "+fruitList.get(j));
					}
					else {
					command.append("PUT "+fruitList.get(j));
					}
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
	 * Checks if answer is correct.
	 * @param answer User inputed answer.
	 * @return True/False
	 */
	public boolean isRightAnswer(Collection<Fruit> answer) {
		return correctStack.equals(answer);
		
	}
	/**
	 * 
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

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
import java.util.Random;
import java.util.Vector;

public class FruitstackGenerator {
	private static Random rnd=new Random();
	private Collection<? extends Collection<Fruit>> fruitStacks;
	private Collection<? extends Fruit> correctStack;
	/**True==monivalintatehtävä, False==kasaa itse tehtävä. */
	private boolean typeOfTask;
	/**
	 * 
	 * @param b True==monivalintatehtävä, False==kasaa itse tehtävä.
	 */
	public FruitstackGenerator(boolean b) {
		typeOfTask=b;
		if(b) {
			makeMultipleChoiceTask(rnd.nextInt(7)+2, 4);
		}
		else {
			makeDiyTask(rnd.nextInt(7)+2);
		}
	}
	public boolean getTypeOfTask() {
		return typeOfTask;
	}
	private void makeDiyTask(int i) {
		// TODO Auto-generated method stub
		
	}
/**
 * 
 * @param size Stack size.
 * @param numOf How many stacks.
 */
	private void makeMultipleChoiceTask(int size, int numOf) {
		// TODO Auto-generated method stub
		fruitStacks=new Vector<Vector<Fruit>>(numOf,0);
		correctStack=new Vector<Fruit>(size,0);
	}
}

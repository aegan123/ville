/*Tehtävän visualisointiosa Ville-ympäristöön.
 * Uudenvuodenaatto/joulukuusi binäärilukuharjoitus
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

import java.io.File;
import java.util.Vector;

/**
 * Class representing the visual aspect of the assignment.
 * @author Juhani Vähä-Mäkilä
 * @version 0.1
 */
public class JoulukuusiVisual {
	/**The actual problem. */
	private JoulukuusiGenerator assigment;
	/**Background picture (=the Christmas tree). */
	private static final File bgPic=new File("../resources/christmas_tree.svg");
	/**A matrix of lamps based on the actual assignment. */
	private Vector<Vector<Lamppu>> lamps;
	/**
	 * Construct all the parts needed to
	 * actually visualize the assignment on a web page.
	 */
	public JoulukuusiVisual() {
		assigment=new JoulukuusiGenerator();
		lamps=new Vector<Vector<Lamppu>>();
		setLamps();
	}
	/**
	 * Sets lamps to match the binarymatrix of the assignment.
	 */
	private void setLamps() {
		Vector<Vector<Boolean>> temp2=this.assigment.getBinarymatrix();
		for(int i=0;i<4;i++) {
			Vector<Lamppu> temp=new Vector<Lamppu>(4,0);
			for(int j=0;j<4;j++) {
				temp.add(new Lamppu(temp2.get(i).get(j).booleanValue()));
			}
			lamps.add(temp);
		}
		
	}
	/**
	 * Checks if the answer is correct.
	 * @param answer User inputed answer.
	 * @return True/False
	 */
	public boolean isRightAnswer(String answer) {
		return assigment.isRightAnswer(answer);
	}
	/**
	 * Returns the File object of the background picture.
	 * @return File object representing the background picture.
	 */
	public static File getBgPic() {
		return bgPic;
	}

	/**
	 * Represents the lamps needed for visualizing.
	 * @author Juhani Vähä-Mäkilä
	 * @version 0.1
	 */
	class Lamppu {
		//Picture source: https://commons.wikimedia.org/w/index.php?title=File:Simpleicons_Interface_light-bulb-outline.svg&oldid=189680700
		//Licensed under CC BY 3.0 (https://creativecommons.org/licenses/by/3.0/deed.en)
		
		/**Whether or not the lamp should be on.*/
		private boolean on;
		/**Path to picture file.*/
		private File kuva;
		/**
		 * 
		 * @param status True if the light should be on. False if not.
		 */
		public Lamppu(boolean status) {
			on=status;
			if (on) {
				//tähän polku päällä olevaan lamppuun
				//setKuva(new File("../resources/light_on.jpeg"));
				kuva=new File("../resources/light_on.svg");
			}else {
				//tähän polku pois päältä olevaan lamppuun
				//setKuva(new File("../resources/light_off.jpeg"));
				kuva=new File("../resources/light_off.svg");
				
			}
		}
		/**
		 * Returns the File object representing the lamp.
		 * @return File object representing the lamp.
		 */
		public File getKuva() {
			return kuva;
		}
		}

}

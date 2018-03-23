/*Tehtävägeneraattori Ville-ympäristöön.
 * Uudenvuodenaatto/joulukuusi binäärilukuharjoitus
 * Distributed under MIT licence.
 * MIT License

Copyright (c) [2018] [Juhani Vähä-Mäkilä]

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

public class Lamppu {
private boolean on;
private File kuva;

public Lamppu(boolean status) {
	on=status;
	if (on) {
		//tähän polku päällä olevaan lamppuun
		setKuva(new File("../resources/light_on.jpeg"));
	}else {
		//tähän polku pois päältä olevaan lamppuun
		setKuva(new File("../resources/light_off.jpeg"));
		
	}
}

public File getKuva() {
	return kuva;
}

private void setKuva(File kuva) {
	this.kuva = kuva;
}
}

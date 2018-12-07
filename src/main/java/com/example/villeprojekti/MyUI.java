/* Demo proto UI for ViLLE excersices
 * Christmas tree/binary number excerisice and Robot and Fruit stacks task excerisice.
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

import javax.servlet.annotation.WebServlet;

import org.vaadin.hezamu.canvas.Canvas;
import org.vaadin.hezamu.canvas.Canvas.CanvasImageLoadListener;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Juhani Vähä-Mäkilä, 2018. Licensed under MIT license.
 * 
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	/** "html5" canvas to draw images to.  */
	private Canvas canvas;
	private Canvas canvas2;
	/** The generated assignment. */
	private FruitstackGenerator stack;
	private ChristmasTreeGenerator kuusi;
	/**Base url to load pictures from. Actual filename will be appended to it later. 
	 * Change this to correct value or don't use it. */
	//private final static String baseurl="https://liquid-moon.pw/utu/";
	private final static String baseurl="http://nightbreaker.fea.st/utu/";
	private final String[]urls= {baseurl+"banana.svg",baseurl+"apple.svg",baseurl+"lemon.svg"};
	/** Layout components used.	 */
	private VerticalLayout mainContent,fruitcontent,content;
	private HorizontalLayout kuusiContent;
	/** Input fields used in binary number exercise. */
	private TextField text1,text2,text3,text4;
	/** y-coordinate of canvas used in fruit stack drawing. */
	private double y=0.0;
	

    /**Foobar yolo! :D	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates the base layout with buttons for different exercises.
	 */
	@Override
    protected void init(VaadinRequest vaadinRequest) {
	mainContent=new VerticalLayout();
	HorizontalLayout buttons=new HorizontalLayout();
	Button joulukuusi=new Button("Joulukuusi");
	Button fruit=new Button("Hedelmäpino, monivalinta");
	Button fruit2=new Button("Hedelmäpino, DIY");
	Button fruit3=new Button("Hedelmäpino, DIY + GET");
	Button empty=new Button("Tyhjennä");
	empty.addClickListener( e -> {
	       reset();
	     });
	joulukuusi.addClickListener( e -> {
       initKuusi();
     });
	fruit.addClickListener( e -> {
		initMultiFruit();
     });
	fruit2.addClickListener( e -> {
		initDIYFruit();
     });
	fruit3.addClickListener( e -> {
		initDIYFruitGet();
     });
	buttons.addComponents(joulukuusi,fruit,fruit2,fruit3,empty);
	content=new VerticalLayout();
	HorizontalLayout hlayout=new HorizontalLayout();
	hlayout.addComponent(new Label("Näillä napeilla valitaa haluttu tehtävä tai resetoidaan koko näkymä."));
	mainContent.addComponents(hlayout,buttons,content);
	setContent(mainContent);
    }

	/**
	 * Resets components in main view.
	 * For demo purposes only.
	 */
	private void reset() {
		try {
			content.removeAllComponents();
			canvas.clear();
			}
		catch(NullPointerException e) {
				
			}
	}
	/**
	 * Redraws answer stack to canvas after using get-command.
	 * For demo purposes only.
	 */
	private void reDrawAnswer() {
		//kuva on 400x400px
		canvas2.clear();
		canvas2.loadImages(urls);
		canvas2.addImageLoadListener(new CanvasImageLoadListener() {
			
			@Override
			public void imagesLoaded() {
				double x=0.0;//, 
				y=50.0*stack.getSize(true)-50.0;
				for(int j=0;j<stack.getSize(false);j++) {
					canvas2.drawImage2(baseurl+stack.getPicture(1, j), x, y,50.0,50.0);
					y-=50;
					
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					}
				
			}
		});
			
		
	}
	/**
	 * Clears the answer canvas to restart the assignment.
	 * Does not generate a new assignment.
	 */
	private void startOver() {
		y=50.0*stack.getSize(true)-50.0;
		canvas2.clear();
		canvas2.loadImages(urls);
		stack.startOver();
		
	}
	/**
	 * Initiates DIY fruitstack + GET visualization.
	 * For demo purposes only.
	 */
	private void initDIYFruitGet() {
		reset();
		stack=new FruitstackGenerator(true);
		y=50.0*stack.getSize(true)-50.0;
		fruitcontent=new VerticalLayout();
		canvas=new Canvas();
		canvas2=new Canvas();
		canvas.setHeight(50*stack.getSize(true)+10, Unit.PIXELS);
		canvas.setWidth(70, Unit.PIXELS);
		canvas2.setHeight(50*stack.getSize(true)+10, Unit.PIXELS);
		canvas2.setWidth(500, Unit.PIXELS);
		HorizontalLayout hlayout=new HorizontalLayout();
		HorizontalLayout hlayout2=new HorizontalLayout();
		HorizontalLayout hlayout3=new HorizontalLayout();
        canvas.loadImages(urls);
		canvas2.loadImages(urls);
		canvas.addImageLoadListener(new CanvasImageLoadListener() {
			
			@Override
			public void imagesLoaded() {
				//kuva on 400x400px
				double x=0.0, y=0.0;
					for(int j=stack.getSize(true);j>0;j--) {
					canvas.drawImage2(baseurl+stack.getPicture(0, j-1), x, y,50.0,50.0);
					y+=50;
					
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					}
				}
				
		});
		Button button5=new Button("PUT Apple");
		button5.addClickListener( e -> {
			//if(stack.addAnswer("PUT Apple ")){
			if(stack.addAnswer(1)){
			//canvas2.drawImage2(baseurl+"apple.svg", 0.0, y,50.0,50.0);
				canvas2.drawImage2(urls[1], 0.0, y,50.0,50.0);
			y-=50.0;
			}
			else {
				 fruitcontent.addComponent(new Label("Can't add more!"));
			}
        });
		Button button6=new Button("PUT Banana");
		button6.addClickListener( e -> {
			//if(stack.addAnswer("PUT Banana ")) {
			if(stack.addAnswer(0)) {
			//canvas2.drawImage2(baseurl+"banana.svg", 0.0, y,50.0,50.0);
				canvas2.drawImage2(urls[0], 0.0, y,50.0,50.0);
			 y-=50.0;
		}
		else {
			 fruitcontent.addComponent(new Label("Can't add more!"));
		}
        });
		Button button7=new Button("PUT Lemon");
		button7.addClickListener( e -> {
			//if(stack.addAnswer("PUT Lemon ")) {
			if(stack.addAnswer(2)) {
			 //canvas2.drawImage2(baseurl+"lemon.svg", 0.0, y,50.0,50.0);
				canvas2.drawImage2(urls[2], 0.0, y,50.0,50.0);
			 y-=50.0;
		}
		else {
			 fruitcontent.addComponent(new Label("Can't add more!"));
		}
       });
		Button button8=new Button("Tarkista");
		button8.addClickListener( e -> {
           fruitcontent.addComponent(new Label(Boolean.toString(stack.isRightAnswer())));
        });
		Button button9=new Button("Use GET");
		button9.addClickListener( e -> {
			if(!stack.usedGet()) {
				fruitcontent.addComponent(new Label("Can't remove more!"));
			} else {
				reDrawAnswer();
			y+=50.0;
			}
        });
		Button button10=new Button("Start over");
		button10.addClickListener( e -> {
           startOver();
        });
		hlayout.addComponents(button5,button6,button7,button8);
		hlayout2.addComponents(button9,button10);
		hlayout3.addComponents(canvas,canvas2);
		fruitcontent.addComponents(hlayout3,hlayout,hlayout2);
		content.addComponent(fruitcontent);
		
	}

	/**
	 * Initiates DIY fruitstack visualization.
	 * For demo purposes only.
	 */
	private void initDIYFruit() {
		reset();
		stack=new FruitstackGenerator(false);
		y=50.0*stack.getSize(true)-50.0;
		fruitcontent=new VerticalLayout();
		canvas=new Canvas();
		canvas2=new Canvas();
		canvas.setHeight(50*stack.getSize(true)+10, Unit.PIXELS);
		canvas.setWidth(70, Unit.PIXELS);
		canvas2.setHeight(50*stack.getSize(true)+10, Unit.PIXELS);
		canvas2.setWidth(500, Unit.PIXELS);
		HorizontalLayout hlayout=new HorizontalLayout();
		HorizontalLayout hlayout2=new HorizontalLayout();
		canvas.loadImages(urls);
		canvas2.loadImages(urls);
		canvas.addImageLoadListener(new CanvasImageLoadListener() {
			
			@Override
			public void imagesLoaded() {
				//kuva on 400x400px
				double x=0.0, y=0.0;
					for(int j=stack.getSize(true);j>0;j--) {
					canvas.drawImage2(baseurl+stack.getPicture(0, j-1), x, y,50.0,50.0);
					y+=50;
					
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					}
				}
				
		});
		Button button1=new Button("PUT Apple");
		button1.addClickListener( e -> {
          //if( stack.addAnswer("PUT Apple ")) {
			if(stack.addAnswer(1)) {
				canvas2.drawImage2(urls[1], 50.0, y,50.0,50.0);
				y-=50.0;
          }
			else {
				 fruitcontent.addComponent(new Label("Can't add more!"));
			}
        });
		Button button2=new Button("PUT Banana");
		button2.addClickListener( e -> {
			 //if(stack.addAnswer("PUT Banana ")) {
			if(stack.addAnswer(0)) {
			 canvas2.drawImage2(urls[0], 50.0, y,50.0,50.0);
			 y-=50.0;
			 }
				else {
					 fruitcontent.addComponent(new Label("Can't add more!"));
				}
        });
		Button button3=new Button("PUT Lemon");
		button3.addClickListener( e -> {
			 //if(stack.addAnswer("PUT Lemon ")) {
			if(stack.addAnswer(2)) {
			 canvas2.drawImage2(urls[2], 50.0, y,50.0,50.0);
			 y-=50.0;
			 }
				else {
					 fruitcontent.addComponent(new Label("Can't add more!"));
				}
        });
		Button button4=new Button("Tarkista");
		button4.addClickListener( e -> {
           fruitcontent.addComponent(new Label(Boolean.toString(stack.isRightAnswer())));
        });
		Button button5=new Button("Start over");
		button5.addClickListener( e -> {
           startOver();
        });
		hlayout.addComponents(button1,button2,button3,button4,button5);
		hlayout2.addComponents(canvas,canvas2);
		fruitcontent.addComponents(hlayout2,hlayout);
		content.addComponent(fruitcontent);
	}

	/**
	 * Initiates multiple choice fruitstack visualization.
	 * For demo purposes only.
	 * @throws InterruptedException 
	 */
	private void initMultiFruit(){
		reset();
		stack=new FruitstackGenerator(4);
		fruitcontent=new VerticalLayout();
		canvas=new Canvas();
		canvas.setHeight(50*stack.getSize(true)+10, Unit.PIXELS);
		canvas.setWidth(500, Unit.PIXELS);
		HorizontalLayout hlayout=new HorizontalLayout();
        canvas.loadImages(urls);
		canvas.addImageLoadListener(new CanvasImageLoadListener() {
			
			@Override
			public void imagesLoaded() {
				//kuva on 400x400px
				double x=0.0, y=0.0;
				for(int i=0;i<4;i++) {
					for(int j=stack.getSize(true)-1;j>=0;j--) {
						canvas.drawImage2(baseurl+stack.getPicture(i, j), x, y,50.0,50.0);
						y+=50;
						
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					y=0;
					x+=50;
				}
				}
		});
		Button button1=new Button("1");
		button1.addClickListener( e -> {
           fruitcontent.addComponent(new Label("1: "+Boolean.toString(stack.isRightAnswer(0))));
        });
		Button button2=new Button("2");
		button2.addClickListener( e -> {
           fruitcontent.addComponent(new Label("2: "+Boolean.toString(stack.isRightAnswer(1))));
        });
		Button button3=new Button("3");
		button3.addClickListener( e -> {
           fruitcontent.addComponent(new Label("3: "+Boolean.toString(stack.isRightAnswer(2))));
        });
		Button button4=new Button("4");
		button4.addClickListener( e -> {
           fruitcontent.addComponent(new Label("4: "+Boolean.toString(stack.isRightAnswer(3))));
        });
		hlayout.addComponents(button1,button2,button3,button4);
		fruitcontent.addComponents(new Label(stack.getCommand()),canvas,hlayout);
		content.addComponent(fruitcontent);
		
	}
	/**
	 * Initaliazes "christmas tree" ui.
	 * For demo purposes only.
	 */
	private void initKuusi() {
		reset();
		kuusi=new ChristmasTreeGenerator();
		kuusiContent=new HorizontalLayout();
		canvas=new Canvas();
		canvas.setHeight(64*4+10, Unit.PIXELS);
		String[]urls= {baseurl+"light_off.svg",baseurl+"light_on.svg"};
		canvas.loadImages(urls);
		canvas.addImageLoadListener(new CanvasImageLoadListener() {
			
			@Override
			public void imagesLoaded() {
				//kuva on 64x64px
				double x=0.0, y=0.0;
				for(int i=0;i<4;i++) {
					for(int j=0;j<4;j++) {
						if(kuusi.isOn(i, j)) {
							canvas.drawImage1(urls[1], x, y);
							x+=65;
						}
						else {
							canvas.drawImage1(urls[0], x,y);
							x+=65;
						}
					}
					x=0.0;
					y+=65;
				}
				}
		});
		VerticalLayout layout=new VerticalLayout();
		  Button button=new Button("Check answer.");
	        button.addClickListener( e -> {
	            layout.addComponent(new Label(Boolean.toString(kuusi.isRightAnswer(text1.getValue()+text2.getValue()+text3.getValue()+text4.getValue()))));
	        });
	    	text1=new TextField();
	    	text2=new TextField();
	    	text3=new TextField();
	    	text4=new TextField();
		layout.addComponents(text1,text2,text3,text4,button);
		kuusiContent.addComponents(canvas,layout);
		content.addComponent(kuusiContent);
	}
/*
	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {

		
		private static final long serialVersionUID = 1L;
    }
*/
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = false)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}

/*Demo proto UI Ville tehtäviin..
 * Uudenvuodenaatto/joulukuusi binäärilukuharjoitus ja Robotti+hedelmäpino
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
	/** The generated assignment. */
	private FruitstackGenerator stack;
	private JoulukuusiGenerator kuusi;
	/**Base url to load pictures from. Actual filename will be appended to it later. */
	private final static String baseurl="https://liquid-moon.pw/utu/";
	private VerticalLayout mainContent,fruitcontent,content;
	private HorizontalLayout kuusiContent;
	private TextField text1,text2,text3,text4;
	private double x,y=0.0;
	

    /**Foobar yolo! :D	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void init(VaadinRequest vaadinRequest) {
	mainContent=new VerticalLayout();
	HorizontalLayout buttons=new HorizontalLayout();
	Button joulukuusi=new Button("Joulukuusi");
	Button fruit=new Button("Hedelmäpino, monivalinta");
	Button fruit2=new Button("Hedelmäpino, DIY");
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
	buttons.addComponents(joulukuusi,fruit,fruit2,empty);
	content=new VerticalLayout();
	mainContent.addComponents(buttons,content);
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
	 * Initiates DIY fruitstack visualization.
	 * For demo purposes only.
	 */
	private void initDIYFruit() {
		reset();
		stack=new FruitstackGenerator(false,4,false);
		y=50.0*stack.getSize()-50.0;
		fruitcontent=new VerticalLayout();
		canvas=new Canvas();
		canvas.setHeight(50*stack.getSize()+10, Unit.PIXELS);
		canvas.setWidth(500, Unit.PIXELS);
		HorizontalLayout hlayout=new HorizontalLayout();
        String[]urls= {baseurl+"banana.svg",baseurl+"apple.svg",baseurl+"lemon.svg"};
		canvas.loadImages(urls);
		canvas.addImageLoadListener(new CanvasImageLoadListener() {
			
			@Override
			public void imagesLoaded() {
				//kuva on 400x400px
				double x=0.0, y=0.0;
					for(int j=stack.getSize();j>0;j--) {
					canvas.drawImage2(baseurl+stack.getPic(0, j-1), x, y,50.0,50.0);
					y+=50;
					}
				}
				
		});
		Button button1=new Button("PUT Apple");
		button1.addClickListener( e -> {
           stack.addAnswer("PUT Apple ");
           canvas.drawImage2(baseurl+"apple.svg", 50.0, y,50.0,50.0);
           y-=50.0;
        });
		Button button2=new Button("PUT Banana");
		button2.addClickListener( e -> {
			 stack.addAnswer("PUT Banana ");
			 canvas.drawImage2(baseurl+"banana.svg", 50.0, y,50.0,50.0);
			 y-=50.0;
        });
		Button button3=new Button("PUT Lemon");
		button3.addClickListener( e -> {
			 stack.addAnswer("PUT Lemon ");
			 canvas.drawImage2(baseurl+"lemon.svg", 50.0, y,50.0,50.0);
			 y-=50.0;
        });
		Button button4=new Button("Tarkista");
		button4.addClickListener( e -> {
           fruitcontent.addComponent(new Label(Boolean.toString(stack.isRightAnswer())));
        });
		hlayout.addComponents(button1,button2,button3,button4);
		fruitcontent.addComponents(canvas,hlayout);
		content.addComponent(fruitcontent);
	}
	/**
	 * Initiates multiple choice fruitstack visualization.
	 * For demo purposes only.
	 */
	private void initMultiFruit() {
		reset();
		stack=new FruitstackGenerator(true,4,false);
		fruitcontent=new VerticalLayout();
		canvas=new Canvas();
		canvas.setHeight(50*stack.getSize()+10, Unit.PIXELS);
		canvas.setWidth(500, Unit.PIXELS);
		HorizontalLayout hlayout=new HorizontalLayout();
        String[]urls= {baseurl+"banana.svg",baseurl+"apple.svg",baseurl+"lemon.svg"};
		canvas.loadImages(urls);
		canvas.addImageLoadListener(new CanvasImageLoadListener() {
			
			@Override
			public void imagesLoaded() {
				//kuva on 400x400px
				double x=0.0, y=0.0;
				for(int i=0;i<4;i++) {
					for(int j=stack.getSize();j>0;j--) {
					canvas.drawImage2(baseurl+stack.getPic(i, j-1), x, y,50.0,50.0);
					y+=50;
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
	 * Actually draws the images to canvas after they have been preloaded by the browser.
	 * Called by CanvasImageLoadListener.
	 */
	private void drawImg() {
		//kuva on 400x400px
		double x=0.0, y=0.0;
		for(int i=0;i<4;i++) {
			for(int j=stack.getSize();j>0;j--) {
			canvas.drawImage2(baseurl+stack.getPic(i, j-1), x, y,50.0,50.0);
			y+=50;
			}
			y=0;
			x+=50;
		}
		
	}
	/**
	 * Initaliazes "christmas tree" ui.
	 * For demo purposes only.
	 */
	private void initKuusi() {
		reset();
		kuusi=new JoulukuusiGenerator();
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

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {

		/** */
		private static final long serialVersionUID = 1L;
    }
}

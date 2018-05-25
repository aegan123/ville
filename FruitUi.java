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
public class FruitUi extends UI {
	/** "html5" canvas to draw images to.  */
	private Canvas canvas;
	/** The generated assignment. */
	private FruitstackGenerator stack;
	/**Base url to load pictures from. Actual filename will be appended to it later. */
	private final String baseurl="https://liquid-moon.pw/utu/";
	

    /**Foobar yolo! :D	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void init(VaadinRequest vaadinRequest) {
		stack=new FruitstackGenerator(true,4,false);
		if(stack.getTypeOfTask())	initFruit();
		else initDYIFruit();
    }
	
	private void initDYIFruit() {
		// TODO Auto-generated method stub
		
	}

	private void initFruit() {
		VerticalLayout content=new VerticalLayout();
		//content.addComponent(canvas = new Canvas());
		canvas=new Canvas();
		canvas.setHeight(50*stack.getSize()+10, Unit.PIXELS);
		canvas.setWidth(500, Unit.PIXELS);
		HorizontalLayout hlayout=new HorizontalLayout();
        String[]urls= {baseurl+"banana.svg",baseurl+"apple.svg",baseurl+"lemon.svg"};
		canvas.loadImages(urls);
		canvas.addImageLoadListener(new CanvasImageLoadListener() {
			
			@Override
			public void imagesLoaded() {
				drawImg();
				}
		});
		/*
		VerticalLayout layout=new VerticalLayout();
		  Button button=new Button("Check answer.");
	        button.addClickListener( e -> {
	            layout.addComponent(new Label(Boolean.toString(kuusi.isRightAnswer(text1.getValue()+text2.getValue()+text3.getValue()+text4.getValue()))));
	        });
		layout.addComponents(text1,text2,text3,text4,button);
		*/
		Button button1=new Button("1");
		button1.addClickListener( e -> {
           content.addComponent(new Label("1: "+Boolean.toString(stack.isRightAnswer(0))));
        });
		Button button2=new Button("2");
		button2.addClickListener( e -> {
           content.addComponent(new Label("2: "+Boolean.toString(stack.isRightAnswer(1))));
        });
		Button button3=new Button("3");
		button3.addClickListener( e -> {
           content.addComponent(new Label("3: "+Boolean.toString(stack.isRightAnswer(2))));
        });
		Button button4=new Button("4");
		button4.addClickListener( e -> {
           content.addComponent(new Label("4: "+Boolean.toString(stack.isRightAnswer(3))));
        });
		hlayout.addComponents(button1,button2,button3,button4);
		content.addComponents(new Label(stack.getCommand()),canvas,hlayout);
		setContent(content);
		
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

	@WebServlet(urlPatterns = "/*", name = "FruitStack", asyncSupported = true)
    @VaadinServletConfiguration(ui = FruitUi.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {

		/** */
		private static final long serialVersionUID = 1L;
    }
}

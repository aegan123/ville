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
public class FruitUi extends UI {
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
	

    /**Foobar yolo! :D	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void init(VaadinRequest vaadinRequest) {
	mainContent=new VerticalLayout();
	HorizontalLayout buttons=new HorizontalLayout();
	Button joulukuusi=new Button("Joulukuusi");
	Button fruit=new Button("Hedelmäpino");
	Button empty=new Button("Tyhjennä");
	empty.addClickListener( e -> {
	       reset();
	     });
	joulukuusi.addClickListener( e -> {
       initKuusi();
     });
	fruit.addClickListener( e -> {
        initFruit();
     });
	buttons.addComponents(joulukuusi,fruit,empty);
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
 * Initializes fruit stack ui.
 * For demo purposes only.
 */
	private void initFruit() {
		reset();
		stack=new FruitstackGenerator(true,4,false);
		if(stack.getTypeOfTask())	initMultiFruit();
		else initDIYFruit();
	}
	private void initDIYFruit() {
		// TODO Auto-generated method stub
		
	}

	private void initMultiFruit() {
		fruitcontent=new VerticalLayout();
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
		//setContent(fruitcontent);
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

	@WebServlet(urlPatterns = "/foobar", name = "FruitStack", asyncSupported = true)
    @VaadinServletConfiguration(ui = FruitUi.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {

		/**Foobar yolo!! */
		private static final long serialVersionUID = 1L;
    }
}

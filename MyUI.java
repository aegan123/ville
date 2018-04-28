package com.example.villeprojekti;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.annotation.WebServlet;

import org.vaadin.hezamu.canvas.Canvas;
import org.vaadin.hezamu.canvas.Canvas.CanvasImageLoadListener;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	private Canvas canvas;

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void init(VaadinRequest vaadinRequest) {
		JoulukuusiVisual kuusi=new JoulukuusiVisual();
        final VerticalLayout layout = new VerticalLayout();
        
        /*final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener( e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));
        });
        
        //layout.addComponents(name, button);
        JoulukuusiGenerator kuusi=new JoulukuusiGenerator();
        Label label=new Label(kuusi.getBinarymatrix().toString());
        TextField text=new TextField();
        text.setCaption("Your answer:");
        Button button=new Button("Check answer.");
        button.addClickListener( e -> {
            layout.addComponent(new Label(Boolean.toString(kuusi.isRightAnswer(text.getValue()))));
        });
        layout.addComponents(label,text,button);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
        */
        setContent(layout);
        layout.addComponent(canvas = new Canvas());
        //canvas.fillRect(10, 10, 200, 200);
        String[] urls=genUrls(kuusi);
		canvas.loadImages(urls);
		canvas.addImageLoadListener(new CanvasImageLoadListener() {
			
			@Override
			public void imagesLoaded() {
				for(int i=0;i<urls.length;i++) {
					canvas.drawImage1(urls[i], i+1, i+1);
				}
				
			}

			
		});
    }
	
    private String[] genUrls(JoulukuusiVisual kuusi) {
		ArrayList<String> temp=new ArrayList<String>();
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				temp.add(kuusi.getLamppu(i, j).toString().substring(2));
			}
		}
		return temp.toArray(new String[temp.size()]);
		}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
    }
}

package com.example.villeprojekti;

import java.io.File;

import javax.servlet.annotation.WebServlet;

import org.vaadin.hezamu.canvas.Canvas;
import org.vaadin.hezamu.canvas.Canvas.CanvasImageLoadListener;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
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
	private Canvas canvas;
	private JoulukuusiGenerator kuusi;
	private String baseurl="https://liquid-moon.pw/utu/";
	private  String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
	private TextField text1=new TextField();
	private TextField text2=new TextField();
	private TextField text3=new TextField();
	private TextField text4=new TextField();
	

    /**Foobar yolo! :D	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void init(VaadinRequest vaadinRequest) {
		initKuusi();
		//initFruit();

		/*
        content.setExpandRatio(layout1, 1);
        final TextField name = new TextField();
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
        
        
        

        //foobar(layout,kuusi);
        //Image image2 = new Image("", resource2);

        //layout.addComponents(image,image2);
*/
    }
	private void initFruit() {
		// TODO Auto-generated method stub
		
	}
	private void initKuusi() {
		kuusi=new JoulukuusiGenerator();
		VerticalLayout content=new VerticalLayout();
		/*
		String[]urls={basepath + "/WEB-INF/images/light_on.svg",basepath + "/WEB-INF/images/light_off.svg",basepath + "/WEB-INF/images/christmas_tree.svg"};
		//String[]urls={"localhost:8081/WEB-INF/images/light_on.svg","localhost:8081/WEB-INF/images/light_off.svg","localhost:8081/WEB-INF/images/christmas_tree.svg"};
		setContent(content);
        HorizontalLayout layout1 = new HorizontalLayout();
        HorizontalLayout layout2 = new HorizontalLayout();
        HorizontalLayout layout3 = new HorizontalLayout();
        HorizontalLayout layout4 = new HorizontalLayout();
        HorizontalLayout layout5 = new HorizontalLayout();
        layout1.setSizeFull();
        layout2.setSizeFull();
        layout3.setSizeFull();
        layout4.setSizeFull();
        //Table table=new Table("Foobar");
        //table.addItem(new Label[] {new Label("0"),new Label("0000")}, null);
        //table.setSizeFull();
        //content.addComponents(new Label("0: 0000"),new Label("1: 0001"),new Label("2: 0010"),new Label("3: 0011"),new Label("4: 0100"),new Label("5: 0101"),new Label("6: 0110"),new Label("7: 0111"),new Label("8: 1000"),new Label("9: 1001"));
        //content.addComponent(table);
        layout5.setCaption("<table><tr><th>Decimal</th><th>Binary</th></tr><tr><td>0</td><td>0000</td></tr></table>");
        layout5.setCaptionAsHtml(true);
        content.addComponent(layout5);
        //content.addComponent(new Label(basepath));
       addImg(layout1,0,kuusi);
        layout1.addComponent(text1);
        content.addComponent(layout1);
        addImg(layout2,1, kuusi);
        layout2.addComponent(text2);
        content.addComponent(layout2);
        addImg(layout3,2, kuusi);
        layout3.addComponent(text3);
        content.addComponent(layout3);
        addImg(layout4,3, kuusi);
        layout4.addComponent(text4);
        content.addComponent(layout4);
        
        Button button=new Button("Check answer.");
        button.addClickListener( e -> {
            content.addComponent(new Label(Boolean.toString(kuusi.isRightAnswer(text1.getValue()+text2.getValue()+text3.getValue()+text4.getValue()))));
        });
        content.addComponent(button);
        */
        
		//content.addComponent(canvas = new Canvas());
		canvas=new Canvas();
		canvas.setHeight(64*4+10, Unit.PIXELS);
		HorizontalLayout hlayout=new HorizontalLayout();

        //canvas.fillRect(10, 10, 200, 200);
        //String[] urls=genUrls(kuusi);
        //String[]urls= {basepath+"/WEB-INF/images/light_off.svg",basepath+"/WEB-INF/images/light_on.svg"};
		
		//Picture source: https://commons.wikimedia.org/w/index.php?title=File:Simpleicons_Interface_light-bulb-outline.svg&oldid=189680700
		//Licensed under CC BY 3.0 (https://creativecommons.org/licenses/by/3.0/deed.en)
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
				//canvas.drawImage1(urls[0], 0.0, 0.0);
				//canvas.drawImage1(urls[1], 65.0, 0.0);
				}

			
		});
		VerticalLayout layout=new VerticalLayout();
		  Button button=new Button("Check answer.");
	        button.addClickListener( e -> {
	            layout.addComponent(new Label(Boolean.toString(kuusi.isRightAnswer(text1.getValue()+text2.getValue()+text3.getValue()+text4.getValue()))));
	        });
		layout.addComponents(text1,text2,text3,text4,button);
		hlayout.addComponents(canvas,layout);
		//content.addComponents(hlayout,layout);
		setContent(hlayout);
		
	}
	private void addImg(HorizontalLayout layout, int i, JoulukuusiGenerator kuusi) {
        FileResource resource=null;
        Image image=null;
        	for(int j=0;j<4;j++) {
        			if(kuusi.isOn(i, j)) {
        					resource = new FileResource(new File(basepath + "/WEB-INF/images/light_on.svg"));
        			}
        			else {
        					resource = new FileResource(new File(basepath + "/WEB-INF/images/light_off.svg"));
        			}
         image = new Image("", resource);
         image.setHeight(50, Unit.PERCENTAGE);
         image.setWidth(50, Unit.PERCENTAGE);
         layout.addComponent(image);
        }
	}
    /*   private String[] genUrls(JoulukuusiGenerator kuusi) {
		ArrayList<String> temp=new ArrayList<String>();
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				temp.add(kuusi.getLamppu(i, j).toString().substring(2));
			}
		}
		return temp.toArray(new String[temp.size()]);
		}
*/
	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {

		/** */
		private static final long serialVersionUID = 1L;
    }
}

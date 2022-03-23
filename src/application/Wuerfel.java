package application;

import java.util.Random;
import java.util.Vector;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Wuerfel extends Group {
	private int zahl;
	
	private Rectangle rect = new Rectangle();
	private double site = 100;
	private Color background = Color.WHITE;
	private Color outline = Color.BLACK; 
	private Color dots = Color.BLACK;
    private RotateTransition rt = new RotateTransition(); 

	public void init() {
		rect.setHeight(site);
		rect.setWidth(site);
		rect.setFill(background);
		rect.setStrokeWidth(site / 60);
		rect.setStroke(outline);
		rect.setArcHeight(site / 10);
		rect.setArcWidth(site / 10);

		Circle circle = new Circle();
		circle.setRadius(site / 10);
		circle.setCenterX(rect.getWidth() / 2);
		circle.setCenterY(rect.getHeight() / 2);
		circle.setFill(dots);
		

	      
	      //Setting the duration for the transition 
		  rt = new RotateTransition(Duration.millis(400), this); 
	      rt.setByAngle(180);
	      rt.setCycleCount(1);
	      rt.setAutoReverse(true);
		
		this.getChildren().addAll(rect, circle);
		this.setOnMouseClicked(e->{	
			this.wuerfeln();
		});
		
		
	}

	public Wuerfel() {
		init();
	}

	public Wuerfel(double site, Color background, Color outline, Color dots) {
		this.site = site;
		this.background = background;
		this.outline = outline;
		if(dots != null) {
			this.dots = dots;
		} 
		init();
	}

	public int wuerfeln() {
		Random r = new Random();
		int zahl = r.nextInt(6)+1;
		this.zahl = zahl;
		rt.setOnFinished(e->{
			show(zahl);
		});
		rt.play();
		
		return zahl;
	}
	
	public void show(int zahl) {
		this.getChildren().clear();
		this.getChildren().add(rect);

		int offset = 14;
		Vector<Circle> punkte = new Vector<>();

		if (zahl > 1 && zahl <= 5) {
			// Punkt oben links
			Circle ol = new Circle();
			ol.setRadius(site / 10);
			ol.setCenterX(ol.getRadius() + offset);
			ol.setCenterY(ol.getRadius() + offset);
			ol.setFill(dots);

			// Punkt unten rechts
			Circle ur = new Circle();
			ur.setRadius(site / 10);
			ur.setCenterX(rect.getWidth() - ur.getRadius() - offset);
			ur.setCenterY(rect.getHeight() - ur.getRadius() - offset);
			ur.setFill(dots);
			punkte.add(ol);
			punkte.add(ur);
		}

		if (zahl % 2 == 1) {
			// Punkt Mitte
			Circle mid = new Circle();
			mid.setRadius(site / 10);
			mid.setCenterX(rect.getWidth() / 2);
			mid.setCenterY(rect.getHeight() / 2);
			mid.setFill(dots);
			punkte.add(mid);
		}

		if (zahl == 4 || zahl == 5) {
			// Punkt oben rechts
			Circle or = new Circle();
			or.setRadius(site / 10);
			or.setCenterX(rect.getWidth() - or.getRadius() - offset);
			or.setCenterY(or.getRadius() + offset);
			or.setFill(dots);
			punkte.add(or);

			// Punkt unten links
			Circle ul = new Circle();
			ul.setRadius(site / 10);
			ul.setCenterX(ul.getRadius() + offset);
			ul.setCenterY(rect.getHeight() - ul.getRadius() - offset);
			ul.setFill(dots);
			punkte.add(ul);

		}
		if (zahl == 6) {
			Circle ol = new Circle();
			ol.setRadius(site / 10);
			ol.setCenterX(rect.getWidth() / 4);
			ol.setCenterY(rect.getHeight() / 6);
			ol.setFill(dots);
			punkte.add(ol);

			Circle ml = new Circle();
			ml.setRadius(site / 10);
			ml.setCenterX(rect.getWidth() / 4);
			ml.setCenterY(rect.getHeight() / 2);
			ml.setFill(dots);
			punkte.add(ml);

			Circle ul = new Circle();
			ul.setRadius(site / 10);
			ul.setCenterX(rect.getWidth() / 4);
			ul.setCenterY(rect.getHeight() - (rect.getHeight() / 6));
			ul.setFill(dots);
			punkte.add(ul);

			Circle or = new Circle();
			or.setRadius(site / 10);
			or.setCenterX(rect.getWidth() - (rect.getWidth() / 4));
			or.setCenterY(rect.getHeight() / 6);
			or.setFill(dots);
			punkte.add(or);

			Circle mr = new Circle();
			mr.setRadius(site / 10);
			mr.setCenterX(rect.getWidth() - (rect.getWidth() / 4));
			mr.setCenterY(rect.getHeight() / 2);
			mr.setFill(dots);
			punkte.add(mr);

			Circle ur = new Circle();
			ur.setRadius(site / 10);
			ur.setCenterX(rect.getWidth() - (rect.getWidth() / 4));
			ur.setCenterY(rect.getHeight() - (rect.getHeight() / 6));
			ur.setFill(dots);
			punkte.add(ur);
		}
 		
		this.getChildren().addAll(punkte);
	}

	public int getZahl() {
		return zahl;
	}

	public void setZahl(int zahl) {
		this.zahl = zahl;
	}
	
	
	
}

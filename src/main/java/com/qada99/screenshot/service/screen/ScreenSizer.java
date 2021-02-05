package com.qada99.screenshot.service.screen;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ScreenSizer {
	

    
    private Rectangle screen;
    private List<ScreenSetted> listeners = new ArrayList<>();
   
    public ScreenSizer() {
      this.screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    }
    public void addListener(ScreenSetted listener){
    	this.listeners.add(listener);
    	
    }
    public Rectangle getScreen() {
        return this.screen;
    }
    public void setScreen() {
  
    	
//	            try {
//	                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//	            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//	            }
	            JFrame frame = new JFrame();
	            frame.setUndecorated(true);
	            // This works differently under Java 6
	            frame.setBackground(new Color(0, 0, 0, 0));
//	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setLayout(new BorderLayout());
	            frame.add(new ScreenSizerPane());
	            frame.setBounds(getVirtualBounds());
	     	    frame.setVisible(true);
				frame.toFront();
	  
     
    }
    public class ScreenSizerPane extends JPanel {

        private Point mouseAnchor;
        private Point dragPoint;

        public SelectionPane selectionPane;

        public ScreenSizerPane() {
            setOpaque(false);
            setLayout(null);
            selectionPane = new SelectionPane();
            this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            add(selectionPane);
            MouseAdapter adapter = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    mouseAnchor = e.getPoint();
                    dragPoint = null;
                    selectionPane.setLocation(mouseAnchor);
                    selectionPane.setSize(0, 0);
                }
                
                @Override
				public void mouseReleased(MouseEvent e) {
                	  dragPoint = e.getPoint();
                      int width = dragPoint.x - mouseAnchor.x;
                      int height = dragPoint.y - mouseAnchor.y;

                      int x = mouseAnchor.x;
                      int y = mouseAnchor.y;

                      if (width < 0) {
                          x = dragPoint.x;
                          width *= -1;
                      }
                      if (height < 0) {
                          y = dragPoint.y;
                          height *= -1;
                      }
					ScreenSizer.this.screen = new Rectangle(ScreenSizerPane.this.selectionPane.getX(),
							ScreenSizerPane.this.selectionPane.getY(), ScreenSizerPane.this.selectionPane.getWidth(),
							ScreenSizerPane.this.selectionPane.getHeight());
					for (ScreenSetted listener : listeners) {
						listener.screenSetted();
					}

	                  SwingUtilities.getWindowAncestor(ScreenSizerPane.this).dispose();

//	                  Platform.runLater(new Runnable() {
//						@Override
//						public void run() {							
//							// TODO Auto-generated method stub
//							Main.setScreen(new Rectangle(ScreenSizerPane.this.selectionPane.getX(),ScreenSizerPane.this.selectionPane.getY(),ScreenSizerPane.this.selectionPane.getWidth() ,ScreenSizerPane.this.selectionPane.getHeight() ));
//							Main.getpStage().setOpacity(1.0);
//						}
//					});
                  
                }

				@Override
                public void mouseDragged(MouseEvent e) {
                    dragPoint = e.getPoint();
                    int width = dragPoint.x - mouseAnchor.x;
                    int height = dragPoint.y - mouseAnchor.y;

                    int x = mouseAnchor.x;
                    int y = mouseAnchor.y;

                    if (width < 0) {
                        x = dragPoint.x;
                        width *= -1;
                    }
                    if (height < 0) {
                        y = dragPoint.y;
                        height *= -1;
                    }
                    selectionPane.setBounds(x, y, width, height);
                    selectionPane.revalidate();
                    repaint();
						

					
                }
            };
            addMouseListener(adapter);
            addMouseMotionListener(adapter);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g.create();

            Rectangle bounds = new Rectangle(0, 0, getWidth(), getHeight());
            Area area = new Area(bounds);
            area.subtract(new Area(selectionPane.getBounds()));

            g2d.setColor(new Color(192, 192, 192, 64));
            g2d.fill(area);

        }
    }

    public class SelectionPane extends JPanel {

		public SelectionPane() {
            setOpaque(false);






        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            // I've chosen NOT to fill this selection rectangle, so that
            // it now appears as if you're "cutting" away the selection
//            g2d.setColor(new Color(128, 128, 128, 64));
//            g2d.fillRect(0, 0, getWidth(), getHeight());

            float dash1[] = {10.0f};
            BasicStroke dashed =
                    new BasicStroke(3.0f,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER,
                    10.0f, dash1, 0.0f);
            g2d.setColor(Color.BLACK);
            g2d.setStroke(dashed);
            g2d.drawRect(0, 0, getWidth() - 3, getHeight() - 3);
            g2d.dispose();
        }
    }

    public static Rectangle getVirtualBounds() {

        Rectangle bounds = new Rectangle(0, 0, 0, 0);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice lstGDs[] = ge.getScreenDevices();
        for (GraphicsDevice gd : lstGDs) {

            bounds.add(gd.getDefaultConfiguration().getBounds());

        }

        return bounds;

    }
}
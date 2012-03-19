package org.openpixi.pixi.ui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;

import org.openpixi.pixi.physics.Particle2D;

import com.jogamp.opengl.util.Animator;
 
 
public class Sample implements GLEventListener, KeyListener, Runnable {
 
	static GLU glu = new GLU();
	 
    static GLCanvas canvas = new GLCanvas();
 
    static Frame frame = new Frame("Jogl Frame");
 
    static Animator animator = new Animator(canvas);
    
    public void display(GLAutoDrawable gLDrawable) {
    	final GL2 gl = gLDrawable.getGL().getGL2();
    	gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        
        
    	for (int j=0; j < Particle2DPanel.s.particles.size(); j++) {
        	Particle2D par = Particle2DPanel.s.particles.get(j);
            float drawx = (float)(par.x*7)/Particle2DPanel.xmax - 3.5f;
            float drawy = (float)(par.y*5)/Particle2DPanel.ymax - 2.5f;
            
            gl.glLoadIdentity();
            gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
            gl.glTranslatef(drawx, drawy, -10.0f);
        	
        	gl.glBegin(GL2.GL_POLYGON);
        	if (par.charge > 0) {
        		gl.glColor3f(0.0f, 0.0f, 1.0f);
			} else {
				gl.glColor3f(1.0f, 0.0f, 0.0f);
			}

        	for (int i=0; i < 360; i++)
        	{
        		float degInRad = (float) (i*(3.14159/180));
        		gl.glVertex3f((float) Math.cos(degInRad)*((float)par.radius/80f),(float) Math.sin(degInRad)*((float)par.radius/80f),0.0f);
        	}
        	gl.glEnd();
        }
    }
 
    public void displayChanged(GLAutoDrawable gLDrawable, boolean modeChanged, boolean deviceChanged) {
    }
 
    public void init(GLAutoDrawable gLDrawable) {
        GL2 gl = gLDrawable.getGL().getGL2();
        gl.glShadeModel(GLLightingFunc.GL_SMOOTH);
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LEQUAL);
        gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
        ((Component) gLDrawable).addKeyListener(this);
    }
 
    public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height) {
        GL2 gl = gLDrawable.getGL().getGL2();
        if (height <= 0) {
            height = 1;
        }
        float h = (float) width / (float) height;
        gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(50.0f, h, 1.0, 1000.0);
        gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
 
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        	exit();
        }
    }
 
    public void keyReleased(KeyEvent e) {
    }
 
    public void keyTyped(KeyEvent e) {
    }
 
    public void dispose(GLAutoDrawable gLDrawable) {
        // do nothing
    }
    
	public static void exit() {
        animator.stop();
        frame.dispose();
        System.exit(0);
    }
    
    public void run() {
    	canvas.addGLEventListener(this);
        frame.add(canvas);
        frame.setSize(700, 500);
        frame.setUndecorated(true);
        frame.setExtendedState(Frame.NORMAL);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });
        frame.setVisible(true);
        animator.start();
        canvas.requestFocus();
    }
}
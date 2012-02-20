//First I would like to start with very simple class Force.java, so we could see the graphic result.
package org.openpixi.pixi.physics;

public class Force {

	/** Constant gravity in x-direction */
	public double gx;

	/** Constant gravity in y-direction */
	public double gy;

	/** Drag coefficient */
	public double drag;

	/** Electric field in x - direction */
	public double ex;

	/** Electric field in y - direction */
	public double ey;

	/** Magnetic field in z - direction */
	public double bz;
	
	/** New empty force */
	public Force()
	{
		reset();
	}

	public void reset()
	{
		gx = 0;
		gy = 0;
		drag = 0;
		ex = 0;
		ey = 0;
		bz = 0;
	}
	
	//method that sets the gravity in x - direction
	public void setGX(double gx)
	{
		this.gx = gx;
	}
	
	//method that sets the gravity in y - direction
	public void setGY(double gy)
	{
		this.gy = gy;
	}
	
	//method that sets the electrical field in x - direction
	public void setEX(double ex)
	{
		this.ex = ex;
	}
	
	//method that sets the electrical field in y - direction
	public void setEY(double ey)
	{
		this.ey = ey;
	}
	
	//method that sets the magnetic field in z - direction
	public void setBZ(double bz)
	{
		this.bz = bz;
	}
	//getting the force in the x - direction
	public double getForceX(double vx, double vy, Particle2D par)
	{
		return -drag * vx + par.mass * gx + par.charge * ex + par.charge * vy * bz;
	}
	
	//getting the force in the y - direction
	public double getForceY(double vx, double vy, Particle2D par)
	{
		return - drag * vy + par.mass * gy + par.charge * ey - par.charge * vx * bz;
	}
	
}
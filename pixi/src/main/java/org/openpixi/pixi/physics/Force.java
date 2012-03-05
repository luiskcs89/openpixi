/*
 * OpenPixi - Open Particle-In-Cell (PIC) Simulator
 * Copyright (C) 2012  OpenPixi.org
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
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

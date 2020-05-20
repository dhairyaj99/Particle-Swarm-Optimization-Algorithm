package pso;

import java.util.Random;
import java.util.Scanner;

public class psoAlgo {
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		double w;
		double c1;
		double c2;
		
		System.out.println("What is the inertia parameter: ");
		w = s.nextDouble();
		System.out.println("What is the c1 parameter: ");
		c1 = s.nextDouble();
		c2 = c1;
		
		particleSwarm(w,c1,c2,500,30);

	}
	
	public static void particleSwarm(double ws, double c1s, double c2s, int maxIterations, int maxDims) {
		

		int maxIteration = maxIterations;
		int maxDim = maxDims;

		Random rand = new Random();
		double globalBest = 50000;
		double[][] particles;
		double[][] particlesPos = new double[30][maxDim];	
		double[][] particlesVel = new double[30][maxDim];
		double [] particleBestPos = new double[30];

		double w, c1, c2, r1, r2;

		w = ws;
		c1 = c1s;
		c2 = c2s;
		r1 = rand.nextDouble();
		r2 = rand.nextDouble();

		//initilize particles randomly
		//initilize velocities to zero
		particles = createPop(maxDim);
		particlesPos = popPos(maxDim, particlesPos);
		particlesVel = initVel(maxDim, particlesVel);

		for(int i=0; i<maxIteration; i++) {
			double bestFit = 5000;
			
			double bestNeighPos = 50;
			Boolean feasible;
			for (int j=0; j<particles.length; j++) {
				double tempBest = 50;
				//Evaluate fitness
				double fitness = fitnessFunction(maxDim, particles, particlesPos, j);
				particles[j][1] = fitness;

				//update personal best position
				for(int k=0;k<particlesPos.length;k++) {
					if(particlesPos[j][k]<5.12 && particlesPos[j][k]>-5.12)
						feasible = true;
					else
						feasible=false;
					if(Math.abs(particlesPos[j][k])<Math.abs(tempBest) && feasible == true) {
						tempBest = particlesPos[j][k];
					}
				}
				particleBestPos[j] = tempBest;

				//update Neighbourhood best position
				for(int k=0;k<particleBestPos.length;k++) {
					if(particlesPos[j][k]<5.12 && particlesPos[j][k]>-5.12)
						feasible = true;
					else
						feasible=false;

					if(Math.abs(particleBestPos[k])<Math.abs(bestNeighPos) && feasible == true) {
						bestNeighPos = particleBestPos[k];
					}
				}
			}

			//update neighbourhood best particles
			for(int j=0;j<particles.length;j++) {
				if (particles[j][1] < bestFit) {
					bestFit = particles[j][1];
				}
			}
			System.out.println(bestFit);

			//Calculate new Velocity and update particle position
			for (int j=0; j<30; j++) {
				for(int k=0;k<30;k++) {
					particlesVel[j][k] = updateVel(particlesVel[j][k],bestNeighPos, w, c1, c2, r1, r2, particleBestPos[j], particlesPos[j][k]);
					particlesPos[j][k] = updatePos(particlesPos[j][k],particlesVel[j][k]);
				}
			}

			//update global best
			if(bestFit<globalBest)
				globalBest = bestFit;
		}
		System.out.println();
		System.out.println(globalBest);
	}

	public static double[][] createPop (int maxDim) {
		double[][] particles = new double[30][2];

		for (int i=0; i<30; i++) {
			particles[i][0] = 0;
			particles[i][1] = 5000;
		}

		return particles;
	}

	public static double[][] popPos (int maxDim, double[][] particlesPos) {
		Random rand = new Random();

		for (int i=0; i<30; i++) {
			for (int j=0; j<maxDim; j++) {
				double pos = -5.12 + (5.12 - (-5.12)) * rand.nextDouble();
				particlesPos[i][j] = pos;
			}
		}

		return particlesPos;
	}

	public static double[][] initVel (int maxDim, double[][] particlesVel) {

		for (int i=0; i<30; i++) {
			for (int j=0; j<maxDim; j++) {
				particlesVel[i][j] = 0;
			}
		}

		return particlesVel;
	}

	public static double updateVel (double curVel, double neighBest, double w, double c1, double c2, double r1, double r2, double y,
			double curPos) {
		double newVel = 0;
		double wv = 0;
		double c1r1 = 0;
		double c2r2 = 0;

		double vmax = 5.12;
		double vmin = -5.12;

		wv = w*curVel;
		c1r1 = c1*r1*(y-curPos);
		c2r2 = c2*r2*(neighBest - curPos);

		newVel = wv + c1r1 + c2r2;

		if(newVel > vmax)
			newVel = vmax;
		if(newVel<vmin)
			newVel = vmin;

		return newVel;
	}

	public static double updatePos (double prevPos, double curVel) {
		double newPos = 0;

		newPos = prevPos + curVel;

		return newPos;
	}


	public static double fitnessFunction (int maxDim, double [][] particles, double [][] particlesPos, int j) {

		double fitness = 0;
		Random rand = new Random();
		double p1;

		for(int i=0; i<maxDim; i++) {
			p1 = Math.pow(particlesPos[j][i], 2);
			p1 -= 10*(Math.cos(particlesPos[j][i]*(2*Math.PI)));
			fitness += p1;
		}

		fitness += 10*maxDim;

		return fitness;
	}

}
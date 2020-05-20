package pso;
import java.util.Random;

public class randSearch {

		public static void main(String[] args) {

			int maxIteration = 500;
			int maxDim = 30;
			int swarmSize = 30;
			
			Random rand = new Random();
			double globalBest = 50000;
			double[][] particles = new double[maxIteration*swarmSize][maxDim];
			double [] particleFit = new double[maxIteration*swarmSize];
			
			particles = popPos(maxDim,particles,particles.length);
			
			for(int i=0;i<particles.length;i++) {
				for(int j=0;j<particles[i].length;j++) {
					particleFit[i] = fitnessFunction(maxDim, particles, i);
				}
				System.out.println(particleFit[i]);
			}
			
			for(int i=0;i<particleFit.length;i++) {
				if(particleFit[i]<globalBest)
					globalBest = particleFit[i];
			}
			System.out.println();
			System.out.println(globalBest);
			
		}

		public static double[][] popPos (int maxDim, double[][] particlesPos, int size) {
			Random rand = new Random();

			for (int i=0; i<size; i++) {
				for (int j=0; j<maxDim; j++) {
					double pos = -5.12 + (5.12 - (-5.12)) * rand.nextDouble();
					particlesPos[i][j] = pos;
				}
			}

			return particlesPos;
		}

		public static double fitnessFunction (int maxDim, double [][] particlesPos, int j) {

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

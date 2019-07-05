package mma.legacy.interval;

/**
 * Clase para el ejemplo de trabajo con Legacy
 * Controla operaciones sobre intervalos que pudeen ser abiertos o cerrados
 * @author Agustin
 */
public class Interval {
	private double minimum;
	private double maximum;
	private Opening opening;

	/**
	 * Constructor de la clase
	 * @param minimum
	 * @param maximum
	 * @param opening
	 */
	public Interval(double minimum, double maximum, Opening opening) {
		this.minimum = minimum;
		this.maximum = maximum;
		this.opening = opening;
	}

	/**
	 * Este método calcula el punto medio del intervalo
	 * @return double
	 */
	public double midPoint() {		
		return (maximum + minimum) / 2;	
	}

	/**
	 * Este método mira si un número está dentro de un determinado intervalo
	 * @param value
	 * @return boolean
	 */
	public boolean includes(double value) {
		switch (opening) {
			case BOTH_OPENED:			
				return minimum < value && value < maximum;
			case LEFT_OPENED: 			
				return minimum < value && value <= maximum;
			case RIGHT_OPENED:			
				return minimum <= value && value < maximum;
			case UNOPENED:
				return minimum <= value && value <= maximum;
			default:			
				assert false;
				return false;
		}
	}

	/**
	 * Este método comprueba si un intervalo está dentro de otro intervalo
	 * @param interval
	 * @return boolean
	 */
	public boolean includes(Interval interval) {
		boolean minimumIncluded = this.includes(interval.minimum);
		boolean maximumIncluded = this.includes(interval.maximum);
		switch (opening) {
			case BOTH_OPENED:
				switch (interval.opening) {
					case BOTH_OPENED:
						return (minimumIncluded || minimum == interval.minimum)
								&& (maximumIncluded || maximum == interval.maximum);
					case LEFT_OPENED:
						return (minimumIncluded || minimum == interval.minimum)
								&& (maximumIncluded);
					case RIGHT_OPENED:
						return (minimumIncluded)
								&& (maximumIncluded || maximum == interval.maximum);
					case UNOPENED:
						return (minimumIncluded) && (maximumIncluded);
					default:
						assert false;
						return false;
				}
			case LEFT_OPENED:
				switch (interval.opening) {
					case BOTH_OPENED:
						return (minimumIncluded || minimum == interval.minimum)
								&& (maximumIncluded || maximum == interval.maximum);
					case LEFT_OPENED:
						return (minimumIncluded || minimum == interval.minimum) 
								&& (maximumIncluded || maximum == interval.maximum);
					case RIGHT_OPENED:
						return (minimumIncluded)
								&& (maximumIncluded || maximum == interval.maximum);
					case UNOPENED:
						return (minimumIncluded)
								&& (maximumIncluded || maximum == interval.maximum);
					default:
						assert false;
						return false;
				}
			case RIGHT_OPENED:
				switch (interval.opening) {
				case BOTH_OPENED:
					return (minimumIncluded || minimum == interval.minimum)
							&& (maximumIncluded || maximum == interval.maximum);
				case LEFT_OPENED:
					return (minimumIncluded || minimum == interval.minimum)
							&& (maximumIncluded);
				case RIGHT_OPENED:
					return (minimumIncluded || minimum == interval.minimum)
						&& (maximumIncluded || maximum == interval.maximum);
				case UNOPENED:
					return (minimumIncluded || minimum == interval.minimum)
						&& (maximumIncluded);
				default:
					assert false;
					return false;
			}
			case UNOPENED:
				switch (interval.opening) {
					case BOTH_OPENED: 				
						return (minimumIncluded || minimum == interval.minimum) 						
								&& (maximumIncluded || maximum == interval.maximum);
					case LEFT_OPENED: 				
						return (minimumIncluded || minimum == interval.minimum)
								&& (maximumIncluded || maximum == interval.maximum);
					case RIGHT_OPENED: 				
						return (minimumIncluded || minimum == interval.minimum) 
								&& (maximumIncluded || maximum == interval.maximum);
					case UNOPENED:
						return (minimumIncluded || minimum == interval.minimum) 
								&& (maximumIncluded || maximum == interval.maximum);
					default:
						assert false; 				
						return false; 
					}
			default:
				assert false;
				return false;
		}
	}

	/**
	 * Este método comprueba si un intervalo intersecciona con otro intervalo
	 * @param interval
	 * @return boolean
	 */
	public boolean intersectsWith(Interval interval) {
		if (minimum == interval.maximum) {
			switch (opening) {
				case BOTH_OPENED:
				case LEFT_OPENED:
				return false;
				case RIGHT_OPENED:
				case UNOPENED:
					return interval.opening == Opening.LEFT_OPENED ||
						interval.opening == Opening.UNOPENED;
				default:
					assert false;
					return false;	
			}
		}
		if (maximum == interval.minimum) {
			switch (opening) {
				case BOTH_OPENED:
				case RIGHT_OPENED:
					return false;
				case LEFT_OPENED:
				case UNOPENED:
					return interval.opening == Opening.RIGHT_OPENED ||
						interval.opening == Opening.UNOPENED;	
				default:
					assert false;																																									
					return false;
			}
		}
		return this.includes(interval.minimum)
				|| this.includes(interval.maximum);
	}
	
	@Override
	public String toString() {
		return "";
	}
	
	@Override
	public boolean equals(Object object) {
		return false;
	}
	
	@Override
	public int hashCode() {
		return 0;
	}
}

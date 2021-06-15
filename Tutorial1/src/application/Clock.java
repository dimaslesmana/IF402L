package application;

public class Clock {
	private int hours;
	private int minutes;
	private int seconds;

	public Clock() {
		this.hours = this.minutes = this.seconds;
	}

	public Clock(int hours, int minutes, int seconds) {
		super();
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}

	public void addSecond() {
		this.seconds++;
		seconds %= 60;

		if (seconds == 0) {
			addMinute();
		}
	}

	public void addMinute() {
		this.minutes++;
		minutes %= 60;

		if (minutes == 0) {
			addHour();
		}
	}

	public void addHour() {
		this.hours++;
	}

	public String getTime() {
		StringBuilder builder = new  StringBuilder();

		builder.append(this.hours);
		builder.append(":");
		builder.append(this.minutes);
		builder.append(":");
		builder.append(this.seconds);

		return builder.toString();
	}

}

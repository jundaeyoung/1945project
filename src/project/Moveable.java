package project;

public interface Moveable {
	public abstract void left();

	public abstract void right();
	
	public abstract void up();

	public abstract void down();
	
	// 필요하면 재정의해서 사용 (대각선 이동)
	
	default public void upLeft() {};
	
	default public void upRight() {};
	
	default public void downLeft() {};
	
	default public void downRight() {};
}

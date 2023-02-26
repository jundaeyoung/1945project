package project_list;

public interface Moveable {
	default public void left() {};
	default public void left(int speed) {};

	default public void right() {};
	default public void right(int speed) {};
	
	default public void up() {};
	default public void up(int speed) {};

	default public void down() {};
	default public void down(int speed) {};
	
	// 필요하면 재정의해서 사용 (대각선 이동)
	
	default public void downLeft() {};
	
	default public void downRight() {};
}

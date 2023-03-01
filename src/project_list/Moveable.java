package project_list;

public interface Moveable {
	default public void left() {};

	default public void right() {};
	
	default public void up() {};

	public void down();
}

/*
 * @author Roshy Moongathottathil & Sebastian Pilz 
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
// Durch Tasten druck werden die Figuren bewegt
public class Tasten implements KeyListener {

	//wenn die Taste w gedr�ckt wird, wird moveupsp auf true gesetzt
	//und wenn die Taste s gedr�ckt wird, wird movedownsp auf true gesetzt
	public void keyPressed(KeyEvent a) {
		if (a.getKeyCode() == KeyEvent.VK_W) {
			Var.moveupsp1 = true;
		} else if (a.getKeyCode() == KeyEvent.VK_S) {
			Var.movedownsp1 = true;
		}

		if (a.getKeyCode() == KeyEvent.VK_UP) {
			Var.moveupsp2 = true;
		} else if (a.getKeyCode() == KeyEvent.VK_DOWN) {
			Var.movedownsp2 = true;
		}
	}
	//wenn die Tasten nicht mehr gedr�ckt werden, werden movedownsp und moveupsp auf false gesetzt
	public void keyReleased(KeyEvent a) {
		if (a.getKeyCode() == KeyEvent.VK_W) {
			Var.moveupsp1 = false;
		} else if (a.getKeyCode() == KeyEvent.VK_S) {
			Var.movedownsp1 = false;
		}

		if (a.getKeyCode() == KeyEvent.VK_UP) {
			Var.moveupsp2 = false;
		} else if (a.getKeyCode() == KeyEvent.VK_DOWN) {
			Var.movedownsp2 = false;
		}

	}

	public void keyTyped(KeyEvent a) {

	}
}

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class DButton extends JButton {
	
	public DButton( String icon , Color background , Color foreground ){
		
		this.setIcon( new ImageIcon(icon));
		this.setFocusable(false);
		// this.setBorderPainted( false );
		this.setBackground( background );
		this.setForeground( foreground );
		
	}
	
}

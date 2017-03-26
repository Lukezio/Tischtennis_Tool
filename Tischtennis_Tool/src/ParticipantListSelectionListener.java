import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.JPopupMenu;


public class ParticipantListSelectionListener implements MouseListener{

	ParticipantViewer pv;
	JPopupMenu p;
	
	UserInfoLabel lastSelectedLabel = null;
	int lastX = 0;
	int lastY = 0;
	
	public ParticipantListSelectionListener(ParticipantViewer pv) {
		this.pv = pv;
		p = new JPopupMenu("Löschen");
		p.add(new AbstractAction("Löschen") {			
			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					DatabaseHandler.removeFromDatabase(lastSelectedLabel.getPerson().getID());
					pv.updateParticipantList();
					pv.updateScrollbar();
					SouthernPanel.updateNumbers();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		lastSelectedLabel = (UserInfoLabel) e.getSource();
		lastX = e.getX();
		lastY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {		
		if (e.isPopupTrigger()) {
			p.show(lastSelectedLabel, lastX, lastY = 0);
		}
	}

}

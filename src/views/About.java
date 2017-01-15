package views;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import javax.swing.JDialog;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import java.awt.Color;

public class About extends JDialog {
	
	private static final long serialVersionUID = -8794497827595862599L;
	private JLabel lblWebSite;
	private JLabel lblGnuSite;
	private JLabel lblEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					About dialog = new About();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public String readFromJARFile(String filename) throws IOException {
		InputStream is = getClass().getResourceAsStream(filename);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		StringBuffer sb = new StringBuffer();
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line);
			sb.append("\n");
		}
		br.close(); isr.close(); is.close();
		return sb.toString();
	}
	/**
	 * Create the dialog.
	 */
	public About() {
		setBackground(Color.DARK_GRAY);
		getContentPane().setBackground(Color.DARK_GRAY);
		setTitle("About CaesarCipherCrackZ");
		setIconImage(Toolkit.getDefaultToolkit().getImage(About.class.getResource("/resources/unlock.png")));
		setBounds(100, 100, 500, 350);
		
		JLabel lblCopyright = new JLabel("Copyright © 2017  Emil Sergiev");
		lblCopyright.setForeground(Color.BLACK);
		lblWebSite = new JLabel("<html><a style=\"text-decoration: none\" href=\"\">http://emil.free.bg</a></html>");
		lblWebSite.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblWebSite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("http://emil.free.bg"));
				} catch (URISyntaxException | IOException ex) {
					//It looks like there's a problem
					ex.printStackTrace();
				}
			}
		});
		JLabel lblGnuGpl = new JLabel("GNU GPL 3.0");
		lblGnuGpl.setForeground(Color.BLACK);
		lblGnuSite = new JLabel("<html><a style=\"text-decoration: none\" href=\"\">http://www.gnu.org/licenses</a></html>");
		lblGnuSite.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblGnuSite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("http://www.gnu.org/licenses"));
				} catch (URISyntaxException | IOException ex) { 
					//It looks like there's a problem
					ex.printStackTrace();
				}
			}
		});
		JLabel lblVersion = new JLabel("Version: Roman.1a - 1.0");
		lblVersion.setForeground(Color.BLACK);
		JLabel lblReleaseDate = new JLabel("Release date: January 15, 2017");
		lblReleaseDate.setForeground(Color.BLACK);
		JLabel lblContact = new JLabel("Contact: ");
		lblContact.setForeground(Color.BLACK);
		lblEmail = new JLabel("<html><a style=\"text-decoration: none\" href=\"\">emilsergiev@abv.bg</a></html>");
		lblEmail.setEnabled(false);
		lblEmail.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().mail(new URI("mailto:emilsergiev@abv.bg?subject=CaesarGui"));
				} catch (URISyntaxException | IOException ex) {
					//It looks like there's a problem
					ex.printStackTrace();
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCopyright)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblWebSite, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblGnuGpl)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblGnuSite, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblContact)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblVersion)
						.addComponent(lblReleaseDate))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCopyright)
						.addComponent(lblWebSite))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGnuGpl)
						.addComponent(lblGnuSite))
					.addGap(18)
					.addComponent(lblVersion)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblReleaseDate)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(lblContact))
					.addGap(38)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
					.addGap(17))
		);
		
		JTextArea txtArea = new JTextArea();
		txtArea.setForeground(Color.BLACK);
		txtArea.setMargin(new Insets(0, 5, 0, 0));
		txtArea.setBackground(Color.GRAY);
		txtArea.setFont(new Font("Dialog", Font.PLAIN, 11));
		txtArea.setWrapStyleWord(true);
		try { txtArea.setText(readFromJARFile("/resources/LICENSE")); }
		catch (IOException e) { e.printStackTrace(); }
		txtArea.setEditable(false);
		scrollPane.setViewportView(txtArea);
		getContentPane().setLayout(groupLayout);
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() { 
				scrollPane.getVerticalScrollBar().setValue(0);
			}
		});	
	}
}

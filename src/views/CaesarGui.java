package views;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.Color;

public class CaesarGui extends JFrame {

	private static final long serialVersionUID = -8794497827595862599L;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private static AudioClip themeMusic;
	private JPanel contentPane;
	private JTextArea txtEncrypted;
	private JTextArea txtDecrypted;
	private JButton btnCrackIt;
	private JRadioButtonMenuItem btnEnglish;
	private JRadioButtonMenuItem btnBrute;
	private JMenuItem mntmExit;
	private JMenuItem mntmOpen;
	private JFileChooser fileChooser;
	private JMenuItem mntmSave;
	private JMenuItem mntmAbout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CaesarGui frame = new CaesarGui();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CaesarGui() {
		initSound();
		initComponents();
		createEvents();
	}

	public void initSound() {
		themeMusic = Applet.newAudioClip(getLocation("/resources/secretcode.au"));
		themeMusic.loop();
	}

	private URL getLocation(String filename) {
		URL url = null;
		try {
			url = this.getClass().getResource(filename);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	public String[] readDictionary(String[] dicWords) {
		InputStream is = getClass().getResourceAsStream("/resources/google-10000-english.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		for (int i = 0; i < dicWords.length; i++) {
			try {
				dicWords[i] = br.readLine();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			br.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return dicWords;
	}

	public static String[] allSolutions(String encrypted) {
		String[] decrypted = new String[25]; // the 25 decoded versions
		for (byte i = 0; i < decrypted.length; i++) {
			decrypted[i] = decrypt(encrypted, (byte) (i+1));
		}
		return decrypted;
	}

	public static String decrypt(String encrypted, byte key) {
		encrypted = encrypted.toLowerCase().replaceAll("[^a-z \n]", "");
		char[] array = encrypted.toCharArray();

		for (int i = 0; i < array.length; i++) {
			char letter = array[i];

			if (letter != ' ' && letter != '\n') {
				letter = (char) (letter - key);
				if (letter > 'z') {
					letter = (char) (letter - 26);
				}
				if (letter < 'a') {
					letter = (char) (letter + 26);
				}
			}
			array[i] = letter;
		}
		return new String(array);
	}

	public static byte findBestMatch(String[] decrypted, String[] dicWords) {
		int[] match = matchedWords(decrypted, dicWords);
		int max = match[0]; // the maximum # of matched words in each decoded version
		byte position = 0; // the position of the best match version which will be returned
		// find the most matches
		for (byte i = 1; i < match.length; i++) {
			if (match[i] > max) {
				max = match[i];
				position = i;
			}
		}
		return position;
	}

	public static int[] matchedWords(String[] decrypted, String[] dicWords) {
		int[] match = new int[decrypted.length]; // # of matched words in each decoded version
		for (byte i = 0; i < decrypted.length; i++) {
			String[] words = decrypted[i].split("\\s+");
			for (int j = 0; j < words.length; j++) {
				words[j] = words[j].replaceAll("[^\\w]", "");
				for (int x = 0; x < dicWords.length; x++) {
					if (words[j].equals(dicWords[x])) {
						match[i]++;
					}
				}
			}
		}
		return match;
	}
	///////////////////////////////////////////////////////////////////////////////////
	// This method contains all of the code for creating and initializing components //
	///////////////////////////////////////////////////////////////////////////////////
	private void initComponents() {
		setTitle("CaesarCipherCrackZ");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/unlock.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblInput = new JLabel("Enter encrypted text bellow:");
		lblInput.setForeground(Color.BLACK);

		JScrollPane scrollPaneInput = new JScrollPane();
		scrollPaneInput.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		btnCrackIt = new JButton("Crack it");
		btnCrackIt.setForeground(Color.BLACK);
		btnCrackIt.setBackground(Color.LIGHT_GRAY);

		JScrollPane scrollPaneOutput = new JScrollPane();
		scrollPaneOutput.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		JLabel lblIcon1 = new JLabel("");
		lblIcon1.setIcon(new ImageIcon(CaesarGui.class.getResource("/resources/funny-computer-gif.gif")));

		JLabel lblIcon2 = new JLabel("");
		lblIcon2.setIcon(new ImageIcon(CaesarGui.class.getResource("/resources/java.gif")));

		JLabel lblIcon3 = new JLabel("");
		lblIcon3.setIcon(new ImageIcon(CaesarGui.class.getResource("/resources/anonymous.png")));

		JLabel lblOutput = new JLabel("-.-- --- ..-  .-- .. .-.. .-..  .-.. --- ...- .  -- .");
		lblOutput.setForeground(Color.BLACK);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
										.addComponent(scrollPaneInput, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblIcon1, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnCrackIt)))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblIcon3, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblIcon2, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblOutput)
												.addComponent(scrollPaneOutput, GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)))
								.addComponent(lblInput))
						.addContainerGap())
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(lblInput)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblIcon1)
										.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
										.addComponent(btnCrackIt))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(scrollPaneInput, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
										.addGap(1)))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(16)
										.addComponent(lblIcon2)
										.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
										.addComponent(lblIcon3))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(5)
										.addComponent(lblOutput)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(scrollPaneOutput, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)))
						.addContainerGap())
				);
		contentPane.setLayout(gl_contentPane);

		txtEncrypted = new JTextArea();
		txtEncrypted.setForeground(Color.BLACK);
		txtEncrypted.setBackground(Color.LIGHT_GRAY);
		txtEncrypted.setFont(new Font("Dialog", Font.PLAIN, 17));
		scrollPaneInput.setViewportView(txtEncrypted);
		txtEncrypted.setLineWrap(true);
		txtEncrypted.setWrapStyleWord(true);

		txtDecrypted = new JTextArea();
		txtDecrypted.setBackground(Color.LIGHT_GRAY);
		txtDecrypted.setForeground(Color.BLACK);
		txtDecrypted.setFont(new Font("Dialog", Font.PLAIN, 17));
		txtDecrypted.setEditable(false);
		scrollPaneOutput.setViewportView(txtDecrypted);
		txtDecrypted.setLineWrap(true);
		txtDecrypted.setWrapStyleWord(true);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setForeground(Color.BLACK);
		menuBar.setBackground(Color.GRAY);
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		mnFile.setBorderPainted(false);
		mnFile.setForeground(Color.BLACK);
		mnFile.setBackground(Color.DARK_GRAY);
		menuBar.add(mnFile);

		mntmOpen = new JMenuItem("Open");
		mntmOpen.setIcon(new ImageIcon(CaesarGui.class.getResource("/resources/document-open.png")));
		mntmOpen.setBorderPainted(false);
		mntmOpen.setBackground(Color.GRAY);
		mntmOpen.setForeground(Color.BLACK);
		mnFile.add(mntmOpen);

		mntmSave = new JMenuItem("Save");
		mntmSave.setIcon(new ImageIcon(CaesarGui.class.getResource("/resources/document-save.png")));
		mntmSave.setBorderPainted(false);
		mntmSave.setForeground(Color.BLACK);
		mntmSave.setBackground(Color.GRAY);
		mnFile.add(mntmSave);

		mntmExit = new JMenuItem("Exit");
		mntmExit.setIcon(new ImageIcon(CaesarGui.class.getResource("/resources/application-exit.png")));
		mntmExit.setBorderPainted(false);
		mntmExit.setBackground(Color.GRAY);
		mntmExit.setForeground(Color.BLACK);
		mnFile.add(mntmExit);

		JMenu mnSettings = new JMenu("Settings");
		mnSettings.setBorderPainted(false);
		mnSettings.setForeground(Color.BLACK);
		mnSettings.setBackground(Color.DARK_GRAY);
		menuBar.add(mnSettings);

		btnEnglish = new JRadioButtonMenuItem("English dictionary");
		btnEnglish.setIcon(new ImageIcon(CaesarGui.class.getResource("/resources/accessories-dictionary.png")));
		btnEnglish.setBorderPainted(false);
		btnEnglish.setBackground(Color.GRAY);
		btnEnglish.setForeground(Color.BLACK);
		btnEnglish.setSelected(true);
		buttonGroup.add(btnEnglish);
		mnSettings.add(btnEnglish);

		btnBrute = new JRadioButtonMenuItem("Brute force attack");
		btnBrute.setIcon(new ImageIcon(CaesarGui.class.getResource("/resources/document-decrypt.png")));
		btnBrute.setBorderPainted(false);
		btnBrute.setForeground(Color.BLACK);
		btnBrute.setBackground(Color.GRAY);
		buttonGroup.add(btnBrute);
		mnSettings.add(btnBrute);

		JMenu mnHelp = new JMenu("Help");
		mnHelp.setBorderPainted(false);
		mnHelp.setBackground(Color.DARK_GRAY);
		mnHelp.setForeground(Color.BLACK);
		menuBar.add(mnHelp);

		mntmAbout = new JMenuItem("About");
		mntmAbout.setIcon(new ImageIcon(CaesarGui.class.getResource("/resources/help-about.png")));
		mntmAbout.setBorderPainted(false);
		mntmAbout.setForeground(Color.BLACK);
		mntmAbout.setBackground(Color.GRAY);
		mnHelp.add(mntmAbout);
	}
	//////////////////////////////////////////////////////////////
	// This method contains all of the code for creating events //
	//////////////////////////////////////////////////////////////
	private void createEvents() {
		btnCrackIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String encrypted = txtEncrypted.getText();
				String[] decrypted = allSolutions(encrypted); // the 25 decoded versions
				String[] dicWords = new String[10000]; // the 10K English words from the dictionary
				dicWords = readDictionary(dicWords);
				byte bestMatch = findBestMatch(decrypted, dicWords);

				if (btnEnglish.isSelected()) {
					txtDecrypted.setText(decrypted[bestMatch]);
				}
				else {
					txtDecrypted.setText("");
					for (byte i = 0; i < decrypted.length; i++) {
						txtDecrypted.append("===< key " + (i+1) + " >===\n" + decrypted[i] + "\n\n");
					}
				}
			}
		});
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int errorVal, curr;
				fileChooser = new JFileChooser();
				errorVal = fileChooser.showOpenDialog(CaesarGui.this);
				if (errorVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						FileReader fileReader = new FileReader(file);

						while((curr = fileReader.read()) != - 1) {
							txtEncrypted.append(String.valueOf((char) curr));
						}
						fileReader.close();
					} catch (IOException io) { System.out.println("Problem with file"); }
				}
			}
		});
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int errorVal;
				fileChooser = new JFileChooser();
				errorVal = fileChooser.showSaveDialog(CaesarGui.this);

				if (errorVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						FileWriter fileWriter = new FileWriter(file);
						fileWriter.write(txtDecrypted.getText());
						fileWriter.close();
					}
					catch (IOException io) {
						System.out.println("Problem with file");
					}
				}
			}
		});
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				About about = new About();
				about.setModal(true);
				about.setVisible(true);
			}
		});
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int prompt = JOptionPane.showOptionDialog(CaesarGui.this, "Are you sure you want to exit?", "Caesar Cipher CrackZ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (prompt == JOptionPane.YES_OPTION) {
					System.exit(0); 
				}
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				int prompt = JOptionPane.showOptionDialog(CaesarGui.this, "Are you sure you want to exit?", "Caesar Cipher CrackZ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if(prompt == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
	}
}
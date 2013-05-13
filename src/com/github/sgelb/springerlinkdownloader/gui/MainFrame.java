package com.github.sgelb.springerlinkdownloader.gui;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class MainFrame extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField urlField;
	private JTextField savefolderField;
	private JCheckBox deleteCheckbox;
	private JLabel titleLabel;
	private JLabel authorLabel;
	private JProgressBar progressBar;

	public MainFrame() {
		super(new SpringLayout());
		
		SpringLayout sl_panel = new SpringLayout();
		this.setLayout(sl_panel);

		JLabel lblNewLabel = new JLabel("Enter URL:");
		lblNewLabel.setFocusable(false);
		lblNewLabel.setFont(UIManager.getFont("Button.font"));
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel, 10,
				SpringLayout.WEST, this);
		this.add(lblNewLabel);

		urlField = new JTextField();
		sl_panel.putConstraint(SpringLayout.WEST, urlField, 16,
				SpringLayout.EAST, lblNewLabel);
		sl_panel.putConstraint(SpringLayout.EAST, urlField, -10,
				SpringLayout.EAST, this);
		this.add(urlField);
		urlField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Save folder:");
		lblNewLabel_1.setFocusable(false);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblNewLabel, -6,
				SpringLayout.NORTH, lblNewLabel_1);
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 35,
				SpringLayout.NORTH, this);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel_1, 10,
				SpringLayout.WEST, this);
		this.add(lblNewLabel_1);

		savefolderField = new JTextField();
		sl_panel.putConstraint(SpringLayout.SOUTH, urlField, -2,
				SpringLayout.NORTH, savefolderField);
		sl_panel.putConstraint(SpringLayout.EAST, savefolderField, -10,
				SpringLayout.EAST, this);
		sl_panel.putConstraint(SpringLayout.WEST, savefolderField, 5,
				SpringLayout.EAST, lblNewLabel_1);
		sl_panel.putConstraint(SpringLayout.SOUTH, savefolderField, 0,
				SpringLayout.SOUTH, lblNewLabel_1);
		this.add(savefolderField);
		savefolderField.setColumns(10);

		deleteCheckbox = new JCheckBox("Delete temporary PDF files");
		sl_panel.putConstraint(SpringLayout.NORTH, deleteCheckbox, 6,
				SpringLayout.SOUTH, lblNewLabel_1);
		sl_panel.putConstraint(SpringLayout.WEST, deleteCheckbox, 10,
				SpringLayout.WEST, this);
		deleteCheckbox.setSelected(true);
		this.add(deleteCheckbox);


		progressBar = new JProgressBar();
		sl_panel.putConstraint(SpringLayout.WEST, progressBar, 0, SpringLayout.WEST, lblNewLabel);
		sl_panel.putConstraint(SpringLayout.EAST, progressBar, 0, SpringLayout.EAST, urlField);
		progressBar.setStringPainted(true);
		progressBar.setString("Waiting…");
		this.add(progressBar);

		JButton startBtn = new JButton("Start");
		sl_panel.putConstraint(SpringLayout.NORTH, startBtn, 30, SpringLayout.NORTH, progressBar);
		sl_panel.putConstraint(SpringLayout.EAST, startBtn, 0, SpringLayout.EAST, urlField);
		startBtn.setVerticalAlignment(SwingConstants.BOTTOM);
		startBtn.setIcon(null);
		this.add(startBtn);

		JLabel lblNewLabel_3 = new JLabel("Title:");
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 119,
				SpringLayout.NORTH, this);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel_3, 0,
				SpringLayout.WEST, lblNewLabel);
		this.add(lblNewLabel_3);

		authorLabel = new JLabel("n/a");
		authorLabel.setEnabled(false);
		sl_panel.putConstraint(SpringLayout.WEST, authorLabel, -22,
				SpringLayout.EAST, lblNewLabel_1);
		sl_panel.putConstraint(SpringLayout.EAST, authorLabel, 0,
				SpringLayout.EAST, urlField);
		this.add(authorLabel);

		JLabel lblNewLabel_5 = new JLabel("Author:");
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel_5, 0,
				SpringLayout.WEST, lblNewLabel);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblNewLabel_5, -6,
				SpringLayout.NORTH, lblNewLabel_3);
		this.add(lblNewLabel_5);

		titleLabel = new JLabel("n/a");
		sl_panel.putConstraint(SpringLayout.WEST, titleLabel, 27,
				SpringLayout.EAST, lblNewLabel_3);
		sl_panel.putConstraint(SpringLayout.EAST, titleLabel, 0,
				SpringLayout.EAST, urlField);
		titleLabel.setEnabled(false);
		sl_panel.putConstraint(SpringLayout.NORTH, titleLabel, 0,
				SpringLayout.NORTH, lblNewLabel_3);
		this.add(titleLabel);

		JSeparator separator = new JSeparator();
		sl_panel.putConstraint(SpringLayout.NORTH, authorLabel, 11, SpringLayout.SOUTH, separator);
		sl_panel.putConstraint(SpringLayout.WEST, separator, 10,
				SpringLayout.WEST, this);
		sl_panel.putConstraint(SpringLayout.SOUTH, separator, 8,
				SpringLayout.SOUTH, deleteCheckbox);
		sl_panel.putConstraint(SpringLayout.EAST, separator, 0,
				SpringLayout.EAST, urlField);
		this.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		sl_panel.putConstraint(SpringLayout.NORTH, progressBar, 19, SpringLayout.SOUTH, separator_1);
		sl_panel.putConstraint(SpringLayout.SOUTH, separator_1, 8, SpringLayout.SOUTH, lblNewLabel_3);
		sl_panel.putConstraint(SpringLayout.WEST, separator_1, 0, SpringLayout.WEST, lblNewLabel);
		sl_panel.putConstraint(SpringLayout.EAST, separator_1, 0, SpringLayout.EAST, urlField);
		add(separator_1);
 	}
}

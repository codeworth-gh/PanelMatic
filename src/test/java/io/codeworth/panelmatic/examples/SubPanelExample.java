package io.codeworth.panelmatic.examples;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import io.codeworth.panelmatic.PanelBuilder.HeaderLevel;
import io.codeworth.panelmatic.PanelMatic;
import io.codeworth.panelmatic.TestUtils;
import static io.codeworth.panelmatic.componentbehavior.Modifiers.L_CENTER;

/**
 *
 * @author michaelbar-sinai
 */
public class SubPanelExample extends JPanel {

	private static final long serialVersionUID = 1l;

	private JCheckBox chkMonty;
	private JCheckBox chkEmo;
	private JCheckBox chkColbert;
	private JCheckBox chkSimpsons;
	private JCheckBox chkLorem;
	private JButton btnGo;
	private JComboBox cmbType;

	public SubPanelExample() {
		createComponents();
		PanelMatic.begin( this )
			.addHeader(HeaderLevel.H1, new TestUtils.RandIcon(20), "Qoute Generator")
			.add( "Source", PanelMatic.begin() // second builder
								.add(chkMonty)
								.add(chkEmo)
								.add(chkColbert)
								.add(chkSimpsons)
								.add(chkLorem)
								.get())
			.add( new TestUtils.RoundIcon(Color.red, 16),
				  "Output Type", cmbType )
			.addFlexibleSpace()
			.add( btnGo, L_CENTER )
			.get();
	}

	private void createComponents() {
		chkMonty = new JCheckBox("Monty Python");
		chkEmo = new JCheckBox("Emo Philips");
		chkColbert = new JCheckBox("the Colbert Report");
		chkSimpsons = new JCheckBox("The Simpsons");
		chkLorem = new JCheckBox("Lorem Ipsum");
		cmbType = new JComboBox(new Object[]{"Text","HTML","PDF","AFP"} );
		btnGo = new JButton("Generate Qoute");
	}

	public static void main(String[] args) {
		EventQueue.invokeLater( new Runnable() {
			@Override
			public void run() {
				ExampleRunner.startExample( new SubPanelExample() );
			}});
	}

}

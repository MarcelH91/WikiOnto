/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.ist.clonto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.apache.commons.io.FileUtils;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.Syntax;
import com.hp.hpl.jena.tdb.TDBFactory;

import de.ist.clonto.triplestore.query.QueryAreaStream;
import de.ist.clonto.triplestore.query.QueryProcessor;
import de.ist.clonto.triplestore.transform.Prune;
import de.ist.clonto.triplestore.transform.Refactor;

/**
 *
 * @author Marcel 
 * 
 * Some quickly coded GUI to support our approach to ontology
 * engineering. Main parts were generated via Netbeans.
 */
public class Ontogui extends JFrame {

	private static final long serialVersionUID = 5041095879451070694L;
	
	private Dataset dataset;
	private Path ontoPath;
	private Path queryPath;
	private String metricName;
	private String smellName;
	private String refactorName;
	private String pruneName;

	/**
	 * Creates new form ontogui
	 */
	public Ontogui() {
		initComponents();
		metricName = "type:Depth";
		smellName = "Distant Type";
		refactorName = "Rename Element";
		pruneName = "Abandon Type (selective)";
	}

	private void initComponents() {

		AnalysisjPanel = new javax.swing.JPanel();
		loadOntologyButton = new javax.swing.JButton();
		loadQueryButton = new javax.swing.JButton();
		ontologyNameField = new javax.swing.JTextField();
		smellNameField = new javax.swing.JTextField();
		metricsCombobox = new javax.swing.JComboBox<>();
		backupOntologyButton = new javax.swing.JButton();
		runQueryButton = new javax.swing.JButton();
		runMetricsButton = new javax.swing.JButton();
		badSmellComboBox = new javax.swing.JComboBox<>();
		runSmellAnalysisButton = new javax.swing.JButton();
		TransformationjPanel = new javax.swing.JPanel();
		ContextjScrollPane = new javax.swing.JScrollPane();
		contextArea = new javax.swing.JTextArea();
		descriptionjScrollPane = new javax.swing.JScrollPane();
		descriptionArea = new javax.swing.JTextArea();
		displayRefactoringButton = new javax.swing.JButton();
		runRefactoringButton = new javax.swing.JButton();
		refactorCombobox = new javax.swing.JComboBox<>();
		pruningCombobox = new javax.swing.JComboBox<>();
		displayPruningButton = new javax.swing.JButton();
		runPruningButton = new javax.swing.JButton();
		checkbox1 = new java.awt.Checkbox();
		jScrollPane1 = new javax.swing.JScrollPane();
		queryResultArea = new javax.swing.JEditorPane();
		queryResultArea.addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				System.out.print(e.getURL());
			}
		});

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		AnalysisjPanel.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Analysis"));

		loadOntologyButton.setText("load ontology");
		loadOntologyButton.setToolTipText("Load an existing tdb dataset");
		loadOntologyButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						loadOntology(evt);
					}
				});

		loadQueryButton.setText("load query");
		loadQueryButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				loadQuery(evt);
			}
		});

		ontologyNameField.setEditable(false);
		ontologyNameField.setText("<ontology name>");

		smellNameField.setEditable(false);
		smellNameField.setText("<query name>");

		metricsCombobox
				.setModel(new javax.swing.DefaultComboBoxModel<>(
						new String[] { "type:Depth", "type:CategoryInOut",
								"type:ComputerLanguagesDistanceMeasures",
								"type:NOC", "type:subdomainratio",
								"graph:CategoriesPerEntityMaxAvg",
								"graph:EntitiesPerCategoryMaxAvg",
								"graph:graphMetrics" }));
		metricsCombobox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				metricsComboboxActionPerformed(evt);
			}
		});

		backupOntologyButton.setText("backup");
		backupOntologyButton
				.setToolTipText("Create a backup of the selected tdb dataset."
						+ "Selected folder will be cleaned first.");
		backupOntologyButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						backupOntologyButtonActionPerformed(evt);
					}
				});

		runQueryButton.setText("run query");
		runQueryButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				runQueryButtonActionPerformed(evt);
			}
		});

		runMetricsButton.setText("run metric");
		runMetricsButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				runMetricsButtonActionPerformed(evt);
			}
		});

		badSmellComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "Distant Type", "Eponymous Type",
						"Semantically Distant Type",
						"Semantically Distant Entity", "Multi Topic",
						"Off Topic", "Double Reachable Type",
						"Double Reachable Entity", "Cycle",
						"Lazy Type Metric based",
						"Lazy Type Instance Containment", "Missing Type",
						"Redundant Subtype", "Redundant Instance" }));
		badSmellComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				badSmellComboBoxActionPerformed(evt);
			}
		});

		runSmellAnalysisButton.setText("run smell");
		runSmellAnalysisButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						runSmellAnalysisButtonActionPerformed(evt);
					}
				});

		javax.swing.GroupLayout AnalysisjPanelLayout = new javax.swing.GroupLayout(
				AnalysisjPanel);
		AnalysisjPanel.setLayout(AnalysisjPanelLayout);
		AnalysisjPanelLayout
				.setHorizontalGroup(AnalysisjPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								AnalysisjPanelLayout
										.createSequentialGroup()
										.addGap(20, 20, 20)
										.addGroup(
												AnalysisjPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																AnalysisjPanelLayout
																		.createSequentialGroup()
																		.addGroup(
																				AnalysisjPanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								false)
																						.addComponent(
																								ontologyNameField,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								137,
																								Short.MAX_VALUE)
																						.addComponent(
																								smellNameField))
																		.addGroup(
																				AnalysisjPanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addGroup(
																								AnalysisjPanelLayout
																										.createSequentialGroup()
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												loadOntologyButton)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												backupOntologyButton,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												80,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								AnalysisjPanelLayout
																										.createSequentialGroup()
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)
																										.addComponent(
																												loadQueryButton,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												97,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(121,
																												121,
																												121))))
														.addGroup(
																AnalysisjPanelLayout
																		.createSequentialGroup()
																		.addGroup(
																				AnalysisjPanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								metricsCombobox,
																								0,
																								1,
																								Short.MAX_VALUE)
																						.addComponent(
																								badSmellComboBox,
																								0,
																								240,
																								Short.MAX_VALUE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				AnalysisjPanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								runMetricsButton,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								85,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								runQueryButton,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								85,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								runSmellAnalysisButton,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								85,
																								javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addGap(0, 0, Short.MAX_VALUE)));
		AnalysisjPanelLayout
				.setVerticalGroup(AnalysisjPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								AnalysisjPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												AnalysisjPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																ontologyNameField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																backupOntologyButton)
														.addComponent(
																loadOntologyButton))
										.addGap(3, 3, 3)
										.addGroup(
												AnalysisjPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																smellNameField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																loadQueryButton)
														.addComponent(
																runQueryButton))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												AnalysisjPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																metricsCombobox,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																runMetricsButton))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												AnalysisjPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																badSmellComboBox,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																runSmellAnalysisButton))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		TransformationjPanel.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Transformation"));

		contextArea.setEditable(false);
		contextArea.setColumns(20);
		contextArea.setRows(5);
		contextArea.setText("<Context:>");
		ContextjScrollPane.setViewportView(contextArea);

		descriptionArea.setEditable(false);
		descriptionArea.setColumns(20);
		descriptionArea.setRows(5);
		descriptionArea.setText("<Transformations:>");
		descriptionjScrollPane.setViewportView(descriptionArea);

		displayRefactoringButton.setText("Display Refactoring");
		displayRefactoringButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						displayTransformationInfo(false);
					}
				});

		runRefactoringButton.setText("Execute Refactoring");
		runRefactoringButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						runRefactoringButtonActionPerformed(evt);
					}
				});

		refactorCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "Rename Element", "Change Topic", "Move Entity",
						"Move Type", "Add Missing Subtype",
						"Add Missing Instance", "Unite Information",
						"Extract Entity", "Extract Subtype",
						"Remove Redundant Instances",
						"Remove Redundant Subtypes" }));
		refactorCombobox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				refactorComboboxActionPerformed(evt);
			}
		});

		pruningCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "Abandon Type (selective)", "Abandon Entity",
						"Abandon Type (no rescue)", "Abandon Information",
						"Remove Instance", "Remove Subtype",
						"Abandon Type (rescue all)", "Cleanup Unreachable All",
						"Cleanup Unreachable Type", "Cleanup Unreachable Ent",
						"Lift Cycle", "Custom" }));
		pruningCombobox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				pruningComboboxActionPerformed(evt);
			}
		});

		displayPruningButton.setText("Display Pruning");
		displayPruningButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						displayTransformationInfo(true);
					}
				});

		runPruningButton.setText("Execute Pruning");
		runPruningButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				runPruningButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout TransformationjPanelLayout = new javax.swing.GroupLayout(
				TransformationjPanel);
		TransformationjPanel.setLayout(TransformationjPanelLayout);
		TransformationjPanelLayout
				.setHorizontalGroup(TransformationjPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								TransformationjPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												TransformationjPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																TransformationjPanelLayout
																		.createSequentialGroup()
																		.addGroup(
																				TransformationjPanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								pruningCombobox,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								180,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								refactorCombobox,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								180,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(18,
																				18,
																				18)
																		.addGroup(
																				TransformationjPanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								false)
																						.addComponent(
																								displayPruningButton,
																								javax.swing.GroupLayout.Alignment.LEADING,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								displayRefactoringButton,
																								javax.swing.GroupLayout.Alignment.LEADING,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				TransformationjPanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								runRefactoringButton,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								runPruningButton,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE))
																		.addGap(0,
																				0,
																				Short.MAX_VALUE))
														.addGroup(
																TransformationjPanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				ContextjScrollPane)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				descriptionjScrollPane)))
										.addContainerGap()));
		TransformationjPanelLayout
				.setVerticalGroup(TransformationjPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								TransformationjPanelLayout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												TransformationjPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																descriptionjScrollPane,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(
																TransformationjPanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				ContextjScrollPane,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addGroup(
																				TransformationjPanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								refactorCombobox,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								displayRefactoringButton)
																						.addComponent(
																								runRefactoringButton))))
										.addGap(2, 2, 2)
										.addGroup(
												TransformationjPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																pruningCombobox,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																displayPruningButton)
														.addComponent(
																runPruningButton))));

		checkbox1.setLabel("prettyprint");

		queryResultArea.setEditable(false);
		jScrollPane1.setViewportView(queryResultArea);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														AnalysisjPanel,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(27, 27,
																		27)
																.addComponent(
																		checkbox1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(TransformationjPanel,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addGap(3, 3, 3))
				.addGroup(
						layout.createSequentialGroup().addContainerGap()
								.addComponent(jScrollPane1).addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		AnalysisjPanel,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		checkbox1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addComponent(
														TransformationjPanel,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jScrollPane1).addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void loadOntology(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_loadOntology
		if (null != dataset) {
			dataset.end();
		}
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			dataset = TDBFactory.createDataset(fc.getSelectedFile().toString());
			ontologyNameField.setText(fc.getSelectedFile().getName());
			ontoPath = fc.getSelectedFile().toPath();
		} else {
			JOptionPane.showMessageDialog(this, "Loading ontology failed");
		}
	}// GEN-LAST:event_loadOntology

	private void loadQuery(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_loadQuery
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")
				+ "/sparql"));
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			queryPath = fc.getSelectedFile().toPath();

			smellNameField.setText(fc.getSelectedFile().getName());
		} else {
			JOptionPane.showMessageDialog(this, "Loading query failed");
		}
	}// GEN-LAST:event_loadQuery

	private void backupOntologyButtonActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_backupOntologyButtonActionPerformed
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				FileUtils.cleanDirectory(fc.getSelectedFile());
				FileUtils
						.copyDirectory(ontoPath.toFile(), fc.getSelectedFile());
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this, "Loading ontology failed");
			}
			JOptionPane.showMessageDialog(this, "Created Backup files!");
		} else {
			JOptionPane.showMessageDialog(this, "Loading ontology failed");
		}

	}// GEN-LAST:event_backupOntologyButtonActionPerformed

	private void runQueryButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_runQueryButtonActionPerformed
		List<String> lines = null;
		try {
			lines = Files.readAllLines(queryPath);
		} catch (IOException ex) {
			Logger.getLogger(Ontogui.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		String queryString = "";
		for (String line : lines) {
			queryString += line + System.lineSeparator();
		}
		Query query = QueryFactory.create(queryString, Syntax.syntaxARQ);
		queryResultArea.setText("Starting query: "
				+ queryPath.toFile().getName() + "\n");
		Thread t = new Thread(new QueryProcessor(query, new QueryAreaStream(
				queryResultArea), dataset, checkbox1.getState()));
		t.start();
	}// GEN-LAST:event_runQueryButtonActionPerformed

	private void runRefactoringButtonActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_runRefactoringButtonActionPerformed
		new Refactor(dataset).execute(refactorName);
	}// GEN-LAST:event_runRefactoringButtonActionPerformed

	private void runPruningButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_runPruningButtonActionPerformed
		new Prune(dataset).execute(pruneName);
	}// GEN-LAST:event_runPruningButtonActionPerformed

	private void badSmellComboBoxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_badSmellComboBoxActionPerformed
		smellName = badSmellComboBox.getSelectedItem().toString();
	}// GEN-LAST:event_badSmellComboBoxActionPerformed

	private void runSmellAnalysisButtonActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_runSmellAnalysisButtonActionPerformed
		String filename = smellName;
		File smellFile = new File(System.getProperty("user.dir")
				+ "/sparql/smells/" + filename.replaceAll(" ", "") + ".sparql");

		List<String> lines = null;

		try {
			lines = Files.readAllLines(smellFile.toPath());
		} catch (IOException ex) {
			Logger.getLogger(Ontogui.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		String queryString = "";
		for (String line : lines) {
			queryString += line + System.lineSeparator();
		}
		Query query = QueryFactory.create(queryString, Syntax.syntaxARQ);
		queryResultArea.setText("Starting analysis: " + smellName + "\n");
		Thread t = new Thread(new QueryProcessor(query, new QueryAreaStream(
				queryResultArea), dataset, checkbox1.getState()));
		t.start();
	}// GEN-LAST:event_runSmellAnalysisButtonActionPerformed

	private void refactorComboboxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_refactorComboboxActionPerformed
		refactorName = refactorCombobox.getSelectedItem().toString();
	}// GEN-LAST:event_refactorComboboxActionPerformed

	private void pruningComboboxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_pruningComboboxActionPerformed
		pruneName = pruningCombobox.getSelectedItem().toString();
	}// GEN-LAST:event_pruningComboboxActionPerformed

	private void displayTransformationInfo(boolean isPruning) {// GEN-FIRST:event_displayPruningButtonActionPerformed
		String filename = "";
		String path;
		if(isPruning){
			filename = pruneName.replace(" ", "");
			path = "/sparql/transfdescr/prunings/";
		}else{ 
			filename = refactorName.replace(" ", "");
			path = "/sparql/transfdescr/refactorings/";
		}

		File textFile = new File(System.getProperty("user.dir")
				+ path + filename + "C.txt");

		List<String> lines = null;

		try {
			if (textFile.exists())
				lines = Files.readAllLines(textFile.toPath());
			else
				throw new Exception();
			contextArea.setText("Context:");
			for (String line : lines) 
				contextArea.append("\n" + line);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Reading context file for "
					+ filename + " failed!");
		}

		textFile = new File(System.getProperty("user.dir")
				+ "/sparql/transfdescr/prunings/" + filename + "T.txt");

		lines = null;

		try {
			if (textFile.exists())
				lines = Files.readAllLines(textFile.toPath());
			else
				throw new Exception();
			descriptionArea.setText("Transformations:");
			for (String line : lines) 
				descriptionArea.append("\n" + line);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this,
					"Reading transformation file for " + filename + " failed!");
		}

	}// GEN-LAST:event_displayPruningButtonActionPerformed

	private void metricsComboboxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_metricsComboboxActionPerformed
		metricName = metricsCombobox.getSelectedItem().toString();
	}// GEN-LAST:event_metricsComboboxActionPerformed

	private void runMetricsButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_runMetricsButtonActionPerformed
		String folder = metricName.split(":")[0].toLowerCase();
		String filename = metricName.split(":")[1];
		File metricFile = new File(System.getProperty("user.dir")
				+ "/sparql/metrics/" + folder + "/" + filename + ".sparql");

		List<String> lines = null;

		try {
			lines = Files.readAllLines(metricFile.toPath());
		} catch (IOException ex) {
			Logger.getLogger(Ontogui.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		String queryString = "";
		for (String line : lines) {
			queryString += line + System.lineSeparator();
		}
		Query query = QueryFactory.create(queryString, Syntax.syntaxARQ);
		queryResultArea.setText("Starting analysis:" + metricName + "\n");
		System.err.println(checkbox1.isEnabled());
		Thread t = new Thread(new QueryProcessor(query, new QueryAreaStream(
				queryResultArea), dataset, checkbox1.getState()));
		t.start();
	}// GEN-LAST:event_runMetricsButtonActionPerformed

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Ontogui.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Ontogui.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Ontogui.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Ontogui.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Ontogui().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPanel AnalysisjPanel;
	private javax.swing.JScrollPane ContextjScrollPane;
	private javax.swing.JPanel TransformationjPanel;
	private javax.swing.JButton backupOntologyButton;
	private javax.swing.JComboBox<String> badSmellComboBox;
	private java.awt.Checkbox checkbox1;
	private javax.swing.JTextArea contextArea;
	private javax.swing.JTextArea descriptionArea;
	private javax.swing.JScrollPane descriptionjScrollPane;
	private javax.swing.JButton displayPruningButton;
	private javax.swing.JButton displayRefactoringButton;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JButton loadOntologyButton;
	private javax.swing.JButton loadQueryButton;
	private javax.swing.JComboBox<String> metricsCombobox;
	private javax.swing.JTextField ontologyNameField;
	private javax.swing.JComboBox<String> pruningCombobox;
	private javax.swing.JEditorPane queryResultArea;
	private javax.swing.JComboBox<String> refactorCombobox;
	private javax.swing.JButton runMetricsButton;
	private javax.swing.JButton runPruningButton;
	private javax.swing.JButton runQueryButton;
	private javax.swing.JButton runRefactoringButton;
	private javax.swing.JButton runSmellAnalysisButton;
	private javax.swing.JTextField smellNameField;
	// End of variables declaration//GEN-END:variables
}

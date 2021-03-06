# ReviseMiniCLOntology
A prototype for analysing an extracted ontology in the computer languages domain.

## Runnables

To start an extraction manually, run MyCrawlerManager. The store will be saved at the folder 'Clontotdb'. 
Be advised: You should unlock more main memory by using the Java-VM option: -Xmx4G -XX:+UseConcMarkSweepGC

You may also choose another root type. We do not guarantee that the extraction process terminates in an acceptable time because of Wikipedia's tangledness. Therefore you have to edit the code in 'MyCrawlerManager' by yourself.

We already provide a TDB-store in the .zip file 'clontologytdb.zip.'. You can unpack it into any folder.

To start the demo tool for the revision, run Ontogui.java.

## Ontology Revision

Ontogui.java has to be run to start with a revision. The implemented GUI contains metrics, bad smells, refactorings and prunings that can be tried out.

Tipp: Every query String is read from a corresponding text file only, when you click the corresponding run button. It is quite handy to open a text-editor with a testquery file and select this file as a custom query. The GUI will only keep the path to the created file, but not its content.
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.ist.clonto.triplestore.transform;

import com.hp.hpl.jena.graph.Graph;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.ParameterizedSparqlString;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.tdb.TDB;
import com.hp.hpl.jena.update.UpdateAction;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcel
 */
public class TransformationProcessor {

    private final Dataset dataset;

    public TransformationProcessor(Dataset dataset) {
        this.dataset = dataset;
    }
    
    public long transform(String tfilename, Map<String,String> parameter){
        File tfile = new File(System.getProperty("user.dir")+"/sparql/transformations/"+tfilename);
        String transformation = "";
        try {
            List<String> lines = Files.readAllLines(tfile.toPath());
            for(String line : lines){
                transformation+=line+"\n";
            }
        } catch (IOException ex) {
            Logger.getLogger(TransformationProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataset.begin(ReadWrite.WRITE);
        Graph graph = dataset.asDatasetGraph().getDefaultGraph();
        long size = graph.size();
        ParameterizedSparqlString pss = new ParameterizedSparqlString();
        pss.setCommandText(transformation);
        for(String key: parameter.keySet()){
            if(!parameter.get(key).contains("clonto:")){
                pss.setLiteral(key, parameter.get(key).trim());
            }else{
                pss.setIri(key, parameter.get(key).trim());
            }
        }
        System.out.println(pss.toString());
        UpdateAction.execute(pss.asUpdate(), graph);
        size = graph.size() - size;
        dataset.commit();
        return size;
    }

}
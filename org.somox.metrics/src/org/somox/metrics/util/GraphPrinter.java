package org.somox.metrics.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.regex.Matcher;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.emftext.language.java.types.Type;

import org.jgrapht.Graph;
import org.somox.kdmhelper.KDMHelper;
import org.somox.metrics.helper.ComponentToImplementingClassesHelper;

//import de.fzi.gast.types.GASTClass;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

/**
 * Helper class used in SoMoX to dump internal graphs in GraphML and DOT file format for debugging
 * and metric calibration
 * 
 * @author Steffen Becker
 */
public class GraphPrinter {

	public static final String graphFolderSegment = "/dotClusterTrace";
	
	/**
	 * Logger of this helper class
	 */
	private static Logger logger = Logger.getLogger(GraphPrinter.class);

	/**
	 * Clean the given folder recursively to allow dumping of new graphs
	 * @param outputFolder The folder to delete
	 */
	public static void cleanOutputFolder(String outputFolder) {
		if (logger.isTraceEnabled()) { // only run in trace mode
			String fileURIString = getFileURI(outputFolder);
			if (fileURIString != null) {
				File baseDirectory = new File(fileURIString+graphFolderSegment);
				if (baseDirectory.exists()) {
					for (File f : baseDirectory.listFiles()) {
						f.delete();
					}
					baseDirectory.delete();
				}
			}
		}
	}
	
	/** Dump the given graph in both DOT and GraphML format
	 * @param <V> Type of the graph's vertices
	 * @param <T> Type of the graph's edges
	 * @param componentToClassesHelper Helper class used to retrieve the classes implementing a component
	 * @param relationshipGraph The graph to dump
	 * @param outputFolder The folder in which the graph is dumped
	 * @param iteration Iteration number used to create the filename
	 * @param subgraphNo Subgraph number used to create the filename
	 */
	public static <V, T> void dumpGraph(
			ComponentToImplementingClassesHelper componentToClassesHelper,
			Graph<V, T> relationshipGraph,
			String outputFolder, 
			int iteration,
			int subgraphNo) {

		String fileURIString = getFileURI(outputFolder);
		if (fileURIString != null) {
			File baseDirectory = new File(fileURIString+graphFolderSegment);
			if (!baseDirectory.exists()) {
				baseDirectory.mkdir();
			}
			String filename = baseDirectory.getAbsolutePath()+"/graph"+iteration+"."+subgraphNo;
			dumpGraphToDot(componentToClassesHelper, relationshipGraph, filename+".dot");
			dumpGraphToGML(componentToClassesHelper, relationshipGraph, filename+".gml");
		}
		
	}
	
	/**
	 * Retrieve for the given relative outputFolder an absolute folder name
	 * @param outputFolder The workspace relative path of the output folder
	 * @return The absolute path of the output folder
	 */
	private static String getFileURI(String outputFolder) {
		if (outputFolder == null) {
			logger.warn("Failed to export graph, filename not set!");
		} else {
			IResource fileURI = ResourcesPlugin.getWorkspace().getRoot().findMember(outputFolder);
			if (fileURI == null) {
				logger.warn("Failed to dump graph!");
			} else {
				IContainer parent = fileURI.getParent();
				if (parent != null) {
					IPath rawLocation = parent.getLocation();
					if (rawLocation != null) {
						return rawLocation.toOSString();
					} else {
						logger.warn ("Did not get raw location of "+parent.toString());
					}
				} else {
					logger.warn("Did not find parent of "+fileURI.toString());
				}
			}
		}
		return null;	
	}
			
	private static <V, T> void dumpGraphToDot(
			ComponentToImplementingClassesHelper componentToClassesHelper,
			Graph<V, T> relationshipGraph,
			String filename) {
		try {
			File f = new File(filename);
			FileWriter fw = new FileWriter(f);
			fw.write("digraph G {\n");
			for (V link : relationshipGraph.vertexSet()) {
				fw.write(getNodeId(componentToClassesHelper,link,false)+";\n");
			}
			for (T edge : relationshipGraph.edgeSet()) {
				fw.write(getNodeId(componentToClassesHelper,relationshipGraph.getEdgeSource(edge),false));
				fw.write(" -> ");
				fw.write(getNodeId(componentToClassesHelper,relationshipGraph.getEdgeTarget(edge),false));
				fw.write(" [label=\""+edge.toString().replaceAll("\n", Matcher.quoteReplacement("\\n"))+"\"];\n");
			}
			fw.write("}\n");
			fw.close();
		} catch (Exception e) {
			logger.warn("Producing DOT trace failed",e);
		}
	}

	private static <V, T> void dumpGraphToGML(
			ComponentToImplementingClassesHelper componentToClassesHelper,
			Graph<V, T> relationshipGraph,
			String filename) {
		try {
			File f = new File(filename);
			FileWriter fw;
				fw = new FileWriter(f);
			fw.write("graph [\ndirected 1\n");
			for (V link : relationshipGraph.vertexSet()) {
				fw.write("node [\nname "+getNodeId(componentToClassesHelper,link,true)+"\nlabel "+getNodeId(componentToClassesHelper,link,true)+"\n" +
						"graphics\n[hasFill 0\n]\n]\n");
			}
			for (T edge : relationshipGraph.edgeSet()) {
				fw.write("edge [\nsource "+getNodeId(componentToClassesHelper,relationshipGraph.getEdgeSource(edge),true)+"\n");
				fw.write("target "+getNodeId(componentToClassesHelper,relationshipGraph.getEdgeTarget(edge),true)+"\n");
				fw.write("label \""+edge.toString()+"\"\n" +
						"graphics\n[\ntargetArrow \"standard\"\n]\n]\n");
			}
			fw.write("]\n");
			fw.close();
		} catch (IOException e) {
			logger.warn("Producing GML trace failed",e);
		}
	}

	private static String getNodeId(
			ComponentToImplementingClassesHelper componentToClassesHelper,
			Object component, 
			boolean useRealBreak) {
		if (component instanceof Type)
			return "\"" + KDMHelper.computeFullQualifiedName(((Type)component)) + "\"";
		else if (component instanceof ComponentImplementingClassesLink)
			return getNodeId(componentToClassesHelper, (ComponentImplementingClassesLink)component, useRealBreak);
		return component.toString();
	}
	
	private static String getNodeId(
			ComponentToImplementingClassesHelper componentToClassesHelper,
			ComponentImplementingClassesLink component, 
			boolean useRealBreak) {
		Set<Type> classes = componentToClassesHelper.deriveImplementingClasses(component);
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("\"");
		for (Type clazz : classes) {
			stringBuilder.append(KDMHelper.computeFullQualifiedName(clazz));
			stringBuilder.append(useRealBreak ? "\n" : "\\n");
		}
		if (!useRealBreak) 
			stringBuilder.delete(stringBuilder.length()-2, stringBuilder.length());
		stringBuilder.append("\"");
		
		return stringBuilder.toString();
	}
}

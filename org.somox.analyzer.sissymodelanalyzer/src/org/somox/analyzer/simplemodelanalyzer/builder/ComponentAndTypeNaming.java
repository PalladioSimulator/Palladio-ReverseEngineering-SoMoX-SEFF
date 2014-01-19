package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.*;

import org.somox.kdmhelper.KDMHelper;
import org.somox.kdmhelper.metamodeladdition.Root;

import de.uka.ipd.sdq.pcm.repository.Interface;
import de.uka.ipd.sdq.pcm.repository.RepositoryComponent;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

/**
 * Component, package, interface, and port naming facility.
 * @author Klaus Krogmann
 *
 */
public class ComponentAndTypeNaming {
	
	/**
	 * General logger
	 */
	private Logger logger = Logger.getLogger(ComponentAndTypeNaming.class);
	/**
	 * Counter for composite component names
	 */
	private int compositeComponentNumber, primitiveComponentNumber, requiredPortNumber, providedPortNumber;
	
	private final int MAXIMUM_NAME_LENGTH = 55;
	private final int MAX_NUMBER_OF_PATH_SEGMENTS_IN_INTERFACE_NAME = 3;
	
	public ComponentAndTypeNaming() {
		this.compositeComponentNumber = 0;
		this.primitiveComponentNumber = 0;
		this.requiredPortNumber = 0;
		this.primitiveComponentNumber = 0;
	}

	@Deprecated
	public String createSimpleComponentName(int i, Type astClass) {
		// astClass.getName? => (Category)
		return "Comp. " + i + ": " + ((Category) astClass).getName();
	}

	/**
	 * Creates a primitive component name
	 * @param gastClasses: inner classes of the component
	 * @param shorten true: a short name
	 * @return
	 */
	public String createSimpleComponentName(List<Type> astClasses, boolean shorten) {
		StringBuilder nameBuilder = new StringBuilder();
		nameBuilder.append(" <PC No. "+ primitiveComponentNumber++);
		
		StringBuilder subComponentNames = new StringBuilder();
		for(Type  astClass : astClasses) { 
			// use (CompilationUnit) casting for Type
			subComponentNames.append(" " + KDMHelper.computeFullQualifiedName((Type)astClass));
		}
		
		if(shorten) {
			nameBuilder.append(shorten(subComponentNames.toString()));
		} else {
			nameBuilder.append(subComponentNames);
		}
		
		nameBuilder.append(">");
		
		return nameBuilder.toString();
	}
	
	public String createSimpleComponentName(Type astClass) {
		return KDMHelper.computeFullQualifiedName(astClass) + " <PC No. "+ primitiveComponentNumber++ + ">";
	}
	
	public String createSimpleComponentName(int i, List<ComponentImplementingClassesLink> currentList) {
		StringBuilder sb = new StringBuilder();
		for(ComponentImplementingClassesLink currentClassesLinkList : currentList) {
			for (Type currentClass : currentClassesLinkList.getImplementingClasses()) {
				sb.append(currentClass.getName() + " ");
			}
		}
		String name = "Comp No. " + i + " " + sb.toString();		
		return shorten(name);
	}
	
	/**
	 * Short name
	 * @param innerComponents
	 * @return
	 */
	public String createCompositeComponentName(Collection<ComponentImplementingClassesLink> innerComponents) {		
		return createCompositeComponentName(innerComponents, true);
	}
	
	public String createCompositeComponentName(Collection<ComponentImplementingClassesLink> innerComponents, boolean shorten) {
		compositeComponentNumber++;
		StringBuilder nameBuilder = new StringBuilder();
		nameBuilder.append("CC No. ");
		nameBuilder.append(compositeComponentNumber + " ");
		nameBuilder.append(createComponentNameBasedOnPackageName(compositeComponentNumber, innerComponents));		
		nameBuilder.append(" <");
		
		// collect subcomponent names:
		StringBuilder subComponentName = new StringBuilder();
		for(ComponentImplementingClassesLink subcomponent : innerComponents) {
			if(subcomponent.getComponent() != null) { // empty for very initial components
				subComponentName.append(subcomponent.getComponent().getEntityName() + " ");
			} else {
				subComponentName.append(
						createSimpleComponentName(0, subcomponent.getSubComponents()) + " "); //0: default level 
			}
		}
		subComponentName.deleteCharAt(subComponentName.length()-1);
		
		if(shorten) {
			nameBuilder.append(shorten(subComponentName.toString(), true)); //keep tail intact; remove start
		} else {
			nameBuilder.append(subComponentName.toString());
		}
		nameBuilder.append(">");

		return nameBuilder.toString();
	}

	/**
	 * Search for the package name that occurs most often
	 * @param i running number
	 * @param currentList List of classes belonging to the component
	 * @return Component name
	 */
	private String createComponentNameBasedOnPackageName(int i,
			Collection<ComponentImplementingClassesLink> currentList) {
		String returnComponentName = "";
		HashMap<String, Integer> numberOfPackageNames = new HashMap<String, Integer>();
		HashMap<String, String> packageNames = new HashMap<String, String>();
		String maxNumberPackageId = null;
		String directoryName = "";
		int maxNumber = 0;
		for (ComponentImplementingClassesLink currentClassesLink : currentList) {
			for(Type currentClass : currentClassesLink.getImplementingClasses()) {
				if (KDMHelper.getSurroundingPackage(currentClass) != null) {
					Integer tmpNumber = numberOfPackageNames.get(Root.getIdForPackage(KDMHelper.getSurroundingPackage(currentClass)));
					if (tmpNumber != null) {
						tmpNumber++;
						numberOfPackageNames.put(Root.getIdForPackage(KDMHelper.getSurroundingPackage(currentClass)), tmpNumber);
						if (tmpNumber > maxNumber) {
							maxNumber = tmpNumber;
							maxNumberPackageId = Root.getIdForPackage(KDMHelper.getSurroundingPackage(currentClass));
						}
					} else {
						numberOfPackageNames.put(Root.getIdForPackage(KDMHelper.getSurroundingPackage(currentClass)), 1);
						packageNames.put(Root.getIdForPackage(KDMHelper.getSurroundingPackage(currentClass)), KDMHelper.computeFullQualifiedName(KDMHelper.getSurroundingPackage(currentClass)));
						if (1 > maxNumber) {
							maxNumber = 1;
							maxNumberPackageId = Root.getIdForPackage(KDMHelper.getSurroundingPackage(currentClass));
						}
					}
				} else if (KDMHelper.getJavaNodeSourceRegion(currentClass) != null && KDMHelper.getSourceFile(KDMHelper.getJavaNodeSourceRegion((Commentable)currentClass)) != null){			
					directoryName = KDMHelper.getSourceFile(KDMHelper.getJavaNodeSourceRegion(currentClass)).getPath();				
				} else {
					logger.warn("found neither packages nor directories for GAST class " + KDMHelper.computeFullQualifiedName(currentClass));
				}
			} 
		}

		returnComponentName = "";
		if(maxNumber > 0) {
			String compName = packageNames.get(maxNumberPackageId);
			if (compName != null) {
				returnComponentName += compName;
			}
		}
		
		if(!directoryName.equals("")) {
			returnComponentName += "(dir: " + directoryName + ")";
		}
		
		return shorten(returnComponentName);
	}
	
	public String createComponentInstanceName(RepositoryComponent repositoryComponent) {
		if(repositoryComponent != null) {
			return repositoryComponent.getEntityName() + "-instance";
		} else {
			return "class-level-instance";
		}
	}
	
	/**
	 * Creates the name for a provided interface
	 * @param provInterface
	 * @param component
	 * @return
	 */
	public String createProvidedPortName(Interface provInterface, RepositoryComponent component) {		
		
		String ifName = provInterface.getEntityName();
		if(ifName.contains(".")) {
			String[] subStrings = ifName.split("\\.", 0);
			ifName = subStrings[subStrings.length-1]; //last segment
		}
		ifName += " " + providedPortNumber++;
		
		return shorten(ifName) + " (prov)";		
	}
	
	public String createProvidedSystemPortName(Interface provInterface, RepositoryComponent component) {
		return createProvidedPortName(provInterface, component) + "(sys)";
	}
	
	/**
	 * Creates the name for a required interface
	 * @param reqInterface
	 * @param component
	 * @return
	 */
	public String createRequiredPortName(Interface reqInterface, RepositoryComponent component) {		
		 
		String ifName = reqInterface.getEntityName();
		if(ifName.contains(".")) {
			String[] subStrings = ifName.split("\\.", 0);
			ifName = subStrings[subStrings.length-1]; //last segment
		}
		ifName += " " + requiredPortNumber++;
		
		return shorten(ifName) + " (req)";
	}
	
	public String createRequiredSystemPortName(Interface reqInterface, RepositoryComponent component) {
		return createRequiredPortName(reqInterface, component) + "(sys)";
	}	
	
	/**
	 * Interface name created for a real GAST interface class.
	 * @param interfaceClass
	 * @return
	 */
	public String createInterfaceName(Type interfaceClass) {
		String interfaceName = segmentBasedInterfaceName(KDMHelper.computeFullQualifiedName(interfaceClass));
		return shorten(interfaceName, true);
	}	
	
	/**
	 * Interface name created for a usual class which is not 
	 * marked as an interface.
	 * @param interfaceClass
	 * @return
	 */
	public String createInterfaceNameForClass(Type interfaceClass) {
		String interfaceName = segmentBasedInterfaceName(KDMHelper.computeFullQualifiedName(interfaceClass));		
		return "I" + shorten(interfaceName, true);
	}

	/**
	 * Uses last x segments to create an Interface name
	 * @param qualifiedName
	 * @return
	 */
	private String segmentBasedInterfaceName(String qualifiedName) {
		// last two segments:
		String[] segments = qualifiedName.split("\\.");
		String interfaceName = "";
		
		boolean first = true;
		for(int i = MAX_NUMBER_OF_PATH_SEGMENTS_IN_INTERFACE_NAME; i > 0; i--) {
			if(segments.length>=i) {
				if(!first) {
					interfaceName += ".";					
				}				
				interfaceName += segments[segments.length-i];
				first = false;
			}
		}
		return interfaceName;
	}
	
	/**
	 * Shorten long strings
	 * @param theString string to shorten
	 * @param removeStartOfString switch between removing trail or head of string
	 * @return
	 */
	private String shorten(String theString, boolean removeStartOfString) {
		String name = theString;
		if(theString.length() > MAXIMUM_NAME_LENGTH) {
			if(removeStartOfString) {
				name = "..." + theString.substring(theString.length() - MAXIMUM_NAME_LENGTH + 3, theString.length()) ;
			} else {
				name = theString.substring(0, MAXIMUM_NAME_LENGTH - 3) + "..."; 
			}
		}
		
		return name;		
	}
	
	private String shorten(String theString) {
		return shorten(theString, false);
	}
}

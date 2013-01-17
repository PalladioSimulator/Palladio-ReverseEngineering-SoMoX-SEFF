package org.somox.kdmhelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.gmt.modisco.infra.common.core.internal.utils.ModelUtils;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.somox.kdmhelper.metamodeladdition.Root;

//@SuppressWarnings("restriction")
public class KDMReader {

	private Root root;

	private final static Logger logger = Logger.getLogger(KDMReader.class
			.getName());

	public KDMReader() {
		root = new Root();
		org.eclipse.gmt.modisco.java.emf.JavaPackage.eINSTANCE.eClass();
		org.eclipse.gmt.modisco.omg.kdm.kdm.KdmPackage.eINSTANCE.eClass();
		org.eclipse.modisco.java.composition.javaapplication.JavaapplicationPackage.eINSTANCE
				.eClass();
	}

	public Root getRoot() {
		return root;
	}

	public void loadFiles(Collection<String> filesLocationList)
			throws IOException {
		for (String fileLocation : filesLocationList) {
			File file = new File(fileLocation);
			URI URIfile = URI.createFileURI(file.getAbsolutePath());

			Resource resource = ModelUtils.loadModel(URIfile);
			// TODO fix
			Map<Object, Object> loadOptions = setupLoadOptions(resource);
			addModelToRoot(resource);

		}
	}

	// TODO test
	public void loadFile(URI file) throws IOException {
		Resource resource;
		resource = ModelUtils.loadModel(file);
		addModelToRoot(resource);

	}

	private void addModelToRoot(Resource resource) {
		root.addModels(getModelsFromResource(resource));
	}

	private Collection<Model> getModelsFromResource(Resource resource) {
		List<Model> modelList = new ArrayList<Model>();
		for (EObject obj : resource.getContents()) {
			if (obj instanceof JavaApplication) {
				JavaApplication javaApp = (JavaApplication) obj;
				for (Object ref : javaApp.eCrossReferences()) {
					if (ref instanceof Model) {
						Model model = (Model) ref;
						modelList.add(model);
					}
				}
			}
		}
		return modelList;
	}

	private Map<Object, Object> setupLoadOptions(Resource resource) {
		Map<Object, Object> loadOptions = ((XMLResourceImpl) resource)
				.getDefaultLoadOptions();
		loadOptions.put(XMLResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);
		loadOptions
				.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
		loadOptions
				.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.TRUE);
		loadOptions.put(XMLResource.OPTION_USE_PARSER_POOL,
				new XMLParserPoolImpl());
		loadOptions.put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP,
				new HashMap<Object, Object>());
		return loadOptions;
	}

}

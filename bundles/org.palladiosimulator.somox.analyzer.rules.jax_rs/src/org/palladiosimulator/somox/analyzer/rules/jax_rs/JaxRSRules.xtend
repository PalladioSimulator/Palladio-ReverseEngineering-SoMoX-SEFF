package org.palladiosimulator.somox.analyzer.rules.jax_rs

import java.util.stream.Collectors
import org.emftext.language.java.containers.impl.CompilationUnitImpl
import org.palladiosimulator.somox.analyzer.rules.engine.IRule

import static org.palladiosimulator.somox.analyzer.rules.engine.PCMDetectorSimple.*
import static org.palladiosimulator.somox.analyzer.rules.engine.RuleHelper.*

class JaxRSRules implements IRule{
	
	override processRules(CompilationUnitImpl unitImpl) {
		
		if(unitImpl.name.contains("Test")) return
		
		val numPreds = getAllInterfaces(unitImpl).stream.filter(i|i.name.contains("Predicate")).collect(Collectors.toList).size
		if (numPreds>0){
			return
		}
		
		if(isAbstraction(unitImpl) || unitImpl.name.startsWith("Abstract")) {
			return
		}
		
		val compNameIncluded = (unitImpl.name.contains("ImageDB") || 
			unitImpl.name.contains("CreatorFactory") || 
			unitImpl.name.contains("Loader") ||
			unitImpl.name.contains("DatastoreFactory")
		)
		
		if(compNameIncluded){
			detectDefault(unitImpl)
			return
		}
		
		// project/package dependent - image service - enums are components
		if(isUnitAnEnum(unitImpl)){
			detectDefault(unitImpl)
			return
		}
		
		
		// package dependent - image service / recommendation service - cache comps extending abstract caches / ext abstract recomm
		if(isClassExtending(unitImpl)){
			if(getExtends(unitImpl).name.contains("Cache") || getExtends(unitImpl).name.contains("Recommender")){
				detectDefault(unitImpl)
				return
			}
		}
		
		// package dependant - acme air , service annotation
		if(isUnitAnnotatedWithName(unitImpl, "DataService")){
			detectComponent(unitImpl)
			detectOperationInterface(unitImpl)
			getMethods(unitImpl).forEach[m|
			if(isMethodModifiedExactlyWith(m,"public")) detectProvidedInterface(unitImpl,m)]
			getFields(unitImpl).forEach[f|if(isFieldAbstract(f)) detectRequiredInterface(unitImpl, f)]
		return
		}
		
		
		// technology based and general recognition
		
		val isConverter = isUnitAnnotatedWithName(unitImpl, "Converter")
		if(isConverter){
			detectDefault(unitImpl)
		return
		}
		
		// detect controller component	
		val isUnitController = isUnitAnnotatedWithName(unitImpl, "Path")
		if(isUnitController){
			detectComponent(unitImpl) 
			detectOperationInterface(unitImpl)
			getMethods(unitImpl).forEach[m|
			if(isMethodAnnotatedWithName(m,"DELETE","GET","HEAD","PUT","POST","OPTIONS")) detectProvidedInterface(unitImpl,m)]
			getFields(unitImpl).forEach[f|if(isFieldAbstract(f)) detectRequiredInterface(unitImpl, f)]
		return
		} 
		
		val isWebListener = isUnitAnnotatedWithName(unitImpl, "WebListener","WebServlet")
		if(isWebListener){
			detectComponent(unitImpl)
			detectOperationInterface(unitImpl)
			getMethods(unitImpl).forEach[m|
			if(isMethodModifiedExactlyWith(m,"public") || isMethodModifiedExactlyWith(m,"protected")) detectProvidedInterface(unitImpl,m)]
			getFields(unitImpl).forEach[f|if(isFieldAbstract(f)) detectRequiredInterface(unitImpl, f)]
		return
		}
		
		// detect implementing component
		val isUnitImpl = isClassImplementing(unitImpl)
		if(isUnitImpl && !isUnitController && !isWebListener){
			detectComponent(unitImpl)
			val firstIn = getAllInterfaces(unitImpl).get(0)
			detectOperationInterface(firstIn)
			getMethods(firstIn).forEach[m|detectProvidedInterface(unitImpl, firstIn, m)]
			getFields(unitImpl).forEach[f|if(isFieldAbstract(f)) detectRequiredInterface(unitImpl, f)]
			return
		}
		
		// detect normal components
		val classModified = isClassModifiedExactlyWith(unitImpl, "public","final");
		if(!isUnitImpl && !isUnitController && !isWebListener && classModified){
			detectComponent(unitImpl)
			detectDefault(unitImpl)
			return
		} 
		
	}
	
	def detectDefault(CompilationUnitImpl unitImpl) {
		detectComponent(unitImpl)
		detectOperationInterface(unitImpl)
		getAllPublicMethods(unitImpl).forEach[m|detectProvidedInterface(unitImpl,m)]
		getFields(unitImpl).forEach[f|if(isFieldAbstract(f)) detectRequiredInterface(unitImpl, f)]
	}
	
}
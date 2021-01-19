package org.palladiosimulator.somox.analyzer.rules.jax_rs

import java.util.stream.Collectors
import org.emftext.language.java.containers.impl.CompilationUnitImpl
import org.palladiosimulator.somox.analyzer.rules.engine.IRule

import static org.palladiosimulator.somox.analyzer.rules.engine.PCMDetectorSimple.*
import static org.palladiosimulator.somox.analyzer.rules.engine.RuleHelper.*

class JaxRSRules implements IRule{
	
	override processRules(CompilationUnitImpl unitImpl) {

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
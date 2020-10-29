package org.palladiosimulator.somox.analyzer.rules.spring

import org.palladiosimulator.somox.analyzer.rules.engine.IRule

import static org.palladiosimulator.somox.analyzer.rules.engine.RuleHelper.*
import org.emftext.language.java.containers.impl.CompilationUnitImpl
import org.emftext.language.java.members.Method
import org.emftext.language.java.members.Field
import org.emftext.language.java.parameters.Parameter
import static org.palladiosimulator.somox.analyzer.rules.engine.PCMDetectorSimple.*

class SpringRules implements IRule{
	
	override processRules(CompilationUnitImpl unitImpl) {
		
		val isAbstract = isAbstraction(unitImpl)
		
		// Component detection
		val isComponent = !isAbstract && isUnitAnnotatedWithName(unitImpl, "Component","Service","Controller","RestController")
		
		if(isComponent) detectComponent(unitImpl)
		
		// Component Detection for Spring Repository
		if((isUnitAnnotatedWithName(unitImpl,"FeignClient","Repository") || (isUnitNamedWith(unitImpl, "Repository")) && isAbstract)) {
			detectComponent(unitImpl) 
			detectOperationInterface(unitImpl)
			getMethods(unitImpl).forEach[m|detectProvidedInterface(unitImpl, m)]
		}
		
		// Operation Interface Detection
		// if implementing 1 interface
		var inFs = getAllInterfaces(unitImpl)
		val isImplementingOne = inFs.size==1
		if(isComponent && isImplementingOne) {
			var firstIn = inFs.get(0)
			detectOperationInterface(firstIn)
			for(Method m: getMethods(firstIn)){
				detectProvidedInterface(unitImpl, firstIn, m)
			}
		}
			
		// not implementing 1 interface => Controller class with annotations on methods  
		if(isComponent && !isImplementingOne) 
			for(Method m: getMethods(unitImpl)){
				val annoWithName = isMethodAnnotatedWithName(m,"RequestMapping","GetMapping","PutMapping","PostMapping","DeleteMapping","PatchMapping")
				if(annoWithName || (!annoWithName && m.public)) 
					detectProvidedInterface(unitImpl, m) detectOperationInterface(unitImpl)
			}
				
		
		// Required Role Detection
		if(isComponent){
			
			// field injection
			for(Field f: getFields(unitImpl)){
				val annotated = isFieldAnnotatedWithName(f, "Autowired")
				if(annotated){
					detectRequiredInterface(unitImpl, f)
				}
				val abstr = isFieldAbstract(f)
				val modi = isFieldModifiedExactlyWith(f, "private","final")
				if(!annotated && abstr && modi){
					detectRequiredInterface(unitImpl, f)
				}
			}
			
			// setter injection
			for(Method m: getMethods(unitImpl)){
				if(isMethodAnnotatedWithName(m, "Autowired")){
					for(Parameter p: m.parameters){
						// if abstract type
						val isParaAbstract = isParameterAbstract(p)
						if(isParaAbstract){
							detectRequiredInterface(unitImpl, p)
						}
						// if type is component
						if(!isParaAbstract && isParameterAClassAnnotatedWith(p,"Component","Service","Controller")){
							detectRequiredInterface(unitImpl, p)
						}
					}
				}
			}
			
			// constructor injection
			getConstructors(unitImpl).forEach[constructor | {
				if(isConstructorAnnotatedWithName(constructor,"Autowired")){
					constructor.parameters.forEach[para | {
					if(isParameterAbstract(para) || isParameterAClassAnnotatedWith(para,"Component","Service","Controller")){
						detectRequiredInterface(unitImpl, para)
					}
					if(!isParameterAbstract(para) && isParameterAnnotatedWith(para,"LoadBalanced")){
						detectRequiredInterface(unitImpl, para)
					} 
				}]
				}
			}];
		}
	}
}

# Abbildung von Java-Methoden und –Aufrufen in einer Art Aktivitätsdiagramm

If you are familiar with the background of PCM and fluent interfaces, jump directly to [Motivation](#motivation).

## Introduction

This project is based on the Palladio Component Model, which is a modelling concept developed at the software quality and design institute of KIT.

Was ist PCM?  
Was ist ein SEFF? Was ist ein RDSEFF? (Eine Art Aktivitätsdiagramm)  
Motivation für unser Projekt (Reverse Engineering) -> Vom Code zum Model für Analyse, Refactoring, Featureimplementierung, Wartung  

Was haben wir in unserem Projekt gemacht?   
(MoDisCoo zu JDT, zunächst normale Konstruktoren danach Fluent API, Was ist die Fluent API, )

Wie kann man unser Projekt aufsetzen?  (Getting started etc.)  

Was sind und waren Probleme in unserer Implementierung?   s
Was sind die Limitierungen in unserem  Projekt? (Was haben wir gemacht und was haben wir für zukünftige Arbeiten offen gelassen)? 


### Palladio Component Model (PCM)
Palladio is a software architecture simulation approach which analyses your software at the model level for performance bottlenecks, scalability issues, reliability threats, and allows for a subsequent optimization.
The Palladio Component Model (PCM) is one of the core assets of the Palladio approach. It is designed to enable early performance, reliability, maintainability, and cost predictions for software architectures and is aligned with a component-based software development process. The PCM is implemented using the Eclipse Modeling Framework (EMF). Visit the [Homepage](https://www.palladio-simulator.com) for more information.

#### Creation of PCM Models
The PCM is realized as an Eclipse Plugin. The creation of the different palladio models is fairly similar. As an example, the creation of a repository is shown. Creating PCM repository model is fairly simple using the diagram editor. The image below shows the graphical diagram editor with a simple repository model. The palette on the right provides the user with all the model elements. Selecting an element and clicking onto the model plane creates the basic model elements. Additionally, most elements can be further edited using the ```Properties``` view.
![PCM Repository Model: Diagram Editor](documentation/pcm_repo_model_diagram.png "PCM Repository Model: Diagram Editor")
The tree view of the repository model editor shows the model elements in their structure. New model elements, i.e. children of the tree branches, can be created by right clicking on a tree node. The editor shows the selection that is sensible at this point in the model structure. Furthermore, the tree view shows the 3 repositories that are imported by default: primitive types, failure types and a resource repository. Their elements can be used freely.
![PCM Repository Model: Tree Editor](documentation/pcm_repo_model_tree.png "PCM Repository Model: Tree Editor")
s
#### Fluent Interfaces
A fluent interface, also called fluent API, is a certain style of interface which is especially useful for creating and manipulating objects. The goal of a fluent interface is to increase code legibility by creating a domain-specific language (DSL). Its design relies on method chaining to implement method cascading. Thus, each method usually returns this, i.e. the manipulated object itself. Furthermore, the chaining methods are supposed to "flow like a natural sentence" (hence the name "fluent interface"), automatically guiding the user and giving a natural feeling of the available features.

Prominent examples of fluent interfaces are the [Java Stream API](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html) and [JMock](http://jmock.org).

## Motivation

However, the backend of PCM provides not just one but around 10 different factories, that are needed to create a PCM repository model and 5 needed for the system model.

Although the allocation and resource environment model each only require a single factory to be created, a fluent api exists for their creation. 

This ensures that all models can be created in a similar fashion. Searching for the correct factory for the different model elements and the method names that sets the desired properties is not user friendly. Especially, because the model objects offer more method proposals than sensible for creating a repository model.

The fluent API has the goal not only to reduce the overhead of creating a model programmatically but also to provide a clear frame that guides the user through the different steps of the model creation, naturally indicating which step comes next. Consequently, the API is easy to use even for unexperienced users.


## How to use the Fluent API Model Generator

The easiest way to use the API as an end user is to install it in Eclipse via the provided update page:
* https://updatesite.palladio-simulator.com/palladio-addons-fluentapimodelgenerator/nightly/

### Create Models
The different models each have their own factory. See the links below to get an introduction to each of the models.
- [Repository](documentation/repository.md)
- [System](documentation/system.md)
- [Resource Environment](documentation/resourceEnvironment.md)
- [Allocation](documentation/allocation.md)
- [Usage Model](documentation/usagemodel.md)

## Objectives: 
- To gain better understanding of the system by visualisation
- To provide fast and parallel view (UML Diagram) next to the tree-/code-editor in Eclipse
- To replace the existing PlantUML diagram by a new simplified one
- Transformation of elements from Palladio models to UML elements programatically

<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites



1. Install [Eclipse 2021-12](https://www.eclipse.org/downloads/packages/release/2021-12/r/eclipse-modeling-tools)
2. Open Eclipse and navigate to Help > Install New Software...
3. Install [Palladio Build Updatesite](https://updatesite.palladio-simulator.com/palladio-build-updatesite/nightly/), [Palladio Core Commons](https://updatesite.palladio-simulator.com/palladio-core-commons/nightly/), [Palladio Reverse Engineering Java](https://updatesite.palladio-simulator.com/palladio-reverseengineering-java/nightly/) and [Palladio FluentAPI Model Generator](https://updatesite.palladio-simulator.com/palladio-addons-fluentapimodelgenerator/nightly/)


### Installation

_Below is an example of how you can instruct your audience on installing and setting up your app. This template doesn't rely on any external dependencies or services._

1. Clone the repository to a directory of your choice

   ```sh
   https:
   git clone https://github.com/PalladioSimulator/Palladio-ReverseEngineering-SoMoX.git
   
   ssh:
   git clone git@github.com:PalladioSimulator/Palladio-ReverseEngineering-SoMoX.git
   ```
   
2. Open Eclipse and create a new workspace for the project.
3. Select File > Open Projects From File System...
4. Press on "Directory" and select the cloned repository directory in the file system.
5. Several projects get listed. Select all of them.
6. Press on "Finish".

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->
## Usage

Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.

_For more examples, please refer to the [Documentation](https://example.com)_

<p align="right">(<a href="#top">back to top</a>)</p>


## Limitations:
- We were only implementing Guarded Branch Transitions and omitting Probabilistic Branch Transitions, because we cannot make any reasonable guess about the probability of the different branches.
- We have started exploring the Assignment Statement and its conversion to a SetVariableAction element in the SEFF context. As we found out during our exploration, the SetVariableAction element is used for functions which have a return statement. We stopped further exploration but leave the initial code at the ExpressionStatement visit function.

## Testing
For the usage model JUnit testing is available. The tests and a bigger example for can be found in [```FluentUsageModelFactoryTest```](tests/org.palladiosimulator.generator.fluent.test/src/org/palladiosimulator/generator/fluent/usagemodel/factory/FluentUsageModelFactoryTest.java). In future versions the examples of the other models can be written into unit tests and saved under [```Tests```](tests/org.palladiosimulator.generator.fluent.test/src/org/palladiosimulator/generator/fluent).

## Miscellaneous
See the Palladio-Jira for further improvements to this API:
* https://jira.palladio-simulator.com/browse/COMMONS-30


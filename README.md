# Abbildung von Java-Methoden und –Aufrufen in einer Art Aktivitätsdiagramm

## Introduction
This project is based on the Palladio Component Model (PCM), which is a modeling concept developed at the software quality and design institute of KIT. It is used together with a Service Effect Specification (Seff) and the Eclipse AST parser to create an Abstraction of passed code and modeling of internal behavior. It can be used to describe relationships between provided and required components and models their input and output variables. In future work, it can even be used to analyze runtime variables like CPU / HDD demands.

If you are familiar with the background of PCM, Seff, AST and fluent interfaces, jump directly to [Motivation](#motivation).

### Palladio Component Model (PCM)
Palladio is a software architecture simulation approach which analyses your software at the model level for performance bottlenecks, scalability issues, reliability threats, and allows for a subsequent optimization.
The Palladio Component Model (PCM) is one of the core assets of the Palladio approach. It is designed to enable early performance, reliability, maintainability, and cost predictions for software architectures and is aligned with a component-based software development process. The PCM is implemented using the Eclipse Modeling Framework (EMF). Visit the [Homepage](https://www.palladio-simulator.com) for more information.

### Service Effect Specification (Seff)
Was ist ein SEFF? Was ist ein RDSEFF? (Eine Art Aktivitätsdiagramm)

#### Creation of PCM Models
The PCM is realized as an Eclipse Plugin. The creation of the different palladio models is fairly similar. As an example, the creation of a repository is shown. Creating PCM repository model is fairly simple using the diagram editor. The image below shows the graphical diagram editor with a simple repository model. The palette on the right provides the user with all the model elements. Selecting an element and clicking onto the model plane creates the basic model elements. Additionally, most elements can be further edited using the ```Properties``` view.
![PCM Repository Model: Diagram Editor](documentation/pcm_repo_model_diagram.png "PCM Repository Model: Diagram Editor")
The tree view of the repository model editor shows the model elements in their structure. New model elements, i.e. children of the tree branches, can be created by right clicking on a tree node. The editor shows the selection that is sensible at this point in the model structure. Furthermore, the tree view shows the 3 repositories that are imported by default: primitive types, failure types and a resource repository. Their elements can be used freely.
![PCM Repository Model: Tree Editor](documentation/pcm_repo_model_tree.png "PCM Repository Model: Tree Editor")

#### Fluent Interfaces
A fluent interface, also called fluent API, is a certain style of interface which is especially useful for creating and manipulating objects. The goal of a fluent interface is to increase code legibility by creating a domain-specific language (DSL). Its design relies on method chaining to implement method cascading, thus, each method usually returns the manipulated object itself (a this pointer). Furthermore, the chaining methods are supposed to "flow like a natural sentence" (hence the name "fluent interface"), automatically guiding the user and giving a natural feeling of the available features. The fluent API has the goal to not only reduce the overhead of creating a model programmatically but also to provide a clear frame that guides the user through the different steps of the model creation, naturally indicating which step comes next. Consequently, the API is easy to use even for unexperienced users and is very helpfull in combination with modern IDEs, due to their code completion highlighting.

Prominent examples of fluent interfaces are the [Java Stream API](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html) and [JMock](http://jmock.org).

## Motivation
Analyzing code can be very time-consuming and exhausting. Sometimes one "if" can make the difference between a performant implementation and a slow one, but if you are not common with the code it's hard to find the excact location. Thats why an automated, eazy readable PCM graph from the code would be a perfekt fit for every programmer. It can be used for reverse engineering purposes to find bugs like unaccessable paths or to model the whole program and find all internal and external connections. It eases the learning process for new emlopees and enables a clean codebase with fast to find refactors or analization of resource demands.
This feature was not eazy to implement, since the backend of PCM provides not just one, but around 10 different factories that are all needed to create a PCM repository and system model. A fluent API is a much-appreciated help. Although the allocation and resource environment model each only require a single factory the code can get very messy for other parts like the creation of actions and the assignment of variables without one. The Fluent API also ensures that all models can be created similarly. Searching for the correct factory for the different model elements and the method names that set the desired properties is not user-friendly, especially, since the model objects offer more method proposals than required for creating a repository model. Thankfully, a fluent-API was created in previous work and was ready to use for this implementation.

## How to use the Fluent API Model Generator
The easiest way to use the API as an end user is to install it in Eclipse via the provided update page:
* https://updatesite.palladio-simulator.com/palladio-addons-fluentapimodelgenerator/nightly/

and follow the the instructions under
* https://github.com/PalladioSimulator/Palladio-Addons-FluentApiModelGenerator/blob/master/documentation

### Create Models
The different models each have their own factory. See the links below to get an introduction to each of the models.
- [Repository](https://github.com/PalladioSimulator/Palladio-Addons-FluentApiModelGenerator/blob/master/documentation/repository.md)
- [System](https://github.com/PalladioSimulator/Palladio-Addons-FluentApiModelGenerator/blob/master/documentation/system.md)
- [Resource Environment](https://github.com/PalladioSimulator/Palladio-Addons-FluentApiModelGenerator/blob/master/documentation/resourceEnvironment.md)
- [Allocation](https://github.com/PalladioSimulator/Palladio-Addons-FluentApiModelGenerator/blob/master/documentation/allocation.md)
- [Usage Model](https://github.com/PalladioSimulator/Palladio-Addons-FluentApiModelGenerator/blob/master/documentation/usagemodel.md)

## Objectives: 
We focused on different objectives in this work to help the user:
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
	1. Always accept the warnings and install the software anyway
5. After a final restart all dependencies are installed and Eclipse is ready to go importing the project files

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

Now you are ready to see the implementation and test the available solution.

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->
## Usage

Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.

_For more examples, please refer to the [Documentation](https://example.com)_

<p align="right">(<a href="#top">back to top</a>)</p>


## Limitations:
- We were only implementing Guarded Branch Transitions and omitting Probabilistic Branch Transitions, because we cannot make any reasonable guess about the probability of the different branches.
- We have started exploring the Assignment Statement and its conversion to a SetVariableAction element in the SEFF context. As we found out during our exploration, the SetVariableAction element is used for functions which have a return statement. We stopped further exploration but leave the initial code at the ExpressionStatement visit function
- 

Was sind und waren Probleme in unserer Implementierung?
Was sind die Limitierungen in unserem  Projekt? (Was haben wir gemacht und was haben wir für zukünftige Arbeiten offen gelassen)?

## Testing
For the usage model JUnit testing is available. The tests and a bigger example for can be found in [```FluentUsageModelFactoryTest```](tests/org.palladiosimulator.generator.fluent.test/src/org/palladiosimulator/generator/fluent/usagemodel/factory/FluentUsageModelFactoryTest.java). In future versions the examples of the other models can be written into unit tests and saved under [```Tests```](tests/org.palladiosimulator.generator.fluent.test/src/org/palladiosimulator/generator/fluent).

## Miscellaneous
See the Palladio-Jira for further improvements to this API:
* https://jira.palladio-simulator.com/browse/COMMONS-30


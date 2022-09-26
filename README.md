# Palladio-ReverseEngineering-SoMoX

## Introduction
This project assists the reverse engineering process by transforming the input source code into a Palladio Component Model artifact, the Service Effect Specification (SEFF). The project is based on the Palladio Component Model (PCM), which is a modeling concept developed at the Software Quality and Design (SQD) institute at KIT. It is used together with the Service Effect Specification (SEFF) and the Eclipse JDT AST parser to create an abstract model of the source code. It can be used to describe relationships between provided and required components and models their input and output variables.

If you are familiar with the background of PCM, SEFF, AST, and fluent interfaces, jump directly to [Objectives](#Objectives).  
If you are familiar with the project, jump directly to [Usage](#usage).

## Motivation & Background
Most of the time a forward engineering approach is used when developing software. After the definition of the system specification, a software design is created. With this software design, the source code can be built in a structured way. Some legacy projects do not have any kind of specification or design documentation available. Reverse engineering is a very interesting approach in this scenario because it tries to build higher abstraction models of the available source code. Code analysis can be a very time-consuming and exhausting task. Single condition statements can make the difference between a performant or slow implementation. If the developer is not common with the code it's hard to find the exact location. That's why an automated, easily readable diagram from the code would be a perfect solution for developers. 

It can be used for reverse engineering purposes to retrieve the original design and find all connections between components. It eases the feature development and improvement process in legacy systems and enables a clean code base with fast-to-find refactoring options or analysis of resource demands.  

### Palladio Component Model (PCM)
Palladio is a software architecture simulation approach that analyzes a software project at the model level for performance bottlenecks, issues with scalability, reliability threats, and allows for subsequent optimization. The approach needs different artifacts as input to perform the simulation. Artifacts can be the specification of the components, the composition of the components, or a specification of the behavior of the functions inside the components. This behavior specification is known as Service Effect Specification.

### Service Effect Specification (SEFF)
The Service Effect Specification (SEFF) is a kind of activity diagram to model abstraction of the component and function behavior. It offers several ways to specify the type of action elements of the diagram. For the simulation, the actions can be enriched with resource allocation specifications such as CPU or HDD usage.

### AST
Eclipse provides a way to access and manipulate Java source code via the Java Development Tools (JDT) API. The API can either be accessed via the Java Model or via the Abstract Syntax Tree (AST). For our implementation, we choose to follow the AST path, since it matches the MoDisco / JaMoPP models described [below](#modiscojamopp-version).   

The AST is a detailed tree representation of the Java source code which can be traversed via the [visitor Pattern](#visitor-pattern). It consists of a root node (ICompilationUnit) and many different child nodes which are built upon each other.  
The AST has a structure similar to  
![AST: Structure](doc/ASTStructure.png "AST: Typical AST structure")
where each MethodDeclaration describes a function of the root class and each Statement describes a different code snippet. The AST can be enriched with additional information like TypeDeclarations to model the affiliated classes which we choose not to do (see [Limitations & Future Work](#limitations-&-future-work)).  

### Visitor Pattern
The visitor pattern is a commonly used behavioral pattern, which provides the possibility to separate an algorithm from an object structure on which it operates. It is a perfect match to traverse the AST since it opens the possibility to read and process parts of the object structure without directly modifying it.   

The AST provides an interface where visitors can be accepted and calls their visit function in a recursive cycle. To react to the different types of nodes the visit function has to be overloaded ensuring that each type and its children are processed correctly. Since it's not always wanted to process each child till the very deep end a return value can be set where true traverses the children and false stops the traversing of the children and returns to the parent.  
This pattern enabled us to build a very lightweight code structure and helped to meet all Objectives.

## Objectives:
The project focused on different objectives to help the user:
- Transformation of Java source code to a Palladio Component Model repository with SEFF elements
- Understandable source code structures to enable changes to the original code and further development
- Preprocessing the source code for runtime analysis via PCM

Since there were previous implementations of this project the objectives always stayed the same, but this version updated the code base to match newer Java versions. To stay compatible with the build process of other versions we also created an ` IBlackboardInteractingJob <Blackboard<Object>> ` called Ast2SeffJob. It uses a similar visitor pattern to the other versions but has direct access to the code via the JDT Ast pattern. Further information can be found in [section JDT Version](#jdt-version).

### MoDisco/JaMoPP Version
This project is a further development from the MoDisco and JaMoPP versions. It was implemented based on the JaMopp version, which was based on the MoDisco version. To fully understand our implementation we first describe the differences between MoDisco and JaMoPP.  
The MoDisco version was the first version of the reverse engineering project and was implemented by Klaus Kogmann. It was very clean structured since it had comprehensible mapping rules and was able to directly map code blocks to SEFF actions. The parsing process took Java code and parsed it with the MoDisco parser into the MoDisco model, made processing via the visitor pattern in the GAST2SEFF class, and output different SEFF objects in the XML format. Since the support for the parser stopped and it was not able to parse new structured Java Code the development of the MoDisco version stopped and a new version (JaMoPP) was implemented.   


The JaMoPP version had two different milestones. First, it was implemented with the default JaMoPP parser by Michael Langhammer, but then again the support for the parser stopped and it was not able to parse new structured Java Code. Instead of recreating everything the institute decided to swap the JaMoPP parser for the JDT parser and created a translation between the two models. This text focuses on the JDT parser version.  


Since the JaMopp model was different than the MoDisco model a bit of preprocessing was needed to create a similar matching as before. It was not able to directly differentiate between Internal and External Actions, therefore a big library named "SourceCodeDecorator" was created to help with this process and to make some runtime analysis. The parsing process took Java code and parsed it with the JDT parser. The JDT model was then translated into the JaMoPP model and preprocessed, then parsed into an adjusted GAST2SEFF class where a visitor pattern processed the JaMoPP model to output different SEFFs as XML. The process was therefore very similar to the MoDisco version, but the preprocessing made the whole code very unreadable and induced an unneeded extra step by transforming the code from the JDT AST model to the JaMoPP model.

### JDT Version
The Eclipse Java Development Tools (JDT) offer functionality to traverse Java source code, enriched with additional information like type resolutions and package affiliations. This project tries a new approach by building the visitor pattern with direct reference to the JDT library, omitting the JaMoPP dependency of the previous project implementation. This softens the stiff JaMoPP implementation and opens the possibility to abandon the whole "SourceCodeDecorator". It makes the code cleaner again without any restrictions since the JDT model has full control over which Actions are Internal / External Actions.  
The parsing process takes Java code and parses it with the JDT parser, which is directly traversed by the AST2SEFF visitor pattern to output different SEFFs as XML. It is more similar to the MoDisco version again.

### Comparison Of Approaches
Since this project has a long history with different versions we created a table to compare them all to each other. If you are familiar with one of the versions, this can give a good overview of how the others were structured. Keep in mind that this table only represents what the visitor got as input and does not reflect the preprocessing done in JaMoPP (e.g. gathering For/EnhancedFor/While to Loop beforehand). SetVariableAction and WithInputVariable were recently added to the JDT version and therefore there is no JaMoPP / MoDisco version for it.

| **SEFF Element**         | **MoDisco Version**                                         | **JaMoPP Version**          | **JDT Version**                                           |
|--------------------------|-------------------------------------------------------------|-----------------------------|-----------------------------------------------------------|
| LoopAction               | ForStatement, EnhancedForStatement, WhileStatement          | LoopStatement               | ForStatement, EnhancedForStatement, WhileStatement        |
| BranchAction             | SwitchStatement, TryStatement, IfStatement, IfElseStatement | Switch, TryBlock, Condition | SwitchStatement, TryStatement, IfStatement, ElseStatement |
| AcquireAction            | SynchronizedStatement                                       | SynchronizedBlock           | SynchronizedStatement                                     |
| InternalCallAction       | ExpressionStatement                                         | CallStatement               | ExpressionStatement                                       |
| ExternalCallAction       | ExpressionStatement                                         | CallStatement               | ExpressionStatement                                       |
| InternalAction           | ExpressionStatement                                         | ThisStatement               | ExpressionStatement                                       |
| SetVariableAction        | -                                                           | -                           | ReturnStatement                                           |
| WithInputVariable        | -                                                           | -                           | ExpressionStatement                                       |

### FluentAPI
Since the creation of PCM models can get complex, the [FluentAPI](https://github.com/PalladioSimulator/Palladio-Addons-FluentApiModelGenerator) library was added to this project. It enables the user to focus on creating functional code and takes away the burden of creating PCM objects and referring them to each other. The FluentAPI also ensures that all models can be created similarly and therefore increases the readability of the code. Searching for the correct factory for the different model elements and the method names that set the desired properties is not user-friendly, especially, since the model objects offer more method proposals than required for creating a repository model. It also opens the possibility to read the code like a natural sentence and provides an uniform interface where changes to the PCM meta-model can be made without changing every appearance in our code. Thankfully, a FluentAPI was created in previous work and was ready to use for this implementation.

<!-- GETTING STARTED -->
## Getting Started
Now that the foundation of our project is clear we can jump right into it. To get a local copy up and running follow these simple example steps.

### Prerequisites

1. Install Java (jdk-11)
2. Install Maven (latest)
3. Install [Eclipse 2021-12](https://www.eclipse.org/downloads/packages/release/2021-12/r/eclipse-modeling-tools)
4. Open Eclipse and navigate to Help > Install New Software...
5. Install [Palladio Build Updatesite](https://updatesite.palladio-simulator.com/palladio-build-updatesite/nightly/), [Palladio Core Commons](https://updatesite.palladio-simulator.com/palladio-core-commons/nightly/), [Palladio Reverse Engineering Java](https://updatesite.palladio-simulator.com/palladio-reverseengineering-java/nightly/) and [Palladio FluentAPI Model Generator](https://updatesite.palladio-simulator.com/palladio-addons-fluentapimodelgenerator/nightly/)
	1. Always accept the warnings and install the software anyway
7. After a final restart all dependencies are installed and Eclipse is ready to go importing the project files

### Installation

Below is an example of how this project can be installed and set up. Be sure to meet all [Prerequisites](#Prerequisites), or else the installation might fail.

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

Now you are ready to review, test, and develop the implementation.

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->

## Usage
To analyze the code it first has to be converted into an [AST Representation](https://www.vogella.com/tutorials/EclipseJDT/article.html). To do so create a parser first and parse all wanted files (or directories like in the example below). What the additional settings do can be read in the [Eclipse help Platform Documentation](https://help.eclipse.org/latest/index.jsp?topic=%2Forg.eclipse.jdt.doc.isv%2Freference%2Fapi%2Forg%2Feclipse%2Fjdt%2Fcore%2Fdom%2FCompilationUnit.html).

The full implementation of this simple case can also be found in our [Tests](tests/org.palladiosimulator.somox.ast2seff.test/src/org/palladiosimulator/somox/ast2seff/Ast2SeffTest.java).

First, the parser needs to be set up:

  ```sh
    String  javaCoreVersion = JavaCore.latestSupportedJavaVersion();
    final ASTParser parser = ASTParser.newParser(AST.getJLSLatest());
    parser.setResolveBindings(true);
    parser.setBindingsRecovery(true);
    parser.setStatementsRecovery(true);
    parser.setCompilerOptions(Map.of(JavaCore.COMPILER_SOURCE, javaCoreVersion, JavaCore.COMPILER_COMPLIANCE,
            javaCoreVersion, JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, javaCoreVersion));
  ```

Since we want to parse ".java" files and have environment information from the ".jar" files we created a helper function "get Entries":

  ```sh
    private String[] getEntries(Path dir, String suffix) {
      try (Stream<Path> paths = Files.walk(dir)) {
        return paths
          .filter(path -> Files.isRegularFile(path) && path.getFileName().toString().toLowerCase().endsWith(suffix))
          .map(Path::toAbsolutePath).map(Path::normalize).map(Path::toString).toArray(i -> new String[i]);
      } catch (final IOException e) {
        e.printStackTrace();
        return new String[0];
      }
    }
  ```

Now that the parser is set up and and the helper function is defined we can start parsing our [directory](tests/org.palladiosimulator.somox.ast2seff.test/src/org/palladiosimulator/somox/ast2seff/res/) and create a [Compilation Unit](https://help.eclipse.org/latest/index.jsp?topic=%2Forg.eclipse.jdt.doc.isv%2Freference%2Fapi%2Forg%2Feclipse%2Fjdt%2Fcore%2Fdom%2FCompilationUnit.html) for each file.

  ```sh
    Path dir = Path.of("src/org/palladiosimulator/somox/ast2seff/res");
    String[] classpathEntries = getEntries(dir, ".jar");
    final String[] sources = getEntries(dir, ".java");
    final String[] encodings = new String[sources.length];
    Arrays.fill(encodings,  StandardCharsets.UTF_8.toString());
    final Map<String, CompilationUnit> compilationUnits = new HashMap<>();
    try {
      parser.setEnvironment(classpathEntries, new String[0], new String[0], true);
      parser.createASTs(sources, encodings, new String[0], new FileASTRequestor() {
        @Override
        public void acceptAST(final String sourceFilePath, final CompilationUnit ast) {
          compilationUnits.put(sourceFilePath, ast);
        }
      }, new NullProgressMonitor());
    } catch (IllegalArgumentException | IllegalStateException e) {
      e.printStackTrace();
    }
    return compilationUnits;
  ```

Since additional filtering like the exclusion of private functions is wanted we choose to not pass the whole [CompilationUnit-Objects](https://help.eclipse.org/latest/index.jsp?topic=%2Forg.eclipse.jdt.doc.isv%2Freference%2Fapi%2Forg%2Feclipse%2Fjdt%2Fcore%2Fdom%2FCompilationUnit.html) to the ast2SeffJob, instead we pass a mapping of classNames to methodDeclarations, which we defined as [MethodBundlePairs](bundles/org.palladiosimulator.somox.ast2seff/src/org/palladiosimulator/somox/ast2seff/models/MethodBundlePair.java).

  ```sh
    Map<String, List<MethodBundlePair>> bundleName2methodAssociationMap = new HashMap<String, List<MethodBundlePair>>();
    
    for (var entry : compUnitMap.entrySet()) {
      List<MethodDeclaration> methodDeclarations = MethodDeclarationFinder.perform(entry.getValue());
      for (MethodDeclaration methodDeclaration : methodDeclarations) {
        List<IExtendedModifier> modifierList = (List<IExtendedModifier>) methodDeclaration.modifiers();

        // Generate a seff for public methods only
        IExtendedModifier firstModifier = modifierList.get(0);
        if (firstModifier.isModifier()) {
          Modifier modifier = (Modifier) firstModifier;
          if (modifier.isPublic()) {
            TypeDeclaration typeDeclaration = (TypeDeclaration) methodDeclaration.getParent();
            String className = typeDeclaration.getName().toString();
            if (bundleName2methodAssociationMap.containsKey(className)) {
              bundleName2methodAssociationMap.get(className).add(new MethodBundlePair(className, methodDeclaration)); 
            } else {
              List<MethodBundlePair> methodAssociationList = new ArrayList<MethodBundlePair>();
              methodAssociationList.add(new MethodBundlePair(className, methodDeclaration));
              bundleName2methodAssociationMap.put(className, methodAssociationList); 
            }
          }
        }
      }
    }
  ```

Now that the map is set up, the already developed blackboard implementation for the workflow engine is used to store and fetch the generated element. The conversion from AST to SEFF starts when ` ast2SeffJob.execute() ` is called.

  ```sh
    Ast2SeffJob ast2SeffJob = new Ast2SeffJob();
    Blackboard<Object> blackboard = new Blackboard<>();
    blackboard.addPartition("bundleName2methodAssociationMap", bundleName2methodAssociationMap);
    ast2SeffJob.setBlackboard(blackboard);
    NullProgressMonitor progressMonitor = new NullProgressMonitor();
    ast2SeffJob.execute(progressMonitor);
  ```

<p align="right">(<a href="#top">back to top</a>)</p>


## Limitations & Future Work
- Our current model only implemented Guarded Branch Transitions and omitted Probabilistic Branch Transitions, since we cannot make any reasonable guesses about the probability of the different branches. In future work, a method to approximate the probabilities should be added.
- We have started exploring the Assignment Statement and its conversion to a SetVariableAction element in the SEFF context. As we found out during our exploration, the SetVariableAction element is used for functions that have a return statement. We stopped further exploration but left the initial code at the ExpressionStatement visit function for future work.
- Our parser currently creates one ICompilationUnit for each parsed file, omitting the class structures inside the file. This limits the possible parsed code to one class per file since all methods get parsed into the matching ICompilationUnit. In future work, a more precise abstraction could be made where either more ICompilationUnit gets created or the TypeDeclaration (in this case the class structures) gets included.
- Currently, there is no resource usage information addition for CPU or HDD demands. This was previously implemented in the JaMoPP version, but since we started from scratch again, not covered in this version. With more analysis of the JaMoPP version it should be easy to read it into the JDT version.
- Our internalCallActions are only implemented as a method inlining with a depth of 1. This means that if an InternalCallAction is called multiple times it's not referred to each other, but instead created multiple times. If methods refer to each other (and the depth is greater than 1) they are shown as InternalActions.
- Expression Statements inside if statements could be modeled in further development. Therefore you need to find out if there is a method invocation inside the if condition and model it as InternalAction, InternalCallAction, or ExternalCallAction.

## Testing
The JUnit Framework was used for quality assurance in this project. JUnit has good integration into the Eclipse IDE, therefore this decision was straightforward. For the usage model, JUnit tests are available. The tests and a bigger example of the overall usage with test classes can be found in the [test folder](./tests/org.palladiosimulator.somox.ast2seff.test) of the project.

A simple test strategy was followed to catch the general test cases for each statement visitor function.  
The four standard test cases are:
1. Test an empty statement
2. Test the statement with an expression statement inside
3. Test the statement with the same statement inside
4. Test the statement with another statement inside (loop or branch statement)  

Additional test cases for specific attributes for the different kinds of statements were tested when needed. 



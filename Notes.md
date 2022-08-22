# Notes

## Meetings

### 09.05

- Einführung in das Projekt und in die Thematik
  
Noitzen
- Wie funktionieren die Visitatoren?
- Abbildungsregeln reparieren und optimieren ist die Praktikumsaufgabe
- Wieso funktioniert das gerade nicht? Was müssen wir machen, dass es wieder funktioniert?
- Abbildung von Java-Model zu SEFF
- Abstrakter Syntax Baum hat die gesamten Informationen, deswegen bietet sich das Vistor Pattern an um den Baum zu erkunden/iterieren
- Wie kommen wir an das Java Model?

Aufgaben:
- Installation die Technik-Infrastruktur
- Repository ausbuchen (seff-branch funktioniert, main-branch funktioniert nicht)
- maven clean verify im Root-Verzeichnis um das Projekt zu bauen

Wichtige Links:
- https://www.palladio-simulator.com/tools/tutorials/
- https://sdqweb.ipd.kit.edu/wiki/PCM_Installation#PCM_Nightly
- https://www.eclipse.org/downloads/packages/release/2021-09/r/eclipse-modeling-tools
- https://updatesite.palladio-simulator.com/palladio-reverseengineering-java/nightly/

### 16.05

Bearbeitete Aufgaben:
- Installation von Eclipse mit dem Palladio Nightly Build
- Einbindung des Palladio Reverse Engineering Plugins
- Bestehenden Code lesen und verstehen
- Erstellung eines Miro-Boards mit ersten Diagrammen für das eigenes Verständnis

Fragen:
- Ist es korrekt, dass Palladio Reverse Engineering Plugins einzubinden?
- Haben wir ein richtiges Verständnis von der Aufgabe?
- Ist unser Verständnis des Projekts korrekt?

Neue Aufgaben:
- Dokumentieren was ging mit dem alten Model nicht und was geht mit dem neuen Model
- Anfangen bei Root ändern von JaMoPP auf JDT
- Herausschreiben, welche vom Source Code Decorator kommen
- Datentypen in Visitatoren austauschen (mit kleinen beginnen)

Notizen zum Meeting:
- Fluent API Dependency könnte in Zukunft interessant sein
  - Technisch gleiche Objekte, nur Nutzerfreundlicher die Objekte im Code darzustellen
- Root später loslösen, sodass man nur eine Liste von Compilation Units hat
- Mit instance of prüfen was für eine CompilationUnit das ist
  - Dann müssen wir das casten
- Source Code Decorator komplett loskommen (ersetzt durch Hashmap oder Listen)
  - Was steht da drin, wie bilden wir die Informationen ab?
    - Welche Methode gehört zu welcher Komponente?
  - Kann in Zukunft über das Blackboard geholt werden
- Typen aus dem Model können wir nicht so schnell ändern, falls dort Informationen rausgezogen werden
- Schauen das kein .emftext mehr drin steht


### 23.05

Bearbeitete Aufgaben:
- Root.java analysiert und umgeschrieben
- SwitchStatementHelper.java analysiert und umgeschrieben
- Erste Testklassen und Testfälle angelegt

Fragen:
- Uns fällt es schwer einen Startpunkt zu sehen. Wo sollten wir beginnen?
  - Wo sollen wir uns entlang hangeln?
- Switch Model von EMF? Brauchen wir ein eigenes Switch-Konzept?
- Was beinhaltet Root? Alle Methoden? Alle Pakete? Was wird hier übergeben?
  - Wahrscheinlich alle Compilation Units (Eine Datei je Compilation Unit)
- Was macht er SourceCodeDecorator? Warum müssen wir von ihm loskommen?

Neue Aufgaben:
- Bei SEFF einsteigen

Notizen zum Meeting:
- Type Bindung -> Über die Bindings gehen und schauen wo die Methode überhaupt ist
  - Korrespondierende Methodendeklaration aus dem Aufruf finden (über andere Compilation Unit gehen)
  - AST Parser erstellt Bindungs erst später, deswegen ist das getrennt
- Im besten Falle migirieren zu den JDT Visitatoren
  - Verwenden von den bereitgestellten Bibliotheken
- Filtern von AbstractTypeDeclaration mit instanceof
- SourceCodeDecorator -> Datenhaltungsablage
  - Ziel ist es durch was sinnvolleres zu ersetzen, es soll komplett rausfallen
  - Alle Informationen wurden da reingeschmissen
  - Speichern zu welcher Methodendeklaration der SEFF gehört (SEFF to Method Mapping)
    - Start ist meist Schnittstellenfunktion
    - Wir bekommen HashMap/Liste für welche Methoden eine SEFF erstellt wird
  - Ersetzen der Klasse durch eine HashMap
- Start ist Ast2Seff Job
- Test Cases kopieren und erweitern, um alle einfachen Fälle noch immer abzudecken und Seiteneffekte zu beobachten/vermeiden

### 30.05
Bearbeitete Aufgaben:
- Implementierung einer neuen ASTVisitor-Unterklasse (Ast2SeffVisitor)
  - Innere Verschachtelungen werden auch berücksichtigt 
- Ersten Ast2Seff Test geschrieben
  - Einbindung einer Beispielklasse, welche in einen AST geparst wird
- Weitere Analyse von alten Visitor-Klassen

Fragen:
- Wie tief betrachten wir den AST einer Methode?
- Passt die Vorgehensweise unserer Implementierung?
- Wie kann man ein SEFF Objekt grafisch im Editor anzeigen?

Neue Aufgaben:
- Eine Methode ruft andere Methode auf
- Parameterübergabe
- Switch Case
- External Calls

Notizen zum Meeting:
- SEFF brauch ein Root Element wo man das ranhängt
- GetInterfaceSourceCodeLink -> Sind die Methoden, die ein SEFF benötigen

### 07.06

Bearbeitete Aufgaben:
- External Calls implementiert
- Switch Case implementiert
- Erweiterte XML Generierung (Repository + Component) umgesetzt

Fragen:
- Wie sollen Switch Expressions im SEFF modeliert werden?
  - Je Switch Case ein Branch? -> Ja, Verhalten kann redundant modeliert sein
- Sind andere Bibliotheken External oder Internal Calls?

Neue Aufgaben:
- Testfälle für die visit-Methoden
- Alle Kontrollstrukturen aus den alten Visitatoren einbauen
  - visit-Methode für EnhancedForStatement 
- External Calls korrekt umsetzen und richtig referenzieren

Notizen zum Meeting:
- Was ist der vollquantifizierte Paketname einer Methode?
  - Wenn es mit java. anfängt 
  - Liste von Liste von Zeichenketten um zu überprüfen
- Type Bindings
- Source Code Decorator
- Wir müssen noch die lokalen Variablen deklarieren

### 13.06

Bearbeitete Aufgaben:
- Directory Scan Test Case implementiert
- externalCalls weiter implementiert
- visit-Methode für EnhancedForStatement implementiert
- visit-Methode für SynchronizedStatement implementiert
- visit-Methode für IfStatements mit else if Blöcken repariert
- Erstellung der Test-Klassen für die einzelnen Visitatoren
  - Erster Tests für den IfStatement-Visitor implementiert

Neue Aufgaben:
- External Calls weiter implementieren
  - Komponentenzugehörigkeit und Interface mit Provided Schnittstelle implementieren
- Wie können Variablen Declarations umgesetzt werden
- 

Notizen zum Meeting:
- Für die Type Bindings muss ein Directory Scan umgesetzt werden
  - Ein Scan einzelner Klassen führt nicht zur Setzung der Type Bindings
- Wir linken gar nicht auf den SEFF
  - Komponente stellt Schnittstelle bereit
  - SEFF ist in der Komponente
  - Komponenten ruft externen SEFF auf (andere Schnittstelle)
  - Eigentlich wird nur auf die Schnittstelle gelinkt
  - Zu Beginn 
- Wir bekommen welche Methode zur welcher Komponente
  - HashMap mit der Zugehörigkeit wird bereitgestellt
- VariablenDeclaration
  - Sind interessant für Verzweigungen und Schleifen
  - Ist die Variable immer true?
  - Hat die Liste sehr viele Elemente?
  - Wir sollen schauen, wie wir Variablen im SEFF setzen können

### 21.06

Bearbeitete Aufgaben:
- Provides / Requires Pfeile fixen
- BasicComponent Namen setzen
- PassiveResources Namen richtig setzen

Fragen:
- 

Neue Aufgaben:
- Provides / Requires Pfeile fixen
- BasicComponent Namen setzen
- Interface methoden parameter setzen
- Übergabeparameter hinzufügen
- PassiveResources Namen richtig setzen
- Methoden Testen
- Aufräumen
- Weitere Namen setzen
- SimpleClass.callExternalMethod umbenennen + seff für internalFunctionCalls erstellen

Notizen zum Meeting:
- 

### 27.06

Bearbeitete Aufgaben:
- Provides / Requires Pfeile fixen
- BasicComponent Namen setzen
- Interface methoden parameter setzen
- Übergabeparameter hinzufügen
- PassiveResources Namen richtig setzen
- Methoden Testen
- Aufräumen
- Weitere Namen setzen
- SimpleClass.callExternalMethod umbenennen + seff für internalFunctionCalls erstellen
---------------------------
- Master in Seff-Branch mergen
- Variablen für external Calls (nachschauen im MediaStore)
- Set Variable Action modellieren (erstmal für Primitive Datentypen, danach die Klassen Datentypen)
- Variablendeklarationen auch modellieren

Neue Aufgaben:
- Private und öffentliche Methoden die nicht in der SEFF-Liste stehen müssen auch generiert werden
- STRG F -> Suchen im alten Quelltext, für was eine Interne Action generiert wird (wshl. im JaMoPP Visitor)
- Composite- und Collection Data Types für Parameter erstellen und setzen
- Master in Seff-Branch mergen
- Variablen für external Calls (nachschauen im MediaStore)
- Variablendeklarationen auch modellieren
- Set Variable Action modellieren (erstmal für Primitive Datentypen, danach die Klassen Datentypen)
- Listenarten als Collection Data Type modellieren
- Simple Statement modellieren (JaMoPPVisitor)
- Aufschreiben: Was wurde im JaMoPPVisitor erstellt, wie ist das Mapping auf unsere eingenen Methoden
  - Dieser Inhalt kommt in unsere Dokumentation
  - Tabelle erstellen für JaMoPP und Modisco Quelltext
  - Implementierung soll sich gleich verhalten
- Schauen, was in den Palladio Modisco Visitatoren erstellt wurde 
  - https://github.com/PalladioSimulator/Palladio-ReverseEngineering-SoMoX-MoDisco 
- https://sdqweb.ipd.kit.edu/publications/pdfs/krogmann2010a.pdf 
  - Als Hauptreferenz verstehen
- Schauen, ob wir im Media Store eine Internal Call Action wiederfinden
- Weitere Namen setzen
- Alte Classen / Alten Code raus moven

Notizen zum Meeting:
- Wie weit steigen wir im Baum ab, ab welchem Level machen wir eine Internal Action?
- Condition Felder auslassen und @position string wie im alten Model setzen
- Collection Data Type (Struct) und Composite Data Type 
  - Bibliothek für das Erstellen von Data Types verwenden
  - https://github.com/PalladioSimulator/Palladio-Addons-FluentApiModelGenerator/blob/master/documentation/repository.md#data-types
- https://svnserver.informatik.kit.edu/i43/svn/code/CaseStudies/MediaStore3/trunk/Model/MediaStore3_Model/
  - UserId: anonymous PW: anonymous   
  - https://sdqweb.ipd.kit.edu/wiki/Anonymous_SVN_Access 
- Aktive Teilnahme in den Abschlusspräsentationen
- Gut vorbereitete Abschlusspräsentation
- Bei Branch, Condition in Name, nicht zwingend in Condition Feld (Probleme bei Zuweisung wegen Typ)
- Fluent API einbinden für Datentype modellierungen
- Bei MediaStore3 iFileStorage für return
- https://bwsyncandshare.kit.edu/s/eYmydsSWMSxCmdk

### 04.07

Fragen:
- Was ist Stoex? (https://updatesite.palladio-simulator.com/archive/pcm_archive/revisions/2009-03-07_PCM_javadoc/de/uka/ipd/sdq/stoex/StoexPackage.html)
- Variablen aktuell anhand von übergabe, nicht anhand von requiredInterface
- representations.aird resetten?
- Try Catch Namen?
- OutputVariableUsageCompartment?

### JaMoPPStatemenVisitor

| **Alte Implementierung** | **Aktuelle Implementierung**                       |
|--------------------------|----------------------------------------------------|
| LoopStatement            | ForStatement, EnhancedForStatement, WhileStatement |
| Switch                   | SwitchStatement                                    |
| TryBlock                 | TryStatement                                       |
| ClassMethod              | ExpressionStatement                                |
| SynchronizedBlock        | SynchronizedStatement                              |
| SimpleStatement          | ExpressionStatement                                |
| Condition                | IfStatement                                        |

### Modisco GastStatementVisitor

| **Alte Implementierung** | **Aktuelle Implementierung** |
|--------------------------|------------------------------|
| EnhancedForStatement     | EnhancedForStatement         |
| SwitchStatement          | SwitchStatement              |
| TryStatement             | TryStatement                 |
| ClassMethod              | ExpressionStatement          |
| SynchronizedStatement    | SynchronizedStatement        |
| ExpressionStatement      | ExpressionStatement          |
| IfStatement              | IfStatement                  |
| WhileStatement           | WhileStatement               |
| ForStatement             | ForStatement                 |
| ReturnStatement          | ReturnStatement              |
| Block                    |                              |

### HowTo: Fix representation.aird

- Alles aus XML-Element "diagram:DSemanticDiagram" lÃ¶schen (nicht sicher ob nÃ¶tig)
- rechtsklick auf Projekt Bundle indem representation.aird enthalten ist
- Copy
- Mit Paste als neues Projekt anlegen
- Representations fÃ¼r repositories neu erstellen
- ???
- Profit

### Offene Aufgaben (Aufschrieb Fabi)

- PrÃ¤sentation
- Readme
- Variablendeklaration modellieren
- Composite/Collection Data Types modellieren
  - FluentApi Einsatz?
- Benamung aller SEFF-Elemente
- Code aufrÃ¤umen
- FluentAPI Ã¼berprÃ¼fen

Neue Aufgaben (Aufschrieb Marcel):
- Variablen aus Interface ziehen anstatt von Ãœbergabe Parametern
- return Variablen setzen
- Fluent API checken

Fragen:
- External call bei Ã¼berladenen Funktionen (welche wird aufgerufen?)
- Wie sollen Variablen aussehen? MediaStore gibt nicht viel her

Bearbeitete Aufgaben:
- Composite/Collection Data Types modellieren (ohne weitere parameter)
- Variablen aus Interface ziehen anstatt von Ãœbergabe Parametern
- return Variablen setzen (bisher nur primitive, muss refactored werden)
- FluentAPI Ã¼berprÃ¼fen

### Offene Aufgaben (Aufschrieb Marcel 22.08)
- Aufr�umen
- ToDo f�r Probabilistic setzen 
- compositeDataType
- checkstyle (SpotBugs Eclipse Plugin)
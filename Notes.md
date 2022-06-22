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

Notizen zum Meeting:
- 
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

Aufghaben:
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
- Was beinhaltet Root? Alle Methoden? Alle Pakete? Was wird hier übergeben?
- Was beinhaltet die Collection bei addModels?
- Uns fällt es schwer einen Startpunkt zu sehen. Wo sollten wir beginnen?
  - Wir haben uns X überlegt
- Was macht er SourceCodeDecorator? Warum können wir von ihm loskommen?

Neue Aufgaben:
- 

Notizen zum Meeting:
- 
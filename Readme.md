## Opis

Prosta aplikacja w javafx służąca do zarządzania zadaniami. Baza danych znajduje się na chmurze mongodb atlas.

## Funkcjonalności
> CRUD dla encji Person, Project, Assignment<br>
> Pasek postępu w widoku jest zależny od zmiennej 'completed' w encji Assignment<br>
> Eksportowanie danych do pliku w formacie JSON lub XML<br>
> Importowanie danych z pliku w formacie JSON lub XML<br>
> Wyświetlenie diagramu poglądowego dla postępu w realizacji zadania<br>
> Testy jednostkowe dla serwisów

## Użyte wzorce projektowe
[Builder](src/main/java/ganttchart/model/builders/AssignmentBuilder.java)<br>
[Adapter](src/main/java/ganttchart/serialization/adapter/MembersAdapter.java)<br>
[Strategy](src/main/java/ganttchart/serialization/XMLSerializator.java)<br>
[Singleton](src/main/java/ganttchart/util/ConnectionManager.java)<br>


## Screenshot

![Screenshot](example1.png)

![Screenshot](example.png)


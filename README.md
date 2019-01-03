# LOGO
Projekt realizowany w ramach ćwiczeń: Technologie Obiektowe 2

## Uruchomienie programu
Chcąc uruchomić projekt, należy użyć komendy: gradle run

## Cel ćwiczenia
Celem tej aplikacji jest stworzenie platformy wspomagającej naukę programowania dla dzieci w wieku szkolnym. <br>
Aplikacja ta podzielona będzie na dwie części:
1. Część przechowująca zbiory zadań
2. Część interaktywna, w której użytkownik może pisać komendy <br>
Zadaniem użytkownika będzie stworzenie takiego obrazka, jaki ukazany jest w treści ćwiczenia.

## Budowa UI
Aplikacja składa się z czterech obsarów:
1. Okna pokazującego położenie żółwia oraz wszystkie linie jakie narysował
2. Obszaru na tekst, do którego wpisywane są kolejne polecenia
3. Przycisków [Clear] oraz [Run], które to albo czyszczą ekran albo przesyłają aktualną komendę, znajdującą się w polu tekstowym, do systemu
4. Okienka pokazującego stopień rozwiązania zadania
5. Przycisków [<] oraz [>], pozwalających na łatwe zmienianie zadań

## Zasada działania programu
Po wpisaniu polecenia i zatwierdzeniu go przyciskiem [Run], tekst znajdujący się w polu tekstowym przesyłany jest do systemu, który to parsuje go, a następnie uaktualnia pozycję żółwia oraz [listę narysowanych wektorów]. <br>
[lista narysowanych wektorów] - jest to lista, która przechowuje wszystkie widoczne wektory narysowane przez żółwia w danej sesji. <br>
W momencie kiedy [lista narysowanych wektorów] jest równa [liście wektorów zawatych w treści ćwiczenia] zadanie zostaje uznane za zaliczone. Wynik rozwiązania można zobaczyć po prawej stronie, nad przyciskami [Clear] oraz [Run] <br>
Porównanie to dokonywane jest przy każdorazowej aktualizacji [listy narysowanych wektorów] <br>
Przycisk [Clear] czyści aktualną sesję i rozpoczyna nową.

## milestone1 - co już mamy?
- Zrobiliśmy interaktywne GUI, będące w stanie obsługiwać podstawowe komendy { NP [liczba], LW [liczba], PW [liczba] }
	- NP [liczba] - ruch żółwia do przodu o [liczba] pikseli
	- LW [liczba] - obrót żółwia o [liczba] stopni w lewo
	- PW [liczba] - obrót żółwia o [liczba] stopni w prawo
- Dodaliśmy ćwiczenie, którego wykonanie można sprawdzić w konsoli - kiedy jest wykonane, wypisywana jest wartość true
	- Zadaniem tym jest kwadrat będący do narysowania, którego to wierzchołki znajdują się odpowiednio w punktach { A( 0, 0 ), B( 100, 0 ), C( 100, -100 ), D( 0, -100 ) }
	- Rozwiązaniem jest zbiór wektorów { AB, BC, CD, DA }
### Jak zaliczyć zadanie?
- przykładowe komendy do zaliczenia zadania:
	- np 100 pw 90 np 100 pw 90 np 100 pw 90 np 100 pw 90
	- np 50 np 50 pw 90 np 50 np 50 pw 90 np 50 np 50 pw 90 np 50 np 50 pw 90
- można te komendy również podzielić na mniejsze i każdorazowo wciskać [Run]
	- np 100 pw 90, a potem wcisnąć 4x [Run] - po wciśnięciu [Run] tekst z pola tekstowego nie jest usuwany, co może pomóc w testowaniu

## milestone2 - co nowego wprowadziliśmy?
- uaktualniliśmy widok
	- wprowadziliśmy obsługę zmiany zadań oraz wyświetlanie wyniku
- plik ex.txt, zawierający w sobie zadania do wykonania
	- zadania oddzielane są od siebie znakiem "/"
	- ładowane są one podczas startu do programu
	- chcąc dodać nowe zadanie, należy dodać znak "/" po ostatnim zadaniu, a następnie wpisać własne
	- do nawigacji między zadaniami można używać przycisków [<] oraz [>]
- Ocenianie zadań
	- program sprawdza liczbę komend użytych do narysowania zadania
	- liczba ta porównywana jest później z liczbą komend użytych w ćwiczeniu
	- są trzy możliwe oceny zadania:
		- UNSOLVED - jeżeli zadanie nie zostało zaliczone
		- SOLVED - jeżeli zadanie zostało zaliczona, ale nie w najbardziej optymalny sposób
		- IDEAL - jeżeli zadanie zostało zaliczone co najmniej tak dobrze jak w ćwiczeniu
	- oceny te przypisywane są do konkretnego zadania, a ich wynik zapisywany jest później do pliku ex.dat
	- zapisywane jest najlepsze podejście
- komenda powtórz
	- składnia komendy: powtórz [liczba] \\[ { komendy } \\]
	- przykłady: { powtórz 4 [ np 100 pw 90 ], powtórz 3 [ np 100 lw 120 ] }
	- UWAGA: wszystkie nawiasy kwadratowe muszą być oddzielone od innych wyrazów białym znakiem
		- "powtórz 4 [ np 100 lw 90 ]" - ok
		- "powtórz 4 [np 100 lw 90]" - nie ok
	- przy ocenianiu zadań komenda powtórz traktowana jest jako jedna komenda + liczba komend w środku pętli
		- "powtórz 4 [ np 100 lw 90 ]" - 3 komendy
		- "np 100 lw 90 np 100 lw 90 np 100 lw 90 np 100 lw 90" - 8 komend

## Q&A
- Mam problem z załadowaniem projektu przy pomocy Gradle'a
	- Sprawdź jakiej wersji gradle'a używasz. Dla wersji 5.0 wszystko działa poprawnie
	- Może to być problem dotyczący jego wersji. Jeżeli masz zainstalowanego Gradle'a, to sprawdź jego wersję i przy ładowaniu projektu spróbuj wyspecyfikować, aby użyć tej wersji jaką masz u siebie. Nam pomogło :) - testowaliśmy to dla wersji 4.10.2
- Nie widzi mi biblioteki javafx
	- Zacznij od sprawdzenia czy masz taką bibliotekę. W przypadku systemu Windows jest ona dołączana wraz z całym pakietem JDK, jednakże na dystrybucjach Linuxa trzeba ją pobrać oddzielnie (sudo apt-get install openjfx).
	- Jeżeli dalej jest problem, to możesz zastosować pewnien trick, polegający na odłączeniu biblioteki JRE z projektu, a następnie ponownym jej załadowaniu. Metodę tą potwierdziły nasze doświadczenia :)

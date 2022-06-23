<div class="sidenav">
	<a href="/BookStore/pretraga/pretraziKnjige">Pretraga kataloga</a>
	<a href="/BookStore/pretraga/pretraziKnjizevneVeceri">Pretraga knjizevnih veceri</a>
	<sec:authorize access="hasRole('Administrator')">
		<a href="/BookStore/adminKatalog/dodajPisca">Dodaj novog pisca</a>
		<a href="/BookStore/adminKatalog/obrisiPisca">Obrisi Pisca</a>
		<a href="/BookStore/adminKatalog/dodajJezik">Dodaj novi Jezik</a>
		<a href="/BookStore/adminKatalog/obrisiJezik">Obrisi Jezik</a>
		<a href="/BookStore/adminKatalog/dodajKategoriju">Dodaj novu Kategoriju</a>
		<a href="/BookStore/adminKatalog/obrisiKategoriju">Obrisi Kategoriju</a>
		<a href="/BookStore/adminKatalog/dodajKnjigu">Dodaj novu knjigu</a>
		<a href="/BookStore/adminZaposleni/sviZaposleni">Prikazi zaposlene</a>
		<a href="/BookStore/report/allReports">Izvestaji</a>
	</sec:authorize>
	<sec:authorize access="hasAnyRole('Administrator', 'Zaposleni')">
		<a href="/BookStore/narudzbine/pretraziNarudzbine">Narudzbine</a>
	</sec:authorize>
	<sec:authorize access="hasRole('Korisnik')">
		<a href="/BookStore/korisnik/prikazOmiljenihKnjiga">Omiljene knjige</a>
		<a href="/BookStore/korisnik/prikazPrijavljenihKV">Prijavljene knjizevne veceri</a>
	</sec:authorize>
	<sec:authorize access="!isAuthenticated()">
		<a href="/BookStore/auth/loginPage">Uloguj se</a>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<a href="/BookStore/auth/logout">Odjava</a>
	</sec:authorize>
</div>
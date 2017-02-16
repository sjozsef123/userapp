package solomonj.msg.userapp.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IPublicationService;
import solomonj.msg.userapp.jpa.model.Article;
import solomonj.msg.userapp.jpa.model.Author;
import solomonj.msg.userapp.jpa.model.Book;
import solomonj.msg.userapp.jpa.model.Magazine;
import solomonj.msg.userapp.jpa.model.Newspaper;
import solomonj.msg.userapp.jpa.model.Publication;

@Named("publicationmanagedbean")
@ApplicationScoped
public class PublicationManagedBean implements Serializable {

	private static final long serialVersionUID = 1565015472267456236L;

	private Logger oLogger = Logger.getLogger(PublicationManagedBean.class);
	private IPublicationService publicationBean;
	private List<Publication> publicationList;
	private String filter;

	private Book book;
	private Magazine magazine;
	private Newspaper newspaper;
	
	public void onLoad() {

		System.out.println("clear");
		book = null;
		magazine = null;
		newspaper = null;
		filter = "";
	}
	

	public IPublicationService getpublicationBean() {
		if (publicationBean == null) {
			try {
				InitialContext jndi = new InitialContext();
				publicationBean = (IPublicationService) jndi.lookup(IPublicationService.jndiNAME);
			} catch (NamingException e) {

				oLogger.error("Can't get bean");
			}
		}
		return publicationBean;
	}

	public String getType(Publication pub) {
		return pub.getClass().getSimpleName();
	}

	public void deletePublication(Publication publication) {

		try {

			publicationBean.deletePublication(publication);
		} catch (ServiceException e) {

			oLogger.error("Failed to delete publication");
		}
	}

	public List<Publication> getPublicationList() {

		publicationList = new ArrayList<>();

		try {
			publicationList = getpublicationBean().filterPublicationByName(filter);
			return publicationList;
		} catch (ServiceException e) {

			oLogger.equals("Failed to query publication list.");
			return publicationList;
		}
	}

	public void setPublicationList(List<Publication> publicationList) {
		this.publicationList = publicationList;
	}
	
	public void createBook() {
		book = new Book();
	}
	
	public void createMagazine() {
		magazine = new Magazine();
	}

	public void createNewspaper() {
		newspaper = new Newspaper();
	}

	public void setBookTitle(String title) {
		book.setTitle(title);
	}
	
	public void setMagazineTitle(String title) {
		magazine.setTitle(title);
	}
	
	public void setNewspaperTitle(String title) {
		newspaper.setTitle(title);
	}

	public void setBookStock(String sStock) {

		int stock = Integer.parseInt(sStock);
		book.setCopiesLeft(stock);
		book.setNrOfCopies(stock);
	}
	
	public void setMagazineStock(String sStock) {

		int stock = Integer.parseInt(sStock);
		magazine.setCopiesLeft(stock);
		magazine.setNrOfCopies(stock);
	}
	
	public void setNewspaperStock(String sStock) {

		int stock = Integer.parseInt(sStock);
		newspaper.setCopiesLeft(stock);
		newspaper.setNrOfCopies(stock);
	}

	public void setBookPublisher(String publisher) {
		book.setPublisher(publisher);
	}
	
	public void setMagazinePublisher(String publisher) {
		magazine.setPublisher(publisher);
	}
	
	public void setNewspaperPublisher(String publisher) {
		newspaper.setPublisher(publisher);
	}

	public void setBookReleaseDate(String year) {

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(year));
		book.setReleaseDate(calendar.getTime());
		System.out.println(book.getReleaseDate());
	}

	public void setMagazineReleaseDate(String year, String month) {

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(year));
		calendar.set(Calendar.MONTH, Integer.parseInt(month));
		magazine.setReleaseDate(calendar.getTime());
	}

	public void setNewspaperReleaseDate(String year, String month, String day) {

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(year));
		calendar.set(Calendar.MONTH, Integer.parseInt(month));
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
		newspaper.setReleaseDate(calendar.getTime());
	}

	public void setBookAuthors(String[] pAuthors) {

		List<Author> authors = new ArrayList<>();

		for (String string : pAuthors) {
			String[] parts = string.split(",");

			Author author = new Author();
			author.setId(Integer.parseInt(parts[0]));
			author.setName(parts[1]);

			authors.add(author);
		}
		book.setbAuthors(authors);
	}

	public void setMagazineAuthors(String[] pAuthors) {

		List<Author> authors = new ArrayList<>();

		for (String string : pAuthors) {
			String[] parts = string.split(",");

			Author author = new Author();
			author.setId(Integer.parseInt(parts[0]));
			author.setName(parts[1]);

			authors.add(author);
		}
		magazine.setmAuthors(authors);
	}
	
	public void setNewspaperArticles(String[] pArticles) {

		List<Article> articles = new ArrayList<>();

		for (String string : pArticles) {

			String[] parts = string.split(",");

			Article article = new Article();
			article.setId(Integer.parseInt(parts[0]));
			article.setTitle(parts[1]);

			articles.add(article);
		}
		newspaper.setArticles(articles);
	}
	
	public void castItem(Publication publication) {
		switch(publication.getClass().getSimpleName()) {
		case "Book":
				book = (Book) publication;
				magazine = null;
				newspaper = null;
			break;
		case "Magazine":
				magazine = (Magazine) publication;
				book = null;
				newspaper = null;
			break;
		case "Newspaper":
				newspaper = (Newspaper) publication;
				book = null;
				magazine = null;
			break;
		}
	}

	public void addBook() {
		System.out.println("added");
		try {
			publicationBean.createPublication(book);
			book = null;
		} catch (ServiceException e) {
			oLogger.equals("Failed to create book.");
		}
	}
	
	public void finalizeBookEdit() {
		
		try {
			publicationBean.updatePublication(book);
			book = null;
		} catch (ServiceException e) {
			oLogger.error("Failed to update book.");
		}
		
	}

	public void addMagazine() {

		try {
			publicationBean.createPublication(magazine);
			magazine = null;
		} catch (ServiceException e) {
			oLogger.equals("Failed to create magazine.");
		}
	}
	
	public void finalizeMagazineEdit() {
		
		try {
			publicationBean.updatePublication(magazine);
			magazine = null;
		} catch (ServiceException e) {
			oLogger.error("Failed to update magazine");
		}
	}

	public void addNewspaper() {

		try {
			publicationBean.createPublication(newspaper);
			newspaper = null;
		} catch (ServiceException e) {
			oLogger.equals("Failed to create newspaper.");
		}
	}
	
	public void finalizeNewspaperEdit() {
		
		try {
			publicationBean.updatePublication(newspaper);
			newspaper = null;
		} catch (ServiceException e) {
			oLogger.error("Failed to update newspaper");
		}
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public void clearFilter() {

		filter = "";
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Magazine getMagazine() {
		return magazine;
	}

	public void setMagazine(Magazine magazine) {
		this.magazine = magazine;
	}

	public Newspaper getNewspaper() {
		return newspaper;
	}

	public void setNewspaper(Newspaper newspaper) {
		this.newspaper = newspaper;
	}
	
	
}

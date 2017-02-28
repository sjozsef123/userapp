package solomonj.msg.userapp.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.primefaces.event.RowEditEvent;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IPublicationService;
import solomonj.msg.appuser.common.util.PublicationFilter;
import solomonj.msg.userapp.jpa.model.Article;
import solomonj.msg.userapp.jpa.model.Author;
import solomonj.msg.userapp.jpa.model.Book;
import solomonj.msg.userapp.jpa.model.Magazine;
import solomonj.msg.userapp.jpa.model.Newspaper;
import solomonj.msg.userapp.jpa.model.Publication;

/**
 * Managed bean for publications.
 * 
 * @author Majai Robert
 *
 */
@Named("publicationmanagedbean")
@SessionScoped
public class PublicationManagedBean implements Serializable {

	private static final long serialVersionUID = 1565015472267456236L;

	// private Logger oLogger = Logger.getLogger(PublicationManagedBean.class);
	private IPublicationService publicationBean;
	private List<Publication> publicationList;
	private String titleFilter;

	private Book book;
	private Magazine magazine;
	private Newspaper newspaper;

	private PublicationFilter publicationFilter;
	private List<String> selectedAuthors;
	private List<String> selectedArticles;

	public PublicationManagedBean() {

		publicationFilter = new PublicationFilter();
		selectedAuthors = new ArrayList<>();
		selectedArticles = new ArrayList<>();
	}

	@PostConstruct
	public void init() {

		publicationList = new ArrayList<>();
		try {
			publicationList = getpublicationBean().getPublicationByFilter(publicationFilter);
		} catch (ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
	}
	
	public void newPubs() {
		
		book = new Book();
		magazine = new Magazine();
		newspaper = new Newspaper();
		
	}

	public void onLoad() {


		book = new Book();
		magazine = new Magazine();
		newspaper = new Newspaper();
	}

	public IPublicationService getpublicationBean() {
		if (publicationBean == null) {
			try {
				InitialContext jndi = new InitialContext();
				publicationBean = (IPublicationService) jndi.lookup(IPublicationService.jndiNAME);
			} catch (NamingException e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						LoginManagedBean.getResourceBundleString("publication.naming"), null));
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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
	}

	public boolean selectedBAuthors(Book book) {

		selectedAuthors.clear();
		List<Author> authors = book.getbAuthors();

		for (Author a : authors) {
			selectedAuthors.add(new Integer(a.getId()).toString());
		}
		return true;
	}

	public boolean selectedMAuthors(Magazine book) {

		selectedAuthors.clear();
		List<Author> authors = book.getmAuthors();

		for (Author a : authors) {
			selectedAuthors.add(new Integer(a.getId()).toString());
		}
		return true;
	}
	
	public boolean selectedArticles(Newspaper newspaper) {
		
		selectedArticles.clear();
		List<Article> articles = newspaper.getArticles();
		
		for(Article a: articles) {
			selectedArticles.add(new Integer(a.getId()).toString());
		}
		return true;
	}

	public List<Publication> getPublicationList() {

		return publicationList;
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
		switch (publication.getClass().getSimpleName()) {
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
		try {
			
			List<Author> bAuthors = new ArrayList<>();
			for (String author : selectedAuthors) {
				bAuthors.add(new Author(Integer.parseInt(author)));
			}
			book.setbAuthors(bAuthors);
			book.setCopiesLeft(book.getNrOfCopies());
			selectedAuthors.clear();
			publicationBean.createPublication(book);
			init();
			newPubs();
		} catch (ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
	}

	public void finalizeBookEdit() {

		try {
			publicationBean.updatePublication(book);
		} catch (ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}

	}

	public void addMagazine() {

		try {
			
			List<Author> mAuthors = new ArrayList<>();
			for (String author : selectedAuthors) {
				mAuthors.add(new Author(Integer.parseInt(author)));
			}
			magazine.setmAuthors(mAuthors);
			magazine.setCopiesLeft(magazine.getNrOfCopies());
			selectedAuthors.clear();
			publicationBean.createPublication(magazine);
			init();
			newPubs();
		} catch (ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
	}

	public void finalizeMagazineEdit() {

		try {
			publicationBean.updatePublication(magazine);
			magazine = null;
		} catch (ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
	}

	public void addNewspaper() {

		try {
			List<Article> articles = new ArrayList<>();
			for (String article : selectedArticles) {
				articles.add(new Article(Integer.parseInt(article)));
			}
			newspaper.setArticles(articles);
			newspaper.setCopiesLeft(newspaper.getNrOfCopies());
			selectedArticles.clear();
			publicationBean.createPublication(newspaper);
			init();
			newPubs();
		} catch (ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
	}

	public void finalizeNewspaperEdit() {

		try {
			publicationBean.updatePublication(newspaper);
			newspaper = null;
		} catch (ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
	}

	public String getFilter() {
		return titleFilter;
	}

	public void setFilter(String filter) {
		this.titleFilter = filter;
	}

	public void clearFilter() {

		publicationFilter = new PublicationFilter();
		init();
	}

	public boolean isStockValid(int maxStock, int editedCurrentStock) {

		return maxStock > editedCurrentStock ? true : false;
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

	public PublicationFilter getPublicationFilter() {
		return publicationFilter;
	}

	public void setPublicationFilter(PublicationFilter publicationFilter) {
		this.publicationFilter = publicationFilter;
	}

	public List<String> getSelectedAuthors() {
		return selectedAuthors;
	}

	public void setSelectedAuthors(List<String> selectedAuthors) {
		this.selectedAuthors = selectedAuthors;
	}
	
	

	public List<String> getSelectedArticles() {
		return selectedArticles;
	}

	public void setSelectedArticles(List<String> selectedArticles) {
		this.selectedArticles = selectedArticles;
	}

	public void onRowEdit(RowEditEvent event) {

		try {
			switch (event.getObject().getClass().getSimpleName()) {
			case "Book":
				List<Author> bAuthors = new ArrayList<>();
				for (String author : selectedAuthors) {
					bAuthors.add(new Author(Integer.parseInt(author)));
				}
				Book book = (Book) event.getObject();
				book.setbAuthors(bAuthors);
				getpublicationBean().updatePublication(book);
				selectedAuthors.clear();
				break;
			case "Magazine":
				List<Author> mAuthors = new ArrayList<>();
				for(String author: selectedAuthors) {
					mAuthors.add(new Author(Integer.parseInt(author)));
				}
				Magazine magazine = (Magazine) event.getObject();
				magazine.setmAuthors(mAuthors);
				getpublicationBean().updatePublication(magazine);
				selectedAuthors.clear();
				break;
			case "Newspaper":
				getpublicationBean().updatePublication((Newspaper) event.getObject());
				break;
			}
			init();
			FacesMessage msg = new FacesMessage("Publication Edited", ((Publication) event.getObject()).getTitle());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (ServiceException e) {
			e.printStackTrace();
			getClass();
			FacesMessage msg = new FacesMessage("Edit Failed", ((Publication) event.getObject()).getTitle());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	public void onRowCancel(RowEditEvent event) {

		FacesMessage msg = new FacesMessage("Edit Cancelled", ((Publication) event.getObject()).getTitle());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		selectedAuthors.clear();
	}

}

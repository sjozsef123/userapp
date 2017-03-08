import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

//import org.junit.Test;

import solomonj.msg.userapp.jpa.model.Article;
import solomonj.msg.userapp.jpa.model.Author;
import solomonj.msg.userapp.jpa.model.Book;
import solomonj.msg.userapp.jpa.model.Magazine;
import solomonj.msg.userapp.jpa.model.Newspaper;
import solomonj.msg.userapp.jpa.model.Publication;
import solomonj.msg.userapp.jpa.util.JaxB;

public class JaxbTest {

//	@Test
	public void test() {
		
		Book book = new Book();
		book.setTitle("book");
		List<Author> bauthors = new ArrayList<>();
		bauthors.add(new Author(1, "name1"));
		bauthors.add(new Author(2, "name2"));
		book.setbAuthors(bauthors);
		
		Magazine magazine = new Magazine();
		magazine.setTitle("magazine");
		List<Author> mauthors = new ArrayList<>();
		mauthors.add(new Author(1, "name1"));
		mauthors.add(new Author(2, "name2"));
		magazine.setmAuthors(mauthors);
		
		Newspaper newspaper = new Newspaper();
		newspaper.setTitle("newspaper");
		List<Article> articles = new ArrayList<>();
		articles.add(new Article(1, "art1"));
		articles.add(new Article(2, "art2"));
		newspaper.setArticles(articles);
		
//		JaxB.marshall(book);
		
		List<Publication> publications = new ArrayList<>();
		publications.add(book);
		publications.add(magazine);
		publications.add(newspaper);
		
//		try {
//			JaxB.marshal(publications, Publication.class, "publications", new FileOutputStream(new File("target/out.xml")));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		List<Publication> unpub = JaxB.unmarshal(Publication.class, "target/out.xml");
//		System.err.println(unpub.size());
	}
	
}
